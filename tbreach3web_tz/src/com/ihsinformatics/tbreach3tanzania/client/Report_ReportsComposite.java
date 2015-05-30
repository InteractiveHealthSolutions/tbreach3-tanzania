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
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.datepicker.client.DateBox;
import com.google.gwt.user.datepicker.client.DateBox.DefaultFormat;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.ihsinformatics.tbreach3tanzania.shared.AccessType;
import com.ihsinformatics.tbreach3tanzania.shared.DataType;
import com.ihsinformatics.tbreach3tanzania.shared.Parameter;
import com.ihsinformatics.tbreach3tanzania.shared.TBRT;
import com.ihsinformatics.tbreach3tanzania.shared.UserRightsUtil;
import com.ihsinformatics.tbreach3tanzania.shared.model.User;

public class Report_ReportsComposite extends Composite implements IReport, ClickHandler, ChangeHandler, ValueChangeHandler<Boolean>
{
	private static ServerServiceAsync	service							= GWT.create (ServerService.class);
	private static LoadingWidget		loading							= new LoadingWidget ();
	private static final String			menuName						= "DATALOG";
	private String						reportsDB						= "tbr3tanzania_rpt";
	private String						filter							= "";
	private String						startDate						= "";
	private String						endDate							= "";
	private String						patientId						= "";
	private String						district						= "";
	private String						screeningApproach				= "";
	private String						gender							= "";
	private String						startAge						= "";
	private String						endAge							= "";
	private String						diagnosisMethod					= "";
	private String						status							= "";
	private String						tbType							= "";
	private String						screeningStrategy				= "";
	
	private UserRightsUtil				rights							= new UserRightsUtil ();

	private FlexTable					flexTable						= new FlexTable ();
	private FlexTable					topFlexTable					= new FlexTable ();
	private FlexTable					rightFlexTable					= new FlexTable ();
	private FlexTable					filterFlexTable					= new FlexTable ();

	private Grid						grid							= new Grid (1, 4);

	private HorizontalPanel				dateRangeHorizontalPanel		= new HorizontalPanel ();
	private HorizontalPanel				timeRangeHorizontalPanel		= new HorizontalPanel ();
	private HorizontalPanel				patientIdHorizontalPanel		= new HorizontalPanel ();
	private HorizontalPanel				genderHorizontalPanel			= new HorizontalPanel ();
	
	private VerticalPanel				screeningStrategyVerticalPanel  = new VerticalPanel ();	
	private VerticalPanel				screeningApproachVerticalPanel	= new VerticalPanel ();
	private VerticalPanel				diagnosisMethodVerticalPanel	= new VerticalPanel ();
	private VerticalPanel				statusVerticalPanel				= new VerticalPanel ();
	private VerticalPanel				tbTypeVerticalPanel				= new VerticalPanel ();
	

	private Button						viewButton						= new Button ("Save");
	private Button						closeButton						= new Button ("Close");
	private Button						exportButton					= new Button ("Export");

	private Label						lblSelectCategory				= new Label ("Select Category:");
	private Label						lblSnapshot						= new Label ("Snapshot:");
	private Label						snapshotLabel					= new Label ();
	private Label						lblCaution						= new Label ("Some reports may take 5 to 10 minutes to generate. Please wait until report download window appears.");
	private Label						lblTbReachLog					= new Label (TBRT.getProjectTitle () + " Reports");
	private Label						lblSelectReport					= new Label ("Select Report:");
	private Label						lblFilter						= new Label ("Filter (Check all that apply):");
	private TextBox						patientIdTextBox				= new TextBox ();

	private ListBox						categoryComboBox				= new ListBox ();
	private ListBox						reportsListComboBox				= new ListBox ();
	private ListBox						patientFilterTypeComboBox		= new ListBox ();
	private ListBox						districtComboBox				= new ListBox ();
	private ListBox						ageGroupComboBox				= new ListBox ();
	

	private DateBox						fromDateBox						= new DateBox ();
	private DateBox						toDateBox						= new DateBox ();
	private DateBox						fromTimeDateBox					= new DateBox ();
	private DateBox						toTimeDateBox					= new DateBox ();

	private CheckBox					dateRangeFilterCheckBox			= new CheckBox ("Date Range:");
	private CheckBox					timeRangeFilterCheckBox			= new CheckBox ("Time Range:");
	private CheckBox					patientIdCheckBox				= new CheckBox ("Patient ID:");
	private CheckBox					districtCheckBox				= new CheckBox ("District:");
	private CheckBox					screeningApproachCheckBox		= new CheckBox ("Community Screening Approach:");
	private CheckBox					screeningStrategyCheckBox		= new CheckBox ("Screening Strategy:");
	private CheckBox					genderCheckBox					= new CheckBox ("Gender:");
	private CheckBox					ageGroupCheckBox				= new CheckBox ("Age Group:");
	private CheckBox					diagnosisMethodCheckBox			= new CheckBox ("Diagnosis Method:");
	private CheckBox					statusCheckBox					= new CheckBox ("Status:");
	private CheckBox					tbTypeCheckBox					= new CheckBox ("Case Classification:");
	
	private RadioButton					maleRadioButton					= new RadioButton ("gender", "Male");
	private RadioButton					femaleRadioButton				= new RadioButton ("gender", "Female");

	private CheckBox					CTRScreeningACheckBox			= new CheckBox ("Contact Tracing (CTR)");
	private CheckBox					MINScreeningACheckBox			= new CheckBox ("Targeted Mining Community (MIN)");
	private CheckBox					PASScreeningACheckBox			= new CheckBox ("Targeted Pastoral Community (PAS)");
	private CheckBox					othersScreeningACheckBox		= new CheckBox ("Others");
	
	private CheckBox					IFSScreeningSCheckBox			= new CheckBox ("Intensified facility based screening (IFS)");
	private CheckBox					CMSScreeningSCheckBox			= new CheckBox ("Community Based TB Screening (CMS)");
	
