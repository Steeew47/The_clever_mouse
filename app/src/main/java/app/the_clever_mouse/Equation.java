package app.the_clever_mouse;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;


public class Equation {

    private GameView gameView;
    private Paint paint;

    int a,b;
    int result;





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
        canvas.drawText(Integer.toString(a)+" + "+Integer.toString(b)+" = ? ",400,400,paint);
    }

}
