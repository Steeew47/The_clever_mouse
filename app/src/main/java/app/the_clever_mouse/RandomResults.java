package app.the_clever_mouse;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class RandomResults{

    public boolean isResult;
    public int[] result = new int[7];
    Random random = new Random();

    public RandomResults(){}


    public void getRandomResults(GameView gameView){



        ArrayList<Integer> list = new ArrayList<Integer>();
        for (int i=1; i<11; i++) {
            list.add(new Integer(i));
        }
        Collections.shuffle(list);


        for(int i=0; i<7; i++){
            result[i] = list.get(i);
        }


    }
}
