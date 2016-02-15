package com.zchx.lb.superfree.ui.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.zchx.lb.superfree.R;
import com.zchx.lb.superfree.app.AppConstants;
import com.zchx.lb.superfree.event.TestEvent;
import com.zchx.lb.superfree.presenter.RegisterOkPresenter;
import com.zchx.lb.superfree.presenter.impl.RegisterOkPresenterImpl;
import com.zchx.lb.superfree.ui.ui.widget.TimeButton;
import com.zchx.lb.superfree.ui.ui.widget.TopBar;
import com.zchx.lb.superfree.ui.ui.widget.circleImage.RoundImageView;
import com.zchx.lb.superfree.utils.L;
import com.zchx.lb.superfree.utils.T;
import com.zchx.lb.superfree.view.RegisterOkView;


import org.sunger.net.utils.FormValidation;
import org.sunger.net.utils.WidgetUtils;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * 输入验证码的界面
 */
public class VertificationCodeActivity extends BaseActivity implements  RegisterOkView {

    @Bind(R.id.topBar)
    TopBar topBar;
    @Bind(R.id.tv_phoneNumber)
    TextView tvPhoneNumber;


    @Bind(R.id.tv_num_vertification_code)
    EditText tvNumVertificationCode;//输入验证码的输入框



    @Bind(R.id.tv_reset_get_verify_code)
    TimeButton tvResetGetVerifyCode;//获得验证码的按钮


    @Bind(R.id.tv_click_registerOk)
    RoundImageView tvClickRegisterOk;//注册完成的点击事件

    String mobile;//注册的手机号
    String password;//密码
    String rePersonMobile;//邀请人的手机号
    String verifyCode;//验证码


    private RegisterOkPresenter registerOkPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vertification_code);
        ButterKnife.bind(this);
        tvResetGetVerifyCode.onCreate(savedInstanceState);
        tvResetGetVerifyCode.setTextAfter("s后重新获取").setTextBefore("点击获取验证码").setLenght(60 * 1000);
        initTopBar();
        initTask();
    }

    private void initTask() {
        registerOkPresenter=new RegisterOkPresenterImpl(this);

        mobile = getIntent().getStringExtra(AppConstants.ParamDefaultValue.MOBILE);
        password = getIntent().getStringExtra(AppConstants.ParamDefaultValue.PASSWORD);
        rePersonMobile=getIntent().getStringExtra(AppConstants.ParamDefaultValue.RECPERSONMOBILE);
        String replace = mobile.replace(mobile.substring(3, 8), "*****");
        if (replace != null) {
            tvPhoneNumber.setText(replace);
        }
        tvNumVertificationCode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                verifyCode = tvNumVertificationCode.getText().toString();
                if (verifyCode != null) {
                    registerOkPresenter.confirmVerify(mobile, verifyCode);
                }

            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

    }
    //点击获取验证码的事件
    public void GetVerifyCode(View v) {
        if(mobile!=null){
            registerOkPresenter.getVerifyCode(mobile);
        }

    }

    //点击注册完成的事件
    public void RegisterOK(View v) {

        if(valid(verifyCode))return;//验证码不正确
        //TODO 如何对邀请人的手机号进行验证
        if(mobile!=null&&password!=null){
            registerOkPresenter.registerSuccess(mobile, password, rePersonMobile);
        }


    }

    private void initTopBar() {
        topBar.setButtonVisable(1, false);
        topBar.setTitleText("填写验证码");
        topBar.setOnTopbarClickListener(new TopBar.topbarClickListener() {
            @Override
            public void leftClick() {
                //TODO 跳转到注册界面
                startActivity(new Intent(VertificationCodeActivity.this, RegisterActivity.class));
            }

            @Override
            public void rightClick() {

                //TODO 不进行处理

            }
        });

    }


    public boolean valid(String verifyCode) {

        if (!FormValidation.isVerifyCode(verifyCode)) {
            WidgetUtils.requestFocus(tvNumVertificationCode);
            setEditTextError(tvNumVertificationCode, R.string.msg_error_verifyCode);
            return true;
        }
        return false;
    }

    private void setEditTextError(EditText editText, int msgId) {
        editText.setFocusable(true);
        editText.setError(getString(msgId));
    }

    //获得验证码失败
    @Override
    public void showVerifyError(String getVerifyError) {
        showMsgInBottom(getVerifyError);
        L.d("getVerifyError------>"+getVerifyError);


    }

    //获得验证码成功
    @Override
    public void showVerifySuccess(String getVerifySuccessMsg) {

    }

    //验证验证码成功
    @Override
    public void confirmVerifySuccess(String confirmVerifySuccessMsg) {
        L.d("confirmVerifySuccess--->"+confirmVerifySuccessMsg);
    }

    //验证验证码失败
    @Override
    public void confirmVerifyError(String confirmVerifyError) {
            showMsgInBottom(confirmVerifyError);
            L.d("confirmVerifyError--->"+confirmVerifyError);
    }

    //注册失败
    @Override
    public void showRegisterError(String RegisterErrorMsg) {
        if (RegisterErrorMsg != null) {
            T.showShortToast(getApplicationContext(), RegisterErrorMsg);
        }

    }

    //注册成功--->向外部发送消息
    @Override
    public void showRegisterSuccess(String RegisterSuccessMsg) {
        L.d("RegisterSuccessMsg----Success->"+RegisterSuccessMsg);
        //验证成功，直接跳转到登录界面
        startActivity(new Intent(VertificationCodeActivity.this, LoginActivity.class));
        finish();
    }

    @Override
    protected void onDestroy() {
        tvResetGetVerifyCode.onDestroy();
        super.onDestroy();
    }
}
