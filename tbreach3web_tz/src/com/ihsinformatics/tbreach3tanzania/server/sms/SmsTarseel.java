/* Copyright(C) 2015 Interactive Health Solutions, Pvt. Ltd.

This program is free software; you can redistribute it and/or modify it under the terms of the GNU General Public License as
published by the Free Software Foundation; either version 3 of the License (GPLv3), or any later version.
This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.

See the GNU General Public License for more details. You should have received a copy of the GNU General Public License along with this program; if not, write to the Interactive Health Solutions, info@ihsinformatics.com
You can also access the license on the internet at the address: http://www.gnu.org/licenses/gpl-3.0.html

Interactive Health Solutions, hereby disclaims all copyright interest in this program written by the contributors. */
package com.ihsinformatics.tbreach3tanzania.server.sms;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import javax.management.InstanceAlreadyExistsException;
import net.jmatrix.eproperties.EProperties;
import org.irdresearch.smstarseel.context.TarseelContext;
import com.ihsinformatics.tbreach3tanzania.server.util.SmsTarseelUtil;

public class SmsTarseel
{
 
	
	@SuppressWarnings("unchecked")
	public static boolean Instantiate() throws IOException, InstanceAlreadyExistsException{ 
		
		System.out.println(">>>>LOADING SYSTEM PROPERTIES...");
		InputStream f = Thread.currentThread().getContextClassLoader().getResourceAsStream("smstarseel.properties");
		// Java Properties donot seem to support substitutions hence EProperties are used to accomplish the task
		
		EProperties root = new EProperties();
		root.load(f);

		// Java Properties to send to context and other APIs for configuration
		Properties prop = new Properties();
		prop.putAll(SmsTarseelUtil.convertEntrySetToMap(root.entrySet()));

		TarseelContext.instantiate(prop, "smstarseel.cfg.xml");
		return true;
	}
}
