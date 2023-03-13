package com.example.snake;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

public class snakeActivity extends AppCompatActivity implements SensorEventListener {

    // Initialisations variables
    SensorManager sensorManager;
    Sensor Accelerometre;
    ImageView carre;

    int direction = 0;

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
    }

    /**
     * Méthode appelée lorsque les données capteurs sont MAJ
     * @param sensorEvent
     */
    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        // Récupération des délimitations de l'écran
        int largeurEcran = Resources.getSystem().getDisplayMetrics().widthPixels;
        int hauteurEcran = Resources.getSystem().getDisplayMetrics().heightPixels;

        // Récupère les dimensions du carré
        int largeurCarre = carre.getWidth();
        int hauteurCarre = carre.getHeight();

        // Récupère les valeurs de gavité x et y
        float x = (int) sensorEvent.values[0];
        float y = (int) sensorEvent.values[1];

//        Log.d("coordonnéeX", "x: "+ x);
//        Log.d("coordonnéeY", "y: "+ y);


        // Récupère les nouvelles positions
        int newPosX = (int) (carre.getX());
        int newPosY = (int) (carre.getY());

//        Log.d("coordonnéeX", "x: "+ newPosX);

        // Tests de directions du carré
        // Si orientation X > ou < que 2, change la direction du carré
        // Même chose pour Y
        if (x >= 3){
            direction = 1;
            newPosX -= 10;

        } else if (x <= -3) {
            newPosX += 10;
        } else if (y >= 3){
            newPosY += 10;
        } else if (y <= -3) {
            newPosY -= 10;
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


        // Positionne le carré avec les nouvelles positions
        carre.setX(newPosX);
        carre.setY(newPosY);
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