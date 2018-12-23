package app.the_clever_mouse;

import java.util.ArrayList;
import java.util.Collections;

public class RandomResults{

    public boolean shuffleIt;
    public int[] result = new int[5];

    public RandomResults(){}


    public void getRandomResults(){



        ArrayList<Integer> list = new ArrayList<Integer>();
        for (int i=1; i<11; i++) {
            list.add(new Integer(i));
        }
        Collections.shuffle(list);

        result[0] = list.get(0);
        result[1] = list.get(1);
        result[2] = list.get(2);
        result[3] = list.get(3);
        result[4] = list.get(4);





    }
}
