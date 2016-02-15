package com.zchx.lb.superfree.ui.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zchx.lb.superfree.R;
import com.zchx.lb.superfree.app.AppConstants;
import com.zchx.lb.superfree.ui.ui.widget.TopBar;
import com.zchx.lb.superfree.ui.ui.widget.autoFitTextView.AutofitTextView;
import com.zchx.lb.superfree.ui.ui.widget.circleImage.RoundImageView;
import com.zchx.lb.superfree.ui.ui.widget.progressbar.HorizontalProgressBarWithNumber;
import com.zchx.lb.superfree.utils.T;
import com.zchx.lb.superfree.utils.storage.PreferencesStore;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Request;

public class ProjectDetailActivity extends BaseActivity {

    @Bind(R.id.topBar)
    TopBar topBar;
    @Bind(R.id.tv_num_annual_Earnings)
    TextView tvNumAnnualEarnings;//年化收益率
    @Bind(R.id.tv_num_deadline)
    TextView tvNumDeadline;//期限
    @Bind(R.id.tv_num_scale)
    TextView tvNumScale;//项目规模
    @Bind(R.id.tv_num_portion)
    TextView tvNumPortion;//多少元起投
    @Bind(R.id.horizonProgressBar)
    HorizontalProgressBarWithNumber horizonProgressBar;//水平进度条
    @Bind(R.id.tv_num_raised)
    TextView tvNumRaised;//已经融资进度


    @Bind(R.id.tv_num_residual_share)
    TextView tvNumResidualShare;//剩余的份数

    @Bind(R.id.tv_residual_share)
    TextView tvResidualShare;

    @Bind(R.id.tv_project_type)
    TextView tvProjectType;
    @Bind(R.id.tv_related_project)
    TextView tvRelatedProject;//项目类型
    @Bind(R.id.tv_num_interest_time)
    TextView tvNumInterestTime;//起息时间
    @Bind(R.id.tv_num_repayment_mode)
    TextView tvNumRepaymentMode;//还款方式
    @Bind(R.id.tv_num_Repayment_date)
    TextView tvNumRepaymentDate;//还款日期


    @Bind(R.id.tv_Check_details)
    TextView tvCheckDetails;

    @Bind(R.id.tv_calculator)
    RoundImageView tvCalculator;//计算器的按钮

    @Bind(R.id.tv_click)
    RoundImageView tvClick;//投资的按钮

