package edu.up.cs301_facemaker;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

// FaceSurfaceView
// This class is responsible for drawing the face model onto the screen
// @author Allison E.

public class FaceSurfaceView extends SurfaceView implements SurfaceHolder.Callback {

    private Face face; // face model that will be drawn on the screen

    public FaceSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        getHolder().addCallback(this);
        // lets drawing use lockCanvas()
        setWillNotDraw(false);
    }

    public void setFace(Face f) {
        this.face = f;
        redraw(); // draw immediately when face is set
    }

    public void redraw() {
        SurfaceHolder holder = getHolder();
        Canvas canvas = holder.lockCanvas();

        if (canvas != null) {
            canvas.drawColor(0xFFFFFFFF); // white background
            if (face != null) {
                face.onDraw(canvas); // draw face if it exist
            }
            holder.unlockCanvasAndPost(canvas);
        }
    }

    // draw when surface appears
    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        redraw();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        redraw();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) { }
}