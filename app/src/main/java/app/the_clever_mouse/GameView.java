package app.the_clever_mouse;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.View;

public class GameView extends View {

    private Bitmap mouse;



    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


        canvas.drawBitmap(mouse,0,0,null);
    }

    public GameView(Context context) {

        super(context);

        mouse = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.mouse),200,200,false);


    }
}
