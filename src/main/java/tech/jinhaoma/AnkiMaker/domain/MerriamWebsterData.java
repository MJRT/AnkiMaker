package tech.jinhaoma.AnkiMaker.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * Created by mjrt on 1/18/2017.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "MerriamWebsterData")
public class MerriamWebsterData {
    @Id
    @GeneratedValue
    private Long id;
    private String word;
    private String splitWord;
}
