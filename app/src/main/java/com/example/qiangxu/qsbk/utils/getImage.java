package com.example.qiangxu.qsbk.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by QiangXu on 2015/12/29.
 */
public class getImage {

    public static String getImageURL(String image){
        String url = "http://pic.qiushibaike.com/system/pictures/%s/%s/%s/%s";
        Pattern pattern = Pattern.compile("(\\d+)\\d{4}");
        Matcher matcher = pattern.matcher(image);
        matcher.find();
        return String.format(url, matcher.group(1), matcher.group(), "medium", image);
    }

}
