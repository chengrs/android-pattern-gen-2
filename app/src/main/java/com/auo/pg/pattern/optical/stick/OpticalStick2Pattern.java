package com.auo.pg.pattern.optical.stick;

import java.util.TimerTask;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.DisplayMetrics;
import android.widget.ImageView;

import com.auo.pg.Color;

public class OpticalStick2Pattern extends OpticalStickPattern {

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

        drawBitmap();
        drawBitmap2();

        mTimerTask = new TimerTask() {
            @Override
            public void run() {
                // send message to handler
            }
        };
    }

    private void drawBitmap() {
        int edgeLength = 40;

        Config config = Config.ARGB_8888;
        mBitmap = Bitmap.createBitmap(mWidth, mHeight, config);
        Canvas canvas = new Canvas(mBitmap);
        canvas.drawColor(Color.BLACK);

        Paint paint = new Paint();
        paint.setColor(Color.WHITE);

        for (int i = 0; i < mHeight; i += 2 * edgeLength) {
            for (int j = 0; j < mWidth; j += 2 * edgeLength) {
                canvas.drawRect(j, i, j + edgeLength, i + edgeLength, paint);
            }
        }

        for (int i = edgeLength; i < mHeight; i += 2 * edgeLength) {
            for (int j = edgeLength; j < mWidth; j += 2 * edgeLength) {
                canvas.drawRect(j, i, j + edgeLength, i + edgeLength, paint);
            }
        }
    }

    private void drawBitmap2() {
        Config config = Config.ARGB_8888;
        mBitmap2 = Bitmap.createBitmap(mWidth, mHeight, config);
        Canvas canvas = new Canvas(mBitmap2);
        canvas.drawColor(Color.GRAY127);
    }
}
