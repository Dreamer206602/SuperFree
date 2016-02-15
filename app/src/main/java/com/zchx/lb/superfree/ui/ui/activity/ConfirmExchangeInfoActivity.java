package com.zchx.lb.superfree.ui.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.zchx.lb.superfree.R;
import com.zchx.lb.superfree.app.AppConstants;
import com.zchx.lb.superfree.presenter.BalanceAndAssetsPresenter;
import com.zchx.lb.superfree.presenter.ConfirmPayPresenter;
import com.zchx.lb.superfree.presenter.MyPointPresenter;
import com.zchx.lb.superfree.presenter.impl.BalanceAndAssetsPresenterImpl;
import com.zchx.lb.superfree.presenter.impl.ConfirmPayPresenterImpl;
import com.zchx.lb.superfree.presenter.impl.MyPointPresenterImpl;
import com.zchx.lb.superfree.ui.ui.widget.TopBar;
import com.zchx.lb.superfree.ui.ui.widget.circleImage.RoundImageView;
import com.zchx.lb.superfree.utils.L;
import com.zchx.lb.superfree.utils.T;
import com.zchx.lb.superfree.utils.storage.PreferencesStore;
import com.zchx.lb.superfree.view.BalanceAndAssetsView;
import com.zchx.lb.superfree.view.ConfirmPayView;
import com.zchx.lb.superfree.view.MyPointView;

import org.sunger.net.utils.FormValidation;
import org.sunger.net.utils.WidgetUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 确认兑换信息的Activity
 */
public class ConfirmExchangeInfoActivity extends BaseActivity implements ConfirmPayView, MyPointView, BalanceAndAssetsView {

    @Bind(R.id.topBar)
    TopBar topBar;
    @Bind(R.id.tv_goods_title)
    TextView tvProductName;//产品的名字

    @Bind(R.id.tv_num_product_integral)
    TextView tvNumProductIntegral;//产品所需积分


    @Bind(R.id.tv_num_product_price)
    TextView tvNumProductPrice;//兑换产品的价格


    @Bind(R.id.tv_num_user_balance)
    TextView tvNumUserBalance;//账户余额


    @Bind(R.id.tv_balance_low)
    TextView tvBalanceLow;//余额不足的提醒

    @Bind(R.id.tv_click_recharge)
    TextView tvClickRecharge;//立即充值的按钮


    @Bind(R.id.tv_num_user_integral)
    TextView tvNumUserIntegral;//用户的可用积分


    @Bind(R.id.tv_integral_low)
    TextView tvIntegralLow;//积分不足的提醒


    @Bind(R.id.tv_click_get_integral)
    TextView tvClickGetIntegral;//如何获得积分的按钮


    @Bind(R.id.et_user_name)
    EditText etPersonName;//用户的姓名
    @Bind(R.id.et_user_phone)
    EditText etPersonPhone;//用户的手机号

    @Bind(R.id.et_province_city_area)
    EditText etProvinceCityArea;//用户的地址1
    @Bind(R.id.et_user_detail_address)
    EditText etPersonAddress;//用户的地址2

    @Bind(R.id.et_user_pay_password)
    EditText etPayPassword;//支付密码

    @Bind(R.id.tv_click_pay)
    RoundImageView tvClickPay;

    private String productName;//产品的名字,需要传值过来
    private String productNumIntegral;//产品的积分，需要传值过来的数据
    private String productPrice;//产品价格
    private String productId;//产品所需要的ID号
    private String mobile;//

