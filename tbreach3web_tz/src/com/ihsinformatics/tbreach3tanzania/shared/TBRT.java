/* Copyright(C) 2015 Interactive Health Solutions, Pvt. Ltd.

This program is free software; you can redistribute it and/or modify it under the terms of the GNU General Public License as
published by the Free Software Foundation; either version 3 of the License (GPLv3), or any later version.
This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.

See the GNU General Public License for more details. You should have received a copy of the GNU General Public License along with this program; if not, write to the Interactive Health Solutions, info@ihsinformatics.com
You can also access the license on the internet at the address: http://www.gnu.org/licenses/gpl-3.0.html

Interactive Health Solutions, hereby disclaims all copyright interest in this program written by the contributors. */
/**
 * This class contains constants and project-specific methods which will be used throughout the System
 */

package com.ihsinformatics.tbreach3tanzania.shared;

import java.util.ArrayList;

import com.ihsinformatics.tbreach3tanzania.shared.model.Defaults;
import com.ihsinformatics.tbreach3tanzania.shared.model.Definition;

/**
 * @author owais.hussain@irdresearch.org
 * 
 */
public final class TBRT
{
	private static String		resourcesPath;
	private static String		hashingAlgorithm;
	private static String		projectTitle			= "TB REACH TANZANIA";
	private static String		databaseName			= "tbr3tanzania";
	private static String		reportingDatabase		= "tbr3tanzania_rpt";
	private static String		reportsDirectoryName	= "rpt";
	private static int			sessionLimit			= 900000;

	private static String		currentVersion			= "";
	private static String		currentUserName			= "";
	private static String		currentRole				= "";
	private static String		passCode				= "";
	private static String[][]	schema;
	private static Definition[]	definitions;
	private static Defaults[]	defaults;
	
	
	
	public static String getHashingAlgorithm ()
	{
		return hashingAlgorithm;
	}

	public static void setHashingAlgorithm (String hashingAlgorithm)
	{
		TBRT.hashingAlgorithm = hashingAlgorithm;
	}

	public static String getProjectTitle ()
	{
		return projectTitle;
	}

	public static void setProjectTitle (String projectTitle)
	{
		TBRT.projectTitle = projectTitle;
	}

	public static String getDatabaseName ()
	{
		return databaseName;
	}

	public static void setDatabaseName (String databaseName)
	{
		TBRT.databaseName = databaseName;
	}

	public static String getReportingDatabase ()
	{
		return reportingDatabase;
	}

	public static void setReportingDatabase (String reportingDatabase)
	{
		TBRT.reportingDatabase = reportingDatabase;
	}

	public static String getReportsDirectoryName ()
	{
		return reportsDirectoryName;
	}

	public static void setReportsDirectoryName (String reportsDirectoryName)
	{
		TBRT.reportsDirectoryName = reportsDirectoryName;
	}

	public static int getSessionLimit ()
	{
		return sessionLimit;
	}

	public static void setSessionLimit (int sessionLimit)
	{
		TBRT.sessionLimit = sessionLimit;
	}

	public static String[][] getSchema ()
	{
		return schema;
	}

	public static void setSchema (String[][] schema)
	{
		TBRT.schema = schema;
	}

	public static Definition[] getDefinitions ()
	{
		return definitions;
	}

	public static void setDefinitions (Definition[] definitions)
	{
		TBRT.definitions = definitions;
	}

	public static Defaults[] getDefaults ()
	{
		return defaults;
	}

	public static void setDefaults (Defaults[] defaults)
	{
		TBRT.defaults = defaults;
	}

	public static void setResourcesPath (String resourcesPath)
	{
		TBRT.resourcesPath = resourcesPath;
	}

	public static void fillSchema (String[][] schema)
	{
		TBRT.schema = schema;
	}

	public static void fillDefinitions (Definition[] definitions)
	{
		TBRT.definitions = definitions;
	}

	public static void fillDefaults (Defaults[] defaults)
	{
		TBRT.defaults = defaults;
	}

	/**
	 * Get maximum length of a column in a table
	 * 
	 * @param tablename
	 * @param columnName
	 * @return
	 */
	public static int getMaxLength (String tablename, String columnName)
	{
		try
		{
			for (int i = 0; i < schema.length; i++)
			{
				if (schema[i][0].equals (tablename) && schema[i][1].equals (columnName))
					return Integer.parseInt (schema[i][4]);
			}
		}
		catch (NumberFormatException e)
		{
			e.printStackTrace ();
			return 0;
		}
		return 255;
	}

