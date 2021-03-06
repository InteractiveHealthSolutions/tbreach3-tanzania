/* Copyright(C) 2015 Interactive Health Solutions, Pvt. Ltd.

This program is free software; you can redistribute it and/or modify it under the terms of the GNU General Public License as
published by the Free Software Foundation; either version 3 of the License (GPLv3), or any later version.
This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.

See the GNU General Public License for more details. You should have received a copy of the GNU General Public License along with this program; if not, write to the Interactive Health Solutions, info@ihsinformatics.com
You can also access the license on the internet at the address: http://www.gnu.org/licenses/gpl-3.0.html

Interactive Health Solutions, hereby disclaims all copyright interest in this program written by the contributors. */


package com.ihsinformatics.tbreach3tanzania.mobile;

import java.util.Date;
import java.util.Hashtable;
import javax.microedition.lcdui.Choice;
import javax.microedition.lcdui.ChoiceGroup;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.DateField;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Item;
import javax.microedition.lcdui.ItemStateListener;
import javax.microedition.lcdui.StringItem;
import javax.microedition.lcdui.TextField;
import com.ihsinformatics.tbreach3tanzania.mobile.shared.ErrorProvider;
import com.ihsinformatics.tbreach3tanzania.mobile.shared.FormType;
import com.ihsinformatics.tbreach3tanzania.mobile.shared.Metadata;
import com.ihsinformatics.tbreach3tanzania.mobile.shared.TBRT;
import com.ihsinformatics.tbreach3tanzania.mobile.util.DateTimeUtil;
import com.ihsinformatics.tbreach3tanzania.mobile.util.StringUtil;

public class ReportList extends BaseForm implements CommandListener, ItemStateListener
{

	String		formType	= FormType.REPORT_LIST;
	// Mobile fields
	ChoiceGroup	reportList;
	StringItem filter;
	StringItem selectReport;
	ChoiceGroup date;
	DateField	dateFrom;
	DateField	dateTo;
	ChoiceGroup district;
	ChoiceGroup districtList;
	
	Command		okCommand	= new Command ("Show", Command.OK, 1);
	Command		backCommand	= new Command ("Back", Command.BACK, 2);

	public ReportList (String title, TBReach3TanzaniaMain tbreach3tanzaniaMidlet)
	{
		super (title, tbreach3tanzaniaMidlet);
	}

	public void init ()
	{
		
		selectReport = new StringItem("Reports: ","");
		reportList = new ChoiceGroup ("", Choice.EXCLUSIVE);
		reportList.append ("No. of Patient Screened", null);
		reportList.append ("No. of Suspect Identified", null);
		reportList.append ("TB Suspects Symptoms Stats", null);
		reportList.append ("No. of Confirmed Cases", null);
		reportList.append ("No. of Closed Cases",null);
		reportList.append ("Closed Cases Stats", null);
		filter = new StringItem("Filters: ","");
		date = new ChoiceGroup ("", Choice.MULTIPLE);
		date.append ("Date", null);
		dateFrom = new DateField ("from: ", DateField.DATE);
		dateTo = new DateField ("to: ", DateField.DATE);
		dateFrom.setDate (new Date ());
		dateTo.setDate (new Date ());
		district = new ChoiceGroup ("", Choice.MULTIPLE);
		district.append ("District", null);
		districtList = new ChoiceGroup ("", Choice.EXCLUSIVE);
		String[] districts = Metadata.getMetaValues (Metadata.DISTRICT);
		for (int i = 0; i < districts.length; i++)
			districtList.append (districts[i], null);
		
		formItems = new Item[] { selectReport, reportList , filter , date , dateFrom , dateTo , district, districtList}; 
		startTimestamp = new Date ();
		initialShow ();
		
		startTimestamp = new Date ();
		// Add commands
		addCommand (okCommand);
		addCommand (backCommand);
		// Add listeners
		setCommandListener (this);
		setItemStateListener (this);

	}

	/**
	 * Shows/Hides form items initially
	 */
	private void initialShow ()
	{
		deleteAll ();
		for (int i = 0; i < formItems.length; i++)
			append (formItems[i]);
		
		hide (dateFrom);
		hide (dateTo);
		hide (districtList);
	}

