package app.the_clever_mouse;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class Player {

    int currentPos;
    int moveDirection;

    int level = 1;
    int playerLife = 3;
    int score = 0;


    private GameView gameView;
    private Bitmap bmp;

    public int topPos;
    public int[] mousePos = new int[5];





    public Player(GameView gameView, Bitmap bmp)    {
        this.gameView = gameView;
        this.bmp = bmp;

        topPos = gameView.screenHeight-gameView.mouse_size-200;

        mousePos[2] = gameView.mouse_size*2;
        mousePos[0] = 0;
        mousePos[1] = gameView.mouse_size;
        mousePos[3] = gameView.mouse_size*3;
        mousePos[4] = gameView.mouse_size*4;
        currentPos = mousePos[2];
    }

    public Player(){

    }

    public void setPosition(float x, float y){
        if(x<mousePos[1])currentPos = mousePos[0];
        if(x >= mousePos[1] && x<mousePos[2])currentPos = mousePos[1];
        if(x >= mousePos[2] && x<mousePos[3])currentPos = mousePos[2];
        if(x >= mousePos[3] && x<mousePos[4])currentPos = mousePos[3];
        if(x >= mousePos[4])currentPos = mousePos[4];


    }


    private void update(){
    /*
        if(MainActivity.player.moveDirection == 1){
            if(currentPos<mousePos[4]) {
                currentPos = currentPos + gameView.mouse_size;
            }
        }
        if(MainActivity.player.moveDirection == -1){
            if(currentPos>mousePos[0]){
                currentPos = currentPos - gameView.mouse_size;
            }
        }

*/
    }

    public void _onDraw(Canvas canvas){
        update();
        canvas.drawBitmap(bmp,currentPos,topPos,null);
    }
}
