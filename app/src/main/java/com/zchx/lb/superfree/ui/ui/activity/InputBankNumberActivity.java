package com.zchx.lb.superfree.ui.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.zchx.lb.superfree.R;
import com.zchx.lb.superfree.app.AppConstants;
import com.zchx.lb.superfree.presenter.CheckBankPresenter;
import com.zchx.lb.superfree.presenter.impl.CheckBankPresenterImpl;
import com.zchx.lb.superfree.ui.ui.widget.TopBar;
import com.zchx.lb.superfree.ui.ui.widget.autoFitTextView.AutofitTextView;
import com.zchx.lb.superfree.ui.ui.widget.circleImage.RoundImageView;
import com.zchx.lb.superfree.utils.L;
import com.zchx.lb.superfree.utils.T;
import com.zchx.lb.superfree.utils.storage.PreferencesStore;
import com.zchx.lb.superfree.view.CheckBankView;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.sunger.net.utils.FormValidation;
import org.sunger.net.utils.WidgetUtils;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.qqtheme.framework.picker.OptionPicker;
import me.drakeet.materialdialog.MaterialDialog;
import okhttp3.Request;

/**
 * 填写银行卡号的界面
 */
public class InputBankNumberActivity extends BaseActivity implements CheckBankView {

    @Bind(R.id.topBar)
    TopBar topBar;



    @Bind(R.id.et_user_name)
    EditText etUserName;//用户名


    @Bind(R.id.et_ID)
    EditText etID;//身份证号码

    @Bind(R.id.tv_bank)
    TextView tvBank;//点击选择银行的按钮


    @Bind(R.id.tv_select_bank)
    EditText etSelectBank;//显示银行的名称


    @Bind(R.id.et_input_bank_number)
    EditText etInputBankNumber;//输入银行的银行号码


    @Bind(R.id.et_phoneNumber)
    EditText etPhoneNumber;//银行预留的手机号


    @Bind(R.id.tvOK)
    RoundImageView tvOK;
    @Bind(R.id.et_user_pay_password)
    EditText etPayPassword;//支付密码

    private String mobile;//TODO 需要传值过来的手机号
    private String userName;//用户名     绑卡成功之后，把用户名传给账户设置的界面的用户名来展示
    private String userID;//用户身份证号
    private String bankName;//绑定的银行卡
    private String bankNum;//银行号码
    private String bankPhone;//银行预留的手机号
    private String payPassword;//支付密码
    private CheckBankPresenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_bank_number);
        ButterKnife.bind(this);
        //用户注册登录时的手机号码
        mobile= PreferencesStore.getInstance(getApplicationContext()).readString(AppConstants.ParamDefaultValue.MOBILE,"");
        presenter = new CheckBankPresenterImpl(this);
        initTopBar();
        initEvent();
    }

    private void initEvent() {

        tvBank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OptionPicker picker = new OptionPicker(InputBankNumberActivity.this, new String[]{
                        "工商银行", "农业银行", "建设银行", "中国银行", "光大银行", "民生银行",
                        "平安银行", "中信银行", "广发银行", "邮储银行", "兴业银行", "浦发银行", "华夏银行", "交通银行"
                });
                picker.setOffset(3);
                picker.setSelectedIndex(1);
                picker.setTextSize(15);
                picker.setOnOptionPickListener(new OptionPicker.OnOptionPickListener() {
                    @Override
                    public void onOptionPicked(String option) {
                        etSelectBank.setText(option);
                    }
                });
                picker.show();

            }
        });



        tvOK.setBackgroundResource(R.mipmap.btn_ok);
        tvOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO  跳转到账户设置的界面
                userName = etUserName.getText().toString();
                userID = etID.getText().toString();
                bankName = etSelectBank.getText().toString();
                bankNum = etInputBankNumber.getText().toString();
                bankPhone = etPhoneNumber.getText().toString();
                payPassword = etPayPassword.getText().toString();
                //TODO  进行各个字段进行验证的处理
                if(valid(userID,bankNum,bankPhone,payPassword))return;
                if(userName!=null&&userID!=null&&bankName!=null&&bankNum!=null&&bankPhone!=null&&payPassword!=null){
                    presenter.CheckBak(mobile, userName, userID, bankNum, bankPhone, payPassword, bankName);
                }

            }
        });

    }

    private void initTopBar() {

        topBar.setButtonVisable(1, false);
        topBar.setTitleText("填写银行卡号");
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

    public boolean valid(String userIDCard,String bankNum,String bankPhone,String payPassword){

//        //对中文用户名的验证
//        if(!FormValidation.isChinese(userName)){
//            WidgetUtils.requestFocus(etUserName);
//            setEditTextError(etUserName, R.string.msg_error_username);
//            return true;
//        }

        //对身份证号的验证
        if (!FormValidation.personIdValidation(userIDCard)){
            WidgetUtils.requestFocus(etID);
            setEditTextError(etID,R.string.msg_error_userIDCard);
            return  true;
        }

        //对中文银行卡名的验证
//        if(!FormValidation.isChinese(bankName)){
//            WidgetUtils.requestFocus(etSelectBank);
//            setEditTextError(etSelectBank,R.string.msg_error_bankname);
//        }

        //对银行卡号的验证
        if(!FormValidation.isBankCard(bankNum)){
            WidgetUtils.requestFocus(etInputBankNumber);
            setEditTextError(etInputBankNumber,R.string.msg_error_banknum);
        }

        //对银行卡预留手机号的验证
        if(!FormValidation.isMobile(bankPhone)){
            WidgetUtils.requestFocus(etPhoneNumber);
            setEditTextError(etPhoneNumber, R.string.msg_error_phone);
            return true;
        }
        //对6位支付密码的验证
        if (!FormValidation.isVerifyCode(payPassword)){
            WidgetUtils.requestFocus(etPayPassword);
            setEditTextError(etPayPassword,R.string.msg_error_pay_password);
            return  true;
        }

        return false;
    }

    private void setEditTextError(EditText editText, int msgId) {
        editText.setFocusable(true);
        editText.setError(getString(msgId));
    }


    @Override
    public void showCheckBankViewSuccess(String successMsg) {
        T.showShortToast(getApplicationContext(),successMsg);//绑卡成功的吐司显示
        L.d("InputBankNumberActivity--->Success" + successMsg);
        PreferencesStore.getInstance(getApplicationContext()).save(AppConstants.ParamDefaultValue.USERNAME, userName);
        PreferencesStore.getInstance(getApplicationContext()).save(AppConstants.ParamDefaultValue.CARDNO, bankNum);
        PreferencesStore.getInstance(getApplicationContext()).save(AppConstants.ParamDefaultValue.BANKNAME,bankName);
        PreferencesStore.getInstance(getApplicationContext()).save(AppConstants.ParamDefaultValue.ISBINDSUCCESS,false);
        //TODO 绑卡成功，跳转到用户设置的界面
        startActivity(new Intent(InputBankNumberActivity.this, AccountSettingActivity.class));
        this.finish();
    }

    @Override
    public void showCheckBankViewFail(String failMsg) {
        if (failMsg != null) {
            T.showShortToast(getApplicationContext(), failMsg);//绑卡失败的吐司显示
            L.d("InputBankNumberActivity--->fail"+failMsg);
        }


    }



}


