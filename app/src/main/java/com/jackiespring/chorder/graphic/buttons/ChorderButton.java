package com.jackiespring.chorder.graphic.buttons;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;

public abstract class ChorderButton extends AppCompatButton {


    protected ChorderButton(@NonNull Context context) {
        super(context);
    }

    protected ChorderButton(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    protected ChorderButton(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    protected int getColor(int res ) {
        return getContext().getResources().getColor(res, null);
    }
}
