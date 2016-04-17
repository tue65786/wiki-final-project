/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


package edu.temple.cis3238.wiki.ui.tags.helpers;

import edu.temple.cis3238.wiki.ui.tags.*;
import edu.temple.cis3238.wiki.utils.StringUtils;
import edu.temple.cis3238.wiki.vo.*;

/**
 * Factory methods for wrapping Topic fields in HTML Markup.
 * @author (c)2016
 * @see TopicVO
 * @see TopicList
 * @see TopicSingle
 */
public class TopicHTMLFactory {

	/**
	 * Markup for metadata -- dates, revisions etc..
	 * @param vo 
	 * @return Wrapped text.
	 */
	public static String getTopicStats(TopicVO vo) {
   StringBuilder sb = new StringBuilder( "" );
   if ( !vo.getTopicModified().isEmpty() ) {
	  sb.append( " " ).append( vo.getTopicModified() );
   }
   sb.append( " [Rev." ).append( vo.getRevisions() + "]" );
   return sb.toString();
}
/**
 * Makes HTML &lt;a&gt; <i>[hyperlink]</i> for {@linkplain TagsVO tags}
 * @param vo
 * @param tagLinkRequestParam request param <i>[ex: View.jsp?<u>tagID</u>=...]</i>
 * @param tagLinkPage URL <i>[ex: <u>View.jsp</u>?tagID=...]</i>
 * @return a tag href
 */
public static   String makeA(TagsVO vo,String tagLinkRequestParam,String tagLinkPage) {
   return "<a href=\"" + makeHref( vo , tagLinkRequestParam, tagLinkPage) + "\">" + vo.getTagName().toLowerCase() + "</a>";
}
/**
 * Makes HTML &lt;a&gt; <i>[hyperlink]</i> for {@linkplain TopicVO tags}
 * @param vo Topic
 * @param topicLinkRequestParam request param <i>[ex: View.jsp?<u>topicID</u>=nnn]</i>
 * @param topicLinkPage URL <i>[ex: <u>View.jsp</u>?topicID=nnn]</i>
 * @return a topic href
 */
public static String makeA(TopicVO vo,String topicLinkRequestParam,String topicLinkPage) {
   return "<a href=\"" + makeHref( vo,topicLinkRequestParam,topicLinkPage ) + "\">" + org.apache.commons.lang3.StringUtils.capitalize( vo.getTopicName().toLowerCase() ) + "</a>";
}
/**
 * Hyperlink helper fgr topics
 * @param vo
 * @param topicLinkRequestParam
 * @param topicLinkPage
 * @return 
 * @see #makeA(edu.temple.cis3238.wiki.vo.TopicVO, java.lang.String, java.lang.String) 
 */
public static   String makeHref(TopicVO vo,String topicLinkRequestParam,String topicLinkPage) {
   return StringUtils.toS( topicLinkPage ) + "?" + StringUtils.toS( topicLinkRequestParam ) + "=" + vo.getTopicName() + "&topicPK=" + vo.getTopicID();
}
/**
 *  Hyperlink helper for tags
 * @param vo
 * @param tagLinkRequestParam
 * @param tagLinkPage
 * @return 
 * @see #makeA(edu.temple.cis3238.wiki.vo.TopicVO, java.lang.String, java.lang.String) 
 */
public static   String makeHref(TagsVO vo,String tagLinkRequestParam,String tagLinkPage) {
   return StringUtils.toS( tagLinkPage ) + "?" + StringUtils.toS( tagLinkRequestParam ) + "=" + vo.getTagName() + "&tagPK=" + vo.getTagID();
}
/**
 * Generates markup for tags
 * @param vo
 * @param tagLinkRequestParam
 * @param tagLinkPage
 * @return HTML Markup
 * @see #makeA(edu.temple.cis3238.wiki.vo.TagsVO, java.lang.String, java.lang.String) 
 */
public static  String makeTagsCSV(TopicVO vo,String tagLinkRequestParam,String tagLinkPage) {
	StringBuilder sb = new StringBuilder( "" );
	if ( vo == null || vo.getTagsCollection() == null || vo.getTagsCollection().isEmpty() ) {
		return "No Tags";
	}
	for ( TagsVO tag : vo.getTagsCollection() ) {
		sb.append( "&nbsp;" );
		sb.append( makeA( tag,tagLinkRequestParam,tagLinkPage ) );
		sb.append( "&nbsp;|" );
	}
	return org.apache.commons.lang3.StringUtils
			.removeEnd( StringUtils.toS( sb.toString() ), "|" );
}
}
