package com.auo.pg.pattern.power;

import android.content.Context;
import android.widget.ImageView;

import com.auo.pg.R;
import com.auo.pg.pattern.Pattern;

public class PowerWhitePattern extends Pattern {
    @Override
    public void setPattern(Context context, ImageView v) {
        v.setImageResource(R.drawable.white);
    }

    @Override
    public void destroy() {
        // do nothing
    }
}
