package com.zchx.lb.superfree.ui.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.zchx.lb.superfree.R;
import com.zchx.lb.superfree.ui.ui.widget.TopBar;

import butterknife.Bind;
import butterknife.ButterKnife;

//移动支付协议
public class PayAgreementActivity extends AppCompatActivity {

    @Bind(R.id.topBar)
    TopBar topBar;
    @Bind(R.id.web_payAgreement)
    WebView webPayAgreement;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_agreement);
        ButterKnife.bind(this);
        initEvent();
        initTopBar();
    }

    private void initEvent() {
        WebSettings settings = webPayAgreement.getSettings();
        settings.setTextSize(WebSettings.TextSize.LARGER);
        settings.setLoadWithOverviewMode(true);
        settings.setUseWideViewPort(true);
        webPayAgreement.loadUrl("file:///android_asset/service.html");

    }

    private void initTopBar() {

        topBar.setButtonVisable(1, false);
        topBar.setTitleText("移动支付协议");
        topBar.setOnTopbarClickListener(new TopBar.topbarClickListener() {
            @Override
            public void leftClick() {
                PayAgreementActivity.this.finish();
            }

            @Override
            public void rightClick() {

            }
        });


    }
}
