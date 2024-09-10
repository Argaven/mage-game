// Joystick.java

package com.example.simplegame;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;

public class Joystick {
    private float outerCircleCenterX, outerCircleCenterY, outerCircleRadius;
    private float innerCircleCenterX, innerCircleCenterY, innerCircleRadius;
    private boolean isPressed = false;
    private float actuatorX = 0, actuatorY = 0;

    public Joystick(float centerX, float centerY, float outerRadius, float innerRadius) {
        outerCircleCenterX = centerX;
        outerCircleCenterY = centerY;
        outerCircleRadius = outerRadius;
        innerCircleCenterX = centerX;
        innerCircleCenterY = centerY;
        innerCircleRadius = innerRadius;
    }

    public void draw(Canvas canvas) {
        Paint paint = new Paint();
        // Draw outer circle
        paint.setColor(Color.GRAY);
        canvas.drawCircle(outerCircleCenterX, outerCircleCenterY, outerCircleRadius, paint);
        // Draw inner circle
        paint.setColor(Color.DKGRAY);
        canvas.drawCircle(innerCircleCenterX, innerCircleCenterY, innerCircleRadius, paint);
    }

    public void handleTouch(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (isPressed(event)) {
                    isPressed = true;
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if (isPressed) {
                    float deltaX = event.getX() - outerCircleCenterX;
                    float deltaY = event.getY() - outerCircleCenterY;
                    float distance = (float) Math.sqrt(deltaX * deltaX + deltaY * deltaY);

                    if (distance < outerCircleRadius) {
                        innerCircleCenterX = event.getX();
                        innerCircleCenterY = event.getY();
                    } else {
                        innerCircleCenterX = outerCircleCenterX + (deltaX / distance) * outerCircleRadius;
                        innerCircleCenterY = outerCircleCenterY + (deltaY / distance) * outerCircleRadius;
                    }

                    actuatorX = (innerCircleCenterX - outerCircleCenterX) / outerCircleRadius;
                    actuatorY = (innerCircleCenterY - outerCircleCenterY) / outerCircleRadius;
                }
                break;
            case MotionEvent.ACTION_UP:
                isPressed = false;
                innerCircleCenterX = outerCircleCenterX;
                innerCircleCenterY = outerCircleCenterY;
                actuatorX = 0;
                actuatorY = 0;
                break;
        }
    }

    public boolean isPressed(MotionEvent event) {
        float deltaX = event.getX() - outerCircleCenterX;
        float deltaY = event.getY() - outerCircleCenterY;
        return Math.sqrt(deltaX * deltaX + deltaY * deltaY) < outerCircleRadius;
    }

    public float getActuatorX() {
        return actuatorX;
    }

    public float getActuatorY() {
        return actuatorY;
    }
}
