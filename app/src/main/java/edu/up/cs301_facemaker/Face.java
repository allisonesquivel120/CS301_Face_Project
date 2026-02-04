package edu.up.cs301_facemaker;
// @author : Allison Esquivel
import android.graphics.Canvas;
import android.graphics.Color;

import java.util.Random;

public class Face
{
    // instance variables
    int skinColor;
    int eyeColor;
    int hairColor;
    int hairStyle;

    private Random rand = new Random();

    // calls radomize method to generate a face

    public Face()
    {
        randomize();
    }
    // method that calls assigns
    // a random color to
    // skinColor, eyeColor, hairColor, and hairStyle
    // using randomColor method
    private void randomize()
    {
        skinColor = randomColor();

        eyeColor = randomColor();

        hairColor = randomColor();

        hairStyle = randomColor();
    }
    // helper method :
    // generates a random color
    // by assigning different values for r, g, and b
    private int randomColor()
    {
        int r = rand.nextInt(255);

        int g = rand.nextInt(255);

        int b = rand.nextInt(255);

        return Color.rgb(r,g,b);
    }
    // main method to draw the face
    public void onDraw(Canvas canvas)
    {
        // nothing for now
    }

    // helper method
    private void drawEyes(Canvas canvas)
    {
        //TODO: draw head using eye color
    }
    // helper method
    private void drawHead(Canvas canvas)
    {
        //TODO: draw head using skinColor
    }
    // helper method
    private void drawHair(Canvas canvas)
    {
        //TODO: switch hairstyles + draw correct shape
    }
}
