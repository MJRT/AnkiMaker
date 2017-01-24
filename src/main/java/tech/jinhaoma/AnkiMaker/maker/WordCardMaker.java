package tech.jinhaoma.AnkiMaker.maker;

import tech.jinhaoma.AnkiMaker.common.TxtUtils;
import tech.jinhaoma.AnkiMaker.model.BingData;
import tech.jinhaoma.AnkiMaker.model.MerriamWebsterData;
import tech.jinhaoma.AnkiMaker.model.VocabularyData;
import tech.jinhaoma.AnkiMaker.model.WordCard;
import tech.jinhaoma.AnkiMaker.task.BingTask;
import tech.jinhaoma.AnkiMaker.task.MerriamWebsterTask;
import tech.jinhaoma.AnkiMaker.task.VocabularyTask;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by mjrt on 1/24/2017.
 */
public class WordCardMaker {

    public List<String> getCard(List<String> words) throws ExecutionException, InterruptedException, IOException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        BingTask bingTask = new BingTask();
        VocabularyTask vocabularyTask = new VocabularyTask(300);
        MerriamWebsterTask merriamWebsterTask = new MerriamWebsterTask(100);

        List<BingData> bingDatas = bingTask.asyncBingTask(words);
        System.out.println("bing ok");
        List<VocabularyData> vocabularyDatas = vocabularyTask.asyncVocabularyTask(words);
        System.out.println("vocabulary ok");
        List<MerriamWebsterData> merriamWebsterDatas = merriamWebsterTask.asyncMerriamWebsterTask(words);
        System.out.println("MerriamWebster ok");

        List<WordCard> cards = new ArrayList<>();
        List<String> exception = new ArrayList<>();

        for(int i = 0 ; i < vocabularyDatas.size() ; i++){
            if(vocabularyDatas.get(i) == null){
                exception.add(words.get(i));
            } else {
                List<String> means = vocabularyDatas.get(i).getMean();
                for (int j = 0 ; j < means.size() ; j++){
                    WordCard card = new WordCard();
                    card.setWord(words.get(i));
                    card.setAps(bingDatas.get(i).getApsUs());
                    card.setMeanChinses(bingDatas.get(i).getMeanChinese());
                    card.setSplitWord(merriamWebsterDatas.get(i).getSplitWord());
                    card.setMeanEnglish(vocabularyDatas.get(i).getMean().get(j));
                    card.setSentence(vocabularyDatas.get(i).getSentence().get(j));
                    card.setExplain(vocabularyDatas.get(i).getShortExplain()+"<br>"+
                                    vocabularyDatas.get(i).getLongExplain());
                    cards.add(card);
                }
            }
        }

        List<String> result = new ArrayList<>();
        for(WordCard card :cards){
            result.add(card.toCard());
        }

        TxtUtils.writeTxt("E:\\card.txt",exception,"UTF-8");
        System.out.println("made " + cards.size()+ " cards");
        return result;
    }

    public static void main(String[] args) throws IOException, NoSuchMethodException, InterruptedException, ExecutionException, InstantiationException, IllegalAccessException, InvocationTargetException {

        String inFile = "E:\\Myfile\\ANKI卡包\\文本\\rankedWord.txt";
        String outFile = "E:\\card.txt";

        List<String> words = TxtUtils.readTxt(inFile,"UTF-8");
        WordCardMaker wordCardMaker = new WordCardMaker();
        List<String> cards = wordCardMaker.getCard(words);
        TxtUtils.writeTxt(outFile,cards ,"UTF-8");
    }
}
