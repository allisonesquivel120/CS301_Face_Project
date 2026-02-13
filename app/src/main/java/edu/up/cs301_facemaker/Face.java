package edu.up.cs301_facemaker;
// @author : Allison Esquivel
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.Random;

public class Face
{
    // instance variables
    int skinColor;
    int eyeColor;
    int hairColor;
    int hairStyle;

    private Random rand = new Random();
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);




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

        int w = canvas.getWidth();
        int h = canvas.getHeight();


        float cx = w / 2f;
        float cy = h / 2f;
        float radius = Math.min(w,h) *0.3f;

        drawHead(canvas,cx,cy,radius);
        drawEyes(canvas, cx, cy, radius);
        drawHair(canvas,cx,cy,radius);

    }

    // helper method
    private void drawEyes(Canvas canvas, float cx, float cy, float radius)
    {
        // draw eyes
        paint.setColor(eyeColor);
        float eyeOffsetX = radius *0.4f;
        float eyeOffsetY = radius *0.2f;
        float eyeRadius = radius *0.1f;

        canvas.drawCircle(cx - eyeOffsetX, cy - eyeOffsetY, eyeRadius, paint);
        canvas.drawCircle(cx + eyeOffsetX, cy - eyeOffsetY, eyeRadius,paint);
    }
    // helper method
    private void drawHead(Canvas canvas,float cx, float cy, float radius)
    {
        // draw head
        paint.setColor(skinColor);
        canvas.drawCircle(cx, cy, radius,paint);
    }
    // helper method
    private void drawHair(Canvas canvas,float cx, float cy, float radius)
    {
        // draw hair
        paint.setColor(hairColor);
        switch(hairStyle)
        {
            case 0: // flat top
                canvas.drawRect(cx - radius, cy - radius, cx + radius, cy - radius / 2, paint);
                break;

            case 1:
                canvas.drawRect(cx - radius, cy - radius, cx + radius, cy - radius/ 3, paint );
                break;

            case 2: // round cap
            default:
                canvas.drawCircle(cx, cy - radius / 2, radius* 0.7f, paint);
                break;
        }
    }
}
