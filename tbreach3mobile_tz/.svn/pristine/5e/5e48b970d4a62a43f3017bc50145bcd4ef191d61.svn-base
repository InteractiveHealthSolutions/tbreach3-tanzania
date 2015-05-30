
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