	/**
	 * Validation method before submitting form
	 * 
	 * @return
	 */
	private boolean validate ()
	{

		StringBuffer sb = new StringBuffer ();

		if (StringUtil.isStringSelected (date, "Date", true)){
			// Date should not be of future
			if (DateTimeUtil.isDateInFuture (dateFrom.getDate ()))
				sb.append ("Date From: " + ErrorProvider.getError (ErrorProvider.INVALID_DATE_OR_TIME) + "\n");
		
			if (DateTimeUtil.isDateInFuture (dateTo.getDate ()))
				sb.append ("Date To: " + ErrorProvider.getError (ErrorProvider.INVALID_DATE_OR_TIME) + "\n");
		
			// Treatment Started and Completion Date validation
			if(DateTimeUtil.isDateAfter (dateFrom.getDate (),dateTo.getDate ()))
				sb.append ("Inavlid Dates" + "\n");
		}
		
		if (sb.length () != 0)
		{
			tbreach3tanzaniaMidlet.showAlert (sb.toString (), null);
			return false;
		}
	
		return true;
	}

	/**
	 * Item change Listener method
	 */
	public void itemStateChanged (Item i)
	{
		if (i instanceof TextField)
			return;
		else if (i == date)
		{
		   if (StringUtil.isStringSelected (date, "Date", true))
		   {
			   show (dateFrom);
		   	   show (dateTo);
		   }
		   else
		   {
			   hide (dateFrom);
		   	   hide (dateTo);
		   }
		}
		else if (i == district)
		{
			if (StringUtil.isStringSelected (district, "District", true))
				show (districtList);
			else
			    hide (districtList);			
		}	
	}

	public void commandAction (Command command, Displayable displayable)
	{
		if (command == okCommand)
		{
			
				String request = createRequestPayload ();
				System.out.println (request);
				Hashtable model = tbreach3tanzaniaMidlet.sendToServer (request);

				if (model != null)
				{
					String status = (String) model.get ("status");
					if (status.equals (TBRT.XML_RESULT))
					{
						System.out.println ("success");
						tbreach3tanzaniaMidlet.showAlert ("RESULT: " + (String) model.get ("msg"), null);
						TBRT.setLastOperationTimestamp (new Date ().getTime ());
						init ();
					}
					else if (status.equals (TBRT.XML_ERROR))
					{
						System.out.println ((String) model.get ("msg"));
						tbreach3tanzaniaMidlet.showAlert ("ERROR: " + (String) model.get ("msg"), null);
					}
				}
			
		}
		else if (command == backCommand)
		{
			deleteAll ();
			removeCommand (okCommand);
			removeCommand (backCommand);
			tbreach3tanzaniaMidlet.setDisplay (prevDisplayable);
		}
	}

	protected String createRequestPayload ()
	{
		StringBuffer request = new StringBuffer ();
		request.append ("appver=" + TBRT.VERSION);
		request.append ("&form=" + formType);
		request.append ("&startTimestamp=" + startTimestamp.getTime ());
		request.append ("&endTimestamp=" + new Date ().getTime ());
		request.append ("&userName=" + TBRT.getCurrentUserName ());
		request.append ("&report=" + StringUtil.getString (reportList));
				
		if (StringUtil.isStringSelected (date, "Date", true))
		{
			request.append ("&dateFlag=" + "true");
			request.append ("&dateTo=" + DateTimeUtil.getDateTime (dateTo.getDate ()));
			request.append ("&dateFrom=" + DateTimeUtil.getDateTime (dateFrom.getDate ()));
		}
		else request.append ("&dateFlag=" + "false");
		
		if (StringUtil.isStringSelected (district, "District", true))
		{
			request.append ("&districtFlag=" + "true");
			request.append ("&district=" + Metadata.getKey (Metadata.DISTRICT, StringUtil.getString (districtList)));		
		}
		else request.append ("&districtFlag=" + "false");
		
		return request.toString ();
	}

}
