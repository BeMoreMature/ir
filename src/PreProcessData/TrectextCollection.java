package PreProcessData;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Classes.Path;

/**
 * This is for INFSCI 2140 in 2018
 *
 */
public class TrectextCollection implements DocumentCollection {
	// Essential private methods or variables can be added.
	private BufferedReader bufferedReader;

	private final static String docNumberFormat = "<DOCNO>(.+)</DOCNO>";
	// YOU SHOULD IMPLEMENT THIS METHOD.
	public TrectextCollection() throws IOException {
		// 1. Open the file in Path.DataTextDir.
		// 2. Make preparation for function nextDocument().
		// NT: you cannot load the whole corpus into memory!!
		File file = new File(Path.DataTextDir);
		this.bufferedReader = new BufferedReader(new FileReader(file));
	}
	
	// YOU SHOULD IMPLEMENT THIS METHOD.
	public Map<String, Object> nextDocument() throws IOException {
		// 1. When called, this API processes one document from corpus, and returns its doc number and content.
		// 2. When no document left, return null, and close the file.
		String temp;
		String num = null;
		String content = null;
		while((temp = bufferedReader.readLine()) != null){
			if(temp.equals("<DOC>")){
				temp = bufferedReader.readLine();	// if this line is <DOCNO>, keep the number inside the tag
				Pattern pattern = Pattern.compile(docNumberFormat);
				Matcher matcher = pattern.matcher(temp);
				if(matcher.find()){
					num = matcher.group(1);
				}

				while(!(temp = bufferedReader.readLine()).equals("<TEXT>")){}	//when line is not <TEXT>, skip it
				StringBuilder stringBuilder = new StringBuilder();
				while((temp != null) && !(temp = bufferedReader.readLine()).equals("</TEXT>")){	// from the <TEXT>, loop until </TEXT>
					stringBuilder.append(temp).append(" ");
				}
				content = stringBuilder.toString();
				char [] textArray = content.toCharArray();
				Map<String, Object> docMap = new HashMap<>();
				docMap.put(num, textArray);
				return docMap;
			}
		}
		bufferedReader.close();
		return null;
	}
	
}
