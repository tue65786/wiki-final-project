/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.temple.cis3238.wiki.ui.servlets;

import edu.temple.cis3238.wiki.ui.beans.*;
import edu.temple.cis3238.wiki.utils.*;
import edu.temple.cis3238.wiki.vo.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import java.util.*;
import java.util.logging.*;
import java.util.regex.*;
import org.apache.commons.fileupload.*;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author
 */
@WebServlet(name = "UploaderServlet", urlPatterns = { "/Uploader" })
public class UploaderServlet extends HttpServlet {

private static final String UPLOAD_DIRECTORY = "uploads";
public static final String REDIRECT_ON_COMPLETE_PAGE = "UploadSuccess.jsp";
// upload settings
private static final int MEMORY_THRESHOLD = 1024 * 1024 * 3; 	// 3MB
private static final int MAX_FILE_SIZE = 1024 * 1024 * 40; // 40MB
private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 50; // 50MB
private static final long serialVersionUID = -482591925582907679L;
private String topicID;
private TopicCollection collection;
private TopicVO topic;
private boolean success;
private String message;

/**
 * @return the message
 */
public String getMessage() {
   return message;
}

/**
 * @param message the message to set
 */
public void setMessage(String message) {
   this.message = message;
}

/**
 * @return the topic
 */
public TopicVO getTopic() {
   return topic;
}

/**
 * @param topic the topic to set
 */
public void setTopic(TopicVO topic) {
   this.topic = topic;
}

/**
 * @return the topicID
 */
public String getTopicID() {
   return topicID;
}

/**
 * @param topicID the topicID to set
 */
public void setTopicID(String topicID) {
   this.topicID = topicID;
}

/**
 * @return the success
 */
public boolean isSuccess() {
   return success;
}

/**
 * @param success the success to set
 */
public void setSuccess(boolean success) {
   this.success = success;
}

/**
 * Processes requests for HTTP  <code>POST</code> method.
 *
 * @param request  servlet request
 * @param response servlet response
 * @throws ServletException if a servlet-specific error occurs
 * @throws IOException      if an I/O error occurs
 */
protected void processRequest(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
   response.setContentType( "text/html;charset=UTF-8" );
   try (PrintWriter out = response.getWriter()) {

	  // checks if the request actually contains upload file
	  if ( !ServletFileUpload.isMultipartContent( request ) ) {
		 // if not, we stop here
		 PrintWriter writer = response.getWriter();
		 writer.println( "Error: Form must be  enctype = multipart/form-data." );
		 writer.flush();
		 setSuccess( false );
		 return;
	  }

	  // configures upload settings
	  DiskFileItemFactory factory = new DiskFileItemFactory();

	  factory.setSizeThreshold( MEMORY_THRESHOLD );

	  factory.setRepository( new File( System.getProperty( "java.io.tmpdir" ) ) );

	  ServletFileUpload upload = new ServletFileUpload( factory );
	  upload.setFileSizeMax( MAX_FILE_SIZE );
	  upload.setSizeMax( MAX_REQUEST_SIZE );
	  if ( request.getSession().getAttribute( "topicCollection" ) != null ) {
		 try {
			collection = (TopicCollection) request.getSession().getAttribute( "topicCollection" );
			setTopic( collection.getCurrentTopic() );
			setTopicID( getTopic().getTopicID() + "" );
		 } catch (Exception e) {
			e.printStackTrace();
			if ( getTopic() == null ) {
			   try {
				  setTopicID( request.getSession().getAttribute( "topicID" ).toString() );
				  setTopic( new TopicVOBuilder().setTopicID( Integer.parseInt( getTopicID() ) ).build() );
			   } catch (Exception ex) {
				  ex.printStackTrace();
			   }
			}
		 }
	  } else {
		 setTopicID( "none" );
	  }

	  String uploadPath = FileUtils.makeDir( getServletContext(), UPLOAD_DIRECTORY, getTopic() );
	  //getServletContext().getRealPath( "" )
	  ///			  + File.separator + UPLOAD_DIRECTORY;

	  // creates the directory if it does not exist
	  File uploadDir = new File( uploadPath );
	  if ( !uploadDir.exists() ) {
		 uploadDir.mkdirs();

	  }

	  try {
		 // parses the request's content to extract file data
		 @SuppressWarnings("unchecked")
		 List<FileItem> formItems = upload.parseRequest( request );
		 String fileName = "";

		 if ( formItems != null && formItems.size() > 0 ) {
			// iterates over form's fields
			for ( FileItem item : formItems ) {

			   if ( !item.isFormField() ) {
				  fileName = new File( item.getName() ).getName();
				  String filePath = uploadPath +  File.separator + fileName;
				  System.out.println(filePath);
				  File storeFile = new File( filePath );

				  if ( FileUtils.checkFileExtension( storeFile.getName().toLowerCase(), null ) ) {
					 item.write( storeFile );
					 request.setAttribute( "sourceFile", fileName );
					 System.out.println( "----------------------------" );
					 System.out.println( "FILENAME is :" + fileName );
					 System.out.println( "----------------------------" );
					 request.setAttribute( "topicID", StringUtils.toS( getTopicID() ) );
					 setStatus( request, true, "Success: Topic " + StringUtils.toS( getTopicID() )
											   + " has saved file " + fileName + ". Upload has been done successfully!" );
				  } else {
					 setStatus( request, false, "Exception: Invalid file extension" );
				  }
			   }
			}
		 } else {
			setStatus( request, false, "Exception: No valid file(s)" );
		 }
	  } catch (Exception ex) {
		 ex.printStackTrace();
		 setStatus( request, false, "Exception: " + StringUtils.coalesce( ex.getMessage(), ex.toString(), "unknown" ) );
	  }
	  // redirects client to message page
	  getServletContext().getRequestDispatcher("/"+ REDIRECT_ON_COMPLETE_PAGE ).forward(
			  request, response );
   }
}

private void setStatus(HttpServletRequest request, boolean success, String msg) {
   request.setAttribute( "success", success );
   this.setSuccess( success );
   if ( msg != null ) {
	  setMessage( StringUtils.stripInvalidChars( msg ) );
	  request.setAttribute( "message", getMessage() );
   }
}
// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">

/**
 * Handles the HTTP <code>GET</code> method.
 *
 * @param request  servlet request
 * @param response servlet response
 * @throws ServletException if a servlet-specific error occurs
 * @throws IOException      if an I/O error occurs
 */
@Override
protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
   throw new ServletException( "Form must be submitted as a POST request" );
}

/**
 * Handles the HTTP <code>POST</code> method.
 *
 * @param request  servlet request
 * @param response servlet response
 * @throws ServletException if a servlet-specific error occurs
 * @throws IOException      if an I/O error occurs
 */
@Override
protected void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
   processRequest( request, response );
}

/**
 * Returns a short description of the servlet.
 *
 * @return a String containing servlet description
 */
@Override
public String getServletInfo() {
   return "Short description";
}// </editor-fold>
private static final Logger LOG = Logger.getLogger( UploaderServlet.class.getName() );

}
