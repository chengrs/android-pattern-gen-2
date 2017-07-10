package com.auo.pg;

import java.lang.ref.WeakReference;
import java.util.Timer;
import java.util.TimerTask;

import android.app.AlertDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.auo.pg.pattern.Pattern;
import com.auo.pg.pattern.image.ImagePattern;

public class ImageActivity extends NoTitleActivity {
    private final String TAG = "ImageActivity";

    private final int MODE_AUTO = 1;
    private final int MODE_TOUCH = 2;

    // constant values defined for handler to use
    private final static byte REFRESH_IMAGE = 0x01;

    private int mMode = MODE_TOUCH;

    private ImageView mView;

    private Pattern mPattern;

    private Timer mTimer = null;
    private TimerTask mTimerTask = null;

    private Handler mHandler;

    private OnClickListener mOnClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            mPattern.setPattern(ImageActivity.this, (ImageView)v);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.v(TAG, "onCreate()");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        mView = (ImageView) findViewById(R.id.image_pattern);
        mPattern = new ImagePattern();
        mPattern.setPattern(ImageActivity.this, mView);

        mView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mPattern.setPattern(ImageActivity.this, (ImageView)v);
            }
        });

        mView.setOnLongClickListener(new OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                showDialog();
                return true;
            }
        });

        mHandler = new PatternHandler(ImageActivity.this);
    }

    private void showDialog() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(ImageActivity.this);
        LayoutInflater factory = LayoutInflater.from(ImageActivity.this);

        View view = factory.inflate(R.layout.dialog_image, null);

        final Button mode = (Button) view.findViewById(R.id.btn_mode);
        Button ok = (Button) view.findViewById(R.id.ok);
        final EditText interval = (EditText) view.findViewById(R.id.interval);

        dialog.setView(view);
        final AlertDialog ad = dialog.create();

        if (mMode == MODE_AUTO) {
            mode.setText(R.string.str_touch_mode);
        } else {
            mode.setText(R.string.str_auto_mode);
        }

        mode.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mMode == MODE_AUTO) {
                    stopTimer();

                    mView.setOnClickListener(mOnClickListener);

                    mMode = MODE_TOUCH;
                    mode.setText(R.string.str_auto_mode);
                } else if (mMode == MODE_TOUCH) {
                    mView.setOnClickListener(null);
                    startTimer();

                    mMode = MODE_AUTO;
                    mode.setText(R.string.str_touch_mode);
                }
            }
        });

        ok.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mPattern.mInterval = Integer.parseInt(interval.getText().toString()) * 1000;
                ad.dismiss();
            }
        });

        interval.setText(String.valueOf(((ImagePattern)mPattern).mInterval / 1000));

        ad.show();
    }

    private void stopTimer() {
        if (mTimer != null) {
            mTimer.cancel();
            mTimer = null;
        }

        if (mTimerTask != null) {
            mTimerTask.cancel();
            mTimerTask = null;
        }
    }

    private void startTimer() {
        if (mTimer == null) {
            mTimer = new Timer();
        }

        if (mTimerTask == null) {
            mTimerTask = new TimerTask() {
                @Override
                public void run() {
                    Message message = mHandler.obtainMessage(REFRESH_IMAGE);
                    mHandler.sendMessage(message);
                }
            };
        }
        
        mTimer.schedule(mTimerTask, 0, mPattern.mInterval);
    }

    static class PatternHandler extends Handler {
        private final WeakReference<ImageActivity> mActivity;

        public PatternHandler(ImageActivity activity) {
            mActivity = new WeakReference<ImageActivity>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            ImageActivity activity = mActivity.get();

            switch (msg.what) {
            case REFRESH_IMAGE:
                activity.mPattern.setPattern(activity, activity.mView);
                break;
            }
        }
    }
}
