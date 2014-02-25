package net.oegupm.wicus.webannotator.utils;

import java.io.IOException;
import java.util.ArrayList;

public class WfStepsHandler {
	
	private ArrayList<String> wfsteps;
	
	public WfStepsHandler(String inputLinesPath)
	{
		try {
			wfsteps = FileArrayProvider.readLines(inputLinesPath);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public ArrayList<String> getSubworkflows()
	{
		ArrayList<String> res = new ArrayList<String>();
		
		for (String wfs : wfsteps)
		{
			if((wfs.contains(">")))
			{
				//String name = wfs.substring(wfs.indexOf("#")+1);
				res.add(wfs);
			}
		}
		
		return res;
	}
	

	
	public ArrayList<String> getAbstractSubworkflows()
	{
		ArrayList<String> res = new ArrayList<String>();
		
		for (String wfs : wfsteps)
		{
			if((wfs.contains(">a"))&&(!(wfs.contains(">>"))))
			{
				//String name = wfs.substring(wfs.indexOf("#")+1);
				res.add(wfs);
			}
		}
		
		return res;
	}
	
	

	
	public ArrayList<String> getConcreteSubworkflows()
	{
		ArrayList<String> res = new ArrayList<String>();
		
		for (String wfs : wfsteps)
		{
			if((wfs.contains(">c"))&&(!(wfs.contains(">>"))))
			{
				//String name = wfs.substring(wfs.indexOf("#")+1);
				res.add(wfs);
			}
		}
		
		return res;
	}
	

	
	public ArrayList<String> getAllConcreteSubworkflows()
	{
		ArrayList<String> res = new ArrayList<String>();
		
		for (String wfs : wfsteps)
		{
			if(wfs.contains(">c"))
			{
				//String name = wfs.substring(wfs.indexOf("#")+1);
				res.add(wfs);
			}
		}
		
		return res;
	}
	

	
	public ArrayList<String> getAbstractConcreteSubworkflows()
	{
		ArrayList<String> res = new ArrayList<String>();
		
		for (String wfs : wfsteps)
		{
			if(wfs.contains(">>c"))
			{
				//String name = wfs.substring(wfs.indexOf("#")+1);
				res.add(wfs);
			}
		}
		
		return res;
	}
	
	public String getWorkflow()
	{
		return wfsteps.get(0).substring(wfsteps.get(0).indexOf("#")+1);
	}

}
