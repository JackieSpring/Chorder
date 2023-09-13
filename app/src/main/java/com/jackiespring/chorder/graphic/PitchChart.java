package com.jackiespring.chorder.graphic;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.Utils;
import com.jackiespring.chorder.R;
import com.jackiespring.chorder.music.Note;
import com.jackiespring.chorder.music.NoteSet;

import java.util.ArrayList;

public class PitchChart extends LineChart {

    private static final float DEFAULT_MAX_Y = 150;
    private static final float DEFAULT_MIN_Y = 0;

    private static final int UPPER_SEMITONE_RANGE = +3;
    private static final int LOWER_SEMITONE_RANGE = -3;

    private final float MAX_X = 30;
    private final float MIN_X = 0;

    private float MAX_Y;
    private float MIN_Y;

    private final float XEntriesCount = 30;

    private YAxis yax;
    private LimitLine lineLimit;

    private ArrayList<Entry> yValues    = new ArrayList<Entry>((int) XEntriesCount );
    private LineDataSet set1            = new LineDataSet( yValues, "Pitch");
    private ArrayList<ILineDataSet> dataSets = new ArrayList<>();
    private LineData data;
    private float limit = -1F;

    public PitchChart(Context context) {
        super(context);
        setup();
    }
    public PitchChart(Context context, AttributeSet attrs) {
        super(context, attrs);
        setup();
    }
    public PitchChart(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setup();
    }


    // CONSTRUCTOR
/*
    public PitchChart( LineChart c ) {
        this( c, DEFAULT_MAX_Y, DEFAULT_MIN_Y );
    }
    public PitchChart(LineChart c, NoteSet ns) {
        this( c, Note.nthSemitone(ns.getHighestNote().getFreq(), UPPER_SEMITONE_RANGE) , Note.nthSemitone(ns.getLowestNote().getFreq(), LOWER_SEMITONE_RANGE) );
    }
    public PitchChart( LineChart c, Note n ) {
        this( c, Note.nthSemitone(n.getFreq(), UPPER_SEMITONE_RANGE) , Note.nthSemitone(n.getFreq(), LOWER_SEMITONE_RANGE) );
    }
    public PitchChart( LineChart c, float My, float my ) {
        MAX_Y = My;
        MIN_Y = my;

        chart = c;

        chart.setDragEnabled(false);
        chart.setScaleEnabled(false);
        chart.setDrawMarkers(false);
        chart.setPinchZoom(false);
        chart.setHighlightPerTapEnabled(false);
        chart.setHighlightPerDragEnabled(false);
        chart.setDescription(null);
        chart.setDrawGridBackground(false);
        chart.setGridBackgroundColor( Color.WHITE );

        yax = chart.getAxisRight();

        setupYAxis();
        chart.getAxisLeft().setEnabled(false);
        if ( yValues.isEmpty() ) for ( int i = 0; i < XEntriesCount ; yValues.add( new Entry(i, 0) ) ,i++ );

        set1.setValues( yValues );
        set1.setFillAlpha(100);
        set1.setLabel(null);
        set1.setDrawCircles(false);
        set1.setDrawValues(false);
        set1.setLineWidth(2);
        set1.setColor(Color.valueOf( Color.WHITE).toArgb());
        set1.setAxisDependency( yax.getAxisDependency() );

        dataSets.add( set1 );

        data = new LineData(dataSets);

        chart.setData(data);
    }
*/
    // PRIVATE METHODS

    public void setup() {
        MIN_Y = DEFAULT_MIN_Y;
        MAX_Y = DEFAULT_MAX_Y;

        yax = this.getAxisRight();

        setupChart();
        setupYAxis();
        if ( yValues.isEmpty() ) for ( int i = 0; i < XEntriesCount ; yValues.add( new Entry(i, 0) ) ,i++ );

        set1.setValues( yValues );
        set1.setFillAlpha(100);
        set1.setLabel(null);
        set1.setDrawCircles(false);
        set1.setDrawValues(false);
        set1.setLineWidth(2);
        set1.setColor(Color.valueOf( Color.WHITE).toArgb());
        set1.setAxisDependency( yax.getAxisDependency() );

        dataSets.add( set1 );

        data = new LineData(dataSets);

        this.setData(data);
    }

