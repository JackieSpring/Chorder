package com.jackiespring.chorder.music.instrument;

public class Instrument {

    public static final Instrument BASS_4_STRINGS = new Instrument( "Bass 4 strings", new TuningSet[] {
            TuningSet.BASS_4_STANDARD,
            TuningSet.BASS_4_DROP_D,
    } );
    public static final Instrument BASS_5_STRINGS = new Instrument("Bass 5 strings", new TuningSet[] {
            TuningSet.BASS_5_STANDARD,
    });

    public static final Instrument GUITAR_6_STRINGS =  new Instrument( "Guitar 6 strings", new TuningSet[] {
            TuningSet.GUITAR_6_STANDARD,
    } );

    public static final Instrument UKULELE_4_STRINGS  = new Instrument( "Ukulele 4 strings", new TuningSet[]{
            TuningSet.UKULELE_4_STANDARD,
    } );

    protected String name;
    protected TuningSet [] tuningSets;

    public Instrument( TuningSet [] _tunset ){
        this("", _tunset);
    }
    public Instrument(String _name, TuningSet [] _tunset) {
        name = _name;
        tuningSets = _tunset;
    }

    public String getName() {
        return name;
    }

    public TuningSet[] getTuningSets() {
        return tuningSets;
    }
}
