/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


package edu.temple.cis3238.wiki.ui.tags.helpers;

import edu.temple.cis3238.wiki.utils.StringUtils;
import edu.temple.cis3238.wiki.vo.*;

/**
 *
 * @author (c)2016  Technologies
 */
public class TopicHTMLFactory {
public static String getTopicStats(TopicVO vo) {
   StringBuilder sb = new StringBuilder( "" );
   if ( !vo.getTopicModified().isEmpty() ) {
	  sb.append( " " ).append( vo.getTopicModified() );
   }
   sb.append( " [Rev." ).append( vo.getRevisions() + "]" );
   return sb.toString();
}
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
   return org.apache.commons.lang3.StringUtils.removeEnd( StringUtils.toS( sb.toString() ), "|" );
}

public static   String makeA(TagsVO vo,String tagLinkRequestParam,String tagLinkPage) {
   return "<a href=\"" + makeHref( vo , tagLinkRequestParam, tagLinkPage) + "\">" + vo.getTagName().toLowerCase() + "</a>";
}

public static   String makeA(TopicVO vo,String topicLinkRequestParam,String topicLinkPage) {

   return "<a href=\"" + makeHref( vo,topicLinkRequestParam,topicLinkPage ) + "\">" + org.apache.commons.lang3.StringUtils.capitalize( vo.getTopicName().toLowerCase() ) + "</a>";
}
public static   String makeHref(TopicVO vo,String topicLinkRequestParam,String topicLinkPage) {
   return StringUtils.toS( topicLinkPage ) + "?" + StringUtils.toS( topicLinkRequestParam ) + "=" + vo.getTopicName() + "&topicPK=" + vo.getTopicID();
}
public static   String makeHref(TagsVO vo,String tagLinkRequestParam,String tagLinkPage) {
   return StringUtils.toS( tagLinkPage ) + "?" + StringUtils.toS( tagLinkRequestParam ) + "=" + vo.getTagName() + "&tagPK=" + vo.getTagID();
}
}
