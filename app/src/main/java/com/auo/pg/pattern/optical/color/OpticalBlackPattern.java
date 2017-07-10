package com.auo.pg.pattern.optical.color;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Bitmap.Config;
import android.util.DisplayMetrics;
import android.widget.ImageView;

public class OpticalBlackPattern extends OpticalColorPattern {
    @Override
    public void setPattern(Context context, ImageView v) {
        if ((mBitmap == null) || (mBitmap2 == null)) {
            create(context);
        }

        if (mIsFirst) {
            v.setImageBitmap(mBitmap);
        } else {
            v.setImageBitmap(mBitmap2);
        }
        mIsFirst = !mIsFirst;
    }

    private void create(Context context) {
        DisplayMetrics dm = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(dm);

        mHeight = dm.heightPixels;
        mWidth = dm.widthPixels;

        Paint paint = new Paint();
        paint.setColor(Color.BLACK);

        Config config = Config.ARGB_8888;
        mBitmap = Bitmap.createBitmap(mWidth, mHeight, config);
        Canvas canvas = new Canvas(mBitmap);
        canvas.drawPaint(paint);

        mBitmap2 = Bitmap.createBitmap(mWidth, mHeight, config);
        canvas = new Canvas(mBitmap2);
        paint.setARGB(255, mGrayLevel, mGrayLevel, mGrayLevel);
        canvas.drawPaint(paint);
    }
}
