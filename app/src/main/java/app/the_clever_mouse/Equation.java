package app.the_clever_mouse;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.Random;

public class Equation {

    private GameView gameView;
    public Random random;
    private Paint paint;

    int a,b,sign;
    int result;



    public void genereteNew(){
        this.a = random.nextInt(10);
        this.b = random.nextInt(10);

    }


    public Equation(GameView gameView) {
        this.gameView = gameView;
        paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setTextSize(70);



    }

    private void update(){
        result = a + b;
    }

    public void _onDraw(Canvas canvas){
        update();
        canvas.drawText(Integer.toString(a)+" + "+Integer.toString(b)+" = "+Integer.toString(result),400,400,paint);
    }

}
