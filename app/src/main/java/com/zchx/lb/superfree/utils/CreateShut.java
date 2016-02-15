package com.zchx.lb.superfree.utils;


import android.app.Activity;
import android.content.Intent;
import android.os.Parcelable;

import com.zchx.lb.superfree.R;
import com.zchx.lb.superfree.ui.ui.activity.GuideActivity;

//创建桌面快捷方式的工具
public class CreateShut {
	public CreateShut(Activity activity) {
		// intent进行隐式跳转,到桌面创建快捷方式
		Intent addIntent = new Intent(
				"com.android.launcher.action.INSTALL_SHORTCUT");
		//不允许重建
		addIntent.putExtra("duplicate", false);
		// 得到应用的名称
		String title = activity.getResources().getString(R.string.app_name);
		// 将应用的图标设置为Parceable类型
		Parcelable icon = Intent.ShortcutIconResource.fromContext(
				activity, R.mipmap.ic_launcher);
		// 点击图标之后的意图操作
		Intent myIntent = new Intent(activity, GuideActivity.class);
		// 设置快捷方式的名称
		addIntent.putExtra(Intent.EXTRA_SHORTCUT_NAME, title);
		// 设置快捷方式的图标
		addIntent.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE, icon);
		// 设置快捷方式的意图
		addIntent.putExtra(Intent.EXTRA_SHORTCUT_INTENT, myIntent);
		// 发送广播
		activity.sendBroadcast(addIntent);
	}
}
