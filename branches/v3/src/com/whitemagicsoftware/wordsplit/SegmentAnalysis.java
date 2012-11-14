package com.whitemagicsoftware.wordsplit;

import java.util.List;
import java.util.Map;

/**
 * Stores the details about a possible solution to a concatenated phrase.
 * These details allow the TextSegmenter class to determine whether or not
 * the solution is the most likely.
 */
public class SegmentAnalysis {
	/** */
	private int wordsUsed;
	/** */
	@SuppressWarnings("rawtypes")
	private List<Map.Entry> words;
	/** */
	private String remaining;

	/**
	 * @param words
	 */
	@SuppressWarnings("rawtypes")
	public SegmentAnalysis( List<Map.Entry> words ) {
		setWords( words );
	}

	/**
	 * Splits the given word (concatenated text) into multiple words, with
	 * spaces to separate each word.
	 *
	 * @param concat - The words to split.
	 * @return The given parameter with spaces in between each word.
	 */
	@SuppressWarnings("rawtypes")
	public StringBuilder apply( String concat ) {
		for( Map.Entry entry : getWords() ) {
			String word = (String)(entry.getKey());
			concat = concat.replaceFirst( word, " " + word + " " );
		}

		return new StringBuilder( normalise( concat ) );
	}

	/**
	 * @return ?
	 */
	public boolean matchedAllWords() {
		return getWordCount() == getWordsUsed();
	}

	/**
	 * @return ?
	 */
	public int length() {
		return getRemaining().length();
	}

	/**
	 * Removes multiple spaces from inside a string, as well as trimming white
	 * space from both ends of the string.
	 * @param s 
	 *
	 * @return The value of s with its whitespace normalised.
	 */
	private String normalise( String s ) {
		return s.replaceAll( "\\b\\s{2,}\\b", " " ).trim();
	}

	/**
	 * @param remaining
	 */
	public void setRemaining( String remaining ) {
		this.remaining = normalise( remaining );
	}

	/**
	 * @return ?
	 */
	private String getRemaining() {
		return this.remaining;
	}

	/**
	 * @return ?
	 */
	private double getWordCount() {
		return getWords().size();
	}

	/**
	 * @param wordsUsed
	 */
	public void setWordsUsed( int wordsUsed ) {
		this.wordsUsed = wordsUsed;
	}

	/**
	 * @return ?
	 */
	private double getWordsUsed() {
		return this.wordsUsed;
	}

	/**
	 * Returns the product of the probability of each word in this potential
	 * solution.
	 *
	 * @return A number between 0 and 1.
	 */
	@SuppressWarnings("rawtypes")
	public double getProbability() {
		double probability = 1;

		for( Map.Entry entry : getWords() ) {
			probability *= ((Double)(entry.getValue())).doubleValue();
		}

		return probability * (getWordsUsed() / getWordCount());
	}

	/**
	 * @param words
	 */
	@SuppressWarnings("rawtypes")
	private void setWords( List<Map.Entry> words ) {
		this.words = words;
	}

	/**
	 * @return ?
	 */
	@SuppressWarnings("rawtypes")
	private List<Map.Entry> getWords() {
		return this.words;
	}
}
