package com.zchx.lb.superfree.ui.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.zchx.lb.superfree.R;
import com.zchx.lb.superfree.app.AppConstants;
import com.zchx.lb.superfree.presenter.RegisterOkPresenter;
import com.zchx.lb.superfree.presenter.impl.RegisterOkPresenterImpl;
import com.zchx.lb.superfree.ui.ui.widget.TimeButton;
import com.zchx.lb.superfree.ui.ui.widget.TopBar;
import com.zchx.lb.superfree.ui.ui.widget.circleImage.RoundImageView;
import com.zchx.lb.superfree.utils.T;
import com.zchx.lb.superfree.utils.storage.PreferencesStore;
import com.zchx.lb.superfree.view.RegisterOkView;

import butterknife.Bind;
import butterknife.ButterKnife;
import me.drakeet.materialdialog.MaterialDialog;

//找回支付密码的界面
public class FoundPayPasswordActivity extends AppCompatActivity implements RegisterOkView {

    @Bind(R.id.topBar)
    TopBar topBar;

    @Bind(R.id.tv_phoneNumber)
    TextView tvPhoneNumber;
    @Bind(R.id.tv_num_vertification_code)
    EditText tvNumVertificationCode;

    @Bind(R.id.tv_reset_get_verify_code)
    TimeButton tvResetGetVerifyCode;//获得验证码的按钮

    @Bind(R.id.tv_click_registerOk)
    RoundImageView tvClickOk;

    private String mobile;//用户注册的手机号
    private RegisterOkPresenter presenter;
    private String valiCode;//验证码



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_found_pay_password);
        ButterKnife.bind(this);
        tvResetGetVerifyCode.onCreate(savedInstanceState);
        tvResetGetVerifyCode.setTextAfter("s后重新获取").setTextBefore("点击获取验证码").setLenght(60 * 1000);
        initTopBar();
        initTask();
    }

    private void initTask() {
        mobile= PreferencesStore.getInstance(getApplicationContext()).readString(AppConstants.ParamDefaultValue.MOBILE,"");
        if (mobile != null) {
            String replace=mobile.replace(mobile.substring(3, 8), "*****");
            tvPhoneNumber.setText(replace);
        }
        presenter=new RegisterOkPresenterImpl(this);

        //点击获得验证码的操作
        tvResetGetVerifyCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mobile != null) {
                    presenter.getVerifyCode(mobile);
                }

            }
        });

        //验证验证码的操作
        tvClickOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                valiCode=tvNumVertificationCode.getText().toString();
                if (valiCode != null) {
                    presenter.confirmVerify(mobile,valiCode);
                }
            }
        });


    }

    private void initTopBar() {
        topBar.setButtonVisable(1,false);
        topBar.setTitleText("找回支付密码");
        topBar.setOnTopbarClickListener(new TopBar.topbarClickListener() {
            @Override
            public void leftClick() {
                FoundPayPasswordActivity.this.finish();
            }

            @Override
            public void rightClick() {

            }
        });


    }

    @Override
    public void showVerifyError(String getVerifyErrorMsg) {


    }

    @Override
    public void showVerifySuccess(String getVerifySuccessMsg) {


    }

    //验证验证码成功
    @Override
    public void confirmVerifySuccess(String confirmVerifySuccessMsg) {
        if (confirmVerifySuccessMsg != null) {
            startActivity(new Intent(FoundPayPasswordActivity.this, ForgetPayPasswordActivity.class));
        }
        }

    //验证验证码，失败
    @Override
    public void confirmVerifyError(String confirmVerifyErrorMsg) {
        if (confirmVerifyErrorMsg != null) {
            T.showShortToast(getApplicationContext(),confirmVerifyErrorMsg);

        }




    }

    @Override
    public void showRegisterError(String RegisterErrorMsg) {

    }

    @Override
    public void showRegisterSuccess(String RegisterSuccessMsg) {

    }

    @Override
    protected void onDestroy() {
        tvResetGetVerifyCode.onDestroy();
        super.onDestroy();
    }
}
