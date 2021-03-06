/* Copyright(C) 2015 Interactive Health Solutions, Pvt. Ltd.

This program is free software; you can redistribute it and/or modify it under the terms of the GNU General Public License as
published by the Free Software Foundation; either version 3 of the License (GPLv3), or any later version.
This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.

See the GNU General Public License for more details. You should have received a copy of the GNU General Public License along with this program; if not, write to the Interactive Health Solutions, info@ihsinformatics.com
You can also access the license on the internet at the address: http://www.gnu.org/licenses/gpl-3.0.html

Interactive Health Solutions, hereby disclaims all copyright interest in this program written by the contributors. */

package com.ihsinformatics.tbreach3tanzania.client;

import java.util.Date;
import java.util.Iterator;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.RichTextArea;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBoxBase;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.datepicker.client.DateBox;
import com.google.gwt.user.datepicker.client.DateBox.DefaultFormat;
import com.google.gwt.user.datepicker.client.DatePicker;
import com.ihsinformatics.tbreach3tanzania.shared.AccessType;
import com.ihsinformatics.tbreach3tanzania.shared.CustomMessage;
import com.ihsinformatics.tbreach3tanzania.shared.ErrorType;
import com.ihsinformatics.tbreach3tanzania.shared.InfoType;
import com.ihsinformatics.tbreach3tanzania.shared.TBRT;
import com.ihsinformatics.tbreach3tanzania.shared.UserRightsUtil;
import com.ihsinformatics.tbreach3tanzania.shared.model.Sms;
import com.ihsinformatics.tbreach3tanzania.shared.model.User;

public class SMSComposite extends Composite implements IForm, ClickHandler, ChangeHandler, ValueChangeHandler<Boolean>
{
	private static ServerServiceAsync	service				= GWT.create (ServerService.class);
	private static LoadingWidget		loading				= new LoadingWidget ();
	private static final String			menuName			= "SMS";
	private static final String			tableName			= "Sms";

	private UserRightsUtil				rights				= new UserRightsUtil ();
	private boolean						valid;

	private FlexTable					flexTable			= new FlexTable ();
	private FlexTable					topFlexTable		= new FlexTable ();
	private FlexTable					rightFlexTable		= new FlexTable ();
	private Grid						grid				= new Grid (1, 2);
	private VerticalPanel				groupsVerticalPanel	= new VerticalPanel ();

	private Button						saveButton			= new Button ("Save");
	private Button						closeButton			= new Button ("Close");

	private Label						lblTbReachSms		= new Label (TBRT.getProjectTitle () + " Sms Utility");
	private Label						lblMessageToSend	= new Label ("Message to send:");
	private Label						lblDatetime			= new Label ("Date/Time:");
	private Label						lblGroups			= new Label ("Groups:");

	private TextArea					messageTextBox		= new TextArea ();

	private CheckBox					suspectsCheckBox	= new CheckBox ("Suspects");
	private CheckBox					nonSuspectsCheckBox	= new CheckBox ("Non-Suspects");
	//private CheckBox					supervisorCheckBox	= new CheckBox ("Supervisors");
	private CheckBox					allPatientsCheckBox	= new CheckBox ("All Registered Patients");
	//private CheckBox					monitorCheckBox		= new CheckBox ("Monitors");

	private DateBox						smsDateDateBox		= new DateBox ();

