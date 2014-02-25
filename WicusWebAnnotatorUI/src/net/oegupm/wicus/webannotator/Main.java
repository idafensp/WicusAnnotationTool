package net.oegupm.wicus.webannotator;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import net.oegupm.wicus.webannotator.utils.FileArrayProvider;
import net.oegupm.wicus.webannotator.utils.HtmlCodePrinter;
import net.oegupm.wicus.webannotator.utils.WfStepsHandler;
import net.oegupm.wicus.webannotator.utils.stringsimilarity.SimilarComponent;

public class Main {

	private static final String inputFolder = "/Users/isantana/Dropbox/DOCTORADO/ONTOLOGIAS/WICUS/testcases/WfcomponentsWind/#12DocumentClassificationMulti/modelling/";
	private static final String inputLinesPath = inputFolder + "lines.txt";
	private static final String inputStacksPath = inputFolder + "stacks.txt";
	private static final String catalogUri ="/Users/isantana/Dropbox/DOCTORADO/ONTOLOGIAS/WICUS/testcases/WfcomponentsWind/acdom_library.owl";
	private static final String windImgUriPreffix = "http://wind.isi.edu/marbles/assets/components/workflow_portal/users/2/TextAnalytics/ontology/TextAnalytics/";
	
	public static String generateHTML() {
		
		
		WfStepsHandler wsh = new WfStepsHandler(inputLinesPath);
		HtmlCodePrinter html = new HtmlCodePrinter();
		
		ArrayList<String> stacks = null;
		try {
			stacks = FileArrayProvider.readLines(inputStacksPath);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String htmlCode ="";
		
		String wfName = "DocumentClassification_multi";
		String wfImage = windImgUriPreffix +wfName+".owl.png";
		
		htmlCode = html.getHeader(wfName, wfImage);
		
		
		htmlCode += html.getWorkflow(wsh.getWorkflow(), wsh.getSubworkflows(),stacks);
		

		SimilarComponent sc = new SimilarComponent(catalogUri);
		for(String csw : wsh.getConcreteSubworkflows())
		{
			htmlCode+=html.getConcreteWorkflow(csw,sc);
		}
		
		for(String csw : wsh.getAbstractSubworkflows())
		{
			htmlCode+=html.getAbstractWorkflow(csw,sc);
		}
		

		for(String csw : wsh.getAbstractConcreteSubworkflows())
		{
			htmlCode+=html.getConcreteWorkflow(csw,sc);
		}
		

		for(String s : stacks)
		{
			htmlCode += html.generateStackDependencies(s,wsh.getAllConcreteSubworkflows());
		}
		
		
		htmlCode += html.generateForm(stacks,inputFolder);
		
		htmlCode += html.getFooter();
		
		
//		
//		try {
//			PrintWriter pw = new PrintWriter(destPath, "UTF-8");
//			pw.print(htmlCode);
//			pw.close();
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//			return "ERROR";
//		} catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
//			return "ERROR";
//		}
		
		System.out.println("Fin");
		
		return htmlCode;
	}

	
	
}
