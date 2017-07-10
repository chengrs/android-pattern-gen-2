package com.auo.pg.pattern.power;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.widget.ImageView;

import com.auo.pg.pattern.Pattern;

public class Power2Pattern extends Pattern {
    Bitmap mBitmap;

    @Override
    public void destroy() {
        if (mBitmap != null) {
            mBitmap.recycle();
            mBitmap = null;
        }
    }

    @Override
    public void setPattern(Context context, ImageView v) {
        mHeight = v.getHeight();
        mWidth = v.getWidth();
        int[] pixels = new int[mWidth * mHeight];

        Config config = Config.ARGB_8888;
        mBitmap = Bitmap.createBitmap(mWidth, mHeight, config);
        Canvas canvas = new Canvas(mBitmap);
        Bitmap tmp = Bitmap.createBitmap(mWidth, mHeight, config);

        for (int i = 0; i < mWidth * mHeight; i+=2) {
            pixels[i] = 0xff000000;
            pixels[i + 1] = 0xffffffff;
        }

        tmp.setPixels(pixels, 0, mWidth, 0, 0, mWidth, mHeight);

        canvas.drawBitmap(tmp, 0, 0, null);

        tmp.recycle();
        tmp = null;

        v.setImageBitmap(mBitmap);
    }
}
