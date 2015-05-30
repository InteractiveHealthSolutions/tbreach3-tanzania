
package com.ihsinformatics.tbreach3tanzania.mobile;

import javax.microedition.lcdui.Choice;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.List;
import javax.microedition.midlet.MIDletStateChangeException;
import com.ihsinformatics.tbreach3tanzania.mobile.model.ListItem;
import com.ihsinformatics.tbreach3tanzania.mobile.shared.MenuItem;

class MainList extends List implements CommandListener
{

	private TBReach3TanzaniaMain	tbreach3tanzaniaMidlet;
	private ListItem[]			itemList;
	private Command				cmdOK;
	private Command				cmdExit;

	public MainList (String title, TBReach3TanzaniaMain tbreach3tanzaniaMidlet)
	{
		super (title, Choice.IMPLICIT);
		this.tbreach3tanzaniaMidlet = tbreach3tanzaniaMidlet;
		cmdOK = new Command ("Ok", Command.OK, 0);
		cmdExit = new Command ("Quit", Command.EXIT, 1);
	}

	public void commandAction (Command c, Displayable d)
	{
		if (c == cmdOK)
		{
			int listIndex;
			// Determine what option has been selected and change display
			listIndex = tbreach3tanzaniaMidlet.getSettings ().getItemIndex (getString (this.getSelectedIndex ()));
			switch (listIndex)
			{
				case MenuItem.MENU_REPORT_LIST :
					tbreach3tanzaniaMidlet.reportList.setPrevDisplayable (this);
					tbreach3tanzaniaMidlet.startBaseForm (tbreach3tanzaniaMidlet.reportList);
					break;
				case MenuItem.MENU_REGISTRATION :
					tbreach3tanzaniaMidlet.registrationForm.setPrevDisplayable (this);
					tbreach3tanzaniaMidlet.startBaseForm (tbreach3tanzaniaMidlet.registrationForm);
					break;
				case MenuItem.MENU_SCREENING :
					tbreach3tanzaniaMidlet.screeningForm.setPrevDisplayable (this);
					tbreach3tanzaniaMidlet.startBaseForm (tbreach3tanzaniaMidlet.screeningForm);
					break;
				case MenuItem.MENU_DIAGNOSIS :
					tbreach3tanzaniaMidlet.diagnosisForm.setPrevDisplayable (this);
					tbreach3tanzaniaMidlet.startBaseForm (tbreach3tanzaniaMidlet.diagnosisForm);
					break;	
				case MenuItem.MENU_TREATMENT :
					tbreach3tanzaniaMidlet.treatmentForm.setPrevDisplayable (this);
					tbreach3tanzaniaMidlet.startBaseForm (tbreach3tanzaniaMidlet.treatmentForm);
					break;
			
			}
		}
		else if (c == cmdExit)
		{
			System.out.println ("entering handler");
			deleteAll ();

			try
			{
				tbreach3tanzaniaMidlet.destroyApp (false);
				tbreach3tanzaniaMidlet.notifyDestroyed ();
			}
			catch (MIDletStateChangeException e)
			{
				e.printStackTrace ();
			}
			System.out.println ("leaving handler");
		}
	}

	public void init ()
	{
		tbreach3tanzaniaMidlet.getSettings ().reloadItems ();
		deleteAll ();
		itemList = tbreach3tanzaniaMidlet.getSettings ().getListItems ();
		for (int i = 0; i < itemList.length; i++)
			if (itemList[i].isShow ())
				append (itemList[i].getDisplayName (), null);
		addCommand (cmdOK);
		addCommand (cmdExit);
		setCommandListener (this);
	}
}
