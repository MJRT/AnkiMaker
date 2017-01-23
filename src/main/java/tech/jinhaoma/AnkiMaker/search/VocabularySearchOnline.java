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
import tech.jinhaoma.AnkiMaker.common.HtmlUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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
        if(originWord == null){
            log.error("\"" + word + "\" " + "does not exist.");
            return null;
        }


        Element shortExplain = doc.getElementsByClass("short").first();
        Element longExplain = doc.getElementsByClass("long").first();

        List<String> meanTemp = new ArrayList<>();
        List<String> sentenceTemp = new ArrayList<>();

        Elements group = doc.getElementsByClass("group");
        for(Element tran : group){
            Elements ordinal = tran.getElementsByClass("ordinal");
            for(Element mean : ordinal){
                Elements sense = mean.getElementsByClass("sense");

                StringBuffer wordMean = new StringBuffer();
                StringBuffer stringBuffer = new StringBuffer();

                for(Element t : sense){
                    wordMean.append(t.getElementsByClass("definition").first().html());
                    wordMean.append("<br>");

                    Elements example = t.getElementsByClass("example");
                    if(example == null) {
                        stringBuffer.append("");
                    } else {
                        for(Element sentence : example){
                            stringBuffer.append(sentence.html());
                            stringBuffer.append("<br>");
                        }
                    }
                }
                meanTemp.add(wordMean.toString());
                sentenceTemp.add(HtmlUtils.removeLineFeeds(stringBuffer.toString()));
            }

        }

        VocabularyData result = new VocabularyData(originWord.text(),shortExplain.text(),longExplain.text(),meanTemp,sentenceTemp);
        System.out.println(result.toString());
        return result;
    }

    @Override
    public VocabularyData call() throws Exception {
        return Search(word);
    }


}
