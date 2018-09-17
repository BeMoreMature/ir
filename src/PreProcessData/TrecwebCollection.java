package PreProcessData;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Classes.Path;

/**
 * This is for INFSCI 2140 in 2018
 *
 */
public class TrecwebCollection implements DocumentCollection {
	// Essential private methods or variables can be added.
	private BufferedReader bufferedReader;

	private final static String docNumberFormat = "<DOCNO>(.+)</DOCNO>";
	// YOU SHOULD IMPLEMENT THIS METHOD.
	public TrecwebCollection() throws IOException {
		// 1. Open the file in Path.DataWebDir.
		// 2. Make preparation for function nextDocument().
		// NT: you cannot load the whole corpus into memory!!
		File file = new File(Path.DataWebDir);
		this.bufferedReader = new BufferedReader(new FileReader(file));
	}
	
	// YOU SHOULD IMPLEMENT THIS METHOD.
	public Map<String, Object> nextDocument() throws IOException {
		// 1. When called, this API processes one document from corpus, and returns its doc number and content.
		// 2. When no document left, return null, and close the file.
		// 3. the HTML tags should be removed in document content.
		String temp = null;
		String num = null;
		String content = null;
		while((temp = bufferedReader.readLine()) != null) {
			if (temp.equals("<DOC>")) {
				temp = bufferedReader.readLine();    // // if this line is <DOCNO>, keep the number inside the tag
				Pattern pattern = Pattern.compile(docNumberFormat);
				Matcher matcher = pattern.matcher(temp);
				if (matcher.find()) {
					num = matcher.group(1);
				}
				while(!(temp = bufferedReader.readLine()).equals("</DOCHDR>")){}	//when line is not <TEXT>, skip it
				StringBuilder stringBuilder = new StringBuilder();
				while(!(temp = bufferedReader.readLine()).equals("</DOC>")){	// from the <TEXT>, loop until </TEXT>
					stringBuilder.append(temp).append(" ");
				}
				content = stringBuilder.toString();
				char [] text = content.toCharArray();
				Map<String, Object> webMap = new HashMap<>();
				webMap.put(num, text);
				return webMap;
			}
		}
		bufferedReader.close();
		return null;
	}
	
}
