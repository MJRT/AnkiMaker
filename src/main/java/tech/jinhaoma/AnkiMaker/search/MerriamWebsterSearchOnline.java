package tech.jinhaoma.AnkiMaker.search;



import lombok.extern.log4j.Log4j2;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import tech.jinhaoma.AnkiMaker.domain.MerriamWebsterData;

import java.io.IOException;


/**
 * Created by mjrt on 1/17/2017.
 */
@Log4j2

public class MerriamWebsterSearchOnline extends SearchOnline<MerriamWebsterData>{

    private static final String url = "http://www.dictionaryapi.com/api/v1/references/collegiate/xml/";
    private static final String key = "?key=a19d0c7b-6815-4cec-93f2-2020afe15c24";

    public MerriamWebsterSearchOnline(){super();}
    public MerriamWebsterSearchOnline(String word) {super(word);}

    public static void main(String[] args) throws IOException {

        System.out.println(new MerriamWebsterSearchOnline().Search("easily"));
    }

    /*
    暂时只处理单词格式
     */
    public MerriamWebsterData Search(String word) throws IOException {

        Document doc = Jsoup.connect(url+word+key).ignoreContentType(true).get();
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
        StringBuffer buffer = new StringBuffer();
        for(String t : s){
            buffer.append(t);
            buffer.append("-");
        }
        buffer.deleteCharAt(buffer.length()-1);
        return buffer.toString();
    }

    @Override
    public MerriamWebsterData call() throws Exception {
        return Search(word);
    }
}
