package com.zchx.lb.superfree.ui.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.zchx.lb.superfree.R;
import com.zchx.lb.superfree.app.AppConstants;
import com.zchx.lb.superfree.presenter.ForgetPasswordPresenter;
import com.zchx.lb.superfree.presenter.impl.ForgetPasswordPresenterImpl;
import com.zchx.lb.superfree.ui.ui.widget.TopBar;
import com.zchx.lb.superfree.ui.ui.widget.circleImage.RoundImageView;
import com.zchx.lb.superfree.utils.L;
import com.zchx.lb.superfree.utils.T;
import com.zchx.lb.superfree.utils.storage.PreferencesStore;
import com.zchx.lb.superfree.view.ForgetPasswordView;

import org.sunger.net.utils.FormValidation;
import org.sunger.net.utils.WidgetUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 忘记密码的界面
 */
public class ForgetPasswordActivity extends BaseActivity implements ForgetPasswordView {

    @Bind(R.id.topBar)
    TopBar topBar;
    @Bind(R.id.et_new_password)
    EditText etNewPassword;
    @Bind(R.id.et_confirm_new_password)
    EditText etConfirmNewPassword;
    @Bind(R.id.tv_click_ok)
    RoundImageView tvClickOk;
    private ForgetPasswordPresenter presenter;

    private String mobile;//  TODO 传值过来的手机号
    private String newPassword;
    private String confirmNewPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        ButterKnife.bind(this);
        mobile= PreferencesStore.getInstance(getApplicationContext()).readString(AppConstants.ParamDefaultValue.MOBILE,"");
        presenter=new ForgetPasswordPresenterImpl(this);

        initTopBar();
        initEvent();
    }

    private void initEvent() {
        tvClickOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newPassword=etNewPassword.getText().toString();
                confirmNewPassword=etConfirmNewPassword.getText().toString();
                //TODO 进行进行验证
                if(valid(newPassword,confirmNewPassword))return;
                if(newPassword!=null&&confirmNewPassword!=null) {
                    presenter.ForgetPassword(mobile, newPassword, confirmNewPassword);
                }
            }
        });
    }

    private void initTopBar() {
        topBar.setButtonVisable(1,false);
        topBar.setTitleText("忘记登录密码");
        topBar.setOnTopbarClickListener(new TopBar.topbarClickListener() {
            @Override
            public void leftClick() {
                ForgetPasswordActivity.this.finish();
            }

            @Override
            public void rightClick() {

            }
        });

    }

    @Override
    public void showForgerPasswordSuccess(String successMsg) {
        //修改密码成功之后跳转到登录界面
        if (successMsg != null) {
            startActivity(new Intent(ForgetPasswordActivity.this,LoginActivity.class));
        }

    }

    @Override
    public void showForgetPasswordFail(String errorMsg) {
        if (errorMsg != null) {
            T.showShortToast(getApplicationContext(),errorMsg);
        }

    }
    public boolean valid(String newPassword,String confirmNewPassword){

        if(!FormValidation.isPassword(newPassword)){
            WidgetUtils.requestFocus(etNewPassword);
            setEditTextError(etNewPassword, R.string.msg_error_password);
            return true;
        }
        if(!FormValidation.isPassword(confirmNewPassword)){
            WidgetUtils.requestFocus(etConfirmNewPassword);
            setEditTextError(etConfirmNewPassword,R.string.msg_not_same_password);
            return  true;
        }
        return false;
    }

    private void setEditTextError(EditText editText, int msgId) {
        editText.setFocusable(true);
        editText.setError(getString(msgId));
    }

}
