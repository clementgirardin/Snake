package com.example.snake;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class mainFragment extends Fragment {

    Button btn_jouer;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        // Récupération par id
        btn_jouer = view.findViewById(R.id.btn_jouer);

        // Lance l'activité scan_code_qr au clique du bouton
        btn_jouer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                }
        });
        return view;
    }
}