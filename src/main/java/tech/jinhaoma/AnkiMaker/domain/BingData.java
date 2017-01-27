package tech.jinhaoma.AnkiMaker.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Created by mjrt on 1/23/2017.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "BingData")
public class BingData {

    @Id
    private Long id;
    private String word;
    private String apsUs ;
    @Column(length = 4095)
    private String meanChinese;
}
