package edu.up.cs301_facemaker;

import android.graphics.Color;
import android.view.View;
import android.widget.AdapterView;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;

// FaceController
// This class acts as the controller, it listens to all user interactions.
// @author Allison E.
public class FaceController implements SeekBar.OnSeekBarChangeListener, RadioGroup.OnCheckedChangeListener, AdapterView.OnItemSelectedListener, View.OnClickListener {
    // instance variables
    private Face face; // the model
    private FaceSurfaceView view; // custom surface view

    private SeekBar redSB;
    private SeekBar greenSB;
    private SeekBar blueSB;
    private RadioGroup featureGroup;

    private Spinner hairSpinner;

    // tracks which feature is currently selected
    private int selectedFeature = 1;

    // attaches all listeners + stores references to the model & view
    public FaceController(FaceSurfaceView view, Face face, SeekBar redSB, SeekBar greenSB, SeekBar blueSB, RadioGroup featureGroup, Spinner hairSpinner)
    {
        this.view = view;
        this.face = face;

        this.redSB = redSB;
        this.greenSB = greenSB;
        this.blueSB = blueSB;
        this.featureGroup = featureGroup;
        this.hairSpinner = hairSpinner;
        // seekbar listeners
        redSB.setOnSeekBarChangeListener(this);
        greenSB.setOnSeekBarChangeListener(this);
        blueSB.setOnSeekBarChangeListener(this);
        // radiogroup listeners
        featureGroup.setOnCheckedChangeListener(this);
        // spinner listener
        hairSpinner.setOnItemSelectedListener(this);

    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
    {
        if (!fromUser) return;

        int r = redSB.getProgress();
        int g = greenSB.getProgress();
        int b = blueSB.getProgress();
        int color = Color.rgb(r, g, b);
        // updates the correct feature
        if(selectedFeature == 0)
        {
            face.hairColor = color;
        }
        else if(selectedFeature == 1)
        {
            face.eyeColor = color;
        }
        else
        {
            face.skinColor = color;
        }
        view.redraw();

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
    // radioGroup listener
    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId)
    {
        if(checkedId == group.getChildAt(0).getId())
        {
            selectedFeature = 0; // hair
        }
        else if (checkedId == group.getChildAt(1).getId())
        {
            selectedFeature = 1; // eyes
        }
        else
        {
            selectedFeature = 2; // skin
        }

        syncSeekBars();
    }
    // spinner listener --> user selects hair style
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
    {
        face.hairStyle = position;
        this.view.redraw();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }

    @Override
    public void onClick(View v)
    {
        face.randomize();
        hairSpinner.setSelection(face.hairStyle);
        syncSeekBars();
        view.redraw();

    }
    // helper method: sync the seekbar to selected feature color
    private void syncSeekBars() {
        int color;

        if (selectedFeature == 0) {
            color = face.hairColor;
        } else if (selectedFeature == 1) {
            color = face.eyeColor;
        } else {
            color = face.skinColor;
        }

        redSB.setProgress((color >> 16) & 0xFF);
        greenSB.setProgress((color >> 8) & 0xFF);
        blueSB.setProgress(color & 0xFF);
    }
}

