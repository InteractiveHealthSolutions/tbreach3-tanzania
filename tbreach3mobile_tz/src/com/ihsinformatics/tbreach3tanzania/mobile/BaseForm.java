/* Copyright(C) 2015 Interactive Health Solutions, Pvt. Ltd.

This program is free software; you can redistribute it and/or modify it under the terms of the GNU General Public License as
published by the Free Software Foundation; either version 3 of the License (GPLv3), or any later version.
This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.

See the GNU General Public License for more details. You should have received a copy of the GNU General Public License along with this program; if not, write to the Interactive Health Solutions, info@ihsinformatics.com
You can also access the license on the internet at the address: http://www.gnu.org/licenses/gpl-3.0.html

Interactive Health Solutions, hereby disclaims all copyright interest in this program written by the contributors. */

package com.ihsinformatics.tbreach3tanzania.mobile;

import java.util.Date;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.Item;
import javax.microedition.lcdui.StringItem;

public class BaseForm extends Form
{
	protected TBReach3TanzaniaMain	tbreach3tanzaniaMidlet;
	protected Displayable		prevDisplayable;
	protected Date				startTimestamp;
	protected Item[]			formItems;
	

	public Displayable getPrevDisplayable ()
	{
		return prevDisplayable;
		
	}

	public void setPrevDisplayable (Displayable prvDisplayable)
	{
		this.prevDisplayable = prvDisplayable;
	}

	public BaseForm (String title, TBReach3TanzaniaMain tbreach3tanzaniaMidlet)
	{
		super (title);
		this.tbreach3tanzaniaMidlet = tbreach3tanzaniaMidlet;
	}

	public void init ()
	{

	}

	/**
	 * Returns the index of an item in the formItems array. Returns -1 if item
	 * is not found
	 * 
	 * @param item
	 * @return
	 */
	protected int indexOf (Item item)
	{
		for (int i = 0; i < formItems.length; i++)
			if (formItems[i] == item)
				return i;
		return -1;
	}

	/**
	 * Shows an item on the form. If another item is already existing on the
	 * Item's index, it is replaced
	 * 
	 * @param item
	 */
	protected void show (Item item)
	{
		int i = indexOf (item);
		delete (i);
		insert (i, item);
	}

	/**
	 * Hides an item on the form by replacing it with empty String Item
	 * 
	 * @param item
	 */
	
	
	protected void hide (Item item)
	{
		int i = indexOf (item);
		delete (i);
		StringItem tmp = new StringItem ("", "");
		insert (i, tmp);
	}
	
	/**
	 * Creates query string for HTTP request 
	 * 
	 * @return request
	 */
	protected String createRequestPayload ()
	{
		return "";
	}
}
