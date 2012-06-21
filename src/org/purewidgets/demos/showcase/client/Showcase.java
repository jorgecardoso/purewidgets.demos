package org.purewidgets.demos.showcase.client;




import java.util.ArrayList;

import org.purewidgets.client.application.PublicDisplayApplication;
import org.purewidgets.client.application.PublicDisplayApplicationLoadedListener;
import org.purewidgets.client.im.WidgetManager;
import org.purewidgets.client.widgets.GuiButton;
import org.purewidgets.client.widgets.GuiCheckin;
import org.purewidgets.client.widgets.GuiDownload;
import org.purewidgets.client.widgets.GuiTextBox;
import org.purewidgets.client.widgets.GuiListBox;
import org.purewidgets.client.widgets.GuiUpload;
import org.purewidgets.shared.events.ActionEvent;
import org.purewidgets.shared.events.ActionListener;
import org.purewidgets.shared.im.Application;
import org.purewidgets.shared.logging.Log;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TabPanel;


/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Showcase implements PublicDisplayApplicationLoadedListener, EntryPoint, ActionListener{

	
	@Override
	public void onModuleLoad() {
		
		PublicDisplayApplication.load(this, "WidgetShowcase", false);
		
	}

	@Override
	public void onApplicationLoaded() {
		String page = Window.Location.getPath();
		if ( page.contains("admin.html") ) {
			//Admin.run();
			return;
		}
		
		
		WidgetManager.get().setAutomaticInputRequests(true);
		
		TabPanel tabPanel = new TabPanel();
		
		tabPanel.addStyleName("main");
		RootPanel.get().add(tabPanel);
		
		
		/* Button */
		FlowPanel buttonPanel = new FlowPanel();
		tabPanel.add(buttonPanel, "Button");
		setPanelStyle(buttonPanel);
		
		GuiButton like1 = new GuiButton("btn1", "Like");
		like1.getWidgetOptions().get(0).setSuggestedReferenceCode("myb");
		like1.setLongDescription("Video of Everdith Landrau at TEDxFranklinStreet");
		
		/*
		 * We made changes that need to be sent to the server
		 */
		like1.sendToServer();
		buttonPanel.add(like1);
		
		//GuiButton like2 = new GuiButton("btn2", "Like");
		//like1.setLongDescription("Video of Sherry Turkle: Connected, but alone?");
		
		/* Textbox */
		FlowPanel textboxPanel = new FlowPanel();
		tabPanel.add(textboxPanel, "TextBox");
		this.setPanelStyle(textboxPanel);
		
		GuiTextBox tb1 = new GuiTextBox("txt1", "Send text");
		tb1.setWidth("400px");
		tb1.setLongDescription("Contribute some tags to the tag cloud.");
		textboxPanel.add(tb1);
		
		
		
		/* listbox */
		FlowPanel listboxPanel = new FlowPanel();
		tabPanel.add(listboxPanel, "ListBox");
		this.setPanelStyle(listboxPanel);
		
		ArrayList<String> l = new ArrayList<String>();
		l.add("I don't go");
		l.add("Once a week");
		l.add("Twice a week");
		GuiListBox lb1 = new GuiListBox("poll-1", "On average, how many times to you go to the movies?", l);
		lb1.setShortDescription("Vote");
		lb1.setLongDescription("On average, how many times to you go to the movies?");
		listboxPanel.add(lb1);

		/* upload */
		FlowPanel uploadPanel = new FlowPanel();
		tabPanel.add(uploadPanel, "Upload");
		this.setPanelStyle(uploadPanel);
		
		GuiUpload guiUpload = new GuiUpload("uploadsomething", "Upload");
		guiUpload.addActionListener(this);
		uploadPanel.add(guiUpload);
		
		
		/* download */
		FlowPanel downloadPanel = new FlowPanel();
		tabPanel.add(downloadPanel, "Download");
		this.setPanelStyle(downloadPanel);
		
		GuiDownload guiDownload = new GuiDownload("download-id", "Download", "http://jorge");
		guiDownload.addActionListener(this);
		downloadPanel.add(guiDownload);
		
		/*
		 * Checkin
		 */
		GuiCheckin checkin = new GuiCheckin();
		
//		
//		GuiDownloadButton download = new GuiDownloadButton("download", "Download", "http://teste");
//		download.setLongDescription("Link to video Sherry Turkle: Connected, but alone?");
//	
	}

	/**
	 * @param buttonPanel
	 */
	private void setPanelStyle(Panel buttonPanel) {
		buttonPanel.setStyleName("centred");
		buttonPanel.getElement().getStyle().clearHeight();
		buttonPanel.getElement().getStyle().clearWidth();
	}

	@Override
	public void onAction(ActionEvent<?> e) {
		Log.error(this, "On Action");
		Image img = new Image((String)e.getParam());
		RootPanel.get().add(img);
		
	}
	
	
	
}
