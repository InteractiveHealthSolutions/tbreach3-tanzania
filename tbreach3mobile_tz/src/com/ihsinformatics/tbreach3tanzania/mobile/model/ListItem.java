/* Copyright(C) 2015 Interactive Health Solutions, Pvt. Ltd.

This program is free software; you can redistribute it and/or modify it under the terms of the GNU General Public License as
published by the Free Software Foundation; either version 3 of the License (GPLv3), or any later version.
This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.

See the GNU General Public License for more details. You should have received a copy of the GNU General Public License along with this program; if not, write to the Interactive Health Solutions, info@ihsinformatics.com
You can also access the license on the internet at the address: http://www.gnu.org/licenses/gpl-3.0.html

Interactive Health Solutions, hereby disclaims all copyright interest in this program written by the contributors. */

package com.ihsinformatics.tbreach3tanzania.mobile.model;

public class ListItem
{
	private int		id			= -1;
	private String	displayName	= null;
	boolean			show		= true;

	public ListItem (int id, String displayName, boolean show)
	{
		this.id = id;
		this.displayName = displayName;
		this.show = show;
	}


	public int getId ()
	{
		return id;
	}

	public void setId (int id)
	{
		this.id = id;
	}

	public String getDisplayName ()
	{
		return displayName;
	}

	public void setDisplayName (String displayName)
	{
		this.displayName = displayName;
	}

	public boolean isShow ()
	{
		return show;
	}

	public void setShow (boolean show)
	{
		this.show = show;
	}

}
