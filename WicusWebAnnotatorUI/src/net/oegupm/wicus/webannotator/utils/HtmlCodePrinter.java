package net.oegupm.wicus.webannotator.utils;

import java.util.ArrayList;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.ObjectProperty;

import net.oegupm.wicus.webannotator.utils.stringsimilarity.SimilarComponent;

public class HtmlCodePrinter {
	public static int flip = 0;

	public static ArrayList<String> s;
	public static ArrayList<String> p;
	public static ArrayList<String> o;
	
	public static int noit;
	public static int stackNoit;
	public static int noCwfs;

	public final static String inputStacksPreffix = "stacks_";

	public final static String stackIdPreffix  = "stackid_";
	public final static String idPreffix = "sid_";

	public final static String sSuffix = "_s";
	public final static String pSuffix = "_p";
	public final static String oSuffix = "_o";
	public final static String cnIsAClass = "http://www.w3.org/1999/02/22-rdf-syntax-ns#type"; 
	public final static String formActionUrl = "SkeletonGenerator";
	
	public HtmlCodePrinter()
	{
		s = new ArrayList<String>();
		p = new ArrayList<String>();
		o = new ArrayList<String>();
		noit = 0;
		stackNoit = 0;
		noCwfs = 0;
	}

	public static String getHeader(String wfName, String imgUrl)
	{
		String res = 
				"<!doctype html>\n" + 
				"<html>\n\n" +
				"<head>\n" +
				"	<title>wicus:annotator</title>\n" +
				"	<link rel=\"stylesheet\" href=\"css/reset.css\">\n" +
				"	<link rel=\"stylesheet\" href=\"css/style.css\">\n" +
				"	<link href='http://fonts.googleapis.com/css?family=Oxygen:400,300,700' rel='stylesheet' type='text/css'>\n" +
				"	<script type=\"text/javascript\">\n" +
				"		function submitform()\n" +
				"		{\n" +
				"			  //alert('hey');\n" +
				"			  document.myform.submit();\n" +
				"		}\n" +
				"	</script>\n" +
				"</head>\n" +
				"<body>\n" +
				"<form name='myform' style=\"display:inline\" action=\""+formActionUrl+"\" method=\"POST\">\n" +
				"	<header>\n" +
				"		<div class=\"wrapper\">\n" +
				"			<img src=\"images/logoannotator.png\">\n" +
				"			<div class=\"clear\"></div>\n" +
				"		</div>\n" +
				"	</header>\n" +
				"	<div id=\"banner\">\n" +
				"		<div class=\"wrapper\">\n" +
				"			<section class=\"introduction\">\n" +
				"				<h2>workflow|\n" + wfName + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</h2>\n" +
				"				<p></p>\n" +
				"			</section>\n" +
				"			<section class=\"image\">\n" +
				"				<a href=\"\n" + imgUrl + "\" target=\"_blank\"><img height=\"380\"  src=\"\n" + imgUrl + "\" alt=\"Workflow diagram\" /></a>\n" +
				"			</section>\n" +
				"			<div class=\"clear\"></div>\n" +
				"		</div>\n";
		
		return res;
	}
	
	public static String getFooter()
	{
		String res = "	<div class=\"wrapper\">\n" +
				"		<a class=\"wide-button\" href=\"javascript: submitform()\">SAVE</a>\n" +
				"	</div>\n" +
				"	<footer>\n" +
				"		<section class=\"copyright\">\n" +
				"			<p>Copyright &copy; 2013 <strong>companyname</strong>.<br/>\n" +
				"			All Rights Reserved.</p>\n" +
				"		</section>\n" +
				"	</footer>\n" +
				"</form>\n" +
				"</body>\n";
		return res;
	}
	
