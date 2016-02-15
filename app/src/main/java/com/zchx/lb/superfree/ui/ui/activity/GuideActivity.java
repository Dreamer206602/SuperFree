package com.zchx.lb.superfree.ui.ui.activity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Animation.AnimationListener;
import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;

import com.zchx.lb.superfree.R;
import com.zchx.lb.superfree.utils.NetManager;
import com.zchx.lb.superfree.utils.SharedConfig;

//软件入口，闪屏界面。
public class GuideActivity extends Activity {
	private boolean first;	//判断是否第一次打开软件
	private View view;
	private Context context;
	private Animation animation;
	private NetManager netManager;
	private SharedPreferences shared;
	private static int TIME = 1000; 										// 进入主程序的延迟时间

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		view = View.inflate(this, R.layout.activity_guide, null);
		setContentView(view);
		context = this;							//得到上下文
		shared = new SharedConfig(context).GetConfig(); 	// 得到配置文件
		netManager = new NetManager(context); 				// 得到网络管理器
	}

	@Override
	protected void onResume() {
        insert();
		super.onResume();
	}

	public void onPause() {
		super.onPause();
	}

    public void insert(){
        if (netManager.isOpenNetwork()) {
            // 如果网络可用则判断是否第一次进入，如果是第一次则进入欢迎界面
            first = shared.getBoolean("First", true);
            Intent intent;
            //如果第一次，则进入引导页WelcomeActivity
            if (first) {
                intent = new Intent(GuideActivity.this,
                        WelcomeActivity.class);
            } else {
                intent = new Intent(GuideActivity.this,
                        MainActivity.class);
            }
            startActivity(intent);
//            // 设置Activity的切换效果
//            overridePendingTransition(R.anim.in_from_right,
//                    R.anim.out_to_left);
            GuideActivity.this.finish();
        }else {
            // 如果网络不可用，则弹出对话框，对网络进行设置
            Builder builder = new Builder(context);
            builder.setTitle("没有可用的网络");
            builder.setMessage("是否对网络进行设置?");
            builder.setPositiveButton("确定",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = null;
                            try {
                                String sdkVersion = android.os.Build.VERSION.SDK;
                                if (Integer.valueOf(sdkVersion) > 10) {
                                    intent = new Intent(
                                            android.provider.Settings.ACTION_WIRELESS_SETTINGS);
                                } else {
                                    intent = new Intent();
                                    ComponentName comp = new ComponentName(
                                            "com.android.settings",
                                            "com.android.settings.WirelessSettings");
                                    intent.setComponent(comp);
                                    intent.setAction("android.intent.action.VIEW");
                                }
                                GuideActivity.this.startActivity(intent);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });
            builder.setNegativeButton("取消",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            GuideActivity.this.finish();
                        }
                    });
            builder.show();
        }


    }


}
