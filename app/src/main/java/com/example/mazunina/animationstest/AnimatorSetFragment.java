package com.example.mazunina.animationstest;

import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

public class AnimatorSetFragment extends Fragment {
    private static final String KEY_POINTS = "points";
    private static final String KEY_ROTATED = "rotated";

    private TextView pointsView;

    private float containerHeight;
    private boolean rotated;
    private int points;

    private View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(final View view) {
            ObjectAnimator movementFirstAnimator = ObjectAnimator.ofFloat(view, "translationY",
                    view.getMeasuredHeight() - containerHeight / 2);
            ObjectAnimator movementSecondAnimator = ObjectAnimator.ofFloat(view, "translationY",
                    0);
            final ValueAnimator colorAnimator = ValueAnimator.ofObject(new ArgbEvaluator(),
                    Color.BLUE, Color.YELLOW, Color.CYAN, Color.MAGENTA, Color.RED, ContextCompat.getColor(getContext(), R.color.androidIcon)
            );
            colorAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    ((ImageView) view).setColorFilter((int) colorAnimator.getAnimatedValue(), PorterDuff.Mode.SRC_IN);
                }
            });
            ObjectAnimator rotationAnimation = ObjectAnimator.ofFloat(view, "rotationY", rotated ? 360f : -360f).setDuration(800);

            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.play(movementFirstAnimator)
                    .with(colorAnimator)
                    .with(rotationAnimation)
                    .with(ObjectAnimator.ofFloat(view, "scaleX", 2f))
                    .with(ObjectAnimator.ofFloat(view, "scaleY", 2f))
                    .before(movementSecondAnimator)
                    .before(ObjectAnimator.ofFloat(view, "scaleX", 1f))
                    .before(ObjectAnimator.ofFloat(view, "scaleY", 1f));
            animatorSet.setDuration(1000);
            animatorSet.start();

            rotated = !rotated;
            points++;
            pointsView.setText(String.valueOf(points));
        }
    };

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            points = savedInstanceState.getInt(KEY_POINTS);
            rotated = savedInstanceState.getBoolean(KEY_ROTATED);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (container != null) {
            containerHeight = container.getMeasuredHeight();
        }
        return inflater.inflate(R.layout.layout_animator_set, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        pointsView = view.findViewById(R.id.points);
        pointsView.setText(String.valueOf(points));
        view.findViewById(R.id.image).setOnClickListener(listener);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(KEY_ROTATED, rotated);
        outState.putInt(KEY_POINTS, points);
    }
}

