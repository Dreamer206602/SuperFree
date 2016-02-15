package com.zchx.lb.superfree.view;

import java.util.List;

/**
 * Created on 2016/1/17 11:38
 * Created by Author boobooL
 * 邮箱：boobooMX@163.com
 */
public interface CarouselPictureView {

    //获得轮播图成功
    void getCarouselPictureSuccess(List<String> mData);
    //获得轮播图成功
    void getCarouselPicturePresenterFail(String CarouselPictureErrorMsg);
}
