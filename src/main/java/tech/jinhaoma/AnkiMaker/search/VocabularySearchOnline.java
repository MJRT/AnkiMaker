package tech.jinhaoma.AnkiMaker.search;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import tech.jinhaoma.AnkiMaker.domain.VocabularyData;
import tech.jinhaoma.AnkiMaker.common.HtmlUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * Created by mjrt on 1/17/2017.
 */
@Log4j2
public class VocabularySearchOnline extends SearchOnline<VocabularyData>{

    private final static String url = "https://www.vocabulary.com/dictionary/";

    public VocabularySearchOnline(String word) {
        super(word);
    }
    public VocabularySearchOnline() {
        super();
    }
    public  VocabularyData Search(String word) throws IOException {

        Document doc = Jsoup.connect(url+word).userAgent(HtmlUtils.userAgent).get();

        Element originWord = doc.getElementsByClass("dynamictext").first();
        if(originWord == null){
            String t = word.substring(0,1).toUpperCase() + word.substring(1,word.length());
            if(t.substring(0,1).equals(word.substring(0,1))){
                return null;
            }
            VocabularyData result = Search(t);

            if(result == null){
                log.error("\"" + word + "\" " + "does not exist.");
            } else {
                word = t;
            }

            return result;
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

                    Element  wordclass = t.getElementsByClass("anchor").get(0);
                    String tmp = wordclass.text();
                    switch (tmp){
                        case"n"   : wordclass.addClass("pos_n");break;
                        case"v"   : wordclass.addClass("pos_v");break;
                        case"adj" : wordclass.addClass("pos_a");break;
                        case"adv" : wordclass.addClass("pos_r");break;
                    }
                    wordclass.appendText(".");
                    wordMean.append(t.getElementsByClass("definition").first().html());
                    wordMean.append("<br>");

                    Elements examples = t.getElementsByClass("example");
                    if(examples == null) {
                        stringBuffer.append("");
                    } else {
                        for(Element example : examples){
                            stringBuffer.append(example.html());
                            stringBuffer.append("<br>");
                        }
                    }
                }
                meanTemp.add(wordMean.toString());
                sentenceTemp.add(HtmlUtils.removeLineFeeds(stringBuffer.toString()));
            }

        }

        VocabularyData result = new VocabularyData();
        result.setWord(originWord.text());
        result.setShortExplain(shortExplain == null ? "" : shortExplain.text());
        result.setLongExplain(longExplain == null ? "" : longExplain.text());
        result.setMean(meanTemp);
        result.setSentence(sentenceTemp);
        return result;
    }

    @Override
    public VocabularyData call() throws Exception {
        return Search(word);
    }
    public static void main(String[] args) throws IOException {
        System.out.println(new VocabularySearchOnline().Search("mediterranean").toString());;
    }

}
