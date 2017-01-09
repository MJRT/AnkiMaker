package tech.jinhaoma.AnkiMaker.WordMaker;
import java.io.*;

import java.util.*;
/**
 * Created by mjrt on 12/26/2016.
 */
public class TextToCloze {

    public static void main(String[] args) throws IOException {

        getCloze("E:\\cloze_o.txt", "E:\\Myfile\\ANKI卡包\\文本\\Cloze.txt");

    }

    static void getCloze(String inFile, String outFile) {
        ArrayList<String> res = new ArrayList<>();
        try {
            FileInputStream fis = new FileInputStream(inFile);
            InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
            BufferedReader br = new BufferedReader(isr);
            String line = null;

            while ((line = br.readLine()) != null) {

                String[] s = line.split("@");

                StringBuilder tmp = new StringBuilder();
                for (int i = 0; i < s.length; i++) {
                    if (i % 2 == 0) {
                        tmp.append(s[i]);
                    } else {
                        tmp.append(getMark(i, s[i]));
                    }
                }

                res.add(tmp.toString());
            }
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

    static String getMark(int i ,String str){
        final String clozeFront = "{{c", clozeMid = "::", clozeEnd = "}}";
        return clozeFront + (i+1)/2 + clozeMid + str + clozeEnd;
    }
}



