package imocc.com.imoccclientapp.activity.home;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import imocc.com.imoccclientapp.R;
import imocc.com.imoccclientapp.frgment.home.HomeFragment;
import imocc.com.imoccclientapp.frgment.home.MessageFragment;
import imocc.com.imoccclientapp.frgment.home.MineFragment;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    private FragmentManager fragmentManager;

    private RelativeLayout mHomeLayout;
    private RelativeLayout mMessageLayout;
    private RelativeLayout mMineLayout;

    private HomeFragment mHomeFragment;
    private MessageFragment mMessageFragment;
    private MineFragment mMineFragment;

    private ImageView mHomeImage;
    private ImageView mMessageImage;
    private ImageView mMineImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actvity_home_layout);
        initView();

        //设置默认Fragment
        fragmentManager = getFragmentManager();
        mHomeFragment = new HomeFragment();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.right_content, mHomeFragment);
        fragmentTransaction.commit();
        mHomeImage.setBackgroundResource(R.drawable.comui_tab_home_selected);
    }

    private void initView() {
        mHomeLayout = findViewById(R.id.rl_home_view);
        mMessageLayout = findViewById(R.id.rl_message_view);
        mMineLayout = findViewById(R.id.rl_mine_view);
        mHomeImage = findViewById(R.id.im_home_view);
        mMessageImage = findViewById(R.id.im_message_view);
        mMineImage = findViewById(R.id.im_mine_view);

        mHomeLayout.setOnClickListener(this);
        mMessageLayout.setOnClickListener(this);
        mMineLayout.setOnClickListener(this);
    }

    /**
     * 隐藏Fragment
     */
    private void hideFragment(FragmentTransaction ft, Fragment fragment){

        if (fragment != null && ft != null){
            ft.hide(fragment);
        }
    }

    @Override
    public void onClick(View v) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        switch (v.getId()) {
            case R.id.rl_home_view:
                mHomeImage.setBackgroundResource(R.drawable.comui_tab_home_selected);
                mMessageImage.setBackgroundResource(R.drawable.comui_tab_message);
                mMineImage.setBackgroundResource(R.drawable.comui_tab_home);

                hideFragment(fragmentTransaction, mHomeFragment);

                if (mHomeFragment == null){
                    mHomeFragment = new HomeFragment();
                    fragmentTransaction.add(R.id.right_content, mHomeFragment);
                }else{
                    fragmentTransaction.show(mHomeFragment);
                }

                break;
            case R.id.rl_message_view:
                mHomeImage.setBackgroundResource(R.drawable.comui_tab_home);
                mMessageImage.setBackgroundResource(R.drawable.comui_tab_message_selected);
                mMineImage.setBackgroundResource(R.drawable.comui_tab_home);

                hideFragment(fragmentTransaction, mHomeFragment);

                if (mMessageFragment == null){
                    mMessageFragment = new MessageFragment();
                    fragmentTransaction.add(R.id.right_content, mMessageFragment);
                }else{
                    fragmentTransaction.show(mMessageFragment);
                }

                break;
            case R.id.rl_mine_view:
                mHomeImage.setBackgroundResource(R.drawable.comui_tab_home);
                mMessageImage.setBackgroundResource(R.drawable.comui_tab_message);
                mMineImage.setBackgroundResource(R.drawable.comui_tab_home_selected);

                hideFragment(fragmentTransaction, mHomeFragment);

                if (mMineFragment == null){
                    mMineFragment = new MineFragment();
                    fragmentTransaction.add(R.id.right_content, mMineFragment);
                }else{
                    fragmentTransaction.show(mMineFragment);
                }

                break;
            default:
                break;
        }
        fragmentTransaction.commit();
    }
}
