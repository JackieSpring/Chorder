package com.jackiespring.chorder.graphic;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Space;

import androidx.annotation.Nullable;

import com.jackiespring.chorder.graphic.buttons.NoteButton;
import com.jackiespring.chorder.music.Note;
import com.jackiespring.chorder.music.NoteSet;


public class NoteSelector extends LinearLayout {

    private NoteSet notes;
    private NoteButton[] buttons;


    // COSTRUTTORI

    public NoteSelector(Context context) {
        super(context);
    }

    public NoteSelector(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setup();
    }

    public NoteSelector(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setup();
    }

    public NoteSelector(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        setup();
    }

    private void setup(){

        //this.setPadding(0,16,0,16);
    }

    // GETTER

    public NoteButton [] getButtons() {
        return buttons;
    }

    public NoteSet getNotes() {
        return notes;
    }

    public void setNotes(NoteSet ns) {
        int i = 0;

        this.notes = ns;
        buttons = new NoteButton[ ns.getNoteCount() ];

        for ( Note note : ns.getNotes() ) {
            NoteButton b = new NoteButton(this.getContext(), note);
            Space space = new Space( this.getContext() );
            LinearLayout.LayoutParams pam = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

            space.setMinimumHeight(8);

            this.addView(space);
            this.addView(b, pam);
            buttons[i++] = b;

        }
    }
}
