/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.temple.cis3238.wiki.ui.tags;

import edu.temple.cis3238.wiki.ui.beans.TopicCollection;
import edu.temple.cis3238.wiki.ui.tags.helpers.TopicHistoryHTMLFactory;
import java.util.logging.Logger;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 *
 * Generates topic history log for Topic
 */
public class TopicHistoryTag extends SimpleTagSupport {

	private TopicCollection topicCollection;

	/**
	 * Called by the container to invoke this tag. The implementation of this method is provided by
	 * the tag library developer, and handles all tag processing, body iteration, etc.
	 */
	@Override
	public void doTag() throws JspException {
		JspWriter out = getJspContext().getOut();

		try {
			if (topicCollection == null ||
					 topicCollection.getCurrentTopic() == null ||
					 !topicCollection.isCurrentTopicHistoryLoaded()) {
				out.print("No history");

			} else {
				out.print(TopicHistoryHTMLFactory.create(topicCollection.getCurrentTopic(),
						topicCollection.getCurrentTopic().getTopicHistoryCollection()));
			}
			JspFragment f = getJspBody();
			if (f != null) {
				f.invoke(out);
			}
		} catch (java.io.IOException ex) {
			ex.printStackTrace();
			throw new JspException("Error in TopicHistoryTag tag", ex);
		}
	}
/**
 * Populate topicCollection
 * @param topicCollection Collection Bean
 */
	public void setTopicCollection(TopicCollection topicCollection) {
		this.topicCollection = topicCollection;
	}
	private static final Logger LOG = Logger.getLogger(TopicHistoryTag.class.getName());

}
