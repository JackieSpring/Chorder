package com.jackiespring.chorder;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Space;
import android.widget.TextView;

import com.jackiespring.chorder.databinding.FragmentTunesMenuBinding;
import com.jackiespring.chorder.graphic.buttons.TuningSetButton;
import com.jackiespring.chorder.music.instrument.Instrument;
import com.jackiespring.chorder.music.instrument.TuningSet;
import com.jackiespring.chorder.service.IFCService;

public class TunesMenuFragment extends Fragment {


    private MainActivity mainAct;
    private @NonNull FragmentTunesMenuBinding binding;
    private IFCService ifc;

    private LinearLayout menu;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mainAct = (MainActivity) getActivity();
        binding = FragmentTunesMenuBinding.inflate(inflater, container, false);
        ifc = IFCService.getInstance();
        menu = binding.instrumentsMenu;

        TuningSet [] tunings = new TuningSet[] {
                TuningSet.BASS_4_STANDARD,
                TuningSet.BASS_4_DROP_D,
                TuningSet.BASS_5_STANDARD,
                TuningSet.GUITAR_6_STANDARD,
        };

        Instrument [] instruments = new Instrument[] {
                Instrument.BASS_4_STRINGS,
                Instrument.BASS_5_STRINGS,
                Instrument.GUITAR_6_STRINGS,
                Instrument.UKULELE_4_STRINGS,
        };

        for ( Instrument inst : instruments ) {

            Space spaceName = new Space(mainAct);
            TextView viewName = new TextView( mainAct );
            LinearLayout.LayoutParams lPams = new LinearLayout.LayoutParams( ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT );

            viewName.setText( inst.getName() );
            viewName.setTextColor( getResources().getColor(R.color.white, null) );
            viewName.setAutoSizeTextTypeUniformWithConfiguration( 64, 128, 1, 0 );
            viewName.setMinHeight(64 + 32);
            //viewName.setHeight(128);

            spaceName.setMinimumHeight(64);

            menu.addView( spaceName );
            menu.addView( viewName, lPams );


            for ( TuningSet tSet : inst.getTuningSets() ) {
                Button tBtn = new TuningSetButton( mainAct, tSet );

                tBtn.setOnClickListener( ( View v ) -> {
                    try {
                        ifc.putMsg( new IFCService.IFCMessage( tSet, this ) );
                        NavController c = Navigation.findNavController( v );
                        c.navigate( R.id.action_fragTunesMenu_to_firstFragment2 );
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } );


                Space space = new Space(this.getContext());
                space.setMinimumHeight(16);
                menu.addView(space);
                menu.addView( tBtn );
            }
        }




        return binding.getRoot();
    }
}