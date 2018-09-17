package PreProcessData;

import org.omg.Messaging.SYNC_WITH_TRANSPORT;

/**
 * This is for INFSCI 2140 in 2018
 * 
 * TextTokenizer can split a sequence of text into individual word tokens.
 */
public class WordTokenizer {
	// Essential private methods or variables can be added.
	private char[] texts;

	private int startIndex = 0;
	// YOU MUST IMPLEMENT THIS METHOD.
	public WordTokenizer( char[] texts ) {
		// Tokenize the input texts.
		this.texts = new char[texts.length];
		this.texts = texts.clone();
	}

	// YOU MUST IMPLEMENT THIS METHOD.
	public char[] nextWord() {
		// Return the next word in the document.
		// Return null, if it is the end of the document.
		int i = startIndex;
		for(; i < texts.length; i++){
			if(Character.isLetter(texts[i])){	//find the first letter in the word
				startIndex = i;
				break;
			}
		}

		for(; i < texts.length; i++){
			if(!Character.isLetter(texts[i])){	//	copy continuous letter into char array
				char []word = new char[i-startIndex];
				System.arraycopy(texts, startIndex, word, 0, i-startIndex);
				startIndex = i;
				return word;
			}

		}
		return null;
	}

}
