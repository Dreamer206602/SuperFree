package com.zchx.lb.superfree.ui.ui.activity;

import android.os.Bundle;

import com.zchx.lb.superfree.R;
import com.zchx.lb.superfree.app.AppConstants;
import com.zchx.lb.superfree.callback.WithDrawRecordCallBack;
import com.zchx.lb.superfree.entity.BankRecord;
import com.zchx.lb.superfree.entity.WithDrawRecord;
import com.zchx.lb.superfree.ui.ui.adapter.CommonAdapter;
import com.zchx.lb.superfree.ui.ui.adapter.ViewHolder;
import com.zchx.lb.superfree.ui.ui.widget.TopBar;
import com.zchx.lb.superfree.ui.ui.widget.xListView.XListView;
import com.zchx.lb.superfree.utils.storage.PreferencesStore;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Request;

/**
 * 这是体现记录
 */
public class WithDrawRecordActivity extends BaseActivity {

    @Bind(R.id.topBar)
    TopBar topBar;
    @Bind(R.id.id_ListView)
    XListView mListView;
    private String mobile;
    private List<BankRecord> mDatas;
    private CommonAdapter<BankRecord> mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_with_draw_record);
        ButterKnife.bind(this);
        initTopBar();
        initEvent();
    }

    private void initEvent() {
        mobile = PreferencesStore.getInstance(getApplicationContext()).readString(AppConstants.ParamDefaultValue.MOBILE, "");
        if (mobile != null) {
            OkHttpUtils.post()
                    .url(AppConstants.RequestPath.DOACTIONALL)
                    .addParams(AppConstants.ParamDefaultValue.MOBILE, mobile)
                    .build()
                    .execute(new WithDrawRecordCallBack() {
                        @Override
                        public void onError(Request request, Exception e) {

                        }

                        @Override
                        public void onResponse(WithDrawRecord withDrawRecord) {
                            mDatas = withDrawRecord.getResult_msg();
                            initListView(mDatas);
                        }
                    });
        }
    }

    private void initListView(List<BankRecord> mDatas) {
        mListView.setPullLoadEnable(false);
        mListView.setPullRefreshEnable(false);

        mAdapter = new CommonAdapter<BankRecord>(WithDrawRecordActivity.this, mDatas, R.layout.item_withdraw_record) {
            @Override
            public void convert(ViewHolder holder, BankRecord bankRecord) {

                holder.setText(R.id.tv_addtime, bankRecord.getAddtime());
                holder.setText(R.id.tv_charge, bankRecord.getCharge() + "元");
                holder.setText(R.id.tv_bankname, bankRecord.getBankname());
                holder.setText(R.id.tv_bank_status, bankRecord.getState());
                holder.setText(R.id.tv_withdraw_RMB, bankRecord.getMcount() + "元");

            }
        };
        mListView.setAdapter(mAdapter);


    }

    public void initTopBar() {

        topBar.setButtonVisable(1, false);
        topBar.setTitleText("提现记录");
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
