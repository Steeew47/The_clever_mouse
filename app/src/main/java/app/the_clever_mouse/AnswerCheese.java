


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
    public int trueResultPosition = 0;

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

        //TODO : rzeba dostosowac rozmiary sera; poprawna kolizja na styku 2 obiektow
        if(y > gameView.screenHeight-300-2*gameView.mouse_size-MainActivity.player.topPos){
            y = 0;
            nextTurn = true;

            if(gameView.player.currentPos==trueResultPosition){
                MainActivity.player.score = MainActivity.player.score+100;
                ySpeed++;
            }
            else{
                MainActivity.player.playerLife--;
            }

        }
    }

    public void _onDraw(Canvas canvas){


        for(int i=0; i<5; i++){
            if(result[i] == gameView.equation.result){
                isResult[i] = true;
                trueResultPosition = posStart[i];
            }
            else isResult[i] = false;
        }

        if(!(isResult[0] || isResult[1] || isResult[2] || isResult[3] || isResult[4])){
            int x = random.nextInt(5);
            result[x] = gameView.equation.result;
            trueResultPosition = posStart[x];

        }

        update();
        for(int i=0; i<5; i++){
            canvas.drawBitmap(bmp,posStart[i],y,null);
            canvas.drawText(Integer.toString(result[i]),posStart[i],y,paint);
            //canvas.drawText(Boolean.toString(isResult[i]),posStart[i],y+50,paint);
            //canvas.drawText(Integer.toString(MainActivity.player.currentPos),posStart[i],y+100,paint);
            //canvas.drawText(Integer.toString(trueResultPosition),posStart[i],y+150,paint);
        }

    }



}
