package com.zchx.lb.superfree.ui.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.zchx.lb.superfree.R;
import com.zchx.lb.superfree.app.AppConstants;
import com.zchx.lb.superfree.entity.Recharge;
import com.zchx.lb.superfree.presenter.RechargePresenter;
import com.zchx.lb.superfree.presenter.impl.RechargePresenterImpl;
import com.zchx.lb.superfree.ui.ui.widget.TimeButton;
import com.zchx.lb.superfree.ui.ui.widget.TopBar;
import com.zchx.lb.superfree.ui.ui.widget.circleImage.RoundImageView;
import com.zchx.lb.superfree.utils.L;
import com.zchx.lb.superfree.utils.T;
import com.zchx.lb.superfree.utils.storage.PreferencesStore;
import com.zchx.lb.superfree.view.RechargeView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 这是充值的Activity
 */
public class RechargeActivity extends BaseActivity implements RechargeView, TextWatcher {


    @Bind(R.id.topBar)
    TopBar topBar;


    @Bind(R.id.tv_click)
    RoundImageView tvClick;


    @Bind(R.id.tv_num_bank_card)
    TextView tvNumBankCard;//这是用户绑定成功之后返回的银行卡号，需要传值


    @Bind(R.id.tv_Quota_description)
    TextView tvQuotaDescription;//限额说明的按钮


    @Bind(R.id.tv_num_recharge_RMB)
    EditText etNumRechargeRMB;//输入的充值金额，进行判断，不能大于50000元


    @Bind(R.id.et_identifying_code)
    EditText etIdentifyingCode;//输入的验证码


    @Bind(R.id.tv_get_identify_code)
    TimeButton tvGetIdentifyCode;//点击获得验证码

    @Bind(R.id.checkBox)
    CheckBox checkBox;

    @Bind(R.id.tv_Payment_Agreement)
    TextView tvPaymentAgreement;//TODO 移动支付协议

    private AlertDialog mAlertDialog;

    private String mobile;//用户注册的手机号

    private String bindBankNum;//用户绑定的银行卡号

    private String rechargeRMB;//充值的金额
    private String IdentifyCode;//用户的验证码
    private String orderId;//点击验证码成功时返回的值
    private RechargePresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recharge);
        ButterKnife.bind(this);
        tvGetIdentifyCode.onCreate(savedInstanceState);
        tvGetIdentifyCode.setTextAfter("s后重新获取").setTextBefore("点击获取验证码").setLenght(60 * 1000);
        mobile = PreferencesStore.getInstance(getApplicationContext()).readString(AppConstants.ParamDefaultValue.MOBILE, "");//用户注册的手机号
        bindBankNum = PreferencesStore.getInstance(getApplicationContext()).readString(AppConstants.ParamDefaultValue.CARDNO, "");//用户绑定信息的银行卡号
        presenter = new RechargePresenterImpl(this);
        initTopBar();
        initEvent();
    }

    private void initEvent() {
        if (bindBankNum != null) {
            tvNumBankCard.setText(bindBankNum);
        } else {
            tvNumBankCard.setText("");
        }
        etNumRechargeRMB.addTextChangedListener(this);
        etIdentifyingCode.addTextChangedListener(this);
        //TODO 点击获得验证码的事件 需要动态显示时间
        tvGetIdentifyCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO 对充值的金额和获得的手机号进行验证
                if (rechargeRMB != null) {
                    presenter.GetVerifyCode(mobile, rechargeRMB);
                }

            }
        });

        //TODO 移动支付协议的点击事件
        tvPaymentAgreement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RechargeActivity.this,PayAgreementActivity.class));

            }
        });

        // TODO 限额说明的点击事件
        tvQuotaDescription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    startActivity(new Intent(RechargeActivity.this,ListBanksActivity.class));
            }
        });


        tvClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO 进行验证
                //                if(mobile!=null&&orderId!=null&&IdentifyCode!=null&&rechargeRMB!=null){
                rechargeRMB = etNumRechargeRMB.getText().toString();
                IdentifyCode = etIdentifyingCode.getText().toString();
                if (checkBox.isChecked()) {
                    presenter.ConfirmPay(mobile, orderId, IdentifyCode, rechargeRMB);
                }


            }
        });

    }


    private void initTopBar() {
        topBar.setTitleText("充值");
        //隐藏右边的按钮
        topBar.setButtonVisable(1, false);
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
    public void showGetIdentifyCodeSuccess(String successMsg, String successOrderID) {
        //TODO 点击获得验证码成功时返回的订单号
        if (successMsg != null) {
            orderId = successOrderID;
        }
        L.d("RechargeActivity-->showGetIdentifyCodeSuccess" + successMsg);
    }

    @Override
    public void showGetIdentifyCodeFail(String failMsg) {
        if (failMsg != null) {
            T.showShortToast(getApplicationContext(),failMsg);
        }
        L.d("RechargeActivity-->showGetIdentifyCodeFail" + failMsg);
    }

    @Override
    public void showRechargeSuccess(String successMsg) {
        //充值成功
        if (successMsg != null) {
            T.showShortToast(RechargeActivity.this,successMsg);
        }
    }

    @Override
    public void showRechargeFail(String failMsg) {
        showMsgInBottom(failMsg);

    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        rechargeRMB = etNumRechargeRMB.getText().toString();
        IdentifyCode = etIdentifyingCode.getText().toString();
        if (!TextUtils.isEmpty(rechargeRMB) && !TextUtils.isEmpty(IdentifyCode)) {
            tvClick.setBackgroundResource(R.mipmap.btn_ok);
        } else {
            tvClick.setBackgroundResource(R.mipmap.btn_ok_gray);
        }

    }

    @Override
    protected void onDestroy() {
        tvGetIdentifyCode.onDestroy();
        super.onDestroy();
    }
}
