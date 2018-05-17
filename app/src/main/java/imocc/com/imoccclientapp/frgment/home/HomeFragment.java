package imocc.com.imoccclientapp.frgment.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import imocc.com.genericsdk.okhttp.CommonHttpClient;
import imocc.com.genericsdk.okhttp.lisenter.DisposeDataHandler;
import imocc.com.genericsdk.okhttp.lisenter.DisposeDataLisenter;
import imocc.com.genericsdk.okhttp.request.CommonRequest;
import imocc.com.genericsdk.okhttp.response.CommonJsonCallback;
import imocc.com.imoccclientapp.R;
import imocc.com.imoccclientapp.frgment.BaseFragment;

/**
 * Created by liuhe on 2018/5/13.
 */

public class HomeFragment extends BaseFragment{

    private View homeView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG, "HomeFragment onCreateView: ");
        homeView = inflater.inflate(R.layout.fragment_home_layout, container, false);
        initTest();
        return homeView;
    }

    private void initTest() {
        CommonHttpClient.senRequest(CommonRequest
        .creatGetRequest("https://news-at.zhihu.com/api/4/news/latest", null),
                new CommonJsonCallback(new DisposeDataHandler(new DisposeDataLisenter() {
                    @Override
                    public void onSuccess(Object responseObj) {
                        Log.d(TAG, "onSuccess: : " + responseObj.toString());
                    }

                    @Override
                    public void onFailure(Object reasonObj) {
                    }
                })));
    }
}