	private CheckBox					lightMicroscopyCheckBox			= new CheckBox ("Light Microscopy");
	private CheckBox					LEDMicroscopyCheckBox			= new CheckBox ("LED Microscopy");
	private CheckBox					xRayCheckBox					= new CheckBox ("X-Ray/Clinical");
	private CheckBox					geneXpertCheckBox				= new CheckBox ("GeneXpert");
	
	private CheckBox					nonSuspectCheckBox				= new CheckBox ("Non-Suspects");	
	private CheckBox					suspectCheckBox					= new CheckBox ("Suspects");
	private CheckBox					posDiagnosedCheckBox			= new CheckBox ("Positively Diganosed");
	private CheckBox					negDiagnosedCheckBox			= new CheckBox ("Negatively Diagnosed");
	private CheckBox					closeCheckBox					= new CheckBox ("Closed");
	
	private CheckBox					sspPulmonaryCheckBox			= new CheckBox ("SS+ Pulmonary");	
	private CheckBox					ssnPulmonaryCheckBox			= new CheckBox ("SS- Pulmonary");
	private CheckBox					gxpPulmonaryCheckBox			= new CheckBox ("GX+ Pulmonary");
	private CheckBox					extraPulmonaryCheckBox			= new CheckBox ("Extra Pulmonary");
	private CheckBox					othersCheckBox					= new CheckBox ("Others");
						
	
	@SuppressWarnings("deprecation")
	public Report_ReportsComposite ()
	{
		initWidget (flexTable);
		flexTable.setSize ("700px", "100%");
		flexTable.setWidget (0, 0, topFlexTable);
		topFlexTable.setSize ("100%", "100%");
		lblTbReachLog.setStyleName ("title");
		topFlexTable.setWidget (0, 0, lblTbReachLog);
		topFlexTable.getCellFormatter ().setHorizontalAlignment (0, 0, HasHorizontalAlignment.ALIGN_CENTER);
		topFlexTable.getRowFormatter ().setVerticalAlign (0, HasVerticalAlignment.ALIGN_MIDDLE);
		topFlexTable.getCellFormatter ().setVerticalAlignment (0, 0, HasVerticalAlignment.ALIGN_MIDDLE);
		flexTable.setWidget (1, 0, rightFlexTable);
		rightFlexTable.setSize ("100%", "100%");
		rightFlexTable.setWidget (0, 0, lblSnapshot);
		rightFlexTable.setWidget (0, 1, snapshotLabel);
		rightFlexTable.setWidget (1, 0, lblSelectCategory);
		categoryComboBox.addItem ("-- Select Category --");
		categoryComboBox.addItem ("Reports");
		categoryComboBox.addItem ("Form Dumps");
		rightFlexTable.setWidget (1, 1, categoryComboBox);
		categoryComboBox.setWidth ("100%");
		rightFlexTable.setWidget (2, 0, lblSelectReport);
		rightFlexTable.setWidget (2, 1, reportsListComboBox);
		reportsListComboBox.setWidth ("100%");
		reportsListComboBox.addChangeHandler (this);
		lblCaution.setStyleName ("gwt-MenuItem-selected");
		rightFlexTable.setWidget (3, 1, lblCaution);
		lblCaution.setWidth ("100%");
		rightFlexTable.setWidget (4, 0, lblFilter);
		rightFlexTable.setWidget (4, 1, filterFlexTable);
		
		filterFlexTable.setWidth ("100%");
		filterFlexTable.setWidget (0, 0, dateRangeFilterCheckBox);
		dateRangeFilterCheckBox.setVisible (true);
		timeRangeFilterCheckBox.setVisible (true);
		dateRangeFilterCheckBox.setWidth ("");
		filterFlexTable.setWidget (0, 1, dateRangeHorizontalPanel);
		dateRangeHorizontalPanel.setSize ("200px", "");
		dateRangeHorizontalPanel.add (fromDateBox);
		fromDateBox.setEnabled (false);
		fromDateBox.setFormat (new DefaultFormat (DateTimeFormat.getFormat ("dd-MM-yyyy")));
		fromDateBox.setWidth ("100px");
		dateRangeHorizontalPanel.add (toDateBox);
		toDateBox.setEnabled (false);
		toDateBox.setFormat (new DefaultFormat (DateTimeFormat.getFormat ("dd-MM-yyyy")));
		toDateBox.setWidth ("100px");
		
		filterFlexTable.setWidget (1, 0, timeRangeFilterCheckBox);
		filterFlexTable.setWidget (1, 1, timeRangeHorizontalPanel);
		timeRangeHorizontalPanel.setWidth ("100px");
		timeRangeHorizontalPanel.add (fromTimeDateBox);
		fromTimeDateBox.setEnabled (false);
		fromTimeDateBox.setValue (new Date (1297969200000L));
		fromTimeDateBox.setFormat (new DefaultFormat (DateTimeFormat.getShortTimeFormat ()));
		fromTimeDateBox.setWidth ("50px");
		timeRangeHorizontalPanel.add (toTimeDateBox);
		toTimeDateBox.setEnabled (false);
		toTimeDateBox.setValue (new Date (1298012400000L));
		toTimeDateBox.setFormat (new DefaultFormat (DateTimeFormat.getShortTimeFormat ()));
		toTimeDateBox.setWidth ("50px");
		
		filterFlexTable.setWidget (2, 0, patientIdCheckBox);
		filterFlexTable.setWidget (2, 1, patientIdHorizontalPanel);
		patientIdHorizontalPanel.setWidth ("100%");
		patientIdHorizontalPanel.add (patientFilterTypeComboBox);
		patientFilterTypeComboBox.setEnabled (false);
		patientFilterTypeComboBox.setWidth ("100%");
		patientIdHorizontalPanel.add (patientIdTextBox);
		patientIdTextBox.setVisibleLength (12);
		patientIdTextBox.setMaxLength (11);
		patientIdTextBox.setEnabled (false);
		patientIdTextBox.setWidth ("100%");
		patientIdTextBox.setStyleName ("");
		
		filterFlexTable.setWidget (3, 0, genderCheckBox);
		filterFlexTable.setWidget (3, 1, genderHorizontalPanel);
		maleRadioButton.setValue (true);
		genderHorizontalPanel.add (maleRadioButton);
		genderHorizontalPanel.add (femaleRadioButton);
		maleRadioButton.setEnabled (false);
		femaleRadioButton.setEnabled (false);
		
		filterFlexTable.setWidget (4, 0, ageGroupCheckBox);
		filterFlexTable.setWidget (4, 1, ageGroupComboBox);
		ageGroupComboBox.setEnabled (false);
		
		filterFlexTable.setWidget (5, 0, districtCheckBox);
		districtComboBox.setEnabled (false);
		filterFlexTable.setWidget (5, 1, districtComboBox);
		districtComboBox.setWidth ("100%");
		
		filterFlexTable.setWidget (6,0,statusCheckBox);
		filterFlexTable.setWidget (6,1, statusVerticalPanel);
		statusVerticalPanel.add (nonSuspectCheckBox);
		nonSuspectCheckBox.setEnabled (false);
		nonSuspectCheckBox.setValue (true);
		nonSuspectCheckBox.setName ("SCREENED");
		statusVerticalPanel.add (suspectCheckBox);
		suspectCheckBox.setEnabled (false);
		suspectCheckBox.setValue (true);
		suspectCheckBox.setName ("SUSPECT");
		statusVerticalPanel.add (negDiagnosedCheckBox);
		negDiagnosedCheckBox.setEnabled (false);
		negDiagnosedCheckBox.setValue (true);
		negDiagnosedCheckBox.setName ("NOT");
		statusVerticalPanel.add (posDiagnosedCheckBox);
		posDiagnosedCheckBox.setEnabled (false);
		posDiagnosedCheckBox.setValue (true);
		posDiagnosedCheckBox.setName ("CONFIRMED");
		statusVerticalPanel.add (closeCheckBox);
		closeCheckBox.setEnabled (false);
		closeCheckBox.setValue (true);
		closeCheckBox.setName ("CLOSED");
		
		filterFlexTable.setWidget (8,0,screeningStrategyCheckBox);
		filterFlexTable.setWidget (8,1, screeningStrategyVerticalPanel);
		screeningStrategyVerticalPanel.add (IFSScreeningSCheckBox);
		IFSScreeningSCheckBox.setEnabled (false);
		IFSScreeningSCheckBox.setValue (true);
		IFSScreeningSCheckBox.setName ("IFS");
		screeningStrategyVerticalPanel.add (CMSScreeningSCheckBox);
		CMSScreeningSCheckBox.setEnabled (false);
		CMSScreeningSCheckBox.setValue (true);
		CMSScreeningSCheckBox.setName ("CMS");
		
		filterFlexTable.setWidget (10, 0, screeningApproachCheckBox);
		filterFlexTable.setWidget (10, 1, screeningApproachVerticalPanel);
		screeningApproachVerticalPanel.add (CTRScreeningACheckBox);
		CTRScreeningACheckBox.setEnabled (false);
		CTRScreeningACheckBox.setValue (true);
		CTRScreeningACheckBox.setName ("CTR");
		screeningApproachVerticalPanel.add (MINScreeningACheckBox);
		MINScreeningACheckBox.setEnabled (false);
		MINScreeningACheckBox.setValue (true);
		MINScreeningACheckBox.setName ("MIN");
		screeningApproachVerticalPanel.add (PASScreeningACheckBox);
		PASScreeningACheckBox.setEnabled (false);
		PASScreeningACheckBox.setValue (true);
		PASScreeningACheckBox.setName ("PAS");
		screeningApproachVerticalPanel.add (othersScreeningACheckBox);
		othersScreeningACheckBox.setEnabled (false);
		othersScreeningACheckBox.setValue (true);
		othersScreeningACheckBox.setName ("Others");
		
		filterFlexTable.setWidget (12, 0, diagnosisMethodCheckBox);
		filterFlexTable.setWidget (12, 1, diagnosisMethodVerticalPanel);
		diagnosisMethodVerticalPanel.add (lightMicroscopyCheckBox);
		diagnosisMethodVerticalPanel.add (LEDMicroscopyCheckBox);
		diagnosisMethodVerticalPanel.add (xRayCheckBox);
		diagnosisMethodVerticalPanel.add (geneXpertCheckBox);
		lightMicroscopyCheckBox.setEnabled (false);
		lightMicroscopyCheckBox.setValue (true);
		lightMicroscopyCheckBox.setName ("LIGHT MICROSCOPY");
		LEDMicroscopyCheckBox.setEnabled (false);
		LEDMicroscopyCheckBox.setName ("LED MICROSCOPY");
		LEDMicroscopyCheckBox.setValue (true);
		xRayCheckBox.setEnabled (false);
		xRayCheckBox.setValue (true);
		xRayCheckBox.setName ("X-RAY/CLINICAL");
		geneXpertCheckBox.setEnabled (false);
		geneXpertCheckBox.setValue (true);
		geneXpertCheckBox.setName ("GENEXPERT");
		
		filterFlexTable.setWidget (14, 0, tbTypeCheckBox);
		filterFlexTable.setWidget (14, 1, tbTypeVerticalPanel);
		tbTypeVerticalPanel.add (sspPulmonaryCheckBox);
		tbTypeVerticalPanel.add (ssnPulmonaryCheckBox);
		tbTypeVerticalPanel.add (gxpPulmonaryCheckBox);
		tbTypeVerticalPanel.add (extraPulmonaryCheckBox);
		tbTypeVerticalPanel.add (othersCheckBox);
		sspPulmonaryCheckBox.setEnabled (false);
		sspPulmonaryCheckBox.setValue (true);
		sspPulmonaryCheckBox.setName ("SS+");
		ssnPulmonaryCheckBox.setEnabled (false);
		ssnPulmonaryCheckBox.setName ("SS-");
		ssnPulmonaryCheckBox.setValue (true);
		gxpPulmonaryCheckBox.setEnabled (false);
		gxpPulmonaryCheckBox.setValue (true);
		gxpPulmonaryCheckBox.setName ("GX+");
		extraPulmonaryCheckBox.setEnabled (false);
		extraPulmonaryCheckBox.setValue (true);
		extraPulmonaryCheckBox.setName ("EP");
		othersCheckBox.setEnabled (false);
		othersCheckBox.setValue (true);
		othersCheckBox.setName ("OTHR");
		
		filterFlexTable.getCellFormatter ().setHorizontalAlignment (1, 1, HasHorizontalAlignment.ALIGN_LEFT);
		rightFlexTable.setWidget (5, 1, grid);
		grid.setSize ("100%", "100%");
		viewButton.setEnabled (false);
		viewButton.setText ("View");
		grid.setWidget (0, 0, viewButton);
		exportButton.setEnabled (false);
		grid.setWidget (0, 1, exportButton);
		grid.setWidget (0, 3, closeButton);
		rightFlexTable.getCellFormatter ().setHorizontalAlignment (4, 1, HasHorizontalAlignment.ALIGN_CENTER);
		flexTable.getRowFormatter ().setVerticalAlign (1, HasVerticalAlignment.ALIGN_TOP);
		rightFlexTable.getRowFormatter ().setVerticalAlign (4, HasVerticalAlignment.ALIGN_TOP);
		flexTable.getCellFormatter ().setHorizontalAlignment (0, 0, HasHorizontalAlignment.ALIGN_CENTER);

		dateRangeFilterCheckBox.addValueChangeHandler (this);
		timeRangeFilterCheckBox.addValueChangeHandler (this);
		districtCheckBox.addValueChangeHandler (this);
		screeningApproachCheckBox.addValueChangeHandler (this);
		patientIdCheckBox.addValueChangeHandler (this);
		genderCheckBox.addValueChangeHandler (this);
		ageGroupCheckBox.addValueChangeHandler (this);
		diagnosisMethodCheckBox.addValueChangeHandler (this);
		statusCheckBox.addValueChangeHandler (this);
		tbTypeCheckBox.addValueChangeHandler (this);
		screeningStrategyCheckBox.addValueChangeHandler (this);
		categoryComboBox.addChangeHandler (this);
		reportsListComboBox.addChangeHandler (this);
		viewButton.addClickHandler (this);
		exportButton.addClickHandler (this);
		closeButton.addClickHandler (this);

		refreshList ();
		setRights (menuName);
	}

