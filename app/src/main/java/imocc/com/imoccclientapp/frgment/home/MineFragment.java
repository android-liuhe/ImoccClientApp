package imocc.com.imoccclientapp.frgment.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import imocc.com.imoccclientapp.R;
import imocc.com.imoccclientapp.frgment.BaseFragment;

/**
 * Created by liuhe on 2018/5/13.
 */

public class MineFragment extends BaseFragment {


    private View mineView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        mineView = inflater.inflate(R.layout.fragment_mine_layout, container, false);
        Log.d(TAG, "MineFragment onCreateView: ");
        return mineView;
    }
}
