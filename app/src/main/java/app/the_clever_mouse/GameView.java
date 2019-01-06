package app.the_clever_mouse;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;


/**
 * Glowna klasa zarzadzajaca wyswietlaniem na planszy elementow.
 */

public class GameView extends SurfaceView implements SurfaceHolder.Callback{

    private Bitmap mouse, heart, cheese, bg;
    private long lastClick;
    private SurfaceHolder holder;
    private boolean alreadyExecuted;

    /**Wysokosc ekranu w pixelach*/
    public int screenHeight = getResources().getDisplayMetrics().heightPixels;
    /**Szerokosc ekranu w pixelach*/
    public int screenWidth = getResources().getDisplayMetrics().widthPixels;
    /**Rozmiary obrazku myszy*/
    public int mouse_size = screenWidth/5;
    /**Rozmiary obrazku sera*/
    public int heart_size = screenWidth/10;
    /**Wybor pozycji ruchu gracza*/
    public float whereClickX;
    /**Akctivity zwiazane z ekranem koncowym*/
    public Activity a;
    /** Ustalenie statusu dzialania gry */
    public GameState state = GameState.Ready;
    /** Obiekt losowych odpowiedzi do rownania*/
    public AnswerCheese cheeseObject;
    /** Obiekt silnika gry */
    public GameThread thread;
    /** Obiekt zwiazany z losowym tworzeniem rownan do rozwiazania */
    public Equation equation;
    /** Obiekt gracza */
    public Player player;

    /** Zmienna okreslajaca aktualny stan gry*/
    public enum GameState{
        Ready,Running,Pause,GameOver}

    RandomResults randomResults = new RandomResults();



