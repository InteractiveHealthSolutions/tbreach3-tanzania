
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
