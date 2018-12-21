package app.the_clever_mouse;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.content.Context;
import android.view.SurfaceHolder;
import android.view.SurfaceView;


public class GameView extends SurfaceView implements SurfaceHolder.Callback{

    private Bitmap mouse, heart;
    public int screenHeight = getResources().getDisplayMetrics().heightPixels;
    public int screenWidth = getResources().getDisplayMetrics().widthPixels;
    public int mouse_size = screenWidth/5;
    public int heart_size = screenWidth/10;

    public int[] mousePos = new int[5];





    private SurfaceHolder holder;
    private GameThread thread;
    private Player player;


    public GameView(Context context) {
        super(context);
        holder = getHolder();
        getHolder().addCallback(this);


        thread = new GameThread(getHolder(),this);

        mouse = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.mouse),mouse_size,mouse_size+125,false);
        heart = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.heart),heart_size,heart_size,false);
        setFocusable(true);

        player = new Player(this,mouse);

    }


    @Override
    public void surfaceCreated(SurfaceHolder holder){
        thread.setRunning(true);
        thread.start();

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
            canvas.drawBitmap(heart,mouse_size/2,10,null);
            canvas.drawBitmap(heart,mouse_size,10,null);
        }
        if(MainActivity.player.playerLife == 2){
            canvas.drawBitmap(heart,0,10,null);
            canvas.drawBitmap(heart,mouse_size/2,10,null);
        }
        if(MainActivity.player.playerLife == 1){
            canvas.drawBitmap(heart,0,10,null);

        }

    }
    public void lvlPrint(Canvas canvas){
        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawPaint(paint);
        paint.setColor(Color.WHITE);
        paint.setTextSize(38);
        String levelString = "Level : "+ Integer.toString(MainActivity.player.level);
        canvas.drawText(levelString, screenWidth/2, 50, paint);
    }


    protected void _onDraw(Canvas canvas) {

        mousePos[2] = mouse_size*2;
        mousePos[0] = 0;
        mousePos[1] = mouse_size;
        mousePos[3] = mouse_size*3;
        mousePos[4] = mouse_size*4;

        canvas.drawColor(Color.BLACK);



        lvlPrint(canvas);
        lifePrint(canvas);
        //TODO: na początku gry ma sia pojawiac na srodku ekranu pozycja mouse_size * 2

        if(MainActivity.player.moveDirection == 0){
            canvas.drawBitmap(mouse,MainActivity.player.currentPos,screenHeight-mouse_size-200,null);
        }
        if(MainActivity.player.moveDirection == 1){

            if(MainActivity.player.currentPos>=mousePos[4]){
                canvas.drawBitmap(mouse,MainActivity.player.currentPos,screenHeight-mouse_size-200,null);
            }else{
                MainActivity.player.currentPos = MainActivity.player.currentPos+mouse_size;
                canvas.drawBitmap(mouse,MainActivity.player.currentPos,screenHeight-mouse_size-200,null);
            }

        }
        if(MainActivity.player.moveDirection == -1){
            if(MainActivity.player.currentPos<=mousePos[0]){
                canvas.drawBitmap(mouse,MainActivity.player.currentPos,screenHeight-mouse_size-200,null);
            }else{
                MainActivity.player.currentPos = MainActivity.player.currentPos-mouse_size;
                canvas.drawBitmap(mouse,MainActivity.player.currentPos,screenHeight-mouse_size-200,null);
            }
        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }

}

