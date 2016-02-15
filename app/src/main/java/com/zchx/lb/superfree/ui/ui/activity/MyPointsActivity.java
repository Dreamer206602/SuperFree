package com.zchx.lb.superfree.ui.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.zchx.lb.superfree.R;
import com.zchx.lb.superfree.app.AppConstants;
import com.zchx.lb.superfree.presenter.MyPointPresenter;
import com.zchx.lb.superfree.presenter.impl.MyPointPresenterImpl;
import com.zchx.lb.superfree.ui.ui.fragment.AccessRecordFragment;
import com.zchx.lb.superfree.ui.ui.fragment.UseRecordFragment;
import com.zchx.lb.superfree.ui.ui.widget.TopBar;
import com.zchx.lb.superfree.ui.ui.widget.autoFitTextView.AutofitTextView;
import com.zchx.lb.superfree.ui.ui.widget.circleImage.RoundImageView;
import com.zchx.lb.superfree.ui.ui.widget.riseNumberTextView.RiseNumberTextView;
import com.zchx.lb.superfree.utils.L;
import com.zchx.lb.superfree.utils.storage.PreferencesStore;
import com.zchx.lb.superfree.view.MyPointView;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Request;

/**
 * 我的积分界面
 */
public class MyPointsActivity extends BaseActivity implements MyPointView {

    @Bind(R.id.topBar)
    TopBar topBar;

    @Bind(R.id.img_user)
    RoundImageView imgUser;

    @Bind(R.id.tv_my_point)
    RiseNumberTextView myPoint;

    @Bind(R.id.tv_Integral_rules)
    AutofitTextView tvIntegralRules;
    @Bind(R.id.id_tablayout)
    TabLayout mTabLayout;
    @Bind(R.id.id_viewpager)
    ViewPager mViewpager;

    @Bind(R.id.tv_access_record)
    AutofitTextView tvAccessRecord;//获取积分

    @Bind(R.id.tv_use_record)
    AutofitTextView tvUseRecord;//使用积分

    private String[] mTitles = new String[]{"获取记录", "使用记录"};
    private String mobile;
    private MyPointPresenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_points);
        ButterKnife.bind(this);
        initTopBar();
        initData();
        initEvent();
    }

    //得到传过来的数据
    private void initData() {
        //得到传值过来的手机号
        mobile= PreferencesStore.getInstance(getApplicationContext()).readString(AppConstants.ParamDefaultValue.MOBILE,"");
        presenter=new MyPointPresenterImpl(this);
        if (mobile != null) {
            presenter.myPoint(mobile);
        }


    }

    private void initEvent() {
        mViewpager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {

                switch (position) {
                    case 1:
                        return new UseRecordFragment();
                }
                return new AccessRecordFragment();
            }


            @Override
            public CharSequence getPageTitle(int position) {
                return mTitles[position];
            }

            @Override
            public int getCount() {
                return mTitles.length;
            }
        });
        mTabLayout.setupWithViewPager(mViewpager);


        //积分规则的点击事件
               tvIntegralRules.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MyPointsActivity.this, IntegralRuleActivity.class));
            }
        });


    }

    private void initTopBar() {

        topBar.setButtonVisable(1, false);
        topBar.setTitleText("我的积分");

        topBar.setOnTopbarClickListener(new TopBar.topbarClickListener() {
            @Override
            public void leftClick() {
                MyPointsActivity.this.finish();
            }

            @Override
            public void rightClick() {

            }
        });


    }

    @Override
    public void showMyPointSuccess(String successMsg) {
        if (successMsg != null) {
            myPoint.withNumber(Integer.parseInt(successMsg)).start();
        }

    }

    @Override
    public void showMyPointFail(String errorMsg) {
        if (errorMsg != null) {
            showMsgInBottom(errorMsg);
        }
    }
}
