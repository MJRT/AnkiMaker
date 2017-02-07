package tech.jinhaoma.AnkiMaker.maker;

import tech.jinhaoma.AnkiMaker.common.TxtUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by mjrt on 2/3/2017.
 */
public class Contrastor {

    public static List<String> dealwith (List<String> words , List<String> easy){

        List<String> res = new ArrayList<>();

        Set<String> SET = new HashSet<>(easy);

        for(String word : words){
            if(!SET.contains(word)){
                res.add(word);
            }
        }

        return res;
    }

    public static void main(String[] args) throws IOException {
        List<String> words = TxtUtils.readTxt("E:\\Myfile\\ANKI卡包\\文本\\words_list_in [35,INF] 2017-02-01_18h54m59s.txt","UTF-8");
        List<String> easy = TxtUtils.readTxt("E:\\Myfile\\ANKI卡包\\文本\\easy.txt","UTF-8");

        List<String> res = Contrastor.dealwith(words,easy);
        TxtUtils.writeTxt("E:\\Myfile\\ANKI卡包\\文本\\target.txt",res,"UTF-8");
    }


}
