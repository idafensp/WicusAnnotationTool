package net.oegupm.wicus.webannotator.utils.stringsimilarity;

import java.util.ArrayList;


import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.util.iterator.ExtendedIterator;

public class SimilarComponent 
{
	private static ArrayList<String> components;
	private static ArrayList<OntClass> cList;
	
	public SimilarComponent(String catalogUri)
	{
		OntModel cMod = ModelFactory.createOntologyModel( OntModelSpec.OWL_MEM );
		cMod.read(catalogUri, null);
		
		cList = new ArrayList<OntClass>();
		ExtendedIterator<OntClass> it = cMod.listClasses();
		
		while(it.hasNext())
		{
			OntClass c = it.next();
			cList.add(c);
		}
	}

	public static String getSimilarity(String indName)
	{
		SimilarityStrategy strategy = new JaroWinklerStrategy();
		
		OntClass maxClass = null;
		double maxVal = -1;
		
		for (OntClass comp : cList)
		{
			StringSimilarityService service = new StringSimilarityServiceImpl(strategy);
			String cLN = comp.getLocalName();
			double score = service.score(indName, cLN); 
			//System.out.println("sim|"+indName+"|"+comp+"|"+score);
			if(score>maxVal)
			{
				maxVal = score;
				maxClass = comp;
			}
		}
		
		return maxClass.toString();
		
	}
}
