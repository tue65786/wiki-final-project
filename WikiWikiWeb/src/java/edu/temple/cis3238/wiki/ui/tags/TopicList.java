/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.temple.cis3238.wiki.ui.tags;

import edu.temple.cis3238.wiki.ui.beans.*;
import edu.temple.cis3238.wiki.ui.tags.helpers.TopicHTMLFactory;
import edu.temple.cis3238.wiki.utils.*;
import edu.temple.cis3238.wiki.vo.*;
import java.util.*;
import java.util.logging.*;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 *
 * @author 
 */
public class TopicList extends SimpleTagSupport {

public static final String LIST_STYLES[] = { "TABLE", "LIST" };
public static final int LIST_STYLE_LIST = 1;
public static final int LIST_STYLE_TABLE = 0;
private static final String LIST_TEMPLATE = "<li><h4>[[[HREF]]]</h4>"
		+ "<p class='wikicontent'>[[[ABSTRACT]]]</p>"
		+ "<p class='tags'>[[[TAGS]]]</p>"
		+ "<p class='stats'>[[[STATS]]]</p>"
		+ "</li>";
private static final String TABLE_POST = "</table>";
private static final String TABLE_PRE = "<table class='topicTable'><tr><th>&nbsp;</th><th>Title</th><th>Modified</th><th>Tags </th></tr>";
private static final String TABLE_ROW_TEMPLATE = "<tr class='[[[CLASS]]]'>"
		+ "<td>[[[ROW]]]</td>"
		+ "<td>[[[HREF]]]</td>"
		+ "<td>[[[STATS]]]</td>"
		+ "<td>[[[TAGS]]]</td>"
		+ "</tr>";
private String listStyle;
private String sortField;
private String tagLinkPage;
private String tagLinkRequestParam;
private String topicLinkPage;
private String topicLinkRequestParam;
private ArrayList<TopicVO> topicsList;
TopicCollection topicCollection;

/**
 * Called by the container to invoke this tag.
 * The implementation of this method is provided by the tag library developer,
 * and handles all tag processing, body iteration, etc.
    * @throws javax.servlet.jsp.JspException
 */
@Override
public void doTag() throws JspException {
   JspWriter out = getJspContext().getOut();

   try {
	  if ( topicsList == null || topicsList.isEmpty() ) {
		 out.println( "<p>Topics List Empty.</p>" );
	  } else {
		 System.out.println( "LIST STYLE ====" + listStyle );
		 if ( listStyle.toUpperCase().equals( LIST_STYLES[LIST_STYLE_TABLE] ) ) {
			out.println( TABLE_PRE );
			for ( int i = 0; i < topicsList.size(); i++ ) {
			   out.println( makeTopicRow( topicsList.get( i ), i + 1 ) );
			}
			out.println( TABLE_POST );
		 } else {
			out.println( "<ul class=\"topiclist\">" );
			for ( TopicVO vo : topicsList ) {
			   out.println( makeTopicItem( vo ) );
			}
			out.println( "</ul>" );
		 }
	  }
	  JspFragment f = getJspBody();
	  if ( f != null ) {
		 f.invoke( out );
	  }

   } catch (java.io.IOException ex) {
	  throw new JspException( "Error in TopicList tag", ex );
   }
}
public void setListStyle(String listStyle) {
   this.listStyle = StringUtils.toS( listStyle );
   System.out.println( "edu.temple.cis3238.wiki.ui.tags.TopicList.setListStyle()" );
}
public void setSortField(String sortField) {
   this.sortField = sortField;
}
public void setTagLinkPage(String tagLinkPage) {
   this.tagLinkPage = tagLinkPage;
}
public void setTagLinkRequestParam(String tagLinkRequestParam) {
   this.tagLinkRequestParam = tagLinkRequestParam;
}
public void setTopicLinkPage(String topicLinkPage) {
   this.topicLinkPage = topicLinkPage;
}
public void setTopicLinkRequestParam(String topicLinkRequestParam) {
   this.topicLinkRequestParam = topicLinkRequestParam;
}
public void setTopicsList(TopicCollection _topicCollection) {
   this.topicsList = _topicCollection.getTopics();
}

/**
 * Output form view.
 * @param vo
 * @return 
 */
private String makeTopicItem(TopicVO vo) {
   String ret = LIST_TEMPLATE + "";
   return ret.
		   replace( "[[[ABSTRACT]]]", StringUtils.truncateAtWord( vo.getTopicContent(), 80 ) )
		   .replace( "[[[HREF]]]", TopicHTMLFactory.makeA( vo,topicLinkRequestParam,topicLinkPage ) )
		   .replace( "[[[STATS]]]", TopicHTMLFactory.getTopicStats( vo ) )
		   .replace( "[[[TAGS]]]", TopicHTMLFactory.makeTagsCSV( vo ,tagLinkRequestParam,tagLinkPage) );
}
/**
 * Output table row
 * @param vo
 * @param row
 * @return 
 */
private String makeTopicRow(TopicVO vo, int row) {
   boolean oddrow = row % 2 == 0;
   String ret = TABLE_ROW_TEMPLATE + "";
   return ret.replace( "[[[CLASS]]]", ( oddrow ? "oddrow" : "evenrow" ) )
		   .replace( "[[[ROW]]]", row + "" )
		   .replace( "[[[HREF]]]", TopicHTMLFactory.makeA( vo,topicLinkRequestParam,topicLinkPage ) )
		   .replace( "[[[STATS]]]", TopicHTMLFactory.getTopicStats( vo ) )
		   .replace( "[[[TAGS]]]", TopicHTMLFactory.makeTagsCSV( vo ,tagLinkRequestParam,tagLinkPage) );
}
   private static final Logger LOG = Logger.getLogger( TopicList.class.getName() );


}
