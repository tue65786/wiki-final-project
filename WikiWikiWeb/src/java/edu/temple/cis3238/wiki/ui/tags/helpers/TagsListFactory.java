/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.temple.cis3238.wiki.ui.tags.helpers;

import edu.temple.cis3238.wiki.vo.*;
import java.util.*;

/**
 *
 * @author (c)2016
 */
public class TagsListFactory {

private static final String CLOUD_ITEM_HREF_MARKUP = "<a title=\"Click to view [[[COUNTTOPIC]]] topic(s).\" href=\"[[[URL]]]?[[[QSP]]]=[[[TAGID]]]&pTagID=[[[TAGNAME]]]\">[[[TAGNAME]]]</a>";
private static final String CLOUD_ITEM_STYLE_PREFIX = "<span style=\"font-size:";
private static final String CLOUD_ITEM_STYLE_SUFFIX = "pt;\"> ";
private static final int LARGE = 20;
private static final int LARGE_X = 22;
private static final int LARGE_XX = 26;
private static final int MEDIUM = 16;
private static final int SMALL = 14;
private static final int SMALL_X = 12;
private static final int SMALL_XX = 10;
private static final String[] FONT_SCALE = {
   CLOUD_ITEM_STYLE_PREFIX + SMALL_XX + CLOUD_ITEM_STYLE_SUFFIX,
   CLOUD_ITEM_STYLE_PREFIX + SMALL_X + CLOUD_ITEM_STYLE_SUFFIX,
   CLOUD_ITEM_STYLE_PREFIX + SMALL + CLOUD_ITEM_STYLE_SUFFIX,
   CLOUD_ITEM_STYLE_PREFIX + MEDIUM + CLOUD_ITEM_STYLE_SUFFIX,
   CLOUD_ITEM_STYLE_PREFIX + LARGE + CLOUD_ITEM_STYLE_SUFFIX,
   CLOUD_ITEM_STYLE_PREFIX + LARGE_X + CLOUD_ITEM_STYLE_SUFFIX,
   CLOUD_ITEM_STYLE_PREFIX + LARGE_XX + CLOUD_ITEM_STYLE_SUFFIX };

private double cloudMaxRange;
private double cloudMinRange;
private double couldRange;
private TagsTagSettings settings;
private String tagsMarkup;

public static TagsListFactory create(TagsTagSettings _settings) {
   TagsListFactory factory = new TagsListFactory( _settings );
   if ( !factory.settings.validateParams() ) {
	  throw new NullPointerException( "Invalid settings." );
   }
   if ( _settings.getStyle().equalsIgnoreCase( "cloud" ) ) {
	  factory.setTagsMarkup( factory.createCloud() );
   } else {
	  factory.setTagsMarkup( factory.createTable() );
   }
   return factory;
}

/**
 * @return the tagsMarkup
 */
public String getTagsMarkup() {
   return tagsMarkup;
}

/**
 * @param tagsMarkup the tagsMarkup to set
 */
public void setTagsMarkup(String tagsMarkup) {
   this.tagsMarkup = tagsMarkup;
}

private String createCloud() {
   StringBuilder sb = new StringBuilder( "<div style=\"border:1px solid black;width:" );
   sb.append( getSettings().getWidthpx() )
		   .append( ";padding:5px;margin:3px;\">" );
   setScale();
   for ( int i = 0; i < getSettings().getTagsVOList().size(); i++ ) {
	  if ( i > 1 && i % 5 == 1 ) {
		 sb.append( "<br/>" );
	  }
	  sb.append( makeCloudMarkup( getSettings().getTagsVOList().get( i ) ) );
   }
   sb.append( "</div>" );
   return sb.toString();

}

private String createTable() {
   StringBuilder sb = new StringBuilder( "<table>" );
   for ( TagsVO aVO : getSettings().getTagsVOList() ) {
	  sb.append( aVO.toTableRow() );
   }
   sb.append( "</table>" );
   return sb.toString();
}

/**
 * @return the settings
 */
private TagsTagSettings getSettings() {
   return settings;
}

/**
 * @param settings the settings to set
 */
protected void setSettings(TagsTagSettings settings) {
   this.settings = settings;
}

private String makeCloudMarkup(TagsVO vo) {
   String href = makeHRef( vo );
   int scaleVal = (int) ( Math.round( ( ( vo.getTopicCount() > 1 ? vo.getTopicCount() - cloudMinRange : 1 ) ) / couldRange ) );
   return FONT_SCALE[scaleVal] + href + "</span>";
}

private String makeHRef(TagsVO vo) {
   return CLOUD_ITEM_HREF_MARKUP
		   .replace( "[[[URL]]]", getSettings().getNavigateURL() )
		   .replace( "[[[QSP]]]", getSettings().getQueryStringParam() )
		   .replace( "[[[TAGNAME]]]", vo.getTagName() )
		   .replace( "[[[TAGID]]]", vo.getTagID() + "" )
		   .replace( "[[[COUNTTOPIC]]]", vo.getTopicCount() + "" );

}

private void setScale() {
   double i = 0;
   for ( TagsVO aVO : getSettings().getTagsVOList() ) {
	  i = (double) aVO.getTopicCount();
	  if ( i < cloudMinRange ) {
		 cloudMinRange = i;
	  }
	  if ( i > cloudMaxRange ) {
		 cloudMaxRange = i;
	  }
   }
   couldRange = ( (double) cloudMaxRange - cloudMinRange + 1.0 ) / ( FONT_SCALE.length - 1.0 );
}

private TagsListFactory(TagsTagSettings _settings) {
   this.settings = _settings;
}

}
