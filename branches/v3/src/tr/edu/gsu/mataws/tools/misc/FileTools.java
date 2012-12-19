package tr.edu.gsu.mataws.tools.misc;

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

import java.io.File;

/**
 * This class contains various methods and variables used all over the
 * software to retrieve files.
 * 
 * @author Vincent Labatut
 */
public class FileTools
{	/** Folder containing the input data files */
	public static final String INPUT_FOLDER = "input";
	/** Folder containing the original syntactic description files */
	public static final String IN_COLLECTION_FOLDER = INPUT_FOLDER + File.separator + "collection";
	/** Folder containing other input files */
	public static final String IN_OTHERS_FOLDER = INPUT_FOLDER + File.separator + "others";
	
	/** Folder containing the generated data files */
	public static final String OUTPUT_FOLDER = "output";
	/** Folder containing the generated semantic description files */
	public static final String OUT_COLLECTION_FOLDER = OUTPUT_FOLDER + File.separator + "collection";
	/** Folder containing the generated stat files */
	public static final String OUT_STATS_FOLDER = OUTPUT_FOLDER + File.separator + "stats";
	/** Folder containing the generated other files */
	public static final String OUT_OTHERS_FOLDER = OUTPUT_FOLDER + File.separator + "others";
	
	/** Log folder */
	public static final String LOG_FOLDER = "log";

	/** General folder for Mataws resources */
	public static final String RESOURCES_FOLDER = "resource";
	/** Folder containing Mataws configuration files  */
	public static final String CONFIG_FOLDER = RESOURCES_FOLDER + File.separator + "config";
	/** Folder containing Sigma's Knowledge bases */
	public static final String KNOWBASE_FOLDER = RESOURCES_FOLDER + File.separator + "kbs";
	/** Folder containing the dictionary for the word splitter */
	public static final String SPLITTER_FOLDER = RESOURCES_FOLDER + File.separator + "splitter";
	/** Folder containing WordNet, for JAWS */
	public static final String WORDNET_FOLDER = RESOURCES_FOLDER + File.separator + "wordnet";
}
