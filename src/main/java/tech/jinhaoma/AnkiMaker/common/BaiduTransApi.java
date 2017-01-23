package tech.jinhaoma.AnkiMaker.common;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class BaiduTransApi {
    private static final String TRANS_API_HOST = "http://api.fanyi.baidu.com/api/trans/vip/translate";

    private String appid;
    private String securityKey;

    public BaiduTransApi(String appid, String securityKey) {
        this.appid = appid;
        this.securityKey = securityKey;
    }



}
