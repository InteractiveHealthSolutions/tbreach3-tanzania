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
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.RichTextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.TextBoxBase;
import com.google.gwt.user.client.ui.ToggleButton;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.datepicker.client.DatePicker;
import com.ihsinformatics.tbreach3tanzania.shared.AccessType;
import com.ihsinformatics.tbreach3tanzania.shared.CustomMessage;
import com.ihsinformatics.tbreach3tanzania.shared.ErrorType;
import com.ihsinformatics.tbreach3tanzania.shared.InfoType;
import com.ihsinformatics.tbreach3tanzania.shared.TBRT;
import com.ihsinformatics.tbreach3tanzania.shared.UserRightsUtil;
import com.ihsinformatics.tbreach3tanzania.shared.model.User;

public class Prototype extends Composite implements IForm, ClickHandler, ChangeHandler
{
	private static ServerServiceAsync	service			= GWT.create (ServerService.class);
	private static LoadingWidget		loading			= new LoadingWidget ();
	private static final String			menuName		= "SETUP";

	private UserRightsUtil				rights			= new UserRightsUtil ();
	private boolean						valid;
	private User						current;

	private FlexTable					flexTable		= new FlexTable ();
	private FlexTable					topFlexTable	= new FlexTable ();
	private FlexTable					leftFlexTable	= new FlexTable ();
	private FlexTable					rightFlexTable	= new FlexTable ();
	private Grid						grid			= new Grid (1, 3);

	private ToggleButton				createButton	= new ToggleButton ("Create");
	private Button						saveButton		= new Button ("Save");
	private Button						deleteButton	= new Button ("Delete");
	private Button						closeButton		= new Button ("Close");

	private Label						label			= new Label (TBRT.getProjectTitle () + " Users");
	private Label						label_1			= new Label ("New label");

	private TextBox						textBox			= new TextBox ();

	private ListBox						listBox			= new ListBox ();

	public Prototype ()
	{
		initWidget (flexTable);
		flexTable.setSize ("80%", "100%");
		flexTable.setWidget (0, 1, topFlexTable);
		label.setStyleName ("title");
		topFlexTable.setWidget (0, 0, label);
		topFlexTable.getCellFormatter ().setHorizontalAlignment (0, 0, HasHorizontalAlignment.ALIGN_CENTER);
		flexTable.setWidget (1, 0, leftFlexTable);
		listBox.setEnabled (false);
		leftFlexTable.setWidget (0, 0, listBox);
		listBox.setVisibleItemCount (5);
		flexTable.setWidget (1, 1, rightFlexTable);
		rightFlexTable.setSize ("100%", "100%");
		rightFlexTable.setWidget (0, 0, label_1);
		rightFlexTable.setWidget (0, 1, textBox);
		createButton.setEnabled (false);
		rightFlexTable.setWidget (1, 0, createButton);
		rightFlexTable.setWidget (1, 1, grid);
		grid.setSize ("100%", "100%");
		saveButton.setEnabled (false);
		grid.setWidget (0, 0, saveButton);
		deleteButton.setEnabled (false);
		grid.setWidget (0, 1, deleteButton);
		grid.setWidget (0, 2, closeButton);
		flexTable.getRowFormatter ().setVerticalAlign (1, HasVerticalAlignment.ALIGN_TOP);

		createButton.addClickHandler (this);
		saveButton.addClickHandler (this);
		deleteButton.addClickHandler (this);
		closeButton.addClickHandler (this);

		refresh (flexTable);
		setRights (menuName);
	}

