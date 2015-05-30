
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
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.IntegerBox;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.RichTextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.TextBoxBase;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.datepicker.client.DatePicker;
import com.ihsinformatics.tbreach3tanzania.shared.AccessType;
import com.ihsinformatics.tbreach3tanzania.shared.TBRT;
import com.ihsinformatics.tbreach3tanzania.shared.UserRightsUtil;

public class Report_PatientComposite extends Composite implements IReport, ClickHandler, ChangeHandler, ValueChangeHandler<Boolean>
{
	private static ServerServiceAsync	service							= GWT.create (ServerService.class);
	private static LoadingWidget		loading							= new LoadingWidget ();
	private static final String			menuName						= "DATALOG";
	private UserRightsUtil				rights							= new UserRightsUtil ();
	private boolean						valid;

	private FlexTable					flexTable						= new FlexTable ();
	private FlexTable					topFlexTable					= new FlexTable ();
	private FlexTable					rightFlexTable					= new FlexTable ();

	private Grid						grid							= new Grid (1, 4);

	private HorizontalPanel				patientIdHorizontalPanel		= new HorizontalPanel ();
	private HorizontalPanel				genderHorizontalPanel			= new HorizontalPanel ();
	private HorizontalPanel				ageHorizontalPanel				= new HorizontalPanel ();

	private VerticalPanel				sourceVerticalPanel				= new VerticalPanel ();
	private VerticalPanel				statusVerticalPanel				= new VerticalPanel ();

	private Button						viewButton						= new Button ("View");
	private Button						exportButton					= new Button ("Export");
	private Button						closeButton						= new Button ("Close");

	private Label						lblTbReachLog					= new Label (TBRT.getProjectTitle () + " Patient Custom Search");
	private Label						lblReportFilters				= new Label ("Report filters:");
	private Label						lblFilterValues					= new Label ("Filter values:");
	private Label						lblBetween						= new Label ("Between:");
	private Label						lblAnd							= new Label ("and");

	private IntegerBox					ageFromTextBox					= new IntegerBox ();
	private IntegerBox					ageToTextBox					= new IntegerBox ();
	private TextBox						patientIdTextBox				= new TextBox();

	

	private RadioButton					maleRadioButton					= new RadioButton ("Gender", "Male");
	private RadioButton					femaleRadioButton				= new RadioButton ("Gender", "Female");

	private CheckBox					genderCheckBox					= new CheckBox ("Gender");
	private CheckBox					ageCheckBox						= new CheckBox ("Age");
	private CheckBox					statusCheckBox					= new CheckBox ("Status");	
	private CheckBox					patientIdCheckBox				= new CheckBox ("Patient ID");
	
	private CheckBox					screenedCheckBox				= new CheckBox ("SCREENED");
	private CheckBox					suspectCheckBox					= new CheckBox ("SUSPECT");
	private CheckBox					confirmedCheckBox				= new CheckBox ("CONFIRMED");
	private CheckBox					closedCheckBox					= new CheckBox ("CLOSED");
	private CheckBox					registeredCheckBox				= new CheckBox ("REGISTERED");
	
	private ListBox						patientFilterTypeComboBox		= new ListBox ();
	
