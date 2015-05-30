/* Copyright(C) 2015 Interactive Health Solutions, Pvt. Ltd.

This program is free software; you can redistribute it and/or modify it under the terms of the GNU General Public License as
published by the Free Software Foundation; either version 3 of the License (GPLv3), or any later version.
This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.

See the GNU General Public License for more details. You should have received a copy of the GNU General Public License along with this program; if not, write to the Interactive Health Solutions, info@ihsinformatics.com
You can also access the license on the internet at the address: http://www.gnu.org/licenses/gpl-3.0.html

Interactive Health Solutions, hereby disclaims all copyright interest in this program written by the contributors. */

package com.ihsinformatics.tbreach3tanzania.shared.model;

// Generated Jun 12, 2012 4:08:49 PM by Hibernate Tools 3.4.0.CR1

/**
 * PersonRoleId generated by hbm2java
 */
public class PersonRoleId implements java.io.Serializable
{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= -8840486690526875865L;
	private String 					pid;
	private String				role;

	public PersonRoleId ()
	{
	}

	public PersonRoleId (String  pid, String role)
	{
		this.pid = pid;
		this.role = role;
	}

	public String  getPid ()
	{
		return this.pid;
	}

	public void setPid (String  pid)
	{
		this.pid = pid;
	}

	public String getRole ()
	{
		return this.role;
	}

	public void setRole (String role)
	{
		this.role = role;
	}

	@Override
	public int hashCode ()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((pid == null) ? 0 : pid.hashCode ());
		result = prime * result + ((role == null) ? 0 : role.hashCode ());
		return result;
	}

	@Override
	public boolean equals (Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass () != obj.getClass ())
			return false;
		PersonRoleId other = (PersonRoleId) obj;
		if (pid == null)
		{
			if (other.pid != null)
				return false;
		}
		else if (!pid.equals (other.pid))
			return false;
		if (role == null)
		{
			if (other.role != null)
				return false;
		}
		else if (!role.equals (other.role))
			return false;
		return true;
	}

	@Override
	public String toString ()
	{
		return pid + ", " + role;
	}

}