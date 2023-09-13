package com.jackiespring.chorder.dsp;

import android.util.Log;

import java.util.concurrent.Callable;

import be.tarsos.dsp.AudioDispatcher;
import be.tarsos.dsp.AudioEvent;
import be.tarsos.dsp.AudioProcessor;
import be.tarsos.dsp.filters.HighPass;
import be.tarsos.dsp.filters.LowPassFS;
import be.tarsos.dsp.filters.LowPassSP;
import be.tarsos.dsp.io.android.AudioDispatcherFactory;
import be.tarsos.dsp.pitch.PitchDetectionHandler;
import be.tarsos.dsp.pitch.PitchDetectionResult;
import be.tarsos.dsp.pitch.PitchProcessor;

public class PitchDetector {

    public interface PitchDetectorCallback {
        void handlePitch( PitchDetectionResult result );
    }


    private final PitchDetectionHandler pdh = new PitchDetectionHandler() {
        @Override
        public void handlePitch(PitchDetectionResult result, AudioEvent e) {
            if (callback != null) callback.handlePitch(result);
        }
    };

    private int sampleRate;
    private int sampleCount;

    private PitchDetectorCallback callback;
    private AudioDispatcher dispatcher;
    private AudioProcessor pitchDetector;
    private Thread th;

    boolean flagIsRunning = false;


    public PitchDetector( int _sr, int sc ) {
        sampleRate = _sr;
        sampleCount = sc;
        pitchDetector = new PitchProcessor(PitchProcessor.PitchEstimationAlgorithm.DYNAMIC_WAVELET, sampleRate, sampleCount, pdh);
        //pitchDetector = new PitchProcessor(PitchProcessor.PitchEstimationAlgorithm.YIN, sampleRate, sampleCount, pdh);
    }

    public synchronized void start() {
        if ( flagIsRunning )
            throw new RuntimeException( "Tried to start PitchDetector already running!" );

        dispatcher = AudioDispatcherFactory.fromDefaultMicrophone(sampleRate,sampleCount,0);
        //dispatcher.addAudioProcessor( new LowPassFS( 120, sampleRate ));
        //dispatcher.addAudioProcessor( new HighPass( 30, sampleRate ));
        dispatcher.addAudioProcessor(pitchDetector);
        th = new Thread(dispatcher, "PitchDetector");
        th.start();
        flagIsRunning = true;
    }

    public synchronized void stop() {
        if ( !flagIsRunning )
            throw new RuntimeException( "Tryed to stop PitchDetector is inactive!" );

        dispatcher.stop();
        flagIsRunning = false;
    }

    public void setCallback(PitchDetectorCallback callback) {
        this.callback = callback;
    }

    public synchronized void setSampleRate(int sr ) {
        if ( sampleRate == sr )
            return;

        this.sampleRate = sr;
        pitchDetector = new PitchProcessor(PitchProcessor.PitchEstimationAlgorithm.DYNAMIC_WAVELET, sampleRate, sampleRate / 4, pdh);
    }
}
