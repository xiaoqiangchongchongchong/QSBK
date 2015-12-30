package com.example.qiangxu.qsbk.views;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.FrameLayout;

/**
 * Created by QiangXu on 2015/12/30.
 */
public class MyFrameLayout extends FrameLayout {

    public MyFrameLayout(Context context) {
        super(context);
    }

    public MyFrameLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int hMode = MeasureSpec.getMode(heightMeasureSpec);

        int hSize = MeasureSpec.getSize(heightMeasureSpec);

        switch (hMode){
            case MeasureSpec.UNSPECIFIED:
                Log.d("MyListView", "hMode UNSPECIFIED");
                break;
            case MeasureSpec.AT_MOST:
                break;
            case MeasureSpec.EXACTLY:
                break;
        }

        Log.d("MyListView", "hSize = " + hSize);

        // 因为 ScrollView + ListView 时，高度模式强制设置为 UNSPECIFIED，
        // ListView 只会计算一条的高度；
        // 将 height 的模式，强制设置为 AT_MOST 就会进行计算实际的高度了；
        // 考虑到ListView条目数量不确定，高度也不确定，height size 应该是
        // 是一个最大值；

        heightMeasureSpec = MeasureSpec.makeMeasureSpec(
                Integer.MAX_VALUE>>2,
                MeasureSpec.AT_MOST
        );

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

}
