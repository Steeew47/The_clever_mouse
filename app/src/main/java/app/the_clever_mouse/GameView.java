package app.the_clever_mouse;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.View;



public class GameView extends View {

    private Bitmap mouse;


    public int screenHeight = getResources().getDisplayMetrics().heightPixels;
    public int screenWidth = getResources().getDisplayMetrics().widthPixels;
    public int mouse_size = screenWidth/5;


    public int[] mousePos = new int[5];
    int a_mousePos = mouse_size*2;

    public PlayerMove pm = new PlayerMove();



    @Override
    protected void onDraw(Canvas canvas) {

        mousePos[2] = mouse_size*2;
        mousePos[0] = 0;
        mousePos[1] = mouse_size;
        mousePos[3] = mouse_size*3;
        mousePos[4] = mouse_size*4;




        if(pm.moveDirection == 0){
            canvas.drawBitmap(mouse,a_mousePos,screenHeight-mouse_size-200,null);
        }
        if(pm.moveDirection == 1){

            if(a_mousePos>=mousePos[4]){
                canvas.drawBitmap(mouse,a_mousePos,screenHeight-mouse_size-200,null);
            }else{
                a_mousePos = a_mousePos+mouse_size;
                canvas.drawBitmap(mouse,a_mousePos,screenHeight-mouse_size-200,null);
            }

        }
        if(pm.moveDirection == -1){
            if(a_mousePos<=mousePos[0]){
                canvas.drawBitmap(mouse,a_mousePos,screenHeight-mouse_size-200,null);
            }else{
                a_mousePos = a_mousePos-mouse_size;
                canvas.drawBitmap(mouse,a_mousePos,screenHeight-mouse_size-200,null);
            }
        }

    }



    public GameView(Context context) {
        super(context);

        mouse = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.mouse),mouse_size,mouse_size+125,false);




    }


}