	public static String getWorkflow(String name, ArrayList<String> wfs, ArrayList<String> stacks)
	{
		

		String wfFullname = Constants.resClassWorkflow+name+Constants.WorkflowSuffix;
		String res ="	<div style=\"height:10px;\" id=\"feature-section\" class=\"feature-left\">\n" +
				"		<div class=\"wrapper\">\n" +
				"			<section class=\"description\">\n" +
				"			<h3>"+wfFullname+"</h3>\n" +
				"			<p>:is-a <a href=\""+Constants.cnWorkflow+"\">"+Constants.cnWorkflow+"</a> </p> \n";		
		
		
		addStatement(wfFullname,cnIsAClass,Constants.cnWorkflow);
		
		
		String previousAbsWf = "XXX";
		for(String wf : wfs)
		{
			String wfName = wf.substring(wf.indexOf("#")+1);
			if(wf.startsWith(">c"))
			{
				wfName+=Constants.ConcreteWorkflowSuffix; 
				res += "			<p>:hasSubworkflow <a href=\"#"+wfName+"\">"+wfName+"</a></p>\n";
				
				addStatement(wfFullname,Constants.opnhasSubworkflow,Constants.resClassConcreteWorkflow+wfName);
				
			}
			else
			{
				if(wf.startsWith(">a"))
				{
					wfName+=Constants.AbstractWorkflowSuffix;
					res += "			<p>:hasSubworkflow <a href=\"#"+wfName+"\">"+wfName+"</a></p>\n";

					addStatement(wfFullname,Constants.opnhasSubworkflow,Constants.resClassAbstractWorkflow+wfName);
					previousAbsWf=Constants.resClassAbstractWorkflow+wfName;
				}
				else
				{
					if(wf.startsWith(">>c"))
					{
						wfName+=Constants.ConcreteWorkflowSuffix;
						res += "			<p>&nbsp;&nbsp;&nbsp;:hasConcreteWorkflow <a href=\"#"+wfName+"\">"+wfName+"</a></p>\n";
						
						addStatement(previousAbsWf,Constants.opnhasConcreteWorkflow,Constants.resClassConcreteWorkflow+wfName);
					}
				}
			}	
		}
		
		
		res += 	"			<h3>SUGGESTED SOFTWARE STACKS</h3>\n";
		
		for(String st : stacks)
		{
			String stackFullName = st+Constants.SoftwareStackSuffix;
			res += "		<p><a href=\"#"+stackFullName+"\">"+st+"</a></p>\n";
		}
		
		res += "			</section>\n" +
				"			<div class=\"clear\"></div>\n" +
				"		</div>\n" +
				"	</div>\n";
		
		return res;
	}
	



	
	
