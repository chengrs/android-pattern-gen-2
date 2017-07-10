package com.auo.pg.pattern;

import android.content.Context;
import android.widget.ImageView;

public abstract class Pattern {
    protected Context mContext;

    protected int mHeight;
    protected int mWidth;

    public int mInterval;

    public abstract void setPattern(Context context, ImageView v);
    public abstract void destroy();
}
