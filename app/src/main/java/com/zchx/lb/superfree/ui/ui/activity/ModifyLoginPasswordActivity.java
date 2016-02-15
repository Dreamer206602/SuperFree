package com.zchx.lb.superfree.ui.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.zchx.lb.superfree.R;
import com.zchx.lb.superfree.app.AppConstants;
import com.zchx.lb.superfree.presenter.ModifyLoginPasswordPresenter;
import com.zchx.lb.superfree.presenter.impl.ModifyLoginPasswordPresenterImpl;
import com.zchx.lb.superfree.ui.ui.widget.TopBar;
import com.zchx.lb.superfree.ui.ui.widget.circleImage.RoundImageView;
import com.zchx.lb.superfree.utils.L;
import com.zchx.lb.superfree.utils.T;
import com.zchx.lb.superfree.utils.storage.PreferencesStore;
import com.zchx.lb.superfree.view.ModifyLoginPasswordView;

import org.sunger.net.utils.FormValidation;
import org.sunger.net.utils.WidgetUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 修改登录密码的界面
 * TODO  修改登录成功之后，跳转到哪里?
 */
public class ModifyLoginPasswordActivity extends BaseActivity implements ModifyLoginPasswordView {

    @Bind(R.id.topBar)
    TopBar topBar;
    @Bind(R.id.et_old_password)
    EditText etOldLoginPassword;
    @Bind(R.id.et_new_login_password)
    EditText etNewLoginPassword;
    @Bind(R.id.et_confirm_new_login_password)
    EditText etConfirmNewLoginPassword;
    @Bind(R.id.tv_click_modify_login_password)
    RoundImageView tvClickModifyLoginPassword;

    private ModifyLoginPasswordPresenter presenter;

    private String mobile;// TODO 需要接收传值过来的手机号
    private String oldPassword;//原始密码
    private String newPassword;//新密码
    private String confirmNewPassword;//确认新密码

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_login_password);
        ButterKnife.bind(this);
        mobile= PreferencesStore.getInstance(getApplicationContext()).readString(AppConstants.ParamDefaultValue.MOBILE,"");//传值过来的手机号
        presenter=new ModifyLoginPasswordPresenterImpl(this);
        initTopBar();
        initEvent();
    }

    private void initEvent() {

        tvClickModifyLoginPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                oldPassword=etOldLoginPassword.getText().toString();
                newPassword=etNewLoginPassword.getText().toString();
                confirmNewPassword=etConfirmNewLoginPassword.getText().toString();
                //TODO 进行验证
                if(valid(oldPassword,newPassword,confirmNewPassword))return;
                if(mobile!=null&&oldPassword!=null&&newPassword!=null&&confirmNewPassword!=null){
                    presenter.modifyLoginPassword(mobile,oldPassword,newPassword,confirmNewPassword);
                }

            }
        });


    }

    /**
     * 验证信息
     * @param oldPassword
     * @param newPassword
     * @param confirmNewPassword
     * @return
     */
    public boolean valid(String oldPassword,String newPassword,String confirmNewPassword){

        if(!FormValidation.isPassword(oldPassword)){
            WidgetUtils.requestFocus(etOldLoginPassword);
            setEditTextError(etOldLoginPassword, R.string.msg_error_password);
            return true;
        }
        if(!FormValidation.isPassword(newPassword)){
            WidgetUtils.requestFocus(etNewLoginPassword);
            setEditTextError(etNewLoginPassword,R.string.msg_error_password);
            return  true;
        }
        if(!FormValidation.isPassword(confirmNewPassword)){
            WidgetUtils.requestFocus(etConfirmNewLoginPassword);
            setEditTextError(etConfirmNewLoginPassword,R.string.msg_error_password);
            return  true;
        }


        return false;
    }

    private void setEditTextError(EditText editText, int msgId) {
        editText.setFocusable(true);
        editText.setError(getString(msgId));
    }

    private void initTopBar() {
        topBar.setButtonVisable(1,false);
        topBar.setTitleText("修改登录密码");
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
    public void showModifyLoginPasswordSuccess(String successMsg) {
        L.d("ModifyLoginPasswordActivity", successMsg);
        startActivity(new Intent(ModifyLoginPasswordActivity.this,LoginActivity.class));//跳转到登陆界面
    }

    @Override
    public void showModifyLoginPasswordFail(String errorMsg) {
        L.d("ModifyLoginPasswordActivity",errorMsg);
        showMsgInBottom(errorMsg);

    }
}
