package tech.jinhaoma.AnkiMaker.search;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
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
        Search("eat");
    }

    public static VocabularyData Search(String word) throws IOException {

        Document doc = Jsoup.connect(url+word).get();

        Element originWord = doc.getElementsByClass("dynamictext").first();
        Element shortExplain = doc.getElementsByClass("short").first();
        Element longExplain = doc.getElementsByClass("long").first();
        Elements group = doc.getElementsByClass("group");


        StringBuffer sb = new StringBuffer();
        for(Element tran : group){
            Elements mean = tran.getElementsByClass("definition");
            sb.append(tran.getElementsByClass("groupNumber").text()+".");
            for (Element t : mean){

            }
        }

        if(originWord == null)
            return null;

        VocabularyData result = new VocabularyData();
        result.setMean(originWord.text());
        result.setShortExplain(shortExplain.text());
        result.setLongExplain(longExplain.text());



        return result;


    }

    @Override
    public VocabularyData call() throws Exception {
        return Search(word);
    }


}
