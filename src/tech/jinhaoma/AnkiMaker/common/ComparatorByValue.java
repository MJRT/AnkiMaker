package tech.jinhaoma.AnkiMaker.common;

import java.util.Comparator;
import java.util.Map;

/**
 * Created by mjrt on 1/10/2017.
 */
public class ComparatorByValue implements Comparator<Map.Entry<String ,Integer>> {
    @Override
    public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
        return o2.getValue() - o1.getValue();
    }
}
