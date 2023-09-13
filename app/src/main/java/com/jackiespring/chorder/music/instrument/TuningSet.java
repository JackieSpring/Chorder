package com.jackiespring.chorder.music.instrument;

import com.jackiespring.chorder.music.Note;
import com.jackiespring.chorder.music.NoteSet;

import java.util.List;

public class TuningSet extends NoteSet {


    public static final TuningSet BASS_4_STANDARD = new TuningSet( "Bass 4 strings", new Note[] { Note.E1, Note.A1, Note.D2, Note.G2 } );
    public static final TuningSet BASS_4_DROP_D = new TuningSet( "Bass 4 drop d", new Note[] { Note.D1, Note.A1, Note.D2, Note.G2 } );
    public static final TuningSet BASS_5_STANDARD = new TuningSet( "Bass 5 strings",new Note[] { Note.B0, Note.E1, Note.A1, Note.D2, Note.G2 } );

    public static final TuningSet GUITAR_6_STANDARD = new TuningSet( "Guitar 6 strings",new Note[] { Note.E2, Note.A2, Note.D3, Note.G3, Note.B3, Note.E4 } );

    public static final TuningSet UKULELE_4_STANDARD = new TuningSet( "Ukulele 4 strings", new Note[]{ Note.G4, Note.C4, Note.E4, Note.A4 } );

    private final String name;

    public TuningSet( String n, List<Note> _l) {
        this(n, (Note [])_l.toArray());
    }
    public TuningSet(String n,NoteSet ns) {
        this(n, ns.getNotes());
    }
    public TuningSet( List<Note> _l) {
        this( (Note [])_l.toArray());
    }
    public TuningSet(NoteSet ns) {
        this( ns.getNotes());
    }
    public TuningSet( Note[] _n) {
        this("",_n);
    }
    public TuningSet( String n, Note[] _n) {
        super(_n);
        name = n;
    }

    public String getName() {
        return name;
    }
}