	private void refreshList ()
	{
		String[] filterOptions = {"IS EXACTLY", "STARTS WITH", "ENDS ON", "LOOKS LIKE", "NOT LIKE"};
		for (String s : filterOptions)
		{
			patientFilterTypeComboBox.addItem (s);
		}
		try
		{
			service.getSnapshotTime (new AsyncCallback<String> ()
			{
				public void onSuccess (String result)
				{
					snapshotLabel.setText (result);
				}

				public void onFailure (Throwable caught)
				{
					caught.printStackTrace ();
				}
			});
			service.getTableData ("select location_id, location_name from location where location_type='DISTRICT' order by location_name", new AsyncCallback<String[][]> ()
			{
				public void onSuccess (String[][] result)
				{
					districtComboBox.clear ();
					for (int i = 0; i < result.length; i++)
						districtComboBox.addItem (result[i][1].toString (), result[i][0].toString ());
				}

				public void onFailure (Throwable caught)
				{
					caught.printStackTrace ();
				}
			});
		}
		catch (Exception e)
		{
			e.printStackTrace ();
		}
		
		
		ageGroupComboBox.clear ();
		ageGroupComboBox.addItem ("0-4");
		ageGroupComboBox.addItem ("5-9");
		ageGroupComboBox.addItem ("10-14");
		ageGroupComboBox.addItem ("15-24");
		ageGroupComboBox.addItem ("25-34");
		ageGroupComboBox.addItem ("35-44");
		ageGroupComboBox.addItem ("45-54");
		ageGroupComboBox.addItem ("55-64");
		ageGroupComboBox.addItem ("65+");
		
		refreshFilters(false);		
		
	}
	
