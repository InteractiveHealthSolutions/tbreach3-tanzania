
package com.ihsinformatics.tbreach3tanzania.mobile;

import java.util.Hashtable;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;

class ReportDisplayForm extends BaseForm implements CommandListener
{
	private Command				cmdBack;

	private Hashtable	queryData;
	private String		labid;
	private String		reportDesc;

	public ReportDisplayForm (String title, TBReach3TanzaniaMain tbrMidlet)
	{
		super (title, tbrMidlet);
	}

	public void init ()
	{
		String data = null;

		if (queryData != null)
		{
			data = (String) queryData.get ("data");
			if (data != null && !data.trim ().equals (""))
			{
				data = (String) queryData.get ("data");
			}
			else
			{
				data = "No Data Found for given Lab ID.";
			}
			append (reportDesc);
			if(labid != null && !labid.trim().equals("")){
				append ("LAB ID : " + labid + "\n");
			}
			append (data);

			cmdBack = new Command ("Home", Command.BACK, 1);
			addCommand (cmdBack);

			setCommandListener (this);
		}
	}

	public void setLabid (String labid)
	{
		this.labid = labid;
	}

	public String getLabid ()
	{
		return labid;
	}

	public Hashtable getQueryData ()
	{
		return queryData;
	}

	public void setQueryData (Hashtable queryData)
	{
		this.queryData = queryData;
	}

	public void setReportDesc (String reportDesc)
	{
		this.reportDesc = reportDesc;
	}

	public String getReportDesc ()
	{
		return reportDesc;
	}

	public void commandAction (Command c, Displayable d)
	{
		if (c == cmdBack)
		{
			deleteAll ();
			removeCommand (cmdBack);
			tbreach3tanzaniaMidlet.setDisplay (prevDisplayable);
		}
	}
}
