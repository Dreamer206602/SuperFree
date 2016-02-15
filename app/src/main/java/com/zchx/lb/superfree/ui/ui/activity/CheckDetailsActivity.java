package com.zchx.lb.superfree.ui.ui.activity;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.zchx.lb.superfree.R;
import com.zchx.lb.superfree.app.AppConstants;
import com.zchx.lb.superfree.ui.ui.widget.TopBar;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 检查项目详情的界面
 */
public class CheckDetailsActivity extends BaseActivity {

    @Bind(R.id.topBar)
    TopBar topBar;
    @Bind(R.id.webView)
    WebView mWebView;
    private String enterprise_info;
    private String goods_describe;
    private String product_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_details);
        ButterKnife.bind(this);
        initTopBar();
        initWebView();
    }

    private void initTopBar() {
        product_title=getIntent().getStringExtra(AppConstants.ParamDefaultValue.GOODS_TITLE);
        //对头部的TopBar左侧按钮的操作
        topBar.setButtonVisable(0, true);
        topBar.setButtonVisable(1, false);
        if (product_title != null) {
            topBar.setTitleText(product_title);
        }
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

    private void initWebView() {
        //设置载入页面自适应手机屏幕，居中显示
        //setUseWideViewPort是设置webview推荐使用的窗口，设置为true。
        //setLoadWithOverviewMode是设置webview加载的页面的模式，也设置为true。
        WebSettings mWebSettings = mWebView.getSettings();
        mWebSettings.setTextSize(WebSettings.TextSize.LARGEST);
        mWebSettings.setUseWideViewPort(true);
        mWebSettings.setLoadWithOverviewMode(true);


        enterprise_info = getIntent().getStringExtra(AppConstants.ParamDefaultValue.ENTERPRISE_INFO);
        goods_describe = getIntent().getStringExtra(AppConstants.ParamDefaultValue.GOODS_DESCRIBE);
        if (enterprise_info != null || goods_describe != null) {
            mWebView.loadDataWithBaseURL(null, enterprise_info + goods_describe, "text/html", "utf-8", null);
        }


    }
}
