package com.example.qiangxu.qsbk.domain;

/**
 * Created by QiangXu on 2015/12/29.
 */
public class LeftMenuTitle {

    private String title;
    private int flag;

    public LeftMenuTitle(String title, int flag) {
        this.title = title;
        this.flag = flag;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }
}
