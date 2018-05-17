package imocc.com.genericsdk.okhttp.lisenter;

/**
 * 自定义事件监听
 */

public interface DisposeDataLisenter {

    /**
     *  请求成功回调
     */
    public void onSuccess(Object responseObj);

    /**
     *  请求失败回调
     */
    public void onFailure(Object reasonObj);

}
