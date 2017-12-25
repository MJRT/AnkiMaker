package tech.jinhaoma.AnkiMaker.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import tech.jinhaoma.AnkiMaker.domain.BingData;
import tech.jinhaoma.AnkiMaker.domain.BingThirdPartData;
import tech.jinhaoma.AnkiMaker.domain.BingThirdPartMean;

import java.io.IOException;
import java.util.List;

/**
 * Created by mjrt on 12/25/2017.
 */
@Log4j2
public class BingThirdpPartApi extends Api<BingData>{
    static final private String host = "http://xtk.azurewebsites.net/BingDictService.aspx?Word=";

    public BingThirdpPartApi(){super();}
    public BingThirdpPartApi(String word) {
        super(word);
    }

    public static void main(String[] args) throws Exception {
        System.out.println(new BingThirdpPartApi("room").call().toString());
    }

    @Override
    public BingData Search(String word) throws InterruptedException, IOException {

        ObjectMapper mapper = new ObjectMapper();
        Document doc = Jsoup.connect(host+word).ignoreContentType(true).get();


        BingThirdPartData b3data = mapper.readValue(doc.text(),BingThirdPartData.class);
        BingData bingData = BingData.builder()
                .word(word)
                .apsUs(b3data.getPronunciation().getAmE())
                .meanChinese(formatMean(b3data.getDefs()))
                .build();
        return bingData;
    }

    private String formatMean(List<BingThirdPartMean> means){
        StringBuilder builder = new StringBuilder();

        for(BingThirdPartMean mean : means){
            builder.append(
                    String.format("<li><span>%s</span><span><span>%s</span></span></li>"
                            ,mean.getPos(),mean.getDef())
            );
        }

        return builder.toString();
    }


}
