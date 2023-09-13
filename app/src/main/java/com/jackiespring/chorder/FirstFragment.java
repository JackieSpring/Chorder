package com.jackiespring.chorder;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.jackiespring.chorder.databinding.FragmentFirstBinding;
import com.jackiespring.chorder.dsp.PitchDetector;
import com.jackiespring.chorder.graphic.buttons.NoteButton;
import com.jackiespring.chorder.graphic.NoteSelector;
import com.jackiespring.chorder.graphic.PitchChart;
import com.jackiespring.chorder.music.Note;
import com.jackiespring.chorder.music.NoteSet;
import com.jackiespring.chorder.music.instrument.Instrument;
import com.jackiespring.chorder.music.instrument.TuningSet;
import com.jackiespring.chorder.service.IFCService;

import be.tarsos.dsp.AudioEvent;
import be.tarsos.dsp.pitch.PitchDetectionHandler;
import be.tarsos.dsp.pitch.PitchDetectionResult;

public class FirstFragment extends /*NavHost*/Fragment {


    private float esteem = 0;

    private final PitchDetectionHandler pdh = new PitchDetectionHandler() {
        @Override
        public void handlePitch(PitchDetectionResult result, AudioEvent e) {
            Log.e( "Result", "" + result.getPitch() +" "+ result.getProbability() );
            if ( ! result.isPitched() )
                return;


            mainAct.runOnUiThread( () -> {
                float pitchInHz = result.getPitch();

                float a = 0.3F;
                esteem = ( a ) * pitchInHz - ( 1 - a ) * esteem;
                pitchInHz = esteem;

                final String pitchString = String.format("%3.1f", pitchInHz);

                viewPitchValue.setText( pitchString );
                pc.append( pitchInHz );

                Log.e("Result", "" + pitchString +" "+ result.getProbability());
            });
        }
    };

    private TextView viewPitchValue;
    private TextView viewNote;
    private Button btnMenu;
    private Menu menuInstruments;
    private NoteSelector noteContainer;
    private PitchChart pc ;

    private Note activeNote = null;
    private NoteButton activeNoteButton = null;
    private NoteSet activeNoteSet = null;
    private Instrument activeInstrument = null;


    private Activity mainAct;
    private FragmentFirstBinding binding;
    private IFCService ifc;

    private int sampleRate = 22050;
    private int sampleCount = sampleRate / 8;
    private PitchDetector pDct;


    private boolean flagDetecting = false;
    private boolean flagNoteSelected = false;

    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState
    ) {
        mainAct = getActivity();
        binding = FragmentFirstBinding.inflate(inflater, container, false);
        ifc = IFCService.getInstance();


        pDct = new PitchDetector( sampleRate, sampleCount );
        pDct.setCallback( ( PitchDetectionResult result ) -> {
            Log.e( "Result", "" + result.getPitch() +" "+ result.getProbability() );
            if ( ! result.isPitched() )
                return;


            mainAct.runOnUiThread( () -> {
                final float pitchInHz = result.getPitch();
                final String pitchString = String.format("%3.1f", pitchInHz  );

                viewPitchValue.setText( pitchString );
                pc.append( pitchInHz );

                Log.e("Result", "" + pitchString +" "+ result.getProbability());
            });
        } );


        viewPitchValue = binding.pitchValueView;
        viewNote = binding.noteView;
        noteContainer = binding.containerNoteButtons;
        btnMenu = binding.buttonMenu;

        //activeNoteSet = TuningSet.BASS_4_STANDARD;
        //activeNoteSet = TuningSet.BASS_5_STANDARD;
        activeNoteSet = TuningSet.GUITAR_6_STANDARD;

        if ( ifc.size() > 0 ) {
            try {
                activeNoteSet = (TuningSet) ifc.getMsg().getData();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        pc = (PitchChart) binding.linechart;
        pc.setPitchRange( activeNoteSet );


        return binding.getRoot();

    }



    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        Log.e("STATE", "CREATED");
        super.onViewCreated(view, savedInstanceState);

        noteContainer.setNotes( activeNoteSet );

        String tuneName = "";
        if ( activeNoteSet.getClass().equals( TuningSet.class ) )
            tuneName = ((TuningSet) activeNoteSet).getName();
        else if ( activeInstrument != null )
            tuneName = activeInstrument.getName();
        else
            tuneName = "Custom";

        binding.instrumentView.setText( tuneName );

        for (NoteButton btn : noteContainer.getButtons() ) {
            Note note = btn.getNote();

            btn.setOnClickListener( ( View v ) -> {
                mainAct.runOnUiThread( () -> {

                    if ( flagNoteSelected ) {
                        pc.unsetLimit();
                        activeNoteButton.setState( NoteButton.STATE_UNSELECTED );

                        // Se clicca sul bottone attivo, disattiva
                        if ( activeNote.getCode() == note.getCode() ) {

                            pc.setPitchRange( activeNoteSet );
                            viewNote.setText( R.string.view_note_unselected );

                            activeNote = null;
                            flagNoteSelected = false;
                            return;
                        }
                    }
                    String noteStr = String.format( "%s : %3.2f Hz", note.getSymbol(), note.getFreq() );

                    btn.setState( NoteButton.STATE_SELECTED );
                    viewNote.setText( noteStr );
                    pc.setPitchRange( note );
                    pc.setLimit( note );

                    activeNote = note;
                    activeNoteButton = btn;
                    flagNoteSelected = true;

                });
            } );
        }


        btnMenu.setOnClickListener( (View v) -> {
            NavController c = Navigation.findNavController( v );
            c.navigate( R.id.action_firstFragment2_to_fragTunesMenu );
        } );

        runPitchDetector();

    }

    private synchronized void runPitchDetector( ) {
        if ( flagDetecting )
            return;
        pDct.start();
        flagDetecting = true;
    }
    private synchronized void stopPitchDetector() {
        if ( ! flagDetecting )
            return;
        pDct.stop();
        flagDetecting = false;
    }


    @Override
    public void onDestroyView() {
        Log.e("STATE", "DESTROY");
        super.onDestroyView();
        stopPitchDetector();
        binding = null;
    }

    @Override
    public void onPause() {
        Log.e("STATE", "PAUSE");
        super.onPause();
        stopPitchDetector();
    }

    @Override
    public void onResume() {
        Log.e("STATE", "RESUME");
        super.onResume();
        runPitchDetector();
    }

}




