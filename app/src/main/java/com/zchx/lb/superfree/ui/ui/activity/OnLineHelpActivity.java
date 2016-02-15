package com.zchx.lb.superfree.ui.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.zchx.lb.superfree.R;
import com.zchx.lb.superfree.ui.ui.widget.TopBar;

import butterknife.Bind;
import butterknife.ButterKnife;

//在线帮助的界面
public class OnLineHelpActivity extends AppCompatActivity {

    @Bind(R.id.topBar)
    TopBar topBar;
    //注册与登录
    @Bind(R.id.tv_register_login)
    TextView tvRegisterLogin;
    @Bind(R.id.img_register_login)
    TextView imgRegisterLogin;
    //认证与安全
    @Bind(R.id.tv_certification_safety)
    TextView tvCertificationSafety;
    @Bind(R.id.img_certification_safety)
    TextView imgCertificationSafety;
    //充值与提现
    @Bind(R.id.tv_recharge_withdraw)
    TextView tvRechargeWithdraw;
    @Bind(R.id.img_recharge_withdraw)
    TextView imgRechargeWithdraw;
    //投资与转让
    @Bind(R.id.tv_investment_transfer)
    TextView tvInvestmentTransfer;
    @Bind(R.id.img_investment_transfer)
    TextView imgInvestmentTransfer;
    //积分规则
    @Bind(R.id.tv_integralRules)
    TextView tvIntegralRules;
    @Bind(R.id.img_integralRules)
    TextView imgIntegralRules;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_line_help);
        ButterKnife.bind(this);
        initTopBar();
        initEvent();
    }

    private void initEvent() {

        //注册与登录
        tvRegisterLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO
                if(imgRegisterLogin.getVisibility()==View.GONE){
                    imgRegisterLogin.setVisibility(View.VISIBLE);
                }else{
                    imgRegisterLogin.setVisibility(View.GONE);
                }

            }
        });

        //认证与安全
        tvCertificationSafety.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(imgCertificationSafety.getVisibility()==View.GONE){
                    imgCertificationSafety.setVisibility(View.VISIBLE);
                }else{
                    imgCertificationSafety.setVisibility(View.GONE);
                }
            }
        });


        //充值与体现
        tvRechargeWithdraw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(imgRechargeWithdraw.getVisibility()==View.GONE){
                    imgRechargeWithdraw.setVisibility(View.VISIBLE);
                }else{
                    imgRechargeWithdraw.setVisibility(View.GONE);
                }
            }
        });

        //投资与转让
        tvInvestmentTransfer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(imgInvestmentTransfer.getVisibility()==View.GONE){
                    imgInvestmentTransfer.setVisibility(View.VISIBLE);
                }else{
                    imgInvestmentTransfer.setVisibility(View.GONE);
                }
            }
        });

        //积分规则
        tvIntegralRules.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(imgIntegralRules.getVisibility()==View.GONE){
                    imgIntegralRules.setVisibility(View.VISIBLE);
                }else{
                    imgIntegralRules.setVisibility(View.GONE);
                }
            }
        });





    }

    private void initTopBar() {
        topBar.setButtonVisable(1,false);
        topBar.setTitleText("在线帮助");
        topBar.setOnTopbarClickListener(new TopBar.topbarClickListener() {
            @Override
            public void leftClick() {
                OnLineHelpActivity.this.finish();
            }

            @Override
            public void rightClick() {

            }
        });


    }
}
