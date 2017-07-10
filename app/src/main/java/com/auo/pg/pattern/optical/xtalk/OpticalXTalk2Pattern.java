package com.auo.pg.pattern.optical.xtalk;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.DisplayMetrics;
import android.widget.ImageView;

import com.auo.pg.Color;

public class OpticalXTalk2Pattern extends OpticalXTalkPattern {
    Bitmap mBitmap;

    @Override
    public void setPattern(Context context, ImageView v) {
//        mInterval = 3 * 1000;

        DisplayMetrics displaymetrics = new DisplayMetrics();
        ((Activity)context).getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);

        mHeight = displaymetrics.heightPixels;
        mWidth = displaymetrics.widthPixels;

        int recHeight = mHeight / 4;
        int recWidth = mWidth / 4;

        Paint paint = new Paint();
        paint.setColor(Color.WHITE);

        Config config = Config.ARGB_8888;
        mBitmap = Bitmap.createBitmap(mWidth, mHeight, config);
        Canvas canvas = new Canvas(mBitmap);
        canvas.drawColor(Color.GRAY31);
        canvas.drawRect(recWidth, recHeight, recWidth * 3, recHeight * 3, paint);

        v.setImageBitmap(mBitmap);
    }

    @Override
    public void destroy() {
        if (mBitmap != null) {
            mBitmap.recycle();
            mBitmap = null;
        }
    }
}
