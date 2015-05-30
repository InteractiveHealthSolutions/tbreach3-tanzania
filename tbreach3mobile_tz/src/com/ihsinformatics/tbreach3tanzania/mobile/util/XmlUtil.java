/* Copyright(C) 2015 Interactive Health Solutions, Pvt. Ltd.

This program is free software; you can redistribute it and/or modify it under the terms of the GNU General Public License as
published by the Free Software Foundation; either version 3 of the License (GPLv3), or any later version.
This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.

See the GNU General Public License for more details. You should have received a copy of the GNU General Public License along with this program; if not, write to the Interactive Health Solutions, info@ihsinformatics.com
You can also access the license on the internet at the address: http://www.gnu.org/licenses/gpl-3.0.html

Interactive Health Solutions, hereby disclaims all copyright interest in this program written by the contributors. */

package com.ihsinformatics.tbreach3tanzania.mobile.util;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Hashtable;
import org.kxml2.io.KXmlParser;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class XmlUtil
{

	public static String createTag (String tagName, String tagData)
	{
		return createStartTag (tagName) + tagData + createEndTag (tagName);
	}

	public static String createStartTag (String tagName)
	{
		return "<" + tagName + ">";
	}

	public static String createEndTag (String tagName)
	{
		return "</" + tagName + ">";
	}

	public static Hashtable parseXmlResponse (InputStreamReader isr) throws IOException, XmlPullParserException
	{
		KXmlParser parser = new KXmlParser ();
		Hashtable model = null;
		try
		{
			parser.setInput (isr);
			// if its text, does'nt have a name, but a value
			parser.nextTag ();
			model = new Hashtable ();
			model = getNodeValuePairs (parser, model, "tbrresponse");
		}
		finally
		{
			System.out.println ("closing reader");
			isr.close ();
		}

		return model;
	}

	private static Hashtable getNodeValuePairs (KXmlParser parser, Hashtable model, String elementTag) throws XmlPullParserException, IOException
	{
		// Parse our XML file
		String name = "";
		String text = "";
		while (parser.nextTag () != XmlPullParser.END_TAG)
		{
			name = "";
			text = "";
			parser.require (XmlPullParser.START_TAG, null, null);
			name = parser.getName ();
			text = parser.nextText ();
			System.out.println (name + ":" + text);
			model.put (name, text);
			parser.require (XmlPullParser.END_TAG, null, null);
		}
		return model;
	}

}
