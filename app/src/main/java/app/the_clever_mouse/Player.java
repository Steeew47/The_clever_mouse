package app.the_clever_mouse;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class Player {

    int currentPos;
    int moveDirection;

    int level = 1;
    int playerLife = 1;


    private int x = 0;
    private int xSpeed = 5;
    private GameView gameView;
    private Bitmap bmp;


    public Player(GameView gameView, Bitmap bmp){
        this.gameView = gameView;
        this.bmp = bmp;

    }

    public Player(){

    }

    private void update(){

    }

    public void _onDraw(Canvas canvas){
        update();
        canvas.drawBitmap(bmp,x,10,null);
    }
}
