/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.temple.cis3238.wiki;

import edu.temple.cis3238.wiki.WikiApplicationFilter.RequestWrapper;
import java.io.Serializable;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.PageContext;
import org.apache.tomcat.util.http.RequestUtil;

/**
 * */
public class WikiContext implements Cloneable, Serializable {
   ////////////////////
   //// Incomplete
   //////////////////////
   private static final long serialVersionUID = 2889058640201059039L;

   private HttpServletRequest request;
   private RequestWrapper requestWrapper;

   public Object clone() {
	  WikiContext con = new WikiContext();
//	  con.requestWrapper = WikiApplicationFilter.RequestWrapper(request); 
	  con.request = request;
	  return con;
   }

}
