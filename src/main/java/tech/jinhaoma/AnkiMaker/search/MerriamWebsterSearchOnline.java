package tech.jinhaoma.AnkiMaker.search;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.log4j.Log4j2;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import java.io.IOException;


/**
 * Created by mjrt on 1/17/2017.
 */
@Log4j2
@Data
@AllArgsConstructor
public class MerriamWebsterSearchOnline {

    private static final String url = "http://www.dictionaryapi.com/api/v1/references/collegiate/xml/";
    private static final String key = "?key=a19d0c7b-6815-4cec-93f2-2020afe15c24";

    private String word;
    private long waitTime;

    //暂时只处理单词格式
    public static String Search(String word) throws IOException {

        Document doc = Jsoup.connect(url+word+key).ignoreContentType(true).get();
        Element originWord = doc.select("ew").first();
        Element splitWord = doc.select("hw").first();

        if(originWord == null){
            log.error("\"" + word + "\" " + "does not exist.");
            return "".toString();
        }else {
            return originWord.text();
        }

    }

    public static void main(String[] args) throws IOException {

        System.out.println(Search("amount"));
    }


}
