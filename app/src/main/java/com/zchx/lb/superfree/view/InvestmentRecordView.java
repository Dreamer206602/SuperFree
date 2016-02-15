package com.zchx.lb.superfree.view;

import com.zchx.lb.superfree.entity.Product;

import java.util.List;

/**
 * Created on 2016/1/25 16:14
 * Created by Author boobooL
 * 邮箱：boobooMX@163.com
 */
/*投资记录
 */
public interface InvestmentRecordView {
    void showInvestmentRecordSuccess(List<Product>mDatas);
    void showInvestmentRecordFail(String errorMsg);
}
