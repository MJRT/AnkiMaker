package tech.jinhaoma.AnkiMaker.WordMaker;
import tech.jinhaoma.AnkiMaker.common.Check;

import java.io.*;

import java.util.*;
/**
 * Created by mjrt on 1/9/2017.
 */
public class WordCounter {
    public static void main(String[] args) throws IOException {

        getRankedWord(20,9999999,"E:\\all.txt", "E:\\Myfile\\ANKI卡包\\文本\\rankedWord.txt");

    }
    static void getRankedWord(int lower,int upper,String inFile, String outFile) {

        Map<String,Integer> mp = new TreeMap<>();
        ArrayList<String> res = new ArrayList<>();
        try {
            FileInputStream fis = new FileInputStream(inFile);
            InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
            BufferedReader br = new BufferedReader(isr);
            String line = null;

            while ((line = br.readLine()) != null) {

                String[] s = line.split("[ .?!]");

                for(String word : s){
                    if(mp.get(Filter(word)) != null){
                        mp.put(Filter(word),mp.get(Filter(word))+1);
                    } else {
                        mp.put(Filter(word),1);
                    }

                }

            }
            Iterator iter = mp.keySet().iterator();
            while (iter.hasNext()) {
                String word = (String )iter.next();
                if(lower <= mp.get(word) && mp.get(word) <= upper){
                    res.add(word);
//                    System.out.println(word+"_"+mp.get(word));
                }
            }
            System.out.println(res.size());
            fis.close();
        } catch(Exception e){
            e.printStackTrace();
        }


        try {

            File out = new File(outFile);
            if (!out.exists())
                out.createNewFile();
            FileOutputStream fos = new FileOutputStream(out);
            OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");

            for(String rrr : res) {
                osw.write(rrr);
                osw.write("\r\n");
            }
            osw.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static String Filter(String str){
        int s = 0 , e = 0;
        char[] ch = str.toCharArray();
        for(int i = 0 ; i < ch.length; i++){
            if(Character.isLetter(ch[i])){
                s = i ;
                break;
            }
        }
        for(int i =  ch.length-1 ; i >= 0 ; i--){
            if(Character.isLetter(ch[i])){
                e = i ;
                break;
            }
        }
        if(s < e+1 && e+1 < str.length() && e-s > 1){

            return str.substring(s,e+1);
        }   else {
            return "".toString();
        }
    }
}