	/**
	 * Concatenate an Array of Strings into single String
	 * 
	 * @param array
	 * @return string
	 */
	public static String concatenateArray (String[] array)
	{
		StringBuilder concatenated = new StringBuilder ();
		for (String s : array)
			concatenated.append (s + ",");
		// Remove additional comma
		concatenated.deleteCharAt (concatenated.length () - 1);
		return concatenated.toString ();
	}

	/**
	 * Get a list of unique definition types
	 * 
	 * @return array
	 */
	public static String[] getDefinitionTypes ()
	{
		ArrayList<String> types = new ArrayList<String> ();
		for (Definition d : definitions)
		{
			boolean exists = false;
			for (int i = 0; i < types.size (); i++)
				if (types.get (i).equals (d.getId ().getDefinitionType ()))
					exists = true;
			if (!exists)
				types.add (d.getId ().getDefinitionType ());
		}
		return types.toArray (new String[] {});
	}

	/**
	 * Get a list of constant values
	 * 
	 * @param definitionType
	 * @return array
	 */
	public static String[] getDefinitionValues (String definitionType)
	{
		ArrayList<String> list = new ArrayList<String> ();
		for (Definition d : definitions)
		{
			if (d.getId ().getDefinitionType ().equals (definitionType))
				list.add (d.getDefinitionValue ());
		}
		if (list.size () == 0)
			list.add ("NO " + definitionType);
		return list.toArray (new String[] {});
	}

	/**
	 * Get definition key for a definition type and fully-qualified value
	 * 
	 * @param definitionType
	 * @param definitionValue
	 * @return
	 */
	public static String getDefinitionKey (String definitionType, String definitionValue)
	{
		for (Definition d : definitions)
		{
			if (d.getId ().getDefinitionType ().equals (definitionType) && d.getDefinitionValue ().equals (definitionValue))
				return d.getId ().getDefinitionKey ();
		}
		return "";
	}

	/**
	 * Get definition value for a definition type and key
	 * 
	 * @param definitionType
	 * @param definitionKey
	 * @return
	 */
	public static String getDefinitionValue (String definitionType, String definitionKey)
	{
		for (Definition d : definitions)
		{
			if (d.getId ().getDefinitionType ().equals (definitionType) && d.getId ().getDefinitionKey ().equals (definitionKey))
				return d.getDefinitionValue ();
		}
		return null;
	}

	/**
	 * Get default value for a definition type
	 * 
	 * @param definitionType
	 * @return
	 */
	public static String getDefaultValue (String definitionType)
	{
		for (Defaults d : defaults)
		{
			if (d.getId ().getDefinitionType ().equals (definitionType))
				return d.getId ().getDefaultDefinitionKey ();
		}
		return "";
	}

	public static String getCurrentVersion ()
	{
		return currentVersion;
	}

	public static void setCurrentVersion (String version)
	{
		TBRT.currentVersion = version;
	}

	/**
	 * Get current User Name (saved in cookies on client-side)
	 * 
	 * @return currentUser
	 */
	public static String getCurrentUserName ()
	{
		return currentUserName;
	}

	/**
	 * Set current user
	 * 
	 * @param userName
	 */
	public static void setCurrentUserName (String userName)
	{
		TBRT.currentUserName = userName;
	}

	public static String getCurrentRole ()
	{
		return currentRole;
	}

	public static void setCurrentRole (String userRole)
	{
		TBRT.currentRole = userRole;
	}

	/**
	 * Get pass code (first 4 characters of User's password)
	 * 
	 * @return passCode
	 */
	public static String getPassCode ()
	{
		return passCode;
	}

	/**
	 * Set pass code for current user
	 * 
	 * @param passcode
	 */
	public static void setPassCode (String passcode)
	{
		TBRT.passCode = passcode;
	}

	/**
	 * @return the reportPath
	 */
	public static String getReportPath ()
	{
		String path = getResourcesPath ();
		System.out.println(path);
		char separatorChar = (path.charAt (path.length () - 1));
		return getResourcesPath () + reportsDirectoryName + separatorChar;
	}

	/**
	 * @return the resourcesPath
	 */
	public static String getResourcesPath ()
	{
		return resourcesPath;
	}
}
