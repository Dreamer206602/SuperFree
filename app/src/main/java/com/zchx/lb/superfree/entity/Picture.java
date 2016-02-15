package com.zchx.lb.superfree.entity;

/**
 * Created on 2015/12/28 11:39
 * Created by Author boobooL
 * 邮箱：boobooMX@163.com
 */
public class Picture {

    private String title;
    private int imageId;

    public Picture()
    {
        super();
    }

    public Picture(String title, int imageId)
    {
        super();
        this.title = title;
        this.imageId = imageId;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public int getImageId()
    {
        return imageId;
    }

    public void setImageId(int imageId)
    {
        this.imageId = imageId;
    }


}
