package tr.edu.gsu.mataws.analyzer;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import tr.edu.gsu.mataws.components.TraceType;
import tr.edu.gsu.mataws.components.TraceableParameter;

import edu.smu.tspell.wordnet.Synset;
import edu.smu.tspell.wordnet.SynsetType;
import edu.smu.tspell.wordnet.WordNetDatabase;

public class Analyzer {

	private AnalysisType analysisType = AnalysisType.NoAnalysis;
	
	public String analyzeWords(TraceableParameter tParameter, List<String> preprocessedResult){
		
		System.setProperty("wordnet.database.dir",System.getProperty("user.dir") + File.separator + "dictionary");
		
		WordNetDatabase wd = WordNetDatabase.getFileInstance();
		
		WordnetAnalyzer wa = new WordnetAnalyzer();
		
		String result = "";
		List<String> controlList = new ArrayList<String>();
		
		List<String> analyzeList = new ArrayList<String>();
		for (String string : preprocessedResult) {
			analyzeList.add(string);
		}
		
		Synset[] nounSynsets = null;
		Synset[] verbSynsets = null;
		Synset[] adjectifSynsets = null;
		Synset[] adverbSynsets = null;
		
		boolean entered = false;
		
		//here we try to get only nouns
		for(String string : preprocessedResult){
			nounSynsets = wd.getSynsets(string, SynsetType.NOUN);
			verbSynsets = wd.getSynsets(string, SynsetType.VERB);
			adjectifSynsets = wd.getSynsets(string, SynsetType.ADJECTIVE);
			adverbSynsets = wd.getSynsets(string, SynsetType.ADVERB);
			
			/*if(nounSynsets.length > verbSynsets.length && 
					nounSynsets.length > adjectifSynsets.length && 
					nounSynsets.length > adverbSynsets.length){
			
			} else{
				analyzeList.remove(string);
			}*/
			if(nounSynsets.length != 0){
				if(verbSynsets.length !=0){
					if(((float)((float)nounSynsets.length / verbSynsets.length)) < 0.3)
						analyzeList.remove(string);
				}else if(adverbSynsets.length !=0){
					if(((float)((float)nounSynsets.length / adverbSynsets.length)) < 0.3)
						analyzeList.remove(string);
				}else if(adjectifSynsets.length !=0){
					if(((float)((float)nounSynsets.length / adjectifSynsets.length)) < 0.3)
						analyzeList.remove(string);
				}
			}else
				analyzeList.remove(string);
		}
		
		//in case of no noun exists in the list, then we try to get only verbs 
		if(analyzeList.size() == 0){
			
			entered = true;
			
			for (String string : preprocessedResult) {
				analyzeList.add(string);
			}
			
			for(String string : preprocessedResult){
				nounSynsets = wd.getSynsets(string, SynsetType.NOUN);
				verbSynsets = wd.getSynsets(string, SynsetType.VERB);
				adjectifSynsets = wd.getSynsets(string, SynsetType.ADJECTIVE);
				adverbSynsets = wd.getSynsets(string, SynsetType.ADVERB);
				
				if(verbSynsets.length != 0){
					if(adverbSynsets.length != 0){
						if(((float)((float)verbSynsets.length / adverbSynsets.length)) < 0.3)
							analyzeList.remove(string);
					}else if(adjectifSynsets.length != 0){
						if(((float)((float)verbSynsets.length / adjectifSynsets.length)) < 0.3)
							analyzeList.remove(string);
					}
				}else
					analyzeList.remove(string);
			}
		}
		
		if(preprocessedResult.size() == 0)
			return result;
		
		if(analyzeList.size() == 0){
			result = preprocessedResult.get(0);
			analysisType = AnalysisType.NonNounVerbAnnotation;
			tParameter.addTraceList(TraceType.NonNounVerbAnnotation);
		}
		else if(analyzeList.size() == 1){
			result = analyzeList.get(0);
			analysisType = AnalysisType.OnlyOneRemaining;
			tParameter.addTraceList(TraceType.OnlyOneRemaining);
		}
		else if(wa.onlyOneRepresenter(analyzeList) != null){
			controlList = wa.onlyOneRepresenter(analyzeList);
			tParameter.addTraceList(TraceType.OnlyOneRepresenter);
			if(controlList.size() == 1){
				result = controlList.get(0);
				analysisType = AnalysisType.OnlyOneRepresenter;
			}else{
				for(String string:controlList){
					tParameter.addControlList(string);
				}
				return analyzeWords(tParameter, controlList);
			}
		}
		else if(wa.hypernymialRelationFinder(analyzeList, entered) != null){
			controlList = wa.hypernymialRelationFinder(analyzeList, entered);
			tParameter.addTraceList(TraceType.HypernymialRelation);
			if(controlList.size() == 1){
				result = controlList.get(0);
				analysisType = AnalysisType.HypernymialRelation;
			}else{
				for(String string:controlList){
					tParameter.addControlList(string);
				}
				return analyzeWords(tParameter, controlList);
			}
		}
		else if(entered){
			result = analyzeList.get(0);
			analysisType = AnalysisType.SimpleVerbAnnotation;
			tParameter.addTraceList(TraceType.SimpleVerbAnnotation);
		}
		else if(wa.holonymialRelationFinder(analyzeList) != null){
			controlList = wa.holonymialRelationFinder(analyzeList);
			tParameter.addTraceList(TraceType.HolonymialRelation);
			if(controlList.size() == 1){
				result = controlList.get(0);
				analysisType = AnalysisType.HolonymialRelation;
			}else{
				for(String string:controlList){
					tParameter.addControlList(string);
				}
				return analyzeWords(tParameter, controlList);
			}
		}
		else{
			result = wa.nounAdjunctFinder(analyzeList);
			analysisType = AnalysisType.NounAdjunct;
			tParameter.addTraceList(TraceType.NounAdjunct);
		}
		return result;
	}
	
	public AnalysisType getAnalysisType(){
		return analysisType;
	}
	
}
