package tech.jinhaoma.AnkiMaker.utils;

import java.util.HashSet;

/**
 * Created by mjrt on 2/20/2017.
 */
public class Constants {

    public static final String NEWCODER_NEWS = "https://www.nowcoder.com/discuss?order=3&type=0";
    public static final String NEWCODER_INTERVIEW = "https://www.nowcoder.com/discuss?type=2&order=3";
    public static final String HEU_JW = "http://ugs.hrbeu.edu.cn/2821/list.htm";
    public static final String GSOC = "https://developers.google.cn/open-source/gsoc/timeline";

    public static HashSet<String> nowMarks ;
    static {
        nowMarks = new HashSet<>();
    }

    public static final long MILLISECOND_ONE_DAY = 86400000;
    public static final long MILLISECOND_ONE_HOUR = 3600000;
    public static final long MILLISECOND_ONE_MINUTE = 60000;
    public static final long MILLISECOND_ONE_SECOND = 1000;


    static String[] userAgents = {
            //IE
            "Mozilla/5.0 (compatible; WOW64; MSIE 9.0; Windows NT 6.2)",
            "Mozilla/5.0 (compatible; WOW64; MSIE 10.0; Windows NT 6.2)",
            "Mozilla/5.0 (compatible; WOW64; MSIE 11.0; Windows NT 6.2)",
            "Mozilla/5.0 (compatible; WOW64; MSIE 12.0; Windows NT 6.2)",
            //Chrome
            "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.114 Safari/537.36 Vivaldi/1.9.818.50",
            "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.75 Safari/537.36",
            //Mx
            "Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Maxthon/4.4.8.1000 Chrome/30.0.1599.101 Safari/537.36",
    };
}
