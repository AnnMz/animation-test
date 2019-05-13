package com.example.mazunina.animationstest;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.animation.StateListAnimator;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class StateListAnimatorFragment extends Fragment {
    private TextView textView;

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            int cx = textView.getWidth() / 2;
            int cy = textView.getHeight() / 2;
            float finalRadius = (float) Math.hypot(cx, cy);

            Animator animator = ViewAnimationUtils.createCircularReveal(textView, cx, cy, 0f, finalRadius);
            animator.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    SystemClock.sleep(300);
                    textView.setVisibility(View.GONE);
                }
            });
            animator.start();
            textView.setVisibility(View.VISIBLE);
        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.layout_state_list_animator, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        ImageView imageView = view.findViewById(R.id.like_button);
        textView = view.findViewById(R.id.like_message);
        StateListAnimator stateListAnimator = AnimatorInflater.loadStateListAnimator(getContext(), R.xml.animate_like_press);
        imageView.setStateListAnimator(stateListAnimator);
        imageView.setClickable(true);
        imageView.setOnClickListener(onClickListener);
    }
}
