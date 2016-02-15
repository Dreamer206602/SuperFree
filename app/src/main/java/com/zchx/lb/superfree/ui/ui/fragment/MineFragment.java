package com.zchx.lb.superfree.ui.ui.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.zchx.lb.superfree.R;
import com.zchx.lb.superfree.app.AppConstants;
import com.zchx.lb.superfree.ui.ui.activity.AccountSettingActivity;
import com.zchx.lb.superfree.ui.ui.activity.InvestmentRecordActivity;
import com.zchx.lb.superfree.ui.ui.activity.MoreActivity;
import com.zchx.lb.superfree.ui.ui.activity.MyPointsActivity;
import com.zchx.lb.superfree.ui.ui.activity.RechargeActivity;
import com.zchx.lb.superfree.ui.ui.activity.TransactionDetailActivity;
import com.zchx.lb.superfree.ui.ui.activity.WithDrawActivity;
import com.zchx.lb.superfree.ui.ui.adapter.GridViewAdaper.PictureAdapter;
import com.zchx.lb.superfree.ui.ui.widget.circleImage.RoundImageView;
import com.zchx.lb.superfree.utils.L;
import com.zchx.lb.superfree.utils.ToolsUtil;
import com.zchx.lb.superfree.utils.storage.PreferencesStore;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Request;

/**
 * 这是个人账户的Fragment
 */
public class MineFragment extends BaseFragment  {
    public static MineFragment mineFragment;
    @Bind(R.id.gridView)
    GridView mGridView;

    @Bind(R.id.tv_person_phone)
    TextView tvPersonPhone;//用户的手机号


    @Bind(R.id.tv_person_image)
    RoundImageView tvPersonImage;//用户的头像


    @Bind(R.id.tv_num_available_balance)
    TextView tvNumAvailableBalance;//可用的余额


    @Bind(R.id.tv_num_own_RMB)
    TextView tvNumOwnRMB;//现有资产


    @Bind(R.id.tv_num_received_interest)
    TextView tvNumReceivedInterest;//已收利息

    @Bind(R.id.tv_num_waiting_interest)
    TextView tvNumWaitingInterest;//待收的利息

    @Bind(R.id.tv_click_recharge)
    TextView tvClickRecharge;//充值的按钮


    @Bind(R.id.tv_click_withdraw)
    TextView tvClickWithdraw;//体现的按钮



    private String mobile;//TODO 用户注册的手机号
    private double availableBalance;//可用的余额
    private double allAssets;//所有资产
    private double waitingInterest;//待收利息
    private double receivedInterest;//已收利息

    private String[] titles;
    private int[] images;
    private String cardno;//绑定的银行卡号
    private String userName;//用户绑定个人信息的用户名

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        images = new int[]{
                R.mipmap.investmentrecord,
                R.mipmap.transactiondetail,
                R.mipmap.mypoints,
                //                        R.mipmap.station,
                //                        R.mipmap.invitefriends,
                //                        R.mipmap.sign,
                R.mipmap.accountsettings, R.mipmap.more
        };

