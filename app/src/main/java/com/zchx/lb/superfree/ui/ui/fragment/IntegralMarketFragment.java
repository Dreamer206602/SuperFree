package com.zchx.lb.superfree.ui.ui.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.zchx.lb.superfree.R;
import com.zchx.lb.superfree.app.AppConstants;
import com.zchx.lb.superfree.entity.CarouselPicture;
import com.zchx.lb.superfree.entity.Integral;
import com.zchx.lb.superfree.entity.IntegralMarket;
import com.zchx.lb.superfree.ui.ui.activity.IntegralDetailsActivity;
import com.zchx.lb.superfree.ui.ui.activity.IntegralRuleActivity;
import com.zchx.lb.superfree.ui.ui.adapter.CommonAdapter;
import com.zchx.lb.superfree.ui.ui.adapter.NetworkImageHolderView;
import com.zchx.lb.superfree.ui.ui.adapter.ViewHolder;
import com.zchx.lb.superfree.ui.ui.widget.TopBar;
import com.zchx.lb.superfree.ui.ui.widget.xListView.XListView;
import com.zchx.lb.superfree.utils.storage.CacheUtils;
import com.zchx.lb.superfree.utils.storage.PreferencesStore;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Request;

/**
 * 这是积分商城的Fragment
 */
public class IntegralMarketFragment extends BaseFragment implements XListView.IXListViewListener {


    public static IntegralMarketFragment integralMarketFragment;
    @Bind(R.id.topBar)
    TopBar topBar;
    @Bind(R.id.convenientBanner)
    ConvenientBanner convenientBanner;//顶部广告栏控件


    @Bind(R.id.tv_num_user_integral)
    TextView tvNumAvailableIntegral;//可用的积分


    @Bind(R.id.tv_Integral_rules)
    TextView tvIntegralRules;


    @Bind(R.id.id_ListView)
    XListView mListView;
    private List<Integral> mDatas;
    public CommonAdapter<Integral> mAdapter;
    private String mobile;//需要传值过来的的手机号


    public static IntegralMarketFragment getInstance() {
        if (integralMarketFragment == null) {
            synchronized (IntegralMarketFragment.class) {
                if (integralMarketFragment == null) {
                    integralMarketFragment = new IntegralMarketFragment();
                }
            }
        }
        return integralMarketFragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();

    }

