package com.example.snake;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btn_jouer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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