    private void setupChart() {
        this.setDragEnabled(false);
        this.setScaleEnabled(false);
        this.setDrawMarkers(false);
        this.setPinchZoom(false);
        this.setHighlightPerTapEnabled(false);
        this.setHighlightPerDragEnabled(false);
        this.setDescription(null);
        this.setDrawGridBackground(false);
        this.setGridBackgroundColor( Color.WHITE );
        this.getAxisLeft().setEnabled(false);
    }

    private void setupYAxis() {

        if ( lineLimit != null && limit > MAX_Y || limit < MIN_Y )
            yax.removeLimitLine( lineLimit );

        yax.setAxisMaximum( MAX_Y );
        yax.setAxisMinimum( MIN_Y );
        yax.setTextColor(Color.WHITE);
        yax.setDrawLimitLinesBehindData(true);
        yax.enableGridDashedLine(10, 10, 0);

    }

    // PUBLIC METHODS

    public void append( float value ) {

        //if ( value > MAX_Y )
        //    value = MAX_Y;
        //else if ( value < MIN_Y )
        //    value = MIN_Y;

        float newVal = value;

        this.notifyDataSetChanged();
        this.invalidate();


        for ( int i =  yValues.size(); i > 0 ;  i-- ) {
            float tmp;
            Entry e = yValues.get( i - 1 );
            tmp = e.getY();
            e.setY( newVal );
            newVal = tmp;
        }

        set1.setValues( yValues );
        set1.setFillAlpha(100);

        if (!dataSets.isEmpty() ) dataSets.remove(0);
        dataSets.add( set1 );

        data = new LineData(dataSets);

        this.setData(data);
    }

    public void setLimit( Note n ) {
        setLimit( n.getFreq(), n.getSymbol() );
    }
    public void setLimit(float lim) {
        setLimit(lim, null);
    }
    public void setLimit(float lim, String label) {

        if ( limit == lim )
            return;

        if ( lim < MIN_Y || lim > MAX_Y )
            return;

        limit = lim;

        lineLimit = new LimitLine( lim );
        lineLimit.setLineColor( Color.valueOf( 255,165,0 ).toArgb() );
        lineLimit.setLineWidth(2);
        lineLimit.setEnabled(true);
        lineLimit.disableDashedLine();
        lineLimit.setLabel( label );
        //lineLimit.setTextColor( Color.valueOf( 255,165,0 ).toArgb() );
        lineLimit.setTextColor( R.color.pitch_limit_line );
        lineLimit.setLabelPosition( LimitLine.LimitLabelPosition.LEFT_TOP );


        this.invalidate();
        yax.removeAllLimitLines();
        yax.addLimitLine(lineLimit);
    }

    public void unsetLimit() {
        this.invalidate();
        yax.removeAllLimitLines();
        limit = -1;
        lineLimit = null;
    }


    public void setPitchRange( NoteSet ns ) {
        setPitchRange(
                Note.nthSemitone(ns.getHighestNote().getFreq(), UPPER_SEMITONE_RANGE) ,
                Note.nthSemitone(ns.getLowestNote().getFreq(), LOWER_SEMITONE_RANGE)
        );
    }
    public void setPitchRange( Note n ){
        setPitchRange(
                Note.nthSemitone(n.getFreq(), UPPER_SEMITONE_RANGE) ,
                Note.nthSemitone(n.getFreq(), LOWER_SEMITONE_RANGE)
        );
    }
    public void setPitchRange( float maxF, float minF ) {
        MAX_Y = maxF;
        MIN_Y = minF;
        this.invalidate();
        setupYAxis();
        this.setData( data );
    }
}
