package com.auo.pg.pattern.optical;

import java.util.TimerTask;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View.OnClickListener;
import android.widget.ImageView;

import com.auo.pg.pattern.Pattern;

public class OpticalPattern extends Pattern {

    public static interface OpticalPatternType {
        public static final String PATTERN_TYPE = "pattern_type";

        public final byte TYPE_DEFAULT = 0x00;
        public final byte TYPE_COLOR = 0x01;
        public final byte TYPE_GRAY = 0x02;
        public final byte TYPE_XTALK = 0x03;
        public final byte TYPE_FLICKER = 0x04;
        public final byte TYPE_STICK = 0x05;
    }

    protected byte mType = OpticalPatternType.TYPE_DEFAULT;
    protected static boolean mIsFirst = true;
    protected Bitmap mBitmap;
    protected Bitmap mBitmap2;

    protected TimerTask mTimerTask = null;
    protected int mLevel;

    public OnClickListener mOnclickListener;

    @Override
    public void setPattern(Context context, ImageView v) {
        
    }

    @Override
    public void destroy() {
        if (mBitmap != null) {
            mBitmap.recycle();
            mBitmap = null;
        }

        if (mBitmap2 != null) {
            mBitmap2.recycle();
            mBitmap2 = null;
        }
    }

//    public TimerTask getTimerTask() {
//        return new TimerTask() {
//            @Override
//            public void run() {
//                Message message = mHandler.obtainMessage(REFRESH_IMAGE);
//                mHandler.sendMessage(message);
//            }
//        };
//    }
}
