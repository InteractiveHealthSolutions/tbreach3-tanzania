/* Copyright(C) 2015 Interactive Health Solutions, Pvt. Ltd.

This program is free software; you can redistribute it and/or modify it under the terms of the GNU General Public License as
published by the Free Software Foundation; either version 3 of the License (GPLv3), or any later version.
This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.

See the GNU General Public License for more details. You should have received a copy of the GNU General Public License along with this program; if not, write to the Interactive Health Solutions, info@ihsinformatics.com
You can also access the license on the internet at the address: http://www.gnu.org/licenses/gpl-3.0.html

Interactive Health Solutions, hereby disclaims all copyright interest in this program written by the contributors. */
/**
 * Use Prototype form as a base to create new forms 
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
import com.ihsinformatics.tbreach3tanzania.mobile.shared.TBRT;
import com.ihsinformatics.tbreach3tanzania.mobile.util.DateTimeUtil;

/**
 * @author owais.hussain@irdresearch.org
 * 
 */
public class FormPrototype extends BaseForm implements CommandListener, ItemStateListener
{
	String formType = FormType.LOGIN;
	// Mobile fields
	DateField	dateEntered;
	StringItem	userName;
	TextField	nameTextField;
	DateField	dobDateField;
	ChoiceGroup	genderChoiceGroup;

	Command		okCommand	= new Command ("Save", Command.OK, 1);
	Command		backCommand	= new Command ("Back", Command.BACK, 2);

	public FormPrototype (String title, TBReach3TanzaniaMain tbreach3tanzaniaMidlet)
	{
		super (title, tbreach3tanzaniaMidlet);
	}

	public void init ()
	{
		dateEntered = new DateField ("Date", DateField.DATE);
		userName = new StringItem ("UserName:", TBRT.getCurrentUserName ());
		nameTextField = new TextField ("Suspect's Name:", "", TBRT.VALUE_LENGTH, TextField.ANY);
		dobDateField = new DateField ("Date of Birth:", DateField.DATE);
		dobDateField.setDate (new Date ());
		genderChoiceGroup = new ChoiceGroup ("Gender:", ChoiceGroup.POPUP);
		genderChoiceGroup.append ("Male", null);
		genderChoiceGroup.append ("Female", null);

		Item[] items = {nameTextField, dobDateField, genderChoiceGroup};
		formItems = items;

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
		// Add Items to form
		for (int i = 0; i < formItems.length; i++)
			append (formItems[i]);
		hide (dobDateField);
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

		if (DateTimeUtil.isDateInFuture (dobDateField.getDate ()))
			sb.append (ErrorProvider.getError (ErrorProvider.INVALID_DATE_OR_TIME));
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
		else if (i == genderChoiceGroup)
		{
			hide (nameTextField);
			show (nameTextField);
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
		request.append ("&startTimestamp=" + startTimestamp);
		request.append ("&endTimestamp=" + new Date ().getTime ());
		request.append ("&userName=" + TBRT.getCurrentUserName ());
		return request.toString ();
	}
}
