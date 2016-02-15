package com.zchx.lb.superfree.ui.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.zchx.lb.superfree.R;
import com.zchx.lb.superfree.ui.ui.widget.TopBar;

import butterknife.Bind;
import butterknife.ButterKnife;

//到帐时间说明的界面
public class AccountTimeActivity extends AppCompatActivity {

    @Bind(R.id.topBar)
    TopBar topBar;
    @Bind(R.id.tv_account_time)
    TextView tvAccountTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_time);
        ButterKnife.bind(this);
        initTopBar();
    }

    private void initTopBar() {
        topBar.setTitleText("到帐时间说明");
        topBar.setButtonVisable(1, false);
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
}
