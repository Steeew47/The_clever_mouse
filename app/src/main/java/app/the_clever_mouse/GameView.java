package app.the_clever_mouse;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.SurfaceHolder;
import android.content.Context;
import android.view.SurfaceHolder;
import android.view.SurfaceView;


public class GameView extends SurfaceView implements SurfaceHolder.Callback{

    private Bitmap mouse;
    public int screenHeight = getResources().getDisplayMetrics().heightPixels;
    public int screenWidth = getResources().getDisplayMetrics().widthPixels;
    public int mouse_size = screenWidth/5;
    public int[] mousePos = new int[5];
    int a_mousePos = mouse_size*2;

    private int x = 0;

    private SurfaceHolder holder;


    private GameThread thread;


    public GameView(Context context) {
        super(context);
        holder = getHolder();
        getHolder().addCallback(this);


        thread = new GameThread(getHolder(),this);

        mouse = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.mouse),mouse_size,mouse_size+125,false);

        setFocusable(true);



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


    protected void Fix_onDraw(Canvas canvas) {

        mousePos[2] = mouse_size*2;
        mousePos[0] = 0;
        mousePos[1] = mouse_size;
        mousePos[3] = mouse_size*3;
        mousePos[4] = mouse_size*4;



        if (x < getWidth() - mouse.getWidth()) {
            canvas.drawColor(Color.BLACK);                                                          //Przed kazda zmiana pozycji nalezy ustawic ponownie background aby
            x++;                                                                                    // nie wystepowala smuga za animowanym obiektem
            }

            canvas.drawBitmap(mouse, x, 10, null);

        /*
        if(MainActivity.player.moveDirection == 0){
            canvas.drawBitmap(mouse,a_mousePos,screenHeight-mouse_size-200,null);
        }
        if(MainActivity.player.moveDirection == 1){

            if(a_mousePos>=mousePos[4]){
                canvas.drawBitmap(mouse,a_mousePos,screenHeight-mouse_size-200,null);
            }else{
                a_mousePos = a_mousePos+mouse_size;
                canvas.drawBitmap(mouse,a_mousePos,screenHeight-mouse_size-200,null);
            }

        }
        if(MainActivity.player.moveDirection == -1){
            if(a_mousePos<=mousePos[0]){
                canvas.drawBitmap(mouse,a_mousePos,screenHeight-mouse_size-200,null);
            }else{
                a_mousePos = a_mousePos-mouse_size;
                canvas.drawBitmap(mouse,a_mousePos,screenHeight-mouse_size-200,null);
            }
        }
        */
    }
}