    private String userIntegral;//用户的积分
    private String userRMB;//用户的金额
    private String userName;
    private String userPhone;
    private String userAddress;
    private String userPayPassword;
    private ConfirmPayPresenter confirmPayPresenter;
    private MyPointPresenter myPointPresenter;
    private BalanceAndAssetsPresenter balanceAndAssetsPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_exchange_info);
        ButterKnife.bind(this);
        getData();
        initTopBar();
        initEvent();
    }

    //获得数据
    private void getData() {
        mobile = PreferencesStore.getInstance(getApplicationContext()).readString(AppConstants.ParamDefaultValue.MOBILE, "");
        confirmPayPresenter = new ConfirmPayPresenterImpl(this);
        myPointPresenter = new MyPointPresenterImpl(this);
        balanceAndAssetsPresenter = new BalanceAndAssetsPresenterImpl(this);
        if (mobile != null) {
            myPointPresenter.myPoint(mobile);
        }
        if (mobile != null) {
            balanceAndAssetsPresenter.returnBalance(mobile);
        }
        productName = getIntent().getStringExtra(AppConstants.ParamDefaultValue.PRONAME);
        productNumIntegral = getIntent().getStringExtra(AppConstants.ParamDefaultValue.INTERGRATION);
        productPrice = getIntent().getStringExtra(AppConstants.ParamDefaultValue.PRICE);
        productId = getIntent().getStringExtra(AppConstants.ParamDefaultValue.PRO_ID);

    }

    private void initEvent() {
        if (productName != null) {
            tvProductName.setText(productName);
        }
        if (productNumIntegral != null) {
            tvNumProductIntegral.setText(productNumIntegral);
        }
        if (productPrice != null) {
            tvNumProductPrice.setText(productPrice);
        }


        //TODO   进行逻辑判断
        //点击充值的按钮
        tvClickRecharge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(ConfirmExchangeInfoActivity.this, RechargeActivity.class));
            }
        });
        //确认支付的按钮的点击事件
        tvClickPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //userIntegral=tvNumUserIntegral.getText().toString();
                //userRMB=tvNumUserBalance.getText().toString();
                userName = etPersonName.getText().toString();
                userPhone = etPersonPhone.getText().toString();
                userAddress = etProvinceCityArea.getText().toString() + etPersonAddress.getText().toString();
                userPayPassword = etPayPassword.getText().toString();
                //TODO 进行信息的验证
                if(valid(mobile,userPayPassword))return;
                confirmPayPresenter.ConfirmPay(mobile, productName, productNumIntegral, productPrice, userRMB, userIntegral,
                        userAddress, userName, userPhone, userPayPassword, productId);
//                if (mobile != null && productName != null && productNumIntegral != null && productPrice != null
//                        && userRMB != null && userIntegral != null && userAddress != null && userName != null &&
//                        userPhone != null && userPayPassword != null && productId != null) {
//                    confirmPayPresenter.ConfirmPay(mobile, productName, productNumIntegral, productPrice, userRMB, userIntegral,
//                            userAddress, userName, userPhone, userPayPassword, productId);
//                }


            }
        });

    }

    public boolean valid(String mobile,String userPayPassword){

//        if(!FormValidation.isChinese(userName)){
//            WidgetUtils.requestFocus(etPersonName);
//            setEditTextError(etPersonName, R.string.msg_error_username);
//            return true;
//        }
        if(!FormValidation.isMobile(mobile)){
            WidgetUtils.requestFocus(etPersonPhone);
            setEditTextError(etPersonPhone,R.string.msg_error_phone);
            return  true;
        }
        if(!FormValidation.isVerifyCode(userPayPassword)){
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


    private void initTopBar() {

        topBar.setButtonVisable(1, false);
        topBar.setTitleText("确认兑换消息");
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

    //TODO 支付成功
    @Override
    public void showConfirmPaySuccess(String successMsg) {
                showMsgInBottom(successMsg);
        //支付成功 TODO 跳转到那个界面
        T.showShortToast(getApplicationContext(),successMsg);
        L.d("ConfirmExchangeInfoActivity----->success", successMsg);

    }

    @Override
    public void showConfirmPayFail(String errorMsg) {
        //showMsgInBottom(errorMsg);
        T.showShortToast(getApplicationContext(),errorMsg);
        L.d("ConfirmExchangeInfoActivity----->error", errorMsg);
    }


    //TODO 获得积分成功
    @Override
    public void showMyPointSuccess(String successMsg) {
        if (successMsg != null) {
            userIntegral = successMsg;//用户的积分
            tvNumUserIntegral.setText(successMsg);
        }
    }

    //获得积分失败
    @Override
    public void showMyPointFail(String errorMsg) {
        if (errorMsg != null) {
            showMsgInBottom(errorMsg);
        }
    }

    //TODO 获得余额成功
    @Override
    public void showBalanceSuccess(String successBalance) {
        if (successBalance != null) {
            userRMB = successBalance;
            tvNumUserBalance.setText(successBalance);
        }

    }

    //获得余额失败
    @Override
    public void showBalanceFail(String failBalance) {
        if (failBalance != null) {

        }
    }

    //获得资产成功
    @Override
    public void showAssetsSuccess(String receivedInterest, String waitingInterest, String allAssets) {

    }

    //获得资产失败
    @Override
    public void showAssetsFail(String failAssetsMsg) {

    }
}
