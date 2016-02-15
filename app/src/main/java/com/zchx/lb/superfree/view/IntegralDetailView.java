package com.zchx.lb.superfree.view;

/**
 * Created on 2016/1/26 15:22
 * Created by Author boobooL
 * 邮箱：boobooMX@163.com
 */

import com.bumptech.glide.Glide;
import com.zchx.lb.superfree.R;

/**
 * 积分详情的View
 */
public interface IntegralDetailView {
//    String img_url = result_msg.getString("pro_img");
//    pro_store = result_msg.getString("pro_store");//库存
//    pro_integral = result_msg.getString("pro_integral");//积分
//    pro_content = result_msg.getString("pro_content");//产品详情
    void showIntegralDetailsSuccess(String pro_name,String pro_price,String imgUrl,String pro_store,String pro_integral,String pro_content);
    void showIntegralDetailsFail(String errorMsg);

    //立即兑换的View
    void showExchangeSuccess(String successMsg);
    void showExchangeFail(String errorMsg);


}
