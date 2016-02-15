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

//关于我们的界面
public class AboutUsActivity extends AppCompatActivity {

    @Bind(R.id.topBar)
    TopBar topBar;
    @Bind(R.id.tv_welcome)
    TextView tvWelcome;//点击进入新手引导页

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        ButterKnife.bind(this);
        initTopBar();
        initEvent();
    }

    private void initEvent() {
        tvWelcome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO 点击进入新手引导页
                startActivity(new Intent(AboutUsActivity.this,WelcomeActivity2.class));

            }
        });

    }

    private void initTopBar() {
        topBar.setButtonVisable(1,false);
        topBar.setTitleText("关于我们");
        topBar.setOnTopbarClickListener(new TopBar.topbarClickListener() {
            @Override
            public void leftClick() {
                AboutUsActivity.this.finish();
            }

            @Override
            public void rightClick() {

            }
        });


    }
}
