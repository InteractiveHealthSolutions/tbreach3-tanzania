
package com.ihsinformatics.tbreach3tanzania.mobile;

import java.util.Date;
import java.util.Hashtable;
import javax.microedition.lcdui.ChoiceGroup;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.TextField;
import javax.microedition.midlet.MIDletStateChangeException;
import com.ihsinformatics.tbreach3tanzania.mobile.shared.ErrorProvider;
import com.ihsinformatics.tbreach3tanzania.mobile.shared.FormType;
import com.ihsinformatics.tbreach3tanzania.mobile.shared.Metadata;
import com.ihsinformatics.tbreach3tanzania.mobile.shared.TBRT;
import com.ihsinformatics.tbreach3tanzania.mobile.util.StringUtil;

public class LoginForm extends BaseForm implements CommandListener
{
	String formType = FormType.LOGIN;
	// private TBReach3TanzaniaMain tbrMidlet;
	private TextField	userNameTextField;
	private TextField	passwordTextField;
	private ChoiceGroup	userRoleChoiceGroup;

	private Command		okButton;
	private Command		exitButton;

	public LoginForm (String title, TBReach3TanzaniaMain tbreach3tanzaniaMidlet)
	{
		super (title, tbreach3tanzaniaMidlet);
		userNameTextField = new TextField ("Username", "", 12, TextField.ANY);
		passwordTextField = new TextField ("Password", "", 50, TextField.PASSWORD);
		userRoleChoiceGroup = new ChoiceGroup ("Login As:", ChoiceGroup.POPUP);
		String[] roles = Metadata.getMetaValues (Metadata.USER_ROLE);
		for (int i = 0; i < roles.length; i++)
		{
			userRoleChoiceGroup.append (roles[i], null);
		}
		okButton = new Command ("Login", Command.OK, 1);
		exitButton = new Command ("Quit", Command.BACK, 0);
	}

	public void init ()
	{
		setCommandListener (this);
		append (userNameTextField);
		append (passwordTextField);
		append (userRoleChoiceGroup);
		addCommand (okButton);
		addCommand (exitButton);
	}

	public boolean validate ()
	{
		if (userNameTextField == null || userNameTextField.getString ().trim ().length () == 0)
		{
			tbreach3tanzaniaMidlet.showAlert (ErrorProvider.getError (ErrorProvider.EMPTY_DATA_ERROR), null);
			return false;
		}
		else if (passwordTextField == null || passwordTextField.getString ().trim ().length () == 0)
		{
			tbreach3tanzaniaMidlet.showAlert (ErrorProvider.getError (ErrorProvider.EMPTY_DATA_ERROR), null);
			return false;
		}
		return true;
	}

	public void commandAction (Command c, Displayable d)
	{
		
		System.out.println ("Entering command action");
		if (c == okButton)
		{
		 	if (validate ())
			{
				// For Live, comment out the 4 lines below
				// Hashtable model = new Hashtable ();
				// model.put ("status", TBRT.XML_SUCCESS);
				// model.put ("username", userNameTextField.getString ());
				// model.put ("userrole", Metadata.getKey (Metadata.USER_ROLE,
				// StringUtil.getString (userRoleChoiceGroup)));
				
				// For live, uncomment the block below
				
				// Block Start
				String request = createRequestPayload ();
				System.out.println ("Sending to server");
				Hashtable model = tbreach3tanzaniaMidlet.sendToServer (request);
				System.out.println ("Response received");
				if (model == null)
				{
					System.out.println ("Model is null");
					tbreach3tanzaniaMidlet.showAlert (ErrorProvider.getError (ErrorProvider.AUTHENTICATION_ERROR), null);
				}
				// Block End
				
				if ((String) model.get ("status") != null && ((String) model.get ("status")).equals (TBRT.XML_ERROR))
				{
					tbreach3tanzaniaMidlet.showAlert ((String) model.get ("msg"), null);
				}
				else
				{
					String role = (String) model.get ("userrole");
					String userName = (String) model.get ("username");
					TBRT.setCurrentUserName (userName);
					TBRT.setCurrentUserRole (role);
					String loc = (String) model.get ("locations");
					String[] locations = StringUtil.split (loc, ';');
					String[][] metadata = new String[locations.length][2];
					for (int i = 0; i < locations.length; i++)
					{
						String[] parts = StringUtil.split (locations[i], '|');
						metadata[i][0] = parts[0];
						metadata[i][1] = parts[1];
					}
					Metadata.setMetaValues (Metadata.FACILITY, metadata);
					tbreach3tanzaniaMidlet.startMainMenu ();
				}
			}
		}
		else if (c == exitButton)
		{
			System.out.println ("Entering handler");
			deleteAll ();

			try
			{
				tbreach3tanzaniaMidlet.destroyApp (false);
				tbreach3tanzaniaMidlet.notifyDestroyed ();
			}
			catch (MIDletStateChangeException e)
			{
				System.out.println ("Something crashed..." + e.toString ());
			}
			System.out.println ("Leaving handler");
		}
	}

	protected String createRequestPayload ()
	{
		System.out.println ("Preparing URL");
		String request = "";
		String userNameString = userNameTextField.getString ();
		String passwordString = passwordTextField.getString ();
		String userRoleString = Metadata.getKey (Metadata.USER_ROLE, StringUtil.getString (userRoleChoiceGroup));
		request = "form=" + formType;
		request += "&username=" + userNameString;
		request += "&password=" + passwordString;
		request += "&userrole=" + userRoleString;
		request += "&phonetime=" + new Date ().getTime ();
		return request;
	}
}
