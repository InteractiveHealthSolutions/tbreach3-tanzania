/* Copyright(C) 2015 Interactive Health Solutions, Pvt. Ltd.

This program is free software; you can redistribute it and/or modify it under the terms of the GNU General Public License as
published by the Free Software Foundation; either version 3 of the License (GPLv3), or any later version.
This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.

See the GNU General Public License for more details. You should have received a copy of the GNU General Public License along with this program; if not, write to the Interactive Health Solutions, info@ihsinformatics.com
You can also access the license on the internet at the address: http://www.gnu.org/licenses/gpl-3.0.html

Interactive Health Solutions, hereby disclaims all copyright interest in this program written by the contributors. */

package com.ihsinformatics.tbreach3tanzania.server;

import java.util.Date;
import com.ihsinformatics.tbreach3tanzania.server.sms.SmsTarseel;

public class TBRTMain
{
	public static void main (String[] args)
	{
		try
		{
			SmsTarseel.Instantiate ();
			String regex = "[1-3]\\+|NEGATIVE|1-9AFB";
			String[] test = {"NEGATIVE", "1+", "2+", "3+", "4+", "4+4+", "4-", "1-9AFB", "1", "", "negative", "asdfvc"};
			for (String s : test)
			{
				if (s.matches (regex))
					System.out.println (s + " passed.");
			}
		}
		catch (Exception e)
		{
			e.printStackTrace ();
		}
	}

	@SuppressWarnings("deprecation")
	public static Date parseDate (String str)
	{
		try
		{
			String[] parts = str.split (" ");
			String[] dateParts = parts[0].split ("/");
			int date, month, year, hour = 0, min = 0;
			date = Integer.parseInt (dateParts[0]);
			month = Integer.parseInt (dateParts[1]);
			year = Integer.parseInt (dateParts[2]);

			try
			{
				String[] timeParts = parts[1].split (":");
				hour = Integer.parseInt (timeParts[0]);
				min = Integer.parseInt (timeParts[1]);
			}
			catch (Exception e)
			{
				e.printStackTrace ();
			}

			Date dt = new Date (year - 1900, month - 1, date, hour, min, 0);
			return dt;
		}
		catch (Exception e)
		{
			return null;
		}
	}

	public static int findIndex (String[] array, String str)
	{
		for (int i = 0; i < array.length; i++)
			if (array[i].equalsIgnoreCase (str))
				return i;
		return -1;
	}
}
