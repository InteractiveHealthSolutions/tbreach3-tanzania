
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
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.RichTextArea;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.TextBoxBase;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.datepicker.client.DatePicker;
import com.ihsinformatics.tbreach3tanzania.shared.AccessType;
import com.ihsinformatics.tbreach3tanzania.shared.TBRT;
import com.ihsinformatics.tbreach3tanzania.shared.UserRightsUtil;
import com.ihsinformatics.tbreach3tanzania.shared.model.Encounter;
import com.ihsinformatics.tbreach3tanzania.shared.model.EncounterElement;
import com.ihsinformatics.tbreach3tanzania.shared.model.EncounterId;
import com.ihsinformatics.tbreach3tanzania.shared.model.EncounterResults;
import com.ihsinformatics.tbreach3tanzania.shared.model.Patient;
import com.ihsinformatics.tbreach3tanzania.shared.model.Person;
import com.ihsinformatics.tbreach3tanzania.shared.model.User;

public class Report_PatientSummary extends Composite implements ClickHandler, ChangeHandler, ValueChangeHandler<Boolean>
{
	private static ServerServiceAsync	service						= GWT.create (ServerService.class);
	private static LoadingWidget		loading						= new LoadingWidget ();
	private static final String			menuName					= "DATALOG";

	private UserRightsUtil				rights						= new UserRightsUtil ();

	private FlexTable					flexTable					= new FlexTable ();
	private FlexTable					leftFlexTable				= new FlexTable ();
	private FlexTable					rightFlexTable				= new FlexTable ();
	private FlexTable					topFlexTable				= new FlexTable ();
	private SimplePanel					resultsPanel			    = new SimplePanel ();
	
	private Grid						grid					    = new Grid (9, 9);
	private Grid						grid1					    = new Grid (3, 3);
	private Grid						resultsGrid					= new Grid (1, 1);
	
	private Button						summaryButton				= new Button ("Show Summary");
	private Button						closeButton					= new Button ("Close");
	
	private Label						lblTbReachUsers				= new Label (TBRT.getProjectTitle () + " Patient Summary");
	private Label						lblPatientId				= new Label ("Patient ID:");
	private Label						lblPatientName				= new Label ("Patient Name:");
	private Label						lblDateScreened				= new Label ("Date Screened:");
	private Label						lblPatientStatus			= new Label ("Patient Status:");
	private Label						lblConfirmedDate			= new Label ("Date Diagnosed:");
	private Label						lblConfirmedSource			= new Label ("Souurce of Diagnosis:");
	private Label 						lblTreatmentOutcome			= new Label ("Treatment Outcome:");
	private Label 						lblTreatmentCloseDate		= new Label ("Treatment Ended Date:");
	private Label						lblFormFilled				= new Label ("View Forms: ");
	private Label						lblDateEntered				= new Label ("Date Entered: ");
	private Label						lblSearchBy					= new Label ("SEARCH BY: ");
	private Label						lblSimilarResult			= new Label ("Similar Result: ");
	
	private TextBox						treatmentOutcomeTextBox		= new TextBox ();
	private TextBox						treatmentCloseDateTextBox	= new TextBox ();
	private TextBox						patientIdTextBox			= new TextBox ();
	private TextBox						userNameTextBox				= new TextBox ();
	private TextBox						screenedDateTextBox			= new TextBox ();
	private TextBox						patientStatusTextBox		= new TextBox ();
	private TextBox						confirmedDateTextBox		= new TextBox ();
	private TextBox						confirmedSourceTextBox		= new TextBox ();
	private TextBox						enteredPatientIdTextBox		= new TextBox ();
	private TextBox						dateEnteredTextBox			= new TextBox ();
	private TextBox						patientNameTextBox			= new TextBox ();
	
	
	private ListBox						formFilledComboBox			= new ListBox ();
	private ListBox						patientFilterTypeComboBox	= new ListBox ();
	private ListBox						patientNamesListBox			= new ListBox ();
	private ListBox						patientidtempListBox		= new ListBox ();
	
	private RadioButton					patientIdRadioButton		= new RadioButton ("selection", "Patient ID");
	private RadioButton					patientNameRadioButton		= new RadioButton ("selection", "Patient Name");
	
	
	String patientId;
	

