package com.auo.pg.pattern.image;

import java.io.File;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

import com.auo.pg.pattern.Pattern;

public class ImagePattern extends Pattern {

    private final String mDirectory = Environment.getExternalStorageDirectory().getPath() + File.separator + "pg" + File.separator + "pic";
    protected String mImages[] = null;
    private int mIndex = 0;

    private Bitmap mBitmap;

    public int mInterval = 5 * 1000;

    public OnClickListener mOnClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            setBitmap((ImageView)v);
        }
    };

    public ImagePattern() {
        getImagePath();
    }

    @Override
    public void setPattern(Context context, ImageView v) {
        setBitmap(v);
    }

    @Override
    public void destroy() {
        if (mBitmap != null) {
            mBitmap.recycle();
            mBitmap = null;
        }
    }

    private void getImagePath() {
        File f = new File(mDirectory);
        if (f.exists()) {
            mImages = f.list();
        }

        for (int i = 0; i < mImages.length; i++) {
            mImages[i] = mDirectory.concat(File.separator).concat(mImages[i]);
        }
    }

    private void setBitmap(ImageView v) {
        Bitmap tBitmap = mBitmap;

        if (mIndex >= mImages.length) {
            mIndex = 0;
        }

        mBitmap = BitmapFactory.decodeFile(mImages[mIndex]);
        v.setImageBitmap(mBitmap);

        if (tBitmap != null) {
            tBitmap.recycle();
            tBitmap = null;
        }

        mIndex++;
    }
}
