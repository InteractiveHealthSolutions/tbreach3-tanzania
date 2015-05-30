/* Copyright(C) 2015 Interactive Health Solutions, Pvt. Ltd.

This program is free software; you can redistribute it and/or modify it under the terms of the GNU General Public License as
published by the Free Software Foundation; either version 3 of the License (GPLv3), or any later version.
This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.

See the GNU General Public License for more details. You should have received a copy of the GNU General Public License along with this program; if not, write to the Interactive Health Solutions, info@ihsinformatics.com
You can also access the license on the internet at the address: http://www.gnu.org/licenses/gpl-3.0.html

Interactive Health Solutions, hereby disclaims all copyright interest in this program written by the contributors. */
/* Client/Patient Registration Form  */

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
import com.ihsinformatics.tbreach3tanzania.mobile.shared.Metadata;
import com.ihsinformatics.tbreach3tanzania.mobile.shared.TBRT;
import com.ihsinformatics.tbreach3tanzania.mobile.util.DateTimeUtil;
import com.ihsinformatics.tbreach3tanzania.mobile.util.StringUtil;

public class RegistrationForm extends BaseForm implements CommandListener, ItemStateListener
{

	String		formType	= FormType.REGISTRATION;
	// Mobile fields
	DateField	dateScreening;
	TextField	firstName;
	TextField	middleName;
	TextField	lastName;
	ChoiceGroup	gender;
	TextField	patientScreenId;
	TextField	age;
	ChoiceGroup	maritalStatus;
	TextField	addressHouseNumber;
	TextField	addressStreetName;
	TextField	tenCellLeaderName;
	TextField	nameChairperson;
	TextField	ward;
	ChoiceGroup	addressTown;
	ChoiceGroup	addressCity;
	TextField	mobile;
	TextField	secondaryMobile;
	TextField	landline;
	TextField	nameCommunityHealthVoluteer;
	TextField	mobileCommunityHealthVolunteer;
	TextField	idCommunityHealthVolunteer;

	Command		okCommand	= new Command ("Save", Command.OK, 1);
	Command		backCommand	= new Command ("Back", Command.BACK, 2);

	public RegistrationForm (String title, TBReach3TanzaniaMain tbreach3tanzaniaMidlet)
	{
		super (title, tbreach3tanzaniaMidlet);
	}

	public void init ()
	{
		dateScreening = new DateField ("*Screening Date", DateField.DATE);
		dateScreening.setDate (new Date ());
		firstName = new TextField ("*Client's First Name:", "", TBRT.VALUE_LENGTH, TextField.ANY);
		middleName = new TextField ("Client's Middle Name:", "", TBRT.VALUE_LENGTH, TextField.ANY);
		lastName = new TextField ("*Client's Last Name:", "", TBRT.VALUE_LENGTH, TextField.ANY);
		gender = new ChoiceGroup ("*Gender:", ChoiceGroup.POPUP);
		patientScreenId = new TextField ("*Client Screening ID Number", "", TBRT.ID_LENGTH, TextField.ANY);
		age = new TextField ("*Age in years:", "", 3, TextField.NUMERIC);
		maritalStatus = new ChoiceGroup ("*Marital status", ChoiceGroup.POPUP);
		addressHouseNumber = new TextField ("House Number", "", TBRT.KEY_LENGTH, TextField.NUMERIC);
		addressStreetName = new TextField ("*Village/Street Name", "", TBRT.VALUE_LENGTH, TextField.ANY);
		tenCellLeaderName = new TextField ("*Name of Ten Cell Leader", "", TBRT.VALUE_LENGTH, TextField.ANY);
		nameChairperson = new TextField ("Name of area's Chairperson", "", TBRT.VALUE_LENGTH, TextField.ANY);
		ward = new TextField ("*Ward", "", TBRT.VALUE_LENGTH, TextField.ANY);
		addressTown = new ChoiceGroup ("*District", ChoiceGroup.POPUP);
		addressCity = new ChoiceGroup ("*Region", ChoiceGroup.POPUP);
		mobile = new TextField ("*Client's Mobile Number", "9999999999", 10, TextField.PHONENUMBER);
		secondaryMobile = new TextField ("*Relative/Friend's Mobile Number", "9999999999",10, TextField.PHONENUMBER);
		landline = new TextField ("Landline Number", "", 10, TextField.NUMERIC);
		nameCommunityHealthVoluteer = new TextField ("*CHV or HCW Name", "", TBRT.VALUE_LENGTH, TextField.ANY);
		mobileCommunityHealthVolunteer = new TextField ("*Mobile Number of CHV or HCW", "9999999999", 10, TextField.PHONENUMBER);
		idCommunityHealthVolunteer = new TextField ("Community Health Volunteer ID", String.valueOf (999999), 6, TextField.NUMERIC);

		
		String[] districts = Metadata.getMetaValues (Metadata.DISTRICT);
		for (int i = 0; i < districts.length; i++)
			addressTown.append (districts[i], null);
		String[] regions = Metadata.getMetaValues (Metadata.REGION);
		for (int i = 0; i < regions.length; i++)
			addressCity.append (regions[i], null);
		String[] maritalStatuses = Metadata.getMetaValues (Metadata.MARITAL_STATUS);
		for (int i = 0; i < maritalStatuses.length; i++)
			maritalStatus.append (maritalStatuses[i], null);
		gender.append ("Male", null);
		gender.append ("Female", null);

		formItems = new Item[] {dateScreening, patientScreenId, firstName, middleName, lastName, gender, age, maritalStatus, addressHouseNumber, addressStreetName, tenCellLeaderName, nameChairperson,
				ward, addressTown, addressCity, mobile, secondaryMobile, landline, nameCommunityHealthVoluteer , mobileCommunityHealthVolunteer,
				idCommunityHealthVolunteer}; 
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
	}

