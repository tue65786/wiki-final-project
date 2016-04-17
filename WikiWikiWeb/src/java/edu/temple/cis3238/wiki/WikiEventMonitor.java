/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.temple.cis3238.wiki;

import java.util.*;
import java.util.concurrent.locks.*;
import java.util.logging.Logger;
import javax.servlet.*;
import javax.servlet.http.*;

/**
 * Web application lifecycle listener.
 *
 * @author
 */
public class WikiEventMonitor implements ServletContextListener, ServletContextAttributeListener, HttpSessionListener, HttpSessionAttributeListener, ServletRequestListener, ServletRequestAttributeListener {

	private static Map<String, HttpSession> sessions = new HashMap<String, HttpSession>();
	private static Map<String, Integer> lockedPages;
	Lock lock;
	WikiEngine engine;
	private static int usersOnline = 0;

	public static HttpSession find(boolean uselog,
								   String sessionId) {
		return sessions.get(sessionId);

	}

	@Override
	public void attributeAdded(ServletContextAttributeEvent _event) {
		System.out.println(
				"edu.temple.cis3238.wiki.WikiEventMonitor.attributeAdded()\n added " + _event.getName() + _event.toString());
		LOG.info(Objects.toString(_event.getSource()));

	}

	@Override
	public void attributeAdded(HttpSessionBindingEvent _event) {
		System.out.println(
				"edu.temple.cis3238.wiki.WikiEventMonitor.[SESSION]attributeAdded()\n added " + _event.getName() + _event.toString());
		LOG.info(Objects.toString(_event.getValue().toString()));
		LOG.info(Objects.toString(_event.getSource()));
	}

	@Override
	public void attributeAdded(ServletRequestAttributeEvent _srae) {
		System.out.println(
				"edu.temple.cis3238.wiki.WikiEventMonitor.attributeAdded(): [name]" + _srae.getName());

	}

	@Override
	public void attributeRemoved(ServletContextAttributeEvent _event) {

	}

	@Override
	public void attributeRemoved(HttpSessionBindingEvent _event) {
	}

	@Override
	public void attributeRemoved(ServletRequestAttributeEvent _srae) {
		System.out.println(
				"edu.temple.cis3238.wiki.WikiEventMonitor.attribRemoved(): [name]" + _srae.getName());
	}

	/**
	 *
	 * @param _arg0
	 */
	@Override
	public void attributeReplaced(ServletContextAttributeEvent _arg0) {
//	  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public void attributeReplaced(HttpSessionBindingEvent _event) {
		String sessionID = _event.getSession().getId();
	}

	@Override
	public void attributeReplaced(ServletRequestAttributeEvent _srae) {
//	  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public void contextDestroyed(ServletContextEvent _sce) {
//	  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public void contextInitialized(ServletContextEvent _sce) {
//	  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public void requestDestroyed(ServletRequestEvent _sre) {
//	  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public void requestInitialized(ServletRequestEvent _sre) {
		_sre.getServletContext();
		_sre.getSource();
//	  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public void sessionCreated(HttpSessionEvent se) {
		HttpSession session = se.getSession();
		try {
			sessions.put(session.getId(), session);
			usersOnline++;

		} catch (UnsupportedOperationException | ClassCastException | NullPointerException | IllegalArgumentException e) {
			System.out.println(
					"edu.temple.cis3238.wiki.WikiEventMonitor.sessionCreated() -- " + e.toString());
		}
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent _se) {
		try {
			HttpSession tempSession = sessions.remove(
					_se.getSession().getId());
		} catch (NullPointerException | UnsupportedOperationException | ClassCastException e) {
		}
		usersOnline--;
	}
	private static final Logger LOG = Logger.getLogger(
			WikiEventMonitor.class.getName());
/**
 * Active user sessions for .
 * @return 
 */
	public static String getOnlineUsers() {
		if (usersOnline <= 1) {
			return "<i>There is (1) user online</i>";
		} else {
			return "<i>There are (" + usersOnline + ") users online.</i>";
		}
	}
}
