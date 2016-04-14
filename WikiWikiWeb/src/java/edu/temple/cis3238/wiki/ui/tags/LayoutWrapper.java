/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.temple.cis3238.wiki.ui.tags;

import java.io.*;
import java.util.logging.*;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 *
 * @author (c)2016 3238
 */
public class LayoutWrapper extends SimpleTagSupport {

	/**
	 * Called by the container to invoke this tag. The implementation of this method is provided by
	 * the tag library developer, and handles all tag processing, body iteration, etc.
	 */
	@Override
	public void doTag() throws JspException {
		JspWriter out = getJspContext().getOut();
		StringWriter stringWriter = new StringWriter();
		try {
			getJspBody().invoke(stringWriter);
			out.print(stringWriter.toString());
		} catch (IOException ex) {
			LOG.log(Level.SEVERE, null, ex);
			throw new JspException("Error in LayoutWrapper tag", ex);
		}

	}
	private static final Logger LOG = Logger.getLogger(LayoutWrapper.class.getName());

}
