/* Copyright(C) 2015 Interactive Health Solutions, Pvt. Ltd.

This program is free software; you can redistribute it and/or modify it under the terms of the GNU General Public License as
published by the Free Software Foundation; either version 3 of the License (GPLv3), or any later version.
This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.

See the GNU General Public License for more details. You should have received a copy of the GNU General Public License along with this program; if not, write to the Interactive Health Solutions, info@ihsinformatics.com
You can also access the license on the internet at the address: http://www.gnu.org/licenses/gpl-3.0.html

Interactive Health Solutions, hereby disclaims all copyright interest in this program written by the contributors. */
package com.ihsinformatics.tbreach3tanzania.client;

import java.util.ArrayList;
import java.util.List;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DecoratedPopupPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.NoSelectionModel;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.ihsinformatics.tbreach3tanzania.shared.TBRT;
import com.ihsinformatics.tbreach3tanzania.shared.model.AlertSentGeneXpertResults;

@SuppressWarnings("deprecation")
public class genexpert_ResultComposite extends Composite
{    
	
	private static ServerServiceAsync				service							= GWT.create (ServerService.class);

	private List<AlertSentGeneXpertResults> 		list							= new ArrayList<AlertSentGeneXpertResults>();

	private FlexTable								flexTable						= new FlexTable ();
	private CellTable<AlertSentGeneXpertResults> 	table 							= new CellTable<AlertSentGeneXpertResults>();
	
	private SimplePager 							simplePager 					= new SimplePager();
	
	private Label									lblTbReachGeneXpertResult		= new Label (TBRT.getProjectTitle () + " GeneXpert Results");

