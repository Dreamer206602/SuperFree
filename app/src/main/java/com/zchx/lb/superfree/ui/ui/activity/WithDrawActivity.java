package com.zchx.lb.superfree.ui.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.zchx.lb.superfree.R;
import com.zchx.lb.superfree.app.AppConstants;
import com.zchx.lb.superfree.presenter.WithDrawPresenter;
import com.zchx.lb.superfree.presenter.WithDrawPresenterImpl;
import com.zchx.lb.superfree.ui.ui.widget.TopBar;
import com.zchx.lb.superfree.ui.ui.widget.autoFitTextView.AutofitTextView;
import com.zchx.lb.superfree.ui.ui.widget.circleImage.RoundImageView;
import com.zchx.lb.superfree.utils.L;
import com.zchx.lb.superfree.utils.T;
import com.zchx.lb.superfree.utils.ToolsUtil;
import com.zchx.lb.superfree.utils.storage.PreferencesStore;
import com.zchx.lb.superfree.view.WithDrawView;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.Bind;
import butterknife.ButterKnife;
import me.drakeet.materialdialog.MaterialDialog;
import okhttp3.Request;

/**
 * 这是提现的Activity
 */
public class WithDrawActivity extends BaseActivity implements WithDrawView, TextWatcher {

    @Bind(R.id.topBar)
    TopBar topBar;

    @Bind(R.id.tv_num_bank_card)
    TextView tvNumBankCard;

    @Bind(R.id.tv_bankname)
    TextView tvBankname;//点击选择银行的按钮


    @Bind(R.id.tv_select_bank)
    EditText etSelectBank;//显示输入的银行


    @Bind(R.id.tv_num_can_withdraw_RMB)
    TextView tvNumCanWithdrawRMB;//用户可以体现的金额

    @Bind(R.id.tv_num_withdraw_RMB)
    EditText etNumWithdrawRMB;//用户体现的金额

    @Bind(R.id.tv_num_pay_password)
    EditText etNumPayPassword;//用户的支付密码

    @Bind(R.id.tv_click_ok)
    RoundImageView tvClickOk;

    @Bind(R.id.tv_input_account)
    AutofitTextView tvInputAccount;//到帐时间说明

    @Bind(R.id.tv_forget_pay_password)
    AutofitTextView tvForgetPayPassword;//忘记支付密码

    private String mobile;//需要传值过来的手机号

    private String bindBankNum;//传值过来的银行卡号

    private String canWithDrawMoney;//可以体现的金额,


    private String bankName;//银行名字
    private String withDrawMoney;//提现金额
    private String payPassWord;//支付密码;

    private WithDrawPresenter presenter;
    private String availableBalance;//可用的余额


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_withdrw);
        ButterKnife.bind(this);
        mobile= PreferencesStore.getInstance(getApplicationContext()).readString(AppConstants.ParamDefaultValue.MOBILE,"");
        bindBankNum=PreferencesStore.getInstance(getApplicationContext()).readString(AppConstants.ParamDefaultValue.CARDNO,"");//绑定的银行卡号
        presenter=new WithDrawPresenterImpl(this);
        initTopBar();
        initEvent();


    }

    /**
     * 初始化控件
     */
    private void initEvent() {
        if (bindBankNum != null) {
            tvNumBankCard.setText(bindBankNum);
        }else{
            tvNumBankCard.setText("");
        }
        //返回用户的余额
        if (mobile != null) {
            OkHttpUtils.post()
                    .url(AppConstants.RequestPath.RETURNBALANCE)
                    .addParams(AppConstants.ParamDefaultValue.MOBILE, mobile)
                    .build()
                    .execute(new StringCallback() {
                        @Override
                        public void onError(Request request, Exception e) {
                        }

                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject object = new JSONObject(response);
                                if (object.getString("result").equals("1")) {
                                    availableBalance = object.getString("result_msg");
                                    tvNumCanWithdrawRMB.setText(availableBalance+"元");
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    });
        }

        //到帐时间说明---》界面
        tvInputAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    startActivity(new Intent(WithDrawActivity.this,AccountTimeActivity.class));

            }
        });

        //忘记支付密码的页面跳转
        tvForgetPayPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO 跳转到找回支付密码
                startActivity(new Intent(WithDrawActivity.this, FoundPayPasswordActivity.class));
            }
        });

        //点击确定时触发的事件
        //TODO 进行逻辑的判断-----》当输入框进行改变 的时候，确定按钮的背景进行改变
        etSelectBank.addTextChangedListener(this);
        etNumWithdrawRMB.addTextChangedListener(this);
        etNumPayPassword.addTextChangedListener(this);
        tvClickOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //canWithDrawMoney=tvNumCanWithdrawRMB.getText().toString();
                bankName = etSelectBank.getText().toString();
                withDrawMoney = etNumWithdrawRMB.getText().toString();
                payPassWord = etNumPayPassword.getText().toString();
                //TODO 进行验证----》
//                if(mobile!=null&&bindBankNum!=null&&bankName!=null&&canWithDrawMoney!=null&&withDrawMoney!=null&&payPassWord!=null) {
//                    presenter.WithDrawRMB(mobile, bindBankNum, bankName, canWithDrawMoney, withDrawMoney, payPassWord);
//                }
                if(mobile!=null){
                    presenter.WithDrawRMB(mobile, bindBankNum, bankName, availableBalance, withDrawMoney, payPassWord);
                }

            }
        });


    }


    /**
     * 初始化TopBar
     */
    private void initTopBar() {

        topBar.setTitleText("提现");
        topBar.setOnTopbarClickListener(new TopBar.topbarClickListener() {
            @Override
            public void leftClick() {
                finish();
            }

            @Override
            public void rightClick() {
                startActivity(new Intent(WithDrawActivity.this, WithDrawRecordActivity.class));

            }
        });

    }

    @Override
    public void showWithDrawSuccess(String successMsg) {
        //TODO  提现成功之后，跳转到提现记录的界面
        T.showShortToast(WithDrawActivity.this,successMsg);
        //startActivity(new Intent(WithDrawActivity.this, WithDrawRecordActivity.class));
        L.d("WithDrawActivity--->success"+successMsg);

    }

    @Override
    public void showWithDrawFail(String failMsg) {
        T.showShortToast(WithDrawActivity.this,failMsg);
        L.d("WithDrawActivity--->fail"+failMsg);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {

        bankName=etSelectBank.getText().toString();
        withDrawMoney=etNumWithdrawRMB.getText().toString();
        payPassWord=etNumPayPassword.getText().toString();
        if(!TextUtils.isEmpty(bankName)&&!TextUtils.isEmpty(withDrawMoney)&&!TextUtils.isEmpty(payPassWord)){
            tvClickOk.setBackgroundResource(R.mipmap.btn_ok);
        }else{
            tvClickOk.setBackgroundResource(R.mipmap.btn_ok_gray);
        }



    }
}