        titles = getResources().getStringArray(R.array.item_mine_title);


    }

    public static MineFragment getInstance() {
        if (mineFragment == null) {
            synchronized (MineFragment.class) {
                if (mineFragment == null) {
                    mineFragment = new MineFragment();
                }
            }
        }
        return mineFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.item_mine_top, container, false);
        ButterKnife.bind(this, view);
        initEvent();
        initNetData();
        return view;
    }

    public void initEvent() {

        //TODO 对用户的注册手机号进行设置
        mobile = PreferencesStore.getInstance(getContext()).readString(AppConstants.ParamDefaultValue.MOBILE, "");
        if (!TextUtils.isEmpty(mobile)) {
            String replace = mobile.replace(mobile.substring(3, 8), "*****");
            tvPersonPhone.setText(replace);
        } else {
            tvPersonPhone.setText("");
        }


    }

    public void initNetData() {
        //对个人信息的解析，当个人信息绑定成功之后，需要把银行卡号传递给充值和体现的界面
        if (mobile != null) {
            OkHttpUtils.post()
                    .url(AppConstants.RequestPath.SELECTINFO)
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
                                JSONObject result_msg = object.getJSONObject("result_msg");
                                if (result.equals("1")) {
                                    /**
                                     * username : 李小丽
                                     * bankname :
                                     * bankicon :
                                     * bindmobile : 15366056636
                                     * idcard : 320621199005033320
                                     * cardno : 6216910201109110
                                     * mobile : 15366056636
                                     * bindid :
                                     */
                                    userName = result_msg.getString("username");//用户名
                                    String bankname = result_msg.getString("bankname");
                                    String bankicon = result_msg.getString("bankicon");
                                    String bindmobile = result_msg.getString("bindmobile");
                                    String idcard = result_msg.getString("idcard");
                                    //绑定的银行卡号
                                    cardno = result_msg.getString("cardno");
                                    String mobile = result_msg.getString("mobile");
                                    String bindid = result_msg.getString("bindid");


                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    });
        }


        //返回用户的余额
        if (mobile != null) {
            OkHttpUtils.post()
                    .url(AppConstants.RequestPath.RETURNBALANCE)//用户的余额  mobile替换
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
                                if (object.getString("result").equals("1")) {
                                    availableBalance = object.getDouble("result_msg");//可用的余额
                                    if (availableBalance > 0) {
                                        double db_availableBalance = ToolsUtil.getDouble(availableBalance);
                                        L.d("MineFragment---->ss" + db_availableBalance);
                                        tvNumAvailableBalance.setText(db_availableBalance + "");
                                    }

                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    });
        }

        if (mobile != null) {
            OkHttpUtils.post()
                    .url(AppConstants.RequestPath.RETURNASSETS)//返回总资产
                    .addParams(AppConstants.ParamDefaultValue.MOBILE, mobile)//替换手机号
                    .build()
                    .execute(new StringCallback() {
                        @Override
                        public void onError(Request request, Exception e) {

                        }

                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject object = new JSONObject(response);
                                if (object.getString("result").equals("1")) {
                                    JSONObject result_msg = object.getJSONObject("result_msg");
                                    receivedInterest = result_msg.getDouble("interest_received");//已收的利息
                                    waitingInterest = result_msg.getDouble("no_received");//代收的利息
                                    allAssets = result_msg.getDouble("all_assets");//所有的资产

                                    if (allAssets > 0 && receivedInterest > 0 && waitingInterest > 0) {
                                        double db_allAssets = ToolsUtil.getDouble(allAssets);
                                        double db_receivedInterest = ToolsUtil.getDouble(receivedInterest);
                                        double db_waitingInterest = ToolsUtil.getDouble(waitingInterest);
                                        tvNumOwnRMB.setText(db_allAssets + "");
                                        tvNumReceivedInterest.setText(db_receivedInterest + "");
                                        tvNumWaitingInterest.setText(db_waitingInterest + "");
                                    }

                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    });
        }
    }


    @Override
    public void onResume() {
        super.onResume();
        initGridView();
        initClick();
    }

    //点击按钮的处理
    private void initClick() {
        //TODO 对充值，提现，投资记录，交易明细，
        // TODO 首先进行绑定银行卡的判断，如果用户没有绑定，先去绑定银行卡
        //TODO 充值的按钮
        tvClickRecharge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO 判断用户是否绑定银行卡,没有的话提醒用户去绑定银行卡，否则跳转到充值的界面
                if (PreferencesStore.getInstance(getContext()).readBoolean(AppConstants.ParamDefaultValue.ISBINDSUCCESS, true)) {
                    //TODO 弹出一个对话框，请您先绑定银行卡
                    showDialog();
                } else {
                    Intent intent = new Intent(getActivity(), RechargeActivity.class);
                    startActivity(intent);
                }

            }
        });

        //TODO 提现按钮
        tvClickWithdraw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (PreferencesStore.getInstance(getContext()).readBoolean(AppConstants.ParamDefaultValue.ISBINDSUCCESS, true)) {
                    showDialog();
                } else {
                    Intent intent = new Intent(getActivity(), WithDrawActivity.class);
                    //                    //总资产
                    //                    //银行卡号
                    //                    if (availableBalance >=0 && cardno != null) {
                    //                        intent.putExtra(AppConstants.ParamDefaultValue.AVAILABLEBALANCE, availableBalance);
                    //                        intent.putExtra(AppConstants.ParamDefaultValue.CARDNO, cardno);
                    //                    }
                    startActivity(intent);
                }

            }
        });

    }

    private void initGridView() {
        mGridView.setAdapter(new PictureAdapter(titles, images, getActivity()));

        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                switch (position) {
                    case 0://投资记录
                        if (PreferencesStore.getInstance(getContext()).readBoolean(AppConstants.ParamDefaultValue.ISBINDSUCCESS, true)) {
                            showDialog();
                        } else {
                            startActivity(new Intent(getActivity(), InvestmentRecordActivity.class));
                        }
                        break;

                    case 1://交易明细
                        if (PreferencesStore.getInstance(getContext()).readBoolean(AppConstants.ParamDefaultValue.ISBINDSUCCESS, true)) {
                            showDialog();
                        } else {
                            startActivity(new Intent(getActivity(), TransactionDetailActivity.class));
                        }
                        break;
                    case 2://我的积分
                        startActivity(new Intent(getActivity(), MyPointsActivity.class));
                        break;
                    case 3://账户设置的界面
                        Intent intent = new Intent(getActivity(), AccountSettingActivity.class);
                        startActivity(intent);
                        break;
                    case 4://更多的界面
                        getActivity().startActivityForResult(new Intent(getActivity(), MoreActivity.class), 101);
                        break;

                }

            }
        });


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }






}
