/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.temple.cis3238.wiki;

import java.util.*;
import java.util.concurrent.locks.*;
import javax.servlet.*;
import javax.servlet.http.*;

/**
 * Web application lifecycle listener.
 *
 * @author
 */
public class WikiEventMonitor implements ServletContextListener, ServletContextAttributeListener, HttpSessionListener, HttpSessionAttributeListener, ServletRequestListener, ServletRequestAttributeListener {
   ////////////////////
   //// Incomplete
   //////////////////////
   private static Map<String, HttpSession> sessions = new HashMap<String, HttpSession>();
   private static Map<String, Integer> lockedPages;
   Lock lock;

   private static int usersOnline = 0;

   public static HttpSession find(boolean uselog, String sessionId) {
	  return sessions.get(sessionId);

   }

   @Override
   public void attributeAdded(ServletContextAttributeEvent _event) {
	  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
   }

   @Override
   public void attributeAdded(HttpSessionBindingEvent _event) {

	  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
   }

   @Override
   public void attributeAdded(ServletRequestAttributeEvent _srae) {

	  lock = new StampedLock().asWriteLock();

   }

   @Override
   public void attributeRemoved(ServletContextAttributeEvent _event) {
//	  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
   }

   @Override
   public void attributeRemoved(HttpSessionBindingEvent _event) {
//	  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
   }

   @Override
   public void attributeRemoved(ServletRequestAttributeEvent _srae) {
//	  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
   }

   @Override
   public void attributeReplaced(ServletContextAttributeEvent _arg0) {
//	  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
   }

   @Override
   public void attributeReplaced(HttpSessionBindingEvent _event) {
//	  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
   public void sessionCreated(HttpSessionEvent _se) {
//	  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
   }

   @Override
   public void sessionDestroyed(HttpSessionEvent _se) {
	  try {
		 HttpSession tempSession = sessions.remove(_se.getSession().getId());
	  } catch (NullPointerException | UnsupportedOperationException | ClassCastException e) {
	  }
	  usersOnline--;
   }
}
