package app.the_clever_mouse;

import java.util.ArrayList;
import java.util.Collections;

/** Losowanie liczb z pewnego przedzialu wykorzystywane w tworzeniu rownania oraz odpowiedzi do rownania */

public class RandomResults{

    /**Tablica na 7 losowych liczb*/
    public int[] result = new int[7];
    /**Tablica na 2 losowe liczby odpowiedzialne za znak tworzonego rownania*/
    public int[] randomChar = new int[2];
    /**Jak wielki ma byc przedzial losowanych liczb*/
    public int howBig = 10;

    public RandomResults(){}

    /**
     * Wymuszenie losowania liczb
     * @param gameView
     */
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