	public SMSComposite ()
	{
		initWidget (flexTable);
		flexTable.setSize ("80%", "100%");
		flexTable.setWidget (0, 0, topFlexTable);
		lblTbReachSms.setStyleName ("title");
		topFlexTable.setWidget (0, 10, lblTbReachSms);
		flexTable.setWidget (1, 0, rightFlexTable);
		rightFlexTable.setSize ("100%", "100%");
		rightFlexTable.setWidget (0, 0, lblMessageToSend);
		messageTextBox.setCharacterWidth (30);
		messageTextBox.setVisibleLines (5);
		rightFlexTable.setWidget (0, 1, messageTextBox);
		rightFlexTable.setWidget (1, 0, lblDatetime);
		smsDateDateBox.setFormat (new DefaultFormat (DateTimeFormat.getFormat ("yyyy-MM-dd HH:mm")));
		rightFlexTable.setWidget (1, 1, smsDateDateBox);
		rightFlexTable.setWidget (2, 0, lblGroups);
		rightFlexTable.setWidget (2, 1, groupsVerticalPanel);
		suspectsCheckBox.setHTML ("Suspects");
		groupsVerticalPanel.add (suspectsCheckBox);
		groupsVerticalPanel.add (nonSuspectsCheckBox);
		//groupsVerticalPanel.add (monitorCheckBox);
		groupsVerticalPanel.add (allPatientsCheckBox);
		//groupsVerticalPanel.add (supervisorCheckBox);
		rightFlexTable.setWidget (3, 1, grid);
		grid.setSize ("100%", "100%");
		saveButton.setEnabled (false);
		grid.setWidget (0, 0, saveButton);
		grid.setWidget (0, 1, closeButton);
		rightFlexTable.getCellFormatter ().setVerticalAlignment (0, 0, HasVerticalAlignment.ALIGN_TOP);
		rightFlexTable.getCellFormatter ().setVerticalAlignment (2, 0, HasVerticalAlignment.ALIGN_TOP);
		rightFlexTable.getCellFormatter ().setVerticalAlignment (1, 0, HasVerticalAlignment.ALIGN_TOP);
		flexTable.getRowFormatter ().setVerticalAlign (1, HasVerticalAlignment.ALIGN_TOP);

        allPatientsCheckBox.addValueChangeHandler (this);
		saveButton.addClickHandler (this);
		closeButton.addClickHandler (this);
		

		refreshList ();
		setRights (menuName);
	}

	
	
	
	public void refreshList ()
	{
		// Not implemented
	}

	/**
	 * Display/Hide main panel and loading widget
	 * 
	 * @param status
	 */
	public void load (boolean status)
	{
		flexTable.setVisible (!status);
		if (status)
			loading.show ();
		else
			loading.hide ();
	}

	public void clearControls (Widget w)
	{
		if (w instanceof Panel)
		{
			Iterator<Widget> iter = ((Panel) w).iterator ();
			while (iter.hasNext ())
				clearControls (iter.next ());
		}
		else if (w instanceof TextBoxBase)
		{
			((TextBoxBase) w).setText ("");
		}
		else if (w instanceof RichTextArea)
		{
			((RichTextArea) w).setText ("");
		}
		else if (w instanceof ListBox)
		{
			((ListBox) w).setSelectedIndex (0);
		}
		else if (w instanceof DatePicker)
		{
			((DatePicker) w).setValue (new Date ());
		}
	}
	
	public void setCurrent()
	{
		// Not implemented
	}

	public void fillData ()
	{
		try
		{
			service.getColumnData (tableName, "", "", new AsyncCallback<String[]> ()
			{

				public void onSuccess (String[] result)
				{
					load (false);
				}

				public void onFailure (Throwable caught)
				{
					load (false);
				}
			});
		}
		catch (Exception e)
		{
			e.printStackTrace ();
			load (false);
		}
	}

	public void clearUp ()
	{
		clearControls (flexTable);
	}

	public boolean validate ()
	{
		
		final StringBuilder errorMessage = new StringBuilder ();
		valid = true;
		/* Validate whether at least one group is selected */
		boolean check = false;
		for (int i = 0; i < groupsVerticalPanel.getWidgetCount (); i++)
		{
			if (groupsVerticalPanel.getWidget (i) instanceof CheckBox)
			{
				CheckBox chk = (CheckBox) groupsVerticalPanel.getWidget (i);
				if (chk.getValue ())
				{
					check = true;
					break;
				}
			}
		}
		if (!check)
		{
			errorMessage.append ("You did not select any group. Please select at least one group for sending SMS.");
			valid = false;
		}
		Date date = new Date();
		if(smsDateDateBox.getValue () == null)
		{
			errorMessage.append (CustomMessage.getErrorMessage (ErrorType.EMPTY_DATA_ERROR) + "\n");
			valid = false;			
		} 
		else if (date.compareTo (smsDateDateBox.getValue ()) == 1){
			errorMessage.append (CustomMessage.getErrorMessage (ErrorType.INVALID_DATA_ERROR) + "\n");
			valid = false;				
		}
		
		
		
		/* Validate mandatory fields */
		if (messageTextBox.getText ().length () <= 0)
		{
			errorMessage.append (CustomMessage.getErrorMessage (ErrorType.EMPTY_DATA_ERROR) + "\n");
			valid = false;
		}
		if (!valid)
		{
			Window.alert (errorMessage.toString ());
			load (false);
		}
		return valid;
	}

