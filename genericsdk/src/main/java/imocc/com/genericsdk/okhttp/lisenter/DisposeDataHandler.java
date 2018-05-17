package imocc.com.genericsdk.okhttp.lisenter;

/**
 * Created by liuhe on 2018/5/13.
 */

public class DisposeDataHandler {

    public DisposeDataLisenter mLienter = null;
    public Class<?> mClass = null;

    public DisposeDataHandler(DisposeDataLisenter mLienter) {
        this.mLienter = mLienter;
    }

    public DisposeDataHandler(DisposeDataLisenter mLienter, Class<?> mClass) {
        this.mLienter = mLienter;
        this.mClass = mClass;
    }
}
