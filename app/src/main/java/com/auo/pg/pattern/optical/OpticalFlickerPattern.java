package com.auo.pg.pattern.optical;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Bitmap.Config;
import android.util.DisplayMetrics;
import android.widget.ImageView;

public class OpticalFlickerPattern extends OpticalPattern {
    int[] mPixels;

    Bitmap mBitmap;

    @Override
    public void setPattern(Context context, ImageView v) {
        DisplayMetrics displaymetrics = new DisplayMetrics();
        ((Activity)context).getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);

        int height = displaymetrics.heightPixels;
        int width = displaymetrics.widthPixels;
        int[] pixels = new int[width * height];

        Paint paint = new Paint();

        Config config = Config.ARGB_8888;
        mBitmap = Bitmap.createBitmap(width, height, config);
        Canvas canvas = new Canvas(mBitmap);
        Bitmap tmp = Bitmap.createBitmap(width, height, config);

        for (int i = 0; i < width * height; i+=2) {
            pixels[i] = 0xFF800080;
            pixels[i + 1] = 0xFF008000;
        }
        tmp.setPixels(pixels, 0, width, 0, 0, width, height);
        canvas.drawBitmap(tmp, 0, 0, paint);

        tmp.recycle();
        tmp = null;

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
