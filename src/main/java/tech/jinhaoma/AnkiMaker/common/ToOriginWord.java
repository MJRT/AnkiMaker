package tech.jinhaoma.AnkiMaker.common;

/**
 * Created by mjrt on 1/17/2017.
 */
public class ToOriginWord {

    public String Singularizer(String word){

        String _1 = word.substring(word.length()-1);
        String _2 = word.substring(word.length()-2);
        String _3 = word.substring(word.length()-3);
        String _4 = word.substring(word.length()-4);


        if(_3.equals("ies")){

        }else if (_3.equals("ves")){

        }else if (_2.equals("es")){

        }else if (_1.equals("s")){

        }

        String res = word;








        return res;
    }


    public static void main(String[] args) {
        String s = "1234";
        System.out.println(s.substring(s.length()-3));
    }
}
