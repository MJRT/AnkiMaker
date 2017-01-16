package tech.jinhaoma.AnkiMaker.WordMaker;
import tech.jinhaoma.AnkiMaker.bean.EnWord;

import java.io.*;

import java.util.*;

public class WordToAnkiTxt {

	public static void main(String[] args) throws IOException {

		getAnki("D:\\soft\\英语单词制卡工具6.0\\anki.txt",
				"E:\\Myfile\\ANKI卡包\\文本\\end.txt");
//				"E:\\Myfile\\EveryDayWord\\EveryDayWordCard.txt");
	}
	
	static void getAnki(String inFile,String outFile){
		ArrayList<EnWord> res = new ArrayList<>();
	    try { 
	        FileInputStream fis = new FileInputStream(inFile); 
	        InputStreamReader isr = new InputStreamReader(fis, "UTF-8"); 
	        BufferedReader br = new BufferedReader(isr); 
	        String line = null;
	        int idx = 1;
	        while ((line = br.readLine()) != null) {
	        	if(idx < 3)
				{
					idx++;
					continue;
				}
	            String[] s = line.split("\t");
	        	EnWord t = new EnWord();
				if(s.length > 0)	t.setEnglish(s[0]) ;		   else t.setEnglish("");
				if(s.length > 1)	t.setAps(s[1]); 			   else t.setAps("");
				if(s.length > 2)	t.setChineseOfOnline(s[2]) ;   else t.setChineseOfOnline("");
				if(s.length > 3)	t.setSentenceOfEnglish(s[3]) ; else t.setSentenceOfEnglish("");
				if(s.length > 4)	t.setSentenceOfChinese(s[4]) ; else t.setSentenceOfChinese("");
				if(s.length > 5)	t.setApsOfOxford(s[5]) ;	   else t.setApsOfOxford("");
				if(s.length > 6)	t.setChineseOfOxford(s[6]) ;   else t.setChineseOfOxford("");
				if(s.length > 7)	t.setVocabSimple(s[7]) ; 	   else t.setVocabSimple("");
				if(s.length > 8)	t.setVocabExtend(s[8]) ; 	   else t.setVocabExtend("");
				if(s.length > 9)	t.setStar(s[9]) ; 	 		   else t.setStar("");
				if(s.length > 10)	t.setMeanOfCollins(s[10]) ;    else t.setMeanOfCollins("");

				res.add(DealWith(t));
			}
			fis.close();
	    } catch (Exception e) { 
	        e.printStackTrace(); 
	    }


		try {
//			String Time = new Date().toString() + ".txt";
//			String Name = new String(Time.getBytes("unicode"));
//			System.out.println(""+Name.toCharArray());

			File out = new File(outFile);
	    	if(!out.exists())
	    		out.createNewFile();
	        FileOutputStream fos = new FileOutputStream(out);
	        OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8"); 
	        for(EnWord rrr : res)
	        {
	        	osw.write(rrr.getEnglish());		osw.write("\t");
				osw.write(rrr.getAps());			osw.write("\t");
				osw.write(rrr.getMeanOfEnglish());  osw.write("\t");
				osw.write(rrr.getMeanOfChinses());  osw.write("\t");
				osw.write(rrr.getSentence());
				osw.write("\r\n");
				//System.out.println(rrr.toString());
			}
			System.out.println(res.size());
			osw.flush();
	    } catch (Exception e) { 
	        e.printStackTrace(); 
	    }
	}

	private static EnWord DealWith(EnWord t)
	{
		String[] IPA = t.getAps().split("美");
		if(IPA.length >= 2)
			t.setAps(IPA[1]);

		String[] SoE = t.getSentenceOfEnglish().split("<br>");
		String[] SoC = t.getSentenceOfChinese().split("<br>");
		StringBuffer Sentence = new StringBuffer();
		for(int i = 0 ; i < Math.min(SoC.length,SoE.length) && i < 3 ; i++)
		{
			Sentence.append(SoE[i]);
			Sentence.append("<br>");
			char[] ch = SoC[i].toCharArray();
			if(ch.length > 3)
				ch[0] = ch[1] = ch[2] = ' ';
			SoC[i] = "&nbsp;&nbsp;&nbsp;" + new String(ch);
			Sentence.append(SoC[i]);
			Sentence.append("<br>");
		}
		t.setSentence(Sentence.toString());

		t.setMeanOfChinses(!t.getChineseOfOxford().equals("") ? t.getChineseOfOxford() : t.getChineseOfOnline());
		t.setMeanOfEnglish(t.getVocabSimple() + "<br>"+ "----------" +"<br>"+ t.getVocabExtend());
		return t;
	}

}


