package edu.up.cs301_facemaker;
// @author : Allison Esquivel
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity
{
    private Face face;
    private FaceSurfaceView faceView;

    private SeekBar redSB, greenSB, blueSB;
    private RadioGroup featureGroup;
    private Spinner hairSpinner;
    private Button randomButton;

    private FaceController controller;
    String[] hairStyles = {"Short", "Medium", "Long"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        face = new Face();
        faceView = findViewById(R.id.faceSurfaceView);
        faceView.setFace(face);

        // colors
        redSB = findViewById(R.id.redSeekBar);
        greenSB = findViewById(R.id.greenSeekBar);
        blueSB = findViewById(R.id.blueSeekBar);

        featureGroup = findViewById(R.id.allRadioButton);
        hairSpinner = findViewById(R.id.hairStyleId);
        randomButton = findViewById(R.id.randomFaceButton);

        // spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                this.hairStyles);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        hairSpinner.setAdapter(adapter);

        // controller
        controller = new FaceController(faceView, face, redSB, greenSB, blueSB, featureGroup, hairSpinner);
        randomButton.setOnClickListener(controller);
        hairSpinner.setSelection(face.hairStyle);
        syncSeekBarsToFace();

    }
    private void syncSeekBarsToFace() {
        int color = face.eyeColor; // Eyes selected by default

        redSB.setProgress((color >> 16) & 0xFF);
        greenSB.setProgress((color >> 8) & 0xFF);
        blueSB.setProgress(color & 0xFF);
    }

}