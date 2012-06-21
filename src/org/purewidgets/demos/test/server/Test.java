package org.purewidgets.demos.test.server;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.purewidgets.server.application.PublicDisplayApplication;
import org.purewidgets.server.application.ApplicationLifeCycle;
import org.purewidgets.shared.events.ActionEvent;
import org.purewidgets.shared.logging.Log;
import org.purewidgets.shared.widgets.ListBox;
import org.purewidgets.shared.widgets.TextBox;
import org.purewidgets.shared.widgets.Upload;

public class Test extends HttpServlet implements ApplicationLifeCycle {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	PublicDisplayApplication app;
	HttpServletRequest req;
	HttpServletResponse resp;
	
	long clicks;
	
	String message;
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) {
		this.req = req;
		this.resp = resp;
		
		PublicDisplayApplication.load(req, this, "Test");
	}
	
	@Override
	public void loaded(PublicDisplayApplication application) {
		this.app = application;
		message = "";
	}
	
	/**
	 * Called only on the first time
	 */
	@Override
	public void setup() {
		
		Log.debug(this, "Setup");
		
	}
	
	@Override
	public void start() {
		ArrayList<String> l = new ArrayList<String>();
		l.add("op 1");
		l.add("op 2");
		ListBox list = new ListBox("listid", "Lista xpto", l);
		list.addActionListener(this);
		this.app.addWidget(list, true);
		
		//list.sendToServer();
		Upload upload = new Upload("uploadsomething", "Upload");
		upload.addActionListener(this);
		//upload.sendToServer();
	}
	
	@Override
	public void finish() {
		Log.debug(this, "Finish");

		//for (Widget w : WidgetManager.get().getWidgetList()) {
			
				//message += w.toDebugString() + ";";
			
		//}
		
		
		try {
			resp.getWriter().write(message);
		} catch (IOException e) {
			Log.error(this, "Could not write the Http response.");
			e.printStackTrace();
		}
	}

	@Override
	public void onAction(ActionEvent<?> ae) {
		//ae = (ActionEvent<? extends Widget>)ae;
		message += " onAction: ";
		Log.debug(this, ae.toString());
//		Widget source = (Widget)ae.getSource();
//		
//		if ( source.getWidgetId().equals("button_1") ) {
//			this.clicks++;
//			
//			if ( this.clicks > 1 && this.clicks < 3) {
//				TextBox text = new TextBox("txt_1", "Gimme text");
//				app.addWidget(text);
//			}
//		} else if ( source.getWidgetId().equals("txt_1")) {
//			message += ae.getParam() + "\n"; 
//				
//		} else if ( source.getWidgetId().equals("uploadsomething") ) {
//			message += ae.getParam() + "\n"; 
//		}
		
		
	}
}
