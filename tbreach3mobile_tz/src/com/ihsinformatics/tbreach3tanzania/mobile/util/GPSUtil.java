/* Copyright(C) 2015 Interactive Health Solutions, Pvt. Ltd.

This program is free software; you can redistribute it and/or modify it under the terms of the GNU General Public License as
published by the Free Software Foundation; either version 3 of the License (GPLv3), or any later version.
This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.

See the GNU General Public License for more details. You should have received a copy of the GNU General Public License along with this program; if not, write to the Interactive Health Solutions, info@ihsinformatics.com
You can also access the license on the internet at the address: http://www.gnu.org/licenses/gpl-3.0.html

Interactive Health Solutions, hereby disclaims all copyright interest in this program written by the contributors. */

package com.ihsinformatics.tbreach3tanzania.mobile.util;

import javax.microedition.location.Coordinates;
import javax.microedition.location.Criteria;
import javax.microedition.location.Location;
import javax.microedition.location.LocationException;
import javax.microedition.location.LocationProvider;

public class GPSUtil
{

	private Double	lat;
	private Double	lng;

	public Double getLng ()
	{
		return lng;
	}

	public void setLng (Double lng)
	{
		this.lng = lng;
	}

	public Double getLat ()
	{
		return lat;
	}

	public void setLat (Double lat)
	{
		this.lat = lat;
	}

	public GPSUtil ()
	{
		lat = new Double (-1);
		lng = new Double (-1);
	}

	public void getLocation () throws LocationException, InterruptedException
	{
		// Set criteria for selecting a location provider:
		// accurate to 500 meters horizontally
		Criteria cr = new Criteria ();
		cr.setHorizontalAccuracy (500);

		// Get an instance of the provider
		LocationProvider lp = LocationProvider.getInstance (cr);

		// Request the location, setting a one-minute timeout
		Location l = lp.getLocation (180);
		Coordinates c = l.getQualifiedCoordinates ();

		if (c != null)
		{
			// Use coordinate information
			lat = new Double (c.getLatitude ());
			lng = new Double (c.getLongitude ());
		}

	}

}
