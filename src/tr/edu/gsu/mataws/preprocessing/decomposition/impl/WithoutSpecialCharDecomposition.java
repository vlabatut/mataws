/*
 * Mataws - Multimodal Automatic Tool for the Annotation of  Web Services
 * Copyright 2011 Cihan Aksoy & Koray Man�uhan
 * 
 * This file is part of Mataws - Multimodal Automatic Tool for the Annotation of  Web Services.
 * 
 * Mataws - Multimodal Automatic Tool for the Annotation of  Web Services is 
 * free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2 of the License, or
 * (at your option) any later version.
 * 
 * Mataws - Multimodal Automatic Tool for the Annotation of  Web Services 
 * is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with Mataws - Multimodal Automatic Tool for the Annotation of  Web Services.
 * If not, see <http://www.gnu.org/licenses/>.
 * 
 */
package tr.edu.gsu.mataws.preprocessing.decomposition.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import de.abelssoft.wordtools.jwordsplitter.AbstractWordSplitter;

import tr.edu.gsu.mataws.preprocessing.decomposition.DecompositionStrgy;
import tr.edu.gsu.mataws.preprocessing.decomposition.wrappers.WrapperForEnglishWordSplitter;

/**
 * Splitting Strategy which separates contiguous words
 * into only one word by using JWordSplitter.
 *   
 * @author Cihan Aksoy
 *
 */
public class WithoutSpecialCharDecomposition implements DecompositionStrgy {

	@Override
	public List<String> execute(List<String> paramName) {
		List<String> result = new ArrayList<String>();
		try {
			AbstractWordSplitter ws = new WrapperForEnglishWordSplitter(true);
			for (String string : paramName) {
				Collection<String> splits = ws.splitWord(string);
				for (String string2 : splits) {
					result.add(string2);
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
}
