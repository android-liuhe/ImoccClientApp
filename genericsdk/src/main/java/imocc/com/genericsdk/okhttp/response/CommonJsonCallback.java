package imocc.com.genericsdk.okhttp.response;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.Reader;

import imocc.com.genericsdk.okhttp.exception.OkHttpException;
import imocc.com.genericsdk.okhttp.lisenter.DisposeDataHandler;
import imocc.com.genericsdk.okhttp.lisenter.DisposeDataLisenter;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
  *@author liuhe
  * @function 专门处理JSON的回调
 */

public class CommonJsonCallback implements Callback{

    //与服务器返回字段对应关系
    protected final String RESULT_CODE = "ecode"; //又返回则对http请求来说是成功的
    protected final int RESULT_CODE_VALUE = 0;
    protected final String ERROR_MSG = "emsg";
    protected final String EMPTY_MSG = "";

    /**
     * 自定义异常类型
     */
    protected final int NETWORK_ERROR = -1; //the network relative error
    protected final int JSON_ERROR = -2; //the JSON relative error
    protected final int OTHER_ERROR = -3; //the unkonw error

    private Handler mDeliveryHandler; //进行消息转发
    private DisposeDataLisenter mLisenter;
    private Class<?> mClass;

    private GsonBuilder mGsonBuild;
    private Gson mGson;

    public CommonJsonCallback(DisposeDataHandler handler) {
        this.mLisenter = handler.mLienter;
        this.mClass = handler.mClass;
        this.mDeliveryHandler = new Handler(Looper.getMainLooper());

        mGsonBuild = new GsonBuilder();
        mGson = mGsonBuild.create();

    }

    //请求失败
    @Override
    public void onFailure(Call call, final IOException e) {
        mDeliveryHandler.post(new Runnable() {
            @Override
            public void run() {
                mLisenter.onFailure(new OkHttpException(NETWORK_ERROR, e));
            }
        });
    }

    //真正的相应处理
    @Override
    public void onResponse(Call call, final Response response) throws IOException {
        mDeliveryHandler.post(new Runnable() {
            @Override
            public void run() {
                handlerResponse(response);
            }
        });
    }

    private void handlerResponse(Object response) {

        if (response == null && "".equals(response.toString().trim())){
            mLisenter.onFailure(new OkHttpException(NETWORK_ERROR, EMPTY_MSG));
            return;
        }
        mLisenter.onSuccess(response);
        try {
            JSONObject result = new JSONObject(response.toString());
            if (result.has(RESULT_CODE)){
                //从json对象中去响应码，如果为0，则是正确响应
                if (result.getInt(RESULT_CODE) == RESULT_CODE_VALUE){
                    if (mClass == null){
                        mLisenter.onSuccess(response);
                    }else{
                        //如果mClass不为null，则需要我们将json转化为实体类对象
                        Object ob = mGson.fromJson(result.toString(), mClass);
                        if (ob != null){
                            mLisenter.onSuccess(ob);
                        }else {
                            mLisenter.onFailure(new OkHttpException(JSON_ERROR, EMPTY_MSG));
                        }
                    }
                }
            }else{
                //将服务器返回的异常回调到应用去处理
                mLisenter.onFailure(new OkHttpException(OTHER_ERROR, result.get(RESULT_CODE)));
            }
        } catch (JSONException e) {
            e.printStackTrace();
            mLisenter.onFailure(new OkHttpException(OTHER_ERROR, e.getMessage()));
        }
    }
}
