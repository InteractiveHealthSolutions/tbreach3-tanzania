/* Copyright(C) 2015 Interactive Health Solutions, Pvt. Ltd.

This program is free software; you can redistribute it and/or modify it under the terms of the GNU General Public License as
published by the Free Software Foundation; either version 3 of the License (GPLv3), or any later version.
This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.

See the GNU General Public License for more details. You should have received a copy of the GNU General Public License along with this program; if not, write to the Interactive Health Solutions, info@ihsinformatics.com
You can also access the license on the internet at the address: http://www.gnu.org/licenses/gpl-3.0.html

Interactive Health Solutions, hereby disclaims all copyright interest in this program written by the contributors. */
package com.ihsinformatics.tbreach3tanzania.shared.model;

public class SmsRecipient implements java.io.Serializable
{

	private static final long	serialVersionUID	= -2188806441253925606L;
	private Integer idSmsRecipient;
	private String description;
	private String contactNumber;
	
	public SmsRecipient(){
		
	}
	
	public SmsRecipient(Integer smsRuleId, String description, String target){
		
		this.idSmsRecipient = smsRuleId;
		this.description = description;
		this.contactNumber = target;
	
	}
	
	public Integer getSmsRuleId (){
		
		return this.idSmsRecipient;
	}
	
	public String getDescription (){
		
		return this.description;
	}
	
	public String getTarget (){
		
		return this.contactNumber;
	}

	public void setSmsRuleId (Integer smsRuleId)
	{
		this.idSmsRecipient = smsRuleId;
	}
	
	public void setDescription (String description)
	{
		this.description = description;
	}
	
	public void setTarget (String target)
	{
		this.contactNumber = target;
	}
	
	public String toString ()
	{
		return idSmsRecipient+ ", "+description+", "+contactNumber;
	}
	
}
