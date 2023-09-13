package com.jackiespring.chorder.graphic.buttons;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.graphics.drawable.shapes.RoundRectShape;
import android.widget.Button;

import com.jackiespring.chorder.R;
import com.jackiespring.chorder.music.Note;

public class NoteButton extends StatefullButton {


    private final Note note;
    private final ShapeDrawable shape;


    public NoteButton(Context context, Note n) {
        super(context);
        note = n;
        this.setBackgroundColor( getColor(R.color.note_btn_unselected) );
        this.setTextColor( getColor(R.color.white) );
        this.setText( note.getSymbol() );
        this.setTextSize(24);
        this.setBottom(12);

        shape = new ShapeDrawable();
        shape.setShape( new RectShape() );
        shape.getPaint().setColor( getColor( R.color.note_btn_selected ) );
        shape.getPaint().setStrokeWidth(15f);
        shape.getPaint().setStyle(Paint.Style.STROKE);
        this.setBackground( shape );
    }

    public Note getNote() {
        return note;
    }


    public void setState( int state ) {
        switch( state ) {
            case STATE_SELECTED:
                this.setBackgroundColor( getColor(R.color.note_btn_selected) );
                break;
            case STATE_UNSELECTED:
                this.setBackground( shape );
                //this.setBackgroundColor( getColor(R.color.note_btn_unselected) );
                break;
        }
    }

}
