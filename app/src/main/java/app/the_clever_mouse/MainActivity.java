package app.the_clever_mouse;

import android.annotation.SuppressLint;
import android.content.pm.ActivityInfo;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import java.util.Timer;
import java.util.TimerTask;



public class MainActivity extends AppCompatActivity {

    private Handler handler = new Handler();
    private final static long TIMER_INTERVAL = 1;
    private GameView gameView;
    long lastClick;
    static Player player;

    //LOGCAT TAGS
    String TAG_GestureDetector = "GestureDetector";




    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        gameView = new GameView(this);

        player = new Player();

        setContentView(R.layout.activity_main);
        setContentView(gameView);


        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        gameView.setOnTouchListener(new OnSwipeTouchListener(MainActivity.this) {
            public void onSwipeTop() {
                Log.d(TAG_GestureDetector,"topSwipe");
            }
            public void onSwipeRight() {
                if(System.currentTimeMillis() - lastClick > 500){
                    lastClick = System.currentTimeMillis();
                    Log.d(TAG_GestureDetector,"rightSwipe");

                        player.moveDirection = 1;

                    Log.d(TAG_GestureDetector,Integer.toString(player.moveDirection));
                }
            }
            public void onSwipeLeft() {
                if(System.currentTimeMillis() - lastClick > 500) {
                    lastClick = System.currentTimeMillis();
                    Log.d(TAG_GestureDetector,"leftSwipe");

                        player.moveDirection = -1;

                    Log.d(TAG_GestureDetector,Integer.toString(player.moveDirection));
                }

            }
            public void onSwipeBottom() {
                Log.d(TAG_GestureDetector,"bottomSwipe");
            }

        });
        Log.d(TAG_GestureDetector,Integer.toString(player.moveDirection));


    }



}
