package com.example.snake;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btn_jouer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Met l'aaplication en pleine écran (comme en F11)
        // (Cache la bare de navigation, la barre d'informations en haut de l'écran)
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE | View.SYSTEM_UI_FLAG_FULLSCREEN);
        // Coloration de l'action bar en turquoise
        ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.turquoise)));



        // Récupération par id
        btn_jouer = findViewById(R.id.btn_jouer);
        // Lance l'activité snakeActivity au clique du bouton
        btn_jouer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Lance l'activité snakeActivity
                startActivity(new Intent(getApplicationContext(),snakeActivity.class));
            }
        });
    }
}