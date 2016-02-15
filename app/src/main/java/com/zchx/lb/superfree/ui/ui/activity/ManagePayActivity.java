package com.zchx.lb.superfree.ui.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.zchx.lb.superfree.R;
import com.zchx.lb.superfree.app.AppConstants;
import com.zchx.lb.superfree.presenter.ManagePayPasswordPresenter;
import com.zchx.lb.superfree.presenter.impl.ManagePayPasswordImpl;
import com.zchx.lb.superfree.ui.ui.widget.TopBar;
import com.zchx.lb.superfree.ui.ui.widget.circleImage.RoundImageView;
import com.zchx.lb.superfree.utils.L;
import com.zchx.lb.superfree.utils.T;
import com.zchx.lb.superfree.utils.storage.PreferencesStore;
import com.zchx.lb.superfree.view.ManagePayView;

import org.sunger.net.utils.FormValidation;
import org.sunger.net.utils.WidgetUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 设置支付密码的界面
 */
public class ManagePayActivity extends BaseActivity implements ManagePayView {

    @Bind(R.id.topBar)
    TopBar topBar;
    @Bind(R.id.et_old_pay_password)
    EditText etOldPayPassword;
    @Bind(R.id.et_new_pay_password)
    EditText etNewPayPassword;
    @Bind(R.id.et_confirm_new_pay_password)
    EditText etConfirmNewPayPassword;
    @Bind(R.id.tv_click_modify_pay_password)
    RoundImageView tvClickModifyPayPassword;

    private String mobile;//TODO  记得是传值过来的手机号
    private String oldPayPassword;//旧的支付密码
    private String newPayPassword;//新的支付密码
    private String confirmNewPayPassword;//确认新的支付密码
    private ManagePayPasswordPresenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_pay);
        ButterKnife.bind(this);
        mobile= PreferencesStore.getInstance(getApplicationContext()).readString(AppConstants.ParamDefaultValue.MOBILE,"");
        presenter=new ManagePayPasswordImpl(this);
        initTopBar();
        initEvent();
    }

    private void initEvent() {
        tvClickModifyPayPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                oldPayPassword=etOldPayPassword.getText().toString();
                newPayPassword=etNewPayPassword.getText().toString();
                confirmNewPayPassword=etConfirmNewPayPassword.getText().toString();
                //TODO 进行验证信息
                if(valid(oldPayPassword,newPayPassword,confirmNewPayPassword))return;
                if(mobile!=null&&oldPayPassword!=null&&newPayPassword!=null&&confirmNewPayPassword!=null){
                    presenter.managePayPassword(mobile,oldPayPassword,newPayPassword,confirmNewPayPassword);
                }

            }
        });



    }
    public boolean valid(String oldPayPassword,String newPayPassword,String confirmNewPayPassword){

        if(!FormValidation.isPassword(oldPayPassword)){
            WidgetUtils.requestFocus(etOldPayPassword);
            setEditTextError(etOldPayPassword, R.string.msg_error_password);
            return true;
        }
        if(!FormValidation.isPassword(newPayPassword)){
            WidgetUtils.requestFocus(etNewPayPassword);
            setEditTextError(etNewPayPassword,R.string.msg_error_password);
            return  true;
        }
        if(!FormValidation.isPassword(confirmNewPayPassword)){
            WidgetUtils.requestFocus(etConfirmNewPayPassword);
            setEditTextError(etConfirmNewPayPassword,R.string.msg_error_password);
            return  true;
        }


        return false;
    }

    private void setEditTextError(EditText editText, int msgId) {
        editText.setFocusable(true);
        editText.setError(getString(msgId));
    }



    private void initTopBar() {
        topBar.setButtonVisable(1, false);
        topBar.setTitleText("设置支付密码");
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
    public void showManagePaySuccess(String successMsg) {
        startActivity(new Intent(ManagePayActivity.this,AccountSettingActivity.class));//修改成功跳转到账户设置的界面
        L.d("ManagePayActivity-->success",successMsg);

    }

    @Override
    public void showManagePayFail(String failMsg) {
        showMsgInBottom(failMsg);
        L.d("ManagePayActivity-->fail",failMsg);
    }
}
