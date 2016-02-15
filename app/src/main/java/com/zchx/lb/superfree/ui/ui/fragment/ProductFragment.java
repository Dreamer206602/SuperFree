package com.zchx.lb.superfree.ui.ui.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.gson.Gson;
import com.zchx.lb.superfree.R;
import com.zchx.lb.superfree.app.App;
import com.zchx.lb.superfree.app.AppConstants;
import com.zchx.lb.superfree.entity.Product;
import com.zchx.lb.superfree.entity.ProductAndRecord;
import com.zchx.lb.superfree.ui.ui.activity.ProjectDetailActivity;
import com.zchx.lb.superfree.ui.ui.adapter.CommonAdapter;
import com.zchx.lb.superfree.ui.ui.adapter.ViewHolder;
import com.zchx.lb.superfree.ui.ui.widget.TopBar;
import com.zchx.lb.superfree.ui.ui.widget.waterListView.WaterDropListView;
import com.zchx.lb.superfree.ui.ui.widget.xListView.XListView;
import com.zchx.lb.superfree.ui.ui.widget.xListView.XListView.IXListViewListener;
import com.zchx.lb.superfree.utils.T;
import com.zchx.lb.superfree.utils.storage.CacheUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Request;

/**
 * 这是理财产品的Fragment
 */
public class ProductFragment extends BaseFragment  {

    public static ProductFragment productFragment;
    @Bind(R.id.id_ListView)
    ListView mListView;
    @Bind(R.id.topBar)
    TopBar topBar;
    private List<Product> mDatas;
    private CommonAdapter<Product> mAdapter;


    private void initEvent() {
        String cache = CacheUtils.getCache(AppConstants.RequestPath.CPGOODSALL,
                getContext());

        if (!TextUtils.isEmpty(cache)) {// 如果缓存存在,直接解析数据, 无需访问网路
            parseData(cache);
        }
        getDataFromServer();// 不管有没有缓存, 都获取最新数据, 保证数据最新

    }

    private void getDataFromServer() {
        OkHttpUtils.post()
                .url(AppConstants.RequestPath.CPGOODSALL)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {
                    }

                    @Override
                    public void onResponse(String response) {
                        if (response != null) {
                            parseData(response);
                            // 设置缓存
                            CacheUtils.setCache(AppConstants.RequestPath.CPGOODSALL,
                                    response, getContext());
                        }
                    }
                });
        //                .execute(new ProductCallBack() {
        //                    @Override
        //                    public void onError(Request request, Exception e) {
        //
        //                    }
        //
        //                    @Override
        //                    public void onResponse(ProductAndRecord productAndRecord) {
        //                        mDatas = productAndRecord.getResult_msg();
        //                        if (mDatas != null) {
        //                            initListView(mDatas);
        //                        }
        //
        //                    }
        //                });

    }

    private void parseData(String cache) {

        if (cache != null) {
            ProductAndRecord productAndRecord = new Gson().fromJson(cache, ProductAndRecord.class);
            mDatas = productAndRecord.getResult_msg();
            if (mDatas != null) {
                initListView(mDatas);
            }

        }


    }


    private void initListView( final List<Product> mDatas) {
        if (mDatas != null) {
            mAdapter = new CommonAdapter<Product>(getActivity(), mDatas, R.layout.item_productandrecord) {
                @Override
                public void convert(ViewHolder holder, Product product) {
                    holder.setRoundProgressBarWithText(R.id.roundProgressBar, product.getPercentage());
                    holder.setText(R.id.tv_num_Annual_earnings, product.getGoods_rate() + "%");
                    holder.setText(R.id.tv_num_time_limit, product.getGoods_term() + "天");
                    //TODO 把元----》转换成万元
                    if (product.getGoods_total_amount() > 10000) {
                        holder.setText(R.id.tv_num_scale, product.getGoods_total_amount() / 10000 + "万元");
                    } else {
                        holder.setText(R.id.tv_num_scale, product.getGoods_total_amount() + "元");
                    }

                    holder.setText(R.id.tv_investment_amount, product.getGoods_price() + "");
                    holder.setText(R.id.tv_goods_title, product.getGoods_title());
                }
            };
            mListView.setAdapter(mAdapter);
            mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(getActivity(), ProjectDetailActivity.class);
                    intent.putExtra(AppConstants.ParamDefaultValue.ID, mDatas.get(position - mListView.getHeaderViewsCount()).getId() + "");
                    intent.putExtra(AppConstants.ParamDefaultValue.PERCENTAGE, mDatas.get(position - mListView.getHeaderViewsCount()).getPercentage() + "");
                    intent.putExtra(AppConstants.ParamDefaultValue.GOODS_TITLE,mDatas.get(position-mListView.getHeaderViewsCount()).getGoods_title());
                    startActivity(intent);
                }
            });
        }

    }

    public static ProductFragment getInstance() {
        if (productFragment == null) {
            synchronized (ProductFragment.class) {
                if (productFragment == null) {
                    productFragment = new ProductFragment();
                }
            }
        }
        return productFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_product, container, false);
        ButterKnife.bind(this, view);
        initEvent();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        initTopBar();

    }

    //初始化TopBar
    private void initTopBar() {
        topBar.setButtonVisable(0, false);
        topBar.setButtonVisable(1, false);
        topBar.setTitleText("理财");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }


}
