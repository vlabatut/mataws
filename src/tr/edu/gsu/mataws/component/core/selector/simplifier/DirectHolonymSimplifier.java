package tr.edu.gsu.mataws.component.core.selector.simplifier;

/*
 * Mataws - Multimodal Automatic Tool for the Annotation of Web Services
 * Copyright 2010 Cihan Aksoy and Koray Man�uhan
 * Copyright 2011 Cihan Aksoy
 * Copyright 2012 Cihan Aksoy and Vincent Labatut
 * 
 * This file is part of Mataws - Multimodal Automatic Tool for the Annotation of Web Services.
 * 
 * Mataws - Multimodal Automatic Tool for the Annotation of Web Services is 
 * free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2 of the License, or
 * (at your option) any later version.
 * 
 * Mataws - Multimodal Automatic Tool for the Annotation of Web Services 
 * is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with Mataws - Multimodal Automatic Tool for the Annotation of Web Services.
 * If not, see <http://www.gnu.org/licenses/>.
 * 
 */

import java.util.List;

import edu.smu.tspell.wordnet.Synset;

import tr.edu.gsu.mataws.component.core.selector.IdentifiedWord;
import tr.edu.gsu.mataws.tools.JawsTools;

/**
 * Looks for a direct holo/meronymial relationship between
 * two words in the list, by opposition to looking for a common 
 * holonym. A parameter allows specifying a limit to these search.
 * For instance, a limit of two means we look for holonyms of holonyms.
 * The concerned meronym is kept, while the holonym is removed.
 * <br>
 * Example: {@code "User"}, <b>{@code "Car"}</b>, <b>{@code "Engine"}</b> -> {@code "User"}, </b>{@code "Engine"}</b>
 *  
 * @author Vincent Labatut
 */
public class DirectHolonymSimplifier implements SimplifierInterface<Synset>
{
	/**
	 * Builds a {@code DirectHolonymSimplifier} using the
	 * default distance limit of 2.
	 */
	public DirectHolonymSimplifier()
	{	this.limit = 2;
	}
	
	/**
	 * Builds a {@code DirectHolonymSimplifier} using the
	 * specified distance limit.
	 * 
	 * @param limit 
	 * 		Specified limit.
	 */
	public DirectHolonymSimplifier(int limit)
	{	this.limit = limit;
	}
	
	///////////////////////////////////////////////////////////
	//	PROCESS								///////////////////
	///////////////////////////////////////////////////////////
	/** Limit used when looking for holonyms */
	private int limit;
	
	@Override
	public boolean simplify(List<IdentifiedWord<Synset>> words)
	{	boolean result = false;
		
		// look for a holonymial relationship
		IdentifiedWord<Synset> holonym = null;
		int i = 0;
		while(i<words.size()-1 && holonym!=null)
		{	int j = i + 1;
			IdentifiedWord<Synset> word1 = words.get(i);
			Synset synset1 = word1.getSynset();
			while(j<words.size() && !result)
			{	IdentifiedWord<Synset> word2 = words.get(j);
				Synset synset2 = word2.getSynset();
				// checking the first way
				int distance = JawsTools.isHolonym(synset1, synset2, limit);
				if(distance!=-1)
					holonym = word1;
				else
				{	// checking the other way round
					distance = JawsTools.isHolonym(synset2, synset1, limit);
					if(distance!=-1)
						holonym = word2;
				}
				j++;
			}
			i++;
		}
		
		// remove the holonym from the list
		if(holonym!=null)
		{	result = true;
			words.remove(holonym);
		}
		
		return result;
	}
}