// GameView.java

package com.example.simplegame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GameView extends SurfaceView implements SurfaceHolder.Callback {

    private GameThread thread;
    private Joystick joystick;
    private Character character;

    public GameView(Context context) {
        super(context);

        // Initialize the Game Thread and the Surface Holder
        getHolder().addCallback(this);
        thread = new GameThread(getHolder(), this);

        // Initialize the joystick and character
        joystick = new Joystick(150, getHeight() - 150, 100, 50);
        character = new Character(getWidth() / 2, getHeight() / 2, context);

        setFocusable(true);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        thread.setRunning(true);
        thread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {}

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        while (retry) {
            try {
                thread.setRunning(false);
                thread.join();
                retry = false;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // Draw the game elements
    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        if (canvas != null) {
            character.update(joystick);  // Update character based on joystick
            character.draw(canvas);      // Draw the character
            joystick.draw(canvas);       // Draw the joystick
        }
    }

    // Handle touch events to control the joystick
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        joystick.handleTouch(event);  // Handle joystick movement
        return true;
    }
}
