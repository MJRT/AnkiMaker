package tech.jinhaoma.AnkiMaker.api;

import lombok.*;

import java.io.IOException;
import java.util.concurrent.Callable;

/**
 * Created by mjrt on 1/28/2017.
 */

@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
public abstract class Api<Data>  implements Callable<Data> {

    protected String word;

    public Data Search(String word) throws IOException {
        return null;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    @Override
    public Data call() throws Exception {
        return Search(word);
    }
}
