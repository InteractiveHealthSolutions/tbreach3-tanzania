/* Copyright(C) 2015 Interactive Health Solutions, Pvt. Ltd.

This program is free software; you can redistribute it and/or modify it under the terms of the GNU General Public License as
published by the Free Software Foundation; either version 3 of the License (GPLv3), or any later version.
This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.

See the GNU General Public License for more details. You should have received a copy of the GNU General Public License along with this program; if not, write to the Interactive Health Solutions, info@ihsinformatics.com
You can also access the license on the internet at the address: http://www.gnu.org/licenses/gpl-3.0.html

Interactive Health Solutions, hereby disclaims all copyright interest in this program written by the contributors. */

package com.ihsinformatics.tbreach3tanzania.mobile.model;

import com.ihsinformatics.tbreach3tanzania.mobile.shared.ReportType;

public class ReportListItem
{
	public static final int				MAX_ITEMS				= 2;

	public static final ReportListItem	MISSING_SMEAR			= new ReportListItem (0, "Pending Smear Report", ReportType.REPORT_MISSING_SMEAR, "Patients with pending Smear results", true);
	public static final ReportListItem	MISSING_CXR_RESULT		= new ReportListItem (1, "Pending CXR Result Report", ReportType.REPORT_MISSING_CXR_RESULT, "Patients with pending Xray results", true);

	public final int					INDEX;
	public final String					NAME;
	final boolean						show;
	final String						reportType;
	final String						reportDesc;

	public String getReportDesc ()
	{
		return reportDesc;
	}

	public String getReportType ()
	{
		return reportType;
	}

	public ReportListItem (int index, String displayName, String reportType, String reportDesc, boolean show)
	{
		this.INDEX = index;
		this.reportType = reportType;
		this.reportDesc = reportDesc;
		this.NAME = displayName;
		this.show = show;
	}

	public boolean isShown ()
	{
		return show;
	}
}
