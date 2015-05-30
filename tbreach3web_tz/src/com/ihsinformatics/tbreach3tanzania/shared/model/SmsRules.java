/* Copyright(C) 2015 Interactive Health Solutions, Pvt. Ltd.

This program is free software; you can redistribute it and/or modify it under the terms of the GNU General Public License as
published by the Free Software Foundation; either version 3 of the License (GPLv3), or any later version.
This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.

See the GNU General Public License for more details. You should have received a copy of the GNU General Public License along with this program; if not, write to the Interactive Health Solutions, info@ihsinformatics.com
You can also access the license on the internet at the address: http://www.gnu.org/licenses/gpl-3.0.html

Interactive Health Solutions, hereby disclaims all copyright interest in this program written by the contributors. */
package com.ihsinformatics.tbreach3tanzania.shared.model;

public class SmsRules implements java.io.Serializable
{

	private static final long	serialVersionUID	= -1781517346740667374L;
	private Integer idSmsRules;
	private boolean enable;
	private String description;
	private String targetNumber;
	
	public SmsRules(){
		
	}
	
	public SmsRules(Integer smsRuleId, boolean enable, String description, String target){
		
		this.idSmsRules = smsRuleId;
		this.enable = enable;
		this.description = description;
		this.targetNumber = target;
	
	}
	
	public Integer getSmsRuleId (){
		
		return this.idSmsRules;
	}
	
	public boolean getEnable (){
		
		return this.enable;
	}
	
	public String getDescription (){
		
		return this.description;
	}
	
	public String getTarget (){
		
		return this.targetNumber;
	}

	public void setSmsRuleId (Integer smsRuleId)
	{
		this.idSmsRules = smsRuleId;
	}
	
	public void setEnable (boolean enable)
	{
		this.enable = enable;
	}
	
	public void setDescription (String description)
	{
		this.description = description;
	}
	
	public void setTarget (String target)
	{
		this.targetNumber = target;
	}
	
	public String toString ()
	{
		return idSmsRules+", "+enable+", "+description+", "+targetNumber;
	}
	
}