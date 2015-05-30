/* Copyright(C) 2015 Interactive Health Solutions, Pvt. Ltd.

This program is free software; you can redistribute it and/or modify it under the terms of the GNU General Public License as
published by the Free Software Foundation; either version 3 of the License (GPLv3), or any later version.
This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.

See the GNU General Public License for more details. You should have received a copy of the GNU General Public License along with this program; if not, write to the Interactive Health Solutions, info@ihsinformatics.com
You can also access the license on the internet at the address: http://www.gnu.org/licenses/gpl-3.0.html

Interactive Health Solutions, hereby disclaims all copyright interest in this program written by the contributors. */
/**
 * Comparison Review form for Field Coordinators
 */

package com.ihsinformatics.tbreach3tanzania.mobile;

import java.util.Calendar;
import java.util.Date;
import java.util.Hashtable;
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
import com.ihsinformatics.tbreach3tanzania.mobile.shared.InfoProvider;
import com.ihsinformatics.tbreach3tanzania.mobile.shared.Metadata;
import com.ihsinformatics.tbreach3tanzania.mobile.shared.TBRT;
import com.ihsinformatics.tbreach3tanzania.mobile.util.DateTimeUtil;
import com.ihsinformatics.tbreach3tanzania.mobile.util.StringUtil;

/**
 * @author owais.hussain@irdresearch.org
 * 
 */
public class ComparisonReviewForm extends BaseForm implements CommandListener, ItemStateListener
{
	String formType = FormType.COMPARISON_REVIEW;
	// Mobile fields
	DateField	dateEntered;
	StringItem	userName;
	ChoiceGroup	month;
	ChoiceGroup	year;
	ChoiceGroup	district;
	ChoiceGroup	facility;
	TextField	newCases;
	TextField	treatmentAfterDefault;
	TextField	relapse;
	TextField	extraPulmonary;
	TextField	moved;
	TextField	died;
	StringItem	total;

	Command		okCommand	= new Command ("Save", Command.OK, 1);
	Command		backCommand	= new Command ("Back", Command.BACK, 2);

	public ComparisonReviewForm (String title, TBReach3TanzaniaMain tbreach3tanzaniaMidlet)
	{
		super (title, tbreach3tanzaniaMidlet);
	}

	public void init ()
	{
		dateEntered = new DateField ("Date", DateField.DATE);
		dateEntered.setDate (new Date ());
		userName = new StringItem ("User Name:", TBRT.getCurrentUserName ());
		month = new ChoiceGroup ("Month:", ChoiceGroup.POPUP, Metadata.getMetaValues (Metadata.MONTH), null);
		year = new ChoiceGroup ("Year:", ChoiceGroup.POPUP);
		Calendar c = Calendar.getInstance ();
		c.setTime (new Date());
		int currentYear = c.get (Calendar.YEAR);
		int currentMonth = c.get (Calendar.MONTH);
		

		if(currentMonth==0) {
			currentMonth=11;
			currentYear = currentYear-1;
		}
		
		else {
			currentMonth = currentMonth - 1;
		}
		
		year.append (String.valueOf (currentYear), null);
		year.append (String.valueOf (currentYear - 1), null);
		year.append (String.valueOf (currentYear - 2), null);
		
		for(int i=0;i<month.size();i++) {
			if(i==currentMonth) {
				month.setSelectedIndex (i, true);
				break;
			}
		}
		
		for(int i=0;i<year.size();i++) {
			if(year.getString (i).equals(String.valueOf(currentYear))) {
				year.setSelectedIndex (i, true);
				break;
			}
		}
		
		district = new ChoiceGroup ("District:", ChoiceGroup.POPUP, Metadata.getMetaValues (Metadata.DISTRICT), null);
		facility = new ChoiceGroup ("Facility:", ChoiceGroup.POPUP);
		newCases = new TextField ("New Cases:", "0", 4, TextField.NUMERIC);
		treatmentAfterDefault = new TextField ("Treatment After Default:", "0", 4, TextField.NUMERIC);
		relapse = new TextField ("Relapse:", "0", 4, TextField.NUMERIC);
		extraPulmonary = new TextField ("Extra-Pulmonary:", "0", 4, TextField.NUMERIC);
		moved = new TextField ("Moved:", "0", 4, TextField.NUMERIC);
		died = new TextField ("Died before Treatment:", "0", 4, TextField.NUMERIC);
		total = new StringItem ("Total:", "0");

		formItems = new Item[] {dateEntered, userName, month, year, district, facility, newCases, treatmentAfterDefault, relapse, extraPulmonary, moved, total};
		startTimestamp = new Date ();

		initialShow ();

		addCommand (okCommand);
		addCommand (backCommand);

		setCommandListener (this);
		setItemStateListener (this);
	}

