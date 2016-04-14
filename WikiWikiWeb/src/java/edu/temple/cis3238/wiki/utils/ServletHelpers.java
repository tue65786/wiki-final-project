/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.temple.cis3238.wiki.utils;

import java.io.*;
import java.util.logging.Level;
import javax.servlet.http.*;

/**
 *
 *
 */
public class ServletHelpers implements Serializable {

private static final long serialVersionUID = 9173973156268582162L;

private HttpServletRequest request;
private HttpServletResponse response;
private HttpSession session;

 
public  <T> T getAttribute(String sessionAttrib,T defaultValue ) {
      Object param = session.getAttribute(sessionAttrib);
      T paramValue = null;
      try {
         if ( param != null ) {
            paramValue = (T) param;
         }
      }
      catch ( Exception e ) {
         paramValue = defaultValue;
      }
      return (paramValue);
   }
   
    public int getIntAttribute(String attribute,int defaultVal ) {
      Object paramString = session.getAttribute(attribute);
      int paramValue;
      try {
         paramValue = Integer.parseInt(paramString.toString());
      }
      catch ( Exception e ) { 
         paramValue = defaultVal;
      }
      return (paramValue);
   }
	 public int getIntParameter( String qsParam, int defaultVal ) {
      String paramString = request.getParameter(qsParam);
      int paramValue;
      try {
         paramValue = Integer.parseInt(paramString);
      }
      catch ( Exception nfe ) { 
         paramValue = defaultVal;
      }
      return (paramValue);
   }
	 public String getStrAttribute(String attrib,String defaultVal ) {
		Object param = session.getAttribute(attrib);
		String paramString;
		try {
		   paramString = param.toString();
		   if ( paramString.length() == 0 ) {
			  return (defaultVal);
		   }
		}
		catch ( Exception nfe ) {
		   paramString = defaultVal;
		}
		return (paramString);
	 }
	 
	 public String getStrParameter( String qsParam, String defaultVal ) {
		Object param = request.getParameter(qsParam);
		String paramString;
		try {
		   paramString = param.toString();
		   if ( paramString.length() == 0 ) {
			  return (defaultVal);
		   }
		}
		catch ( Exception e ) {
		  
		   paramString = defaultVal;
		}
		return (paramString);
	 }
	 
	 public Cookie getCookie(
       String cookieName ) {
      Cookie[] cookies = request.getCookies();
      if ( cookies != null ) {
         for ( Cookie cookie : cookies ) {
            if ( cookieName.equals(cookie.getName()) ) {
               return (cookie);
            }
         }
      }
      return (null);
   }
	 public boolean setCookie( String name, String val, int days ) {
      //   int days = 60;
      try {
         Cookie cookie = new Cookie(StringUtils.toS(name), StringUtils.toS(val));
         //cookie.setComment("Created: " + Global.getNow()); 
         cookie.setMaxAge(24 * 60 * days);
         cookie.setPath("wikiwikiweb/");
         response.addCookie(cookie);
         Cookie cookie2 = (Cookie) cookie.clone();
         cookie2.setPath("/");
         response.addCookie(cookie2);
         return true;

      }
      catch ( IllegalStateException e ) {
         e.printStackTrace();
         return false;
      }
      catch ( Exception e ) {
         e.printStackTrace();
         return false;
      }
   }

	 /**
	  * Constructs Servlet helper 
	  * Request and Response are not nullable
	  * @param request current context request from JSP or Servlet
	  * @param response current context request from JSP or Servlet
	  */
	 public ServletHelpers(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
		if ( request == null || response == null ) {
		   throw new NullPointerException( "Request and Response can not be null" );
		}
		
		if ( request.getSession() != null ) {
		   session = request.getSession();
		}
		
	 }
}
