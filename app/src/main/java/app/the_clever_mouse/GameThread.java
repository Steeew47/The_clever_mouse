package app.the_clever_mouse;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

public class GameThread extends Thread {

    private int FPS = 60;
    private double avgFPS;
    private SurfaceHolder surfaceHolder;
    private GameView gameView;
    private AnswerCheese answerCheese;
    private boolean running;
    public static Canvas canvas;


    public GameThread(SurfaceHolder surfaceHolder, GameView gameView){
        super();
        this.surfaceHolder = surfaceHolder;
        this.gameView = gameView;
    }

    @Override
    public void run(){
        long startTime;
        long timeMillis;
        long waitTime;
        long totalTime = 0;
        long frameCount = 0;
        long targetTime = 1000/FPS;

        while(running){
            startTime = System.nanoTime();
            canvas = null;

            try{
                canvas = gameView.getHolder().lockCanvas();
                synchronized (gameView.getHolder()){
                    gameView._onDraw(canvas);
                    MainActivity.player.score ++;
                    MainActivity.player.moveDirection = 0;
                }
            }catch(Exception e){
                e.printStackTrace();
            }finally {
                if(canvas != null){
                    try{
                        surfaceHolder.unlockCanvasAndPost(canvas);
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                }
            }

            timeMillis = (System.nanoTime() - startTime)/1000000;
            waitTime = targetTime - timeMillis;

            try{
                this.sleep(waitTime);
            }catch (Exception e){
                e.printStackTrace();
            }

            totalTime += System.nanoTime() - startTime;
            frameCount ++;
            if (frameCount == FPS){
                avgFPS = 1000 / ((totalTime / frameCount) / 1000000);
                frameCount = 0;
                totalTime = 0;
                System.out.println(avgFPS);

                //MainActivity.player.moveDirection = 0;
                //System.out.println(MainActivity.player.moveDirection);
            }

            }

    }

    public void setRunning(boolean isRunning){
        running = isRunning;
    }
}
