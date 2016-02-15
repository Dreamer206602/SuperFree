package com.zchx.lb.superfree.ui.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.zchx.lb.superfree.R;
import com.zchx.lb.superfree.ui.ui.widget.TopBar;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 银行卡管理的界面
 */
public class BankCardManageActivity extends AppCompatActivity {

    @Bind(R.id.topBar)
    TopBar topBar;
    @Bind(R.id.tv_add_bank)
    TextView tvAddBank;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank_card_manage);
        ButterKnife.bind(this);


        initTopBar();


        tvAddBank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO  点击事件
                startActivity(new Intent(BankCardManageActivity.this,InputBankNumberActivity.class));


            }
        });



    }

    private void initTopBar() {

        topBar.setButtonVisable(0,false);
        topBar.setTitleText("银行卡管理");
        topBar.setRightButtonText("取消");
        topBar.setOnTopbarClickListener(new TopBar.topbarClickListener() {
            @Override
            public void leftClick() {


            }

            @Override
            public void rightClick() {

                finish();
            }
        });

    }
}
