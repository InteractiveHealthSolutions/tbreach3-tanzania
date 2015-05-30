/**
 * Main Menu Composite for TB CONTROL client
 */

package com.ihsinformatics.tbreach3tanzania.client;

import java.util.Date;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.MenuItem;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.ihsinformatics.tbreach3tanzania.shared.TBRT;

public class MainMenuComposite extends Composite
{
	private static VerticalPanel	mainVerticalPanel;

	private MenuBar					mainMenuBar						= new MenuBar (false);
	private MenuBar					setupMenuBar					= new MenuBar (true);
	private MenuBar					formsMenuBar					= new MenuBar (true);
	private MenuBar					smsMenuBar						= new MenuBar (true);
	private MenuBar					reportingMenuBar				= new MenuBar (true);
	private MenuBar					helpMenuBar						= new MenuBar (true);

	private MenuItem				smsMenuItem						= new MenuItem ("SMS", false, smsMenuBar);
	private MenuItem				setupMenuItem					= new MenuItem ("Setup", false, setupMenuBar);
	private MenuItem				formsMenuItem					= new MenuItem ("Forms", false, formsMenuBar);
	private MenuItem				reportingMenuItem				= new MenuItem ("Reporting", false, reportingMenuBar);
	private MenuItem				helpMenuItem					= new MenuItem ("Help", false, helpMenuBar);

	private MenuItem				definitionsMenuItem				= new MenuItem ("Definitions", false, (Command) null);				
	private MenuItem				defaultsMenuItem				= new MenuItem ("Defaults", false, (Command) null);					
	private MenuItem				locationsMenuItem				= new MenuItem ("Locations", false, (Command) null);
	private MenuItem				usersMenuItem					= new MenuItem ("Users", false, (Command) null);
	private MenuItem				userRightsMenuItem				= new MenuItem ("User Rights", false, (Command) null);
	private MenuItem				userMappingMenuItem				= new MenuItem ("User Mapping", false, (Command) null);

	private MenuItem				encounterTypeMenuItem			= new MenuItem ("Encounter Types", false, (Command) null);
	private MenuItem				encounterElementMenuItem		= new MenuItem ("Encounter Elements", false, (Command) null);
	private MenuItem				encounterValueMenuItem			= new MenuItem ("Encounter Values", false, (Command) null);
	private MenuItem				encounterPrerequisiteMenuItem	= new MenuItem ("Encounter Pre-requisites", false, (Command) null);
	private MenuItem				encountersMenuItem				= new MenuItem ("Encounters", false, (Command) null);
	private MenuItem                patientSummaryMenuItem			= new MenuItem ("Patient Summary", false, (Command) null);
	private MenuItem				dashboardMenuItem				= new MenuItem ("Dashboard", false, (Command) null);
	private MenuItem				reportsMenuItem					= new MenuItem ("Reports", false, (Command) null);
	private MenuItem				patientSearchMenuItem			= new MenuItem ("Patient Search", false, (Command) null);
	private MenuItem				logsMenuItem					= new MenuItem ("Logs", false, (Command) null);
	private MenuItem				smsUtilityMenuItem				= new MenuItem ("SMS Utility", false, (Command) null);
	private MenuItem				smsRuleMenuItem					= new MenuItem ("SMS Rules Setup", false, (Command) null);
	private MenuItem				smsRecipientMenuItem			= new MenuItem ("SMS Recipient Setup", false, (Command) null);
	private MenuItem				geneXpertResultMenuItem			= new MenuItem ("GeneXpert Test Results", false, (Command) null);
	
	private MenuItem				aboutMenuItem					= new MenuItem ("About Us", false, (Command) null);
	private MenuItem				aboutMeMenuItem					= new MenuItem ("About Me", false, (Command) null);
	//private MenuItem				helpContentsMenuItem			= new MenuItem ("Help Contents", false, (Command) null);
	private MenuItem				feedbackMenuItem				= new MenuItem ("Feedback", false, (Command) null);
	private MenuItem				logoutMenuItem					= new MenuItem ("Logout", false, (Command) null);

