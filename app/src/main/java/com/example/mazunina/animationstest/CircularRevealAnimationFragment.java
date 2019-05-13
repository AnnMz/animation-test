package com.example.mazunina.animationstest;

import android.animation.Animator;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;

import java.util.Random;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class CircularRevealAnimationFragment extends Fragment {
    private final Random random = new Random();

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            int cx = view.getWidth() / 2;
            int cy = view.getHeight() / 2;
            float finalRadius = (float) Math.hypot(cx, cy);

            Animator animator = ViewAnimationUtils.createCircularReveal(view, cx, cy, 0f, finalRadius);
            animator.start();

            int color = Color.argb(255, random.nextInt(256), random.nextInt(256), random.nextInt(256));
            view.getBackground().setColorFilter(color, PorterDuff.Mode.OVERLAY);
        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.layout_circular_reveal, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        view.findViewById(R.id.button1).setOnClickListener(onClickListener);
        view.findViewById(R.id.button2).setOnClickListener(onClickListener);
        view.findViewById(R.id.button3).setOnClickListener(onClickListener);
        view.findViewById(R.id.button4).setOnClickListener(onClickListener);
        view.findViewById(R.id.button5).setOnClickListener(onClickListener);
        view.findViewById(R.id.button6).setOnClickListener(onClickListener);
    }
}
