package com.jackiespring.chorder.graphic.buttons;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public abstract class StatefullButton extends ChorderButton {

    public static final int STATE_SELECTED = 0;
    public static final int STATE_UNSELECTED = 1;

    protected StatefullButton(@NonNull Context context) {
        super(context);
    }

    protected StatefullButton(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    protected StatefullButton(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public abstract void setState( int state );
}
