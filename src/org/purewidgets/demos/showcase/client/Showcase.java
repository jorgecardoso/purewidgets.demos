package org.purewidgets.demos.showcase.client;

import java.util.ArrayList;

import org.purewidgets.client.application.PDApplication;
import org.purewidgets.client.application.PDApplicationLifeCycle;
import org.purewidgets.client.im.WidgetManager;
import org.purewidgets.client.widgets.PdButton;
import org.purewidgets.client.widgets.PdCheckin;
import org.purewidgets.client.widgets.PdDownload;
import org.purewidgets.client.widgets.PdTextBox;
import org.purewidgets.client.widgets.PdListBox;
import org.purewidgets.client.widgets.PdUpload;
import org.purewidgets.shared.events.ActionEvent;
import org.purewidgets.shared.events.ActionListener;
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
public class Showcase implements PDApplicationLifeCycle, EntryPoint, ActionListener{

	
	@Override
	public void onModuleLoad() {
		PDApplication.load(this, "WidgetShowcase");
	}

	@Override
	public void onPDApplicationLoaded(PDApplication pdApplication) {
		String page = Window.Location.getPath();
		if ( page.contains("admin.html") ) {
			//Admin.run();
			return;
		}
		
		
		final TabPanel tabPanel = new TabPanel();
		
		tabPanel.addStyleName("main");
		RootPanel.get().add(tabPanel);
		
		
		/* Button */
		FlowPanel buttonPanel = new FlowPanel();
		tabPanel.add(buttonPanel, "Button");
		setPanelStyle(buttonPanel);
		
		PdButton like1 = new PdButton("btn11", "Like");
		
		like1.getWidgetOptions().get(0).setIconUrl("http://cdn.macrumors.com/article/2010/09/03/145454-itunes_10_icon.jpg");
		like1.getWidgetOptions().get(0).setSuggestedReferenceCode("myb");
		like1.setLongDescription("Video of Everdith Landrau at TEDxFranklinStreet");
		buttonPanel.add(like1);
		
		
		/* Textbox */
		FlowPanel textboxPanel = new FlowPanel();
		tabPanel.add(textboxPanel, "TextBox");
		Showcase.this.setPanelStyle(textboxPanel);
		
		PdTextBox tb1 = new PdTextBox("txt1", "Send text", null);
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
		PdListBox lb1 = new PdListBox("poll-1", "On average, how many times to you go to the movies?", l);
		lb1.setShortDescription("Vote");
		lb1.setLongDescription("On average, how many times to you go to the movies?");
		listboxPanel.add(lb1);

		/* upload */
		FlowPanel uploadPanel = new FlowPanel();
		tabPanel.add(uploadPanel, "Upload");
		this.setPanelStyle(uploadPanel);
		
		PdUpload guiUpload = new PdUpload("uploadsomething", "Upload");
		guiUpload.addActionListener(this);
		uploadPanel.add(guiUpload);
		
		
		/* download */
		FlowPanel downloadPanel = new FlowPanel();
		tabPanel.add(downloadPanel, "Download");
		this.setPanelStyle(downloadPanel);
		
		PdDownload guiDownload = new PdDownload("download-id", "Download", "http://jorge");
		guiDownload.addActionListener(this);
		downloadPanel.add(guiDownload);
		org.purewidgets.demos.showcase.client.Resources.INSTANCE.css().ensureInjected();
		
		/*
		 * Checkin
		 */
		FlowPanel checkinPanel = new FlowPanel();
		tabPanel.add(checkinPanel, "Checkin");
		this.setPanelStyle(checkinPanel);
		PdCheckin checkin = new PdCheckin();
		checkinPanel.add(checkin);
		
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
