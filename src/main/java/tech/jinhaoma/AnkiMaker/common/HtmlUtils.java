package tech.jinhaoma.AnkiMaker.common;

/**
 * Created by mjrt on 1/19/2017.
 */
public class HtmlUtils {
    public static final String userAgent = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/27.0.1453.94 Safari/537.36";

    public static String addStrong(String text){
        return "<strong>" + text + "</strong>";
    }

    public static String removeLineFeeds(String text){
        String[] tmp = text.split("\n");
        StringBuffer result = new StringBuffer();
        for (String t : tmp){
            result.append(t);
        }
        return result.toString();
    }
}
