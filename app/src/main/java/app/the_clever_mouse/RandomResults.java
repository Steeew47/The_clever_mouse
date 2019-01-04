package app.the_clever_mouse;

import java.util.ArrayList;
import java.util.Collections;


public class RandomResults{

    public boolean isResult;
    public int[] result = new int[7];
    public int[] randomChar = new int[2];
    public int howBig = 10;

    public RandomResults(){}


    public void getRandomResults(GameView gameView){



        ArrayList<Integer> list = new ArrayList<Integer>();
        for (int i=1; i<howBig; i++) {
            list.add(new Integer(i));
        }
        Collections.shuffle(list);


        for(int i=0; i<7; i++){
            result[i] = list.get(i);
        }

        ArrayList<Integer> list2 = new ArrayList<Integer>();
        for (int i=0; i<=1; i++) {
            list2.add(new Integer(i));
        }
        Collections.shuffle(list2);


        for(int i=0; i<=1; i++){
            randomChar[i] = list2.get(i);
        }

    }
}
