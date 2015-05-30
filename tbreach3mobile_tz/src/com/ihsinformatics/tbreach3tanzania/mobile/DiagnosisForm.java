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
import javax.microedition.lcdui.ChoiceGroup;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.DateField;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Item;
import javax.microedition.lcdui.ItemStateListener;
import javax.microedition.lcdui.TextField;
import com.ihsinformatics.tbreach3tanzania.mobile.shared.ErrorProvider;
import com.ihsinformatics.tbreach3tanzania.mobile.shared.FormType;
import com.ihsinformatics.tbreach3tanzania.mobile.shared.InfoProvider;
import com.ihsinformatics.tbreach3tanzania.mobile.shared.TBRT;
import com.ihsinformatics.tbreach3tanzania.mobile.util.StringUtil;

public class DiagnosisForm extends BaseForm implements CommandListener, ItemStateListener
{

	String		formType	= FormType.DIAGNOSIS;

	// Mobile fields
	DateField	dateEntered;
	TextField	patientScreenId;
	TextField	patientLabId;
	ChoiceGroup	tbDiagnosed;
	ChoiceGroup	methodDetection;
	ChoiceGroup	typeOfTb;
	ChoiceGroup	siteOfTb;
	ChoiceGroup	treatmentStarted;
	TextField	districtTbTreatmentNumber;

	Command		okCommand	= new Command ("Save", Command.OK, 1);
	Command		backCommand	= new Command ("Back", Command.BACK, 2);

	public DiagnosisForm (String title, TBReach3TanzaniaMain tbreach3tanzaniaMidlet)
	{
		super (title, tbreach3tanzaniaMidlet);
	}

	public void init ()
	{
		dateEntered = new DateField ("Date", DateField.DATE);
		dateEntered.setDate (new Date ());
		patientScreenId = new TextField ("*Patient Screening ID Number: ", "", TBRT.ID_LENGTH, TextField.ANY);
		patientLabId = new TextField ("*Patient/Client LAB ID Number: ", "99999999999", TBRT.ID_LENGTH, TextField.PHONENUMBER);
		tbDiagnosed = new ChoiceGroup ("*Is Suspect diagnosed to TB: ", ChoiceGroup.POPUP);
		methodDetection = new ChoiceGroup ("*Method of TB detection: ", ChoiceGroup.POPUP);
		typeOfTb = new ChoiceGroup ("*Type of TB:", ChoiceGroup.POPUP);
		siteOfTb = new ChoiceGroup ("Site of Extra-Pulmonary TB:", ChoiceGroup.POPUP);
		treatmentStarted = new ChoiceGroup ("*Patient's treatment started: ", ChoiceGroup.POPUP);
		districtTbTreatmentNumber = new TextField ("District TB Treatment Number", String.valueOf (9999999), 7, TextField.NUMERIC);

		tbDiagnosed.append ("Yes", null);
		tbDiagnosed.append ("No", null);

		treatmentStarted.append ("Yes", null);
		treatmentStarted.append ("No", null);

		methodDetection.append ("Light Microscopy", null);
		methodDetection.append ("LED Microscopy", null);
		methodDetection.append ("X-Ray/Clinical", null);
		methodDetection.append ("GeneXpert", null);
		
		typeOfTb.append ("SS+ Pulmonary", null);
		typeOfTb.append ("SS- Pulmopnary", null);
		typeOfTb.append ("GX+ Pulmonary", null);
		typeOfTb.append ("Extra Pulmonary", null);
		typeOfTb.append ("Others", null);
		
		siteOfTb.append ("Not Known", null);
		siteOfTb.append ("TB of Spine", null);
		siteOfTb.append ("Lymph nodes", null);
		siteOfTb.append ("TB of Hip Joint", null);
		siteOfTb.append ("TB of the Abdomen", null);
		siteOfTb.append ("Others", null);
	
		formItems = new Item[] {dateEntered, patientScreenId , patientLabId, tbDiagnosed, methodDetection, typeOfTb, siteOfTb, treatmentStarted, districtTbTreatmentNumber};
		startTimestamp = new Date ();
		initialShow ();
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
		
		hide(siteOfTb);
	}

