package com.example.mazunina.animationstest;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.dynamicanimation.animation.DynamicAnimation;
import androidx.dynamicanimation.animation.SpringAnimation;
import androidx.dynamicanimation.animation.SpringForce;
import androidx.fragment.app.Fragment;

public class SpringAnimationFragment extends Fragment {
    private ImageView imageViewFirst;
    private ImageView imageViewSecond;
    private ImageView imageViewThird;
    private TextView dampingRatioValueView;
    private TextView stiffnessValueView;

    private float dampingRatioValue;
    private float stiffnessValue;

    private View.OnTouchListener onTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent event) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    break;
                case MotionEvent.ACTION_MOVE:
                    view.setX(event.getRawX());
                    view.setY(event.getRawY());
                    animate(event.getRawX() - view.getX(), event.getRawY() - view.getY());
                    break;
                default:
                    return false;
            }
            return true;
        }
    };
    private SeekBar.OnSeekBarChangeListener dampingRatioBarChangeListener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
            switch (i) {
                case 0:
                    dampingRatioValue = SpringForce.DAMPING_RATIO_NO_BOUNCY;
                    dampingRatioValueView.setText(getString(R.string.damping_ratio_template, "NO"));
                    break;
                case 1:
                    dampingRatioValue = SpringForce.DAMPING_RATIO_LOW_BOUNCY;
                    dampingRatioValueView.setText(getString(R.string.damping_ratio_template, "LOW"));
                    break;
                case 2:
                    dampingRatioValue = SpringForce.DAMPING_RATIO_MEDIUM_BOUNCY;
                    dampingRatioValueView.setText(getString(R.string.damping_ratio_template, "MEDIUM"));
                    break;
                case 3:
                    dampingRatioValue = SpringForce.DAMPING_RATIO_HIGH_BOUNCY;
                    dampingRatioValueView.setText(getString(R.string.damping_ratio_template, "HIGH"));
                    break;
            }
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
            // no-op
        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            // no-op
        }
    };
    private SeekBar.OnSeekBarChangeListener stiffnessBarChangeListener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
            switch (i) {
                case 0:
                    stiffnessValue = SpringForce.STIFFNESS_VERY_LOW;
                    stiffnessValueView.setText(getString(R.string.stiffness_template, "VERY LOW"));
                    break;
                case 1:
                    stiffnessValue = SpringForce.STIFFNESS_LOW;
                    stiffnessValueView.setText(getString(R.string.stiffness_template, "LOW"));
                    break;
                case 2:
                    stiffnessValue = SpringForce.STIFFNESS_MEDIUM;
                    stiffnessValueView.setText(getString(R.string.stiffness_template, "MEDIUM"));
                    break;
                case 3:
                    stiffnessValue = SpringForce.STIFFNESS_HIGH;
                    stiffnessValueView.setText(getString(R.string.stiffness_template, "HIGH"));
                    break;
            }
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
            // no-op
        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            // no-op
        }
    };

    private void animate(float x, float y) {
        final SpringAnimation springAnimFirstX = new SpringAnimation(imageViewFirst, DynamicAnimation.TRANSLATION_X, x);
        springAnimFirstX.getSpring().setDampingRatio(dampingRatioValue).setStiffness(stiffnessValue);
        final SpringAnimation springAnimFirstY = new SpringAnimation(imageViewFirst, DynamicAnimation.TRANSLATION_Y, y);
        springAnimFirstY.getSpring().setDampingRatio(dampingRatioValue).setStiffness(stiffnessValue);
        final SpringAnimation springAnimSecondX = new SpringAnimation(imageViewSecond, DynamicAnimation.TRANSLATION_X, x).setStartVelocity(100);
        springAnimSecondX.getSpring().setDampingRatio(dampingRatioValue).setStiffness(stiffnessValue);
        final SpringAnimation springAnimSecondY = new SpringAnimation(imageViewSecond, DynamicAnimation.TRANSLATION_Y, y).setStartVelocity(100);
        springAnimSecondY.getSpring().setDampingRatio(dampingRatioValue).setStiffness(stiffnessValue);
        final SpringAnimation springAnimThirdX = new SpringAnimation(imageViewThird, DynamicAnimation.TRANSLATION_X, x).setStartVelocity(100);
        springAnimThirdX.getSpring().setDampingRatio(dampingRatioValue).setStiffness(stiffnessValue);
        final SpringAnimation springAnimThirdY = new SpringAnimation(imageViewThird, DynamicAnimation.TRANSLATION_Y, y).setStartVelocity(100);
        springAnimThirdY.getSpring().setDampingRatio(dampingRatioValue).setStiffness(stiffnessValue);
        springAnimFirstX.addUpdateListener(new DynamicAnimation.OnAnimationUpdateListener() {
            @Override
            public void onAnimationUpdate(DynamicAnimation dynamicAnimation, float value, float velocity) {
                springAnimSecondX.animateToFinalPosition(value);
            }
        });
        springAnimSecondX.addUpdateListener(new DynamicAnimation.OnAnimationUpdateListener() {
            @Override
            public void onAnimationUpdate(DynamicAnimation dynamicAnimation, float value, float velocity) {
                springAnimThirdX.animateToFinalPosition(value);
            }
        });
        springAnimFirstY.addUpdateListener(new DynamicAnimation.OnAnimationUpdateListener() {
            @Override
            public void onAnimationUpdate(DynamicAnimation dynamicAnimation, float value, float velocity) {
                springAnimSecondY.animateToFinalPosition(value);
            }
        });
        springAnimSecondY.addUpdateListener(new DynamicAnimation.OnAnimationUpdateListener() {
            @Override
            public void onAnimationUpdate(DynamicAnimation dynamicAnimation, float value, float velocity) {
                springAnimThirdY.animateToFinalPosition(value);
            }
        });
        springAnimFirstX.start();
        springAnimFirstY.start();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.layout_spring, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        imageViewFirst = view.findViewById(R.id.image1);
        imageViewSecond = view.findViewById(R.id.image2);
        imageViewThird = view.findViewById(R.id.image3);
        imageViewFirst.setOnTouchListener(onTouchListener);

        dampingRatioValueView = view.findViewById(R.id.damping_ratio_value);
        dampingRatioValueView.setText(getString(R.string.damping_ratio_template, "MEDIUM"));
        dampingRatioValue = SpringForce.DAMPING_RATIO_MEDIUM_BOUNCY;
        SeekBar dampingRatioBar = view.findViewById(R.id.damping_ratio_bar);
        dampingRatioBar.setOnSeekBarChangeListener(dampingRatioBarChangeListener);

        stiffnessValueView = view.findViewById(R.id.stiffness_value);
        stiffnessValueView.setText(getString(R.string.stiffness_template, "MEDIUM"));
        stiffnessValue = SpringForce.STIFFNESS_MEDIUM;
        SeekBar stiffnessBar = view.findViewById(R.id.stiffness_bar);
        stiffnessBar.setOnSeekBarChangeListener(stiffnessBarChangeListener);
    }
}
