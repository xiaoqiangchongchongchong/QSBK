package com.example.qiangxu.qsbk.views;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ScrollView;

/**
 * Created with IntelliJ IDEA.
 * User: vhly[FR]
 * Date: 15/12/25
 * Email: vhly@163.com
 */
public class MyScrollView extends ScrollView {

    public MyScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {

        int action = ev.getAction();

        switch (action){
            case MotionEvent.ACTION_MOVE:
                Log.d("MyScrollView", "dispatchTouchEvent move");
                break;
        }

        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {


        int action = ev.getAction();

        switch (action){
            case MotionEvent.ACTION_MOVE:
                Log.d("MyScrollView", "onInterceptTouchEvent move");
                break;
        }

        return super.onInterceptTouchEvent(ev);
    }
}