	public Report_PatientSummary ()
	{
		System.out.println (patientIdTextBox.getStyleName () + "  " +patientIdTextBox.getStylePrimaryName ());
		initWidget (flexTable);
		flexTable.setSize ("80%", "100%");
		flexTable.setWidget (0, 0, topFlexTable);
		lblTbReachUsers.setStyleName ("title");
		topFlexTable.setWidget (0, 0, lblTbReachUsers);
		lblTbReachUsers.setWordWrap (false);
		flexTable.setWidget (1, 0, leftFlexTable);
		leftFlexTable.setSize ("100%", "100%");
		enteredPatientIdTextBox.setTitle ("Enter any Patient ID to view Patient's summary.");
		enteredPatientIdTextBox.setName ("USER_ROLE");
		enteredPatientIdTextBox.setEnabled (false);
		enteredPatientIdTextBox.setStyleName ("patientIdTextBox");
		enteredPatientIdTextBox.setMaxLength (11);
		leftFlexTable.setWidget (0, 0, grid);
		grid.setSize ("100%", "30%");
		patientIdRadioButton.setValue (true);
		patientNameTextBox.setEnabled(false);
		patientNameTextBox.setStyleName ("");
		patientIdTextBox.setMaxLength (11);
		patientFilterTypeComboBox.setEnabled (false);
		//patientNameRadioButton.setEnabled (false);
		grid.setWidget (0, 0, lblSearchBy);
		grid.setWidget (1, 0, patientIdRadioButton);
		grid.setWidget (2, 0, enteredPatientIdTextBox);
		grid.setWidget (3, 0, patientNameRadioButton);
		grid.setWidget (4, 0, patientFilterTypeComboBox);
		grid.setWidget (5, 0, patientNameTextBox);
		grid.setWidget (6, 0, summaryButton);
		grid.setWidget (7, 0, closeButton);
		grid.setWidget (8, 0, grid1);
		grid1.setWidget (1, 0, lblSimilarResult);
		grid1.setWidget (2, 0, patientNamesListBox);
		patientNamesListBox.setVisible (false);
		lblSimilarResult.setVisible (false);
		patientNamesListBox.setVisibleItemCount (5);
		patientNamesListBox.setTitle("This list contains the similar results. Selecting anyone fills the details in right panel.");
		flexTable.setWidget (1, 0, leftFlexTable);
		leftFlexTable.getCellFormatter ().setHorizontalAlignment (1, 0, HasHorizontalAlignment.ALIGN_LEFT);
		leftFlexTable.getCellFormatter ().setVerticalAlignment (1, 0, HasVerticalAlignment.ALIGN_TOP);
		leftFlexTable.getCellFormatter ().setVerticalAlignment (0, 0, HasVerticalAlignment.ALIGN_TOP);
		flexTable.setWidget (1, 1, rightFlexTable);
		rightFlexTable.setSize ("100%", "100%");
		lblPatientName.setWordWrap (false);
		lblPatientName.setVisible (false);
		userNameTextBox.setVisible (false);
		lblPatientId.setWordWrap (false);
		lblPatientId.setVisible (false);
		patientIdTextBox.setVisible (false);
		rightFlexTable.setWidget (0, 1, lblPatientId);
		rightFlexTable.setWidget (0, 2, patientIdTextBox);
		rightFlexTable.setWidget (1, 1, lblPatientName);
		rightFlexTable.setWidget (1, 2, userNameTextBox);
		lblDateScreened.setWordWrap(false);
		lblDateScreened.setVisible(false);
		screenedDateTextBox.setVisible (false);
		lblDateScreened.setVisible (false);
		screenedDateTextBox.setVisible (false);
		lblPatientStatus.setWordWrap(false);
		lblPatientStatus.setVisible(false);
		patientStatusTextBox.setVisible(false);
		rightFlexTable.setWidget (2, 1, lblPatientStatus);
		rightFlexTable.setWidget (2, 2, patientStatusTextBox);
		lblConfirmedDate.setWordWrap(false);
		confirmedDateTextBox.setVisible (false);
		lblConfirmedDate.setVisible (false);
		lblConfirmedSource.setWordWrap(false);
		confirmedSourceTextBox.setVisible (false);
		lblConfirmedSource.setVisible (false);
		lblTreatmentOutcome.setWordWrap(false);
		treatmentOutcomeTextBox.setVisible (false);
		lblTreatmentOutcome.setVisible (false);
		lblTreatmentCloseDate.setWordWrap(false);
		treatmentCloseDateTextBox.setVisible (false);
		lblTreatmentCloseDate.setVisible (false);
		lblTreatmentCloseDate.setWordWrap (false);
		lblFormFilled.setVisible (false);
		formFilledComboBox.setVisible(false);
		dateEnteredTextBox.setVisible (false);
		lblDateEntered.setVisible (false);
		lblDateEntered.setWordWrap(false);
		patientidtempListBox.setVisible (false);
		
		rightFlexTable.getCellFormatter ().setVerticalAlignment (2, 0, HasVerticalAlignment.ALIGN_TOP);
		flexTable.getCellFormatter ().setVerticalAlignment (1, 0, HasVerticalAlignment.ALIGN_TOP);
		flexTable.getCellFormatter ().setHorizontalAlignment (1, 1, HasHorizontalAlignment.ALIGN_LEFT);
		flexTable.getCellFormatter ().setVerticalAlignment (1, 1, HasVerticalAlignment.ALIGN_TOP);
		flexTable.getCellFormatter ().setHorizontalAlignment (0, 0, HasHorizontalAlignment.ALIGN_CENTER);
		
		patientNamesListBox.addChangeHandler (this);
		formFilledComboBox.addChangeHandler (this);
		summaryButton.addClickHandler (this);
		closeButton.addClickHandler (this);
		
		patientIdRadioButton.addValueChangeHandler (this);
		patientNameRadioButton.addValueChangeHandler (this);
		
		formFilledComboBox.addItem ("----------SELECT----------");

		String[] filterOptions = {"IS EXACTLY", "STARTS WITH", "ENDS ON", "LOOKS LIKE", "NOT LIKE"};
		for (String s : filterOptions)
		{
			patientFilterTypeComboBox.addItem (s);
		}
		
		refresh (flexTable);
				
		setRights (menuName.toUpperCase ());
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
	 * Clear and Hide the unwanted widgets
	 * 
	 */
	
	public void clear ()
	{

		lblPatientId.setVisible (false);
		patientIdTextBox.setValue ("");
		userNameTextBox.setValue ("");
		patientStatusTextBox.setValue ("");
		screenedDateTextBox.setValue ("");
		confirmedDateTextBox.setValue ("");
		lblPatientId.setVisible (false);
		lblConfirmedDate.setVisible (false);
		confirmedDateTextBox.setVisible (false);
		confirmedSourceTextBox.setValue ("");
		confirmedSourceTextBox.setVisible (false);
		lblConfirmedSource.setVisible (false);
		treatmentOutcomeTextBox.setVisible (false);
		lblTreatmentOutcome.setVisible (false);
		treatmentCloseDateTextBox.setVisible (false);
		lblTreatmentCloseDate.setVisible (false);
		lblDateEntered.setVisible (false);
		dateEnteredTextBox.setVisible(false);
		lblPatientName.setVisible (false);
		patientIdTextBox.setVisible (false);
		userNameTextBox.setVisible (false);	
		lblDateScreened.setVisible (false);
		screenedDateTextBox.setVisible (false);
		lblFormFilled.setVisible (false);
		formFilledComboBox.setVisible(false);
		lblPatientStatus.setVisible(false);
		patientStatusTextBox.setVisible(false);
		formFilledComboBox.clear();
		formFilledComboBox.addItem ("----------SELECT----------");		
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

	
	/**
	 * This method displays the encounters elements of chosen form
	 * 
	 * @param encounterType
	 */
	
	public void fillEncounter(final String encounter)
	{
		
		try
		{
			service.getColumnData ("encounter", "e_id", "encounter_type='" +encounter+"'and pid1='" + patientId + "'", new AsyncCallback<String[]> ()
			{
				public void onFailure (Throwable caught)
				{
					load(false);
				}

				public void onSuccess (String[] result)
			    {    	
			    	
			    	int eid = Integer.parseInt(result[result.length - 1]);			    	
			    	EncounterId encounterId = new EncounterId (eid , patientId, TBRT.getCurrentUserName (), encounter);
					
			    	try
					{
						service.findEncounter (encounterId, new AsyncCallback<Encounter> ()
						{

							public void onFailure (Throwable caught)
							{
								load (false);
							}

							public void onSuccess (Encounter result)
							{
								final Encounter currentEncounter = result;
								dateEnteredTextBox.setValue (currentEncounter.getDateEntered ().toString ());
								dateEnteredTextBox.setVisible (true);
								lblDateEntered.setVisible (true);
								
								service.findEncounterResults (currentEncounter.getId (), new AsyncCallback<EncounterResults[]> ()
								{

									public void onFailure (Throwable caught)
									{
										load(false);
									}

									public void onSuccess (EncounterResults[] result)
									{
										resultsGrid.setVisible (true);
										resultsGrid = new Grid (result.length, 2);
										final Label[] labels = new Label[result.length];
										final TextBox[] textBoxes = new TextBox[result.length];
										for (int i = 0; i < result.length; i++)
										{
											try
											{
												final int cnt = i;
												final String element = result[i].getId ().getElement ();
												final String value = result[i].getValue ();
												textBoxes[cnt] = new TextBox ();
												textBoxes[cnt].setValue (element);
												textBoxes[cnt].setName (element);
												textBoxes[cnt].setText (value);
												resultsGrid.setWidget (cnt, 1, textBoxes[cnt]);
												
												service.findEncounterElement (currentEncounter.getId ().getEncounterType (), result[i].getId ().getElement (), new AsyncCallback<EncounterElement> ()
												{

													public void onFailure (Throwable caught)
													{
														load(false);
													}

													public void onSuccess (EncounterElement result)
													{
														labels[cnt] = new Label (result.getDescription ());
														labels[cnt].setWordWrap (false);
														resultsGrid.setWidget (cnt, 0, labels[cnt]);
													}
															
												}); //findEncounterElement Ends
												
												resultsPanel.setWidget (resultsGrid);
												load (false);
											}
											catch (Exception e)
											{
												e.printStackTrace ();
												load (false);
											}
										} // for loop ends
									} // Success Function Ends
								});  //findEncounterResults Ends
								
							} // Success function Ends
						}); //findEncounter Ends
					}
					catch (Exception e)
					{
						load(false);
						e.printStackTrace();
					}
					load(false);						
			    } // success function ends
			}); //getColumnData Ends
		} // try ends
		catch (Exception e1)
		{
			e1.printStackTrace();
		}
		
	}
   
	/**
	 * Fills the patient information
	 * 
	 */
	public void fillData ()
	{
	
		try
		{
			service.findPerson (patientId, new AsyncCallback<Person> ()		
			{

				@Override
				public void onFailure (Throwable caught)
				{
					load(false);
					
				}

				@Override
				public void onSuccess (Person result)
				{
										
					lblPatientName.setVisible (true);
					userNameTextBox.setVisible (true);
					
					lblFormFilled.setVisible (true);
					formFilledComboBox.setVisible(true);
					
					lblPatientStatus.setVisible(true);
					patientStatusTextBox.setVisible(true);
					
					lblPatientId.setVisible (true);
					patientIdTextBox.setVisible (true);
					
					String name = result.getFirstName ();
					name += " " + result.getMiddleName ();
					name += " " + result.getLastName ();
					
					userNameTextBox.setValue (name);
					formFilledComboBox.addItem ("REGISTRATION FORM");
					patientIdTextBox.setValue (patientId);
						try
						{
							service.findPatient (patientId, new AsyncCallback<Patient> ()
							{
								
								@Override
								public void onFailure (Throwable caught)
								{
									load(false);
									
								}

								@Override
								public void onSuccess (Patient patient)
								{
								    
									if (patient.getPatientStatus ()== null)
									{	
										rightFlexTable.setWidget (3, 1, lblFormFilled);
										rightFlexTable.setWidget (3, 2, formFilledComboBox);

										rightFlexTable.setWidget (4, 1, lblDateEntered);
										rightFlexTable.setWidget (4, 2, dateEnteredTextBox);
										rightFlexTable.setWidget (6, 2, resultsPanel);
										resultsPanel.setWidget (resultsGrid);
										patientStatusTextBox.setValue ("SCREENING PENDING");
									}
									else{

										lblDateScreened.setVisible (true);
										screenedDateTextBox.setVisible (true);
										rightFlexTable.setWidget (3, 1, lblDateScreened);
										rightFlexTable.setWidget (3, 2, screenedDateTextBox);
										
										
										if(patient.getPatientStatus ().equals ("SCREENED") || patient.getPatientStatus ().equals("SUSPECT") || patient.getPatientStatus ().equals("NOT"))
										{
											rightFlexTable.setWidget (4, 1, lblFormFilled);
											rightFlexTable.setWidget (4, 2, formFilledComboBox);

											rightFlexTable.setWidget (5, 1, lblDateEntered);
											rightFlexTable.setWidget (5, 2, dateEnteredTextBox);
											rightFlexTable.setWidget (7, 2, resultsPanel);
											resultsPanel.setWidget (resultsGrid);
											
											screenedDateTextBox.setValue (patient.getDateScreened ().toString ());
											formFilledComboBox.addItem ("SCREENING FORM");
											if(patient.getPatientStatus ().equals ("SCREENED"))
												patientStatusTextBox.setValue ("NON-SUSPECT");
											else if (patient.getPatientStatus ().equals ("SUSPECT"))
												patientStatusTextBox.setValue ("SUSPECT");
											else
											{
												patientStatusTextBox.setValue ("CLEAR");
												formFilledComboBox.addItem ("DIAGNOSIS FORM");
											}
										}
									
									
										if (patient.getPatientStatus ().equals ("CONFIRMED") || patient.getPatientStatus ().equals ("CLOSED"))
										{
											screenedDateTextBox.setValue (patient.getDateScreened ().toString ());
											formFilledComboBox.addItem ("SCREENING FORM");
											formFilledComboBox.addItem ("DIAGNOSIS FORM");
											patientStatusTextBox.setValue ("TB DIAGNOSED");
											lblConfirmedDate.setVisible (true);
											confirmedDateTextBox.setVisible (true);
											confirmedDateTextBox.setValue (patient.getDateConfirmed ().toString ());
											lblConfirmedSource.setVisible (true);
											confirmedSourceTextBox.setVisible (true);
											confirmedSourceTextBox.setValue (patient.getConfirmationSource ());
											
											rightFlexTable.setWidget (4, 1, lblConfirmedDate);
											rightFlexTable.setWidget (4, 2, confirmedDateTextBox);

											rightFlexTable.setWidget (5, 1, lblConfirmedSource);
											rightFlexTable.setWidget (5, 2, confirmedSourceTextBox);
											

											rightFlexTable.setWidget (6, 1, lblDateEntered);
											rightFlexTable.setWidget (7, 2, dateEnteredTextBox);
											rightFlexTable.setWidget (8, 2, resultsPanel);
											resultsPanel.setWidget (resultsGrid);
							   			
											if (patient.getPatientStatus ().equals("CLOSED"))
											{	
												rightFlexTable.setWidget (6, 1, lblTreatmentOutcome);
												rightFlexTable.setWidget (6, 2, treatmentOutcomeTextBox);

												rightFlexTable.setWidget (7, 1, lblTreatmentCloseDate);
												rightFlexTable.setWidget (7, 2, treatmentCloseDateTextBox);
												
												rightFlexTable.setWidget (8, 1, lblFormFilled);
												rightFlexTable.setWidget (8, 2, formFilledComboBox);
												

												rightFlexTable.setWidget (9, 1, lblDateEntered);
												rightFlexTable.setWidget (9, 2, dateEnteredTextBox);
												rightFlexTable.setWidget (10, 2, resultsPanel);
												resultsPanel.setWidget (resultsGrid);
												
												formFilledComboBox.addItem ("TREATMENT OUTCOME FORM");
												lblTreatmentOutcome.setVisible (true);
												treatmentOutcomeTextBox.setVisible (true);
												treatmentOutcomeTextBox.setValue (patient.getTreatmentOutcome ());	
												lblTreatmentCloseDate.setVisible (true);
												treatmentCloseDateTextBox.setVisible (true);
												treatmentCloseDateTextBox.setValue (patient.getDateClosed ().toString ());
											}
											else
											{
												rightFlexTable.setWidget (6, 1, lblFormFilled);
												rightFlexTable.setWidget (6, 2, formFilledComboBox);

												rightFlexTable.setWidget (7, 1, lblDateEntered);
												rightFlexTable.setWidget (7, 2, dateEnteredTextBox);
												rightFlexTable.setWidget (9, 2, resultsPanel);
												resultsPanel.setWidget (resultsGrid);
												
											}
												
										}
										
									}
								} // Success Function ends
									
							}); //findPatient Ends
						}
						catch (Exception e)
						{
							load(false);
							e.printStackTrace();
						}
					} // Success Function Ends				
			}); //findPerson Ends
			
		} // try Ends
		catch (Exception e)
		{
			load(false);
			e.printStackTrace();
		}
		
		load(false);
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
								enteredPatientIdTextBox.setEnabled (rights.getAccess (AccessType.SELECT));
								summaryButton.setEnabled (rights.getAccess (AccessType.SELECT));
								load (false);
							} 

							public void onFailure (Throwable caught)
							{
								load (false);
							}
						}); // findUser Ends
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
			}); // getUserRights Ends
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
		
		if (sender == closeButton){
			MainMenuComposite.clear ();
		} // if  for {sender = closeButton} ends 
		
		if(sender == summaryButton){
			
			patientNamesListBox.setVisible (false);
			lblSimilarResult.setVisible (false);
			
			if(patientIdRadioButton.getValue())
			{
				if (enteredPatientIdTextBox.getValue ().length ()==0){
					Window.alert ("Please enter Patient Id to search.\n");
					enteredPatientIdTextBox.setValue ("");
					clear();
					load(false);
				}
				else{
					patientId = enteredPatientIdTextBox.getValue ();
					resultsGrid.setVisible (false);
					try
					{
						service.findPatient (enteredPatientIdTextBox.getValue (), new AsyncCallback<Patient> (){

							@Override
							public void onFailure (Throwable caught)
							{
								load(false);
							}

							@Override
							public void onSuccess (Patient result)
							{
							//patient validation
								if (result == null)
								{
									Window.alert ("Patient ID was not found in the database. Please re-enter.\n");
									enteredPatientIdTextBox.setValue ("");
									clear();
									load(false);
								}
								else
								{
									clear();
									fillData();
									load(false);
								}
							}
						
						}); //findPatient Ends
					} // try ends!
					catch (Exception e)
					{
						load(false);
						e.printStackTrace();
					}
				 }
			} // if search by patient ID ends!
			
			else if(patientNameRadioButton.getValue())
			{
				resultsGrid.setVisible(false);
				if (patientNameTextBox.getValue ().length ()==0){
					Window.alert ("Please enter Patient Name to search.\n");
					clear();
					load(false);
				}
				
				else{
					String patientName = null;
					switch (patientFilterTypeComboBox.getSelectedIndex ())
					{
						case 0 :
							patientName = " = '" + TBRTClient.get (patientNameTextBox) + "'";
							break;
						case 1 :
							patientName = " LIKE '" + TBRTClient.get (patientNameTextBox) + "%'";
							break;
						case 2 :
							patientName = " LIKE '%" + TBRTClient.get (patientNameTextBox) + "'";
							break;
						case 3 :
							patientName = " LIKE '%" + TBRTClient.get (patientNameTextBox) + "%'";
							break;
						case 4 :
							patientName = " NOT LIKE '%" + TBRTClient.get (patientNameTextBox) + "%'";
							break;
					}
				
					String sqlQuery = "Select * from person where concat(first_name,middle_name,last_name)"+patientName+"|| concat(first_name,' ',middle_name,' ',last_name)"+ patientName+ "|| concat(first_name,' ',middle_name,last_name)"+patientName;
				
					try
					{
						service.getTableData (sqlQuery, new AsyncCallback<String [][]> (){

						@Override
						public void onFailure (Throwable caught)
						{
							load(false);
							
						}

						@Override
						public void onSuccess (String[][] result)
						{
							if(result.length==0){
								Window.alert ("No such Patient was not found in the database. Please re-enter.\n");
								patientNameTextBox.setValue ("");
								clear();
								load(false);								
							}
							else
							if(result.length==1){
								patientId = result[0][0];
								resultsGrid.setVisible (false);
								clear();
								fillData();
								load(false);
							}
							else{
								
								patientNamesListBox.clear ();
								patientidtempListBox.clear();
								patientNamesListBox.setVisible (true);
								lblSimilarResult.setVisible(true);
								for (String[] s : result){
									patientNamesListBox.addItem (s[2]+" "+s[3]+" "+s[4]);
									patientidtempListBox.addItem (s[0]);
								}
								load(false);
							}
								
						 }
						
					  }); //getTable ends
					} // try ends
					catch (Exception e)
					{
						e.printStackTrace();
						load(false);
					}
				
				}
						
			} // if search by patient name ends!
			
			else
			{
				Window.alert ("Please enter Patient's Id or Name to search.");
				load (false);
				
			}
				
			
		} // if  for {sender = SummaryButton} ends 
	}

	
	public void onChange (ChangeEvent event)
	{
		Widget sender = (Widget) event.getSource ();
		load (true);
				
		if (sender == formFilledComboBox)
		{
			
			String formType = formFilledComboBox.getValue (formFilledComboBox.getSelectedIndex ());
			
			if(formType.equals ("REGISTRATION FORM"))
				fillEncounter("PAT_REG");				
				
			else if(formType.equals ("SCREENING FORM"))
				fillEncounter("SCREEN_A");
							
			else if(formType.equals ("DIAGNOSIS FORM"))
				fillEncounter("DIAG_PAT");
				
			else if(formType.equals ("TREATMENT OUTCOME FORM"))
				fillEncounter("TREATMENT");
						
			else 
				grid.setVisible (false);
		 } // if  for {sender = formFilledComboBox} ends 	
		
		if(sender == patientNamesListBox)
		{
			
			String sqlQuery = "Select * from person where pid = '"+ patientidtempListBox.getValue (patientNamesListBox.getSelectedIndex ())+"'";
					 
				try
				{
					service.getTableData (sqlQuery, new AsyncCallback<String [][]> (){

						@Override
						public void onFailure (Throwable caught)
						{
							load(false);
							
						}

						@Override
						public void onSuccess (String[][] result)
						{
							
							patientId = result[0][0];
							resultsGrid.setVisible (false);
							clear();
							fillData();
							load(false);
						}
						
					}); //getTable ends!
				}
				catch (Exception e)
				{
					e.printStackTrace();
				} 
			 
			
		} // if  for {sender = patientNamesListBox} ends 
	
	}

	@Override
	public void onValueChange (ValueChangeEvent<Boolean> event)
	{
		Widget sender = (Widget) event.getSource ();
		
		if (sender == patientIdRadioButton)
		{
			enteredPatientIdTextBox.setEnabled (patientIdRadioButton.getValue ());
			patientNameTextBox.setEnabled (patientNameRadioButton.getValue ());
			patientFilterTypeComboBox.setEnabled (patientNameRadioButton.getValue ());
			enteredPatientIdTextBox.setValue ("");
			patientNameTextBox.setValue ("");
			if(patientIdRadioButton.getValue ())enteredPatientIdTextBox.setStyleName ("patientIdTextBox");
			else enteredPatientIdTextBox.setStyleName ("");
			if(patientNameRadioButton.getValue ())patientNameTextBox.setStyleName ("patientIdTextBox");
			else patientNameTextBox.setStyleName ("");
			
		}
		if (sender == patientNameRadioButton)
		{
			enteredPatientIdTextBox.setEnabled (patientIdRadioButton.getValue ());
			patientNameTextBox.setEnabled (patientNameRadioButton.getValue ());
			patientFilterTypeComboBox.setEnabled (patientNameRadioButton.getValue ());
			enteredPatientIdTextBox.setValue ("");
			patientNameTextBox.setValue ("");
			if(patientIdRadioButton.getValue ())enteredPatientIdTextBox.setStyleName ("patientIdTextBox");
			else enteredPatientIdTextBox.setStyleName ("");
			if(patientNameRadioButton.getValue ())patientNameTextBox.setStyleName ("patientIdTextBox");
			else patientNameTextBox.setStyleName ("");
		}
		
	}
	
}
