package com.zchx.lb.superfree.ui.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.zchx.lb.superfree.R;
import com.zchx.lb.superfree.app.AppConstants;
import com.zchx.lb.superfree.entity.Integral;
import com.zchx.lb.superfree.presenter.InvestProductPresenter;
import com.zchx.lb.superfree.presenter.impl.InvestProductPresenterImpl;
import com.zchx.lb.superfree.ui.ui.widget.TopBar;
import com.zchx.lb.superfree.ui.ui.widget.autoFitTextView.AutofitTextView;
import com.zchx.lb.superfree.ui.ui.widget.circleImage.RoundImageView;
import com.zchx.lb.superfree.utils.L;
import com.zchx.lb.superfree.utils.T;
import com.zchx.lb.superfree.utils.storage.PreferencesStore;
import com.zchx.lb.superfree.view.InvestProductView;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;
import org.sunger.net.utils.FormValidation;
import org.sunger.net.utils.WidgetUtils;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Request;

/**
 * 这是投资交易的界面
 */
public class InvestActivity extends BaseActivity implements InvestProductView {

    @Bind(R.id.topBar)
    TopBar topBar;


    @Bind(R.id.cb_select)
    CheckBox cbSelect;

    @Bind(R.id.tv_click)
    RoundImageView tvClick;

    @Bind(R.id.tv_num_remaining_money)
    TextView tvNumRemainingMoney;//项目剩余的金额

    @Bind(R.id.tv_num_user_balance)
    TextView tvNumUserBalance;//账户可剩的余额

    @Bind(R.id.tv_recharge)
    TextView tvRecharge;// 去充值按钮

    @Bind(R.id.et_num_investment_amount)
    EditText etNumInvestmentAmount;//输入的投资金额

    @Bind(R.id.tv_num_share)
    TextView tvNumShare;//起投的份额

    @Bind(R.id.et_pay_tv_pay_password)
    EditText etPayTvPayPassword;//支付密码



    @Bind(R.id.tv_num_due_to_net_income)
    TextView tvNumDueToNetIncome;//到期利息

    @Bind(R.id.tv_payment_protocol)
    AutofitTextView tvPaymentProtocol;//移动支付协议

    private String mobile;
    private String productId;//产品的Id
    private String money;//项目剩余的金额
    private String productPrice;//项目起投的份额
    private int productRate;//产品的汇率
    private int productTerm;//产品的期限


    private String userInvestRMB;//用户投资的金额
    private String userPayPassword;
    private String earn;//投资之后的余额
    private InvestProductPresenter investProductPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invest);
        ButterKnife.bind(this);
        investProductPresenter = new InvestProductPresenterImpl(this);
        getData();
        initTopBar();
        initEvent();
    }

    private void getData() {
        //用户注册的手机号
        mobile = PreferencesStore.getInstance(getApplicationContext()).readString(AppConstants.ParamDefaultValue.MOBILE, "");
        productId = getIntent().getStringExtra(AppConstants.ParamDefaultValue.ID);//产品的ID
        productPrice = getIntent().getStringExtra(AppConstants.ParamDefaultValue.GOOD_PRICE);//产品的价格
        productRate = getIntent().getIntExtra(AppConstants.ParamDefaultValue.PRO_RATE, 0);//产品的汇率
        productTerm = getIntent().getIntExtra(AppConstants.ParamDefaultValue.PRO_TERM, 0);//产品的期限
        money = getIntent().getStringExtra(AppConstants.ParamDefaultValue.MONEY);//项目的剩余的金额
        if (productPrice != null) {
            tvNumShare.setText(productPrice);//产品的价格
        }
        if (money != null) {
            tvNumRemainingMoney.setText(money + "元");//项目剩余的金额
        }


    }

    private void initTopBar() {
        //头部左侧按钮的点击事件
        topBar.setButtonVisable(2, false);
        topBar.setTitleText("投资");
        topBar.setOnTopbarClickListener(new TopBar.topbarClickListener() {
            @Override
            public void leftClick() {
                // TODO 点击返回的按钮，跳转到项目详情的按钮
                finish();
            }

            @Override
            public void rightClick() {


            }
        });

    }

    private void initEvent() {

        //获得用户的余额
        if (mobile != null) {
            //返回用户的余额
            if (mobile != null) {
                OkHttpUtils.post()
                        .url(AppConstants.RequestPath.RETURNBALANCE)//用户的余额  mobile替换
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
                                        String availableBalance = object.getString("result_msg");//可用的余额
                                        if (availableBalance != null) {
                                            tvNumUserBalance.setText(availableBalance);
                                        }

                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
            }

        }

        //移动支付协议的点击事件
        tvPaymentProtocol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(InvestActivity.this,PayAgreementActivity.class));
            }
        });


        //去充值的点击事件
        tvRecharge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(InvestActivity.this, RechargeActivity.class));
            }
        });
        //投资金额的输入监听事件
        etNumInvestmentAmount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                //预期收益  *nianhua/360*qixian/100)];
                //平均月息  *nianhua/12/100)];
                //所需要的参数    goods_rate: 8.4,和goods_term: 90,
                //String RMB=tvInputRMB.getText().toString();
                if (!etNumInvestmentAmount.getText().toString().equals("")) {
                    if(Long.parseLong(etNumInvestmentAmount.getText().toString())<= Double.MAX_VALUE) {
                        String text1 = Long.parseLong(etNumInvestmentAmount.getText().toString()) * productRate * productTerm / 36000 + "";
                        tvNumDueToNetIncome.setText(text1);
                    }
                }

            }
        });

        tvClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO 投资成功，弹出一个自定义AlertDialog
                userInvestRMB = etNumInvestmentAmount.getText().toString();
                userPayPassword = etPayTvPayPassword.getText().toString();
                //TODO  进行信息的验证
                if(!TextUtils.isEmpty(userInvestRMB)&&!TextUtils.isEmpty(userPayPassword)){
                    if (valid(userInvestRMB, userPayPassword)) return;
                }

                if (mobile != null && productId != null && userInvestRMB != null && userPayPassword != null) {
                    investProductPresenter.InvestProduct(mobile, productId, userInvestRMB, userPayPassword);
                }

            }
        });

    }

    public boolean valid(String userInvestRMB, String payPassword) {

        if (productPrice != null) {
            if (Integer.parseInt(userInvestRMB) < Integer.parseInt(productPrice)) {
                WidgetUtils.requestFocus(etNumInvestmentAmount);
                setEditTextError(etNumInvestmentAmount, R.string.msg_error_invest_money);
                return true;
            }
        }


        if (!FormValidation.isVerifyCode(payPassword)) {
            WidgetUtils.requestFocus(etPayTvPayPassword);
            setEditTextError(etPayTvPayPassword, R.string.msg_error_pay_password);
            return true;
        }
        return false;
    }

    private void setEditTextError(EditText editText, int msgId) {
        editText.setFocusable(true);
        editText.setError(getString(msgId));
    }


    @Override
    public void showInvestFail(String errorsg) {
        //showMsgInBottom(errorsg);
        if (errorsg != null) {
            T.showShortToast(getApplicationContext(),errorsg);
        }
        L.d("InvestActivity--->error" + errorsg);

    }

    @Override
    public void showInvestSuccess(String successMsg) {

        if (successMsg != null) {
            //earn=successMsg;
            //showMsgInBottom(successMsg);
            if (successMsg != null) {
                T.showShortToast(getApplicationContext(),successMsg);
                startActivity(new Intent(InvestActivity.this, InvestmentRecordActivity.class));
            }

        }

    }


}
