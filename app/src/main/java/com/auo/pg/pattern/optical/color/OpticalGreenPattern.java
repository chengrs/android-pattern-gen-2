package com.auo.pg.pattern.optical.color;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.DisplayMetrics;
import android.widget.ImageView;

public class OpticalGreenPattern extends OpticalColorPattern {
    @Override
    public void setPattern(Context context, ImageView v) {
        if (mBitmap == null) {
            create(context);
        }

        if (mIsFirst) {
            mIsFirst = false;
            v.setImageBitmap(mBitmap);
        } else {
            mIsFirst = true;
            v.setImageBitmap(mBitmap2);
        }
    }

    private void create(Context context) {
        DisplayMetrics dm = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(dm);

        mHeight = dm.heightPixels;
        mWidth = dm.widthPixels;

        Paint paint = new Paint();
        paint.setColor(Color.GREEN);

        Config config = Config.ARGB_8888;
        mBitmap = Bitmap.createBitmap(mWidth, mHeight, config);
        Canvas canvas = new Canvas(mBitmap);
        canvas.drawPaint(paint);

        mBitmap2 = Bitmap.createBitmap(mWidth, mHeight, config);
        canvas = new Canvas(mBitmap2);
        paint.setARGB(255, mGrayLevel, mGrayLevel, mGrayLevel);
        canvas.drawPaint(paint);

//        mTimerTask = new TimerTask() {
//            @Override
//            public void run() {
//                // send message to handler
//            }
//        };
    }
}
