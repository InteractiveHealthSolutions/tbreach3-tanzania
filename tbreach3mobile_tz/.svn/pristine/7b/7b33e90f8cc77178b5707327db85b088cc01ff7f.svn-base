/**
 * Initial Screening form for Cough Monitor
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
import javax.microedition.lcdui.TextField;
import com.ihsinformatics.tbreach3tanzania.mobile.shared.ErrorProvider;
import com.ihsinformatics.tbreach3tanzania.mobile.shared.FormType;
import com.ihsinformatics.tbreach3tanzania.mobile.shared.InfoProvider;
import com.ihsinformatics.tbreach3tanzania.mobile.shared.Metadata;
import com.ihsinformatics.tbreach3tanzania.mobile.shared.TBRT;
import com.ihsinformatics.tbreach3tanzania.mobile.util.DateTimeUtil;
import com.ihsinformatics.tbreach3tanzania.mobile.util.StringUtil;

/**
 * @author owais.hussain@irdresearch.org
 * 
 */
public class ScreeningForm extends BaseForm implements CommandListener, ItemStateListener
{
	String		formType	= FormType.SCREENING;
	boolean		isSuspect;
	// Mobile fields
	DateField	dateEntered;
	TextField	patientScreenId;
	ChoiceGroup	screenStrategy;
	ChoiceGroup	communityApproach;
	TextField	nameScreeningFacility;
	ChoiceGroup	cough;
	ChoiceGroup	coughDuration;
	ChoiceGroup	productiveCough;
	ChoiceGroup	haemoptysis;
	ChoiceGroup	fever;
	ChoiceGroup	feverDuration;
	ChoiceGroup	nightSweat;
	ChoiceGroup	chestPain;
	ChoiceGroup	chestPainDuration;
	ChoiceGroup	tbHistory;
	ChoiceGroup	treatmentHistory;
	ChoiceGroup	treatmentDuration;
	ChoiceGroup	treatmentCompleted;
	ChoiceGroup	familyTbHistory;
	ChoiceGroup	conclusion;
	TextField	nameDiagnosisFacility;
	DateField	dateReferral;

	Command		okCommand	= new Command ("Save", Command.OK, 1);
	Command		backCommand	= new Command ("Back", Command.BACK, 2);

	public ScreeningForm (String title, TBReach3TanzaniaMain tbreach3tanzaniaMidlet)
	{
		super (title, tbreach3tanzaniaMidlet);
	}