	/**
	 * Validation method before submitting form
	 * 
	 * @return
	 */
	private boolean validate ()
	{
		StringBuffer sb = new StringBuffer ();

		// Check for empty fields
		if (StringUtil.getString (patientScreenId).equals (""))
			sb.append ("Suspect/Patient ID: " + ErrorProvider.getError (ErrorProvider.EMPTY_DATA_ERROR) + "\n");
		if (StringUtil.getString (patientScreenId).length () != patientScreenId.getMaxSize ())
			sb.append ("Suspect/Patient ID: " + ErrorProvider.getError (ErrorProvider.OUT_OF_RANGE) + "\n");
		if (StringUtil.getString (firstName).equals (""))
			sb.append ("First Name: " + ErrorProvider.getError (ErrorProvider.EMPTY_DATA_ERROR) + "\n");
		if (StringUtil.getString (lastName).equals (""))
			sb.append ("Last Name: " + ErrorProvider.getError (ErrorProvider.EMPTY_DATA_ERROR) + "\n");
		if (StringUtil.getString (tenCellLeaderName).equals (""))
			sb.append ("Name of Ten Cell Leader: " + ErrorProvider.getError (ErrorProvider.EMPTY_DATA_ERROR) + "\n");
		if (StringUtil.getString (addressStreetName).equals (""))
			sb.append ("Village/Street Name: " + ErrorProvider.getError (ErrorProvider.EMPTY_DATA_ERROR) + "\n");
		if (StringUtil.getString (idCommunityHealthVolunteer).length () != idCommunityHealthVolunteer.getMaxSize ())
			sb.append ("Community Health Volunteer ID: " + ErrorProvider.getError (ErrorProvider.OUT_OF_RANGE) + "\n");
		if (StringUtil.getString (ward).equals (""))
			sb.append ("Ward: " + ErrorProvider.getError (ErrorProvider.EMPTY_DATA_ERROR) + "\n");
		if (StringUtil.getString (age).equals (""))
			sb.append ("Age: " + ErrorProvider.getError (ErrorProvider.EMPTY_DATA_ERROR) + "\n");
		if (StringUtil.getString (nameCommunityHealthVoluteer).equals (""))
			sb.append ("Community Health Voluteer Name: " + ErrorProvider.getError (ErrorProvider.EMPTY_DATA_ERROR) + "\n");

		// Date Entered cannot be a future date or empty
		try
		{
			if (DateTimeUtil.isDateInFuture (dateScreening.getDate ()))
				sb.append ("Date: " + ErrorProvider.getError (ErrorProvider.INVALID_DATE_OR_TIME) + "\n");
		}
		catch (Exception e)
		{
			sb.append ("Form Entry Date has not been set. Please set the date.\n");
		}

		// Age should be between 0 and 199
		if (StringUtil.getString (age).length () != 0)
		{
			Integer i = Integer.valueOf (StringUtil.getString (age));
			if (i.intValue () < 0 || i.intValue () > 199)
				sb.append ("Age: " + ErrorProvider.getError (ErrorProvider.INVALID_DATA_ERROR) + "\n");
		}

		// Phone Numbers length validation
    	if (StringUtil.getString (mobile).length () != mobile.getMaxSize ())
			sb.append ("Mobile Number: " + ErrorProvider.getError (ErrorProvider.OUT_OF_RANGE) + "\n");

		if (StringUtil.getString (secondaryMobile).length () != secondaryMobile.getMaxSize ())
			sb.append ("Secondary Mobile Number: " + ErrorProvider.getError (ErrorProvider.OUT_OF_RANGE) + "\n");

		if (StringUtil.getString (mobileCommunityHealthVolunteer).length () != mobileCommunityHealthVolunteer.getMaxSize ())
			sb.append ("Community Health Volunteer contact number: " + ErrorProvider.getError (ErrorProvider.OUT_OF_RANGE) + "\n");

		// Landline number should be of correct length (UPDATE: can be empty)
		if (StringUtil.getString (landline).length () != 0 && StringUtil.getString (landline).length () != landline.getMaxSize ())
			sb.append ("Landline Number: " + ErrorProvider.getError (ErrorProvider.OUT_OF_RANGE) + "\n");
		
		// names validation
		if(!StringUtil.isAlpha (firstName.getString ()))
			sb.append ("First Name: " + ErrorProvider.getError (ErrorProvider.INVALID_DATA_ERROR) + "\n");
		
		if(!StringUtil.isAlpha (lastName.getString ()))
			sb.append ("Last Name: " + ErrorProvider.getError (ErrorProvider.INVALID_DATA_ERROR) + "\n");
		
		if(!StringUtil.isAlpha (tenCellLeaderName.getString ()))
			sb.append ("Ten Cell Leader Name: " + ErrorProvider.getError (ErrorProvider.INVALID_DATA_ERROR) + "\n");
		
		if(!StringUtil.getString (nameChairperson).equals ("")){
			if(!StringUtil.isAlpha (nameChairperson.getString ()))
				sb.append ("Area's Chairperson's Name: " + ErrorProvider.getError (ErrorProvider.INVALID_DATA_ERROR) + "\n");
		}
		
		if(!StringUtil.isAlpha (nameCommunityHealthVoluteer.getString ()))
			sb.append ("Community Health Volunteer Name: " + ErrorProvider.getError (ErrorProvider.INVALID_DATA_ERROR) + "\n");

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

		/*
		 * else if (i == district) { facility.deleteAll (); String[][]
		 * facilities = Metadata.getMetadata (Metadata.FACILITY); if
		 * (facilities.length > 0) { String districtId = Metadata.getKey
		 * (Metadata.DISTRICT, StringUtil.getString (i)); for (int j = 0; j <
		 * facilities.length; j++) { String idPart = facilities[j][1].substring
		 * (facilities[j][1].indexOf ("-") + 1); if (districtId.equals (idPart))
		 * facility.append (facilities[j][0], null); } } }
		 */
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
		request.append ("&dateScreening=" + dateScreening.getDate ().getTime ());
		request.append ("&patientId=" + StringUtil.getString (patientScreenId));
		request.append ("&locationId=" + Metadata.getKey (Metadata.REGION, StringUtil.getString (addressCity)));

		request.append ("&results=[");
		request.append ("F_NAME=" + StringUtil.getString (firstName) + ";");
		request.append ("M_NAME=" + StringUtil.getString (middleName) + ";");
		request.append ("L_NAME=" + StringUtil.getString (lastName) + ";");
		request.append ("AGE=" + StringUtil.getString (age) + ";");
		request.append ("CONTACT=" + StringUtil.getString (mobile) + ";");
		request.append ("SEC_CONT_NO=" + StringUtil.getString (secondaryMobile) + ";");
		request.append ("LANDLINE=" + StringUtil.getString (landline) + ";");
		request.append ("ADDRESS_HOUSE_NUMBER=" + StringUtil.getString (addressHouseNumber) + ";");
		request.append ("ADDRESS_STREET_NAME=" + StringUtil.getString (addressStreetName) + ";");
		request.append ("TEN_CELL_LEADER=" + StringUtil.getString (tenCellLeaderName) + ";");
		request.append ("CHAIR_PERSON=" + StringUtil.getString (nameChairperson) + ";");
		request.append ("WARD=" + StringUtil.getString (ward) + ";");
		request.append ("DISTRICT=" + Metadata.getKey (Metadata.DISTRICT, StringUtil.getString (addressTown)) + ";");
		request.append ("REGION=" + Metadata.getKey (Metadata.REGION, StringUtil.getString (addressCity)) + ";");
		request.append ("GENDER=" + StringUtil.getString (gender).charAt (0) + ";");
		request.append ("MARITAL_STATUS=" + StringUtil.getString (maritalStatus).charAt (0) + ";");
		request.append ("CHV_NAME=" + StringUtil.getString (nameCommunityHealthVoluteer) + ";");		request.append ("CHV_NO=" + StringUtil.getString (mobileCommunityHealthVolunteer) + ";");
		request.append ("CHV_ID=" + StringUtil.getString (idCommunityHealthVolunteer));
		request.append ("]"); 
		return request.toString ();
	}

}
