package tech.jinhaoma.AnkiMaker.api;



import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import tech.jinhaoma.AnkiMaker.domain.MerriamWebsterData;
import tech.jinhaoma.AnkiMaker.domain.WordMap;
import tech.jinhaoma.AnkiMaker.domain.WordMapRepository;

import java.io.IOException;


/**
 * Created by mjrt on 1/17/2017.
 */
@Log4j2
public class MerriamWebsterApi extends Api<MerriamWebsterData> {

    private static final String url = "http://www.dictionaryapi.com/api/v1/references/collegiate/xml/";
    private static final String key = "?key=a19d0c7b-6815-4cec-93f2-2020afe15c24";

    public MerriamWebsterApi(){super();}
    public MerriamWebsterApi(String word) {super(word);}

    public static void main(String[] args) throws IOException, InterruptedException {

        System.out.println(new MerriamWebsterApi().Search("easily"));
    }

    /*
    暂时只处理单词格式
     */
    public MerriamWebsterData Search(String word) throws InterruptedException {

        Document doc = null;
        try {
            doc = Jsoup.connect(url+word+key).ignoreContentType(true).get();
        } catch (IOException e) {
            Thread.sleep(600);
            try {
                doc = Jsoup.connect(url+word+key).ignoreContentType(true).get();
            } catch (IOException e1) {
                log.error(word +" ->->->->->-> "+e1.toString());
            }
        }
        Element originWord = doc.select("ew").first();
        Element splitWord = doc.select("hw").first();

        if(originWord == null){
            log.error("\"" + word + "\" " + "does not exist.");
            return null;
        }

        MerriamWebsterData result = new MerriamWebsterData();
        result.setWord(originWord.text());
        result.setSplitWord(splitWord == null?originWord.text():ReplaceStarWithDash(splitWord.text()));
        return result;

    }

    private String ReplaceStarWithDash(String line){
        String[] s = line.split("[*]");
        if(s.length > 1){
            StringBuffer buffer = new StringBuffer();
            for(String t : s){
                buffer.append(t);
                buffer.append("-");
            }
            buffer.deleteCharAt(buffer.length()-1);
            return buffer.toString();
        }
        return line;
    }

    @Override
    public MerriamWebsterData call() throws Exception {
        return Search(word);
    }
}
