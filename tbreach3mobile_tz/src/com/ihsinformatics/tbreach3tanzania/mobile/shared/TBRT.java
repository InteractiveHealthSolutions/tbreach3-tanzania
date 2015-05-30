/* Copyright(C) 2015 Interactive Health Solutions, Pvt. Ltd.

This program is free software; you can redistribute it and/or modify it under the terms of the GNU General Public License as
published by the Free Software Foundation; either version 3 of the License (GPLv3), or any later version.
This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.

See the GNU General Public License for more details. You should have received a copy of the GNU General Public License along with this program; if not, write to the Interactive Health Solutions, info@ihsinformatics.com
You can also access the license on the internet at the address: http://www.gnu.org/licenses/gpl-3.0.html

Interactive Health Solutions, hereby disclaims all copyright interest in this program written by the contributors. */

package com.ihsinformatics.tbreach3tanzania.mobile.shared;

public class TBRT
{
	/* Version must be same as of web app */
	public static final String	VERSION						= "1.0";
	public static final String	BASE_URL					= "http://41.188.174.250:8080/tbreach3tanzania/mobileservice";
	//public static final String	BASE_URL 				= "http://localhost:8888/mobileservice";
	public static final String	DEFAULT_SCAN_DELAY			= "2000";
	public static final String	DEFAULT_CONNECTION_TIMEOUT	= "360000";
	public static final String	DEFAULT_PHONE_NUMBER		= String.valueOf (99999999999l);

	public static final String	XML_SUCCESS					= "success";
	public static final String	XML_ERROR					= "error";
	public static final String	XML_RESULT					= "result";

	public static final int		ID_LENGTH					= 11;
	public static final int		KEY_LENGTH					= 10;
	public static final int		VALUE_LENGTH				= 50;
	public static final int		OPENTEXT_LENGTH				= 255;

	private static String		currentUserName				= "";
	private static String		currentUserRole				= "";
	
	private static String		lastFormSubmitted			= "";
	private static long			lastOperationTimestamp		= 0;

	public static String getCurrentUserName ()
	{
		return currentUserName;
	}

	public static void setCurrentUserName (String currentUserName)
	{
		TBRT.currentUserName = currentUserName;
	}

	public static String getCurrentUserRole ()
	{
		return currentUserRole;
	}

	public static void setCurrentUserRole (String currentUserRole)
	{
		TBRT.currentUserRole = currentUserRole;
	}

	public static String getLastFormSubmitted ()
	{
		return lastFormSubmitted;
	}

	public static void setLastFormSubmitted (String lastFormSubmitted)
	{
		TBRT.lastFormSubmitted = lastFormSubmitted;
	}
	
	public static long getLastOperationTimestamp ()
	{
		return lastOperationTimestamp;
	}

	public static void setLastOperationTimestamp (long lastOperationTimestamp)
	{
		TBRT.lastOperationTimestamp = lastOperationTimestamp;
	}
}