	/**
	 * This method refreshes data inside a widget recursively. If the widget is
	 * a List box and the "name" property of the list box is set, then the
	 * method searches the value in "name" (e.g. MARITAL_STATUS) in definitions
	 * and loads into the list. Otherwise if the "name" property is not set, the
	 * list box is left untouched. If the widget is text-type, and the "name"
	 * property is set, then the method sets max length to the allowed length in
	 * table meta data. (The format of name property has to be in the format:
	 * table_name;column_name)
	 * 
	 * @param widget
	 */
	public void refresh (Widget widget)
	{
		if (widget instanceof FlexTable)
		{
			Iterator<Widget> iter = ((FlexTable) widget).iterator ();
			while (iter.hasNext ())
				refresh (iter.next ());
		}
		else if (widget instanceof Panel)
		{
			Iterator<Widget> iter = ((Panel) widget).iterator ();
			while (iter.hasNext ())
				refresh (iter.next ());
		}
		else if (widget instanceof TextBox)
		{
			TextBox text = (TextBox) widget;
			String name = text.getName ();
			if (!name.equals (""))
			{
				String[] parts = name.split (";");
				if (parts.length == 2)
					text.setMaxLength (TBRT.getMaxLength (parts[0], parts[1]));
			}
		}
		else if (widget instanceof ListBox)
		{
			if (!((ListBox) widget).getName ().equals (""))
				widget = TBRTClient.fillList (widget);
		}
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

	public void setCurrent ()
	{
		current = null;
	}

	public void fillData ()
	{
		try
		{
			service.findUser (TBRTClient.get (listBox), new AsyncCallback<User> ()
			{
				public void onSuccess (User result)
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
		/* Validate mandatory fields */
		if (true)
		{
			errorMessage.append (CustomMessage.getErrorMessage (ErrorType.EMPTY_DATA_ERROR) + "\n");
			valid = false;
		}
		/* Validate data-type rules */
		if (true)
		{
			errorMessage.append (CustomMessage.getErrorMessage (ErrorType.INVALID_DATA_ERROR) + "\n");
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
				current = new User ();
				setCurrent ();
				service.saveUser (current, null, new AsyncCallback<Boolean> ()
				{
					public void onSuccess (Boolean result)
					{
						if (result == null)
							Window.alert (CustomMessage.getErrorMessage (ErrorType.DUPLICATION_ERROR));
						else if (result)
						{
							Window.alert (CustomMessage.getInfoMessage (InfoType.INSERTED));
							clearUp ();
						}
						else
							Window.alert (CustomMessage.getErrorMessage (ErrorType.INSERT_ERROR));
						load (false);
					}

					public void onFailure (Throwable caught)
					{
						Window.alert (CustomMessage.getErrorMessage (ErrorType.INSERT_ERROR));
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
		if (validate ())
		{
			try
			{
				setCurrent ();
				service.updateUser (current, null, new AsyncCallback<Boolean> ()
				{
					public void onSuccess (Boolean result)
					{
						if (result)
							Window.alert (CustomMessage.getInfoMessage (InfoType.UPDATED));
						else
							Window.alert (CustomMessage.getErrorMessage (ErrorType.UPDATE_ERROR));
						load (false);
					}

					public void onFailure (Throwable caught)
					{
						Window.alert (CustomMessage.getErrorMessage (ErrorType.UPDATE_ERROR));
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

	public void deleteData ()
	{
		if (validate ())
		{
			try
			{
				service.deleteUser (current, new AsyncCallback<Boolean> ()
				{
					public void onSuccess (Boolean result)
					{
						if (result)
						{
							Window.alert (CustomMessage.getInfoMessage (InfoType.DELETED));
							clearUp ();
						}
						else
							Window.alert (CustomMessage.getErrorMessage (ErrorType.DELETE_ERROR));
						load (false);
					}

					public void onFailure (Throwable caught)
					{
						Window.alert (CustomMessage.getErrorMessage (ErrorType.DELETE_ERROR));
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
								// TODO: Decide select rights
								listBox.setEnabled (rights.getAccess (AccessType.SELECT));
								createButton.setEnabled (rights.getAccess (AccessType.INSERT));
								saveButton.setEnabled (rights.getAccess (AccessType.UPDATE));
								deleteButton.setEnabled (rights.getAccess (AccessType.DELETE));
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
		if (sender == createButton)
		{
			if (createButton.isDown ())
				clearUp ();
			listBox.setEnabled (!createButton.isDown ());
			load (false);
		}
		else if (sender == saveButton)
		{
			if (createButton.isDown ())
				saveData ();
			else
				updateData ();
		}
		else if (sender == deleteButton)
		{
			deleteData ();
		}
		else if (sender == closeButton)
		{
			MainMenuComposite.clear ();
		}
	}

	public void onChange (ChangeEvent event)
	{
		Widget sender = (Widget) event.getSource ();
		load (true);
		if (sender == listBox)
		{
			fillData ();
		}
	}
}
