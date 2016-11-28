package tech.jinhaoma.AnkiMaker.bean;

import java.io.Serializable;

/**
 * Created by mjrt on 11/28/2016.
 */
public class EnWord implements Serializable{

    private static final long serialVersionUID = 1L;

    private String English;
    private String IPA;
    private String ChineseOfOnline;
    private String SentenceOfEnglish;
    private String SentenceOfChinese;
    private String Sentence;
    private String ChineseOfOxford;
    private String Vocab;

    public EnWord() {
    }

    @Override
    public String toString() {
        return "EnWord{" +
                "English='" + English + '\'' +
                ", IPA='" + IPA + '\'' +
                ", ChineseOfOnline='" + ChineseOfOnline + '\'' +
                ", SentenceOfEnglish='" + SentenceOfEnglish + '\'' +
                ", SentenceOfChinese='" + SentenceOfChinese + '\'' +
                ", Sentence='" + Sentence + '\'' +
                ", ChineseOfOxford='" + ChineseOfOxford + '\'' +
                ", Vocab='" + Vocab + '\'' +
                '}';
    }

    public EnWord(String english, String IPA, String chineseOfOnline, String sentenceOfEnglish, String sentenceOfChinese, String Sentence, String chineseOfOxford, String vocab) {
        English = english;
        this.IPA = IPA;
        ChineseOfOnline = chineseOfOnline;
        SentenceOfEnglish = sentenceOfEnglish;
        SentenceOfChinese = sentenceOfChinese;
        this.Sentence = Sentence;
        ChineseOfOxford = chineseOfOxford;
        Vocab = vocab;
    }

    public void setEnglish(String english) {
        English = english;
    }

    public void setIPA(String IPA) {
        this.IPA = IPA;
    }

    public void setChineseOfOnline(String chineseOfOnline) {
        ChineseOfOnline = chineseOfOnline;
    }

    public void setSentenceOfEnglish(String sentenceOfEnglish) {
        SentenceOfEnglish = sentenceOfEnglish;
    }

    public void setSentenceOfChinese(String sentenceOfChinese) {
        SentenceOfChinese = sentenceOfChinese;
    }

    public void setSentence(String Sentence) {
        this.Sentence = Sentence;
    }

    public void setChineseOfOxford(String chineseOfOxford) {
        ChineseOfOxford = chineseOfOxford;
    }

    public void setVocab(String vocab) {
        Vocab = vocab;
    }

    public String getEnglish() {
        return English;
    }

    public String getIPA() {
        return IPA;
    }

    public String getChineseOfOnline() {
        return ChineseOfOnline;
    }

    public String getSentenceOfEnglish() {
        return SentenceOfEnglish;
    }

    public String getSentenceOfChinese() {
        return SentenceOfChinese;
    }

    public String getSentence() {
        return Sentence;
    }

    public String getChineseOfOxford() {
        return ChineseOfOxford;
    }

    public String getVocab() {
        return Vocab;
    }
}