    public GameView(Context context) {
        super(context);
        holder = getHolder();
        getHolder().addCallback(this);
        a = (Activity)context;


        thread = new GameThread(getHolder(),this);
        bg = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.bg),screenWidth,screenHeight,false);
        mouse = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.mouse),mouse_size,mouse_size+125,false);
        heart = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.heart),heart_size,heart_size,false);
        setFocusable(true);
        cheese = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.cheese),mouse_size,mouse_size+125,false);

        player = new Player(this,mouse);
        equation = new Equation(this);


    }

    /**
     * Stworzona powierzchnia planszy
     * @param holder
     */
    @Override
    public void surfaceCreated(SurfaceHolder holder){
        thread.setRunning(true);
        cheeseObject = new AnswerCheese(this,cheese);
        thread.start();

        cheeseObject.nextTurn = true;

    }

    /**
     * Zmiana powierzchni planszy
     * @param holder
     * @param format
     * @param width
     * @param height
     */
    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height){

    }

    /**
     * Znisczenie powierzchni planszy
     * @param holder
     */
    @Override
    public void surfaceDestroyed(SurfaceHolder holder){
        boolean retry = true;
        while(retry){
            try{
                thread.setRunning(false);
                thread.join();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            retry = false;
        }
    }

    /**
     * Aktualizacja i sprawdzanie warunku zakonczenia gry.
     * @param canvas
     */
    public void update(Canvas canvas){
        if(MainActivity.player.playerLife == 0){
            state = GameState.GameOver;
            whereClickX = 0;

        }
    }

    /**
     * Rysowania serc zwiazanych z poziomem zycia gracza.
     * @param canvas
     */
    public void lifePrint(Canvas canvas){
        if(MainActivity.player.playerLife == 3){
            canvas.drawBitmap(heart,0,10,null);
            canvas.drawBitmap(heart,heart_size,10,null);
            canvas.drawBitmap(heart,heart_size*2,10,null);
        }
        if(MainActivity.player.playerLife == 2){
            canvas.drawBitmap(heart,0,10,null);
            canvas.drawBitmap(heart,heart_size,10,null);
        }
        if(MainActivity.player.playerLife == 1){
            canvas.drawBitmap(heart,0,10,null);
        }

    }
    /**
     * Wyswietlanie poziomu trudnosci oraz zdobytych punktow.
     * @param canvas
     */
    public void lvlPrint(Canvas canvas){
        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(3);
        paint.setAntiAlias(true);
        paint.setStrokeCap(Paint.Cap.SQUARE);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setTextSize(60);
        String levelString = "Level : "+ Integer.toString(MainActivity.player.level);
        canvas.drawText(levelString, screenWidth/2+150, 50, paint);
        canvas.drawText("Score : " +Integer.toString(MainActivity.player.score),screenWidth/2+150,50+60,paint);
    }

    /**
     * Ekran koncowy, zakonczenie gry zwiazane z utraceniem wszystkich zyc.
     * @param canvas
     */
    public void gameOverPrint(Canvas canvas){

        Rect bounds = new Rect();

        Paint paint = new Paint();
        paint.setStrokeWidth(10);
        paint.setAntiAlias(true);
        paint.setStrokeCap(Paint.Cap.SQUARE);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.WHITE);
        paint.setTextSize(150);

        Paint paint2 = new Paint();
        paint2.setTextSize(150);
        paint2.setColor(Color.BLACK);

        String textGameOver = "GAME OVER";
        String textScore = "SCORE : " + Integer.toString(MainActivity.player.score);
        String textLevel = "LEVEL : " + Integer.toString(MainActivity.player.level);
        String textBack = "click anywhere to back";

        paint.getTextBounds(textGameOver,0,textGameOver.length(),bounds);

        canvas.drawText(textGameOver,screenWidth/2-bounds.width()/2,screenHeight/2,paint);
        canvas.drawText(textGameOver,screenWidth/2-bounds.width()/2,screenHeight/2,paint2);

        paint2.setTextSize(100);
        canvas.drawText(textScore,screenWidth/2-bounds.width()/2,screenHeight/2+bounds.height(),paint2);
        canvas.drawText(textLevel,screenWidth/2-bounds.width()/2,screenHeight/2+bounds.height()+100,paint2);

        paint2.setTextSize(50);
        canvas.drawText(textBack,screenWidth/2-bounds.width()/2+150,screenHeight/2+bounds.height()+400,paint2);
    }


    /**
     * Koniec rundy czyli udzielnie odpowiedzi na wyswietlane rownanie.
     */
    public void endofTurn(){
        if(cheeseObject.nextTurn == true) {


            randomResults.getRandomResults(this);
            equation.equationChar = randomResults.randomChar[0];
            equation.b = randomResults.result[5];
            equation.a = randomResults.result[6];

            for(int i=0; i<5; i++){
                cheeseObject.result[i] = randomResults.result[i];
            }

        }cheeseObject.nextTurn = false;
    }


    /**
     * Detekcja klikniecia w ekran odpowiedzialnego za zmiane kierunku ruchu postaci - gracza.
     * @param event
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (System.currentTimeMillis() - lastClick > 50) {
            lastClick = System.currentTimeMillis();
            synchronized (getHolder()) {
                whereClickX = event.getX();
                player.setPosition(event.getX());

            }
        }
        return true;
    }


    /**
     * Ciagle wyswietlanie wszystkich elementow gry.
     * @param canvas
     */

    protected void _onDraw(Canvas canvas) {

        if(state == GameState.Ready){
            update(canvas);
            canvas.drawBitmap(bg,0,0,null);
            lvlPrint(canvas);
            lifePrint(canvas);
            endofTurn();
            equation._onDraw(canvas);
            cheeseObject._onDraw(canvas);
            player._onDraw(canvas);
        }
        if(state == GameState.GameOver){


            canvas.drawBitmap(bg,0,0,null);
            gameOverPrint(canvas);


            if(whereClickX>0 && !alreadyExecuted){
                Intent intent = new Intent(a,MainActivity.class);
                a.startActivity(intent);
                alreadyExecuted = true;
            }
        }
    }

}

