package tech.jinhaoma.AnkiMaker.common;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mjrt on 1/18/2017.
 */
public class TxtOperator {
    public static ArrayList<String> readTxt(String path,  String encode) throws IOException {
        FileInputStream fis = new FileInputStream(path);
        InputStreamReader isr = new InputStreamReader(fis, encode);
        BufferedReader br = new BufferedReader(isr);
        String line = null;
        ArrayList<String> result = new ArrayList<>();
        while ((line = br.readLine()) != null) {
            result.add(line);
        }
        br.close();
        isr.close();
        fis.close();
        return result;
    }

    public static boolean writeTxt(String path, List<String> reslut, String encode) throws IOException {
        File out = new File(path);
        if (!out.exists())
            out.createNewFile();
        FileOutputStream fos = new FileOutputStream(out);
        OutputStreamWriter osw = new OutputStreamWriter(fos, encode);

        for(String rrr : reslut) {
            osw.write(rrr);
            osw.write("\r\n");
        }
        osw.flush();
        osw.close();
        fos.close();
        return true;
    }

    public static boolean appedTxt(String path, String reslut, String encode) throws IOException {
        File out = new File(path);
        if (!out.exists())
            out.createNewFile();
        FileOutputStream fos = new FileOutputStream(out,true);
        OutputStreamWriter osw = new OutputStreamWriter(fos, encode);


        osw.write(reslut);
        osw.write("\r\n");

        osw.flush();
        osw.close();
        fos.close();
        return true;
    }
}

