package com.example.mazunina.animationstest;

import android.graphics.drawable.AnimatedVectorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class AnimatedVectorDrawableFragment extends Fragment {
    private static final String KEY_CHECKED = "checked";

    private ImageButton button;

    private AnimatedVectorDrawable plusToCheckmark;
    private AnimatedVectorDrawable checkmarkToPlus;

    private boolean checked;

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            AnimatedVectorDrawable drawable = checked ? checkmarkToPlus : plusToCheckmark;
            button.setBackground(drawable);
            drawable.start();
            checked = !checked;
        }
    };

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            checked = savedInstanceState.getBoolean(KEY_CHECKED);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.layout_animated_vector_drawable, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        plusToCheckmark = (AnimatedVectorDrawable) getContext().getDrawable(R.drawable.avd_plus_to_checkmark);
        checkmarkToPlus = (AnimatedVectorDrawable) getContext().getDrawable(R.drawable.avd_checkmark_to_plus);

        button = view.findViewById(R.id.plus_button);
        button.setBackground(checked ? checkmarkToPlus : plusToCheckmark);
        button.setOnClickListener(onClickListener);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(KEY_CHECKED, checked);
    }
}