	public void init ()
	{
		dateEntered = new DateField ("*Screening Date", DateField.DATE);
		dateEntered.setDate (new Date ());
		patientScreenId = new TextField ("*Patient Screening ID Number", "", TBRT.ID_LENGTH, TextField.ANY);
		screenStrategy = new ChoiceGroup ("*Screening Strategy", ChoiceGroup.POPUP);
		communityApproach = new ChoiceGroup ("*Community Screening Approach", ChoiceGroup.MULTIPLE);
		nameScreeningFacility = new TextField ("*Name of Screening Facility","",TBRT.VALUE_LENGTH, TextField.ANY );
		cough = new ChoiceGroup ("*Do you have a Cough?", ChoiceGroup.POPUP);
		coughDuration = new ChoiceGroup ("Is duration more than two weeks: ", ChoiceGroup.POPUP);
		productiveCough = new ChoiceGroup ("Is your cough Productive: ", ChoiceGroup.POPUP);
		haemoptysis = new ChoiceGroup ("*Is their blood in your Sputum: ", ChoiceGroup.POPUP);
		fever = new ChoiceGroup ("*Do you have Fever?", ChoiceGroup.POPUP);
		feverDuration = new ChoiceGroup ("Is duration more than two weeks: ", ChoiceGroup.POPUP);
		nightSweat = new ChoiceGroup ("*Do you sweat a lot at nights: ", ChoiceGroup.POPUP);
		chestPain = new ChoiceGroup ("*Do you have chest pain?", ChoiceGroup.POPUP);
		chestPainDuration = new ChoiceGroup ("Is Duration more than two weeks: ", ChoiceGroup.POPUP);
		tbHistory = new ChoiceGroup ("*Were you ever diagnosed for TB: ", ChoiceGroup.POPUP);
		treatmentHistory = new ChoiceGroup ("*Have you ever had a TB treatment: ", ChoiceGroup.POPUP);
		treatmentDuration = new ChoiceGroup ("Duration of treatment in Months: ", ChoiceGroup.POPUP);
		treatmentCompleted = new ChoiceGroup ("*Was treatment completed: ", ChoiceGroup.POPUP);
		familyTbHistory = new ChoiceGroup ("*TB family history: ", ChoiceGroup.POPUP);
		conclusion = new ChoiceGroup ("*Is the candidate a suspect of TB: ", ChoiceGroup.POPUP);
		nameDiagnosisFacility = new TextField ("*Referred Diagnosis Facility: ","",TBRT.VALUE_LENGTH, TextField.ANY);
		dateReferral = new DateField ("Referral Date: ", DateField.DATE);
		dateReferral.setDate (new Date ());

		screenStrategy.append ("IFS", null);
		screenStrategy.append ("CMS", null);
		String[] CM = Metadata.getMetaValues (Metadata.COMMUNITY_APPROACH);
		for (int i = 0; i < CM.length; i++)
			communityApproach.append (CM[i], null);
		String[] TEMP = Metadata.getMetaValues (Metadata.TEMPORARAY);
		
		cough.append ("Yes", null);
		cough.append ("No", null);
		cough.append ("Don't know", null);
		cough.append ("Refused", null);
		coughDuration.append ("Yes", null);
		coughDuration.append ("No", null);
		coughDuration.append ("Don't know", null);
		coughDuration.append ("Refused", null);
		productiveCough.append ("Yes", null);
		productiveCough.append ("No", null);
		productiveCough.append ("Don't know", null);
		productiveCough.append ("Refused", null);
		haemoptysis.append ("Yes", null);
		haemoptysis.append ("No", null);
		haemoptysis.append ("Don't know", null);
		haemoptysis.append ("Refused", null);
		fever.append ("Yes", null);
		fever.append ("No", null);
		fever.append ("Don't know", null);
		fever.append ("Refused", null);
		feverDuration.append ("Yes", null);
		feverDuration.append ("No", null);
		nightSweat.append ("Yes", null);
		nightSweat.append ("No", null);
		nightSweat.append ("Don't know", null);
		nightSweat.append ("Refused", null);
		chestPain.append ("Yes", null);
		chestPain.append ("No", null);
		chestPain.append ("Don't know", null);
		chestPain.append ("Refused", null);
		chestPainDuration.append ("Yes", null);
		chestPainDuration.append ("No", null);
		chestPainDuration.append ("Don't know", null);
		chestPainDuration.append ("Refused", null);
		tbHistory.append ("Yes", null);
		tbHistory.append ("No", null);
		tbHistory.append ("Don't know", null);
		tbHistory.append ("Refused", null);
		treatmentHistory.append ("Yes", null);
		treatmentHistory.append ("No", null);
		treatmentHistory.append ("Don't know", null);
		treatmentHistory.append ("Refused", null);
		treatmentCompleted.append ("Yes", null);
		treatmentCompleted.append ("No", null);
		treatmentCompleted.append ("Don't know", null);
		treatmentCompleted.append ("Refused", null);
		familyTbHistory.append ("Yes", null);
		familyTbHistory.append ("No", null);
		familyTbHistory.append ("Don't know", null);
		familyTbHistory.append ("Refused", null);
		conclusion.append ("Yes", null);
		conclusion.append ("No", null);

		for (int i = 0; i < 13; i++)
		{
			treatmentDuration.append (String.valueOf (i), null);
		}

		formItems = new Item[] {dateEntered, patientScreenId, screenStrategy, communityApproach, nameScreeningFacility, cough, coughDuration, productiveCough, haemoptysis, fever, feverDuration,
				nightSweat, chestPain, chestPainDuration, tbHistory, treatmentHistory, treatmentDuration, treatmentCompleted, familyTbHistory, conclusion, nameDiagnosisFacility, dateReferral};
		startTimestamp = new Date ();
		initialShow ();
		// Add commands
		addCommand (okCommand);
		addCommand (backCommand);
		// Add listeners
		setCommandListener (this);
		setItemStateListener (this);
		
		hide (communityApproach);

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

		// Date Entered cannot be a future date or empty
		try
		{
			if (DateTimeUtil.isDateInFuture (dateEntered.getDate ()))
				sb.append ("Date: " + ErrorProvider.getError (ErrorProvider.INVALID_DATE_OR_TIME) + "\n");
		}
		catch (Exception e)
		{
			sb.append ("Form Entry Date has not been set. Please set the date.");
		}
		
		// Date Referral is same or after Screening/Entered date
		if (DateTimeUtil.isDateAfter (dateEntered.getDate (), dateReferral.getDate ()))
			sb.append ("Referral Date: " + ErrorProvider.getError (ErrorProvider.INVALID_DATE_OR_TIME) + "\n");		
			
			
		// Check for empty fields
		if (StringUtil.getString (patientScreenId).equals (""))
			sb.append ("Patient ID: " + ErrorProvider.getError (ErrorProvider.EMPTY_DATA_ERROR) + "\n");
		else if (StringUtil.getString (patientScreenId).length () != patientScreenId.getMaxSize ())
			sb.append ("Patient ID: " + ErrorProvider.getError (ErrorProvider.OUT_OF_RANGE) + "\n");
		if (StringUtil.getString (nameScreeningFacility).equals (""))
			sb.append ("Screening Facility Name: " + ErrorProvider.getError (ErrorProvider.EMPTY_DATA_ERROR) + "\n");
			
		if (StringUtil.getString (conclusion).equals ("Yes"))
			if (StringUtil.getString (nameDiagnosisFacility).equals (""))
				sb.append ("Diagnosis Facility Name: " + ErrorProvider.getError (ErrorProvider.EMPTY_DATA_ERROR) + "\n");
		
		if(StringUtil.getString (screenStrategy).equals("CMS"))
		{
			if(StringUtil.getStringMultipleSelectionCA (communityApproach).equals (""))
				sb.append ("Community Screening Approach: " + ErrorProvider.getError (ErrorProvider.EMPTY_DATA_ERROR) + "\n");

		}
		
		
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
		if (i instanceof TextField)
			return;
		else if(i == screenStrategy)
		{
			if (StringUtil.getString (i).equals ("IFS"))
			{
				hide (communityApproach);
			}
			else
			{
				show (communityApproach);
			}
		}	
		else if (i == cough)
		{
			if (StringUtil.getString (i).equals ("Yes"))
			{
				show (coughDuration);
				show (productiveCough);
			}
			else
			{
				hide (coughDuration);
				hide (productiveCough);
			}
		}
		else if (i == fever)
		{
			if (StringUtil.getString (i).equals ("Yes"))
			{
				show (feverDuration);
			}
			else
			{
				hide (feverDuration);
			}
		}
		else if (i == chestPain)
		{
			if (StringUtil.getString (i).equals ("Yes"))
			{
				show (chestPainDuration);
			}
			else
			{
				hide (chestPainDuration);
			}
		}
		else if (i == treatmentHistory)
		{
			if (StringUtil.getString (i).equals ("Yes"))
			{
				show (treatmentDuration);
				show (treatmentCompleted);
			}
			else
			{
				hide (treatmentDuration);
				hide (treatmentCompleted);
			}
		}
		else if (i == conclusion)
		{
			if (StringUtil.getString (i).equals ("No"))
			{
				hide (nameDiagnosisFacility);
				hide (dateReferral);
			}
			else
			{
				show (nameDiagnosisFacility);
				show (dateReferral);
			}
		}
		
	}
	
	

