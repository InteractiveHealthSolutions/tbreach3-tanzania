/* Copyright(C) 2015 Interactive Health Solutions, Pvt. Ltd.

This program is free software; you can redistribute it and/or modify it under the terms of the GNU General Public License as
published by the Free Software Foundation; either version 3 of the License (GPLv3), or any later version.
This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.

See the GNU General Public License for more details. You should have received a copy of the GNU General Public License along with this program; if not, write to the Interactive Health Solutions, info@ihsinformatics.com
You can also access the license on the internet at the address: http://www.gnu.org/licenses/gpl-3.0.html

Interactive Health Solutions, hereby disclaims all copyright interest in this program written by the contributors. */
/**
 * Sputum Results form for Cough Monitor 
 */

package com.ihsinformatics.tbreach3tanzania.mobile;

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
public class SmearResultsForm extends BaseForm implements CommandListener, ItemStateListener
{
	String formType = FormType.SMEAR_RESULTS;
	// Mobile fields
	DateField	dateEntered;
	StringItem	userName;
	TextField	laboratoryId;
	TextField	patientId;
	ChoiceGroup	sampleType;
	ChoiceGroup	sampleCollected;
	DateField	dateCollected;
	DateField	resultDate;
	ChoiceGroup	smearResult;
	TextField	otherResult;
	ChoiceGroup	reasonNotCollected;
	TextField	otherReasonNotCollected;

	Command		okCommand	= new Command ("Save", Command.OK, 1);
	Command		backCommand	= new Command ("Back", Command.BACK, 2);

	public SmearResultsForm (String title, TBReach3TanzaniaMain tbreach3tanzaniaMidlet)
	{
		super (title, tbreach3tanzaniaMidlet);
	}

