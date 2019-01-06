package app.the_clever_mouse;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


/**
 * Tworzenie Activity z glowna petla gry.
 */

public class gameActivity extends AppCompatActivity {

    public GameView gameView;

    /**
     * Ustawienie widoku na plansze gry.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        gameView = new GameView(this);
        setContentView(gameView);

    }

    /**
     * Akcje zwiazane z nacisniecie przycisku (strzalki) wstecz na telefonie. Powrot do glownego menu lub gry.
     */
    @Override
    public void onBackPressed() {
        gameView.thread.setRunning(false);
        setContentView(R.layout.activity_game);
        final Button buttonResume = findViewById(R.id.resume);
        buttonResume.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                setContentView(gameView);
                //gameView.thread.setRunning(true);
            }
        });

        final Button buttonBack = findViewById(R.id.back);
        buttonBack.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                gameActivity.super.onBackPressed();
                setContentView(R.layout.activity_game);
                MainActivity.player.score = 0;
                MainActivity.player.playerLife = 3;
                MainActivity.player.level = 1;
            }
        });

    }
}
