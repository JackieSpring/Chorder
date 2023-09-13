package com.jackiespring.chorder.graphic.buttons;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;

import androidx.annotation.NonNull;

import com.jackiespring.chorder.R;
import com.jackiespring.chorder.music.instrument.TuningSet;

public class TuningSetButton extends StatefullButton {

    private final TuningSet tSet;
    private final ShapeDrawable shape;

    public TuningSetButton(@NonNull Context context, TuningSet set) {
        super(context);
        tSet = set;

        this.setBackgroundColor( getColor(R.color.white) );
        this.setTextColor( getColor(R.color.white) );
        this.setText( tSet.getName() );
        this.setTextSize(24);
        this.setBottom(12);

        shape = new ShapeDrawable();
        shape.setShape( new RectShape() );
        shape.getPaint().setColor( getColor( R.color.note_btn_selected ) );
        shape.getPaint().setStrokeWidth(15f);
        shape.getPaint().setStyle(Paint.Style.STROKE);
        this.setBackground( shape );
    }

    @Override
    public void setState(int state) {
        switch( state ) {
            case STATE_SELECTED:
                this.setBackgroundColor( getColor(R.color.note_btn_selected) );
                break;
            case STATE_UNSELECTED:
                this.setBackground( shape );
                break;
        }
    }

    public TuningSet getTuningSet() {
        return tSet;
    }
}
