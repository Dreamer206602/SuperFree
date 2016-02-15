package com.zchx.lb.superfree.ui.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.zchx.lb.superfree.R;
import com.zchx.lb.superfree.ui.ui.widget.TopBar;

import butterknife.Bind;
import butterknife.ButterKnife;

//支持的银行卡列表的界面
public class ListBanksActivity extends AppCompatActivity {

    @Bind(R.id.topBar)
    TopBar topBar;
    @Bind(R.id.img_listBanks)
    TextView imgListBanks;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_banks);
        ButterKnife.bind(this);
        initTopBar();
    }

    private void initTopBar() {
        topBar.setButtonVisable(1, false);
        topBar.setTitleText("支持的银行卡的列表");
        topBar.setOnTopbarClickListener(new TopBar.topbarClickListener() {
            @Override
            public void leftClick() {
                ListBanksActivity.this.finish();
            }

            @Override
            public void rightClick() {

            }
        });

    }
}
