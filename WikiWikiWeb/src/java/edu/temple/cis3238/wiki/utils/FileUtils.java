

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.temple.cis3238.wiki.utils;

import edu.temple.cis3238.wiki.ui.servlets.UploaderServlet;
import edu.temple.cis3238.wiki.vo.*;
import javax.servlet.*;
import java.io.File;
import java.io.IOException;
import java.util.logging.*;
import java.util.regex.*;
import org.apache.commons.fileupload.UploadContext;


/**
 * Helper methods for validating and uploading files
 * 
 * @author dan
 * @see UploaderServlet
 * @see UploadContext
 */
public class FileUtils {
   private static final Logger LOG = Logger.getLogger( FileUtils.class.getName() );
   /**
	* Verifies valid file extension
	* @param filePath full path of file
	* @param extraValidExtensions CSV list of additional valid file extensions.
	* @return valid
	* @see edu.temple.cis3238.wiki.ui.servlets.UploadServlet
	*/
   public static boolean checkFileExtension(String filePath,String extraValidExtensions) {
	   String[] extensionArray  = {""};
	   String extensions ="";
	   if (extraValidExtensions != null && !extraValidExtensions.isEmpty() ){
		   extensionArray = extraValidExtensions.replace( " ","").toLowerCase().split( ",");
		   extensions = "|"+org.apache.commons.lang3.StringUtils.join( extensionArray, '|');
		   extensions = org.apache.commons.lang3.StringUtils.removeEnd(extensions.trim(),"|");
	   }
	   File file = new File(filePath);
	   String validExts = "(pdf|mp4|m4v|wmv|flv|swf|avi|mov|mpeg|mpg|mov|doc|docx|xls|xlsx|ppt|pptx|txt|jpg|jpeg|png"+extensions+")";
//        if (!file.isFile()) {
//            return false;
//        }
//		else {
String type = filePath.substring(filePath.lastIndexOf(".") + 1, filePath.length()).toLowerCase();

try {
	Pattern regex = Pattern.compile(validExts, Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE);
	Matcher regexMatcher = regex.matcher(type);
	if (regexMatcher.matches()) {
		return true;
	}
	
} catch (PatternSyntaxException ex) {
	LOG.log(Level.SEVERE, null, ex);
	return false;
}

//        }
return false;
   }
   /**
	* Creates directories and subdirs for specified file.
	* @param context servlet
	* @param uploadDirectory [basepath]/uploads/[TopicID]/[FILENAME]
	* @param topic topicVO
	* @return Absolute path
	* @see edu.temple.cis3238.wiki.ui.servlets.UploadServlet
	*/
   public static String makeDir(ServletContext context, String uploadDirectory, TopicVO topic) {
	   try {
		   String uploadPath = context.getRealPath( "" )
				   + File.separator + uploadDirectory + File.separator + topic.getTopicID();
		   
		   // creates the directory if it does not exist
		   File uploadDir = new File( uploadPath );
		   if ( !uploadDir.exists() ) {
			   uploadDir.mkdirs();
		   }
		   return uploadPath;
	   } catch (Exception e) {
		   e.printStackTrace();
		   return "";
	   }
	   
   }

}
