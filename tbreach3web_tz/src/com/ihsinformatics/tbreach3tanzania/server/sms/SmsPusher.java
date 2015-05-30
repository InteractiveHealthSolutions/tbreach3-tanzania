/* Copyright(C) 2015 Interactive Health Solutions, Pvt. Ltd.

This program is free software; you can redistribute it and/or modify it under the terms of the GNU General Public License as
published by the Free Software Foundation; either version 3 of the License (GPLv3), or any later version.
This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.

See the GNU General Public License for more details. You should have received a copy of the GNU General Public License along with this program; if not, write to the Interactive Health Solutions, info@ihsinformatics.com
You can also access the license on the internet at the address: http://www.gnu.org/licenses/gpl-3.0.html

Interactive Health Solutions, hereby disclaims all copyright interest in this program written by the contributors. */
package com.ihsinformatics.tbreach3tanzania.server.sms;

import java.util.TimerTask;
import java.util.Timer;
import org.irdresearch.smstarseel.context.TarseelContext;
import org.irdresearch.smstarseel.context.TarseelServices;
import org.irdresearch.smstarseel.data.OutboundMessage.PeriodType;
import org.irdresearch.smstarseel.data.OutboundMessage.Priority;
import com.ihsinformatics.tbreach3tanzania.shared.model.Sms;
import com.ihsinformatics.tbreach3tanzania.server.ServerServiceImpl;
import com.ihsinformatics.tbreach3tanzania.server.util.HibernateUtil;


public class SmsPusher
{

	private ServerServiceImpl	ssl;
	Timer timer;	
	int i = 0;   // For Debugging purposes only!
	

    public SmsPusher(){
    	try {
    		System.out.println ("Hello!");    		
    	SmsTarseel.Instantiate ();  //smstarseel properties Initialization 
    	ssl = new ServerServiceImpl ();
        timer = new Timer();
        timer.schedule(new RemindTask(),0,30000);  //RemindTask() is scheduled for time interval 30 seconds      	
    	}
    	catch(Exception e){e.printStackTrace();}
    }
    class RemindTask extends TimerTask {
        public void run() {
        	
            i++;
        	System.out.format("Ilteration No. " + i + " started.%n");
        		
        	//Pushing Pending SMS to smstarseel
        	Object[] objs =  HibernateUtil.util.findObjects ("from Sms where status ='"+SmsStatus.PENDING+"'");
        	        	
        	TarseelServices sc = null;
        	try{
        		for (Object object : objs) {
        			Sms sms = (Sms) object;
        			sms.getMessageText ();
        			sc = TarseelContext.getServices();
        			sc.getSmsService().createNewOutboundSms(sms.getTargetNumber (), sms.getMessageText (), sms.getDateDue (), Priority.HIGH, 24, PeriodType.HOUR, 2, null);
        			sc.commitTransaction();
        			sms.setStatus ("SEND");
        			ssl.updateSms (sms);
					}
        	}catch(Exception e){
        		e.printStackTrace();
        	}
        	finally{
        		if (sc != null)
        			sc.closeSession ();
        	}
        	
        	System.out.format("Ilteration No. " + i + " ended.%n");
        	      	
        }
    }
}
