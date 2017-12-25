package tech.jinhaoma.AnkiMaker.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created by mjrt on 12/25/2017.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BingThirdPartData {
    private String word;
    private BingThirdPartPronunciation pronunciation;
    private List<BingThirdPartMean> defs;
    private List<BingThirdPartSentence> sams;
}