	genexpert_ResultComposite(){
		
		initWidget (flexTable);
		flexTable.setSize ("100%", "100%");
		
		lblTbReachGeneXpertResult.setStyleName ("title");
		
		// Create name column.
	    TextColumn<AlertSentGeneXpertResults> patientIdColumn = new TextColumn<AlertSentGeneXpertResults>() {
	      @Override
	      public String getValue(AlertSentGeneXpertResults gxr) {
	        return gxr.patientId;
	      }
	    };
	    TextColumn<AlertSentGeneXpertResults> sputumTestIdColumn = new TextColumn<AlertSentGeneXpertResults>() {
		  @Override
		  public String getValue(AlertSentGeneXpertResults gxr) {
		      return gxr.sputumTestId;
		    }
		  };
		TextColumn<AlertSentGeneXpertResults> laboratoryIdColumn = new TextColumn<AlertSentGeneXpertResults>() {
		  @Override
		  public String getValue(AlertSentGeneXpertResults gxr) {
			  return gxr.laboratoryId;
			}
		  };
		TextColumn<AlertSentGeneXpertResults> collectedByColumn = new TextColumn<AlertSentGeneXpertResults>() {
		  @Override
		  public String getValue(AlertSentGeneXpertResults gxr) {
			  return gxr.collectedBy;
			}
		  };
		TextColumn<AlertSentGeneXpertResults> dateSubmittedColumn = new TextColumn<AlertSentGeneXpertResults>() {
		  @Override
		  public String getValue(AlertSentGeneXpertResults gxr) {
			  return gxr.dateSubmitted;
			}
		  };
		TextColumn<AlertSentGeneXpertResults> dateTestedColumn = new TextColumn<AlertSentGeneXpertResults>() {
		  @Override
		  public String getValue(AlertSentGeneXpertResults gxr) {
			  return gxr.dateTested;
			}
		  };
		TextColumn<AlertSentGeneXpertResults> isPositiveColumn = new TextColumn<AlertSentGeneXpertResults>() {
		  @Override
		  public String getValue(AlertSentGeneXpertResults gxr) {
				  if (gxr.isPositive) return "True";
				  return "False";
			 }
		   };
		TextColumn<AlertSentGeneXpertResults> drugResistenceColumn = new TextColumn<AlertSentGeneXpertResults>() {
		  @Override
		  public String getValue(AlertSentGeneXpertResults gxr) {
					return gxr.drugResistance;
				}
			 };
		TextColumn<AlertSentGeneXpertResults> remarksColumn = new TextColumn<AlertSentGeneXpertResults>() {
			@Override
			public String getValue(AlertSentGeneXpertResults gxr) {
				  return gxr.remarks;
				}
			};
	   TextColumn<AlertSentGeneXpertResults> geneXpertResultColumn = new TextColumn<AlertSentGeneXpertResults>() {
			@Override
			public String getValue(AlertSentGeneXpertResults gxr) {
				   return gxr.geneXpertResult;
				}
			};
	  TextColumn<AlertSentGeneXpertResults> testIdColumn = new TextColumn<AlertSentGeneXpertResults>() {
		   @Override
		   public String getValue(AlertSentGeneXpertResults gxr) {
				  return String.valueOf (gxr.testId);
				}
			};		
						

	    // Add the columns to Table.
	    table.addColumn(patientIdColumn, "PatientId");
	    table.addColumn(sputumTestIdColumn, "Sputum Test Id");
	    table.addColumn(testIdColumn, "Test Id");
	    table.addColumn(laboratoryIdColumn, "Laboratory Id");
	    table.addColumn(collectedByColumn, "Collected By");
	    table.addColumn(dateSubmittedColumn, "Date Submitted");
	    table.addColumn(dateTestedColumn, "Date Tested");
	    table.addColumn(isPositiveColumn, "is Positive");
	    table.addColumn(drugResistenceColumn, "Drug Resistence");
	    table.addColumn(remarksColumn, "Remarks");
	    table.addColumn(geneXpertResultColumn, "GeneXpert Result");
	  
	   
	    // fill data in table
		service.getGeneXpertResultAsList (new AsyncCallback<AlertSentGeneXpertResults[]> ()
		{

			@Override
			public void onFailure (Throwable caught)
			{
				
			}

			@Override
			public void onSuccess (AlertSentGeneXpertResults[] result)
			{
				
				for(AlertSentGeneXpertResults gxr : result)
					list.add (gxr);
				
			    simplePager.setDisplay(table);
			    simplePager.setPageSize(20);

			    // Create a data provider.
			    ListDataProvider<AlertSentGeneXpertResults> dataProvider = new ListDataProvider<AlertSentGeneXpertResults>();

			    // Connect the table to the data provider.
			    dataProvider.addDataDisplay(table);

			    // Add the data to the data provider, which automatically pushes it to the
			    // Table.
			    List<AlertSentGeneXpertResults> list1 = dataProvider.getList();
			    for (AlertSentGeneXpertResults result1 : list) {
			      list1.add(result1);
			    }
			}
		
		});
		
		 // Add a selection model to handle user selection.
	      final NoSelectionModel<AlertSentGeneXpertResults> selectionModel = new NoSelectionModel<AlertSentGeneXpertResults>();
	      table.setSelectionModel(selectionModel);
	      selectionModel.addSelectionChangeHandler(
	      new SelectionChangeEvent.Handler() {
	         public void onSelectionChange(SelectionChangeEvent event) {
	            AlertSentGeneXpertResults selected = selectionModel.getLastSelectedObject();
	            if (selected != null) {
	     
	            	//pop up for the record clicked on
	            	int i =0;
	            	int x=0,y =0;
	            	for(; i<table.getVisibleItemCount (); i++){
	            		if (table.getVisibleItem (i)==selected){
	            		
	            			x = table.getRowElement(i).getCells().getItem(0).getAbsoluteLeft();
	            			y = table.getRowElement(i).getCells().getItem(0).getAbsoluteTop();
	            		
	            		}
	            	}
	            	
	            	MyPopup p = new MyPopup(selected);
	                int left = x + 20;
	                int top = y + 20;
	                p.setPopupPosition(left, top);
	                p.setGlassEnabled (true);
	                p.show();
	                
	            }
	         }
	      });
		
		flexTable.setWidget (0, 0, lblTbReachGeneXpertResult);
	    flexTable.setWidget (1, 0, table);
	    flexTable.setWidget (2, 0, simplePager);
	    
	    flexTable.getCellFormatter ().setHorizontalAlignment (0, 0, HasHorizontalAlignment.ALIGN_CENTER);
	    flexTable.getCellFormatter ().setHorizontalAlignment (2, 0, HasHorizontalAlignment.ALIGN_CENTER);		
	}

	
	//pop up class
	private static class MyPopup extends DecoratedPopupPanel {

