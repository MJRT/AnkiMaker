package tech.jinhaoma.AnkiMaker.WordMaker;

import java.io.*;

import java.math.*;

import java.util.*;

import tech.jinhaoma.AnkiMaker.common.Check;

import java.text.*;

public class DictToWord {

	public static void main(String[] args) throws IOException {
		getWord("e:\\123.txt","E:\\Private\\EveryDayWord\\output.txt");
	}
	
	
	static void getWord(String inFile,String outFile){
		ArrayList<String> res = new ArrayList<>();
	    try { 
	        FileInputStream fis = new FileInputStream(inFile); 
	        InputStreamReader isr = new InputStreamReader(fis, "UTF-8"); 
	        BufferedReader br = new BufferedReader(isr); 
	        String line = null; 
	        int idx = 1;
	        while ((line = br.readLine()) != null) {

	            String[] s = line.split("@");
	            if( s[0].equals(new String(String.valueOf(idx).getBytes("UTF-8"))));
	            {
					idx++;
                    if(s.length > 1 && !Check.isChinese(s[1]) && s[1].length() < 30)
	            		res.add(s[1]);
	            }

			}

	    } catch (Exception e) { 
	        e.printStackTrace(); 
	    }
	    
	    try { 
	        FileOutputStream fos = new FileOutputStream(outFile); 
	        OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8"); 
	        for(String rrr : res)
	        {
	        	osw.write(rrr);
	        	System.out.println(rrr);
				osw.write("\r\n");
	        }
	        osw.flush(); 
	    } catch (Exception e) { 
	        e.printStackTrace(); 
	    }
	}

}

