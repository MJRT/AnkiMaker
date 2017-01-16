package tech.jinhaoma.AnkiMaker.common;

import static java.lang.Character.isLetter;

public class Check {
	private static final boolean isChinese(char c) {  
	    Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);  
	    if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS  
	            || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS  
	            || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A  
	            || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION  
	            || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION  
	            || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {  
	        return true;  
	    }  
	    return false;  
	}  

	public static final boolean isChinese(String strName) {  
	    char[] ch = strName.toCharArray();
		for (char c : ch) {
			if (isChinese(c)) {
	            return false;
	        }  
	    }
	    return true;
	}



	public static final boolean isEnglishWord(String strName) {
		char[] ch = strName.toCharArray();
		for (int i = 0 ; i < ch.length ; i++) {
			if (!(isLetter(ch[i]) || (	   ch[i] == '-'
										&& ch[i-1>=0?i-1:i+1] != '-'
										&& ch[i+1<ch.length?i+1:i-1] != '-')) ) {
				return false;
			}
		}
		return true;
	}
}

