/* Copyright(C) 2015 Interactive Health Solutions, Pvt. Ltd.

This program is free software; you can redistribute it and/or modify it under the terms of the GNU General Public License as
published by the Free Software Foundation; either version 3 of the License (GPLv3), or any later version.
This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.

See the GNU General Public License for more details. You should have received a copy of the GNU General Public License along with this program; if not, write to the Interactive Health Solutions, info@ihsinformatics.com
You can also access the license on the internet at the address: http://www.gnu.org/licenses/gpl-3.0.html

Interactive Health Solutions, hereby disclaims all copyright interest in this program written by the contributors. */
package com.ihsinformatics.tbreach3tanzania.shared.model;



public class AlertSentGeneXpertResults implements java.io.Serializable
{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= -5761083776436259650L;

	public Integer					testId;
	
	public String				sputumTestId;
	public String				patientId;
	//private Integer				irs;
	public String				laboratoryId;
	public String				collectedBy;
	public String				dateSubmitted;
	public String				dateTested;
	public String				geneXpertResult;
	public Boolean				isPositive;
	public String				mtbBurden;
	public String				drugResistance;
	public Integer				errorCode;
	public String				remarks;
	
	
	public String 				pcId;
	public String 				instrumentId;
	public String 				moduleId;
	public String 				cartridgeId;
	public String 				reagentLotId;
	public String 				cartridgeExpiryDate;
	public String				operatorId;
	
	public String 				probeResultA;
	public String 				probeResultB;
	public String 				probeResultC;
	public String 				probeResultD;
	public String 				probeResultE;
	public String 				probeResultSPC;
	
	public Double 				probeCtA;
	public Double 				probeCtB;
	public Double 				probeCtC;
	public Double 				probeCtD;
	public Double 				probeCtE;
	public Double 				probeCtSPC;
	
	public Double 				probeEndptA;
	public Double 				probeEndptB;
	public Double 				probeEndptC;
	public Double 				probeEndptD;
	public Double 				probeEndptE;
	public Double 				probeEndptSPC;
	
	public AlertSentGeneXpertResults ()
	{
	}
	
	public AlertSentGeneXpertResults (String patientId)
	{
		this.patientId = patientId;
	}
	
	/**
	 * AlertentGeneXpertResults constructor
	 * 
	 * @param array string
	 */
	
	public AlertSentGeneXpertResults (String[] geneXpertResultSet)
	{
		testId = Integer.parseInt (geneXpertResultSet[35]);
		patientId = geneXpertResultSet[0];
		sputumTestId = geneXpertResultSet[1];
		laboratoryId = geneXpertResultSet[2];
		collectedBy = geneXpertResultSet[3];
		dateSubmitted = geneXpertResultSet[4];
		dateTested = geneXpertResultSet[5];
		if (geneXpertResultSet[6]=="true")
			isPositive = true;
		else
			isPositive = false;
		drugResistance = geneXpertResultSet[7];
		remarks = geneXpertResultSet[8];
		geneXpertResult = geneXpertResultSet[9];
		mtbBurden = geneXpertResultSet[10];
		if(!geneXpertResultSet[11].equals (""))
			errorCode = Integer.parseInt(geneXpertResultSet[11]);
		instrumentId = geneXpertResultSet[12];
		moduleId = geneXpertResultSet[13];
		reagentLotId = geneXpertResultSet[14];
		pcId = geneXpertResultSet[15];
		cartridgeId = geneXpertResultSet[16];
		probeResultA = geneXpertResultSet[17];
		probeResultB = geneXpertResultSet[18];
		probeResultC = geneXpertResultSet[19];
		probeResultD = geneXpertResultSet[20];
		probeResultE = geneXpertResultSet[21];
		probeResultSPC = geneXpertResultSet[22];
		if(!geneXpertResultSet[23].equals (""))
			probeCtA = Double.valueOf (geneXpertResultSet[23]);
		if(!geneXpertResultSet[24].equals (""))
			probeCtB = Double.valueOf (geneXpertResultSet[24]);
		if(!geneXpertResultSet[25].equals (""))
			probeCtC = Double.valueOf (geneXpertResultSet[25]);
		if(!geneXpertResultSet[26].equals (""))
			probeCtD = Double.valueOf (geneXpertResultSet[26]);
		if(!geneXpertResultSet[27].equals (""))
			probeCtE = Double.valueOf (geneXpertResultSet[27]);
		if(!geneXpertResultSet[28].equals (""))
			probeCtSPC = Double.valueOf (geneXpertResultSet[28]);
		if(!geneXpertResultSet[29].equals (""))
			probeEndptA = Double.valueOf (geneXpertResultSet[29]);
		if(!geneXpertResultSet[30].equals (""))
			probeEndptB = Double.valueOf (geneXpertResultSet[30]);
		if(!geneXpertResultSet[31].equals (""))
			probeEndptC = Double.valueOf (geneXpertResultSet[31]);
		if(!geneXpertResultSet[32].equals (""))
			probeEndptD = Double.valueOf (geneXpertResultSet[32]);
		if(!geneXpertResultSet[33].equals (""))
			probeEndptE = Double.valueOf (geneXpertResultSet[33]);
		if(!geneXpertResultSet[34].equals (""))
			probeEndptSPC = Double.valueOf (geneXpertResultSet[34]);
		operatorId = geneXpertResultSet[36];
		cartridgeExpiryDate = geneXpertResultSet[37];
	} 
}
