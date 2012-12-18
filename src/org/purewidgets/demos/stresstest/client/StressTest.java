package org.purewidgets.demos.stresstest.client;

import java.util.ArrayList;
import java.util.Date;

import org.purewidgets.client.application.PDApplication;
import org.purewidgets.client.application.PDApplicationLifeCycle;
import org.purewidgets.client.im.json.InputResponseJson;
import org.purewidgets.client.widgets.PdButton;
import org.purewidgets.client.widgets.PdWidget;
import org.purewidgets.shared.im.WidgetInput;
import org.purewidgets.shared.logging.Log;

//
//import org.jorgecardoso.purewidgets.demo.placeinteraction.client.SightingService;
//import org.jorgecardoso.purewidgets.demo.placeinteraction.client.SightingServiceAsync;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window.Navigator;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.gwt.user.client.ui.RootPanel;


/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class StressTest implements PDApplicationLifeCycle, EntryPoint{
//	public static SightingServiceAsync sightingService;
	
	Timer timerAddDelete;
	Timer timerInput;
	
	ArrayList<PdWidget> widgets; 
	
	int widgetIdIndex;
	
	@Override
	public void onModuleLoad() {
		Date d = new Date();
		
		PDApplication.load(this, "StressTest");
	}
	
	@Override
	public void onPDApplicationLoaded(PDApplication pdApplication) {
			
				widgets = new ArrayList<PdWidget>();
				
				Log.debug(this, "Creating " + pdApplication.getParameterInt("numWidgets", 5) + " widgets.");
				
				for (int i = 0; i < pdApplication.getParameterInt("numWidgets", 5); i++) {
					
					PdButton b = new PdButton("button "+i, "Button "+i);
					b.setShortDescription("Test button " + i);
					b.setLongDescription("This is a test button ");
					RootPanel.get().add(b);
					
					widgets.add(b);
				}
				
				
				 
				for ( int i = 0; i < pdApplication.getParameterInt("numInputs", 1); i++ ) {
					new Timer() {
						@Override
						public void run() {
							input();
						}
					}.schedule(i*15000);
				}
				
				
				
		
	}
	
	private void input() {
		if (this.widgets.size() < 1 ) return;
		Log.debug("Sending input");
		
		String user = "StressTest"+(int)(Math.random()*100);

		int randomIndex = (int) (Math.random()*this.widgets.size());
		
		ArrayList<String>parameters = new ArrayList<String>();
		
		WidgetInput widgetInput = new WidgetInput();
		widgetInput.setUserId(user);
		widgetInput.setNickname(user);
		widgetInput.setInputMechanism("PlaceInteractionWebpage:"+Navigator.getUserAgent());
		widgetInput.setParameters(parameters);
		widgetInput.setWidgetId("button "+randomIndex);
		widgetInput.setWidgetOptionId("button "+randomIndex);
		widgetInput.setReferenceCode(this.widgets.get(randomIndex).getWidgetOptions().get(0).getReferenceCode());
		
		
		PDApplication.getCurrent().getInteractionManager().sendWidgetInput(
				PDApplication.getCurrent().getPlaceId(), 
				PDApplication.getCurrent().getApplicationId(), 
				PDApplication.getCurrent().getPlaceId(),
				widgetInput, 
				new AsyncCallback<InputResponseJson>() {

					@Override
					public void onSuccess(InputResponseJson returnValue) {
						Log.debug(this, "Sent! " +returnValue);
					}

					@Override
					public void onFailure(Throwable exception) {
						Log.debug(this, "An error ocurred.");
					}
			
		});		
		
		
	}
	
	private void addDelete() {
		/*
		 * Delete all current widgets
		 */
		for ( PdWidget widget : widgets ) {
			widget.removeFromServer();
		}
		
		widgets.clear();
		/*
		 * Add new ones
		 */
		
		for (int i = 0; i < 5; i++) {
			
			PdButton b = new PdButton("button "+widgetIdIndex, "Button "+widgetIdIndex);
			b.setShortDescription("Test button " + widgetIdIndex);
			widgetIdIndex++;
			
			widgets.add(b);
		}
		
	}

	
}
