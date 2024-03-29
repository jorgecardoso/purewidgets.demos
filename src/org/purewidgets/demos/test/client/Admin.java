package org.purewidgets.demos.test.client;




import java.util.ArrayList;

import org.purewidgets.client.application.PDApplication;
import org.purewidgets.client.storage.ServerStorage;
import org.purewidgets.shared.logging.Log;
import org.purewidgets.shared.storage.KeyValue;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;


/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Admin {
	
	static String options[] = {"allowusertags", "placetags"};
	
	static ArrayList<TextBox> values;
	
	static ServerStorage rs;

	public static void  run(PDApplication pdApplication) {
		rs = pdApplication.getRemoteStorage();
		
		values = new ArrayList<TextBox>();
		
		VerticalPanel verticalPanel = new VerticalPanel();
		
		
		for ( String option: options ) {
			HorizontalPanel horizontalPanel = new HorizontalPanel();
			Label l = new Label(option);
			TextBox textbox = new TextBox();
			values.add(textbox);
			
			horizontalPanel.add(l);
			horizontalPanel.add(textbox);
			
			verticalPanel.add(horizontalPanel);
		}
		RootPanel.get().add(verticalPanel);
		
		Button save = new Button("Save");
		
		RootPanel.get().add(save);
		
		save.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				saveOptions();
				
			}
			
		});
		
		loadOptions();
		
	}
	
	public static void loadOptions() {
		
		for (int i = 0; i < options.length;  i++) {
			rs.getString(options[i],  new AsyncCallback<KeyValue>() {

				@Override
				public void onFailure(Throwable caught) {
					Log.debug("Failed");
					Log.debug(caught.getMessage());
					RootPanel.get().add(new Label("Failed"));
					
				}

				@Override
				public void onSuccess(KeyValue result) {
					
					
					for (int j = 0; j < options.length; j++) {
						if ( options[j].equals(result.getKey()) ) {
							TextBox t = values.get(j);
							t.setText(result.getValue());
									
						}
					}
					Log.debug("ok:" + result.getValue());
					//RootPanel.get().add(new Label("ok: " + result));
				}
				
			});			
			
		}		
	}
	
	
	public static void saveOptions() {
		
		
		for (int i = 0; i < options.length;  i++) {
			String text = values.get(i).getText();
			rs.setString(options[i], text, new AsyncCallback<Void>() {

				@Override
				public void onFailure(Throwable caught) {
					Log.debug("Failed");
					Log.debug(caught.getMessage());
					
					
				}

				@Override
				public void onSuccess(Void result) {
					Log.debug("ok");
					
				}
				
			});
		}
		
		/*
		
		rs.getString("teste",  new AsyncCallback<String>() {

			@Override
			public void onFailure(Throwable caught) {
				Log.debug("Failed");
				Log.debug(caught.getMessage());
				RootPanel.get().add(new Label("Failed"));
				
			}

			@Override
			public void onSuccess(String result) {
				Log.debug("ok:" + result);
				RootPanel.get().add(new Label("ok: " + result));
			}
			
		});
		*/
	
	}
}
