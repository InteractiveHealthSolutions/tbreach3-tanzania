/**
 * Metadata class to hold all Definitions
 */

package com.ihsinformatics.tbreach3tanzania.mobile.shared;

/**
 * @author owais.hussain@irdresearch.org
 * 
 */
public class Metadata
{
	public static final int				DISEASE_CATEGORY	= 0;
	public static final int				DISEASE_TYPE		= 1;
	public static final int				GXP_RESULT			= 2;
	public static final int				LOCATION_TYPE		= 3;
	public static final int				MARITAL_STATUS		= 4;
	public static final int				PATIENT_TYPE		= 5;
	public static final int				REGIMEN				= 6;
	public static final int				REGIMEN_TYPE		= 7;
	public static final int				SMEAR_RESULT		= 8;
	public static final int				TREATMENT_OUTCOME	= 9;
	public static final int				TREATMENT_PHASE		= 10;
	public static final int				USER_ROLE			= 11;
	public static final int				LABORATORY			= 12;
	public static final int				DISTRICT			= 13;
	public static final int             REGION              = 14;
	public static final int				FACILITY			= 15;
	public static final int				MONTH				= 16;
	public static final int				FEEDBACK_TYPE		= 17;
	public static final int				AMPATH_SITES		= 18;
	public static final int				COMMUNITY_APPROACH	= 19;
	public static final int				TEMPORARAY			= 20;

	private static final String[][][]	metadata			= {
		
		// DISEASE_CATEGORY
		{ 
			{"Category I", "CATI"}, 
			{"Category II", "CATII"}
		}, 
		// DISEASE_TYPE
		{ 
			{"Extra-Pulmonary TB", "EP"}, 
			{"Pulmonary TB", "P"}
		},
		// GXP_RESULT
		{ 
			{"Intermediate", "INTER"}, 
			{"RIF Resistive", "RESISTIVE"}, 
			{"RIF Sensitive", "SENSITIVE"}
		}, 
		// LOCATION_TYPE
		{ 
			{"Clinic", "CLINIC"}, 
			{"Hospital", "HOSPITAL"}, 
			{"Houser", "HOUSE"}, 
			{"Laboratory", "LAB"}
		}, 
		// MARITAL_STATUS
		{   
			{"Married", "M"},
			{"Single", "S"}, 
			{"Widow{er}", "W"},
            {"Divorced", "D"}, 
		}, 
		// PATIENT_TYPE
		{ 
			{"Retreatment After Default", "A_DEFAULT"}, 
			{"Retreatment After Fail", "A_FAIL"}, 
			{"New", "NEW"}, 
			{"Relapse", "RELAPSE"}, 
			{"Transferred In", "TRANSFER"}, 
			{"Other", "OTHER"}
		}, 
		// REGIMEN
		{ 
			{"HE", "HE"}, 
			{"RHE", "RHE"}, 
			{"RHZE", "RHZE"}, 
			{"RHZES", "RHZES"}
		}, 
		// REGIMEN_TYPE
		{ 
			{"Adult", "ADULT"}, 
			{"Paediatric", "PAED"}
		}, 
		// SMEAR_RESULT
		{
			{"1+", "1+"},
			{"2+", "2+"},
			{"3+", "3+"},
			{"1-9AFB", "1-9AFB"},
			{"Negative", "NEGATIVE"},
			{"Other", "OTHER"}
		}, 
		// TREATMENT_OUTCOME
		{
			{"Cured", "CURED"}, 
			{"Patient Died", "DIED"}, 
			{"Treatment Complete", "TX_COMP"}, 
			{"Treatment Failed", "TX_FAIL"}
		}, 
		// TREATMENT_PHASE
		{ 
			{"Intensive", "INTENSIVE"}, 
			{"Continuation", "CONT"}
		}, 
		// USER_ROLE
		{ 
			{"Administrator", "ADMIN"},
			{"CHW", "CHW"}
		}, 
		// LABORATORY
		{
			{"Indus Hospital", "INDUS"},
			{"Kenya Laboratories", "K_LABS"}
		}, 
		// DISTRICT
		{	
			{"Babati Town", "BT"},
			{"Babati District", "BD"},
			{"Hanang", "HAN"},
			{"Mbulu", "MB"},
			{"Simanjiro", "SIM"},
			{"Kiteto", "KIT"},
			{"Geita", "GT"},
			{"Tanga", "TANG"}
		}, 
		// REGION
		{
			{"Manyara", "MAN"},
			{"Geita", "GT"},
			{"Tanga", "TANG"}
		},
		// FACITLITY
		{
			
			{"Ziwa Health Center", "ZIWA-UG"}
		}, 
		// MONTHS
		{
			{"January", "1"},
			{"February", "2"},
			{"March", "3"},
			{"April", "4"},
			{"May", "5"},
			{"June", "6"},
			{"July", "7"},
			{"August", "8"},
			{"September", "9"},
			{"October", "10"},
			{"November", "11"},
			{"December", "12"}
		}, 
		// FEEDBACK_TYPE
		{
			{"Error/Bug", "ERROR"},
			{"Change Request", "CHANGE_REQ"},
			{"Suggestion", "SUGGESTION"}
		}, 
		// AMPATH_SITES
		{
			
			{"Ziwa", "ZIWA"}
		},
		// COMMUNITY_APPROACH
		{
			{"Contact tracing", "CTR"}, 
			{"Screening among pastoralist", "PAS"}, 
			{"Screening among Mining community", "MIN"},	
			{"Other", "OTHER"}
		},
		
		// TEMPORARAY
		{
			{"Temp 1", "TMP1"}, 
			{"Temp 2", "TMP2"}, 
			{"Temp 3", "TMP3"},	
			{"Other", "OTHER"}
		}	
	};
	
	public static void setMetaValues (int metaType, String[][] values)
	{
		metadata[metaType] = new String[values.length][2];
		for (int i = 0; i < values.length; i++)
		{
			metadata[metaType][i][0] = values[i][0];
			metadata[metaType][i][1] = values[i][1];
		}
	}

	public static String[] getMetaValues (int metaType)
	{
		String[][] metadata = getMetadata (metaType);
		String[] values = new String[metadata.length];
		for (int i = 0; i < metadata.length; i++)
			values[i] = metadata[i][0];
		return values;
	}

	public static String[][] getMetadata (int metaType)
	{
		return metadata[metaType];
	}

	public static String getKey (int metaType, String value)
	{
		String[][] data = metadata[metaType];
		for (int i = 0; i < data.length; i++)
			if (data[i][0].equals (value))
				return data[i][1];
		return null;
	}

	public static String getValue (int metaType, String key)
	{
		String[][] data = metadata[metaType];
		for (int i = 0; i < data.length; i++)
			if (data[i][0].equals (key))
				return data[i][0];
		return null;
	}
}
