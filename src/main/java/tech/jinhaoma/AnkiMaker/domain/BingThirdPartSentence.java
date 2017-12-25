package tech.jinhaoma.AnkiMaker.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by mjrt on 12/25/2017.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BingThirdPartSentence {
    private String eng;
    private String chn;
    private String mp3Url;
    private String mp4Url;
}
