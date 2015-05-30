/* Copyright(C) 2015 Interactive Health Solutions, Pvt. Ltd.

This program is free software; you can redistribute it and/or modify it under the terms of the GNU General Public License as
published by the Free Software Foundation; either version 3 of the License (GPLv3), or any later version.
This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.

See the GNU General Public License for more details. You should have received a copy of the GNU General Public License along with this program; if not, write to the Interactive Health Solutions, info@ihsinformatics.com
You can also access the license on the internet at the address: http://www.gnu.org/licenses/gpl-3.0.html

Interactive Health Solutions, hereby disclaims all copyright interest in this program written by the contributors. */

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