	      public MyPopup(AlertSentGeneXpertResults gxr) {       
	         super(false);

	         FlexTable mainFlexTable = new FlexTable();
	         
	         //Title
	         Label title = new Label("Detailed GeneXpert Result");
	         title.setStyleName ("popupTitleLabel");
	         mainFlexTable.setWidget (0, 0, title);
	         mainFlexTable.getCellFormatter ().setHorizontalAlignment (0, 0, HasHorizontalAlignment.ALIGN_CENTER);

	         //Close Button
	         ClickListener listener = new ClickListener()
	         {
				@Override
				public void onClick (Widget sender)
				{
					hide();					
				}
	         };
	         Button button = new Button("Close", listener);
	         button.setStyleName ("popupButton");
	         button.setStylePrimaryName ("popupButton");
	         mainFlexTable.setWidget (2, 0, button);
	         mainFlexTable.getCellFormatter ().setHorizontalAlignment (2, 0, HasHorizontalAlignment.ALIGN_CENTER);
	         
	      
	         //Test Details!
	         FlexTable detailsFlexTable = new FlexTable();
	         detailsFlexTable.setText (0, 0, "Patient Id:");
	         detailsFlexTable.setText (1, 0, "Sputum Test Id:");
	         detailsFlexTable.setText (2, 0, "Test Id:");
	         detailsFlexTable.setText (0, 1, gxr.patientId);
	         detailsFlexTable.setText (1, 1, gxr.sputumTestId);
	         detailsFlexTable.setText (2, 1, String.valueOf (gxr.testId));
	         
	         detailsFlexTable.getCellFormatter ().setStyleName (0, 0, "result");
	         detailsFlexTable.getCellFormatter ().setStyleName (1, 0, "result");
	         detailsFlexTable.getCellFormatter ().setStyleName (2, 0, "result");
	         
	         Grid idsGrid = new Grid(1,1);
	         idsGrid.setWidget (0, 0, detailsFlexTable);
	         idsGrid.setStyleName ("grid");
	         
	         //detailed result
	         FlexTable resultFlexTable = new FlexTable();
	         resultFlexTable.setText(0, 0, "MTB Burden:");  
	         resultFlexTable.setText(1, 0, "ProbeResultA:");  
	         resultFlexTable.setText(2, 0, "ProbeResultB:");  
	         resultFlexTable.setText(3, 0,  "ProbeResultC:");  
	         resultFlexTable.setText(4, 0,  "ProbeResultD:");
	         resultFlexTable.setText(5, 0,  "ProbeResultE:");
	         resultFlexTable.setText(6, 0,  "ProbeResultSPC:");
	         resultFlexTable.setText(7, 0,  "ProbeCtA:");  
	         resultFlexTable.setText(8, 0,  "ProbeCtB:");  
	         resultFlexTable.setText(9, 0,  "ProbeCtC:");  
	         resultFlexTable.setText(10, 0,  "ProbeCtD:");
	         resultFlexTable.setText(11, 0,  "ProbeCtE:");
	         resultFlexTable.setText(12, 0,  "ProbeCtSPC:");
	         resultFlexTable.setText(13, 0, "ProbeEndptA:");  
	         resultFlexTable.setText(14, 0, "ProbeEndptB:");  
	         resultFlexTable.setText(15, 0,  "ProbeEndptC:");  
	         resultFlexTable.setText(16, 0, "ProbeEndptD:");
	         resultFlexTable.setText(17, 0,  "ProbeEndptE:");
	         resultFlexTable.setText(18, 0,  "ProbeEndptSPC:");
	         
	         resultFlexTable.setText(0, 1, gxr.mtbBurden); 
	         resultFlexTable.setText(1, 1, gxr.probeResultA);  
	         resultFlexTable.setText(2, 1, gxr.probeResultB);  
	         resultFlexTable.setText(3, 1, gxr.probeResultC);  
	         resultFlexTable.setText(4, 1, gxr.probeResultD);
	         resultFlexTable.setText(5, 1, gxr.probeResultE);
	         resultFlexTable.setText(6, 1, gxr.probeResultSPC);
	         resultFlexTable.setText(7, 1, String.valueOf (gxr.probeCtA));  
	         resultFlexTable.setText(8, 1, String.valueOf (gxr.probeCtB));  
	         resultFlexTable.setText(9, 1, String.valueOf (gxr.probeCtC));  
	         resultFlexTable.setText(10, 1, String.valueOf (gxr.probeCtD));
	         resultFlexTable.setText(11, 1,String.valueOf (gxr.probeCtE));
	         resultFlexTable.setText(12, 1,String.valueOf (gxr.probeCtSPC));
	         resultFlexTable.setText(13, 1,String.valueOf (gxr.probeEndptA));  
	         resultFlexTable.setText(14, 1,String.valueOf (gxr.probeEndptB));  
	         resultFlexTable.setText(15, 1,String.valueOf (gxr.probeEndptC));  
	         resultFlexTable.setText(16, 1,String.valueOf (gxr.probeEndptD));
	         resultFlexTable.setText(17, 1,String.valueOf (gxr.probeEndptE));
	         resultFlexTable.setText(18, 1,String.valueOf (gxr.probeEndptSPC));
	         
	         resultFlexTable.getCellFormatter ().setStyleName (0, 0, "result");
	         resultFlexTable.getCellFormatter ().setStyleName (1, 0, "result");
	         resultFlexTable.getCellFormatter ().setStyleName (2, 0, "result");
	         resultFlexTable.getCellFormatter ().setStyleName (3, 0, "result");
	         resultFlexTable.getCellFormatter ().setStyleName (4, 0, "result");
	         resultFlexTable.getCellFormatter ().setStyleName (5, 0, "result");
	         resultFlexTable.getCellFormatter ().setStyleName (6, 0, "result");
	         resultFlexTable.getCellFormatter ().setStyleName (7, 0, "result");
	         resultFlexTable.getCellFormatter ().setStyleName (8, 0, "result");
	         resultFlexTable.getCellFormatter ().setStyleName (9, 0, "result");
	         resultFlexTable.getCellFormatter ().setStyleName (10, 0, "result");
	         resultFlexTable.getCellFormatter ().setStyleName (11, 0, "result");
	         resultFlexTable.getCellFormatter ().setStyleName (12, 0, "result");
	         resultFlexTable.getCellFormatter ().setStyleName (13, 0, "result");
	         resultFlexTable.getCellFormatter ().setStyleName (14, 0, "result");
	         resultFlexTable.getCellFormatter ().setStyleName (15, 0, "result");
	         resultFlexTable.getCellFormatter ().setStyleName (16, 0, "result");
	         resultFlexTable.getCellFormatter ().setStyleName (17, 0, "result");
	         resultFlexTable.getCellFormatter ().setStyleName (18, 0, "result");
	         
	         Grid detailedResultGrid = new Grid(1,3);
	         detailedResultGrid.setWidget (0, 2, resultFlexTable);
	         
	         //extradetails
	         Grid extraGrid = new Grid(1,1);
	         FlexTable extraFlexTable = new FlexTable();
	         extraFlexTable.setText (0, 0, "Instrument Id:");
	         extraFlexTable.setText (1, 0, "Module Id:");
	         extraFlexTable.setText (2, 0, "Reagent Lot Id:");
	         extraFlexTable.setText (3, 0, "PC Id:");
	         extraFlexTable.setText (4, 0, "Cartridge Id:");
	         extraFlexTable.setText (5, 0, "Cartridge Expiry Date:");
	         extraFlexTable.setText (6, 0, "Error Code:");
	         extraFlexTable.setText (7, 0, "Opertator Id:");
	         extraFlexTable.setText (0, 1, gxr.instrumentId);
	         extraFlexTable.setText (1, 1, gxr.moduleId);
	         extraFlexTable.setText (2, 1, gxr.reagentLotId);
	         extraFlexTable.setText (3, 1, gxr.pcId);
	         extraFlexTable.setText (4, 1, gxr.cartridgeId);
	         extraFlexTable.setText (5, 1, gxr.cartridgeExpiryDate);
	         extraFlexTable.setText (6, 1, String.valueOf (gxr.errorCode));
	         extraFlexTable.setText (7, 1, gxr.operatorId);
	         
	         extraFlexTable.getCellFormatter ().setStyleName (0, 0, "result");
	         extraFlexTable.getCellFormatter ().setStyleName (1, 0, "result");
	         extraFlexTable.getCellFormatter ().setStyleName (2, 0, "result");
	         extraFlexTable.getCellFormatter ().setStyleName (3, 0, "result");
	         extraFlexTable.getCellFormatter ().setStyleName (4, 0, "result");
	         extraFlexTable.getCellFormatter ().setStyleName (5, 0, "result");
	         extraFlexTable.getCellFormatter ().setStyleName (6, 0, "result");
	         extraFlexTable.getCellFormatter ().setStyleName (7, 0, "result");
	         
	         extraGrid.setWidget (0, 0, extraFlexTable);
	         extraGrid.setStyleName ("grid");
	         
	  
	         //Adding grids in scroll bar
	         FlexTable spFlexTable = new FlexTable();
	         spFlexTable.setWidget (0, 0, idsGrid);
	         spFlexTable.setWidget (1, 0, detailedResultGrid);
	         spFlexTable.setWidget (2, 0, extraGrid);
	         
	         ScrollPanel sp = new ScrollPanel();
	         sp.setWidget (spFlexTable);
	         sp.setSize ("220px", "300px");
	         mainFlexTable.setWidget (1, 0, sp);
	         
	         add(mainFlexTable);
	         
	      }
	   }

}



