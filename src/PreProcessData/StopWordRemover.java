package PreProcessData;
import Classes.*;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class StopWordRemover {
	// Essential private methods or variables can be added.
	private BufferedReader bufferedReader;
	private Set<String> stopWord = new HashSet<>();
	// YOU SHOULD IMPLEMENT THIS METHOD.
	public StopWordRemover( ) {
		// Load and store the stop words from the fileinputstream with appropriate data structure.
		// NT: address of stopword.txt is Path.StopwordDir
		File file = new File(Path.StopwordDir);
		try {
			this.bufferedReader = new BufferedReader(new FileReader(file));
			String temp = null;
			while((temp = bufferedReader.readLine())!= null){
				stopWord.add(temp);
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// YOU SHOULD IMPLEMENT THIS METHOD.
	public boolean isStopword( char[] word ) {
		// Return true if the input word is a stopword, or false if not.
		if(stopWord.contains(new String(word))){
			return true;
		}
		return false;
	}
}
