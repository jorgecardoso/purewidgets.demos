package org.purewidgets.demos.helloworld.client;


import org.purewidgets.client.application.PDApplication;
import org.purewidgets.client.application.PDApplicationLifeCycle;
import org.purewidgets.client.widgets.PdButton;
import org.purewidgets.client.widgets.PdWidget;
import org.purewidgets.shared.events.ActionEvent;
import org.purewidgets.shared.events.ActionListener;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;

public class HelloWorld implements PDApplicationLifeCycle, EntryPoint {
	
	@Override
	public void onModuleLoad() {
		
		// Give a name to the application and initialize some 
		// background processes.
		PDApplication.load(this, "MyHelloWorld");
	}

	@Override
	public void onPDApplicationLoaded(PDApplication pdApplication) {
		
		// Create a button widget giving it an id and a label
		PdButton pdButton = new PdButton("myButtonId", "Activate me");
		
		
		// Register the action listener for the list 
		pdButton.addActionListener(new ActionListener() {
			@Override
			public void onAction(ActionEvent<?> e) {
				
				// Get the widget that triggered the event.		 
				PdWidget source = (PdWidget) e.getSource();

				// If the button was activated, do something...
				if ( source.getWidgetId().equals("myButtonId") ) {
					RootPanel.get().add(new Label("Button activated"));
				}
			}
		});
		
		
		// Add the graphical representation of the button to the browser
		// window.
		RootPanel.get("main").add(pdButton);
	}
}
