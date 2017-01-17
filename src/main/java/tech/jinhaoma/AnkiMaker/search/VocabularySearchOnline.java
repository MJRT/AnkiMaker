package tech.jinhaoma.AnkiMaker.search;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import tech.jinhaoma.AnkiMaker.bean.VocabularyData;

import java.io.IOException;

/**
 * Created by mjrt on 1/17/2017.
 */
public class VocabularySearchOnline {

    static String url = "https://www.vocabulary.com/dictionary/";

    public static void main(String[] args) throws IOException {
        Search("eates");
    }

    public static void Search(String word) throws IOException {

        Document doc = Jsoup.connect(url+word).get();
        VocabularyData vd = new VocabularyData();

        Element shortExplain = doc.getElementsByClass("short").first();
        Element longExplain = doc.getElementsByClass("long").first();

        Element cz = doc.getElementsByClass("didyoumean").first();
        Element wd = doc.getElementsByClass("dynamictext").first();

        System.out.println(wd == null);

        System.out.println(wd.text());

    }

}
