package com.example.qiangxu.qsbk.utils;

/**
 * Created by QiangXu on 2015/12/29.
 */
public class getIcon {

    public static String getIconURL(long id, String icon){
        String url = "http://pic.qiushibaike.com/system/avtnew/%s/%s/thumb/%s";
        return String.format(url, id / 10000, id, icon);

    }

}
