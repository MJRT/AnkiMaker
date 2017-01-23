package tech.jinhaoma.AnkiMaker.search;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import tech.jinhaoma.AnkiMaker.bean.BingData;

import java.io.IOException;
import java.util.concurrent.Callable;

/**
 * Created by mjrt on 1/23/2017.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BingSearchOnline implements Callable<BingData>{
    static final private String host = "http://cn.bing.com/dict/search?q=";

    private String word;

    public static void main(String[] args) throws Exception {
        System.out.println(new BingSearchOnline("eat").call());
    }

    /*
    暂只处理中文意思与美语音标
     */
    public BingData Search(String word) throws IOException {
        Document doc = Jsoup.connect(host+word).get();

        Element hd_prUS = doc.getElementsByClass("hd_prUS").get(0);
        Element ul = doc.select("ul").get(1);

        String apsUs = hd_prUS == null ? "" : hd_prUS.html().substring(1);
        String meanChinese = ul == null ? "" : ul.html();

        BingData bd = new BingData();
        bd.setApsUs(apsUs);
        bd.setMeanChinese(meanChinese);

        return bd;
    }

    @Override
    public BingData call() throws Exception {
        return Search(word);
    }
}
