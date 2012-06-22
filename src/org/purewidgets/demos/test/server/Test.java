package org.purewidgets.demos.test.server;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.purewidgets.server.application.PDApplication;
import org.purewidgets.server.application.PDApplicationLifeCycle;
import org.purewidgets.shared.events.ActionEvent;
import org.purewidgets.shared.events.ActionListener;
import org.purewidgets.shared.im.Widget;
import org.purewidgets.shared.logging.Log;
import org.purewidgets.shared.widgets.ListBox;
import org.purewidgets.shared.widgets.Upload;

public class Test extends HttpServlet implements PDApplicationLifeCycle, ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	PDApplication app;
	HttpServletRequest req;
	HttpServletResponse resp;
	
	long clicks;
	
	String message;
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) {
		this.req = req;
		this.resp = resp;
		
		PDApplication.load(req, this, "Test");
	}
	
	@Override
	public void onPDApplicationLoaded(PDApplication application) {
		this.app = application;
		message = "";
	
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
	public void onPDApplicationEnded() {
		Log.debug(this, "Finish");

		try {
			resp.getWriter().write(message);
		} catch (IOException e) {
			Log.error(this, "Could not write the Http response.");
			e.printStackTrace();
		}
	}

	@Override
	public void onAction(ActionEvent<?> ae) {

		message += " onAction: ";
		Log.debug(this, ae.toString());
		Widget source = (Widget)ae.getSource();
		
		if ( source.getWidgetId().equals("listid") ) {
			message += ae.getParam();
		}
		
		
	}
}
