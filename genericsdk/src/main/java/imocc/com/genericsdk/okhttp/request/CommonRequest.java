package imocc.com.genericsdk.okhttp.request;

import java.util.Map;

import okhttp3.FormBody;
import okhttp3.Request;

/**
 * @author liuhe
 * @function 接受请求参数，为我们生成Request对象
 */

public class CommonRequest {

    /**
     *
     * @param url
     * @param params
     * @return 返回一个创建好的PostRequest
     */
    public static Request createPostRequest(String url, RequestParams params){

        FormBody.Builder mFormBodyBuilder = new FormBody.Builder();

        if (params != null){

            //将请求参数遍历到我们的请求构件类中
            for (Map.Entry<String, String> entry : params.urlParams.entrySet()){
                mFormBodyBuilder.add(entry.getKey(), entry.getValue());
            }
        }

        //通过请求构件类获取到真正的请求对象
        FormBody mFormBody = mFormBodyBuilder.build();

        return new Request.Builder().url(url).post(mFormBody).build();
    }

    /**
     *
     * @param url
     * @param params
     * @return 返回创建好的GetRequest对象
     */
    public static Request creatGetRequest(String url, RequestParams params){

        StringBuilder stringBuilder = new StringBuilder(url).append("?");
        if (params != null){
            for (Map.Entry<String, String> entry : params.urlParams.entrySet()){
                stringBuilder.append(entry.getKey())
                        .append(entry.getValue()).append("&");
            }
        }

        return new Request.Builder().url(stringBuilder.toString()
                .substring(0, stringBuilder.length() - 1)).get().build();
    }

}
