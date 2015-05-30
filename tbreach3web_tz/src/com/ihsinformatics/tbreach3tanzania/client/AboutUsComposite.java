/* Copyright(C) 2015 Interactive Health Solutions, Pvt. Ltd.

This program is free software; you can redistribute it and/or modify it under the terms of the GNU General Public License as
published by the Free Software Foundation; either version 3 of the License (GPLv3), or any later version.
This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.

See the GNU General Public License for more details. You should have received a copy of the GNU General Public License along with this program; if not, write to the Interactive Health Solutions, info@ihsinformatics.com
You can also access the license on the internet at the address: http://www.gnu.org/licenses/gpl-3.0.html

Interactive Health Solutions, hereby disclaims all copyright interest in this program written by the contributors. */
/**
 * Pop up to display some information about Project 
 */

package com.ihsinformatics.tbreach3tanzania.client;


import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.RichTextArea;
import com.ihsinformatics.tbreach3tanzania.shared.TBRT;

public class AboutUsComposite extends PopupPanel
{

	FlexTable		flexTable		= new FlexTable ();
	RichTextArea	richTextArea	= new RichTextArea ();
	Label			topLabel		= new Label ("About " + TBRT.getProjectTitle ());
	Label			projectLabel	= new Label ("Project Name: " + TBRT.getProjectTitle ());
	Label			developerLabel	= new Label ("Developed by: Interactive Health Solutions (IHS) , Pakistan)");
	Image			ihsLogoImage	= new Image ("images/ihsLogo.png");
	Button			closeButton		= new Button ("Close");

	public AboutUsComposite ()
	{
		super (true);
		setStyleName ("body");
		setSize ("350px", "138px");
		setPreviewingAllNativeEvents (true);
		setAnimationEnabled (true);
		setGlassEnabled (true);
		generateComposite ();
		setProjectProperties ();
	}

	private void generateComposite ()
	{
		setWidget (flexTable);
		flexTable.setSize ("338px", "126px");
		FlexTable topFlexTable = new FlexTable ();
		flexTable.setWidget (0, 0, topFlexTable);
		topFlexTable.setSize ("90%", "100%");
		HorizontalPanel horizontalPanel = new HorizontalPanel ();
		topFlexTable.setWidget (0, 0, horizontalPanel);
		//Image TBReachLogoImage = new Image ((String) null);
		//horizontalPanel.add (TBReachLogoImage);
		topLabel.setWordWrap (false);
		topLabel.setStyleName ("title");
		topFlexTable.setWidget (0, 5, topLabel);
		topFlexTable.setWidget(3,5,projectLabel);
		topFlexTable.setWidget(4,5,developerLabel);
		FlexTable middleFlexTable = new FlexTable ();
		flexTable.setWidget (1, 0, middleFlexTable);
		middleFlexTable.setSize ("100%", "100%");
		middleFlexTable.setWidget (0, 0, ihsLogoImage);
		ihsLogoImage.setSize ("50%", "50%");
		middleFlexTable.getRowFormatter ().setVerticalAlign (0, HasVerticalAlignment.ALIGN_MIDDLE);
		middleFlexTable.getCellFormatter ().setHorizontalAlignment (0, 0, HasHorizontalAlignment.ALIGN_CENTER);
		middleFlexTable.getCellFormatter ().setHorizontalAlignment (0, 1, HasHorizontalAlignment.ALIGN_CENTER);
		FlexTable bottomFlexTable = new FlexTable ();
		flexTable.setWidget (2, 0, bottomFlexTable);
		bottomFlexTable.setSize ("100%", "100%");
		closeButton.addClickHandler (new ClickHandler ()
		{
			public void onClick (ClickEvent event)
			{
				MainMenuComposite.clear ();
			}
		});
		bottomFlexTable.setWidget (0, 0, closeButton);
		bottomFlexTable.getRowFormatter ().setVerticalAlign (0, HasVerticalAlignment.ALIGN_MIDDLE);
		bottomFlexTable.getCellFormatter ().setHorizontalAlignment (0, 0, HasHorizontalAlignment.ALIGN_CENTER);
		bottomFlexTable.getCellFormatter ().setVerticalAlignment (0, 0, HasVerticalAlignment.ALIGN_MIDDLE);
		flexTable.getRowFormatter ().setVerticalAlign (2, HasVerticalAlignment.ALIGN_MIDDLE);
		flexTable.getCellFormatter ().setHorizontalAlignment (2, 0, HasHorizontalAlignment.ALIGN_CENTER);
		flexTable.getCellFormatter ().setHorizontalAlignment (1, 0, HasHorizontalAlignment.ALIGN_CENTER);
		flexTable.getCellFormatter ().setHorizontalAlignment (0, 0, HasHorizontalAlignment.ALIGN_CENTER);
	}

	private void setProjectProperties ()
	{
		StringBuilder about = new StringBuilder ();
		richTextArea.setTitle ("About " + TBRT.getProjectTitle ());
		about.append ("Project: TB REACH 3 TANZANIA");
		about.append ("\n");
		about.append ("\n");
		about.append ("Developed by: Interactive Health Solutions (IHS) , Pakistan)");
		about.append ("");
		richTextArea.setText (about.toString ());
	}
}
