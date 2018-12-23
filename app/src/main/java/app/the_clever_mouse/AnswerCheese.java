package app.the_clever_mouse;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class AnswerCheese {

    private GameView gameView;
    private Bitmap bmp;
    int x = 0;
    int y = 0;
    int ySpeed = 4;
    private Paint paint;



    public AnswerCheese(GameView gameView, Bitmap bmp,int posStart){

        switch(posStart){
            case 0: x = 0;
                break;
            case 1: x = gameView.mouse_size;
                break;
            case 2: x = gameView.mouse_size*2;
                break;
            case 3: x = gameView.mouse_size*3;
                break;
            case 4: x = gameView.mouse_size*4;
                break;
        }

        this.gameView = gameView;
        this.bmp = bmp;
        paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setTextSize(70);


    }

    public void update(){
        y = y + ySpeed;
    }

    public void _onDraw(Canvas canvas){
        update();
        canvas.drawBitmap(bmp,x,y,null);
    }



}
