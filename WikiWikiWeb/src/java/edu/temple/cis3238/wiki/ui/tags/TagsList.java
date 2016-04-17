/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.temple.cis3238.wiki.ui.tags;

import edu.temple.cis3238.wiki.ui.beans.*;
import edu.temple.cis3238.wiki.ui.tags.helpers.*;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 *
 * Custom tag that generates HTML from ArrayList of {@linkplain TagsVO}.
 * <br/>Pre condition: {@linkplain TagsCollection} populated from {@linkplain GeneralDAO}.
 * <br/>Default Style: <a href='https://en.wikipedia.org/wiki/Tag_cloud'>Tag Cloud<sub>WikiPedia</sub></a>
 */
public class TagsList extends SimpleTagSupport {

   private TagsCollection tagsCollectionBeans;

/**
 * Called by the container to invoke this tag.
 * The implementation of this method is provided by the tag library developer,
 * and handles all tag processing, body iteration, etc.
 */
@Override
public void doTag() throws JspException {
   JspWriter out = getJspContext().getOut();
   
   try {
	TagsListFactory factory;

	factory =  TagsListFactory.create(tagsCollectionBeans.getSettings());
	out.println(factory.getTagsMarkup());

	  JspFragment f = getJspBody();
	  if ( f != null ) {
		 f.invoke( out );
	  }
   } catch (java.io.IOException ex) {
	  throw new JspException( "Error in TagsList tag", ex );
   }
}

	/**
	 *
	 * @param TagsCollectionBeans
	 */
	public void setTagsCollectionBeans(TagsCollection TagsCollectionBeans) {
	  this.tagsCollectionBeans = TagsCollectionBeans;
   }

}
