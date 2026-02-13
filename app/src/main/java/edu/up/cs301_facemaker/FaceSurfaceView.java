package edu.up.cs301_facemaker;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class FaceSurfaceView extends SurfaceView implements SurfaceHolder.Callback {

    private Face face;

    public FaceSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        getHolder().addCallback(this);
        setWillNotDraw(false);
    }

    public void setFace(Face f) {
        this.face = f;
        redraw();
    }

    public void redraw() {
        SurfaceHolder holder = getHolder();
        Canvas canvas = holder.lockCanvas();

        if (canvas != null) {
            canvas.drawColor(0xFFFFFFFF); // white background
            if (face != null) {
                face.onDraw(canvas);
            }
            holder.unlockCanvasAndPost(canvas);
        }
    }

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