package tech.jinhaoma.AnkiMaker.search;


import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

import tech.jinhaoma.AnkiMaker.bean.BaiduData;
import tech.jinhaoma.AnkiMaker.common.BaiduTransApi;
import tech.jinhaoma.AnkiMaker.common.HttpUtils;
import tech.jinhaoma.AnkiMaker.common.MD5;

import java.io.IOException;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

/**
 * Created by mjrt on 1/17/2017.
 */

@Log4j2
@Data
//@AllArgsConstructor
@NoArgsConstructor
public class BaiduSearchOnline implements Callable<BaiduData> {
    private static final String TRANS_API_HOST = "http://api.fanyi.baidu.com/api/trans/vip/translate";

    private static final String APP_ID = "20160903000028063";
    private static final String SECURITY_KEY = "DOCPBODmVTKaLfuHIBoV";

//    @Value("${baidu.id}")
//    private String APP_ID ;
//    @Value("@{baidu.key}")
//    private String SECURITY_KEY ;

    private String word;

    public BaiduSearchOnline(String word) {
        this.word = word;
    }

    public static void main(String[] args) throws Exception {

        BaiduSearchOnline bso = new BaiduSearchOnline("eat");
        BaiduData bd = bso.call();
        System.out.println(bd.toString());
    }

    public  BaiduData Search(String word) throws IOException {

        String json = getTransResult(word, "auto", "zh");
        ObjectMapper mapper = new ObjectMapper();
        BaiduData bd = mapper.readValue(json,BaiduData.class);

        return bd;
    }

    @Override
    public BaiduData call() throws Exception {
        return Search(word);
    }

    public String getTransResult(String query, String from, String to) throws UnsupportedEncodingException {
        Map<String, String> params = buildParams(query, from, to);
        return HttpUtils.get(TRANS_API_HOST, params);
    }

    private Map<String, String> buildParams(String query, String from, String to) throws UnsupportedEncodingException {
        Map<String, String> params = new HashMap<String, String>();
        params.put("q", query);
        params.put("from", from);
        params.put("to", to);

        params.put("appid", APP_ID);

        // 随机数
        String salt = String.valueOf(System.currentTimeMillis());
        params.put("salt", salt);

        // 签名
        String src = APP_ID + query + salt + SECURITY_KEY; // 加密前的原文
        params.put("sign", MD5.md5(src));

        return params;
    }


}
