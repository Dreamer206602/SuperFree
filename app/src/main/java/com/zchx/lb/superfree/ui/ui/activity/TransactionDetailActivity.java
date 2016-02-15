package com.zchx.lb.superfree.ui.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.zchx.lb.superfree.R;
import com.zchx.lb.superfree.app.AppConstants;
import com.zchx.lb.superfree.callback.TransactionDetailCallBack;
import com.zchx.lb.superfree.entity.Transaction;
import com.zchx.lb.superfree.presenter.TransactionDetailPresenter;
import com.zchx.lb.superfree.presenter.impl.TransactionDetailPresenterImpl;
import com.zchx.lb.superfree.ui.ui.adapter.CommonAdapter;
import com.zchx.lb.superfree.ui.ui.adapter.ViewHolder;
import com.zchx.lb.superfree.entity.TransactionDetail;
import com.zchx.lb.superfree.ui.ui.widget.TopBar;
import com.zchx.lb.superfree.utils.L;
import com.zchx.lb.superfree.utils.T;
import com.zchx.lb.superfree.utils.storage.PreferencesStore;
import com.zchx.lb.superfree.view.TransactionDetailView;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Request;

/**
 * 交易明细的界面
 */
public class TransactionDetailActivity extends AppCompatActivity implements TransactionDetailView {


    @Bind(R.id.topBar)
    TopBar topBar;
    @Bind(R.id.id_ListView)
    ListView mListView;
    private List<Transaction>mDatas;
    private CommonAdapter<Transaction>mAdapter;
    private String mobile;

    private TransactionDetailPresenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_detail);
        ButterKnife.bind(this);
        mobile= PreferencesStore.getInstance(getApplicationContext()).readString(AppConstants.ParamDefaultValue.MOBILE,"");
        presenter=new TransactionDetailPresenterImpl(this);
        if (mobile != null) {
            presenter.transactionDetail(mobile);//
        }
        initTopBar();

    }
    private void initListView(List<Transaction> mDatas) {
        if(mDatas!=null){
            mAdapter=new CommonAdapter<Transaction>(TransactionDetailActivity.this,mDatas,R.layout.item_transaction_detail) {
                @Override
                public void convert(ViewHolder holder, Transaction transaction) {

                    holder.setAutoFitText(R.id.tv_time,transaction.getAddtime());
                    holder.setAutoFitText(R.id.tv_num_Transaction_amount,transaction.getMcount()+"");//充值的金额
                    holder.setAutoFitText(R.id.tv_num_Transaction_balance,transaction.getBalance()+"");//余额

                }
            };

            mListView.setAdapter(mAdapter);


        }

    }


    private void initTopBar() {

        topBar.setButtonVisable(1,false);
        topBar.setTitleText("交易明细");
        topBar.setOnTopbarClickListener(new TopBar.topbarClickListener() {
            @Override
            public void leftClick() {
                //TODO 页面的跳转
                TransactionDetailActivity.this.finish();
            }

            @Override
            public void rightClick() {

            }
        });

    }

    @Override
    public void showTransactionDetailSuccess(List<Transaction> mDatas) {
        if (mDatas != null) {
            initListView(mDatas);
        }

    }

    @Override
    public void showTransactionDetailFail(String errorMsg) {
        if (errorMsg != null) {
            T.showShortToast(getApplicationContext(),errorMsg);
        }
    }
}
