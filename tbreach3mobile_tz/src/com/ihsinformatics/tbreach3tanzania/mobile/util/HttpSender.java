/* Copyright(C) 2015 Interactive Health Solutions, Pvt. Ltd.

This program is free software; you can redistribute it and/or modify it under the terms of the GNU General Public License as
published by the Free Software Foundation; either version 3 of the License (GPLv3), or any later version.
This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.

See the GNU General Public License for more details. You should have received a copy of the GNU General Public License along with this program; if not, write to the Interactive Health Solutions, info@ihsinformatics.com
You can also access the license on the internet at the address: http://www.gnu.org/licenses/gpl-3.0.html

Interactive Health Solutions, hereby disclaims all copyright interest in this program written by the contributors. */

package com.ihsinformatics.tbreach3tanzania.mobile.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Hashtable;
import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;
import com.ihsinformatics.tbreach3tanzania.mobile.model.MessageEntry;

public class HttpSender
{
	public MessageEntry	entry;
	public String		baseUrl;
	public Hashtable	model;

	public Hashtable doPost (String baseUrl, MessageEntry entry)
	{
		HttpConnection httpConn = null;
		InputStream is = null;
		OutputStream os = null;
		String url = null;
		int responseCode;
		boolean waitForResponse;
		model = null;

		System.out.println ("<doPost>");
		url = entry.getUrl ();
		System.out.println ("making URL");
		waitForResponse = entry.getWaitForResponse ();
		System.out.println ("URL:" + url);
		System.out.println ("WaitForResponse = " + String.valueOf (waitForResponse));
		try
		{
			// Open an HTTP Connection object
			System.out.println ("Connecting...");
			httpConn = (HttpConnection) Connector.open (url);
			// Setup HTTP Request to POST
			httpConn.setRequestMethod (HttpConnection.POST);
			httpConn.setRequestProperty ("User-Agent", "Profile/MIDP-1.0 Confirguration/CLDC-1.0");
			httpConn.setRequestProperty ("Accept_Language", "en-US");
			// Content-Type is must to pass parameters in POST Request
			httpConn.setRequestProperty ("Content-Type", "application/x-www-form-urlencoded");
			System.out.println ("Post Paramters :" + entry.getPostParams ());
			os = httpConn.openOutputStream ();
			os.write (entry.getPostParams ().getBytes ());
			/**
			 * Caution: os.flush() is controversial. It may create unexpected
			 * behavior on certain mobile devices. Try it out for your mobile
			 * device
			 **/
			os.flush();
			System.out.println ("OS flushed");

			// Read Response from the Server
			responseCode = httpConn.getResponseCode ();
			System.out.println (responseCode + "ResponseCode --- HttpConnection");
			if (responseCode != HttpConnection.HTTP_OK)
			{
				throw new IOException ("Http response code: " + responseCode);
			}
			if (waitForResponse)
			{
				is = httpConn.openDataInputStream ();
				InputStreamReader isr = new InputStreamReader (is);
				model = XmlUtil.parseXmlResponse (isr);
			}
			else
			{
				System.out.println ("Parsing response");
			}
			System.out.println ("Response Complete");
		}
		catch (ClassCastException e)
		{
			throw new IllegalArgumentException ("Not an HTTP URL");
		}
		catch (IOException e)
		{
			e.printStackTrace ();
		}
		catch (Exception e)
		{
			e.printStackTrace ();
		}
		finally
		{
			if (os != null)
			{
				try
				{
					System.out.println ("Closing output stream");
					os.close ();
				}
				catch (IOException e)
				{
					e.printStackTrace ();
				}
			}

			if (httpConn != null)
			{
				try
				{
					System.out.println ("Closing http connection");
					httpConn.close ();
				}
				catch (IOException e)
				{
					e.printStackTrace ();
				}
			}
			System.out.println ("</doPost>");
		}
		return model;
	}

	/*
	 * private void doGet (String baseUrl, MessageEntry entry) { HttpConnection
	 * hc = null; String url = null; int responseCode; boolean successful =
	 * true; // XMLResponseModel responseModel = null;
	 * 
	 * System.out.println ("<doGet>"); url = baseUrl + "/" + entry.getUrl (); //
	 * progressListener.update("URL try count " + (submitRetries + 1) + "\n" //
	 * ); // progressListener.update("Connecting to " + url + "\n");
	 * 
	 * // progressListener.update("Params :" + entry.getPostParams() + "\n");
	 * currentWaitForResponse = true;
	 * 
	 * System.out.println ("Connecting"); try { hc = (HttpConnection)
	 * Connector.open (url); hc.setRequestMethod (HttpConnection.GET);
	 * 
	 * // currentConnection = hc; //
	 * System.out.println("Monitor Task scheduled"); // initTimer();
	 * 
	 * responseCode = hc.getResponseCode (); if (responseCode !=
	 * HttpConnection.HTTP_OK) { successful = false; throw new IOException
	 * ("Http response code: " + responseCode); } //
	 * progressListener.update("Connection successful\n"); System.out.println
	 * ("Parsing response");
	 * 
	 * // progressListener.update("Parsing response...\n"); // responseModel =
	 * XMLUtil.parseXMLResponse(new // InputStreamReader(hc.openInputStream()));
	 * // System.out.println(responseModel);
	 * 
	 * } catch (ClassCastException e) { successful = false; throw new
	 * IllegalArgumentException ("Not an HTTP URL"); } catch (IOException e) {
	 * successful = false; // progressListener.update("Connection error:" +
	 * e.toString() + // "\n"); e.printStackTrace (); } catch (SecurityException
	 * e) { successful = false; //
	 * progressListener.update("Permission not granted:" + e.toString()+ //
	 * "\n"); e.printStackTrace (); } catch (XmlPullParserException e) {
	 * successful = false; //progressListener.update("XML parsing failed:" +
	 * e.toString() + "\n"); e.printStackTrace(); }
	 * 
	 * catch (Exception e) { successful = false; if (e instanceof
	 * InterruptedException) { // progressListener.update("Took too long: " +
	 * e.toString() + // "\n"); } else { //
	 * progressListener.update("Unknown error: " + e.toString() + // "\n"); }
	 * e.printStackTrace (); } finally { // currentConnection = null; //
	 * monitor.cancel(); // System.out.println("Monitor Task Cancelled");
	 * 
	 * if (!successful) { // submitRetries++; } else { // submitRetries = 0; }
	 * 
	 * // progressListener.stop(successful, responseModel); if (hc != null) {
	 * try { System.out.println ("Closing http connection"); hc.close (); }
	 * catch (IOException e) { e.printStackTrace (); } } System.out.println
	 * ("</doGet>"); } }
	 */
}
