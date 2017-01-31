package tech.jinhaoma.AnkiMaker.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created by mjrt on 1/31/2017.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardsTemp {

    List<String> cards;
    List<Integer> rank;
    List<String> exception;

}
