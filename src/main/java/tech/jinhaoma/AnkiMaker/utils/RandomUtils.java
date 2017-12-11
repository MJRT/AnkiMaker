package tech.jinhaoma.AnkiMaker.utils;

import java.util.Random;

/**
 * Created by mjrt on 2/9/2017.
 */
public class RandomUtils {

    static private Random random;

    static {
        random = new Random(System.currentTimeMillis());
    }

    public static int nextInt(int left,int right){
        if(left > right){
            int t = left;
            left = right;
            right = t;
        }
        return random.nextInt(right-left)+left;
    }

}