	/**
	 * Validation method before submitting form
	 * 
	 * @return
	 */
	private boolean validate ()
	{
		StringBuffer sb = new StringBuffer ();

		// Check for empty fields and length
		if (StringUtil.getString (patientScreenId).equals (""))
			sb.append ("Patient ID: " + ErrorProvider.getError (ErrorProvider.EMPTY_DATA_ERROR) + "\n");
		else if (StringUtil.getString (patientScreenId).length () != patientScreenId.getMaxSize ())
			sb.append ("Patient ID: " + ErrorProvider.getError (ErrorProvider.OUT_OF_RANGE) + "\n");

		if (StringUtil.getString (patientLabId).equals (""))
			sb.append ("Patient Lab ID: " + ErrorProvider.getError (ErrorProvider.EMPTY_DATA_ERROR) + "\n");
		else if (StringUtil.getString (patientLabId).length () != patientLabId.getMaxSize ())
			sb.append ("Patient Lab ID: " + ErrorProvider.getError (ErrorProvider.OUT_OF_RANGE) + "\n");
		else if (!StringUtil.isNumeric (patientLabId.getString ()))
			sb.append ("Patient Lab ID: " + ErrorProvider.getError (ErrorProvider.INVALID_DATA_ERROR) + "\n");
		
		if (StringUtil.getString (treatmentStarted).equals ("Yes"))
		{
			if (StringUtil.getString (districtTbTreatmentNumber).length () != districtTbTreatmentNumber.getMaxSize ())
				sb.append ("District TB Treatment Number: " + ErrorProvider.getError (ErrorProvider.OUT_OF_RANGE) + "\n");
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
		else if (i == tbDiagnosed)
		{
			if (StringUtil.getString (i).equals ("No"))
			{
				hide (methodDetection);
				hide (treatmentStarted);
				hide (districtTbTreatmentNumber);
				hide (typeOfTb);
			}
			else
			{
				show (methodDetection);
				show (treatmentStarted);
				show (districtTbTreatmentNumber);
				show (typeOfTb);
			}
		}
		else if (i == treatmentStarted)
		{
			if (StringUtil.getString (i).equals ("No"))
			{
				hide (districtTbTreatmentNumber);
			}
			else
			{
				show (districtTbTreatmentNumber);
			}	
			
		} 
		else if (i == typeOfTb)
		{
			if (StringUtil.getString (i).equals ("Extra Pulmonary"))
				show (siteOfTb);
			else
				hide (siteOfTb);
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
		request.append ("&patientId=" + StringUtil.getString (patientScreenId));
		
		request.append ("&results=[");
		request.append ("PATIENT_LAB_ID=" + StringUtil.getString (patientLabId) + ";");
		request.append ("TB_DIAGNOSED=" + StringUtil.getString (tbDiagnosed).charAt (0) + ";");
		if (StringUtil.getString (tbDiagnosed).equals ("Yes"))
		{
			request.append ("TB_TYPE=" + StringUtil.getString (typeOfTb) + ";");
			if (StringUtil.getString (typeOfTb).equals ("Extra Pulmonary"))
				request.append ("TB_SITE=" + StringUtil.getString (siteOfTb) + ";");
			request.append ("METHOD_DETECTION=" + StringUtil.getString (methodDetection) + ";");
			request.append ("TREATMENT_STARTED=" + StringUtil.getString (treatmentStarted).charAt (0) + ";");
			if (StringUtil.getString (treatmentStarted).equals ("Yes"))
			{
				request.append ("DIST_TB_TREAT_NO=" + StringUtil.getString (districtTbTreatmentNumber));
			}
		}
		request.append ("]");
		return request.toString ();
	}

}
