package app.the_clever_mouse;

import android.graphics.Bitmap;
import android.graphics.Canvas;


/**
 * Parametry gracza.
 */

public class Player {

    /**Aktualna pozycja gracza*/
    public int currentPos;
    /**Aktualny poziom gry*/
    public int level = 1;
    /**Aktualny poziom zycia gracza*/
    public int playerLife = 3;
    /**Aktualny stan zdobytych punktow*/
    public int score = 0;


    private GameView gameView;
    private AnswerCheese answerCheese;
    private Bitmap bmp;

    /**Parametr odpowiedzialny za wyswietlanie obrazka gracza na dole ekranu*/
    public int topPos;
    /**Tablica 5 mozliwych pozycji gracza tak aby wszystkie zmiescily sie na ekranie*/
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

    /**
     * Ustalenie pozycji gracza
     * @param x
     */
    public void setPosition(float x){
        if(x<mousePos[1])currentPos = mousePos[0];
        if(x >= mousePos[1] && x<mousePos[2])currentPos = mousePos[1];
        if(x >= mousePos[2] && x<mousePos[3])currentPos = mousePos[2];
        if(x >= mousePos[3] && x<mousePos[4])currentPos = mousePos[3];
        if(x >= mousePos[4])currentPos = mousePos[4];


    }

    /**
     * Ustalanie dla jakich wartosci puntkow ma nastapic zmiana poziomu trudnosci
     */
    private void update(){
        if(MainActivity.player.score == 300){
            MainActivity.player.level = 2;
            gameView.randomResults.howBig = 20;
        }
        if(MainActivity.player.score == 600){
            MainActivity.player.level = 3;
            gameView.randomResults.howBig = 30;
        }
        if(MainActivity.player.score == 900){
            MainActivity.player.level = 4;
            gameView.randomResults.howBig = 40;

        }
        if(MainActivity.player.score == 1200){
            MainActivity.player.level = 5;
            gameView.randomResults.howBig = 50;

        }
        if(MainActivity.player.score == 1500){
            MainActivity.player.level = 5;
            gameView.randomResults.howBig = 60;

        }
        if(MainActivity.player.score == 1800){
            MainActivity.player.level = 6;
            gameView.randomResults.howBig = 70;

        }

    }

    /**
     * Wyswietlanie obiektu gracza na ekranie
     * @param canvas
     */
    public void _onDraw(Canvas canvas){
        update();
        canvas.drawBitmap(bmp,currentPos,topPos,null);
    }
}
