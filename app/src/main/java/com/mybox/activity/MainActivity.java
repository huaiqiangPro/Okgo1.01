package com.mybox.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mybox.okgo.R;
import com.mybox.ui.fragment.NewRecommendFragment;
import com.mybox.ui.fragment.Rec2Fragment;
import com.mybox.ui.fragment.Rec1Fragment;
import com.mybox.ui.fragment.Rec3Fragment;
import com.mybox.ui.fragment.RecyclerFragment;


public class MainActivity extends AppCompatActivity implements OnClickListener {

    private FragmentManager fm;
    private Fragment mHomeFragment,mPondFragment,mMessageFragment,mMineFragment;
    private Fragment mCurrent;

    private RelativeLayout mHomeLayout;
    private RelativeLayout mPondLayout;
    private RelativeLayout mMessageLayout;
    private RelativeLayout mMineLayout;
    private TextView mHomeView;
    private TextView mPondView;
    private TextView mMessageView;
    private TextView mMineView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        mHomeFragment = new NewRecommendFragment();
        fm = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.content_layout, mHomeFragment);
        fragmentTransaction.commit();
    }



    private void initView() {
        mHomeLayout = (RelativeLayout) findViewById(R.id.home_layout_view);
        mHomeLayout.setOnClickListener(this);
        mPondLayout = (RelativeLayout) findViewById(R.id.pond_layout_view);
        mPondLayout.setOnClickListener(this);
        mMessageLayout = (RelativeLayout) findViewById(R.id.message_layout_view);
        mMessageLayout.setOnClickListener(this);
        mMineLayout = (RelativeLayout) findViewById(R.id.mine_layout_view);
        mMineLayout.setOnClickListener(this);

        mHomeView = (TextView) findViewById(R.id.home_image_view);
        mPondView = (TextView) findViewById(R.id.fish_image_view);
        mMessageView = (TextView) findViewById(R.id.message_image_view);
        mMineView = (TextView) findViewById(R.id.mine_image_view);
        mHomeView.setBackgroundResource(R.drawable.comui_tab_home_selected);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void hideFragment(Fragment fragment, FragmentTransaction ft) {
        if (fragment != null) {
            ft.hide(fragment);
        }
    }

    @Override
    public void onClick(View v) {
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        switch (v.getId()) {
            case R.id.home_layout_view:
                mHomeView.setBackgroundResource(R.drawable.comui_tab_home_selected);
                mPondView.setBackgroundResource(R.drawable.comui_tab_pond);
                mMessageView.setBackgroundResource(R.drawable.comui_tab_message);
                mMineView.setBackgroundResource(R.drawable.comui_tab_person);

                hideFragment(mMineFragment, fragmentTransaction);
                hideFragment(mPondFragment, fragmentTransaction);
                hideFragment(mMessageFragment, fragmentTransaction);
                if (mHomeFragment == null) {
                    mHomeFragment = new NewRecommendFragment();
                    fragmentTransaction.add(R.id.content_layout, mHomeFragment);
                } else {
                    mCurrent = mHomeFragment;
                    fragmentTransaction.show(mHomeFragment);
                }
                break;
            case R.id.pond_layout_view:
                mPondView.setBackgroundResource(R.drawable.comui_tab_pond_selected);
                mMessageView.setBackgroundResource(R.drawable.comui_tab_message);
                mHomeView.setBackgroundResource(R.drawable.comui_tab_home);
                mMineView.setBackgroundResource(R.drawable.comui_tab_person);

                hideFragment(mMineFragment, fragmentTransaction);
                hideFragment(mHomeFragment, fragmentTransaction);
                hideFragment(mMessageFragment, fragmentTransaction);
                if (mPondFragment == null) {
                    mPondFragment = new Rec1Fragment();
                    fragmentTransaction.add(R.id.content_layout, mPondFragment);
                } else {
                    mCurrent = mPondFragment;
                    fragmentTransaction.show(mPondFragment);
                }
                break;
            case R.id.message_layout_view:
                mMessageView.setBackgroundResource(R.drawable.comui_tab_message_selected);
                mHomeView.setBackgroundResource(R.drawable.comui_tab_home);
                mPondView.setBackgroundResource(R.drawable.comui_tab_pond);
                mMineView.setBackgroundResource(R.drawable.comui_tab_person);

                hideFragment(mMineFragment, fragmentTransaction);
                hideFragment(mPondFragment, fragmentTransaction);
                hideFragment(mHomeFragment, fragmentTransaction);
                if (mMessageFragment == null) {
                    mMessageFragment = new Rec2Fragment();
                    fragmentTransaction.add(R.id.content_layout, mMessageFragment);
                } else {
                    mCurrent = mMessageFragment;
                    fragmentTransaction.show(mMessageFragment);
                }

                break;
            case R.id.mine_layout_view:
                mMineView.setBackgroundResource(R.drawable.comui_tab_person_selected);
                mHomeView.setBackgroundResource(R.drawable.comui_tab_home);
                mPondView.setBackgroundResource(R.drawable.comui_tab_pond);
                mMessageView.setBackgroundResource(R.drawable.comui_tab_message);

                hideFragment(mPondFragment, fragmentTransaction);
                hideFragment(mHomeFragment, fragmentTransaction);
                hideFragment(mMessageFragment, fragmentTransaction);
                if (mMineFragment == null) {
                    mMineFragment = new RecyclerFragment();
                    fragmentTransaction.add(R.id.content_layout, mMineFragment);
                } else {
                    mCurrent = mPondFragment;
                    fragmentTransaction.show(mMineFragment);
                }


                break;
        }

        fragmentTransaction.commit();
    }
}
