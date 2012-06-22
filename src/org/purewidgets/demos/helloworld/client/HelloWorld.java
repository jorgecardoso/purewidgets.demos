package org.purewidgets.demos.helloworld.client;


import java.util.ArrayList;

import org.purewidgets.client.application.PDApplication;
import org.purewidgets.client.application.PDApplicationLifeCycle;
import org.purewidgets.client.widgets.GuiListBox;
import org.purewidgets.shared.events.ActionEvent;
import org.purewidgets.shared.events.ActionListener;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;

public class HelloWorld implements PDApplicationLifeCycle, EntryPoint{
	
	@Override
	public void onModuleLoad() {
		/*
		 * Give a name to the application and initialize some 
		 * background processes.
		 */
		PDApplication.load(this, "MyHelloWorld");
	}

	@Override
	public void onPDApplicationLoaded(PDApplication pdApplication) {
		
		ArrayList<String> options = new ArrayList<String>();
		options.add("To be");
		options.add("Not to be");
		GuiListBox listbox = new GuiListBox("listboxId", "To be, or not to be",  options);
		listbox.setShortDescription("Choose!");
		
		
		/*
		 * Register the action listener for the list 
		 */
		listbox.addActionListener(new ActionListener() {
			@Override
			public void onAction(ActionEvent<?> e) {
				if ( e.getSelection().getShortDescription().equalsIgnoreCase("to be") ){
					RootPanel.getBodyElement().getStyle().setBackgroundColor("#ffffff");
				} else {
					RootPanel.getBodyElement().getStyle().setBackgroundColor("#000000");
				}
				
			}
		});
		
		/*
		 * Add the graphical representation of the listbox to the browser
		 * window.
		 */
		RootPanel.get("main").add(listbox);
		
	}
}
