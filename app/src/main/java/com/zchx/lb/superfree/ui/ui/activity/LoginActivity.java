package com.zchx.lb.superfree.ui.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;

import com.zchx.lb.superfree.R;
import com.zchx.lb.superfree.app.App;
import com.zchx.lb.superfree.app.AppConstants;
import com.zchx.lb.superfree.presenter.LoginPresenter;
import com.zchx.lb.superfree.presenter.impl.LoginPresenterImpl;
import com.zchx.lb.superfree.ui.ui.widget.TopBar;
import com.zchx.lb.superfree.ui.ui.widget.circleImage.RoundImageView;
import com.zchx.lb.superfree.utils.L;
import com.zchx.lb.superfree.utils.T;
import com.zchx.lb.superfree.utils.UiHelper;
import com.zchx.lb.superfree.utils.storage.PreferencesStore;
import com.zchx.lb.superfree.view.LoginView;

import org.sunger.net.utils.FormValidation;
import org.sunger.net.utils.KeyboardUtils;
import org.sunger.net.utils.WidgetUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 登录的界面
 */
public class LoginActivity extends BaseActivity implements LoginView,OnClickListener{

    @Bind(R.id.topBar)
    TopBar topBar;

    @Bind(R.id.et_phone)
    EditText etPhone;
    @Bind(R.id.et_password)
    EditText etPassword;

    @Bind(R.id.tv_click_login)
    RoundImageView tvClickLogin;
    String mobile;
    String password;
    @Bind(R.id.tv_forget_pay_password)
    TextView tvForgetPassword;
    private LoginPresenter loginPresenter;
    //TODO  逻辑判断
    //TODO 1.点击按钮前，先判断输入的手机号和密码是否符合规则
    //TODO  2.保存用户的手机号和密码

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        loginPresenter = new LoginPresenterImpl(this);
        initTopBar();
        initEvent();
    }

    private void initEvent() {

        tvClickLogin.setOnClickListener(this);
        tvForgetPassword.setOnClickListener(this);

    }

    private void initTopBar() {
        topBar.setButtonVisable(0, false);
        topBar.setTitleText("用户登录");
        topBar.setRightButtonBackground();
        topBar.setRightButtonText("注册");
        topBar.setOnTopbarClickListener(new TopBar.topbarClickListener() {
            @Override
            public void leftClick() {
                //TODO 点击取消按钮，直接跳转到项目详情的界面
                startActivity(new Intent(LoginActivity.this, ProjectDetailActivity.class));
            }

            @Override
            public void rightClick() {
                //TODO 跳转到注册界面
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });
    }
    /**
     * 验证信息
     * @param mobile
     * @param password
     * @return
     */
    public boolean valid(String mobile,String password){

        if(!FormValidation.isMobile(mobile)){
            WidgetUtils.requestFocus(etPhone);
            setEditTextError(etPhone, R.string.msg_error_phone);
            return true;
        }
        if(!FormValidation.isPassword(password)){
            WidgetUtils.requestFocus(etPassword);
            setEditTextError(etPassword,R.string.msg_error_password);
            return  true;
        }

        return false;
    }

    private void setEditTextError(EditText editText, int msgId) {
        editText.setFocusable(true);
        editText.setError(getString(msgId));
    }

    @Override
    public void showLoginFail(String errorMsg ) {
        if (errorMsg != null) {
            T.showShortToast(getApplicationContext(),errorMsg);
        }

    }
    @Override
    public void showLoginSuccess() {
        PreferencesStore.getInstance(getApplicationContext()).save(AppConstants.ParamDefaultValue.MOBILE,mobile);
        PreferencesStore.getInstance(getApplicationContext()).save(AppConstants.ParamDefaultValue.PASSWORD,password);
        PreferencesStore.getInstance(getApplicationContext()).save(AppConstants.ParamDefaultValue.ISLOGIN, false);
        //TODO 判断如果用户已经绑卡成功，跳转到个人的界面
        setResult(RESULT_OK);
            finish();
    }


    public void login(){
        //TODO   进行逻辑的判断
        //TODO  登录成功---跳转到账户设置界面
        KeyboardUtils.hide(this);
        mobile = etPhone.getText().toString();
        password = etPassword.getText().toString();
        if (valid(mobile, password)) return;//验证手机号和密码
        //连接请求
        if (mobile != null && password != null) {
            loginPresenter.login(mobile, password);
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_click_login:
                login();
                break;
            case R.id.tv_forget_pay_password:
                startActivity(new Intent(LoginActivity.this, FoundPasswordActivity.class));
                break;
        }

    }


}
