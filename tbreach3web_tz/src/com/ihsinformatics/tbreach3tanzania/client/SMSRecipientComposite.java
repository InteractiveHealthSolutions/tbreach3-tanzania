/* Copyright(C) 2015 Interactive Health Solutions, Pvt. Ltd.

This program is free software; you can redistribute it and/or modify it under the terms of the GNU General Public License as
published by the Free Software Foundation; either version 3 of the License (GPLv3), or any later version.
This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.

See the GNU General Public License for more details. You should have received a copy of the GNU General Public License along with this program; if not, write to the Interactive Health Solutions, info@ihsinformatics.com
You can also access the license on the internet at the address: http://www.gnu.org/licenses/gpl-3.0.html

Interactive Health Solutions, hereby disclaims all copyright interest in this program written by the contributors. */
package com.ihsinformatics.tbreach3tanzania.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.ihsinformatics.tbreach3tanzania.shared.TBRT;
import com.ihsinformatics.tbreach3tanzania.shared.model.SmsRecipient;
import com.google.gwt.user.client.ui.Button;

public class SMSRecipientComposite extends Composite implements ClickHandler
{
	private static ServerServiceAsync	service				= GWT.create (ServerService.class);
	private static LoadingWidget		loading				= new LoadingWidget ();
	//private static final String			menuName			= "SMS";
	//private static final String			tableName			= "Sms";
	
	//private UserRightsUtil				rights				= new UserRightsUtil ();
	
	private FlexTable					flexTable			= new FlexTable ();
	private FlexTable					topFlexTable		= new FlexTable ();
	private FlexTable					rightFlexTable		= new FlexTable ();
	
	private	Grid						grid				= new Grid (1,40);
	
	private Label						lblTbReachSms				= new Label (TBRT.getProjectTitle () + " Sms Recipient Setup");
	
	private Label						lblRegionalCoordinator		= new Label ("Regional TB & Leprosy Coordinator: ");
	private Label						lblMECoordinator			= new Label ("M & E Coordinator: ");
	private	Label						lbltangaDistrict			= new Label ("Tanga TB & Leprosy Coordinator: ");
	private	Label						lblgeitaDistrict			= new Label ("Geita TB & Leprosy Coordinator: ");
	private	Label						lblkitetoDistrict			= new Label ("Kiteto TB & Leprosy Coordinator: ");
	private	Label						lblsimanjiroDistrict		= new Label ("Simanjiro TB & Leprosy Coordinator: ");
	private	Label						lblmbuluDistrict			= new Label ("Mbulu TB & Leprosy Coordinator: ");
	private	Label						lblhanangDistrict			= new Label ("Hanang TB & Leprosy Coordinator: ");
	private	Label						lblbabatiDistrict			= new Label ("Babati District TB & Leprosy Coordinator: ");
	private Label						lblbabatiTownDistrict		= new Label ("Babati Town TB & Leprosy Coordinator: ");
	
	private TextBox					contactNoRegionalCoordinator	= new TextBox ();
	private TextBox					contactNoMECoordinator			= new TextBox ();
	private	TextBox					contactNotangaDistrict			= new TextBox ();
	private	TextBox					contactNogeitaDistrict			= new TextBox ();
	private	TextBox					contactNokitetoDistrict			= new TextBox ();
	private	TextBox					contactNosimanjiroDistrict		= new TextBox ();
	private	TextBox					contactNombuluDistrict			= new TextBox ();
	private	TextBox					contactNohanangDistrict			= new TextBox ();
	private	TextBox					contactNobabatiDistrict			= new TextBox ();
	private TextBox					contactNobabatiTownDistrict		= new TextBox ();
	
	private Button						saveButton			= new Button ("Save");
	private Button						closeButton			= new Button ("Close");
	