	public void commandAction (Command command, Displayable displayable)
	{
		if (command == okCommand)
		{

			if (validate ())
			{

				if (StringUtil.getString (conclusion).equals ("Yes"))
					isSuspect = true;
				else
					isSuspect = false;
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
		request.append ("&dateEntered=" + dateEntered.getDate ().getTime ());
		request.append ("&patientId=" + StringUtil.getString (patientScreenId));
		request.append ("&locationId=" + StringUtil.getString (nameScreeningFacility));
		request.append ("&isSuspect=" + (isSuspect ? "1" : "0"));

		request.append ("&results=[");
		request.append ("SCREENING_STRATEGY=" + StringUtil.getString (screenStrategy) + ";");
		if(StringUtil.getString (screenStrategy).equals("CMS"))
		{
			request.append ("COMMUNITY_APPROACH=" + StringUtil.getStringMultipleSelectionCA (communityApproach) + ";");
		}
		request.append ("N_SCREENING_FACILITY=" + StringUtil.getString (nameScreeningFacility) + ";");
		request.append ("COUGH=" + StringUtil.getString (cough).charAt (0) + ";");
		if (StringUtil.getString (cough).equals ("Yes"))
		{
			request.append ("COUGH_DURATION=" + StringUtil.getString (coughDuration).charAt (0) + ";");
			request.append ("PRODUCTIVE_COUGH=" + StringUtil.getString (productiveCough).charAt (0) + ";");
		}
		request.append ("HAEMOPTYSIS=" + StringUtil.getString (haemoptysis).charAt (0) + ";");
		request.append ("FEVER=" + StringUtil.getString (fever).charAt (0) + ";");
		if (StringUtil.getString (fever).equals ("Yes"))
		{
			request.append ("FEVER_DURATION=" + StringUtil.getString (feverDuration).charAt (0) + ";");
		}
		request.append ("NIGHT_SWEAT=" + StringUtil.getString (nightSweat).charAt (0) + ";");
		request.append ("CHEST_PAIN=" + StringUtil.getString (chestPain).charAt (0) + ";");
		if (StringUtil.getString (chestPain).equals ("Yes"))
		{
			request.append ("CHEST_PAIN_DURATION=" + StringUtil.getString (chestPainDuration).charAt (0) + ";");
		}
		request.append ("TB_HISTORY=" + StringUtil.getString (tbHistory).charAt (0) + ";");
		request.append ("TREATMENT_HISTORY=" + StringUtil.getString (treatmentHistory).charAt (0) + ";");
		request.append ("TREATMENT_DURATION=" + StringUtil.getString (treatmentDuration) + ";");
		request.append ("TREATMENT_COMPLETED=" + StringUtil.getString (treatmentCompleted).charAt (0) + ";");
		request.append ("FAMILY_TB_HISTORY=" + StringUtil.getString (familyTbHistory).charAt (0) + ";");
		request.append ("CONCLUSION=" + StringUtil.getString (conclusion).charAt (0));
		if (StringUtil.getString (conclusion).equals ("Yes"))
		{
			request.append (";" + "N_DIAGNOSIS_FACILITY=" + StringUtil.getString (nameDiagnosisFacility) + ";");
			request.append ("REFERRAL_DATE=" + DateTimeUtil.getDateTime (dateReferral.getDate ()));
		}

		request.append ("]");
		return request.toString ();
	}

}