package app.the_clever_mouse;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.Random;


public class Equation {

    private GameView gameView;
    private Paint paint, paint2;
    private Random random;

    int a,b;
    int result;
    int equationChar;

    //equationChar = 1 - DODAWANIE
    //equationChar = 2 - ODEJMOWANIE

    public Equation(GameView gameView) {
        this.gameView = gameView;
        paint = new Paint();
        paint.setStrokeWidth(10);
        paint.setAntiAlias(true);
        paint.setStrokeCap(Paint.Cap.SQUARE);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.WHITE);
        paint.setTextSize(200);

        paint2 = new Paint();
        paint2.setTextSize(200);
        paint2.setColor(Color.BLACK);



    }

    private void update(Canvas canvas){



        if(equationChar == 0){
            result = a + b;
            canvas.drawText(Integer.toString(a)+" + "+Integer.toString(b)+" = ? ",50,400,paint);
            canvas.drawText(Integer.toString(a)+" + "+Integer.toString(b)+" = ? ",50,400,paint2);
        }
        else{
            if(a>b){
                result = a - b;
                canvas.drawText(Integer.toString(a)+" - "+Integer.toString(b)+" = ? ",50,400,paint);
                canvas.drawText(Integer.toString(a)+" - "+Integer.toString(b)+" = ? ",50,400,paint2);
            }
            else{
                result = b - a;
                canvas.drawText(Integer.toString(b)+" - "+Integer.toString(a)+" = ? ",50,400,paint);
                canvas.drawText(Integer.toString(b)+" - "+Integer.toString(a)+" = ? ",50,400,paint2);
            }
        }

    }

    public void _onDraw(Canvas canvas){
        update(canvas);
    }

}