	public SMSRecipientComposite ()
	{
		initWidget (flexTable);
		flexTable.setSize ("97%", "100%");
		flexTable.setWidget (0, 0, topFlexTable);
		lblTbReachSms.setStyleName ("title");
		topFlexTable.setWidget (0, 0, lblTbReachSms);
		flexTable.setWidget (1, 0, rightFlexTable);
		rightFlexTable.setSize ("100%", "100%");
		
		lblRegionalCoordinator.setWordWrap (false);
		
		rightFlexTable.setWidget (0, 0, lblRegionalCoordinator);
		rightFlexTable.setWidget (1, 0, lblMECoordinator);	
		rightFlexTable.setWidget (3, 0, lbltangaDistrict);
		rightFlexTable.setWidget (4, 0, lblgeitaDistrict);
		rightFlexTable.setWidget (5, 0, lblkitetoDistrict);
		lblsimanjiroDistrict.setWordWrap (false);
		rightFlexTable.setWidget (6, 0, lblsimanjiroDistrict);
		rightFlexTable.setWidget (7, 0, lblmbuluDistrict);
		rightFlexTable.setWidget (8, 0, lblhanangDistrict);
		lblbabatiDistrict.setWordWrap (false);
		rightFlexTable.setWidget (9, 0, lblbabatiDistrict);
		lblbabatiTownDistrict.setWordWrap (false);
		rightFlexTable.setWidget (10, 0, lblbabatiTownDistrict);
		
		rightFlexTable.setWidget (0, 1, contactNoRegionalCoordinator);
		contactNoRegionalCoordinator.setMaxLength (10);
		contactNoRegionalCoordinator.setSize ("80px", "10px");
		contactNoRegionalCoordinator.setName ("0");
		contactNoRegionalCoordinator.setText ("Regional Coordinator");
		rightFlexTable.setWidget (1, 1, contactNoMECoordinator);
		contactNoMECoordinator.setMaxLength (10);
		contactNoMECoordinator.setSize ("80px", "10px");
		contactNoMECoordinator.setName ("1");
		rightFlexTable.setWidget (3, 1, contactNotangaDistrict);
		contactNotangaDistrict.setMaxLength (10);
		contactNotangaDistrict.setSize ("80px", "10px");
		contactNotangaDistrict.setName ("2");
		rightFlexTable.setWidget (4, 1, contactNogeitaDistrict);
		contactNogeitaDistrict.setMaxLength (10);
		contactNogeitaDistrict.setSize ("80px", "10px");
		contactNogeitaDistrict.setName ("3");
		rightFlexTable.setWidget (5, 1, contactNokitetoDistrict);
		contactNokitetoDistrict.setMaxLength (10);
		contactNokitetoDistrict.setSize ("80px", "10px");
		contactNokitetoDistrict.setName ("4");
		rightFlexTable.setWidget (6, 1, contactNosimanjiroDistrict);
		contactNosimanjiroDistrict.setMaxLength (10);
		contactNosimanjiroDistrict.setSize ("80px", "10px");
		contactNosimanjiroDistrict.setName ("5");
		rightFlexTable.setWidget (7, 1, contactNombuluDistrict);
		contactNombuluDistrict.setMaxLength (10);
		contactNombuluDistrict.setSize ("80px", "10px");
		contactNombuluDistrict.setName ("6");
		rightFlexTable.setWidget (8, 1, contactNohanangDistrict);
		contactNohanangDistrict.setMaxLength (10);
		contactNohanangDistrict.setSize ("80px", "10px");
		contactNohanangDistrict.setName ("7");
		rightFlexTable.setWidget (9, 1, contactNobabatiDistrict);
		contactNobabatiDistrict.setMaxLength (10);
		contactNobabatiDistrict.setSize ("80px", "10px");
		contactNobabatiDistrict.setName ("8");
		rightFlexTable.setWidget (10, 1,contactNobabatiTownDistrict);
		contactNobabatiTownDistrict.setMaxLength (10);
		contactNobabatiTownDistrict.setSize ("80px", "10px");
		contactNobabatiTownDistrict.setName ("9");
		
		flexTable.getCellFormatter ().setHorizontalAlignment (0, 0, HasHorizontalAlignment.ALIGN_CENTER);
		rightFlexTable.getCellFormatter ().setHorizontalAlignment (0, 0, HasHorizontalAlignment.ALIGN_RIGHT);
		rightFlexTable.getCellFormatter ().setHorizontalAlignment (1, 0, HasHorizontalAlignment.ALIGN_RIGHT);
		rightFlexTable.getCellFormatter ().setHorizontalAlignment (3, 0, HasHorizontalAlignment.ALIGN_RIGHT);
		rightFlexTable.getCellFormatter ().setHorizontalAlignment (4, 0, HasHorizontalAlignment.ALIGN_RIGHT);
		rightFlexTable.getCellFormatter ().setHorizontalAlignment (5, 0, HasHorizontalAlignment.ALIGN_RIGHT);
		rightFlexTable.getCellFormatter ().setHorizontalAlignment (6, 0, HasHorizontalAlignment.ALIGN_RIGHT);
		rightFlexTable.getCellFormatter ().setHorizontalAlignment (7, 0, HasHorizontalAlignment.ALIGN_RIGHT);
		rightFlexTable.getCellFormatter ().setHorizontalAlignment (8, 0, HasHorizontalAlignment.ALIGN_RIGHT);
		rightFlexTable.getCellFormatter ().setHorizontalAlignment (9, 0, HasHorizontalAlignment.ALIGN_RIGHT);
		rightFlexTable.getCellFormatter ().setHorizontalAlignment (10, 0, HasHorizontalAlignment.ALIGN_RIGHT);
		rightFlexTable.getCellFormatter ().setHorizontalAlignment (0, 1, HasHorizontalAlignment.ALIGN_LEFT);
		rightFlexTable.getCellFormatter ().setHorizontalAlignment (1, 1, HasHorizontalAlignment.ALIGN_LEFT);
		rightFlexTable.getCellFormatter ().setHorizontalAlignment (3, 1, HasHorizontalAlignment.ALIGN_LEFT);
		rightFlexTable.getCellFormatter ().setHorizontalAlignment (4, 1, HasHorizontalAlignment.ALIGN_LEFT);
		rightFlexTable.getCellFormatter ().setHorizontalAlignment (5, 1, HasHorizontalAlignment.ALIGN_LEFT);
		rightFlexTable.getCellFormatter ().setHorizontalAlignment (6, 1, HasHorizontalAlignment.ALIGN_LEFT);
		rightFlexTable.getCellFormatter ().setHorizontalAlignment (7, 1, HasHorizontalAlignment.ALIGN_LEFT);
		rightFlexTable.getCellFormatter ().setHorizontalAlignment (8, 1, HasHorizontalAlignment.ALIGN_LEFT);
		rightFlexTable.getCellFormatter ().setHorizontalAlignment (9, 1, HasHorizontalAlignment.ALIGN_LEFT);
		rightFlexTable.getCellFormatter ().setHorizontalAlignment (10, 1, HasHorizontalAlignment.ALIGN_LEFT);
		
		flexTable.setWidget (4, 0, grid);
		grid.setWidget (0, 19, saveButton);
		grid.setWidget (0, 21, closeButton);
		
		saveButton.addClickHandler (this);
		closeButton.addClickHandler (this);
		
		setCurrent ();
		
		
	}

