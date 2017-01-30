package tech.jinhaoma.AnkiMaker.search;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import tech.jinhaoma.AnkiMaker.common.HtmlUtils;
import tech.jinhaoma.AnkiMaker.domain.BingData;

import java.io.IOException;
import java.util.concurrent.Callable;

/**
 * Created by mjrt on 1/23/2017.
 */

public class BingSearchOnline extends SearchOnline<BingData>{
    static final private String host = "http://cn.bing.com/dict/search?q=";

    public BingSearchOnline(String word) {
        super(word);
    }

    public static void main(String[] args) throws Exception {
        System.out.println(new BingSearchOnline("room").call());
    }

    /*
    暂只处理中文意思与美语音标
     */
    @Override
    public BingData Search(String word) throws IOException {
        Document doc = Jsoup.connect(host+word).get();

        Element originWord = doc.getElementById("headword");
        if (originWord == null){
            return null;
        }
        Element change = doc.getElementsByClass("pos web").first().text("Web.");

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
