package com.zchx.lb.superfree.ui.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zchx.lb.superfree.R;
import com.zchx.lb.superfree.app.AppConstants;
import com.zchx.lb.superfree.presenter.IntegralDetailPresenter;
import com.zchx.lb.superfree.presenter.impl.IntegralDetailPresenterImpl;
import com.zchx.lb.superfree.ui.ui.widget.TopBar;
import com.zchx.lb.superfree.ui.ui.widget.circleImage.RoundImageView;
import com.zchx.lb.superfree.utils.T;
import com.zchx.lb.superfree.utils.storage.PreferencesStore;
import com.zchx.lb.superfree.view.IntegralDetailView;

import butterknife.Bind;
import butterknife.ButterKnife;
import me.drakeet.materialdialog.MaterialDialog;

/**
 * 这是积分详情的Activity
 */
public class IntegralDetailsActivity extends BaseActivity implements IntegralDetailView {


    @Bind(R.id.topBar)
    TopBar topBar;
    @Bind(R.id.tv_click_exchange)
    RoundImageView tvClickExchange;
    @Bind(R.id.webView)
    WebView mWebView;

    @Bind(R.id.id_image)
    ImageView mImage;


    @Bind(R.id.tv_num_product_integral)
    TextView tvNumIntegral;//积分


    @Bind(R.id.tv_num_product_price)
    TextView tvRMB;//所需要的人民币

    @Bind(R.id.tv_inventory)
    TextView tvInventory;//库存

    private String pro_id;//产品的ID
    private String proIntegral;//产品的积分
    private String proPrice;//产品的价格
    private String proName;//产品的名字
    private String mobile;//需要用户注册的手机号
    private IntegralDetailPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_integral_details);
        ButterKnife.bind(this);
        initGetData();
        initTopBar();
        initEvent();
    }

    //获得所需要的参数
    private void initGetData() {
        mobile = PreferencesStore.getInstance(getApplicationContext()).readString(AppConstants.ParamDefaultValue.MOBILE, "");
        //获得产品的ID
        pro_id = getIntent().getStringExtra(AppConstants.ParamDefaultValue.PRO_ID);
        presenter = new IntegralDetailPresenterImpl(this);
        if (pro_id != null) {
            presenter.IntegralDetail(pro_id);
        }

    }

    private void initEvent() {
        tvClickExchange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //TODO 判断如果用户没有绑卡弹出一个弹出框，否则进行验证
                if (PreferencesStore.getInstance(getApplicationContext()).readBoolean(AppConstants.ParamDefaultValue.ISBINDSUCCESS, true)) {
                    //请先绑定银行卡
                    //TODO 如果用户是未绑卡的状态，跳转弹出一个对话框
                    T.showShortToast(getApplicationContext(),"请您先绑定银行卡");
                } else {
                    if (mobile != null && proName != null && proIntegral != null && proPrice != null && pro_id != null) {
                        presenter.exChange(mobile, proName, proIntegral, proPrice, pro_id);
                    }
                }


            }
        });


    }


    //初始化TopBar
    private void initTopBar() {
        topBar.setButtonVisable(2, false);
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

    //获得积分成功
    @Override
    public void showIntegralDetailsSuccess(String pro_name, String pro_price, String imgUrl, String pro_store, String pro_integral, String pro_content) {
        if (pro_name != null) {
            proName = pro_name;
            topBar.setTitleText(pro_name);

        }
        if (imgUrl != null) {
            Glide.with(IntegralDetailsActivity.this)
                    .load(imgUrl)
                    .placeholder(R.mipmap.ic_default_adimage)
                    .crossFade()
                    .into(mImage);
        }

        if (pro_content != null) {
            WebSettings mWebSettings = mWebView.getSettings();
            mWebSettings.setUseWideViewPort(true);
            mWebSettings.setLoadWithOverviewMode(true);
            //WebView加载数据
            mWebView.loadDataWithBaseURL(null, pro_content, "text/html", "utf-8", null);

        }

        if (pro_integral != null) {
            proIntegral = pro_integral;
            tvNumIntegral.setText(pro_integral);//积分
        }
        if (pro_price != null) {
            proPrice = pro_price;
            tvRMB.setText(pro_price + "元");//所需的人民币
        }

        if (pro_store != null) {
            tvInventory.setText("(库存: " + pro_store + ")");//库存
        }


    }

    //获得积分失败
    @Override
    public void showIntegralDetailsFail(String errorMsg) {

    }

    //立即兑换成功
    @Override
    public void showExchangeSuccess(String successMsg) {
        if (successMsg != null) {
            Intent intent = new Intent(IntegralDetailsActivity.this, ConfirmExchangeInfoActivity.class);
            if (pro_id != null && proName != null && proIntegral != null && proPrice != null) {
                intent.putExtra(AppConstants.ParamDefaultValue.PRO_ID, pro_id);//产品的ID
                intent.putExtra(AppConstants.ParamDefaultValue.PRONAME, proName);//产品的名字
                intent.putExtra(AppConstants.ParamDefaultValue.INTERGRATION, proIntegral);//产品积分
                intent.putExtra(AppConstants.ParamDefaultValue.PRICE, proPrice);//产品价格
                startActivity(intent);
            }
        }
    }

    //立即兑换失败
    @Override
    public void showExchangeFail(String errorMsg) {
        if (errorMsg != null) {
            T.showShortToast(getApplicationContext(),errorMsg);
        }
    }
}
