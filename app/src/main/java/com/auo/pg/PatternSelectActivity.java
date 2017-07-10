package com.auo.pg;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.auo.pg.pattern.optical.OpticalPattern.OpticalPatternType;

public class PatternSelectActivity extends NoTitleActivity {
    private final String TAG = "PatternSelectActivity";

//    public static final String PATTERN_TYPE = "pattern_type";
//
//    public final byte TYPE_COLOR = 0x01;
//    public final byte TYPE_GRAY = 0x02;
//    public final byte TYPE_XTALK = 0x03;
//    public final byte TYPE_FLICKER = 0x04;
//    public final byte TYPE_STICK = 0x05;

    private Button mColor;
    private Button mGray;
    private Button mXtalk;
    private Button mFlicker;
    private Button mStick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.v(TAG, "onCreate()");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pattern_select);

        mColor = (Button) findViewById(R.id.btn_color);
        mGray = (Button) findViewById(R.id.btn_gray);
        mXtalk = (Button) findViewById(R.id.btn_xtalk);
        mFlicker = (Button) findViewById(R.id.btn_flicker);
        mStick = (Button) findViewById(R.id.btn_stick);

        mColor.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PatternSelectActivity.this, OpticalActivity.class);
                intent.putExtra(OpticalPatternType.PATTERN_TYPE, OpticalPatternType.TYPE_COLOR);
                startActivity(intent);
            }
        });

        mGray.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PatternSelectActivity.this, OpticalActivity.class);
                intent.putExtra(OpticalPatternType.PATTERN_TYPE, OpticalPatternType.TYPE_GRAY);
                startActivity(intent);
            }
        });

        mXtalk.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PatternSelectActivity.this, OpticalActivity.class);
                intent.putExtra(OpticalPatternType.PATTERN_TYPE, OpticalPatternType.TYPE_XTALK);
                startActivity(intent);
            }
        });

        mFlicker.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PatternSelectActivity.this, OpticalActivity.class);
                intent.putExtra(OpticalPatternType.PATTERN_TYPE, OpticalPatternType.TYPE_FLICKER);
                startActivity(intent);
            }
        });

        mStick.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PatternSelectActivity.this, OpticalActivity.class);
                intent.putExtra(OpticalPatternType.PATTERN_TYPE, OpticalPatternType.TYPE_STICK);
                startActivity(intent);
            }
        });
    }

}
