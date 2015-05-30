
package com.ihsinformatics.tbreach3tanzania.mobile.util;

import java.util.Vector;
import javax.microedition.lcdui.ChoiceGroup;
import javax.microedition.lcdui.Item;
import javax.microedition.lcdui.StringItem;
import javax.microedition.lcdui.TextField;
import com.ihsinformatics.tbreach3tanzania.mobile.shared.Metadata;

public class StringUtil
{
	public static String replaceAll (String oldStr, String newStr, String inString)
	{
		while (inString.indexOf (oldStr) != -1)
			inString = replaceFirst (oldStr, newStr, inString);
		return inString;
	}

	public static String replaceFirst (String oldStr, String newStr, String inString)
	{
		int start = inString.indexOf (oldStr);
		if (start == -1)
		{
			return inString;
		}
		StringBuffer sb = new StringBuffer ();
		sb.append (inString.substring (0, start));
		sb.append (newStr);
		sb.append (inString.substring (start + oldStr.length ()));
		return sb.toString ();
	}

	/**
	 * Returns the current string value in an item field
	 * 
	 * @param item
	 * @return
	 */
	public static String getString (Item item)
	{
		if (item instanceof StringItem)
			return ((StringItem) item).getText ();
		if (item instanceof TextField)
			return ((TextField) item).getString ();
		if (item instanceof ChoiceGroup)
			return ((ChoiceGroup) item).getString (((ChoiceGroup) item).getSelectedIndex ());
		return null;
	}

	/**
	 * Returns the selected string values in an choicegroup separated by ':'
	 * 
	 * @param item
	 * @return
	 */
	public static String getStringMultipleSelection (ChoiceGroup choice)
	{
		boolean[] selectedIndices = new boolean[choice.size ()];

		choice.getSelectedFlags (selectedIndices);
		String string = "";
		for (int i = 0; i < choice.size (); i++)
		{
			if (selectedIndices[i])
			{
				string += choice.getString (i) + ":";
			}
		}
		return string;
	}
	
	
	public static String getStringMultipleSelectionCA (ChoiceGroup choice)
	{
		boolean[] selectedIndices = new boolean[choice.size ()];

		choice.getSelectedFlags (selectedIndices);
		String string = "";
		for (int i = 0; i < choice.size (); i++)
		{
			if (selectedIndices[i])
			{
				String temp =  Metadata.getKey (Metadata.COMMUNITY_APPROACH, choice.getString (i));
				string +=  temp + ",";
			}
		}
		if (string.equals (""))
			return "";
		string = string.substring (0, string.length ()-1);
		return string;
	}

	public static boolean isStringSelected (ChoiceGroup choice, String string, boolean wrapString)
	{
		boolean[] selectedIndices = new boolean[choice.size ()];

		choice.getSelectedFlags (selectedIndices);

		for (int i = 0; i < choice.size (); i++)
		{
			if (selectedIndices[i])
			{
				if (wrapString)
				{
					if (choice.getString (i).toLowerCase ().indexOf (string.toLowerCase ()) != -1)
					{
						return true;
					}
				}
				else
				{
					if (choice.getString (i).toLowerCase ().equals (string.toLowerCase ()))
					{
						return true;
					}
				}
			}
		}

		return false;
	}
	
	public static String[] split (String string, char filter)
	{
		
		if (string == null)
			return null;
		if (string.equals (""))
			return new String[0];
		Vector vector = new Vector ();
		int pointer = 0;
		for (int i = 0; i < string.length (); i++)
			if (string.charAt (i) == filter)
			{
				vector.addElement (string.substring (pointer, i));
				pointer = i + 1;
			}
		vector.addElement (string.substring (pointer));
		String[] array = new String[vector.size ()];
		for (int i = 0; i < array.length; i++)
		{
			array[i] = vector.elementAt (i).toString ();
		}
		return array;
	}
	
	public static boolean isNumeric (String string)
	{
	
		for(int i = 0; i<string.length (); i++){
			if(!(string.charAt (i) >= '0' && string.charAt (i) <= '9'))
				return false;		
		}
		return true;
	}
	
	public static boolean isAlpha (String string)
	{
	
		for(int i = 0; i<string.length (); i++){
			if(!((string.charAt (i) >= 'A' && string.charAt (i) <= 'Z')||(string.charAt (i) >= 'a' && string.charAt (i) <= 'z')||string.charAt (i)==' '))
				return false;		
		}
		return true;
	}
}