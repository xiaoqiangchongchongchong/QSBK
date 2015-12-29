package com.example.qiangxu.qsbk.views;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.example.qiangxu.qsbk.R;
import com.squareup.okhttp.Response;
import com.squareup.picasso.Transformation;

import java.io.InputStream;

/**
 * Created by QiangXu on 2015/12/29.
 */
public class VideoDraw implements Transformation {

    private Context context;

    public VideoDraw(Context context) {
        this.context = context;
    }

    @Override
    public Bitmap transform(Bitmap source) {
        Bitmap result = Bitmap.createBitmap(source.getWidth(), source.getHeight(), Bitmap.Config.ARGB_8888);
        //Paint paint = new Paint();
        Canvas canvas=new Canvas(result);
        Bitmap  bitmap = BitmapFactory.decodeResource(context.getResources(), R.mipmap.video_play_normal);
        canvas.drawBitmap(bitmap, source.getWidth()/2, source.getHeight()/2, null);
        source.recycle();
        return result;
    }

    @Override
    public String key() {
        return "video";
    }
}
