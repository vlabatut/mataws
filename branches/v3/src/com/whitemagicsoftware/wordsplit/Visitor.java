package com.whitemagicsoftware.wordsplit;

import java.util.List;

/**
 * Defines the mechanism that allows the SegmentVisitor to collect statistics
 * on the combination of words that forms a possible solution to the
 * text splitting.
 */
public interface Visitor {
	/**
	 * Returns details about the likelihood that the given list of words will
	 * solve the text splitting problem.
	 *
	 * @param list - The list of split words.
	 * @return ?
	 */
	@SuppressWarnings("rawtypes")
	public SegmentAnalysis visit( List list );
}
