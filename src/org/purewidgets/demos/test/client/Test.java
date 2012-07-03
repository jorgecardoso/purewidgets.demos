package org.purewidgets.demos.test.client;


import java.util.ArrayList;

import org.purewidgets.client.application.PDApplication;
import org.purewidgets.client.application.PDApplicationLifeCycle;
import org.purewidgets.client.im.WidgetManager;
import org.purewidgets.client.widgets.PdButton;
import org.purewidgets.client.widgets.PdDownload;
import org.purewidgets.client.widgets.PdTextBox;
import org.purewidgets.client.widgets.PdListBox;
import org.purewidgets.client.widgets.PdUpload;
import org.purewidgets.shared.events.ActionEvent;
import org.purewidgets.shared.events.ActionListener;
import org.purewidgets.shared.im.Application;
import org.purewidgets.shared.logging.Log;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.RootPanel;


/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Test implements PDApplicationLifeCycle, EntryPoint, ActionListener{
	
	String postUrl = "";
	String uploadId = "";
	
	PDApplication pdApplication;
	
	@Override
	public void onModuleLoad() {
		
		PDApplication.load(this, "Test");
		
		
	}

	@Override
	public void onPDApplicationLoaded(PDApplication pdApplication) {
		this.pdApplication = pdApplication;
		
		String page = Window.Location.getPath();
		if ( page.contains("admin.html") ) {
			Admin.run(pdApplication);
			return;
		}
		
		//WidgetManager.get().setAutomaticInputRequests(true);
		
		
		PdButton like1 = new PdButton("btn1", "Like");
		like1.setLongDescription("Video of Everdith Landrau at TEDxFranklinStreet");
		
		PdButton like2 = new PdButton("btn2", "Like");
		like1.setLongDescription("Video of Sherry Turkle: Connected, but alone?");
		
		PdTextBox tb1 = new PdTextBox("txt1", "Send text", null);
		tb1.setLongDescription("Contribute some tags to the tag cloud.");
		
		ArrayList<String> l = new ArrayList<String>();
		l.add("I don't go");
		l.add("Once a week");
		l.add("Twice a week");
		PdListBox lb1 = new PdListBox("poll-1", "On average, how many times to you go to the movies?", l);
		lb1.setShortDescription("Vote");
		lb1.setLongDescription("On average, how many times to you go to the movies?");
		RootPanel.get("content").add(lb1);

		l = new ArrayList<String>();
		l.add("One");
		l.add("Two");
		l.add("Three");
		PdListBox lb2 = new PdListBox("poll-2", "What is your favourite number?", l);
		lb2.setShortDescription("Vote");
		lb2.setLongDescription("What is your favourite number?");
		RootPanel.get("content").add(lb2);
		
		PdUpload guiUpload = new PdUpload("uploadsomething", "Upload");
		guiUpload.addActionListener(this);
		
		PdDownload download = new PdDownload("download", "Download", "http://teste");
		download.setLongDescription("Link to video Sherry Turkle: Connected, but alone?");
	
	}

	@Override
	public void onAction(ActionEvent<?> e) {
		Log.error(this, "On Action");
		Image img = new Image((String)e.getParam());
		RootPanel.get().add(img);
		
	}
	
	
	
}