    private AlertDialog mAlertDialog;
    private String productId;
    private int percentage;
    private String enterprise_info;
    private String goods_describe;
    //TODO 如果用户未登录的话，需要将产品ID（pro_id），期限(goods_term),项目汇率(good_rate),
    //TODO 项目剩余的金额（money）,
    private int goods_term;//期限
    private int goods_rate;//项目汇率
    private int balanceCount;//剩余的产品的数量
    private String goods_title;//产品的名字
    private String goods_price;//产品的价格
    private String money;//项目剩余的金额


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_detail);
        ButterKnife.bind(this);
        getData();
        initTopBar();
        initEvent();
    }

    private void getData() {
        //productId  为String的类型
        productId = getIntent().getStringExtra(AppConstants.ParamDefaultValue.ID);
        //进度的类型为 int类型
        percentage = Integer.parseInt(getIntent().getStringExtra(AppConstants.ParamDefaultValue.PERCENTAGE));
        //产品名字
        goods_title = getIntent().getStringExtra(AppConstants.ParamDefaultValue.GOODS_TITLE);
    }

    private void initTopBar() {
        //对头部的TopBar左侧按钮的操作
        topBar.setButtonVisable(0, true);
        topBar.setButtonVisable(1, false);
        if (goods_title != null) {
            topBar.setTitleText(goods_title);
        } else {
            topBar.setTitleText("超有利");
        }

        topBar.setOnTopbarClickListener(new TopBar.topbarClickListener() {
            @Override
            public void leftClick() {
                ProjectDetailActivity.this.finish();
            }

            @Override
            public void rightClick() {

            }
        });

    }


    private void initEvent() {

        if (productId != null) {
            OkHttpUtils.post()
                    .url(AppConstants.RequestPath.CPGOODSDETAIL)
                    .addParams(AppConstants.ParamDefaultValue.ID, productId)
                    .build().execute(new StringCallback() {
                @Override
                public void onError(Request request, Exception e) {
                }

                @Override
                public void onResponse(String response) {
                    try {
                        JSONObject object = new JSONObject(response);
                        if (object.getString("result").equals("1")) {
                            JSONObject result_msg = object.getJSONObject("result_msg");

                            goods_term = result_msg.getInt("goods_term");
                            int count = result_msg.getInt("count");

                            goods_title = result_msg.getString("goods_title");

                            goods_rate = result_msg.getInt("goods_rate");
                            long goods_total_amount = result_msg.getLong("goods_total_amount");
                            String repay_date = result_msg.getString("repay_date");


                            balanceCount = result_msg.getInt("balanceCount");//产品数量
                            goods_price = result_msg.getString("goods_price");//产品的价格


                            String end_date = result_msg.getString("end_date");


                            enterprise_info = result_msg.getString("enterprise_info");
                            String category_name = result_msg.getString("category_name");

                            money = result_msg.getString("money");//项目剩余的金额
                            String type_name = result_msg.getString("type_name");
                            goods_describe = result_msg.getString("goods_describe");
                            int goods_count = result_msg.getInt("goods_count");

                            //TODO  判断逻辑  判断项目收益率和项目期限
                            if (goods_rate > 0 && goods_term > 0) {
                                tvNumDeadline.setText(goods_term + "天");//项目期限
                                tvNumAnnualEarnings.setText(goods_rate + "");//年化收益率
                            }

                            //TODO 对投资总额进行单位的转换
                            if (goods_total_amount > 10000) {
                                tvNumScale.setText(goods_total_amount / 10000 + "万");//投资总额
                            } else {
                                tvNumScale.setText(goods_total_amount + "元");//投资总额
                            }
                            horizonProgressBar.setProgress(percentage);//水平进度条
                            tvNumRaised.setText(percentage + "%");//已经融资的进度
                            tvNumPortion.setText(goods_price + "元/份");//1500元起投

                            tvNumResidualShare.setText(balanceCount + "");//剩余的份数

                            tvRelatedProject.setText(category_name);//项目类型
                            tvNumInterestTime.setText(end_date);//起息时间
                            tvNumRepaymentMode.setText(type_name);//    还款方式
                            tvNumRepaymentDate.setText(repay_date);//还款日期
                        }
                    } catch (JSONException e) {


                    }

                }
            });
        }
        //判断，当投资的进度为100 时候，计算器的点击事件和投资按钮的点击事件是不可点击的
        if (percentage == 100) {
            //TODO 提醒用户，该产品已经投资满了，请选择其他产品
            T.showShortToast(ProjectDetailActivity.this, "该产品已经投资满了，请选择其他产品");
            tvCalculator.setClickable(false);
            tvClick.setClickable(false);
        }
    }

    public void InvestClick(View v) {
        //TODO 进行判断，用户是否登陆,如果登录跳转到投资界面，否则弹出一个吐司
        // TODO 判断用户已经登录状态，跳转到立即投资页面
        if (PreferencesStore.getInstance(getApplicationContext()).readBoolean(AppConstants.ParamDefaultValue.ISBINDSUCCESS, true)) {
            //TODO 如果用户是未登录状态，跳转弹出一个对话框
            T.showShortToast(getApplicationContext(), "请您先绑定银行卡");
        } else {
            Intent intent = new Intent(ProjectDetailActivity.this, InvestActivity.class);
            //TODO  需要把productId传递给投资的界面
            if (productId != null && goods_price != null && money != null) {
                intent.putExtra(AppConstants.ParamDefaultValue.ID, productId);//产品的ID
                intent.putExtra(AppConstants.ParamDefaultValue.GOOD_PRICE, goods_price);//产品的价格
                intent.putExtra(AppConstants.ParamDefaultValue.PRO_RATE, goods_rate);//项目汇率
                intent.putExtra(AppConstants.ParamDefaultValue.PRO_TERM, goods_term);//项目期限
                intent.putExtra(AppConstants.ParamDefaultValue.MONEY, money);//项目剩余的金额
            }
            startActivity(intent);
        }

    }

    //点击计算器的响应事件
    public void calculatorClick(View v) {
        mAlertDialog = new AlertDialog.Builder(ProjectDetailActivity.this).create();
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        RelativeLayout layout = (RelativeLayout) inflater.inflate(R.layout.dialog_calculator, null);
        layout.setBackgroundResource(R.mipmap.calculator_bg);
        mAlertDialog.setView(layout);
        mAlertDialog.show();
        Window window = mAlertDialog.getWindow();
        window.setContentView(R.layout.dialog_calculator);

        final EditText tvInputRMB = (EditText) mAlertDialog.findViewById(R.id.et_input_money);
        final TextView tvEarning = (TextView) mAlertDialog.findViewById(R.id.tv_num_prospective_earnings);
        final TextView tvMonth = (TextView) mAlertDialog.findViewById(R.id.tv_num_average_monthly);
        final AutofitTextView tvRemind = (AutofitTextView) mAlertDialog.findViewById(R.id.tv_remind);

        tvInputRMB.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                //预期收益  *nianhua/360*qixian/100)];
                //平均月息  *nianhua/12/100)];
                //所需要的参数    goods_rate: 8.4,和goods_term: 90,
                //String RMB=tvInputRMB.getText().toString();
                if (!tvInputRMB.getText().toString().equals("")) {
                    if (Long.parseLong(tvInputRMB.getText().toString()) <= Integer.MAX_VALUE) {
                        tvRemind.setVisibility(View.GONE);
                        String text1 = Integer.parseInt(tvInputRMB.getText().toString()) * goods_rate * goods_term / 36000 + "";
                        String text2 = Integer.parseInt(tvInputRMB.getText().toString()) * goods_rate / 1200 + "";
                        tvEarning.setText(text1);
                        tvMonth.setText(text2);
                    }else{
                        tvRemind.setVisibility(View.VISIBLE);
                        tvEarning.setText("");
                        tvMonth.setText("");
                    }
                }
            }
        });
        //取消按钮
        mAlertDialog.findViewById(R.id.tv_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAlertDialog.dismiss();
            }
        });

        //确定按钮
        mAlertDialog.findViewById(R.id.tv_ok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAlertDialog.dismiss();
            }
        });


    }

    //点击查看项目详情
    public void CheckDetails(View v) {
        Intent intent = new Intent(ProjectDetailActivity.this, CheckDetailsActivity.class);

        if (!TextUtils.isEmpty(enterprise_info) || !TextUtils.isEmpty(goods_describe)) {
            intent.putExtra(AppConstants.ParamDefaultValue.ENTERPRISE_INFO, enterprise_info);
            intent.putExtra(AppConstants.ParamDefaultValue.GOODS_DESCRIBE, goods_describe);
            intent.putExtra(AppConstants.ParamDefaultValue.GOODS_TITLE,goods_title);
        }

        startActivity(intent);


    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
