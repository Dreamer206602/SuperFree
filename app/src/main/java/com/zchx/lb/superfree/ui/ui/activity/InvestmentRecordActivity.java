package com.zchx.lb.superfree.ui.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.widget.ListView;
import android.widget.TextView;

import com.zchx.lb.superfree.R;
import com.zchx.lb.superfree.app.AppConstants;
import com.zchx.lb.superfree.callback.ProductCallBack;
import com.zchx.lb.superfree.entity.Product;
import com.zchx.lb.superfree.presenter.InvestmentRecordPresenter;
import com.zchx.lb.superfree.presenter.impl.InvestmentRecordPresenterImpl;
import com.zchx.lb.superfree.ui.ui.adapter.CommonAdapter;
import com.zchx.lb.superfree.entity.ProductAndRecord;
import com.zchx.lb.superfree.ui.ui.adapter.ViewHolder;
import com.zchx.lb.superfree.ui.ui.widget.TopBar;
import com.zchx.lb.superfree.utils.L;
import com.zchx.lb.superfree.utils.storage.PreferencesStore;
import com.zchx.lb.superfree.view.InvestmentRecordView;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Request;

/**
 * 投资记录的界面
 */
public class InvestmentRecordActivity extends BaseActivity implements InvestmentRecordView {

    @Bind(R.id.topBar)
    TopBar topBar;
    @Bind(R.id.id_ListView)
    ListView mListView;


    private List<Product>mDatas;
    private CommonAdapter<Product>mAdapter;
    private String mobile;//需要接收的手机号
    private InvestmentRecordPresenter presenter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_investment_record);
        ButterKnife.bind(this);
        mobile= PreferencesStore.getInstance(getApplicationContext()).readString(AppConstants.ParamDefaultValue.MOBILE,"");//用户注册的手机号
        presenter=new InvestmentRecordPresenterImpl(this);
        initTopBar();
        initEvent();

    }
    private void initEvent() {
        if(mobile!=null){
            presenter.InvestmentRecord(mobile);
        }


    }

//    private void initGetNetData() {
    //        if(mobile!=null) {
    //            OkHttpUtils.post()
    //                    .url(AppConstants.RequestPath.SELECTINVESTMENTDETAILS)
    //                    .addParams(AppConstants.ParamDefaultValue.MOBILE, mobile)//要替换手机号
    //                    .build()
    //                    .execute(new ProductCallBack() {
    //                        @Override
    //                        public void onError(Request request, Exception e) {
    //
    //                        }
    //
    //                        @Override
    //                        public void onResponse(ProductAndRecord productAndRecord) {
    //                            mDatas = productAndRecord.getResult_msg();
    //                            initListView(mDatas);
    //                        }
    //                    });
    //        }
    //
    //
    //
    //    }

    private void initListView(List<Product> mDatas) {

        mAdapter = new CommonAdapter<Product>(InvestmentRecordActivity.this, mDatas, R.layout.item_productandrecord2) {
            @Override
            public void convert(ViewHolder holder, Product product) {

                holder.setRoundProgressBarWithText(R.id.roundProgressBar, product.getPercentage());
                holder.setText(R.id.tv_num_Annual_earnings, product.getGoods_rate() + "%");
                holder.setText(R.id.tv_num_time_limit, product.getGoods_term() + "天");
                //TODO 判断总投资金额的数目
                if(product.getGoods_total_amount()>10000) {
                    holder.setText(R.id.tv_num_scale, product.getGoods_total_amount()/10000 + "万");
                }else {
                    holder.setText(R.id.tv_num_scale, product.getGoods_total_amount() + "元");
                }
                holder.setText(R.id.tv_goods_title, product.getGoods_title());
            }
        };
        mListView.setAdapter(mAdapter);


    }

    private void initTopBar() {
        topBar.setButtonVisable(1,false);
        topBar.setTitleText("投资记录");
        topBar.setOnTopbarClickListener(new TopBar.topbarClickListener() {
            @Override
            public void leftClick() {
                //TODO 页面的跳转
                InvestmentRecordActivity.this.finish();
            }
            @Override
            public void rightClick() {

            }
        });

    }

    @Override
    public void showInvestmentRecordSuccess(List<Product> mDatas) {
        if(mDatas!=null){
            initListView(mDatas);
        }

    }

    @Override
    public void showInvestmentRecordFail(String errorMsg) {
        if(errorMsg!=null){
           showMsgInBottom(errorMsg);
        }
    }
}
