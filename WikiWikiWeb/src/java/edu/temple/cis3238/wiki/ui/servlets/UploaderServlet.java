/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.temple.cis3238.wiki.ui.servlets;

import edu.temple.cis3238.wiki.utils.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import java.util.*;
import org.apache.commons.fileupload.*;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
/**
 *
 * @author
 */
@WebServlet(name = "UploaderServlet", urlPatterns = { "/Uploader" })
public class UploaderServlet extends HttpServlet {
 private static final String UPLOAD_DIRECTORY = "upload";
private static final String REDIRECT_ON_COMPLETE_PAGE = "Wiki.jsp";    
// upload settings
    private static final int MEMORY_THRESHOLD = 1024 * 1024 * 3; 	// 3MB
    private static final int MAX_FILE_SIZE = 1024 * 1024 * 40; // 40MB
    private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 50; // 50MB
    private String topicID;
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
        if (!ServletFileUpload.isMultipartContent(request)) {
            // if not, we stop here
            PrintWriter writer = response.getWriter();
            writer.println("Error: Form must has enctype=multipart/form-data.");
            writer.flush();
            return;
        }

        // configures upload settings
        DiskFileItemFactory factory = new DiskFileItemFactory();
        // sets memory threshold - beyond which files are stored in disk 
        factory.setSizeThreshold(MEMORY_THRESHOLD);
        // sets temporary location to store files
        factory.setRepository(new File(System.getProperty("java.io.tmpdir")));

        ServletFileUpload upload = new ServletFileUpload(factory);

        // sets maximum size of upload file
        upload.setFileSizeMax(MAX_FILE_SIZE);

        // sets maximum size of request (include file + form data)
        upload.setSizeMax(MAX_REQUEST_SIZE);

        // constructs the directory path to store upload file
        // this path is relative to application's directory
        String uploadPath = getServletContext().getRealPath("")
                + File.separator + UPLOAD_DIRECTORY;

        // creates the directory if it does not exist
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
            
        }

        try {
            // parses the request's content to extract file data
            @SuppressWarnings("unchecked")
            List<FileItem> formItems = upload.parseRequest(request);
            String fileName = "";
            if (formItems != null && formItems.size() > 0) {
                // iterates over form's fields
                for (FileItem item : formItems) {
                    if (!item.isFormField()) {
                        fileName = new File(item.getName()).getName();
                        String filePath = uploadPath + File.separator + fileName;
                        File storeFile = new File(filePath);

                        if (request.getSession().getAttribute("topicID") != null) {
                            topicID = request.getSession().getAttribute("topicID").toString();
                        }
                        else {
                            topicID = "none";
                        }
                        if (storeFile.getName().toLowerCase().endsWith("doc") 
							|| storeFile.getName().toLowerCase().endsWith("pdf") 
							||storeFile.getName().toLowerCase().endsWith("docx") 
							|| storeFile.getName().toLowerCase().endsWith("xls") 
							|| storeFile.getName().toLowerCase().endsWith("xlsx") 
							|| storeFile.getName().toLowerCase().endsWith("ppt") 
							|| storeFile.getName().toLowerCase().endsWith("pptx") 
							||  storeFile.getName().toLowerCase().endsWith("jpg") 
							|| storeFile.getName().toLowerCase().endsWith("gif") 
							|| storeFile.getName().toLowerCase().endsWith("png") 
							|| storeFile.getName().toLowerCase().endsWith("jpeg")) {
                            item.write(storeFile);

                            request.setAttribute("message", "Topic "+ StringUtils.toS(topicID)
                                    + " has saved file " + fileName + ". Upload has been done successfully!");
                            request.setAttribute("sourceFile", fileName);
                            System.out.println("----------------------------");
                            System.out.println("FILENAME is :" + fileName);
                            System.out.println("----------------------------");
                            request.setAttribute("topicID", StringUtils.toS(topicID));
                        }
                    }
                }
            }
            request.setAttribute("hasEx", false);
        } catch (Exception ex) {
            request.setAttribute("message",
                    "Error: Something went wrong: " + ex.getMessage());
            request.setAttribute("hasEx", true);
        }
        // redirects client to message page
        getServletContext().getRequestDispatcher("/message.jsp").forward(
                request, response);
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
   throw new ServletException("Form must be submitted as a POST request");
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

}
