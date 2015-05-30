/* Copyright(C) 2015 Interactive Health Solutions, Pvt. Ltd.

This program is free software; you can redistribute it and/or modify it under the terms of the GNU General Public License as
published by the Free Software Foundation; either version 3 of the License (GPLv3), or any later version.
This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.

See the GNU General Public License for more details. You should have received a copy of the GNU General Public License along with this program; if not, write to the Interactive Health Solutions, info@ihsinformatics.com
You can also access the license on the internet at the address: http://www.gnu.org/licenses/gpl-3.0.html

Interactive Health Solutions, hereby disclaims all copyright interest in this program written by the contributors. */

package com.ihsinformatics.tbreach3tanzania.mobile.util;

import java.io.OutputStream;
import java.io.PrintStream;
import javax.microedition.io.Connector;
import javax.microedition.io.file.FileConnection;
import com.ihsinformatics.tbreach3tanzania.mobile.TBReach3TanzaniaMain;

public class FileWriter
{

	public static void rite (String msg, TBReach3TanzaniaMain tbrMidlet)
	{
		try
		{
			FileConnection filecon = (FileConnection) Connector.open ("file:///SDCard/mynewfile.txt");
			// Always check whether the file or directory exists.
			// Create the file if it doesn't exist.
			if (!filecon.exists ())
			{
				filecon.create ();
			}

			OutputStream out = filecon.openOutputStream ();
			PrintStream ps = new PrintStream (out);
			ps.println (msg);
			ps.flush ();

			out.close ();

		}

		catch (Exception e)
		{
			System.out.println (e.toString ());
		}
	}
}
