package com.sunzn.brand.sample;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.sunzn.brand.library.BrandView;

public class MainActivity extends AppCompatActivity {

    private BrandView circleOne, rectangleOne;
    private BrandView circleTwo, rectangleTwo;
    private BrandView circleThr, rectangleThr;
    private BrandView circleFou, rectangleFou;
    private BrandView circleFiv, rectangleFiv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CircleActionListener circleActionListener = new CircleActionListener();
        circleOne = findViewById(R.id.circle_one);
        circleTwo = findViewById(R.id.circle_two);
        circleThr = findViewById(R.id.circle_thr);
        circleFou = findViewById(R.id.circle_fou);
        circleFiv = findViewById(R.id.circle_fiv);
        circleOne.setOnClickListener(circleActionListener);
        circleTwo.setOnClickListener(circleActionListener);
        circleThr.setOnClickListener(circleActionListener);
        circleFou.setOnClickListener(circleActionListener);
        circleFiv.setOnClickListener(circleActionListener);

        RectangleActionListener rectangleActionListener = new RectangleActionListener();
        rectangleOne = findViewById(R.id.rectangle_one);
        rectangleTwo = findViewById(R.id.rectangle_two);
        rectangleThr = findViewById(R.id.rectangle_thr);
        rectangleFou = findViewById(R.id.rectangle_fou);
        rectangleFiv = findViewById(R.id.rectangle_fiv);
        rectangleOne.setOnClickListener(rectangleActionListener);
        rectangleTwo.setOnClickListener(rectangleActionListener);
        rectangleThr.setOnClickListener(rectangleActionListener);
        rectangleFou.setOnClickListener(rectangleActionListener);
        rectangleFiv.setOnClickListener(rectangleActionListener);
    }

    private class CircleActionListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            int ID = v.getId();
            if (ID == R.id.circle_one) {
                circleOne.setCheck(true);
                circleTwo.setCheck(false);
                circleThr.setCheck(false);
                circleFou.setCheck(false);
                circleFiv.setCheck(false);
                return;
            }
            if (ID == R.id.circle_two) {
                circleOne.setCheck(false);
                circleTwo.setCheck(true);
                circleThr.setCheck(false);
                circleFou.setCheck(false);
                circleFiv.setCheck(false);
                return;
            }
            if (ID == R.id.circle_thr) {
                circleOne.setCheck(false);
                circleTwo.setCheck(false);
                circleThr.setCheck(true);
                circleFou.setCheck(false);
                circleFiv.setCheck(false);
                return;
            }
            if (ID == R.id.circle_fou) {
                circleOne.setCheck(false);
                circleTwo.setCheck(false);
                circleThr.setCheck(false);
                circleFou.setCheck(true);
                circleFiv.setCheck(false);
                return;
            }
            if (ID == R.id.circle_fiv) {
                circleOne.setCheck(false);
                circleTwo.setCheck(false);
                circleThr.setCheck(false);
                circleFou.setCheck(false);
                circleFiv.setCheck(true);
            }
        }
    }

    private class RectangleActionListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            int ID = v.getId();
            if (ID == R.id.rectangle_one) {
                rectangleOne.setCheck(true);
                rectangleTwo.setCheck(false);
                rectangleThr.setCheck(false);
                rectangleFou.setCheck(false);
                rectangleFiv.setCheck(false);
                return;
            }
            if (ID == R.id.rectangle_two) {
                rectangleOne.setCheck(false);
                rectangleTwo.setCheck(true);
                rectangleThr.setCheck(false);
                rectangleFou.setCheck(false);
                rectangleFiv.setCheck(false);
                return;
            }
            if (ID == R.id.rectangle_thr) {
                rectangleOne.setCheck(false);
                rectangleTwo.setCheck(false);
                rectangleThr.setCheck(true);
                rectangleFou.setCheck(false);
                rectangleFiv.setCheck(false);
                return;
            }
            if (ID == R.id.rectangle_fou) {
                rectangleOne.setCheck(false);
                rectangleTwo.setCheck(false);
                rectangleThr.setCheck(false);
                rectangleFou.setCheck(true);
                rectangleFiv.setCheck(false);
                return;
            }
            if (ID == R.id.rectangle_fiv) {
                rectangleOne.setCheck(false);
                rectangleTwo.setCheck(false);
                rectangleThr.setCheck(false);
                rectangleFou.setCheck(false);
                rectangleFiv.setCheck(true);
            }
        }
    }
}