	public boolean validate ()
	{
	
		if(contactNoRegionalCoordinator.getValue ().length () == 10 && contactNoMECoordinator.getValue ().length () == 10 
		      && contactNotangaDistrict.getValue ().length () == 10 && contactNogeitaDistrict.getValue ().length () == 10
		      && contactNokitetoDistrict.getValue ().length () == 10 && contactNosimanjiroDistrict.getValue ().length () == 10
		      && contactNombuluDistrict.getValue ().length () == 10 && contactNohanangDistrict.getValue ().length () == 10
		      && contactNobabatiDistrict.getValue().length () == 10  && contactNobabatiTownDistrict.getValue().length () == 10
		   )
				return true;
		else
			return false;
	}

	
	public void saveData ()
	{
		if(validate()){
		
			SmsRecipient[] smsRecipient = new SmsRecipient[10];
		
			smsRecipient[0] = new SmsRecipient();
			smsRecipient[0].setSmsRuleId (Integer.parseInt (contactNoRegionalCoordinator.getName ()));
			smsRecipient[0].setDescription ("Regional Coordinator");
			smsRecipient[0].setTarget (contactNoRegionalCoordinator.getValue ());
		
			smsRecipient[1] = new SmsRecipient();
			smsRecipient[1].setSmsRuleId (Integer.parseInt (contactNoMECoordinator.getName ()));
			smsRecipient[1].setDescription ("M & E Coordinator");
			smsRecipient[1].setTarget (contactNoMECoordinator.getValue ());
		
			smsRecipient[2] = new SmsRecipient();
			smsRecipient[2].setSmsRuleId (Integer.parseInt (contactNotangaDistrict.getName ()));
			smsRecipient[2].setDescription ("Tanga Coordinator");
			smsRecipient[2].setTarget (contactNotangaDistrict.getValue ());
		
			smsRecipient[3] = new SmsRecipient();
			smsRecipient[3].setSmsRuleId (Integer.parseInt (contactNogeitaDistrict.getName ()));
			smsRecipient[3].setDescription ("Geita Coordinator");
			smsRecipient[3].setTarget (contactNogeitaDistrict.getValue ());
		
			smsRecipient[4] = new SmsRecipient();
			smsRecipient[4].setSmsRuleId (Integer.parseInt (contactNokitetoDistrict.getName ()));
			smsRecipient[4].setDescription ("Kiteto Coordinator");
			smsRecipient[4].setTarget (contactNokitetoDistrict.getValue ());
		
			smsRecipient[5] = new SmsRecipient();
			smsRecipient[5].setSmsRuleId (Integer.parseInt (contactNosimanjiroDistrict.getName ()));
			smsRecipient[5].setDescription ("Simanjiro Coordinator");
			smsRecipient[5].setTarget (contactNosimanjiroDistrict.getValue ());
		
			smsRecipient[6] = new SmsRecipient();
			smsRecipient[6].setSmsRuleId (Integer.parseInt (contactNombuluDistrict.getName ()));
			smsRecipient[6].setDescription ("Mbulu Coordinator");
			smsRecipient[6].setTarget (contactNombuluDistrict.getValue ());
		
			smsRecipient[7] = new SmsRecipient();
			smsRecipient[7].setSmsRuleId (Integer.parseInt (contactNohanangDistrict.getName ()));
			smsRecipient[7].setDescription ("Hanang Coordinator");
			smsRecipient[7].setTarget (contactNohanangDistrict.getValue ());
		
			smsRecipient[8] = new SmsRecipient();
			smsRecipient[8].setSmsRuleId (Integer.parseInt (contactNobabatiDistrict.getName ()));
			smsRecipient[8].setDescription ("Babati Coordinator");
			smsRecipient[8].setTarget (contactNobabatiDistrict.getValue ());
		
			smsRecipient[9] = new SmsRecipient();
			smsRecipient[9].setSmsRuleId (Integer.parseInt (contactNobabatiTownDistrict.getName ()));
			smsRecipient[9].setDescription ("Babati Town Coordinator");
			smsRecipient[9].setTarget (contactNobabatiTownDistrict.getValue ());
		
			try
			{
				service.updateSmsRecipient (smsRecipient, new AsyncCallback<Boolean> ()
				{

					@Override
					public void onFailure (Throwable caught)
					{
						load(false);				
					}

					@Override
					public void onSuccess (Boolean result)
					{				
						Window.alert ("Recipient Contacts Information updated Successfully!.");
					}
			
				});
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		
		}
		
		else
			Window.alert ("Please enter Valid Mobile Numbers.");

			
	}



	public void setCurrent ()
	{
	

		try
		{
			service.findSmsRecipients (new AsyncCallback<SmsRecipient[]> ()
			{

				@Override
				public void onFailure (Throwable caught)
				{
					// TODO Auto-generated method stub
					
				}

				@Override
				public void onSuccess (SmsRecipient[] result)
				{
					
					for(SmsRecipient i : result)
					{
						if (i.getSmsRuleId () == 0)
							contactNoRegionalCoordinator.setValue (i.getTarget ());
						else if (i.getSmsRuleId () == 1)
							contactNoMECoordinator.setValue (i.getTarget ());
						else if (i.getSmsRuleId () == 2)
							contactNotangaDistrict.setValue (i.getTarget ());
						else if (i.getSmsRuleId () == 3)
							contactNogeitaDistrict.setValue (i.getTarget ());
						else if (i.getSmsRuleId () == 4)
							contactNokitetoDistrict.setValue (i.getTarget ());
						else if (i.getSmsRuleId () == 5)
							contactNosimanjiroDistrict.setValue (i.getTarget ());
						else if (i.getSmsRuleId () == 6)
							contactNombuluDistrict.setValue (i.getTarget ());
						else if (i.getSmsRuleId () == 7)
							contactNohanangDistrict.setValue (i.getTarget ());
						else if (i.getSmsRuleId () == 8)
							contactNobabatiDistrict.setValue (i.getTarget ());
						else if (i.getSmsRuleId () == 9)
							contactNobabatiTownDistrict.setValue (i.getTarget ()); 
					}
					
				}
				
			});
		}
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void setRights (String menuName)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onClick (ClickEvent event)
	{
Widget sender = (Widget) event.getSource ();
		
		if (sender == saveButton)
		{
			load (true);
			saveData ();
			load(false);
		}
		if (sender == closeButton)
		{
			load(false);
		}	
		
	}

	private void load (boolean status)
	{
		flexTable.setVisible (!status);
		if (status)
			loading.show ();
		else
			loading.hide ();
		
	}

}
