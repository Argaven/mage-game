// MainActivity.java

package com.example.simplegame;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Setting the GameView as the content of this activity
        setContentView(new GameView(this));
    }
}