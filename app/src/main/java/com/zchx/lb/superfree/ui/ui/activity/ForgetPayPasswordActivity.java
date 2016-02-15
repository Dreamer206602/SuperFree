package com.zchx.lb.superfree.ui.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.zchx.lb.superfree.R;
import com.zchx.lb.superfree.app.AppConstants;
import com.zchx.lb.superfree.presenter.ForgetPayPassWordPresenter;
import com.zchx.lb.superfree.presenter.impl.ForgetPayPassWordPresenterImpl;
import com.zchx.lb.superfree.ui.ui.widget.TopBar;
import com.zchx.lb.superfree.ui.ui.widget.circleImage.RoundImageView;
import com.zchx.lb.superfree.utils.L;
import com.zchx.lb.superfree.utils.T;
import com.zchx.lb.superfree.utils.storage.PreferencesStore;
import com.zchx.lb.superfree.view.ForgetPayPasswordView;

import org.sunger.net.utils.FormValidation;
import org.sunger.net.utils.WidgetUtils;

import butterknife.Bind;
import butterknife.ButterKnife;
import me.drakeet.materialdialog.MaterialDialog;

/**
 * 忘记支付密码的界面
 */
public class ForgetPayPasswordActivity extends AppCompatActivity implements ForgetPayPasswordView {

    @Bind(R.id.topBar)
    TopBar topBar;
    @Bind(R.id.et_new_pay_password)
    EditText etNewPayPassword;
    @Bind(R.id.et_confirm_new_pay_password)
    EditText etConfirmNewPayPassword;
    @Bind(R.id.tv_click_ok)
    RoundImageView tvClickOk;

    private String mobile;//TODO 记得是传值过来的手机号
    private String newPayPassword;
    private String confirmNewPayPassword;
    private ForgetPayPassWordPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_pay_password);
        ButterKnife.bind(this);
        mobile= PreferencesStore.getInstance(getApplicationContext()).readString(AppConstants.ParamDefaultValue.MOBILE,"");
        presenter=new ForgetPayPassWordPresenterImpl(this);
        initTopBar();
        initEvent();
    }

    private void initEvent() {

        tvClickOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newPayPassword=etNewPayPassword.getText().toString();
                confirmNewPayPassword=etConfirmNewPayPassword.getText().toString();
                //TODO  进行判断  是否符合六位的支付密码
                if(valid(newPayPassword,confirmNewPayPassword))return;
                if(mobile!=null&&newPayPassword!=null&&confirmNewPayPassword!=null){
                    presenter.ForgetPayPassword(mobile,newPayPassword,confirmNewPayPassword);
                }


            }
        });

    }

    private void initTopBar() {
        topBar.setButtonVisable(1,false);
        topBar.setTitleText("忘记支付密码");
        topBar.setOnTopbarClickListener(new TopBar.topbarClickListener() {
            @Override
            public void leftClick() {
                ForgetPayPasswordActivity.this.finish();
            }

            @Override
            public void rightClick() {

            }
        });


    }

    @Override
    public void showForgetPayPasswordSuccess(String successMsg) {
        L.d("ForgetPayPasswordActivity-->success", successMsg);
        if (successMsg != null) {
            T.showShortToast(getApplicationContext(),successMsg);
            startActivity(new Intent(ForgetPayPasswordActivity.this, WithDrawActivity.class));
        }

    }

    @Override
    public void showForgetPayPasswordFail(String errorMsg) {
        if (errorMsg != null) {
            T.showShortToast(getApplicationContext(),errorMsg);
            L.d("ForgetPayPasswordActivity-->fail", errorMsg);
        }

    }
    public boolean valid(String newPayPassword,String confirmNewPayPassword){

        if(!FormValidation.isVerifyCode(newPayPassword)){
            WidgetUtils.requestFocus(etNewPayPassword);
            setEditTextError(etNewPayPassword, R.string.msg_error_pay_password);
            return true;
        }
        if(!FormValidation.isVerifyCode(confirmNewPayPassword)){
            WidgetUtils.requestFocus(etConfirmNewPayPassword);
            setEditTextError(etConfirmNewPayPassword,R.string.msg_error_pay_password);
            return  true;
        }

        return false;
    }

    private void setEditTextError(EditText editText, int msgId) {
        editText.setFocusable(true);
        editText.setError(getString(msgId));
    }

}