	public static String getConcreteWorkflow(String name, SimilarComponent sc)
	{
		flip++;
		
		String frl = "";
		if(flip%2==0)
		{
			frl="feature-left";
		}
		else
		{
			frl="feature-left_2";
		}

		String fullName =  name.substring(name.indexOf("#")+1)+Constants.ConcreteWorkflowSuffix;
		String stepFullName = name.substring(name.indexOf("#")+1)+Constants.WorkflowStepSuffix;
		String reqsFullName = name.substring(name.indexOf("#")+1)+Constants.SoftwareRequirementsSuffix;
		String stackFullName = name.substring(name.indexOf("#")+1)+Constants.SoftwareStackSuffix;
		String componentFullName = name.substring(name.indexOf("#")+1)+Constants.SoftwareComponentSuffix;
		String confFullName = name.substring(name.indexOf("#")+1)+Constants.ConfigurationInfoSuffix;
		String parFullName = name.substring(name.indexOf("#")+1)+Constants.ConfigurationParameterSuffix;
		String binFullName = name.substring(name.indexOf("#")+1)+Constants.SoftwareBinarySuffix;
		

		String ifullName = Constants.resClassConcreteWorkflow + fullName;
		String istepFullName = Constants.resClassWorkflowStep + stepFullName;
		String ireqsFullName = Constants.resClassSoftwareRequirements + reqsFullName;
		String istackFullName = Constants.resClassSoftwareStack+ stackFullName;
		String icomponentFullName = Constants.resClassSoftwareComponent + componentFullName;
		String iconfFullName = Constants.resClassConfigurationInfo + confFullName;
		String iparFullName = Constants.resClassConfigurationParameter + parFullName;
		String ibinFullName = Constants.resClassSoftwareBinary+ binFullName;
		

		String id = getSid();
		String sid = id + sSuffix;
		String pid = id + pSuffix;
		String oid = id + oSuffix;


		String id2 = getSid();
		String sid2 = id2 + sSuffix;
		String pid2 = id2 + pSuffix;
		String oid2 = id2 + oSuffix;

		String id3 = getSid();
		String sid3 = id3 + sSuffix;
		String pid3 = id3 + pSuffix;
		String oid3 = id3 + oSuffix;

		String id4 = getSid();
		String sid4 = id4 + sSuffix;
		String pid4 = id4 + pSuffix;
		String oid4 = id4 + oSuffix;
		
		String candidateComponent = sc.getSimilarity(name);
		
		String res =
				"	<div id=\"feature-section\" class=\""+frl+"\">\n" +
				"		<div class=\"wrapper\">\n" +
				"			<section class=\"description\">\n" +
				"			<h3 id=\""+fullName+"\">"+fullName+"</h3>\n" +
				"			<p>:is-a <a href=\""+Constants.cnConcreteWorkflow+"\">"+Constants.cnConcreteWorkflow+"</a> </p>\n" +
				"			<p><a href=\""+Constants.opnrequires+"\">:requires</a> "+reqsFullName+" </p>\n" +
				"			<p>&nbsp;&nbsp;&nbsp;&lfloor;<a href=\""+Constants.opncomposedBySoftwareStack+"\">:composedBySoftwareStack</a> "+stackFullName+" </p>\n" +
				"			<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&lfloor;<a href=\""+Constants.opnhasSoftwareComponent+"\">:hasSoftwareComponent</a> "+componentFullName+" </p>\n" +
				"			<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&lfloor;<a href=\""+Constants.opnhasConfigurationInfo+"\">:hasConfigurationInfo</a> "+confFullName+" </p>\n" +
				"			<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&lfloor;"+parFullName+" <a href=\""+Constants.opnisConfigurationParameterOf+"\">:isConfigurationParameterOf</a></p>\n" +
				"			<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&lfloor;<a href=\""+Constants.dpnbinary+"\">:parameterName</a> <input type=\"hidden\" name=\""+sid3+"\" value=\""+iparFullName+"\"><input type=\"hidden\" name=\""+pid3+"\" value=\""+Constants.dpnparameterName+"\"><input name=\""+oid3+"\" size=\"70\" type=\"text\" value=\"\"> </p>\n" +
				"			<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&lfloor;<a href=\""+Constants.dpnbinary+"\">:parameterValue</a> <input type=\"hidden\" name=\""+sid4+"\" value=\""+iparFullName+"\"><input type=\"hidden\" name=\""+pid4+"\" value=\""+Constants.dpnparameterValue+"\"><input name=\""+oid4+"\" size=\"70\" type=\"text\" value=\"\"> </p>\n" +
				"			<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&lfloor;<a href=\""+Constants.opnhasBinary+"\">:hasBinary</a> "+binFullName+" </p>\n" +
				"			<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&lfloor;<a href=\""+Constants.dpnbinary+"\">:binary</a> <input type=\"hidden\" name=\""+sid+"\" value=\""+ibinFullName+"\"><input type=\"hidden\" name=\""+pid+"\" value=\""+Constants.dpnbinary+"\"><input name=\""+oid+"\" size=\"70\" type=\"text\" value=\"REPO:\"> </p>\n" +
				"			<hr><h4>Steps:</h4>\n" +
				"				<p>"+stepFullName+"&nbsp;&nbsp;&nbsp;&nbsp; :is-a <input type=\"hidden\" name=\""+sid2+"\" value=\""+istepFullName+"\"><input type=\"hidden\" name=\""+pid2+"\" value=\""+cnIsAClass+"\"><input name=\""+oid2+"\" type=\"text\" value=\""+candidateComponent+"\"></p>\n";
	
		
		
		res += "			</section>\n" +
				"			<div class=\"clear\"></div>\n" +
				"		</div>\n" +
				"	</div> \n";
		
		

		addStatement(ifullName,cnIsAClass,Constants.cnConcreteWorkflow);
		addStatement(ifullName,cnIsAClass,Constants.cnPPlanPlan);
		
		addStatement(istepFullName,cnIsAClass,Constants.cnWorkflowStep);
		addStatement(istepFullName,cnIsAClass,Constants.cnPPlanStep);
		
		addStatement(istepFullName,Constants.opnisStepOfWorkflow,ifullName);
		addStatement(istepFullName,Constants.opnisStepOfPlan,ifullName);
		

		addStatement(ireqsFullName,cnIsAClass,Constants.cnSoftwareRequirements);
		addStatement(iconfFullName,cnIsAClass,Constants.cnConfigurationInfo);
		addStatement(istackFullName,cnIsAClass,Constants.cnSoftwareStack);
		addStatement(icomponentFullName,cnIsAClass,Constants.cnSoftwareComponent);
		addStatement(ibinFullName,cnIsAClass,Constants.cnSoftwareBinary);
		addStatement(iparFullName,cnIsAClass,Constants.cnConfigurationParameter);
		addStatement(iparFullName,cnIsAClass,Constants.cnPPlanVariable);
		

		addStatement(ifullName,Constants.opnrequires,ireqsFullName);
		addStatement(ifullName,Constants.opndcrequires,ireqsFullName);

		addStatement(iparFullName,Constants.opnisConfigurationParameterOf,iconfFullName);
		addStatement(iparFullName,Constants.opnisInputVarOf,iconfFullName);
		

		addStatement(ireqsFullName,Constants.opncomposedBySoftwareStack,istackFullName);
		addStatement(istackFullName,Constants.opnhasSoftwareComponent,icomponentFullName);
		addStatement(icomponentFullName,Constants.opnhasConfigurationInfo,iconfFullName);
		addStatement(icomponentFullName,Constants.opnhasBinary,ibinFullName);
		
		
		
		
		
		return res;
	}
	
