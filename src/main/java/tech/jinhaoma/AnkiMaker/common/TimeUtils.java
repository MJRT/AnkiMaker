package tech.jinhaoma.AnkiMaker.common;

import javax.xml.crypto.Data;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by mjrt on 2/1/2017.
 */
public class TimeUtils {
    public static String getNowTime() {
        DateFormat dateFormat =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String time = dateFormat.format(date);
        String[] s = time.split(" ");
        StringBuffer buffer = new StringBuffer();
        for (String ss : s){
            buffer.append("_");
            buffer.append(ss);
        }

        time = buffer.toString();
        buffer.setLength(0);
        s = time.split(":");
        for (int i = 0 ; i < s.length ; i++){

            buffer.append(s[i]);
            switch (i){
                case 0 : buffer.append("h");break;
                case 1 : buffer.append("m");break;
                case 2 : buffer.append("s");break;
            }
        }

        return buffer.toString().substring(1);
    }

    public static void main(String[] args) {
        System.out.println(TimeUtils.getNowTime());
    }
}
