/* Copyright(C) 2015 Interactive Health Solutions, Pvt. Ltd.

This program is free software; you can redistribute it and/or modify it under the terms of the GNU General Public License as
published by the Free Software Foundation; either version 3 of the License (GPLv3), or any later version.
This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.

See the GNU General Public License for more details. You should have received a copy of the GNU General Public License along with this program; if not, write to the Interactive Health Solutions, info@ihsinformatics.com
You can also access the license on the internet at the address: http://www.gnu.org/licenses/gpl-3.0.html

Interactive Health Solutions, hereby disclaims all copyright interest in this program written by the contributors. */
/**
 * Sites data reporting form for Field Coordinators
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
public class SitesDataForm extends BaseForm implements CommandListener, ItemStateListener
{
	String formType = FormType.SITES_DATA;
	// Mobile fields
	DateField	dateEntered;
	StringItem	userName;
	ChoiceGroup	month;
	ChoiceGroup	year;
	ChoiceGroup site;
	StringItem	labRegister;
	TextField	maleScreened;
	TextField	femaleScreened;
	StringItem	treatmentRegister;
	TextField	smearPositive;
	TextField	cxr;
	TextField	epCases;
	TextField	paediatricCases;
	TextField	other;
	TextField	smearPositiveTreated;
	TextField	maleScreenedForHIV;
	TextField	femaleScreenedForHIV;
	TextField	maleFoundHIV;
	TextField	femaleFoundHIV;

	Command		okCommand	= new Command ("Save", Command.OK, 1);
	Command		backCommand	= new Command ("Back", Command.BACK, 2);

	public SitesDataForm (String title, TBReach3TanzaniaMain tbreach3tanzaniaMidlet)
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
		site = new ChoiceGroup ("Site:", ChoiceGroup.POPUP, Metadata.getMetaValues (Metadata.AMPATH_SITES), null);
		labRegister = new StringItem ("LAB REGISTER:", "");
		maleScreened = new TextField ("Males Screened:", "0", 4, TextField.NUMERIC);
		femaleScreened = new TextField ("Females Screened:", "0", 4, TextField.NUMERIC);
		treatmentRegister = new StringItem ("TREATMENT REGISTER:", "");
		smearPositive = new TextField ("Smear Positives:", "0", 4, TextField.NUMERIC);
		cxr = new TextField ("Chest X-Rays:", "0", 4, TextField.NUMERIC);
		epCases = new TextField ("Extra-Pulmonary Cases:", "0", 4, TextField.NUMERIC);
		paediatricCases = new TextField ("Child Cases:", "0", 4, TextField.NUMERIC);
		other = new TextField ("Others:", "0", 4, TextField.NUMERIC);
		smearPositiveTreated = new TextField ("No of smear positive patients treated:", "0", 4, TextField.NUMERIC);
		maleScreenedForHIV = new TextField ("Male Patients Screened for HIV attending Clinic:", "0", 4, TextField.NUMERIC);
		femaleScreenedForHIV = new TextField ("Female Patients Screened for HIV attending Clinic:", "0", 4, TextField.NUMERIC);
		maleFoundHIV = new TextField ("Male Patients Screened and found HIV Positive:", "0", 4, TextField.NUMERIC);
		femaleFoundHIV = new TextField ("Female Patients Screened and found HIV Positive:", "0", 4, TextField.NUMERIC);
		formItems = new Item[] {dateEntered, userName, month, year, site, labRegister, maleScreened, femaleScreened, treatmentRegister, smearPositive, cxr, epCases, paediatricCases, other, smearPositiveTreated, maleScreenedForHIV,
				femaleScreenedForHIV, maleFoundHIV, femaleFoundHIV};
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
		int maleScreenedNum = Integer.parseInt (StringUtil.getString (maleScreened));
		int femaleScreenedNum = Integer.parseInt (StringUtil.getString (femaleScreened));
		int smearPositiveNum = Integer.parseInt (StringUtil.getString (smearPositive));
		int smearPositiveTreatedNum = Integer.parseInt (StringUtil.getString (smearPositiveTreated));
		int maleScreenedForHIVNum = Integer.parseInt (StringUtil.getString (maleScreenedForHIV));
		int femaleScreenedForHIVNum = Integer.parseInt (StringUtil.getString (femaleScreenedForHIV));
		int maleFoundHIVNum = Integer.parseInt (StringUtil.getString (maleFoundHIV));
		int femaleFoundHIVNum = Integer.parseInt (StringUtil.getString (femaleFoundHIV));
		if (smearPositiveTreatedNum > smearPositiveNum)
			sb.append ("Smear Positive Cases Treated cannot be greater than Smear Positive Cases" + "\n");
		if (maleScreenedForHIVNum > maleScreenedNum)
			sb.append ("Male Patients attending Clinic and Screened for HIV cannot be greater than Male Screened" + "\n");
		if (femaleScreenedForHIVNum > femaleScreenedNum)
			sb.append ("Female Patients attending Clinic and Screened for HIV cannot be greater than Female Screened" + "\n");
		if (maleFoundHIVNum > maleScreenedForHIVNum)
			sb.append ("Male Patients found HIV Positive cannot be greater than Male Screened" + "\n");
		if (femaleFoundHIVNum > femaleScreenedForHIVNum)
			sb.append ("Female Patients found HIV Positive cannot be greater than Female Screened" + "\n");
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
			return;
	}

	public void commandAction (Command command, Displayable displayable)
	{
		if (command == okCommand)
		{
			// Check for empty fields
			TextField[] items = {maleScreened, femaleScreened, smearPositive, cxr, epCases, paediatricCases, other, smearPositiveTreated, maleScreenedForHIV, femaleScreenedForHIV, maleFoundHIV,
					femaleFoundHIV};
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
		request.append ("SITE=" + Metadata.getKey (Metadata.AMPATH_SITES, StringUtil.getString (site)) + ";");
		request.append ("LR_MALE_SCREEN=" + StringUtil.getString (maleScreened) + ";");
		request.append ("LR_FEMALE_SCREEN=" + StringUtil.getString (femaleScreened) + ";");
		request.append ("TR_SM_POSITIVE=" + StringUtil.getString (smearPositive) + ";");
		request.append ("TR_CXR=" + StringUtil.getString (cxr) + ";");
		request.append ("TR_EP_CASES=" + StringUtil.getString (epCases) + ";");
		request.append ("TR_PAED_CASES=" + StringUtil.getString (paediatricCases) + ";");
		request.append ("TR_OTHER=" + StringUtil.getString (other) + ";");
		request.append ("SM_POSITIVE_TREATED=" + StringUtil.getString (smearPositiveTreated) + ";");
		request.append ("MALE_SCREENED_HIV=" + StringUtil.getString (maleScreenedForHIV) + ";");
		request.append ("FEMALE_SCREENED_HIV=" + StringUtil.getString (femaleScreenedForHIV) + ";");
		request.append ("MALE_HIV_POSITIVE=" + StringUtil.getString (maleFoundHIV) + ";");
		request.append ("FEMALE_HIV_POSITIVE=" + StringUtil.getString (femaleFoundHIV) + ";");
		request.append ("]");
		return request.toString ();
	}
}
