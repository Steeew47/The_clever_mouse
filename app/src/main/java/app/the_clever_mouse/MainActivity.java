package app.the_clever_mouse;

import android.annotation.SuppressLint;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;



public class MainActivity extends AppCompatActivity {

    private GameView gameView;
    static Player player;





    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        gameView = new GameView(this);

        player = new Player();

        setContentView(R.layout.activity_main);
        setContentView(gameView);


        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);



    }



}
