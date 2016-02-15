package com.zchx.lb.superfree.ui.ui.fragment;


import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zchx.lb.superfree.app.App;
import com.zchx.lb.superfree.ui.ui.activity.BaseActivity;

import me.drakeet.materialdialog.MaterialDialog;


/**
 * Created on 2015/12/17 14:22
 * Created by Author boobooL
 * 邮箱：boobooMX@163.com
 */
public class BaseFragment  extends Fragment {

    protected View mContentView;
    protected App mApp;


    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mApp=App.getInstance();
    }

    public void showDialog(){
        final MaterialDialog materialDialog=new MaterialDialog(getContext());
            materialDialog.setTitle("友情提醒");
            materialDialog.setMessage("请您先绑定银行卡");
                materialDialog.setPositiveButton("确定", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        materialDialog.dismiss();
                    }
                });
        materialDialog.show();


    }


}