	public void saveData ()
	{
		if (validate ())
		{
			try
			{
				 
				String filter = "where ifnull(Mobile, '') <> '' and (";
				load (true);
				if (allPatientsCheckBox.getValue ()){
					filter += "PID in (select pid from patient where alive = 1) or ";
				    filter += "1=0)"; 
				}
				else {
					
					if(suspectsCheckBox.getValue ())
						filter += "PID in (select pid from patient where patient_status = 'SUSPECTED') or " ;									 			
					else if(nonSuspectsCheckBox.getValue ())
						filter += "PID in (select pid from patient where patient_status = 'SCREENED') or " ;    				
				
				filter += "1=0)"; 			
				
				}
				
				service.getColumnData ("person", "mobile", filter, new AsyncCallback<String[]> ()
				{


					public void onSuccess (String[] result)
					{
						System.out.println (result.length);
						Sms[] messages = new Sms[result.length];
						for (int i = 0; i < result.length; i++)
						{
							messages[i] = new Sms (result[i], smsDateDateBox.getValue ());
							messages[i].setMessageText (TBRTClient.get (messageTextBox));
							messages[i].setStatus ("PENDING");
						}
						try
						{
							service.sendGenericSMSAlert (messages, new AsyncCallback<Void> ()
							{

								public void onSuccess (Void result)
								{
									Window.alert (CustomMessage.getInfoMessage (InfoType.INSERTED)
											+ "\nMessages will be delivered within 1 half an hour from Scheduled time.");
									load (false);
									
								}

								public void onFailure (Throwable caught)
								{
									load (false);
								}
							});
						} 
						catch (Exception e)
						{
							e.printStackTrace ();
						}
					}
					public void onFailure (Throwable caught)
					{
						load (false);
					}
				});
			}
			catch (Exception e)
			{
				e.printStackTrace ();
				load (false);
			} 
		}
		
	}

	public void updateData ()
	{
		// Not implemented
	}

	public void deleteData ()
	{
		// Not implemented
	}

	public void setRights (String menuName)
	{
		try
		{
			load (true);
			service.getUserRgihts (TBRT.getCurrentUserName (), TBRT.getCurrentRole (), menuName, new AsyncCallback<Boolean[]> ()
			{

				public void onSuccess (Boolean[] result)
				{
					final Boolean[] userRights = result;
					try
					{
						service.findUser (TBRT.getCurrentUserName (), new AsyncCallback<User> ()
						{

							public void onSuccess (User result)
							{
								rights.setRoleRights (TBRT.getCurrentRole (), userRights);
								saveButton.setEnabled (rights.getAccess (AccessType.UPDATE));
								load (false);
							}

							public void onFailure (Throwable caught)
							{
								load (false);
							}
						});
					}
					catch (Exception e)
					{
						e.printStackTrace ();
						load (false);
					}
				}

				public void onFailure (Throwable caught)
				{
					load (false);
				}
			});
		}
		catch (Exception e)
		{
			e.printStackTrace ();
			load (false);
		}
	}

	public void onClick (ClickEvent event)
	{
		Widget sender = (Widget) event.getSource ();
		load (true);
		if (sender == saveButton)
		{
			saveData ();
		}
		
	}

	public void onChange (ChangeEvent event)
	{
		// not implemented
	}

	@Override
	public void onValueChange (ValueChangeEvent<Boolean> event)
	{
		Widget sender = (Widget) event.getSource ();
		if (sender == allPatientsCheckBox)
		{
			suspectsCheckBox.setEnabled (!allPatientsCheckBox.getValue ());
			nonSuspectsCheckBox.setEnabled (!allPatientsCheckBox.getValue ());
		}
		
	}
	
	
}