	public void init ()
	{
		dateEntered = new DateField ("Date", DateField.DATE);
		dateEntered.setDate (new Date ());
		userName = new StringItem ("User Name:", TBRT.getCurrentUserName ());
		laboratoryId = new TextField ("*Laboratory ID:", "", TBRT.ID_LENGTH, TextField.ANY);
		patientId = new TextField ("*Patient/Suspect's ID:", "", TBRT.ID_LENGTH, TextField.NUMERIC);
		sampleType = new ChoiceGroup ("Sputum Sample Type:", ChoiceGroup.POPUP);
		sampleCollected = new ChoiceGroup ("Was Sputum sample collected?", ChoiceGroup.POPUP);
		dateCollected = new DateField ("Sample Collection Date:", DateField.DATE);
		dateCollected.setDate (new Date ());
		resultDate = new DateField ("Smear Result Date:", DateField.DATE);
		resultDate.setDate (new Date ());
		smearResult = new ChoiceGroup ("Smear Result:", ChoiceGroup.POPUP);
		otherResult = new TextField ("Other Smear Result:", "", TBRT.VALUE_LENGTH, TextField.ANY);
		reasonNotCollected = new ChoiceGroup ("Unable to collect Sputum because..", ChoiceGroup.POPUP);
		otherReasonNotCollected = new TextField ("Other reason:", "", TBRT.OPENTEXT_LENGTH, TextField.ANY);

		String[] results = Metadata.getMetaValues (Metadata.SMEAR_RESULT);
		for (int i = 0; i < results.length; i++)
			smearResult.append (results[i], null);
		sampleType.append ("Spot", null);
		sampleType.append ("Morning", null);
		sampleCollected.append ("Yes", null);
		sampleCollected.append ("No", null);
		reasonNotCollected.append ("Unable to Produce Sputum", null);
		reasonNotCollected.append ("Communication Barrier", null);
		reasonNotCollected.append ("Refused", null);
		reasonNotCollected.append ("Other", null);

		formItems = new Item[] {dateEntered, userName, laboratoryId, patientId, sampleType, sampleCollected, dateCollected, resultDate, smearResult, otherResult, reasonNotCollected,
				otherReasonNotCollected};
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
		hide (otherResult);
		hide (reasonNotCollected);
		hide (otherReasonNotCollected);
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

		if (StringUtil.getString (patientId).equals (""))
			sb.append ("Suspect/Patient ID: " + ErrorProvider.getError (ErrorProvider.EMPTY_DATA_ERROR) + "\n");
		if (DateTimeUtil.isDateInFuture (dateEntered.getDate ()))
			sb.append (ErrorProvider.getError (ErrorProvider.INVALID_DATE_OR_TIME) + "\n");
		if (dateCollected.getDate () != null)
		{
			if (DateTimeUtil.isDateInFuture (dateCollected.getDate ()))
				sb.append ("Smear Collection Date: " + ErrorProvider.getError (ErrorProvider.INVALID_DATE_OR_TIME) + "\n");
		}
		if (resultDate.getDate () != null)
		{
			if (DateTimeUtil.isDateInFuture (resultDate.getDate ()))
				sb.append ("Smear Result Date: " + ErrorProvider.getError (ErrorProvider.INVALID_DATE_OR_TIME) + "\n");
		}
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
		else if (i == sampleCollected)
		{
			if (StringUtil.getString (i).equals ("Yes"))
			{
				show (dateCollected);
				show (resultDate);
				show (smearResult);
				hide (reasonNotCollected);
				hide (otherReasonNotCollected);
			}
			else
			{
				hide (dateCollected);
				hide (resultDate);
				hide (smearResult);
				hide (otherResult);
				show (reasonNotCollected);
				if (StringUtil.getString (reasonNotCollected).equals ("Other"))
					show (otherReasonNotCollected);
			}
		}
		else if (i == smearResult)
		{
			if (StringUtil.getString (i).equals ("Other"))
			{
				show (otherResult);
			}
			else
			{
				hide (otherResult);
			}
		}
		else if (i == reasonNotCollected)
		{
			if (StringUtil.getString (i).equals ("Other"))
			{
				show (otherReasonNotCollected);
			}
			else
			{
				hide (otherReasonNotCollected);
			}
		}
		else if (i == dateCollected || i == resultDate)
		{
			Date cDate = dateCollected.getDate ();
			Date rDate = resultDate.getDate ();
			if (rDate.getTime () < cDate.getTime ())
				resultDate.setDate (cDate);
		}
	}

	public void commandAction (Command command, Displayable displayable)
	{
		if (command == okCommand)
		{
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
		request.append ("&patientId=" + StringUtil.getString (patientId));
		request.append ("&locationId=" + StringUtil.getString (laboratoryId));

		request.append ("&results=[");
		request.append ("F_DATE=" + DateTimeUtil.getDateTime (dateEntered.getDate ()) + ";");
		request.append ("LAB_ID=" + StringUtil.getString (laboratoryId) + ";");
		request.append ("SPUT_TYPE=" + StringUtil.getString (sampleType).charAt (0) + ";");
		request.append ("COLLECTED=" + StringUtil.getString (sampleCollected).charAt (0) + ";");
		if (StringUtil.getString (sampleCollected).charAt (0) == 'Y')
		{
			request.append ("COL_DATE=" + DateTimeUtil.getDateTime (dateCollected.getDate ()) + ";");
			request.append ("RES_DATE=" + DateTimeUtil.getDateTime (resultDate.getDate ()) + ";");
			// Since + sign converts into a space in query string, it must be
			// replaced with %2B
			request.append ("RESULT=" + StringUtil.replaceAll ("+", "%2B", StringUtil.getString (smearResult)) + ";");
			request.append ("OTH_RESULT=" + StringUtil.getString (otherResult) + ";");
		}
		else
		{
			request.append ("REASON=" + StringUtil.getString (reasonNotCollected) + ";");
			request.append ("OTH_REASON=" + StringUtil.getString (otherReasonNotCollected) + ";");
		}
		request.append ("]");
		return request.toString ();
	}
}
