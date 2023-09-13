package com.jackiespring.chorder;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;

import com.github.mikephil.charting.utils.Utils;

import androidx.appcompat.app.AppCompatActivity;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.jackiespring.chorder.databinding.ActivityMainBinding;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Utils.init(this);




        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.RECORD_AUDIO } , PackageManager.PERMISSION_GRANTED);

        if ( ActivityCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED ) {
            Log.e("PERMS", "MISSING PERMISSION!!");
            return ;
        }

        //List<Fragment> flist = new ArrayList<>( );
        //NavHostFragment nhc = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.firstFragment2);
        //NavController nc = nhc.getNavController();



    }


}