// Character.java
//

package com.example.simplegame;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

public class Character {
    private float x, y;
    private Bitmap characterBitmap;

    public Character(float x, float y) {
        this.x = x;
        this.y = y;
        // IMAGE_NEEDED_1 for the character sprite
        characterBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.IMAGE_NEEDED_1);
    }

    public void update(Joystick joystick) {
        // Move the character according to the joystick's direction
        this.x += joystick.getActuatorX() * 5;
        this.y += joystick.getActuatorY() * 5;
    }

    public void draw(Canvas canvas) {
        // Draw the character on the screen
        canvas.drawBitmap(characterBitmap, x, y, new Paint());
    }
}
