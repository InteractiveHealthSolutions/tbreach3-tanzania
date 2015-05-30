/* Copyright(C) 2015 Interactive Health Solutions, Pvt. Ltd.

This program is free software; you can redistribute it and/or modify it under the terms of the GNU General Public License as
published by the Free Software Foundation; either version 3 of the License (GPLv3), or any later version.
This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.

See the GNU General Public License for more details. You should have received a copy of the GNU General Public License along with this program; if not, write to the Interactive Health Solutions, info@ihsinformatics.com
You can also access the license on the internet at the address: http://www.gnu.org/licenses/gpl-3.0.html

Interactive Health Solutions, hereby disclaims all copyright interest in this program written by the contributors. */

package com.ihsinformatics.tbreach3tanzania.mobile.model;

import com.ihsinformatics.tbreach3tanzania.mobile.shared.FormType;
import com.ihsinformatics.tbreach3tanzania.mobile.shared.MenuItem;
import com.ihsinformatics.tbreach3tanzania.mobile.shared.TBRT;

public class Settings
{
	private ListItem	listItems[]	= null;

	public Settings ()
	{
		listItems = new ListItem[MenuItem.getTotalItems ()];
		listItems[MenuItem.MENU_REPORT_LIST] = new ListItem (MenuItem.MENU_REPORT_LIST, "Reports Menu", false);
		listItems[MenuItem.MENU_REGISTRATION] = new ListItem (MenuItem.MENU_REGISTRATION, "Registration Form", false);
		listItems[MenuItem.MENU_SCREENING] = new ListItem (MenuItem.MENU_SCREENING, "Initial Screening Form", false);
		listItems[MenuItem.MENU_SMEAR_RESULTS] = new ListItem (MenuItem.MENU_SMEAR_RESULTS, "Smear Results Form", false);
		listItems[MenuItem.MENU_DIAGNOSIS] = new ListItem (MenuItem.MENU_DIAGNOSIS, "Diagnosis Form", false);
		listItems[MenuItem.MENU_TREATMENT] = new ListItem (MenuItem.MENU_TREATMENT, "Treatment Outcome Form", false);
		listItems[MenuItem.MENU_TREATMENT_REVIEW] = new ListItem (MenuItem.MENU_TREATMENT_REVIEW, "Treatment Review Form", false);
		listItems[MenuItem.MENU_SITES_DATA] = new ListItem (MenuItem.MENU_SITES_DATA, "AMPATH Sites Data", false);
		listItems[MenuItem.MENU_LAB_REVIEW] = new ListItem (MenuItem.MENU_LAB_REVIEW, "Lab Review Form", false);
		listItems[MenuItem.MENU_COMPARISON_REVIEW] = new ListItem (MenuItem.MENU_TREATMENT, "Comparison Review Form", false);
		listItems[MenuItem.MENU_FEEDBACK] = new ListItem (MenuItem.MENU_FEEDBACK, "Feedback Form", false);
		listItems[MenuItem.MENU_UNDO] = new ListItem (MenuItem.MENU_UNDO, "Undo Form", false);
	}

	private void loadSettings (String[] formTypes)
	{
		for (int i = 0; i < formTypes.length; i++)
		{
			if (formTypes[i].equals (FormType.REGISTRATION))
				listItems[MenuItem.MENU_REGISTRATION].setShow (true);
			if (formTypes[i].equals (FormType.SCREENING))
				listItems[MenuItem.MENU_SCREENING].setShow (true);
			if (formTypes[i].equals (FormType.DIAGNOSIS))
				listItems[MenuItem.MENU_DIAGNOSIS].setShow (true);
			if (formTypes[i].equals (FormType.TREATMENT))
				listItems[MenuItem.MENU_TREATMENT].setShow (true);
		}
    	listItems[MenuItem.MENU_REPORT_LIST].setShow (true);
	}

	public void reloadItems ()
	{
		String currentRole = TBRT.getCurrentUserRole ();
		String[] all = new String[] {FormType.SCREENING, FormType.REGISTRATION, FormType.DIAGNOSIS, FormType.TREATMENT};
		if (currentRole.equals ("ADMIN"))
			loadSettings (all);
		/*else if (currentRole.equals ("CM"))
			loadSettings (new String[] {FormType.SCREENING});*/
		else
			loadSettings (all);
	}

	public int getItemIndex (String displayName)
	{
		int index = -1;
		for (int i = 0; i < listItems.length; i++)
		{
			if (listItems[i].getDisplayName ().equals (displayName))
			{
				index = i;
				break;
			}
		}
		return index;
	}

	public ListItem[] getListItems ()
	{
		return listItems;
	}

	public void setListItems (ListItem[] listItems)
	{
		this.listItems = listItems;
	}

	public ListItem getListItem (int index)
	{
		return this.listItems[index];
	}

	public void setListItem (int index, ListItem item)
	{
		this.listItems[index] = item;
	}
}
