/* Copyright(C) 2015 Interactive Health Solutions, Pvt. Ltd.

This program is free software; you can redistribute it and/or modify it under the terms of the GNU General Public License as
published by the Free Software Foundation; either version 3 of the License (GPLv3), or any later version.
This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.

See the GNU General Public License for more details. You should have received a copy of the GNU General Public License along with this program; if not, write to the Interactive Health Solutions, info@ihsinformatics.com
You can also access the license on the internet at the address: http://www.gnu.org/licenses/gpl-3.0.html

Interactive Health Solutions, hereby disclaims all copyright interest in this program written by the contributors. */

package com.ihsinformatics.tbreach3tanzania.mobile.shared;

public class MenuItem
{
	public static final int	MENU_REPORT_LIST		= 0;
	public static final int MENU_REGISTRATION		= 1;
	public static final int	MENU_SCREENING			= 2;
	public static final int	MENU_SMEAR_RESULTS		= 3;
	public static final int MENU_DIAGNOSIS		    = 4;
	public static final int	MENU_TREATMENT			= 5;
	public static final int	MENU_TREATMENT_REVIEW	= 6;
	public static final int	MENU_SITES_DATA			= 7;
	public static final int	MENU_LAB_REVIEW			= 8;
	public static final int	MENU_COMPARISON_REVIEW	= 9;
	public static final int	MENU_FEEDBACK			= 10;
	public static final int	MENU_UNDO				= 11;

	public static int getTotalItems ()
	{
		return 12;
	}
}
