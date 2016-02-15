package com.zchx.lb.superfree.ui.ui.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.google.gson.Gson;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.zchx.lb.superfree.R;
import com.zchx.lb.superfree.app.AppConstants;
import com.zchx.lb.superfree.entity.CarouselPicture;
import com.zchx.lb.superfree.entity.SelectNewProduct;
import com.zchx.lb.superfree.ui.ui.activity.ProjectDetailActivity;
import com.zchx.lb.superfree.ui.ui.adapter.CommonAdapter;
import com.zchx.lb.superfree.ui.ui.adapter.NetworkImageHolderView;
import com.zchx.lb.superfree.ui.ui.adapter.ViewHolder;
import com.zchx.lb.superfree.ui.ui.widget.TopBar;
import com.zchx.lb.superfree.utils.storage.CacheUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Request;


/**
 * 这是首页的Fragment
 */
public class HomeFragment extends BaseFragment {

    public static HomeFragment homeFragment;
    @Bind(R.id.topBar)
    TopBar topBar;
    @Bind(R.id.convenientBanner)
    ConvenientBanner convenientBanner;
    @Bind(R.id.id_ListView)
    ListView mListView;
    private List<String> networkImages;
    private List<SelectNewProduct> mDatas;
    private CommonAdapter<SelectNewProduct> mAdapter;
    private String id;
    private int percentage;

    //提供一个HomeFragment对象
    public static HomeFragment getInstance() {
        if (homeFragment == null) {
            synchronized (HomeFragment.class) {
                if (homeFragment == null) {
                    homeFragment = new HomeFragment();
                }
            }
        }
        return homeFragment;
    }

    private void initEvent() {
        //        mListView.setPullLoadEnable(false);
        //        mListView.setPullRefreshEnable(false);

        //TODO 获得首页轮播图
        String cache = CacheUtils.getCache(AppConstants.RequestPath.PICTURECPGOODS,
                getContext());
        if (!TextUtils.isEmpty(cache)) {// 如果缓存存在,直接解析数据, 无需访问网路
            parseData(cache);
        }
        getDataFromServer();// 不管有没有缓存, 都获取最新数据, 保证数据最新

    }

    private void initEvent2() {
        // TODO 获得首页的数据
        String cache2 = CacheUtils.getCache(AppConstants.RequestPath.SELECTNEWPRODUCT,
                getContext());
        if (!TextUtils.isEmpty(cache2)) {// 如果缓存存在,直接解析数据, 无需访问网路
            parseData2(cache2);
        }
        getDataFromServer2();// 不管有没有缓存, 都获取最新数据, 保证数据最新

    }


