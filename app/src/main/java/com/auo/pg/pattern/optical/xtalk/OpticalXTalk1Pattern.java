package com.auo.pg.pattern.optical.xtalk;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.ImageView;

import com.auo.pg.Color;

public class OpticalXTalk1Pattern extends OpticalXTalkPattern {
    Bitmap mBitmap;

    @Override
    public void setPattern(Context context, ImageView v) {
        DisplayMetrics displaymetrics = new DisplayMetrics();
        ((Activity)context).getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);

        mHeight = displaymetrics.heightPixels;
        mWidth = displaymetrics.widthPixels;

        int recHeight = mHeight / 3;
        int recWidth = mWidth / 3;

        Paint paint = new Paint();
        paint.setColor(Color.WHITE);

        Config config = Config.ARGB_8888;
        mBitmap = Bitmap.createBitmap(mWidth, mHeight, config);
        Canvas canvas = new Canvas(mBitmap);
        Log.e("", "mGrayLevel = " + mGrayLevel);
        canvas.drawColor(mGrayLevel);
        canvas.drawRect(recWidth, recHeight, recWidth * 2, recHeight * 2, paint);

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
