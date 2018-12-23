package app.the_clever_mouse;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.Random;

public class Equation {

    private GameView gameView;
    public Random random;
    private Paint paint;

    int a = 2;
    int b = 10;
    int result;


    public Equation(GameView gameView) {
        this.gameView = gameView;
        paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setTextSize(70);



    }
    private void update(){

    }

    public void _onDraw(Canvas canvas){
        update();
        canvas.drawText(Integer.toString(a)+Integer.toString(b),400,400,paint);
    }

}
