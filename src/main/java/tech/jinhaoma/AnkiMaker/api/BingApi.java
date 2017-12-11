package tech.jinhaoma.AnkiMaker.api;

import lombok.extern.log4j.Log4j2;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import tech.jinhaoma.AnkiMaker.utils.HtmlUtils;
import tech.jinhaoma.AnkiMaker.domain.BingData;
import tech.jinhaoma.AnkiMaker.domain.WordMapRepository;

import java.io.IOException;

/**
 * Created by mjrt on 1/23/2017.
 */

@Log4j2
public class BingApi extends Api<BingData> {
    static final private String host = "http://cn.bing.com/dict/search?q=";

    @Autowired
    private WordMapRepository repository;

    public BingApi(){super();}
    public BingApi(String word) {
        super(word);
    }

    public static void main(String[] args) throws Exception {
        System.out.println(new BingApi("room").call());
    }

    /*
    暂只处理中文意思与美语音标
     */
    @Override
    public BingData Search(String word) throws InterruptedException {

        Document doc = null;
        try {
            doc =Jsoup.connect(host+word).get();
        } catch (IOException e) {
            Thread.sleep(600);
            try {
                doc = Jsoup.connect(host+word).get();
            } catch (IOException e1) {
                log.error(word +" ->->->->->-> "+e1.toString());
            }
        }

        Element originWord = doc.getElementById("headword");
        if (originWord == null){
            log.error(word + " no search resulte");
            return null;
        }


        Element change = doc.getElementsByClass("pos web").first();
        if(change != null){
            change.text("Web.");
        } else {
            log.error(word + " no \" web \" mean");
        }

        Element hd_prUS = doc.getElementsByClass("hd_prUS").first();
        Element ul = doc.select("ul").get(1);

        String apsUs = hd_prUS == null ? "" : hd_prUS.html().substring(1);
        String meanChinese = ul == null ? "" : ul.html();

        BingData bd = new BingData();
        bd.setWord(originWord.text());
        bd.setApsUs(apsUs);
        bd.setMeanChinese(HtmlUtils.removeLineFeeds(meanChinese));

        return  bd;
    }

}