	public Report_PatientComposite ()
	{
		initWidget (flexTable);
		flexTable.setSize ("440px", "100%");
		flexTable.setWidget (0, 0, topFlexTable);
		lblTbReachLog.setStyleName ("title");
		topFlexTable.setWidget (0, 10, lblTbReachLog);
		flexTable.setWidget (1, 0, rightFlexTable);
		rightFlexTable.setSize ("100%", "100%");
		rightFlexTable.setWidget (0, 0, lblReportFilters);
		rightFlexTable.setWidget (0, 1, lblFilterValues);
		rightFlexTable.setWidget (1, 1, sourceVerticalPanel);
		rightFlexTable.setWidget (5, 0, patientIdCheckBox);
		rightFlexTable.setWidget (5, 1, patientIdHorizontalPanel);
		patientIdHorizontalPanel.setWidth ("100%");
		patientIdHorizontalPanel.add (patientFilterTypeComboBox);
		patientFilterTypeComboBox.setEnabled (false);
		patientFilterTypeComboBox.setWidth ("100%");
		patientIdHorizontalPanel.add (patientIdTextBox);
		patientIdTextBox.setEnabled (false);
		rightFlexTable.setWidget (6, 0, genderCheckBox);
		rightFlexTable.setWidget (6, 1, genderHorizontalPanel);
		maleRadioButton.setEnabled (false);
		maleRadioButton.setValue (true);
		genderHorizontalPanel.add (maleRadioButton);
		femaleRadioButton.setEnabled (false);
		genderHorizontalPanel.add (femaleRadioButton);
		rightFlexTable.setWidget (7, 0, ageCheckBox);
		rightFlexTable.setWidget (7, 1, ageHorizontalPanel);
		ageHorizontalPanel.add (lblBetween);
		ageFromTextBox.setEnabled (false);
		ageFromTextBox.setText ("0");
		ageFromTextBox.setMaxLength (2);
		ageFromTextBox.setVisibleLength (2);
		ageHorizontalPanel.add (ageFromTextBox);
		ageHorizontalPanel.add (lblAnd);
		ageToTextBox.setEnabled (false);
		ageToTextBox.setText ("100");
		ageToTextBox.setVisibleLength (3);
		ageToTextBox.setMaxLength (3);
		ageHorizontalPanel.add (ageToTextBox);
		rightFlexTable.setWidget (8, 0, statusCheckBox);
		rightFlexTable.setWidget (8, 1, statusVerticalPanel);
		screenedCheckBox.setEnabled (false);
		screenedCheckBox.setValue (true);
		statusVerticalPanel.add (screenedCheckBox);
		suspectCheckBox.setEnabled (false);
		suspectCheckBox.setValue (true);
		statusVerticalPanel.add (suspectCheckBox);
		confirmedCheckBox.setEnabled (false);
		confirmedCheckBox.setValue (true);
		statusVerticalPanel.add (confirmedCheckBox);
		closedCheckBox.setEnabled (false);
		closedCheckBox.setValue (true);
		statusVerticalPanel.add (closedCheckBox);
		registeredCheckBox.setEnabled (false);
		registeredCheckBox.setValue (true);
		statusVerticalPanel.add (registeredCheckBox);
		
		rightFlexTable.setWidget (14, 1, grid);
		grid.setSize ("100%", "100%");
		exportButton.setEnabled (false);
		exportButton.setText ("Export");
		grid.setWidget (0, 1, viewButton);
		grid.setWidget (0, 2, exportButton);
		grid.setWidget (0, 3, closeButton);
		flexTable.getRowFormatter ().setVerticalAlign (1, HasVerticalAlignment.ALIGN_TOP);
		
		String[] filterOptions = {"IS EXACTLY", "STARTS WITH", "ENDS ON", "LOOKS LIKE", "NOT LIKE"};
		for (String s : filterOptions)
		{
			patientFilterTypeComboBox.addItem (s);
		}

        patientIdCheckBox.addValueChangeHandler (this);
		genderCheckBox.addValueChangeHandler (this);
		ageCheckBox.addValueChangeHandler (this);
		statusCheckBox.addValueChangeHandler (this);
		viewButton.addClickHandler (this);
		exportButton.addClickHandler (this);
		closeButton.addClickHandler (this);

		refreshList ();
		setRights (menuName);
	}

