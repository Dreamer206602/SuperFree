package com.zchx.lb.superfree.ui.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;

import com.zchx.lb.superfree.R;
import com.zchx.lb.superfree.app.AppConstants;
import com.zchx.lb.superfree.presenter.RegisterOnePresenter;
import com.zchx.lb.superfree.presenter.impl.RegisterOnePresenterImpl;
import com.zchx.lb.superfree.ui.ui.widget.TopBar;
import com.zchx.lb.superfree.ui.ui.widget.circleImage.RoundImageView;
import com.zchx.lb.superfree.utils.L;
import com.zchx.lb.superfree.view.RegisterOneView;


import org.sunger.net.utils.FormValidation;
import org.sunger.net.utils.WidgetUtils;


import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 注册的界面
 */
public class RegisterActivity extends BaseActivity implements RegisterOneView {

    @Bind(R.id.topBar)
    TopBar topBar;
    @Bind(R.id.tv_click_next)
    RoundImageView tvClickNext;

    @Bind(R.id.tv_recPersonMobile)
    EditText tvRecPersonMobile;
    @Bind(R.id.et_phone)
    EditText etPhone;
    @Bind(R.id.et_password)
    EditText etPassword;
    @Bind(R.id.et_confirm_password)
    EditText etConfirmPassword;

    @Bind(R.id.tv_agree_superFree)
    TextView tvAgreeSuperFree;
    @Bind(R.id.tv_protocol)
    TextView tvProtocol;
    @Bind(R.id.tv_and)
    TextView tvAnd;
    @Bind(R.id.tv_Privacy_Clause)
    TextView tvPrivacyClause;
    String mobile;
    String password;
    String rePassword;
    String recPersonMobile;
    private RegisterOnePresenter registerOnePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        registerOnePresenter=new RegisterOnePresenterImpl(this);
        initTopBar();
        initEvent();
    }
    private void initEvent() {

        tvClickNext.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mobile = etPhone.getText().toString();
                password = etPassword.getText().toString();
                rePassword = etConfirmPassword.getText().toString();
                recPersonMobile = tvRecPersonMobile.getText().toString();
                //TODO  进行信息的验证
                if(valid(mobile,password,rePassword))return;
                if(mobile!=null&&password!=null&&rePassword!=null) {
                    registerOnePresenter.RegisterOne(mobile, password, rePassword, recPersonMobile);
                }



            }
        });
    }

    /**
     * 验证信息
     * @param mobile
     * @param password
     * @param rePassword
     * @param
     *   //String  rePersonMobile
     */
    public boolean valid(String mobile,String password,String rePassword){

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
        if(!FormValidation.isPassword(rePassword)){
            WidgetUtils.requestFocus(etConfirmPassword);
            setEditTextError(etConfirmPassword,R.string.msg_error_password);
            return  true;
        }
//        if(rePersonMobile!=null){
        //            if(!FormValidation.isMobile(rePersonMobile)){
        //                WidgetUtils.requestFocus(tvRecPersonMobile);
        //                setEditTextError(tvRecPersonMobile, R.string.msg_error_phone);
        //                return true;
        //            }
        //        }

        return false;
    }

    private void setEditTextError(EditText editText, int msgId) {
        editText.setFocusable(true);
        editText.setError(getString(msgId));
    }


    private void initTopBar() {
        //隐藏右边的按钮
        topBar.setButtonVisable(1, false);
        topBar.setTitleText("用户注册");

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


    @Override
    public void showRegisterOneSuccess(String successMsg) {
        L.d("RegisterActivity-->one->success"+successMsg);
        Intent intent = new Intent(RegisterActivity.this, VertificationCodeActivity.class);
        intent.putExtra(AppConstants.ParamDefaultValue.MOBILE, mobile);
        intent.putExtra(AppConstants.ParamDefaultValue.PASSWORD, password);
        intent.putExtra(AppConstants.ParamDefaultValue.RECPERSONMOBILE, recPersonMobile);
        startActivity(intent);
        finish();

    }

    @Override
    public void showRegisterOneErrorMsg(String errorMsg) {
        showMsgInBottom(errorMsg);
        L.d("RegisterActivity-->one->success"+errorMsg);
    }
}
