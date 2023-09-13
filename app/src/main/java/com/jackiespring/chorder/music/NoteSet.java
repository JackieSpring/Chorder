package com.jackiespring.chorder.music;

import java.util.ArrayList;
import java.util.List;

public class NoteSet {


    protected ArrayList<Note> notes;
    protected int noteCount;
    protected Note highNote;
    protected Note lowNote;


    public NoteSet( Note [] _n ) {
        float Mp = 0;
        float mp = Float.MAX_VALUE;
        float freq;
        ArrayList<Note> _l = new ArrayList<>( _n.length );
        noteCount = _n.length;

        for ( Note note : _n ) {
            _l.add(note);
            freq = note.getFreq();

            if ( freq > Mp ) {
                Mp = freq;
                highNote = note;
            }

            if ( freq < mp ) {
                lowNote = note;
                mp = freq;
            }
        }

        notes = _l;
    }

    public NoteSet( List<Note> _l ) {
        this( (Note [])_l.toArray() );
    }

    public NoteSet( NoteSet ns ) {
        this(ns.notes);
    }

    public ArrayList<Note> getNotes() {
        return notes;
    }

    public Note getHighestNote() {
        return highNote;
    }

    public Note getLowestNote() {
        return lowNote;
    }

    public int getNoteCount() {
        return noteCount;
    }
}
