package tech.jinhaoma.AnkiMaker.bean;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created by mjrt on 1/17/2017.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaiduData {
    private String from;
    private String to;
    private List<BaiduResult> trans_result;

}
