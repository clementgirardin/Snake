package com.example.snake;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.ImageView;

public class snakeActivity extends AppCompatActivity implements SensorEventListener {

    // Initialisations variables
    SensorManager sensorManager;
    Sensor Accelerometre;
    ImageView carre;

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

        // Récupère les valeurs de gavité x et y
        float x = (int) sensorEvent.values[0];
        float y = (int) sensorEvent.values[1];

        float largeurCarre = carre.getWidth();
        float hauteurCarre = carre.getHeight();


        // Augmentation de la vitesse de déplacement du carré
        x *= 5.0f;
        y *= 5.0f;

        // Récupère les nouvelles positions
        float newPosX = carre.getX() - x;
        float newPosY = carre.getY() + y;


        // Test pour que le carré ne sorte pas de l'écran
        // Tests pour la largeur
        if (newPosX < 0) {
            newPosX = 0;
        } else if (newPosX > largeurEcran - largeurCarre) {
            newPosX = largeurEcran - largeurCarre;
        }
        // Tests pour la hauteur
        if (newPosY < 0) {
            newPosY = 0;
        } else if (newPosY > hauteurEcran - hauteurCarre) {
            newPosY = hauteurEcran - hauteurCarre;
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
}