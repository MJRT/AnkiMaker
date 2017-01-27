package tech.jinhaoma.AnkiMaker.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;

import java.util.List;

/**
 * Created by mjrt on 1/17/2017.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "BaiduData")
public class BaiduData {
    @Id
    private long id;

    @Column(name = "oriLanguage")
    private String from;
    @Column(name = "tranLanguage")
    private String to;

    private String word;
    @Column(length = 4095)
    private String mean;

    private List<BaiduResult> trans_result;
}
