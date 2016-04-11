/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.temple.cis3238.wiki.ui.tags;

import edu.temple.cis3238.wiki.vo.*;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 *
 */
public class TopicSingle extends SimpleTagSupport {
   ////////////////////
   //// Incomplete
   //////////////////////
   private TopicVO TopicVO;
   private boolean showTags;
   private String tagURLPrefix;
   private String CSSClassTopicTitle;
   private String CSSClassTopicBody;
   private String CSSClassTagList;
   private static final String LIST_TEMPLATE = "<li><h4>[[[HREF]]]</h4>"
		+ "<p class='content'>[[[ABSTRACT]]]</p>"
		+ "<p class='tags'>[[[TAGS]]]</p>"
		+ "<p class='stats'>[[[STATS]]]</p>"
		+ "</li>";

/**
 * Called by the container to invoke this tag.
 * The implementation of this method is provided by the tag library developer,
 * and handles all tag processing, body iteration, etc.
 */
@Override
public void doTag() throws JspException {
   JspWriter out = getJspContext().getOut();
   
   try {
	  // TODO: insert code to write html before writing the body content.
	  // e.g.:
	  //
	  // out.println("<strong>" + attribute_1 + "</strong>");
	  // out.println("    <blockquote>");

	  JspFragment f = getJspBody();
	  if ( f != null ) {
		 f.invoke( out );
	  }

	  // TODO: insert code to write html after writing the body content.
	  // e.g.:
	  //
	  // out.println("    </blockquote>");
   } catch (java.io.IOException ex) {
	  throw new JspException( "Error in topic tag", ex );
   }
}

   public void setTopicVO(TopicVO TopicVO) {
	  this.TopicVO = TopicVO;
   }

   public void setShowTags(boolean showTags) {
	  this.showTags = showTags;
   }

   public void setTagURLPrefix(String tagURLPrefix) {
	  this.tagURLPrefix = tagURLPrefix;
   }

   public void setCSSClassTopicTitle(String CSSClassTopicTitle) {
	  this.CSSClassTopicTitle = CSSClassTopicTitle;
   }

   public void setCSSClassTopicBody(String CSSClassTopicBody) {
	  this.CSSClassTopicBody = CSSClassTopicBody;
   }

   public void setCSSClassTagList(String CSSClassTagList) {
	  this.CSSClassTagList = CSSClassTagList;
   }

}
