package com.zchx.lb.superfree.ui.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.zchx.lb.superfree.R;
import com.zchx.lb.superfree.app.AppConstants;
import com.zchx.lb.superfree.ui.ui.widget.TopBar;
import com.zchx.lb.superfree.ui.ui.widget.autoFitTextView.AutofitTextView;
import com.zchx.lb.superfree.utils.storage.PreferencesStore;

import butterknife.Bind;
import butterknife.ButterKnife;

//银行卡管理2
public class BankManage2Activity extends AppCompatActivity {

    @Bind(R.id.topBar)
    TopBar topBar;
    @Bind(R.id.tv_bankname)
    AutofitTextView tvBankname;
    @Bind(R.id.tvBankNumber)
    AutofitTextView tvBankNumber;

    private String bankName;
    private String bankNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank_manage2);
        ButterKnife.bind(this);
        bankName= PreferencesStore.getInstance(getApplicationContext()).readString(AppConstants.ParamDefaultValue.BANKNAME,"");
        bankNum=PreferencesStore.getInstance(getApplicationContext()).readString(AppConstants.ParamDefaultValue.CARDNO,"");
        initTopBar();
        initEvent();
    }

    private void initEvent() {
        if (bankName != null) {
            tvBankname.setText(bankName);
        }
        if (bankNum != null) {
            tvBankNumber.setText(bankNum);
        }

    }

    private void initTopBar() {
        topBar.setButtonVisable(0,false);
        topBar.setRightButtonText("取消");
        topBar.setTitleText("银行卡管理");
        topBar.setOnTopbarClickListener(new TopBar.topbarClickListener() {
            @Override
            public void leftClick() {

            }

            @Override
            public void rightClick() {
                //TODO  点击取消跳转到账户设置的界面
               startActivity(new Intent(BankManage2Activity.this,AccountSettingActivity.class));

            }
        });
    }
}
