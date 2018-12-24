


package app.the_clever_mouse;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.Random;

public class AnswerCheese {

    private GameView gameView;
    private Bitmap bmp;
    int x = 0;
    int y = 0;
    int ySpeed = 4;
    private Paint paint;

    Random random = new Random();

    int idIndex;
    public boolean nextTurn;

    public boolean isResult[] = new boolean[5];
    public int result[] = new int[5];
    int posStart[] = new int[5];




    public AnswerCheese(GameView gameView, Bitmap bmp){

        for(int i=0; i<5; i++){
            posStart[i] = gameView.mouse_size*i;
        }


        this.gameView = gameView;
        this.bmp = bmp;
        paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setTextSize(70);

    }

    public void update(){

        y = y + ySpeed;
        if(y >= gameView.screenHeight-gameView.mouse_size){
            y = 0;
            nextTurn = true;
            //  gameView.equation.genereteNew();

        }
    }

    public void _onDraw(Canvas canvas){
        update();


        for(int i=0; i<5; i++){
            if(result[i] == gameView.equation.result)isResult[i] = true;
            else isResult[i] = false;
        }

        if(!(isResult[0] || isResult[1] || isResult[2] || isResult[3] || isResult[4])){
            int x = random.nextInt(5);
            result[x] = gameView.equation.result;

        }

        for(int i=0; i<5; i++){
            canvas.drawBitmap(bmp,posStart[i],y,null);
            canvas.drawText(Integer.toString(result[i]),posStart[i],y,paint);
            canvas.drawText(Boolean.toString(isResult[i]),posStart[i],y+50,paint);
        }

    }



}
