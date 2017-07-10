package com.auo.pg.pattern.optical.gray;

import android.content.Context;
import android.widget.ImageView;

import com.auo.pg.R;
import com.auo.pg.pattern.Pattern;

public class OpticalGray127Pattern extends Pattern {
    @Override
    public void setPattern(Context context, ImageView v) {
        v.setImageResource(R.drawable.gray127);
    }

    @Override
    public void destroy() {
        // do nothing
    }
}
