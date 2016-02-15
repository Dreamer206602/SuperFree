package com.zchx.lb.superfree.view;

import com.zchx.lb.superfree.entity.Transaction;

import java.util.List;

/**
 * Created on 2016/2/2 22:07
 * Created by Author boobooL
 * 邮箱：boobooMX@163.com
 */

/**
 * 交易明细的View
 */
public interface TransactionDetailView {

    void showTransactionDetailSuccess(List<Transaction>mDatas);
    void showTransactionDetailFail(String errorMsg);


}
