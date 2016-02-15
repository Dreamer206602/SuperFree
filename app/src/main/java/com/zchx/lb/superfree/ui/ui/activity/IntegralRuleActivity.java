package com.zchx.lb.superfree.ui.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.zchx.lb.superfree.R;
import com.zchx.lb.superfree.ui.ui.widget.TopBar;
import com.zchx.lb.superfree.ui.ui.widget.autoFitTextView.AutofitTextView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 积分规则的界面
 */
public class IntegralRuleActivity extends AppCompatActivity {

    @Bind(R.id.topBar)
    TopBar topBar;
    @Bind(R.id.tv_common_problem)
    AutofitTextView tvCommonProblem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_integral_rule);
        ButterKnife.bind(this);


        initTopBar();
        initEvent();

    }

    private void initEvent() {

        //TODO 点击常见问题的按钮目前是隐藏的
        tvCommonProblem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO   具体内容未知
            }
        });


    }

    private void initTopBar() {

        topBar.setButtonVisable(1, false);
        topBar.setTitleText("积分规则");
        topBar.setOnTopbarClickListener(new TopBar.topbarClickListener() {
            @Override
            public void leftClick() {

                IntegralRuleActivity.this.finish();
            }

            @Override
            public void rightClick() {

            }
        });


    }
}
