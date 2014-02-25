<%@page import="net.oegupm.wicus.webannotator.utils.HtmlCodePrinter"%>
<%@page import="net.oegupm.wicus.webannotator.utils.Constants"%>
<%@page import="com.hp.hpl.jena.ontology.DatatypeProperty"%>
<%@page import="com.hp.hpl.jena.ontology.Individual"%>
<%@page import="com.hp.hpl.jena.ontology.ObjectProperty"%>
<%@page import="com.hp.hpl.jena.ontology.OntClass"%>
<%@page import="com.hp.hpl.jena.ontology.OntModel"%>
<%@page import="com.hp.hpl.jena.ontology.OntModelSpec"%>
<%@page import="com.hp.hpl.jena.rdf.model.Literal"%>
<%@page import="com.hp.hpl.jena.rdf.model.ModelFactory"%>
<%@page import="com.hp.hpl.jena.rdf.model.Statement"%>
<%@page import="com.hp.hpl.jena.rdf.model.StmtIterator"%>
<%@page import="com.hp.hpl.jena.util.iterator.ExtendedIterator"%>
<%@page import="com.hp.hpl.jena.rdf.model.Resource"%>
<%@page import="com.hp.hpl.jena.rdf.model.Property"%>
<%@page import="com.hp.hpl.jena.rdf.model.Statement"%>
<%@page import="com.hp.hpl.jena.rdf.model.StmtIterator"%>
<%@page import="java.io.FileOutputStream"%>
<%@page import="java.util.Iterator "%>


<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%

System.out.println("LETS GO!");

String owlWicusFile = "wicus.owl";
String owlWicusFileOut = "wicus.tmp.inds.owl";

String inputFolder = request.getParameter("inputFolder");

OntModel mInd = ModelFactory.createOntologyModel( OntModelSpec.OWL_MEM );
OntModel model = ModelFactory.createOntologyModel( OntModelSpec.OWL_MEM );
model.read(inputFolder + owlWicusFile, null);


Iterator<ObjectProperty> it = model.listObjectProperties();

System.out.println("----OBJECT PROPERTIES----");
while (it.hasNext()) {
	Property ontclass = it.next();
	System.out.println(ontclass);
} 


System.out.println("----END OBJECT PROPERTIES----");


String pre = HtmlCodePrinter.idPreffix;
String res = "";

int noit = Integer.valueOf(request.getParameter("noit"));

res +="<h1>NOIT:"+noit+"</h1>";

for (int i = 0; i<noit; i++)
{
	String sid = pre + i + HtmlCodePrinter.sSuffix;
	String pid = pre + i +  HtmlCodePrinter.pSuffix;
	String oid = pre + i +  HtmlCodePrinter.oSuffix;

	String ss = request.getParameter(sid);
	String sp = request.getParameter(pid);
	String so = request.getParameter(oid);
	
	res += ss + "<br>\n" + sp + "<br>\n" + so + "<hr>\n";

	System.out.println("INTPUTS:\n" + ss + "|\n" + sp + "|\n" + so + "|\n-----------------------------");

	boolean flag = false;
	Resource s = model.getResource(ss);
	Property p = model.getObjectProperty(sp);
	if(p==null)
	{
		flag = true; //is datatype
		p = model.getDatatypeProperty(sp);
		System.out.println("data!");
	}
	Resource o = model.getResource(so);
	

	System.out.println("RESOURCES:\n" + s + "\n" + p + "\n" + o + "\n-----------------------------");
	
	if(sp.equals(HtmlCodePrinter.cnIsAClass))
	{
		OntClass cl = model.getOntClass(so);
		Individual ind = mInd.createIndividual(ss,cl);
		System.out.println(ss+"-INDIVIDUAL->"+ind.toString());
	}
	else
	{
		if(flag)
		{
			mInd.addLiteral(s, p, so);
		}
		else
		{
			mInd.add(s, p, o);
		}
	}
	
	
}

int stackNoit = Integer.valueOf(request.getParameter("stackNoit"));
int noCwfs = Integer.valueOf(request.getParameter("noCwfs"));
for (int i = 0; i<stackNoit; i++)
{
	String stack = request.getParameter(HtmlCodePrinter.inputStacksPreffix + i);

	System.out.println("STACK:"  + stack + "\n");
	
	for(int j=0; j<noCwfs;j++)
	{
		String check = request.getParameter(stack + "_" + j);
		
		if(check!=null)
		{
			System.out.println(stack + "_" + j + "--check:"  + check + "\n");
			Resource s = model.getResource(check);
			Property p = model.getObjectProperty(Constants.opndependsOn);
			Resource o = model.getResource(stack);
			mInd.add(s, p, o);
		}
		
	}

}




FileOutputStream indos = new FileOutputStream(inputFolder + owlWicusFileOut);
mInd.write(indos, "TURTLE");

System.out.println("File generated:"+inputFolder + owlWicusFileOut);


%>

<%= res %>
</body>
</html>