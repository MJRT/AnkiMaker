package tech.jinhaoma.AnkiMaker.search;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import tech.jinhaoma.AnkiMaker.bean.VocabularyData;

import java.io.IOException;
import java.util.concurrent.Callable;

/**
 * Created by mjrt on 1/17/2017.
 */
@Log4j2
@AllArgsConstructor
@NoArgsConstructor
public class VocabularySearchOnline implements Callable<VocabularyData>{

    private final static String url = "https://www.vocabulary.com/dictionary/";

    private String word;

    public static void main(String[] args) throws IOException {
        Search("eates");
    }

    public static VocabularyData Search(String word) throws IOException {

        Document doc = Jsoup.connect(url+word).get();

        Element originWord = doc.getElementsByClass("dynamictext").first();
        Element shortExplain = doc.getElementsByClass("short").first();
        Element longExplain = doc.getElementsByClass("long").first();


        if(originWord == null){
            return null;
        } else {
            return new VocabularyData(originWord.text(),
                                      shortExplain.text(),
                                      longExplain.text());
        }

    }

    @Override
    public VocabularyData call() throws Exception {
        return Search(word);
    }
}