    //需要刷新用户的积分
    public void initData() {
        if (PreferencesStore.getInstance(getContext()).readBoolean(AppConstants.ParamDefaultValue.ISLOGIN, true)) {
            mobile = PreferencesStore.getInstance(getContext()).readString(AppConstants.ParamDefaultValue.MOBILE, "");
            if (mobile != null) {
                OkHttpUtils.post()
                        .url(AppConstants.RequestPath.GETINTER)
                        .addParams(AppConstants.ParamDefaultValue.MOBILE, mobile)
                        .build()
                        .execute(new StringCallback() {
                            @Override
                            public void onError(Request request, Exception e) {
                            }
                            @Override
                            public void onResponse(String response) {
                                try {
                                    JSONObject object = new JSONObject(response);
                                    String result = object.getString("result");
                                    String result_msg = object.getString("result_msg");
                                    if (result.equals("1")) {
                                        tvNumAvailableIntegral.setText(result_msg);
                                    } else {
                                        tvNumAvailableIntegral.setText("");
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
            }

        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View ret = inflater.inflate(R.layout.fragment_integral_market, container, false);
        ButterKnife.bind(this, ret);
        initNetImage();//加载积分轮播图
        initNetData();//加载积分图片
        return ret;
    }

    private void initNetData() {
        // TODO 获得积分列表的数据
        String cache2 = CacheUtils.getCache(AppConstants.RequestPath.PICTUREINTER,
                getContext());
        if (!TextUtils.isEmpty(cache2)) {// 如果缓存存在,直接解析数据, 无需访问网路
            parseData2(cache2);
        }
        getDataFromServer2();// 不管有没有缓存, 都获取最新数据, 保证数据最新
    }

    //解析积分列表的数据
    private void parseData2(String cache2) {
        if (cache2 != null) {
            IntegralMarket integralMarket = new Gson().fromJson(cache2, IntegralMarket.class);
            mDatas = integralMarket.getResult_msg();
            if (mDatas != null) {
                initListView(mDatas);
            }
        }

    }

    //TODO 获得积分列表的网络数据
    private void getDataFromServer2() {
        OkHttpUtils.post()
                .url(AppConstants.RequestPath.PICTUREINTER)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {
                    }

                    @Override
                    public void onResponse(String response) {
                        if (response != null) {
                            parseData2(response);
                            // 设置缓存
                            CacheUtils.setCache(AppConstants.RequestPath.PICTUREINTER, response, getContext());
                        }
                    }
                });

    }

    private void initListView(final List<Integral> mDatas) {
        mListView.setPullLoadEnable(false);
        mAdapter = new CommonAdapter<Integral>(getActivity(), mDatas, R.layout.item_integration) {
            @Override
            public void convert(ViewHolder holder, Integral integral) {
                String pro_img = integral.getPro_img();
                ImageView img = holder.getView(R.id.id_image);
                Glide.with(getContext())
                        .load(pro_img)
                        .placeholder(R.mipmap.ic_default_adimage)
                        .crossFade()
                        .into(img);
                holder.setText(R.id.tv_goods_title, integral.getPro_name());
                holder.setText(R.id.tv_num_product_integral, integral.getPro_integral() + "");
                holder.setText(R.id.tv_num_product_price, integral.getPro_price() + "元");
            }
        };
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), IntegralDetailsActivity.class);
                intent.putExtra(AppConstants.ParamDefaultValue.PRO_ID, mDatas.get(position - mListView.getHeaderViewsCount()).getPro_id() + "");
                startActivity(intent);
            }
        });


    }

    //TODO 加载积分轮播图
    private void initNetImage() {
        //TODO 获得积分轮播图
        String cache = CacheUtils.getCache(AppConstants.RequestPath.PICTUREINTERALL,
                getContext());
        if (!TextUtils.isEmpty(cache)) {// 如果缓存存在,直接解析数据, 无需访问网路
            parseData(cache);
        }
        getDataFromServer();// 不管有没有缓存, 都获取最新数据, 保证数据最新
    }

    //解析积分轮播图
    private void parseData(String cache) {
        if (cache != null) {
            CarouselPicture carouselPicture = new Gson().fromJson(cache, CarouselPicture.class);
            List<String> result_msg = carouselPicture.getResult_msg();
            if (result_msg != null) {
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

    }

    //TODO 获得积分轮播图的数据
    private void getDataFromServer() {
        OkHttpUtils.post().url(AppConstants.RequestPath.PICTUREINTERALL)
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
                            CacheUtils.setCache(AppConstants.RequestPath.PICTUREINTERALL, response, getContext());
                        }
                    }
                });
        //                .execute(new CarouselPictureCallBack() {
        //            @Override
        //            public void onError(Request request, Exception e) {
        //            }
        //            @Override
        //            public void onResponse(CarouselPicture carouselPicture) {
        //                result_msg = carouselPicture.getResult_msg();
        //                initConvenientBanner();
        //            }
        //        });

    }

    @Override
    public void onResume() {
        super.onResume();
        initTopBar();
        initEvent();

    }

    /**
     * 初始化事件
     */
    private void initEvent() {
        //点击积分规则的点击事件
        tvIntegralRules.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), IntegralRuleActivity.class));
            }
        });


    }

    //初始化TopBar
    public void initTopBar() {
        //隐藏TopBar左侧的按钮
        topBar.setButtonVisable(0, false);
        topBar.setButtonVisable(1, false);
        topBar.setTitleText("积分超市");
    }

    //    private void initConvenientBanner() {
    //        initImageLoader();
    //        //网络加载例子
    //        convenientBanner.setPages(new CBViewHolderCreator<NetworkImageHolderView>() {
    //            @Override
    //            public NetworkImageHolderView createHolder() {
    //                return new NetworkImageHolderView();
    //            }
    //        }, result_msg)
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
    //
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
    public void onRefresh() {


    }

    @Override
    public void onLoadMore() {


    }


}
