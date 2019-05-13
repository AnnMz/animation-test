package com.example.mazunina.animationstest;

import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.AnticipateOvershootInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.OvershootInterpolator;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class HorizontalMovingAnimationFragment extends Fragment {
    private float containerWidth;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (container != null) {
            containerWidth = container.getMeasuredWidth();
        }
        return inflater.inflate(R.layout.layout_horizontal_moving, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        view.findViewById(R.id.text_view1).setOnClickListener(getOnClickListener(new AccelerateInterpolator()));
        view.findViewById(R.id.text_view2).setOnClickListener(getOnClickListener(new DecelerateInterpolator()));
        view.findViewById(R.id.text_view3).setOnClickListener(getOnClickListener(new AccelerateDecelerateInterpolator()));
        view.findViewById(R.id.text_view4).setOnClickListener(getOnClickListener(new AnticipateInterpolator()));
        view.findViewById(R.id.text_view5).setOnClickListener(getOnClickListener(new OvershootInterpolator()));
        view.findViewById(R.id.text_view6).setOnClickListener(getOnClickListener(new AnticipateOvershootInterpolator()));
        view.findViewById(R.id.text_view7).setOnClickListener(getOnClickListener(new BounceInterpolator()));
    }

    private View.OnClickListener getOnClickListener(final TimeInterpolator interpolator) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ObjectAnimator animator;
                if (view.getX() < containerWidth / 2) {
                    animator = ObjectAnimator.ofFloat(view, "translationX",
                            containerWidth - getResources().getDimensionPixelSize(R.dimen.activity_horizontal_margin) * 2 - view.getMeasuredWidth());
                } else {
                    animator = ObjectAnimator.ofFloat(view, "translationX",
                            0);
                }
                animator.setDuration(800);
                animator.setInterpolator(interpolator);
                animator.start();
            }
        };
    }
}