    //TODO 获得积分轮播的服务器数据
    private void getDataFromServer() {
        OkHttpUtils.post().url(AppConstants.RequestPath.PICTURECPGOODS)
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
                            CacheUtils.setCache(AppConstants.RequestPath.PICTURECPGOODS,
                                    response, getContext());
                        }
                    }
                });
    }

    //解析积分轮播图
    private void parseData(String cache) {
        if (cache != null) {
            CarouselPicture carouselPicture = new Gson().fromJson(cache, CarouselPicture.class);
            List<String> result_msg = carouselPicture.getResult_msg();
            initImageLoader();
            //网络加载例子
            convenientBanner.setPages(new CBViewHolderCreator<NetworkImageHolderView>() {
                @Override
                public NetworkImageHolderView createHolder() {
                    return new NetworkImageHolderView();
                }
            }, result_msg)
                    //设置两个点图片作为翻页指示器，不设置则没有指示器，可以根据自己需求自行配合自己的指示器,不需要圆点指示器可用不设
                    .setPageIndicator(new int[]{R.mipmap.ic_page_indicator, R.mipmap.ic_page_indicator_focused});
            //设置指示器的方向
            //                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.ALIGN_PARENT_RIGHT)
            // .setOnItemClickListener(this)
            //监听翻页事件;
            // .setOnPageChangeListener(this);
            //开始自动翻页
            convenientBanner.startTurning(3000);

        }
    }


    //获得首页的内容的服务数据
    private void getDataFromServer2() {

        OkHttpUtils.post().url(AppConstants.RequestPath.SELECTNEWPRODUCT).build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {
            }

            @Override
            public void onResponse(String result) {

                if (result != null) {
                    parseData2(result);
                    // 设置缓存
                    CacheUtils.setCache(AppConstants.RequestPath.SELECTNEWPRODUCT, result, getContext());
                }
            }
        });

    }

    //解析首页的网络数据
    private void parseData2(String cache2) {
        if (cache2 != null) {
            try {
                JSONObject object = new JSONObject(cache2);
                if (object.getString("result").equals("1")) {
                    JSONObject result_msg = object.getJSONObject("result_msg");
                    id = result_msg.getString("id");
                    int goods_term = result_msg.getInt("goods_term");
                    percentage = result_msg.getInt("percentage");
                    int goods_total_amount = result_msg.getInt("goods_total_amount");
                    Double goods_rate = result_msg.getDouble("goods_rate");
                    String goods_title = result_msg.getString("goods_title");
                    mDatas = new ArrayList<SelectNewProduct>();
                    mDatas.add(new SelectNewProduct(id, goods_term, percentage, goods_total_amount, goods_rate, goods_title));
                    mAdapter = new CommonAdapter<SelectNewProduct>(getActivity(), mDatas, R.layout.item_home) {
                        @Override
                        public void convert(ViewHolder holder, SelectNewProduct selectNewProduct) {
                            holder.setText(R.id.tv_goods_term, selectNewProduct.getGoods_term() + "");
                            holder.setSeekBarProgress(R.id.percentage, selectNewProduct.getPercentage());

                            if (selectNewProduct.getGoods_total_amount()>10000){
                                holder.setText(R.id.tv_goods_total_amount, selectNewProduct.getGoods_total_amount()/10000 + "");
                            }
                            holder.setText(R.id.tv_goods_rate, selectNewProduct.getGoods_rate() + "");
                            holder.setText(R.id.tv_goods_title, selectNewProduct.getGoods_title());

                            holder.setOnClickListener(R.id.tv_click, new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent intent = new Intent(getActivity(), ProjectDetailActivity.class);
                                    intent.putExtra(AppConstants.ParamDefaultValue.ID, id);
                                    intent.putExtra(AppConstants.ParamDefaultValue.PERCENTAGE, percentage + "");
                                    startActivity(intent);

                                }
                            });
                        }
                    };
                    mListView.setAdapter(mAdapter);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View ret = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, ret);
        initTopBar();
        initEvent();
        initEvent2();
        return ret;
    }


    //初始化TopBar
    private void initTopBar() {
        topBar.setButtonVisable(0, false);
        topBar.setButtonVisable(1, false);
        topBar.setTitleText("超有利");

    }

    //    private void initConvenientBanner() {
    //        initImageLoader();
    //        //网络加载例子
    //        convenientBanner.setPages(new CBViewHolderCreator<NetworkImageHolderView>() {
    //            @Override
    //            public NetworkImageHolderView createHolder() {
    //                return new NetworkImageHolderView();
    //            }
    //        }, networkImages)
    //                //设置两个点图片作为翻页指示器，不设置则没有指示器，可以根据自己需求自行配合自己的指示器,不需要圆点指示器可用不设
    //                .setPageIndicator(new int[]{R.mipmap.ic_page_indicator, R.mipmap.ic_page_indicator_focused});
    //        //设置指示器的方向
    //        //                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.ALIGN_PARENT_RIGHT)
    //        // .setOnItemClickListener(this)
    //        //监听翻页事件;
    //        // .setOnPageChangeListener(this);
    //
    //        //开始自动翻页
    //        convenientBanner.startTurning(3000);
    //    }

    //初始化网络图片缓存库
    private void initImageLoader() {
        //网络图片例子,结合常用的图片缓存库UIL,你可以根据自己需求自己换其他网络图片库
        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder().
                showImageForEmptyUri(R.mipmap.ic_default_adimage)
                .cacheInMemory(true)
                        // .cacheOnDisk(true)
                .cacheOnDisc(true)
                .build();

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
                getActivity()).defaultDisplayImageOptions(defaultOptions)
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .denyCacheImageMultipleSizesInMemory()
                .discCacheFileNameGenerator(new Md5FileNameGenerator())
                .tasksProcessingOrder(QueueProcessingType.LIFO).build();
        ImageLoader.getInstance().init(config);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }


}