	private void refreshList ()
	{
		
	/*	try
		{
			load (true);
			service.getColumnData ("Monitor", "MonitorID", "", new AsyncCallback<String[]> ()
			{

				public void onSuccess (String[] result)
				{
					for (String s : result)
						
					load (false);
				}

				public void onFailure (Throwable caught)
				{
					caught.printStackTrace ();
					load (false);
				}
			});
		}
		catch (Exception e)
		{
			e.printStackTrace ();
			load (false);
		} */
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

	public void clearUp ()
	{
		clearControls (flexTable);
	}

	public boolean validate ()
	{
		final StringBuilder errorMessage = new StringBuilder ();
		valid = true;
		/* Validate data-type rules */
		if (!valid)
			Window.alert (errorMessage.toString ());
		return valid;
	}

	public void viewData (boolean export)
	{
		try
		{
			StringBuilder query = new StringBuilder ();
			StringBuilder columns = new StringBuilder ();
			StringBuilder filters = new StringBuilder ();
			
			// Select columns to display and apply filters
			
			columns.append ("concat (Pr.pid) as 'ID'");
			columns.append (", concat(Pr.first_name,\" \",Pr.middle_name,\" \",Pr.last_name) as 'Full Name'");
			columns.append (", concat (P.patient_status) as 'Status'");
			
			if (patientIdCheckBox.getValue ())
			{
				switch (patientFilterTypeComboBox.getSelectedIndex ())
				{
					case 0 :
						filters.append(" and Pr.pid = '"+patientIdTextBox.getValue()+"'");
						break;
					case 1 :
						filters.append ( "and Pr.pid LIKE '" + TBRTClient.get (patientIdTextBox) + "%'");
						break;
					case 2 :
						filters.append ( " and Pr.pid LIKE '%" + TBRTClient.get (patientIdTextBox) + "'");
						break;
					case 3 :
						filters.append ( " and Pr.pid LIKE '%" + TBRTClient.get (patientIdTextBox) + "%'");
						break;
					case 4 :
						filters.append( "and Pr.pid NOT LIKE '%" + TBRTClient.get (patientIdTextBox) + "%'");
						break;
				}
							
			}
			
			if (genderCheckBox.getValue ())
			{
				columns.append (", concat(Pr.Gender) as 'Gender'");
				filters.append ("and Pr.Gender = '" + (maleRadioButton.getValue () ? "M" : "F") + "' ");
			}
			
			if (ageCheckBox.getValue ())
			{
				columns.append (", concat(Pr.approximate_age) as 'Age'");
				filters.append ("and Pr.approximate_age between " + TBRTClient.get (ageFromTextBox) + " and " + TBRTClient.get (ageToTextBox) + " ");
			}
						
			if (statusCheckBox.getValue ())
			{
				String statuses = "";
				// Iterate for all sources selected
				for (Iterator<Widget> iter = statusVerticalPanel.iterator (); iter.hasNext ();)
				{
					CheckBox chk = (CheckBox) iter.next ();
					if (chk.getValue ())
						statuses += "'" + chk.getText () + "',";
					  
				}
					
				filters.append ("and P.patient_status in (" + statuses.substring (0, statuses.lastIndexOf (',') - 1) + "') ");
			}
			
		
			query.append ("select " + columns.toString () + " from patient as P ");
			query.append ("inner join person as Pr on P.patient_id = Pr.pid ");
			query.append (filters.toString ());

			if(export)
			{
				service.generateCSVfromQuery ("tbr3tanzania", query.toString (), new AsyncCallback<String> ()
				{

					public void onSuccess (String result)
					{
						Window.open (result, "_blank", "");
						load (false);
					}

					public void onFailure (Throwable caught)
					{
						load (false);
					}
				});
			}
			
			else
			{
				service.generatePDFfromQuery ("tbr3tanzania", query.toString (), new AsyncCallback<String> ()
				{

					public void onSuccess (String result)
					{
						Window.open (result, "_blank", "");
						load (false);
					}

					public void onFailure (Throwable caught)
					{
						load (false);
					}
				});
			}
			
		}
		catch (Exception e)
		{
			e.printStackTrace ();
			load (false);
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
					rights.setRoleRights (TBRT.getCurrentRole (), userRights);
					exportButton.setEnabled (rights.getAccess (AccessType.PRINT));
					viewButton.setEnabled (rights.getAccess (AccessType.SELECT));
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

	public void onClick (ClickEvent event)
	{
		Widget sender = (Widget) event.getSource ();
		load (true);
		if (sender == viewButton)
		{			
			viewData (false);
		}				
		else if (sender == exportButton)
		{
			viewData (true);
		}
		else if (sender == closeButton)
		{
			MainMenuComposite.clear ();
		}
	}

	public void onChange (ChangeEvent event)
	{
		// Not implemented
	}

	public void onValueChange (ValueChangeEvent<Boolean> event)
	{
		Widget sender = (Widget) event.getSource ();
		
		
		if (sender == genderCheckBox)
		{
			maleRadioButton.setEnabled (genderCheckBox.getValue ());
			femaleRadioButton.setEnabled (genderCheckBox.getValue ());
		}
		else if (sender == ageCheckBox)
		{
			ageFromTextBox.setEnabled (ageCheckBox.getValue ());
			ageToTextBox.setEnabled (ageCheckBox.getValue ());
		}
			
		else if (sender == statusCheckBox)
		{
			screenedCheckBox.setEnabled (statusCheckBox.getValue ());
			suspectCheckBox.setEnabled (statusCheckBox.getValue ());			
			confirmedCheckBox.setEnabled (statusCheckBox.getValue ());			
			closedCheckBox.setEnabled (statusCheckBox.getValue ());		
			registeredCheckBox.setEnabled (statusCheckBox.getValue ());
			
		}
		
		else if (sender == patientIdCheckBox)
		{
			patientIdTextBox.setEnabled (patientIdCheckBox.getValue());
			patientFilterTypeComboBox.setEnabled (patientIdCheckBox.getValue());
		}
	}
}
