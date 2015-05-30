/* Copyright(C) 2015 Interactive Health Solutions, Pvt. Ltd.

This program is free software; you can redistribute it and/or modify it under the terms of the GNU General Public License as
published by the Free Software Foundation; either version 3 of the License (GPLv3), or any later version.
This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.

See the GNU General Public License for more details. You should have received a copy of the GNU General Public License along with this program; if not, write to the Interactive Health Solutions, info@ihsinformatics.com
You can also access the license on the internet at the address: http://www.gnu.org/licenses/gpl-3.0.html

Interactive Health Solutions, hereby disclaims all copyright interest in this program written by the contributors. */
/**
 * Enumeration to represent information type
 */

package com.ihsinformatics.tbreach3tanzania.mobile.shared;

/**
 * @author owais.hussain@irdresearch.org
 * 
 */
public class InfoProvider
{
	public static final int	ACCESS_GRANTED			= 0;
	public static final int	CONFIRM_CLOSE			= 1;
	public static final int	CONFIRM_OPERATION		= 2;
	public static final int	CONNECTION_SUCCESSFUL	= 3;
	public static final int	DELETED					= 4;
	public static final int	INSERTED				= 5;
	public static final int	OPERATION_SUCCESSFUL	= 6;
	public static final int	SESSION_RENEWED			= 7;
	public static final int	UPDATED					= 8;
	public static final int	VALID					= 9;

	public static String getInfo (int infoType)
	{
		String message = "";
		switch (infoType)
		{
			case InfoProvider.ACCESS_GRANTED :
				message = "Access granted, you have successfully logged in.";
				break;
			case InfoProvider.CONFIRM_CLOSE :
				message = "Are you sure you want to close the application?";
				break;
			case InfoProvider.CONFIRM_OPERATION :
				message = "This operation is irreversable. Are you sure you want to proceed?";
				break;
			case InfoProvider.CONNECTION_SUCCESSFUL :
				message = "Connection with the server was successful.";
				break;
			case InfoProvider.DELETED :
				message = "Data deleted successfully.";
				break;
			case InfoProvider.INSERTED :
				message = "Data inserted successfully.";
				break;
			case InfoProvider.OPERATION_SUCCESSFUL :
				message = "Request has been processed by the server successfully.";
				break;
			case InfoProvider.SESSION_RENEWED :
				message = "Session has been renewed.";
				break;
			case InfoProvider.UPDATED :
				message = "Data updated successfully.";
				break;
			case InfoProvider.VALID :
				message = "Data validated.";
				break;
		}
		return message;
	}
}
