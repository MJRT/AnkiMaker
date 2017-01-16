package tech.jinhaoma.AnkiMaker.bean;

import java.io.Serializable;

/**
 * Created by mjrt on 11/28/2016.
 */
public class EnWord implements Serializable{

    private static final long serialVersionUID = 1L;

    private String English;
    private String Aps;
    private String ChineseOfOnline;
    private String SentenceOfEnglish;
    private String SentenceOfChinese;
    private String ApsOfOxford;
    private String ChineseOfOxford;
    private String VocabSimple;
    private String VocabExtend;
    private String Star;
    private String MeanOfCollins;

    private String Sentence;
    private String MeanOfChinses;
    private String MeanOfEnglish;

    public EnWord() {
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getEnglish() {
        return English;
    }

    public void setEnglish(String english) {
        English = english;
    }

    public String getAps() {
        return Aps;
    }

    public void setAps(String aps) {
        Aps = aps;
    }

    public String getChineseOfOnline() {
        return ChineseOfOnline;
    }

    public void setChineseOfOnline(String chineseOfOnline) {
        ChineseOfOnline = chineseOfOnline;
    }

    public String getSentenceOfEnglish() {
        return SentenceOfEnglish;
    }

    public void setSentenceOfEnglish(String sentenceOfEnglish) {
        SentenceOfEnglish = sentenceOfEnglish;
    }

    public String getSentenceOfChinese() {
        return SentenceOfChinese;
    }

    public void setSentenceOfChinese(String sentenceOfChinese) {
        SentenceOfChinese = sentenceOfChinese;
    }

    public String getApsOfOxford() {
        return ApsOfOxford;
    }

    public void setApsOfOxford(String apsOfOxford) {
        ApsOfOxford = apsOfOxford;
    }

    public String getChineseOfOxford() {
        return ChineseOfOxford;
    }

    public void setChineseOfOxford(String chineseOfOxford) {
        ChineseOfOxford = chineseOfOxford;
    }

    public String getVocabSimple() {
        return VocabSimple;
    }

    public void setVocabSimple(String vocabSimple) {
        VocabSimple = vocabSimple;
    }

    public String getVocabExtend() {
        return VocabExtend;
    }

    public void setVocabExtend(String vocabExtend) {
        VocabExtend = vocabExtend;
    }

    public String getStar() {
        return Star;
    }

    public void setStar(String star) {
        Star = star;
    }

    public String getMeanOfCollins() {
        return MeanOfCollins;
    }

    public void setMeanOfCollins(String meanOfCollins) {
        MeanOfCollins = meanOfCollins;
    }

    public String getSentence() {
        return Sentence;
    }

    public void setSentence(String sentence) {
        Sentence = sentence;
    }

    public String getMeanOfChinses() {
        return MeanOfChinses;
    }

    public void setMeanOfChinses(String meanOfChinses) {
        MeanOfChinses = meanOfChinses;
    }

    public String getMeanOfEnglish() {
        return MeanOfEnglish;
    }

    public void setMeanOfEnglish(String meanOfEnglish) {
        MeanOfEnglish = meanOfEnglish;
    }
}
