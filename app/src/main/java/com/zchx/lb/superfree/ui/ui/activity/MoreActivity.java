package com.zchx.lb.superfree.ui.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.zchx.lb.superfree.R;
import com.zchx.lb.superfree.app.AppConstants;
import com.zchx.lb.superfree.event.TestEvent;
import com.zchx.lb.superfree.presenter.LogoutPresenter;
import com.zchx.lb.superfree.presenter.impl.LogoutPresenterImpl;
import com.zchx.lb.superfree.ui.ui.widget.TopBar;
import com.zchx.lb.superfree.ui.ui.widget.circleImage.RoundImageView;
import com.zchx.lb.superfree.utils.L;
import com.zchx.lb.superfree.utils.T;
import com.zchx.lb.superfree.utils.storage.PreferencesStore;
import com.zchx.lb.superfree.view.LogoutView;

import butterknife.Bind;
import butterknife.ButterKnife;
import me.drakeet.materialdialog.MaterialDialog;


public class MoreActivity extends BaseActivity implements LogoutView {

    @Bind(R.id.topBar)
    TopBar topBar;
    @Bind(R.id.tv_online_help)
    TextView tvOnlineHelp;
    @Bind(R.id.tv_about_us)
    TextView tvAboutUs;

//    @Bind(R.id.tv_service_phone)
//    TextView tvServicePhone;
//    @Bind(R.id.tv_feed_callback)
//    TextView tvFeedCallback;

    @Bind(R.id.tv_click_quit)
    RoundImageView quit;
    private LogoutPresenter logoutPresenter;
    String mobile;//传值过来的手机号
    String password;
    String rePersonMobile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more);
        ButterKnife.bind(this);
        mobile= PreferencesStore.getInstance(getApplicationContext()).readString(AppConstants.ParamDefaultValue.MOBILE,"");
        initTopBar();
        initEvent();
    }

    private void initEvent() {
        logoutPresenter=new LogoutPresenterImpl(this);
        quit.setBackgroundResource(R.mipmap.quit);

        //点击退出的按钮
        quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mobile!=null){
                    logoutPresenter.logout(mobile);
                }
            }
        });

        //在线帮助的点击事件
        tvOnlineHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MoreActivity.this,OnLineHelpActivity.class));
            }
        });

        //关于我们的点击事件
        tvAboutUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MoreActivity.this,AboutUsActivity.class));
            }
        });


    }

    private void initTopBar() {
        topBar.setButtonVisable(1,false);
        topBar.setTitleText("更多");
        topBar.setOnTopbarClickListener(new TopBar.topbarClickListener() {
            @Override
            public void leftClick() {
                finish();

            }

            @Override
            public void rightClick() {

            }
        });

    }

    //注销成功
    @Override
    public void LogoutSuccess(String successMsg) {

        //TODO 退出成功之后，需要跳转到主界面
        //TODO  推出之后，保存
        setResult(RESULT_OK);
        finish();


    }

    //注销失败
    @Override
    public void LogoutFail(String logoutErrorMsg) {
        if (logoutErrorMsg != null) {
            T.showShortToast(getApplicationContext(),logoutErrorMsg);

        }
    }

}
