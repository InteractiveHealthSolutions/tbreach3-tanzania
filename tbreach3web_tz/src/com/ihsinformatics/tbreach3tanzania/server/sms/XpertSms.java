/* Copyright(C) 2015 Interactive Health Solutions, Pvt. Ltd.

This program is free software; you can redistribute it and/or modify it under the terms of the GNU General Public License as
published by the Free Software Foundation; either version 3 of the License (GPLv3), or any later version.
This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.

See the GNU General Public License for more details. You should have received a copy of the GNU General Public License along with this program; if not, write to the Interactive Health Solutions, info@ihsinformatics.com
You can also access the license on the internet at the address: http://www.gnu.org/licenses/gpl-3.0.html

Interactive Health Solutions, hereby disclaims all copyright interest in this program written by the contributors. */
package com.ihsinformatics.tbreach3tanzania.server.sms;


import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;
import com.ihsinformatics.tbreach3tanzania.server.ServerServiceImpl;
import com.ihsinformatics.tbreach3tanzania.server.util.HibernateUtil;
import com.ihsinformatics.tbreach3tanzania.shared.model.AlertSentGeneXpertResults;
import com.ihsinformatics.tbreach3tanzania.shared.model.Person;
import com.ihsinformatics.tbreach3tanzania.shared.model.Sms;
import com.ihsinformatics.tbreach3tanzania.shared.model.SmsRecipient;
import com.ihsinformatics.tbreach3tanzania.shared.model.SmsRules;

public class XpertSms
{
	private ServerServiceImpl	ssl;
	Timer timer;	
	int i = 0;   // For Debugging purposes only!

	public XpertSms(){
    	try {
    	ssl = new ServerServiceImpl ();
        timer = new Timer();
        timer.schedule(new RemindTask(), //RemindTask is scheduled 
        		0,        //initial delay
                30000);  //subsequent rate (30 seconds)
	
    	}
    	catch(Exception e){e.printStackTrace();}
    }
	
    class RemindTask extends TimerTask {
        public void run() {
        	        	
    		String [][] result = ssl.getTableData ("select * from GeneXpertResults");
    		
    		if(result.length != 0){
    			for(int i = 0; i <result.length; i++){
    			   
    				String patientId = result[i][0];
        				    				
    				Object obj =  HibernateUtil.util.findObject ("from Person where pid ='"+patientId+"'");
    				if(obj!=null){
    					Person person = (Person) obj;
    					Calendar c = Calendar.getInstance();
    					c.add(Calendar.HOUR_OF_DAY, 0);
    					c.add(Calendar.MINUTE, 0);
    					c.add (Calendar.SECOND, 30);
    				
    					StringBuffer sb = new StringBuffer ();
    			    
    					sb.append("Patient's ID: "+patientId+"\n");
    					sb.append("Your Result is Ready."+"\n");
    					
    					// 'alert on TB Sputum Results to Client'
    					SmsRules r1 = (SmsRules)  ssl.findSmsRule ("1");
    					if(r1.getEnable ()){
    						Sms sms = new Sms(person.getMobile (), sb.toString () , c.getTime (), c.getTime (),SmsStatus.PENDING, null, null);	
    						ssl.sendGenericSMSAlert(sms);
    					}
    					
    					// 'alert on TB Sputum Results to Regional Coordinator'
    					SmsRules r2 = (SmsRules)  ssl.findSmsRule ("2");
    					if(r2.getEnable ()){
    						SmsRecipient sr = (SmsRecipient) ssl.findSmsRecipient ("0");
    						String targetNumber = sr.getTarget ();
    						Sms sms = new Sms(targetNumber, sb.toString () , c.getTime (), c.getTime (),SmsStatus.PENDING, null, null);	
    						ssl.sendGenericSMSAlert(sms);
    					}
    					
    					// 'alert on TB Sputum Results to M&E Coordinator'
    					SmsRules r3 = (SmsRules)  ssl.findSmsRule ("3");
    					if(r3.getEnable ()){
    						SmsRecipient sr = (SmsRecipient) ssl.findSmsRecipient ("1");
    						String targetNumber = sr.getTarget ();
    						Sms sms = new Sms(targetNumber, sb.toString () , c.getTime (), c.getTime (),SmsStatus.PENDING, null, null);	
    						ssl.sendGenericSMSAlert(sms);
    					}
    					
    					// 'alert on TB Sputum Results to District Coordinator'
    					SmsRules r4 = (SmsRules)  ssl.findSmsRule ("4");
    					if(r4.getEnable ()){
    						String targetNumber = null;
    						if (person.getState ().equals ("TANG")){
    							SmsRecipient sr = (SmsRecipient) ssl.findSmsRecipient ("2");
        						targetNumber = sr.getTarget ();
    						}
    						else if (person.getState ().equals ("GT")){
    							SmsRecipient sr = (SmsRecipient) ssl.findSmsRecipient ("3");
        						targetNumber = sr.getTarget ();
    						}
    						else if (person.getState ().equals ("KIT")){
    							SmsRecipient sr = (SmsRecipient) ssl.findSmsRecipient ("4");
        						targetNumber = sr.getTarget ();
    						}
    						else if (person.getState ().equals ("SIM")){
    							SmsRecipient sr = (SmsRecipient) ssl.findSmsRecipient ("5");
        						targetNumber = sr.getTarget ();
    						}
    						else if (person.getState ().equals ("MB")){
    							SmsRecipient sr = (SmsRecipient) ssl.findSmsRecipient ("6");
        						targetNumber = sr.getTarget ();
    						}
    						else if (person.getState ().equals ("HAN")){
    							SmsRecipient sr = (SmsRecipient) ssl.findSmsRecipient ("7");
        						targetNumber = sr.getTarget ();
    						}
    						else if (person.getState ().equals ("BD")){
    							SmsRecipient sr = (SmsRecipient) ssl.findSmsRecipient ("8");
        						targetNumber = sr.getTarget ();
    						}
    						else if (person.getState ().equals ("BT")){
    							SmsRecipient sr = (SmsRecipient) ssl.findSmsRecipient ("9");
        						targetNumber = sr.getTarget ();
    						}
    						Sms sms = new Sms(targetNumber, sb.toString () , c.getTime (), c.getTime (),SmsStatus.PENDING, null, null);	
    						ssl.sendGenericSMSAlert(sms);
    					}
    					
    					AlertSentGeneXpertResults gxr = new AlertSentGeneXpertResults(result[i]);
    					ssl.saveAlertSentGeneXpertResults (gxr);
    					
    					HibernateUtil.util.delete ("delete from GeneXpertResults where TestID = '"+ result[i][35]+"'");
    				}	
    			}
    		}       	
        	
        } // run ends

    } // Task Class 
	
	
}
