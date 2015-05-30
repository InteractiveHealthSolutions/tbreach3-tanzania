
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
