package tech.jinhaoma.AnkiMaker.search;


import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import tech.jinhaoma.AnkiMaker.bean.BaiduData;
import tech.jinhaoma.AnkiMaker.common.BaiduTransApi;

import java.io.IOException;

import java.util.concurrent.Callable;

/**
 * Created by mjrt on 1/17/2017.
 */

@Log4j2
@AllArgsConstructor
@NoArgsConstructor
public class BaiduSearchOnline implements Callable<BaiduData> {

    private String word;

    private static final String APP_ID = "20160903000028063";
    private static final String SECURITY_KEY = "DOCPBODmVTKaLfuHIBoV";

    public static void main(String[] args) throws Exception {

        BaiduSearchOnline bso = new BaiduSearchOnline("eat");
        BaiduData bd = bso.call();
        System.out.println(bd.toString());
    }

    public  BaiduData Search(String word) throws IOException {
        BaiduTransApi api = new BaiduTransApi(APP_ID, SECURITY_KEY);

        String json = api.getTransResult(word, "auto", "zh");
        ObjectMapper mapper = new ObjectMapper();
        BaiduData bd = mapper.readValue(json,BaiduData.class);

        return bd;
    }

    @Override
    public BaiduData call() throws Exception {
        return Search(word);
    }
}