	public static String getAbstractWorkflow(String name, SimilarComponent sc)
	{
		flip++;
		
		String frl = "";
		if(flip%2==0)
		{
			frl="feature-left";
		}
		else
		{
			frl="feature-left_2";
		}
		
		String fullName =  name.substring(name.indexOf("#")+1)+Constants.AbstractWorkflowSuffix;
		String stepFullName = name.substring(name.indexOf("#")+1)+Constants.WorkflowStepSuffix;
		
		String id = getSid();
		String sid = id + sSuffix;
		String pid = id + pSuffix;
		String oid = id + oSuffix;

		String candidateComponent = sc.getSimilarity(name);
		
		String res =
				"	<div id=\"feature-section-abs\" class=\""+frl+"\">\n" +
				"		<div class=\"wrapper\">\n" +
				"			<section class=\"description\">\n" +
				"			<h3 id=\""+fullName+"\">"+fullName+"</h3>\n" +
				"			<p>:is-a <a href=\""+Constants.cnAbstractWorkflow+"\">"+Constants.cnAbstractWorkflow+"</a> </p>\n" +
				"			<hr><h4>Steps:</h4>\n" +
				"				<p>"+stepFullName+"&nbsp;&nbsp;&nbsp;&nbsp; :is-a <input type=\"hidden\" name=\""+sid+"\" value=\""+Constants.resClassWorkflowStep+fullName+"\"><input type=\"hidden\" name=\""+pid+"\" value=\""+cnIsAClass+"\"><input name=\""+oid+"\" type=\"text\" value=\""+candidateComponent+"\"></p>\n";

	
		
		
		res += "			</section>\n" +
				"			<div class=\"clear\"></div>\n" +
				"		</div>\n" +
				"	</div>";

		addStatement(Constants.resClassAbstractWorkflow+fullName,cnIsAClass,Constants.cnAbstractWorkflow);
		addStatement(Constants.resClassAbstractWorkflow+fullName,cnIsAClass,Constants.cnPPlanPlan);
		addStatement(Constants.resClassWorkflowStep+stepFullName,cnIsAClass,Constants.cnWorkflowStep);
		addStatement(Constants.resClassWorkflowStep+stepFullName,cnIsAClass,Constants.cnPPlanStep);
		addStatement(Constants.resClassWorkflowStep+stepFullName,Constants.opnisStepOfWorkflow,Constants.resClassAbstractWorkflow+fullName);
		addStatement(Constants.resClassWorkflowStep+stepFullName,Constants.opnisStepOfPlan,Constants.resClassAbstractWorkflow+fullName);
		
		
		return res;
	}
	
