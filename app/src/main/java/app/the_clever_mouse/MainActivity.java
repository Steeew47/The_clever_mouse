package app.the_clever_mouse;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Menu glowne gry
 */

public class MainActivity extends AppCompatActivity {


    static Player player;

    /**
     * Wyswietlanie elementow glownego menu gry - przycisk start, tlo, grafika.
     * @param savedInstanceState
     */

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        player = new Player();


        final Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,gameActivity.class);
                startActivity(intent);
            }
        });


        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);



    }



}
