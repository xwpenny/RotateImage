package com.example.rotateimage;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, View.OnTouchListener {

    ImageView iv1, iv2;
    RelativeLayout rl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iv1 = (ImageView) findViewById(R.id.iv1);
        iv2 = (ImageView) findViewById(R.id.iv2);
        rl = (RelativeLayout) findViewById(R.id.rl);
        iv1.setOnClickListener(this);
        iv2.setOnClickListener(this);
        rl.setOnTouchListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv1:
                setRotate(360, 5000);
                break;
            case R.id.iv2:
                setRotate(-360, 5000);
                break;
            default:
                break;
        }

    }

    public void setRotate(float rotate, int time) {
        ObjectAnimator anim = ObjectAnimator.ofFloat(rl, "rotation", mAngle, mAngle + rotate);
        anim.setDuration(time);
        anim.start();
        mAngle = (mAngle + rotate) % 360;
    }

    private float mAngle = 0;

    private float startX;
    private float startY;
    private float centerX;
    private float centerY;

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            startX = event.getRawX();
            startY = event.getRawY();
            return true;
        }
        if (centerX == 0) {
            int[] location = new int[2];
            rl.getLocationOnScreen(location);
            centerX = location[0] + rl.getWidth() / 2;
            centerY = location[1] + rl.getHeight() / 2;
        }
        float endX = event.getRawX();
        float endY = event.getRawY();
        float angle = RotateUtil.computeAngle(centerX, centerY, startX, startY, endX, endY);
        Log.e("xianwei", angle + "");
        if (RotateUtil.isClockWise(centerX, centerY, startX, startY, endX, endY)) {
            setRotate(angle, 1);
        } else {
            setRotate(-angle, 1);
        }
        startX = endX;
        startY = endY;
        return true;
    }


}