	private static void addStatement(String ss, String sp, String so)
	{
		s.add(ss);
		p.add(sp);
		o.add(so);
	}
	
	private static String getSid()
	{
		String itId = idPreffix  + noit;
		noit++;
		
		return itId;
	}
	
	
	public static String generateForm(ArrayList<String> stacks, String inputFolder)
	{
		String res ="";
		
		for(int i=0; i<s.size();i++)
		{
			String id = getSid();
			String sid = id + sSuffix;
			String pid = id + pSuffix;
			String oid = id + oSuffix;
			
			String it = "<input type=\"hidden\" name=\""+sid+"\" value=\""+s.get(i)+"\"><input type=\"hidden\" name=\""+pid+"\" value=\""+p.get(i)+"\"><input type=\"hidden\" name=\""+oid+"\" type=\"text\" value=\""+o.get(i)+"\">\n";
			res +=it;
		}
		
		for(int i=0; i<stacks.size();i++)
		{
			String stackFullName = stacks.get(i)+Constants.SoftwareStackSuffix;
			String istackFullname = Constants.resClassSoftwareStack + stackFullName;
		
			res += "<input type=\"hidden\" name=\""+ inputStacksPreffix  + i + "\" value=\""+istackFullname+"\">\n";
		}

		res += "<input type=\"hidden\" name=\"noit\" value=\""+noit+"\">\n";
		res += "<input type=\"hidden\" name=\"stackNoit\" value=\""+stackNoit+"\">\n";
		res += "<input type=\"hidden\" name=\"noCwfs\" value=\""+noCwfs+"\">\n";
		res += "<input type=\"hidden\" name=\"inputFolder\" value=\""+inputFolder+"\">\n";
		
		return res;
	}
	
