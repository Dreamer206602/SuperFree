package com.zchx.lb.superfree.ui.ui.activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.zchx.lb.superfree.R;
import com.zchx.lb.superfree.app.AppConstants;
import com.zchx.lb.superfree.entity.IntegralMarket;
import com.zchx.lb.superfree.entity.Product;
import com.zchx.lb.superfree.ui.ui.fragment.BaseFragment;
import com.zchx.lb.superfree.ui.ui.fragment.HomeFragment;
import com.zchx.lb.superfree.ui.ui.fragment.IntegralMarketFragment;
import com.zchx.lb.superfree.ui.ui.fragment.MineFragment;
import com.zchx.lb.superfree.ui.ui.fragment.ProductFragment;
import com.zchx.lb.superfree.utils.L;
import com.zchx.lb.superfree.utils.storage.PreferencesStore;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created on 2015/12/17 15:04
 * Created by Author boobooL
 * 邮箱：boobooMX@163.com
 */

/**
 * 这是MainActivity
 */
public class MainActivity extends BaseActivity {
    @Bind(R.id.main_Container)
    FrameLayout mainContainer;
    @Bind(R.id.home)
    RadioButton home;
    @Bind(R.id.product)
    RadioButton product;
    @Bind(R.id.integralMarket)
    RadioButton integralMarket;
    @Bind(R.id.mine)
    RadioButton mine;
    @Bind(R.id.main_bottom_menu)
    RadioGroup mainBottomMenu;
    BaseFragment bf[];
    int curPageIndex = 0;

    private int aty_frg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        //测试2
        bf = new BaseFragment[4];
        bf[0] = HomeFragment.getInstance();
        bf[1] = ProductFragment.getInstance();
        bf[2] = IntegralMarketFragment.getInstance();
        bf[3] = MineFragment.getInstance();
        FragmentTransaction fragmentTransaction =
                getSupportFragmentManager().beginTransaction();
        for (int i = 0; i < 4; i++) {
            fragmentTransaction.add(R.id.main_Container, bf[i]);
            fragmentTransaction.hide(bf[i]);
        }
        fragmentTransaction.show(bf[0]);
        fragmentTransaction.commit();
        initEvent();
        switchFragment(0);
    }

    /**
     * 初始化事件
     */
    private void initEvent() {
        mainBottomMenu.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int index = 0;
                switch (checkedId) {
                    //首页
                    case R.id.home:
                        index = 0;
                        break;
                    //产品
                    case R.id.product:
                        index = 1;
                        break;
                    //积分超市
                    case R.id.integralMarket:
                        index = 2;
                        ((IntegralMarketFragment)(bf[2])).initData();
                        break;
                    //个人账户
                    case R.id.mine:
                        if(PreferencesStore.getInstance(getApplicationContext()).readBoolean(AppConstants.ParamDefaultValue.ISLOGIN,true)){
                            startActivityForResult(new Intent(MainActivity.this,LoginActivity.class), 100);
                            index = curPageIndex;
                        }else{
                            index = 3;
                            //TODO 界面的跳转都去刷新一下界面
                            ((MineFragment)bf[3]).initEvent();
                            ((MineFragment)bf[3]).initNetData();
                        }
                        break;
                }
                switchFragment(index);
            }
        });
    }

    //切换fragment
    private void switchFragment(int index){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.hide(bf[curPageIndex]);
        fragmentTransaction.show(bf[index]);
        curPageIndex = index;
        fragmentTransaction.commitAllowingStateLoss();


        //底部tab也要跟着变化
        home.setChecked(false);
        product.setChecked(false);
        integralMarket.setChecked(false);
        mine.setChecked(false);

        switch (index){
            case 0:
                home.setChecked(true);
                break;
            case 1:
                product.setChecked(true);
                break;
            case 2:
                integralMarket.setChecked(true);
                break;
            case 3:
                mine.setChecked(true);
                break;
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == RESULT_OK){
            if(requestCode == 100){
                //从登陆页面回调的
                switchFragment(3);//跳转到个人界面
                ((MineFragment)bf[3]).initEvent();
                ((MineFragment)bf[3]).initNetData();
            } else if(requestCode == 101){
                //从更多界面退出时
                switchFragment(0);//跳转到个人界面
                PreferencesStore.getInstance(getApplicationContext()).save(AppConstants.ParamDefaultValue.ISLOGIN, true);

            }

        }
    }
}