	@SuppressWarnings("deprecation")
	public MainMenuComposite ()
	{
		VerticalPanel topVerticalPanel = new VerticalPanel ();
		initWidget (topVerticalPanel);
		mainVerticalPanel = new VerticalPanel ();
		mainVerticalPanel.setHorizontalAlignment (HasHorizontalAlignment.ALIGN_CENTER);
		mainVerticalPanel.setSize ("100%", "100%");
		topVerticalPanel.setHorizontalAlignment (HasHorizontalAlignment.ALIGN_CENTER);
		topVerticalPanel.add (mainMenuBar);
		topVerticalPanel.setSize ("400px", "100%");
		topVerticalPanel.add (mainVerticalPanel);
		mainMenuBar.setSize ("100%", "100%");
		mainMenuBar.setAutoOpen (true);
		mainMenuBar.setAnimationEnabled (true);
		setupMenuBar.setAutoOpen (true);
		setupMenuBar.setAnimationEnabled (true);
		formsMenuBar.setAutoOpen (true);
		formsMenuBar.setAnimationEnabled (true);
		reportingMenuBar.setAutoOpen (true);
		reportingMenuBar.setAnimationEnabled (true);
		helpMenuBar.setAutoOpen (true);
		helpMenuBar.setAnimationEnabled (true);
		
		
		geneXpertResultMenuItem.setCommand (new Command ()
		{
		public void execute()
		{
			clear ();
			Cookies.setCookie ("CurrentMenu", "SMS");
			try
			{
				mainVerticalPanel.add (new genexpert_ResultComposite ().asWidget ());
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
			
		}
	
	
		});

		definitionsMenuItem.setCommand (new Command ()
		{
			public void execute ()
			{
				clear ();
				Cookies.setCookie ("CurrentMenu", "DEFINITION");
				mainVerticalPanel.add (new DefinitionComposite ().asWidget ());
			}
		});
		defaultsMenuItem.setCommand (new Command ()
		{
			public void execute ()
			{
				clear ();
				Cookies.setCookie ("CurrentMenu", "DEFINITION");
				mainVerticalPanel.add (new DefaultsComposite ().asWidget ());
			}
		});
		locationsMenuItem.setCommand (new Command()
		{
			public void execute ()
			{
				clear ();
				Cookies.setCookie ("CurrentMenu", "SETUP");
				mainVerticalPanel.add (new LocationComposite ().asWidget ());
			}
		});
		usersMenuItem.setCommand (new Command ()
		{
			public void execute ()
			{
				clear ();
				Cookies.setCookie ("CurrentMenu", "SETUP");
				mainVerticalPanel.add (new UsersComposite ().asWidget ());
			}
		});
		userRightsMenuItem.setCommand (new Command ()
		{
			public void execute ()
			{
				clear ();
				Cookies.setCookie ("CurrentMenu", "SETUP");
				mainVerticalPanel.add (new UserRightsComposite ().asWidget ());
			}
		});
		userMappingMenuItem.setCommand (new Command()
		{
			public void execute ()
			{
				clear ();
				Cookies.setCookie ("CurrentMenu", "SETUP");
				mainVerticalPanel.add (new UserMappingComposite ().asWidget ());
			}
		});
		encounterTypeMenuItem.setCommand (new Command ()
		{

			public void execute ()
			{
				clear ();
				Cookies.setCookie ("CurrentMenu", "ENCOUNTER");
				mainVerticalPanel.add (new EncounterTypeComposite ().asWidget ());
			}
		});
		encounterElementMenuItem.setCommand (new Command ()
		{
			public void execute ()
			{
				clear ();
				Cookies.setCookie ("CurrentMenu", "ENCOUNTER");
				mainVerticalPanel.add (new EncounterElementComposite ().asWidget ());
			}
		});
		encounterValueMenuItem.setCommand (new Command ()
		{
			public void execute ()
			{
				clear ();
				Cookies.setCookie ("CurrentMenu", "ENCOUNTER");
				mainVerticalPanel.add (new EncounterValueComposite ().asWidget ());
			}
		});
		encounterPrerequisiteMenuItem.setCommand (new Command ()
		{
			public void execute ()
			{
				clear ();
				Cookies.setCookie ("CurrentMenu", "ENCOUNTER");
				mainVerticalPanel.add (new EncounterPrerequisiteComposite ().asWidget ());
			}
		});
		encountersMenuItem.setCommand (new Command ()
		{
			public void execute ()
			{
				clear ();
				Cookies.setCookie ("CurrentMenu", "ENCOUNTER");
				mainVerticalPanel.add (new EncounterComposite ().asWidget ());
			}
		});
		patientSearchMenuItem.setCommand (new Command ()
		{

			public void execute ()
			{
				clear ();
				Cookies.setCookie ("CurrentMenu", "DATALOG");
				mainVerticalPanel.add (new Report_PatientComposite ().asWidget ());
			}
		});
		patientSummaryMenuItem.setCommand (new Command ()
		{

			public void execute ()
			{
				clear ();
				Cookies.setCookie ("CurrentMenu", "DATALOG");
				mainVerticalPanel.add (new Report_PatientSummary ().asWidget ());
			}
		});
		
		dashboardMenuItem.setCommand (new Command ()
		{

			public void execute ()
			{
				clear ();
				Cookies.setCookie ("CurrentMenu", "DATALOG");
				mainVerticalPanel.add (new Report_DashboardComposite ().asWidget ());
			}
		});
		reportsMenuItem.setCommand (new Command ()
		{

			public void execute ()
			{
				clear ();
				Cookies.setCookie ("CurrentMenu", "DATALOG");
				mainVerticalPanel.add (new Report_ReportsComposite ().asWidget ());
			}
		});
		logsMenuItem.setCommand (new Command ()
		{

			public void execute ()
			{
				clear ();
				Cookies.setCookie ("CurrentMenu", "DATALOG");
				mainVerticalPanel.add (new Report_LogComposite ().asWidget ());
			}
		});
		smsUtilityMenuItem.setCommand (new Command ()
		{

			public void execute ()
			{
				clear ();
				Cookies.setCookie ("CurrentMenu", "SMS");
				mainVerticalPanel.add (new SMSComposite ().asWidget ());
			}
		});
		smsRuleMenuItem.setCommand (new Command ()
		{

			public void execute ()
			{
				clear ();
				Cookies.setCookie ("CurrentMenu", "SMS");
				mainVerticalPanel.add (new SMSRuleComposite ().asWidget ());
			}
		});
		smsRecipientMenuItem.setCommand (new Command ()
		{

			public void execute ()
			{
				clear ();
				Cookies.setCookie ("CurrentMenu", "SMS");
				mainVerticalPanel.add (new SMSRecipientComposite ().asWidget ());
			}
		});
		aboutMeMenuItem.setCommand (new Command ()
		{
			public void execute ()
			{
				try
				{
					String user = Cookies.getCookie ("UserName");
					Date loginDate = new Date (Long.parseLong (Cookies.getCookie ("LoginTime")));
					int mins = new Date (new Date ().getTime () - loginDate.getTime ()).getMinutes ();
					String str = "CURRENT USER: " + user + "\n" + "LOGIN TIME: " + loginDate.toGMTString ().replace ("GMT", "") + "\n" + "CURRENT SESSION: " + mins + " mins";
					Window.alert (str);
				}
				catch (Exception e)
				{
					e.printStackTrace ();
				}
			}
		});
		aboutMenuItem.setCommand (new Command ()
		{

			public void execute ()
			{
				clear ();
				Cookies.setCookie ("CurrentMenu", "About Us");
				mainVerticalPanel.add (new AboutUsComposite ().asWidget ());
			}
		});
		feedbackMenuItem.setCommand (new Command ()
		{

			public void execute ()
			{
				clear ();
				Cookies.setCookie ("CurrentMenu", "FEEDBACK");
				// mainVerticalPanel.add (new FeedbackComposite ().asWidget ());
			}
		});
		
		logoutMenuItem.setCommand (new Command ()
		{
			public void execute ()
			{
				TBReach3Tanzania.logout ();
			}
		});

		if (TBRT.getCurrentRole().equals ("ADMIN")){
			mainMenuBar.addItem (setupMenuItem);	
			mainMenuBar.addItem (formsMenuItem);
		}
		
		mainMenuBar.addItem (smsMenuItem);	
		mainMenuBar.addItem (reportingMenuItem);
		mainMenuBar.addItem (helpMenuItem);
		mainMenuBar.addItem (logoutMenuItem);

		setupMenuBar.addItem (definitionsMenuItem);
		setupMenuBar.addItem (usersMenuItem);
		setupMenuBar.addItem (userRightsMenuItem);
		setupMenuBar.addItem (userMappingMenuItem);
		setupMenuBar.addItem (locationsMenuItem);
		

		
		formsMenuBar.addItem (encounterTypeMenuItem);
		formsMenuBar.addItem (encounterElementMenuItem);
		formsMenuBar.addItem (encounterPrerequisiteMenuItem);
		formsMenuBar.addItem (encountersMenuItem);
		
		
		smsMenuBar.addItem  (smsUtilityMenuItem);
		smsMenuBar.addItem  (smsRuleMenuItem);
		smsMenuBar.addItem  (smsRecipientMenuItem);
		smsMenuBar.addItem (geneXpertResultMenuItem);

		reportingMenuBar.addItem (patientSummaryMenuItem);
		reportingMenuBar.addItem (dashboardMenuItem);
		reportingMenuBar.addItem (reportsMenuItem);
		
		helpMenuBar.addItem (aboutMeMenuItem);
		helpMenuBar.addItem (aboutMenuItem);
	}

	public static void clear ()
	{
		Cookies.setCookie ("CurrentMenu", "");
		mainVerticalPanel.clear ();
	}
}
