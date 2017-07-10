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

public class OpticalStick1Pattern extends OpticalStickPattern {

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

        int recHeight = mHeight / 4;
        int recWidth = mWidth / 4;

        Config config = Config.ARGB_8888;
        mBitmap = Bitmap.createBitmap(mWidth, mHeight, config);
        Canvas canvas = new Canvas(mBitmap);
        canvas.drawColor(Color.BLACK);

        Paint paint = new Paint();
        paint.setColor(Color.WHITE);

        // brute force :D
        canvas.drawRect(0, 0, recWidth, recHeight, paint);
        canvas.drawRect(recWidth *2, 0, recWidth * 3, recHeight, paint);
        canvas.drawRect(recWidth, recHeight, recWidth * 2, recHeight * 2, paint);
        canvas.drawRect(recWidth * 3, recHeight, recWidth * 4, recHeight * 2, paint);

        canvas.drawRect(0, recHeight * 2, recWidth, recHeight * 3, paint);
        canvas.drawRect(recWidth *2, recHeight * 2, recWidth * 3, recHeight * 3, paint);
        canvas.drawRect(recWidth, recHeight * 3, recWidth * 2, recHeight * 4, paint);
        canvas.drawRect(recWidth * 3, recHeight * 3, recWidth * 4, recHeight * 4, paint);

        mBitmap2 = Bitmap.createBitmap(mWidth, mHeight, config);
        canvas = new Canvas(mBitmap2);
        canvas.drawColor(Color.GRAY127);

        mTimerTask = new TimerTask() {
            @Override
            public void run() {
                // send message to handler
            }
        };
    }
}
