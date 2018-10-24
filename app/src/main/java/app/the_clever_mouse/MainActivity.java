package app.the_clever_mouse;

import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import app.the_clever_mouse.GameView;

import android.util.DisplayMetrics;
import android.util.Log;



public class MainActivity extends AppCompatActivity {

    private GameView gameView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        gameView = new GameView(this);
        setContentView(gameView);
        Log.d("UWUGA",Integer.toString(gameView.mouse_size));

    }
}