	public void refreshFilters (boolean filter)
	{
		dateRangeFilterCheckBox.setEnabled (filter);			
		timeRangeFilterCheckBox.setEnabled (filter);				
		patientIdCheckBox.setEnabled (filter);					
		districtCheckBox.setEnabled (filter);					
		screeningApproachCheckBox.setEnabled (filter);			
		genderCheckBox.setEnabled (filter);						
		ageGroupCheckBox.setEnabled (filter);					
		diagnosisMethodCheckBox.setEnabled (filter);
		statusCheckBox.setEnabled (filter);
		tbTypeCheckBox.setEnabled (filter);
		screeningStrategyCheckBox.setEnabled (filter);
		viewButton.setEnabled (filter);
		exportButton.setEnabled (filter);
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

	/**
	 * Creates appropriate filter for given column names
	 * 
	 * @param dateColumnName
	 * @param districtColumnName
	 * @param screeningApproachColumnName
	 * @param genderColumnName
	 * @param ageColumnName
	 * @param patientColumnName
	 * @param diagnosisMethodColumnName
	 * @param statusColumnName
	 * @param tbTypeColumnName
	 * @param screeningStrategyColumnName
	 */
	@SuppressWarnings("deprecation")
	private String filterData (String dateColumnName, String districtColumnName, String screeningApproachColumnName, String genderColumnName, String ageColumnName, String patientColumnName, String diagnosisMethodColumnName, String statusColumnName, String tbTypeColumnName, String screeningStrategyColumnName)
	{
		filter = "";
		startDate = "";
		endDate = "";
		district = "";
		screeningApproach = "";
		patientId = "";
		gender = "";
		startAge = "";
		endAge = "";
		diagnosisMethod = "";
		status = "";
		tbType = "";
		screeningStrategy = "";

		if (dateRangeFilterCheckBox.getValue ())
		{
			Date start = new Date (fromDateBox.getValue ().getTime ());
			Date end = new Date (toDateBox.getValue ().getTime ());
			StringBuilder startString = new StringBuilder ();
			StringBuilder endString = new StringBuilder ();
			if (timeRangeFilterCheckBox.getValue ())
			{
				start.setHours (fromTimeDateBox.getValue ().getHours ());
				start.setMinutes (fromTimeDateBox.getValue ().getMinutes ());
				end.setHours (toTimeDateBox.getValue ().getHours ());
				end.setMinutes (toTimeDateBox.getValue ().getMinutes ());
			}
			startString.append ((start.getYear () + 1900) + "-" + (start.getMonth () + 1) + "-" + start.getDate () + " " + start.getHours () + ":" + start.getMinutes () + ":00");
			endString.append ((end.getYear () + 1900) + "-" + (end.getMonth () + 1) + "-" + end.getDate () + " " + end.getHours () + ":" + end.getMinutes () + ":00");
			startDate = startString.toString ();
			endDate = endString.toString ();
		}
		
		if (districtCheckBox.getValue ())
		{
			district = " = '" + TBRTClient.get (districtComboBox) + "'";
		}
		
		if (screeningApproachCheckBox.getValue ())
		{
			
			// Iterate for all Screening Approach selected
			for (Iterator<Widget> iter = screeningApproachVerticalPanel.iterator (); iter.hasNext ();)
			{
				CheckBox chk = (CheckBox) iter.next ();
				if (chk.getValue ()){
					screeningApproach += screeningApproachColumnName +" LIKE '%" + chk.getName () + "%' OR ";				  
				}			
			}
			
			screeningApproach = screeningApproach.substring (0, screeningApproach.lastIndexOf (" OR ")) + ")";

		}
		
		
		if (patientIdCheckBox.getValue ())
		{
			switch (patientFilterTypeComboBox.getSelectedIndex ())
			{
				case 0 :
					patientId = " = '" + TBRTClient.get (patientIdTextBox) + "'";
					break;
				case 1 :
					patientId = " LIKE '" + TBRTClient.get (patientIdTextBox) + "%'";
					break;
				case 2 :
					patientId = " LIKE '%" + TBRTClient.get (patientIdTextBox) + "'";
					break;
				case 3 :
					patientId = " LIKE '%" + TBRTClient.get (patientIdTextBox) + "%'";
					break;
				case 4 :
					patientId = " NOT LIKE '%" + TBRTClient.get (patientIdTextBox) + "%'";
					break;
			}
		}
		
		if (genderCheckBox.getValue ())
		{
			if(maleRadioButton.getValue ())
				gender += " = 'M'";
			else
				gender += " = 'F'";
		}
		
		if (ageGroupCheckBox.getValue ())
		{
			switch (ageGroupComboBox.getSelectedIndex ())
			{
				case 0 :
					startAge = "0";
					endAge = "4";
					break;
				case 1 :
					startAge = "5";
					endAge = "9";
					break;
				case 2 :
					startAge = "10";
					endAge = "14";
					break;
				case 3 :
					startAge = "15";
					endAge = "24";
					break;
				case 4 :
					startAge = "25";
					endAge = "34";
					break;
				case 5 :
					startAge = "35";
					endAge = "44";
					break;
				case 6 :
					startAge = "45";
					endAge = "54";
					break;
				case 7 :
					startAge = "55";
					endAge = "64";
					break;
				case 8 :
					startAge = "65";
					endAge = "1000";
					break;	
			}
			
		}
		
		if(diagnosisMethodCheckBox.getValue ())
		{
			// Iterate for all Diagnosis Method selected
			for (Iterator<Widget> iter = diagnosisMethodVerticalPanel.iterator (); iter.hasNext ();)
			{
				CheckBox chk = (CheckBox) iter.next ();
				if (chk.getValue ()){
					diagnosisMethod += diagnosisMethodColumnName +" = '" + chk.getName () + "' OR ";				  
				}			
			}
			
			diagnosisMethod = diagnosisMethod.substring (0, diagnosisMethod.lastIndexOf (" OR ")) + ")";
		}
		
		if(statusCheckBox.getValue())
		{
			// Iterate for all Status selected
			for (Iterator<Widget> iter = statusVerticalPanel.iterator (); iter.hasNext ();)
			{
				CheckBox chk = (CheckBox) iter.next ();
				if (chk.getValue ()&& chk.isEnabled ()){
					status += statusColumnName +" = '" + chk.getName () + "' OR ";				  
				}			
			}
			
			status = status.substring (0, status.lastIndexOf (" OR ")) + ")";
			
			
		}
		
		if(tbTypeCheckBox.getValue())
		{
			// Iterate for all tbTypes selected
			for (Iterator<Widget> iter = tbTypeVerticalPanel.iterator (); iter.hasNext ();)
			{
				CheckBox chk = (CheckBox) iter.next ();
				if (chk.getValue ()){
					tbType += tbTypeColumnName +" = '" + chk.getName () + "' OR ";				  
				}			
			}
			
			tbType = tbType.substring (0, tbType.lastIndexOf (" OR ")) + ")";
			
		}
		
		if(screeningStrategyCheckBox.getValue())
		{
			// Iterate for all screeningStrategies selected
			for (Iterator<Widget> iter = screeningStrategyVerticalPanel.iterator (); iter.hasNext ();)
			{
				CheckBox chk = (CheckBox) iter.next ();
				if (chk.getValue ()){
					screeningStrategy += screeningStrategyColumnName +" = '" + chk.getName () + "' OR ";				  
				}			
			}			
			screeningStrategy = screeningStrategy.substring (0, screeningStrategy.lastIndexOf (" OR ")) + ")";			
		}
		
		
		if (dateRangeFilterCheckBox.getValue () && !dateColumnName.equals (""))
			filter += " AND " + dateColumnName + " BETWEEN '" + startDate + "' AND '" + endDate + "'";
		if (districtCheckBox.getValue () && !districtColumnName.equals (""))
			filter += " AND " + districtColumnName + district;
		if (screeningApproachCheckBox.getValue () && !screeningApproachColumnName.equals (""))
			filter += " AND ( " + screeningApproach;
		if (patientIdCheckBox.getValue () && !patientColumnName.equals (""))
			filter += " AND " + patientColumnName + patientId;
		if (genderCheckBox.getValue ())
			filter += " AND " + genderColumnName + gender;
		if (ageGroupCheckBox.getValue ())
			filter += " AND " + ageColumnName + " BETWEEN " + startAge + " AND " + endAge;
		if (diagnosisMethodCheckBox.getValue () && !diagnosisMethodColumnName.equals (""))
			filter += " AND ( " + diagnosisMethod;
		if (statusCheckBox.getValue () && !statusColumnName.equals (""))
			filter += " AND ( " + status;
		if (tbTypeCheckBox.getValue () && !tbTypeColumnName.equals (""))
			filter += " AND ( " + tbType;
		if (screeningStrategyCheckBox.getValue () && !screeningStrategyColumnName.equals (""))
			filter += " AND ( " + screeningStrategy;
 		return filter;
	}

	public void clearUp ()
	{
		// Not implemented
	}

	public boolean validate ()
	{
		return true;
	}

	public void viewData (final boolean export)
	{
		String reportSelected = TBRTClient.get (reportsListComboBox).replace (" ", "");
		String query = "";
		// Change the below tbreach3tanzania if live reports required
		reportsDB = "tbr3tanzania_rpt";
		// Case Detection Reports
		if (TBRTClient.get (categoryComboBox).equals ("Reports"))
		{
			if(reportSelected.equals("PendingTestPatientsList"))
			{
				query = "select p1.patient_id, concat (p2.first_name,' ',p2.middle_name,' ',p2.last_name) as first_name, p1.date_suspected from tbr3tanzania_rpt.patient as p1, tbr3tanzania_rpt.person as p2 WHERE p1.pid = p2.pid AND patient_status = 'SUSPECT'"  + filterData ("", "", "", "", "", "","","","","");
			}
			
			else if(reportSelected.equals("ListofScreenedPatients"))
			{
				query = "select sc.COUGH, sc.FEVER, sc.CHEST_PAIN, sc.NIGHT_SWEAT, sc.HAEMOPTYSIS, pa.treatment_outcome, pa.patient_id, pr.gender, pr.approximate_age, l.location_name, pa.date_screened, sc.COMMUNITY_APPROACH, sc.screening_strategy, pa.patient_status from tbr3tanzania_rpt.person as pr, tbr3tanzania_rpt.location as l , tbr3tanzania_rpt.patient as pa left outer join (SELECT max(e_id), pid1, COMMUNITY_APPROACH, screening_strategy, COUGH, FEVER, CHEST_PAIN, NIGHT_SWEAT, HAEMOPTYSIS FROM tbr3tanzania_rpt.Enc_SCREEN_A group by pid1) as sc on sc.pid1 = pa.pid  where (patient_status != 'REGISTERED') and pr.pid = pa.pid and pr.state = l.location_id" + filterData ("pa.date_screened","pr.state","sc.community_approach","pr.gender","pr.approximate_age","pa.patient_id","","pa.patient_status","","sc.screening_strategy");
			}
			
			else if(reportSelected.equals ("ListofSuspectsIdentified"))
			{
				query = "select pa.treatment_outcome, pa.patient_id, pr.gender, pr.approximate_age, l.location_name, pa.date_screened, sc.COMMUNITY_APPROACH, sc.screening_strategy, sc.CHEST_PAIN,COUGH, sc.PRODUCTIVE_COUGH, sc.FEVER, sc.HAEMOPTYSIS, sc.NIGHT_SWEAT, pa.patient_status from tbr3tanzania_rpt.person as pr, tbr3tanzania_rpt.location as l , tbr3tanzania_rpt.patient as pa left outer join (SELECT max(e_id), pid1, COMMUNITY_APPROACH, screening_strategy, CHEST_PAIN,COUGH, PRODUCTIVE_COUGH, FEVER, HAEMOPTYSIS, NIGHT_SWEAT FROM tbr3tanzania_rpt.Enc_SCREEN_A group by pid1) as sc on sc.pid1 = pa.pid  where (patient_status != 'REGISTERED' AND patient_status != 'SCREENED') and pr.pid = pa.pid and pr.state = l.location_id" + filterData ("pa.date_screened","pr.state","sc.community_approach","pr.gender","pr.approximate_age","pa.patient_id","","pa.patient_status","","sc.screening_strategy");
			}
			
			else if (reportSelected.equals ("ListofSuspectsTestedinLab"))
			{
				query = "select * from tbr3tanzania_rpt.person as pr, tbr3tanzania_rpt.location as l , tbr3tanzania_rpt.patient as pa left outer join (SELECT max(e_id), pid1, COMMUNITY_APPROACH , screening_strategy , N_DIAGNOSIS_FACILITY FROM tbr3tanzania_rpt.Enc_SCREEN_A group by pid1) as sc on sc.pid1 = pa.pid  where (patient_status != 'REGISTERED' AND patient_status != 'SCREENED' AND patient_status != 'SUSPECT') and pr.pid = pa.pid and pr.state = l.location_id" + filterData ("pa.date_screened","pr.state","sc.community_approach","pr.gender","pr.approximate_age","pa.patient_id","","pa.patient_status","","sc.screening_strategy");
			}
			
			else if (reportSelected.equals ("ListofTBCasesDetected"))
			{
				query = "select p1.treatment_outcome, p1.patient_id, p1.date_confirmed, p1.confirmation_source, p2.gender, p2.approximate_age, p1.patient_status, l.location_name, p1.disease_type, p1.disease_site, sc.N_Diagnosis_Facility from tbr3tanzania_rpt.person as p2 , tbr3tanzania_rpt.location l , tbr3tanzania_rpt.patient as p1 left outer join (SELECT max(e_id), pid1, N_DIAGNOSIS_FACILITY FROM tbr3tanzania_rpt.Enc_SCREEN_A group by pid1) as sc on sc.pid1 = p1.pid WHERE p1.pid = p2.pid AND (patient_status != 'REGISTERED' AND patient_status != 'SCREENED' AND patient_status != 'SUSPECT' AND patient_status != 'NOT') AND p2.state = l.location_id" + filterData("p1.date_confirmed","p2.state","","p2.gender","p2.approximate_age","p1.patient_id","p1.confirmation_source","pa.patient_status","p1.disease_type","");
			}
			else
			{
				query = "";
			}
		}
		else if (TBRTClient.get (categoryComboBox).equals ("Form Dumps"))
		{
			query = "select * from Enc_" + TBRTClient.get (reportsListComboBox) + " where 1 = 1" + filterData ("date_entered", "", "location_id", "", "", "pid1","","","","");
		}

		load (true);
		if (query.equals (""))
		{
			try
			{
				service.generateReport (reportSelected, new Parameter[] {new Parameter ("UserName", TBRT.getCurrentUserName (), DataType.STRING)}, export, new AsyncCallback<String> ()
				{
					public void onSuccess (String result)
					{
						String fileName = getFileName(result);
						System.out.println(Window.Location.getHref ()+fileName);
						String url = Window.Location.getHref ()+fileName;
						Window.open (url, "_blank", ""); 
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
				load (false);
				e.printStackTrace ();
			}
		}
		else
		{
			if (TBRTClient.get (categoryComboBox).equals ("Form Dumps"))
			{
				try
				{
					service.generateCSVfromQuery (reportsDB, query, new AsyncCallback<String> ()
					{

						public void onSuccess (String result)
						{
							
							String fileName = getFileName(result);
							System.out.println(Window.Location.getHref ()+fileName);
							String url = Window.Location.getHref ()+fileName;
							Window.open (url, "_blank", ""); 
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
					load (false);
					e.printStackTrace ();
				}
			}
			else
			{
				try
				{
					service.generateReportFromQuery (reportsDB, reportSelected, query, export, new AsyncCallback<String> ()
					{

						public void onSuccess (String result)
						{		
						    String fileName = getFileName(result);
							String url = Window.Location.getHref ()+fileName;
							Window.open (url, "_blank", "");
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
					load (false);
					e.printStackTrace ();
				}
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
								viewButton.setEnabled (rights.getAccess (AccessType.PRINT));
								exportButton.setEnabled (rights.getAccess (AccessType.PRINT));
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
		}
	}

	private String getFileName (String result)
	{
		int i = result.lastIndexOf ('\\');
		return result.substring (i+1);
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
		Widget sender = (Widget) event.getSource ();
		// Fill report names
		if (sender == categoryComboBox)
		{
			String text = TBRTClient.get (sender);
			if (text.equals ("-- Select Category --"))
			{
				refreshFilters(false);
				reportsListComboBox.clear ();
			}
			
			if (text.equals ("Reports"))
			{
				reportsListComboBox.clear ();
				reportsListComboBox.addItem ("List of Screened Patients");
				reportsListComboBox.addItem ("List of Suspects Identified");
				//reportsListComboBox.addItem ("Pending Test Patients List");
				reportsListComboBox.addItem ("List of Suspects Tested in Lab");
				reportsListComboBox.addItem ("List of TB Cases Detected");
				refreshFilters(true);
				diagnosisMethodCheckBox.setEnabled (false);
				tbTypeCheckBox.setEnabled (false);
			}
			else if (text.equals ("Form Dumps"))
			{
				refreshFilters(false);
				dateRangeFilterCheckBox.setEnabled (true);			
				timeRangeFilterCheckBox.setEnabled (true);				
				patientIdCheckBox.setEnabled (true);
				
				load (true);
				reportsListComboBox.clear ();
				try
				{
					service.getColumnData ("encounter_type", "encounter_type", "", new AsyncCallback<String[]> ()
					{
						public void onSuccess (String[] result)
						{
							for (String s : result)
								reportsListComboBox.addItem (s);
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
		}
		if (sender == reportsListComboBox)
		{
			if(categoryComboBox.getSelectedIndex ()==0){
				refreshFilters(false);			
			}
			if(categoryComboBox.getSelectedIndex ()==1){
				refreshFilters(true);
				switch (reportsListComboBox.getSelectedIndex ())
				{
					case 0 :
						if(statusCheckBox.getValue()){
							nonSuspectCheckBox.setEnabled (true);
							suspectCheckBox.setEnabled (true);
							posDiagnosedCheckBox.setEnabled (true);
							negDiagnosedCheckBox.setEnabled (true);
							closeCheckBox.setEnabled (true);
							}
						diagnosisMethodCheckBox.setEnabled (false);
						tbTypeCheckBox.setEnabled (false);
						break;
					case 1 :
						if(statusCheckBox.getValue()){
							nonSuspectCheckBox.setEnabled (false);
							suspectCheckBox.setEnabled (true);
							posDiagnosedCheckBox.setEnabled (true);
							negDiagnosedCheckBox.setEnabled (true);
							closeCheckBox.setEnabled (true);
							} 
						diagnosisMethodCheckBox.setEnabled (false);	
						tbTypeCheckBox.setEnabled (false);
						break;
					case 2 :
						if(statusCheckBox.getValue()){
							nonSuspectCheckBox.setEnabled (false);
							suspectCheckBox.setEnabled (false);
							posDiagnosedCheckBox.setEnabled (true);
							negDiagnosedCheckBox.setEnabled (true);
							closeCheckBox.setEnabled (true);
							}
						diagnosisMethodCheckBox.setEnabled (false);
						tbTypeCheckBox.setEnabled (false);
						break;
					case 3 :
						if(statusCheckBox.getValue()){
							nonSuspectCheckBox.setEnabled (false);
							suspectCheckBox.setEnabled (false);
							posDiagnosedCheckBox.setEnabled (true);
							negDiagnosedCheckBox.setEnabled (false);
							closeCheckBox.setEnabled (true);
							}
						screeningApproachCheckBox.setEnabled (false);
						screeningStrategyCheckBox.setEnabled (false);
						break;
				}
			}
			if(categoryComboBox.getSelectedIndex ()==3){
				refreshFilters(true);
			}
			//viewButton.setEnabled (!TBRTClient.get (reportsListComboBox).equals ("Form Dumps"));
		}
	}

	public void onValueChange (ValueChangeEvent<Boolean> event)
	{
		Widget sender = (Widget) event.getSource ();
		if (sender == dateRangeFilterCheckBox)
		{
			fromDateBox.setEnabled (dateRangeFilterCheckBox.getValue ());
			toDateBox.setEnabled (dateRangeFilterCheckBox.getValue ());
		}
		else if (sender == timeRangeFilterCheckBox)
		{
			fromTimeDateBox.setEnabled (timeRangeFilterCheckBox.getValue ());
			toTimeDateBox.setEnabled (timeRangeFilterCheckBox.getValue ());
		}
		else if (sender == patientIdCheckBox)
		{
			patientFilterTypeComboBox.setEnabled (patientIdCheckBox.getValue ());
			patientIdTextBox.setEnabled (patientIdCheckBox.getValue ());
			if(patientIdCheckBox.getValue ()) patientIdTextBox.setStyleName ("patientIdTextBox");
			else patientIdTextBox.setStyleName ("");
		}
		else if (sender == districtCheckBox)
		{
			districtComboBox.setEnabled (districtCheckBox.getValue ());
		}
		else if (sender == screeningApproachCheckBox)
		{
			CTRScreeningACheckBox.setEnabled (screeningApproachCheckBox.getValue ());
			PASScreeningACheckBox.setEnabled (screeningApproachCheckBox.getValue ());
			MINScreeningACheckBox.setEnabled (screeningApproachCheckBox.getValue ());
			othersScreeningACheckBox.setEnabled (screeningApproachCheckBox.getValue ());
		}
		else if (sender == genderCheckBox)
		{
			maleRadioButton.setEnabled(genderCheckBox.getValue());
			femaleRadioButton.setEnabled(genderCheckBox.getValue());
		}
		else if (sender == ageGroupCheckBox)
		{
			ageGroupComboBox.setEnabled(ageGroupCheckBox.getValue ());
		}
		else if (sender == diagnosisMethodCheckBox)
		{
			lightMicroscopyCheckBox.setEnabled (diagnosisMethodCheckBox.getValue ());
			LEDMicroscopyCheckBox.setEnabled (diagnosisMethodCheckBox.getValue ());
			xRayCheckBox.setEnabled (diagnosisMethodCheckBox.getValue ());
			geneXpertCheckBox.setEnabled (diagnosisMethodCheckBox.getValue ());
		}	
		else if (sender == statusCheckBox)
		{
			nonSuspectCheckBox.setEnabled (statusCheckBox.getValue ());
			suspectCheckBox.setEnabled (statusCheckBox.getValue ());
			posDiagnosedCheckBox.setEnabled (statusCheckBox.getValue ());
			negDiagnosedCheckBox.setEnabled (statusCheckBox.getValue ());
			closeCheckBox.setEnabled (statusCheckBox.getValue ());
			
			if(TBRTClient.get (reportsListComboBox).equals ("List of Suspects Identified"))
			{
				nonSuspectCheckBox.setEnabled (false);
			}
			else if(TBRTClient.get (reportsListComboBox).equals ("List of Suspects Tested in Lab"))
			{
				nonSuspectCheckBox.setEnabled (false);
				suspectCheckBox.setEnabled (false);
			}
			else if (TBRTClient.get (reportsListComboBox).equals ("List of TB Cases Detected"))
			{
				nonSuspectCheckBox.setEnabled (false);
				suspectCheckBox.setEnabled (false);
				negDiagnosedCheckBox.setEnabled (false);
			}	
				
			
		}	
		else if (sender == tbTypeCheckBox)
		{
			sspPulmonaryCheckBox.setEnabled (tbTypeCheckBox.getValue ());
			ssnPulmonaryCheckBox.setEnabled (tbTypeCheckBox.getValue ());
			gxpPulmonaryCheckBox.setEnabled (tbTypeCheckBox.getValue ());
			extraPulmonaryCheckBox.setEnabled (tbTypeCheckBox.getValue ());
			othersCheckBox.setEnabled (tbTypeCheckBox.getValue ());
			
		}
		
		else if (sender == screeningStrategyCheckBox)
		{
			IFSScreeningSCheckBox.setEnabled (screeningStrategyCheckBox.getValue());	
			CMSScreeningSCheckBox.setEnabled (screeningStrategyCheckBox.getValue());
		}
	}
}
