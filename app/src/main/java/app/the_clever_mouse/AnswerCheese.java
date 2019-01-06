

package app.the_clever_mouse;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.Random;

/**
 * Tworzenie i rysowanie odpowiedzi do rownan.
 */



public class AnswerCheese {


    private GameView gameView;

    private Bitmap bmp;
    private Paint paint;
    private Paint paint2;
    Random random = new Random();

    /**Losowa pozycja dla prawidlowej odpowiedzi*/
    public int x = 0;
    /**Pozycja na osi Y gdzie znajduja sie rysunki odpowiedzi*/
    public int y = 0;
    /**Predkosc poruszania sie rysunkow odpowiedzi*/
    public int ySpeed = 4;
    /**Pozycja prawidlowej odpowiedzi*/
    public int trueResultPosition = 0;
    /**Oznaczenie czy rozpoczeto nowa runde*/
    public boolean nextTurn;
    /**Tablica z sprawdzeniem czy wynik jest prawidlowy*/
    public boolean isResult[] = new boolean[5];
    /**Tablica pieciu pozycji startowych*/
    int posStart[] = new int[5];
    /**Toblica losowych wynikow dla rownania*/
    public int result[] = new int[5];


    /**
     *
     * @param gameView glowna pole gry.
     * @param bmp wczytanie obrazka dla obiektu odpowiedzi czyli sera.
     *
     */

    public AnswerCheese(GameView gameView, Bitmap bmp){


        for(int i=0; i<5; i++){
            posStart[i] = gameView.mouse_size*i;
        }


        this.gameView = gameView;
        this.bmp = bmp;
        paint = new Paint();
        paint.setStrokeWidth(10);
        paint.setAntiAlias(true);
        paint.setStrokeCap(Paint.Cap.SQUARE);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.WHITE);
        paint.setTextSize(150);

        paint2 = new Paint();
        paint2.setTextSize(150);
        paint2.setColor(Color.BLACK);

    }

    /**
     * Sprawdzanie poziomu trudnosci i ustalanie odpowiedniej predkosci poruszania sie obrazkow z odpowiedziami oraz detekcja wybory prawidlowej odpowiedzi i przydzielanie odpowiednich punkow lub utrata zycia.
     */
    public void update(){

        y = y + ySpeed;


        if(MainActivity.player.level == 4){
            this.ySpeed = 5;
        }
        if(MainActivity.player.level == 5){
            this.ySpeed = 5;
        }
        if(MainActivity.player.level == 6){
            this.ySpeed = 5;
        }



        if(y > gameView.screenHeight-300-2*gameView.mouse_size-MainActivity.player.topPos){
            y = 0;
            nextTurn = true;

            if(gameView.player.currentPos==trueResultPosition){
                MainActivity.player.score = MainActivity.player.score+100;
            }
            else{
                MainActivity.player.playerLife--;
            }

        }
    }


    /**
     * Rysowanie obiekow odpowiedzi wraz z naniesionymi odpowiedziamy losowymi i jedna prawidlowa.
     * @param canvas plansza na ktorej sa rysowane obiekty.
     */
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
            canvas.drawText(Integer.toString(result[i]),posStart[i]+60,y+250,paint);
            canvas.drawText(Integer.toString(result[i]),posStart[i]+60,y+250,paint2);
        }

    }



}