	public static String generateStackDependencies(String stackName, ArrayList<String> cwfs)
	{
		
		noCwfs = cwfs.size();
		flip++;
		
		String frl = "";
		if(flip%2==0)
		{
			frl="feature-left";
		}
		else
		{
			frl="feature-left_2";
		}
		
		String res = "";
		
		
		String stackFullName = stackName+Constants.SoftwareStackSuffix;
		String componentFullName = stackName+Constants.SoftwareComponentSuffix;
		String confFullName = stackName+Constants.ConfigurationInfoSuffix;
		String parFullName = stackName+Constants.ConfigurationParameterSuffix;
		String binFullName = stackName+Constants.SoftwareBinarySuffix;
		
		String istackFullname = Constants.resClassSoftwareStack + stackFullName;
		String icomponentFullName = Constants.resClassSoftwareComponent + componentFullName;
		String iconfFullName = Constants.resClassConfigurationInfo + confFullName;
		String iparFullName = Constants.resClassConfigurationParameter + parFullName;
		


		String id3 = getSid();
		String sid3 = id3 + sSuffix;
		String pid3 = id3 + pSuffix;
		String oid3 = id3 + oSuffix;

		String id4 = getSid();
		String sid4 = id4 + sSuffix;
		String pid4 = id4 + pSuffix;
		String oid4 = id4 + oSuffix;
		
		res =	"	<div id=\"feature-section-stack\" class=\""+frl+"\">\n" +
				"		<div class=\"wrapper\">\n" +
				"			<section class=\"description\">\n" +
				"			<h3 id=\""+stackFullName+"\">"+stackFullName+"</h3>\n" +
				"			<p>:is-a <a href=\""+Constants.cnSoftwareStack+"\">"+Constants.cnSoftwareStack+"</a> </p>\n" +
				"			<p>&nbsp;&nbsp;&nbsp;&lfloor;<a href=\""+Constants.opnhasSoftwareComponent+"\">:hasSoftwareComponent</a> "+componentFullName+" </p>\n" +
				"			<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&lfloor;<a href=\""+Constants.opnhasConfigurationInfo+"\">:hasConfigurationInfo</a> "+confFullName+" </p>\n" +
				"			<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&lfloor;"+parFullName+" <a href=\""+Constants.opnisConfigurationParameterOf+"\">:isConfigurationParameterOf</a></p>\n" +
				"			<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&lfloor;<a href=\""+Constants.dpnbinary+"\">:parameterName</a> <input type=\"hidden\" name=\""+sid3+"\" value=\""+iparFullName+"\"><input type=\"hidden\" name=\""+pid3+"\" value=\""+Constants.dpnparameterName+"\"><input name=\""+oid3+"\" size=\"70\" type=\"text\" value=\"\"> </p>\n" +
				"			<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&lfloor;<a href=\""+Constants.dpnbinary+"\">:parameterValue</a> <input type=\"hidden\" name=\""+sid4+"\" value=\""+iparFullName+"\"><input type=\"hidden\" name=\""+pid4+"\" value=\""+Constants.dpnparameterValue+"\"><input name=\""+oid4+"\" size=\"70\" type=\"text\" value=\"\"> </p>\n" +
				"			<hr><h4>Dependant Concrete Workflows:</h4><br><br>\n";
		
		
		int i=0;
		String id = getStackSid(istackFullname);
		for (String cwf : cwfs)
		{
			String fullName =  cwf.substring(cwf.indexOf("#")+1)+Constants.SoftwareStackSuffix;
			String ifullName = Constants.resClassSoftwareStack + fullName;

			String ssid = id + "_" + i;
			
			res += "			<input type=\"checkbox\" name=\"" + ssid + "\" value=\"" + ifullName + "\"> " + ifullName + "<br>\n";
			
			i++;
			
		}
			
		
		
		res += "			</section>\n" +
				"			<div class=\"clear\"></div>\n" +
				"		</div>\n" +
				"	</div>";
		
		
		addStatement(iconfFullName,cnIsAClass,Constants.cnConfigurationInfo);
		addStatement(istackFullname,cnIsAClass,Constants.cnSoftwareStack);
		addStatement(icomponentFullName,cnIsAClass,Constants.cnSoftwareComponent);
		addStatement(iparFullName,cnIsAClass,Constants.cnConfigurationParameter);
		addStatement(iparFullName,cnIsAClass,Constants.cnPPlanVariable);
		

		addStatement(iparFullName,Constants.opnisConfigurationParameterOf,iconfFullName);
		addStatement(iparFullName,Constants.opnisInputVarOf,iconfFullName);
		
		
		addStatement(istackFullname,Constants.opnhasSoftwareComponent,icomponentFullName);
		addStatement(icomponentFullName,Constants.opnhasConfigurationInfo,iconfFullName);
		
		
		return res;
	}
	
	private static String getStackSid(String stackId)
	{
		String itId = stackId;
		stackNoit++;
		
		return itId;
	}
}
