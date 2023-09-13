package com.jackiespring.chorder.music;

public class Note {

    public static final Note A0 = new Note("A0", 21, 27.5F);
    public static final Note A1 = new Note("A1", 33, 55);
    public static final Note A2 = new Note("A2", 45, 110);
    public static final Note A3 = new Note("A3", 57, 220);
    public static final Note A4 = new Note("A4", 69, 440);
    public static final Note A5 = new Note("A5", 81, 880);

    public static final Note Bb0 = new Note("Bb0", 22, 29.135F);
    public static final Note Bb1 = new Note("Bb1", 34, 58.27F);
    public static final Note Bb2 = new Note("Bb2", 46, 116.54F);
    public static final Note Bb3 = new Note("Bb3", 58, 223.08F);
    public static final Note Bb4 = new Note("Bb4", 70, 466.16F);
    public static final Note Bb5 = new Note("Bb5", 82, 932.32F);

    public static final Note B0 = new Note("B0", 23, 30.868F);
    public static final Note B1 = new Note("B1", 35, 61.735F);
    public static final Note B2 = new Note("B2", 47, 123.471F);
    public static final Note B3 = new Note("B3", 59, 246.942F);
    public static final Note B4 = new Note("B4", 71, 493.883F);
    public static final Note B5 = new Note("B5", 83, 987.767F);

    public static final Note C0 = new Note("C0", 12, 16.351F);
    public static final Note C1 = new Note("C1", 24, 32.703F);
    public static final Note C2 = new Note("C2", 36, 65.406F);
    public static final Note C3 = new Note("C3", 48, 130.812F);
    public static final Note C4 = new Note("C4", 60, 261.624F);
    public static final Note C5 = new Note("C5", 72, 523.248F);
    public static final Note C6 = new Note("C6", 84, 1046.496F);

    public static final Note D0 = new Note("D0", 14, 18.354F);
    public static final Note D1 = new Note("D1", 26, 36.708F);
    public static final Note D2 = new Note("D2", 38, 73.416F);
    public static final Note D3 = new Note("D3", 50, 146.832F);
    public static final Note D4 = new Note("D4", 62, 293.664F);
    public static final Note D5 = new Note("D5", 74, 587.328F);
    public static final Note D6 = new Note("D6", 86, 1174.656F);

    public static final Note E0 = new Note("E0", 16, 20.601F);
    public static final Note E1 = new Note("E1", 28, 41.202F);
    public static final Note E2 = new Note("E2", 40, 82.404F);
    public static final Note E3 = new Note("E3", 52, 164.808F);
    public static final Note E4 = new Note("E4", 64, 329.616F);
    public static final Note E5 = new Note("E5", 76, 659.232F);
    public static final Note E6 = new Note("E6", 88, 1318.464F);


    public static final Note G0 = new Note("G0", 19, 24.499F);
    public static final Note G1 = new Note("G1", 31, 48.998F);
    public static final Note G2 = new Note("G2", 43, 97.996F);
    public static final Note G3 = new Note("G3", 55, 195.992F);
    public static final Note G4 = new Note("G4", 67, 391.984F);
    public static final Note G5 = new Note("G5", 79, 783.968F);
    public static final Note G6 = new Note("G6", 91, 1567.936F);

    private static final float a_fact = 1.0594F; // 2^1/12


    // STATIC METHODS

    public static float prevSemitone( float freq ) {
        return (float) (freq * Math.pow( a_fact, -1 ));
    }
    public static float prevSemitone( Note n ) {
        return prevSemitone( n.freq );
    }

    public static float nextSemitone( float freq ) {
        return (float) (freq * Math.pow( a_fact, -1 ));
    }
    public static float nextSemitone( Note n ) {
        return nextSemitone( n.freq );
    }

    public static float nthSemitone( float freq, float n ) {
        return (float) (freq * Math.pow( a_fact, n ));
    }
    public static float nthSemitone( Note note, float n ) {
        return nthSemitone( note.freq, n );
    }



    // INSTANCE

    private final String symbol ;
    private final int code ;
    private final float freq;

    public Note( String _s, int _c, float _f ){
        symbol = _s;
        code = _c;
        freq = _f;
    }

    public float getFreq() {
        return freq;
    }

    public int getCode() {
        return code;
    }

    public String getSymbol() {
        return symbol;
    }
}
