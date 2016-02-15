package com.zchx.lb.superfree.ui.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.zchx.lb.superfree.R;
import com.zchx.lb.superfree.app.AppConstants;
import com.zchx.lb.superfree.ui.ui.widget.TopBar;
import com.zchx.lb.superfree.utils.storage.PreferencesStore;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * 账户设置的界面
 */
public class AccountSettingActivity extends BaseActivity {


    @Bind(R.id.topBar)
    TopBar topBar;
    @Bind(R.id.tv_manage_bank)
    TextView tvManageBank;//银行卡管理
    @Bind(R.id.tv_user_name)//当绑定好银行卡的时候，显示用户的姓名
    TextView tvUserName;
    @Bind(R.id.tv_login_password)//修改登录密码
    TextView tvLoginPassword;
    @Bind(R.id.tv_pay_password)//修改支付密码
    TextView tvPayPassword;
    //    @Bind(R.id.toggleButton)
    //    ToggleButton toggleButton;
    private String userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_setting);
        ButterKnife.bind(this);
        initTopBar();
        initEvent();
    }
    private void initEvent() {
        //        toggleButton.toggle();
        //        toggleButton.setOnToggleChanged(new ToggleButton.OnToggleChanged() {
        //            @Override
        //            public void onToggle(boolean on) {
        //
        //            }
        //        });
        userName=PreferencesStore.getInstance(getApplicationContext()).readString(AppConstants.ParamDefaultValue.USERNAME,"");
        if (userName != null) {
            tvUserName.setText(userName);
        }


        //修改登录密码的跳转
        tvLoginPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AccountSettingActivity.this,ModifyLoginPasswordActivity.class));
            }
        });
        //修改支付密码的界面
        tvPayPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AccountSettingActivity.this,ManagePayActivity.class));
            }
        });

        //点击银行卡管理的点击事件----》跳转到银行卡管理界面
        tvManageBank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(PreferencesStore.getInstance(getApplicationContext()).readBoolean(AppConstants.ParamDefaultValue.ISBINDSUCCESS,true)){
                    startActivity(new Intent(AccountSettingActivity.this, BankCardManageActivity.class));
                }else{
                    startActivity(new Intent(AccountSettingActivity.this,BankManage2Activity.class));
                }
                AccountSettingActivity.this.finish();


            }
        });
    }




    private void initTopBar() {

        topBar.setButtonVisable(1, false);
        topBar.setTitleText("账户设置");
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
}
