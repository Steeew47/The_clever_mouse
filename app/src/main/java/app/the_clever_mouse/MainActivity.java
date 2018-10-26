package app.the_clever_mouse;

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



    //LOGCAT TAGS
    String TAG_GestureDetector = "GestureDetector";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gameView = new GameView(this);

        setContentView(R.layout.activity_main);
        setContentView(gameView);

        //gd = new GestureDetector(this, new MyGestureListener());



        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        Timer timer = new Timer();






        gameView.setOnTouchListener(new OnSwipeTouchListener(MainActivity.this) {
            public void onSwipeTop() {
                Log.d(TAG_GestureDetector,"topSwipe");
            }
            public void onSwipeRight() {
                Log.d(TAG_GestureDetector,"rightSwipe");
                gameView.pm.moveDirection = 1;
                Log.d(TAG_GestureDetector,Integer.toString(gameView.pm.moveDirection));
            }
            public void onSwipeLeft() {
                Log.d(TAG_GestureDetector,"leftSwipe");
                gameView.pm.moveDirection = -1;
                Log.d(TAG_GestureDetector,Integer.toString(gameView.pm.moveDirection));
            }
            public void onSwipeBottom() {
                Log.d(TAG_GestureDetector,"bottomSwipe");
            }

        });
        Log.d(TAG_GestureDetector,Integer.toString(gameView.pm.moveDirection));
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        gameView.invalidate();
                        gameView.pm.moveDirection = 0;
                        Log.d(TAG_GestureDetector,Integer.toString(gameView.pm.moveDirection));
                    }
                });
            }
        },0,TIMER_INTERVAL);

    }



}