	/**
	 * Shows/Hides form items initially
	 */
	private void initialShow ()
	{
		deleteAll ();
		// Add Items to form
		for (int i = 0; i < formItems.length; i++)
			append (formItems[i]);
	}

	/**
	 * Validation method before submitting form
	 * 
	 * @return
	 */
	private boolean validate ()
	{
		boolean result = true;
		StringBuffer sb = new StringBuffer ();

		if (DateTimeUtil.isDateInFuture (dateEntered.getDate ()))
			sb.append (ErrorProvider.getError (ErrorProvider.INVALID_DATE_OR_TIME));
		if (StringUtil.getString (facility).equals (""))
			sb.append ("Facility: " + ErrorProvider.getError (ErrorProvider.EMPTY_DATA_ERROR));
		result = sb.length () == 0;
		if (result == false)
			tbreach3tanzaniaMidlet.showAlert (sb.toString (), null);
		return result;
	}

	/**
	 * Item change Listener method
	 */
	public void itemStateChanged (Item i)
	{
		if (i instanceof TextField)
		{
			int tot = 0;
			TextField[] items = {newCases, treatmentAfterDefault, relapse, extraPulmonary, moved};
			for (int cnt = 0; cnt < items.length; cnt++)
			{
				String str = StringUtil.getString (items[cnt]);
				if (!str.equals (""))
					tot += Integer.parseInt (str);
			}
			total.setText (String.valueOf (tot));
		}
		else if (i == district)
		{
			facility.deleteAll ();
			String[][] facilities = Metadata.getMetadata (Metadata.FACILITY);
			if (facilities.length > 0)
			{
				String districtId = Metadata.getKey (Metadata.DISTRICT, StringUtil.getString (i));
				for (int j = 0; j < facilities.length; j++)
				{
					String idPart = facilities[j][1].substring (facilities[j][1].indexOf ("-") + 1);
					if (districtId.equals (idPart))
						facility.append (facilities[j][0], null);
				}
			}
		}
	}

	public void commandAction (Command command, Displayable displayable)
	{
		if (command == okCommand)
		{
			// Check for empty fields
			TextField[] items = {newCases, treatmentAfterDefault, relapse, extraPulmonary, moved};
			for (int i = 0; i < items.length; i++)
				if (StringUtil.getString (items[i]).equals (""))
					items[i].setString ("0");
			if (validate ())
			{
				String request = createRequestPayload ();
				System.out.println (request);
				Hashtable model = tbreach3tanzaniaMidlet.sendToServer (request);

				if (model != null)
				{
					String status = (String) model.get ("status");
					if (status.equals (TBRT.XML_SUCCESS))
					{
						System.out.println ("success");
						tbreach3tanzaniaMidlet.showAlert (InfoProvider.getInfo (InfoProvider.OPERATION_SUCCESSFUL), null);
						TBRT.setLastFormSubmitted (formType);
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
		request.append ("&dateEntered=" + dateEntered.getDate ().getTime ());

		request.append ("&results=[");
		request.append ("F_DATE=" + DateTimeUtil.getDateTime (dateEntered.getDate ()) + ";");
		request.append ("YEAR=" + StringUtil.getString (year) + ";");
		request.append ("MONTH=" + Metadata.getKey (Metadata.MONTH, StringUtil.getString (month)) + ";");
		request.append ("DISTRICT=" + Metadata.getKey (Metadata.DISTRICT, StringUtil.getString (district)) + ";");
		request.append ("FACILITY=" + Metadata.getKey (Metadata.FACILITY, StringUtil.getString (facility)) + ";");
		request.append ("NEW_CASES=" + StringUtil.getString (newCases) + ";");
		request.append ("TX_AFTER_DEFAULT=" + StringUtil.getString (treatmentAfterDefault) + ";");
		request.append ("RELAPSE=" + StringUtil.getString (relapse) + ";");
		request.append ("EP=" + StringUtil.getString (extraPulmonary) + ";");
		request.append ("MOVED=" + StringUtil.getString (moved) + ";");
		request.append ("DIED=" + StringUtil.getString (died) + ";");
		request.append ("]");
		return request.toString ();
	}
}
