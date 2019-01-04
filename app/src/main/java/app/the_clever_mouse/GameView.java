package app.the_clever_mouse;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Toast;

import java.util.Random;


public class GameView extends SurfaceView implements SurfaceHolder.Callback{

    private Bitmap mouse, heart, cheese, bg;

    public int screenHeight = getResources().getDisplayMetrics().heightPixels;
    public int screenWidth = getResources().getDisplayMetrics().widthPixels;
    public int mouse_size = screenWidth/5;
    public int heart_size = screenWidth/10;
    private long lastClick;
    public float whereClickX;


    public AnswerCheese cheeseObject;
    RandomResults randomResults = new RandomResults();

    private Random random;
    private SurfaceHolder holder;
    private GameThread thread;
    public Player player;;
    public Equation equation;




    public GameView(Context context) {
        super(context);
        holder = getHolder();
        getHolder().addCallback(this);


        thread = new GameThread(getHolder(),this);
        bg = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.bg),screenWidth,screenHeight,false);
        mouse = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.mouse),mouse_size,mouse_size+125,false);
        heart = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.heart),heart_size,heart_size,false);
        setFocusable(true);
        cheese = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.cheese),mouse_size,mouse_size+125,false);

        player = new Player(this,mouse);
        equation = new Equation(this);


    }


    @Override
    public void surfaceCreated(SurfaceHolder holder){
        thread.setRunning(true);
        cheeseObject = new AnswerCheese(this,cheese);
        thread.start();
        cheeseObject.nextTurn = true;

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height){

    }

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



    public void update(){


    }

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
        if(MainActivity.player.playerLife == 0){
            thread.setRunning(false);



        }


    }
    public void lvlPrint(Canvas canvas){
        Paint paint = new Paint();
        //paint.setColor(Color.BLACK);
        //paint.setStyle(Paint.Style.FILL);
        //canvas.drawPaint(paint);
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(3);
        paint.setAntiAlias(true);
        paint.setStrokeCap(Paint.Cap.SQUARE);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setTextSize(60);
        String levelString = "Level : "+ Integer.toString(MainActivity.player.level);
        canvas.drawText(levelString, screenWidth/2+150, 50, paint);
        canvas.drawText("Score : " +Integer.toString(MainActivity.player.score),screenWidth/2+150,50+60,paint);
        //canvas.drawText(Boolean.toString(this.randomResults.isResult),screenWidth/2,200,paint);
        //canvas.drawText(Float.toString(whereClickX),screenWidth/2,300,paint);
    }



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






    protected void _onDraw(Canvas canvas) {

        //canvas.drawColor(Color.BLACK);
        canvas.drawBitmap(bg,0,0,null);
        lvlPrint(canvas);
        lifePrint(canvas);
        endofTurn();
        equation._onDraw(canvas);
        cheeseObject._onDraw(canvas);
        player._onDraw(canvas);
        update();


    }



}

