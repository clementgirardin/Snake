package com.example.snake;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

public class snakeActivity extends AppCompatActivity implements SensorEventListener {

    // Initialisations variables
    SensorManager sensorManager;
    Sensor Accelerometre;
    ImageView carre;



    Handler handler = new Handler();

    // 1:droite, 2:bas, 3:gauche, 4:haut
    int direction = 0;

    // Runnable qui met à jour la position du carré toutes les 5ms
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            // Récupération des délimitations de l'écran
            int largeurEcran = Resources.getSystem().getDisplayMetrics().widthPixels;
            int hauteurEcran = Resources.getSystem().getDisplayMetrics().heightPixels;

            // Récupère les dimensions du carré
            int largeurCarre = carre.getWidth();
            int hauteurCarre = carre.getHeight();

            // Récupère les nouvelles positions
            int newPosX = (int) (carre.getX());
            int newPosY = (int) (carre.getY());


            // Mise à jour de la position en fonction de la direction
            switch (direction) {
                // Case 0 sans code pour quel le carré reste immobile au départ du jeu
                case 0:
                    break;
                case 1:
                    newPosX += 5;
                    break;
                case 2:
                    newPosY += 5;
                    break;
                case 3:
                    newPosX -= 5;
                    break;
                case 4:
                    newPosY -= 5;
                    break;
                default:
                    break;
            }

            // Test pour que le carré ne sorte pas de l'écran
            // Tests pour la largeur
            if (newPosX < 0) {
                newPosX = 0;
            } else if (newPosX > largeurEcran - largeurCarre) {
                newPosX = (largeurEcran - largeurCarre);
            }
            // Tests pour la hauteur
            if (newPosY < 0) {
                newPosY = 0;
            } else if (newPosY > hauteurEcran - hauteurCarre) {
                newPosY = (hauteurEcran - hauteurCarre);
            }

            // Positionne le carré avec ses nouvelles positions x et y
            carre.setX(newPosX);
            carre.setY(newPosY);

            // Relance la mise à jour toutes les 5ms
            handler.postDelayed(this, 5);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snake);

        // Récupération par id
        carre = findViewById(R.id.carre);

        // Initialisation sensorManager pour gérer les capteurs
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        // Affectation du capteur "ACCELEROMER" à la variable Accelerometre
        Accelerometre = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        // Lance le déplacement du carré toutes les 5 milisecondes
        handler.postDelayed(runnable, 5);

        // Met l'aaplication en pleine écran (comme en F11)
        // (Cache la bare de navigation, la barre d'informations en haut de l'écran)
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE | View.SYSTEM_UI_FLAG_FULLSCREEN);
    }


    /**
     * Méthode appelée lorsque les données capteurs sont MAJ
     * @param sensorEvent
     */
    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {

        // Récupère les valeurs de gavité x et y
        float x = (int) sensorEvent.values[0];
        float y = (int) sensorEvent.values[1];

        // Tests de directions du carré
        // Si orientation X > ou < que 2, change la direction du carré
        // Même chose pour Y
        if (x >= 3){
            direction = 3;
        } else if (x <= -3) {
            direction = 1;
        } else if (y >= 3){
            direction = 2;
        } else if (y <= -3) {
            direction = 4;
        }
    }

    /**
     * Méthode non utilisé (implémentée de base)
     *
     * @param sensor
     * @param i
     */
    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
    /**
     * Enregistre l'événement du capteur
     */
    @Override
    protected void onResume() {
        super.onResume();
        // Enregistre un sensorEventListener pour mettre a jour les valeurs x et y
        sensorManager.registerListener(this, Accelerometre, SensorManager.SENSOR_DELAY_UI);
    }
}