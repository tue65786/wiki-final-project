/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


package edu.temple.cis3238.constants;

import edu.temple.cis3238.wiki.dao.GeneralDAO;
import edu.temple.cis3238.wiki.ui.beans.TopicCollection;
import edu.temple.cis3238.wiki.ui.tags.TagsList;
import edu.temple.cis3238.wiki.ui.tags.helpers.*;
import edu.temple.cis3238.wiki.vo.*;

/**
 *
 * @author
 */
public class QUERY_PARAMS {

	/**
	 *
	 */
	public static final String WIKI_VIEWMODE = "mode";

	/**
	 * Default Request Param for TagID.
	 * @see TagsVO
	 * @see TagsList
	 * @see TagsListFactory
	 */
	public static final String TAG_ID = "tagPK";
/**
	 * Default Request Param for Tag Name.
	 * @see TagsVO
	 * @see TagsList
	 * @see TagsListFactory
	 */
	public static final String TAG_NAME = "pTagID";

	/**
	 *
	 */
	public static final String TOPIC_ID = "topicPK";

	/**
	 * Default Request Param for TopicID
	 * @see TopicVO
	 * @see TopicCollection
	 * @see TopicHTMLFactory
	 * 
	 */
	public static final String TOPIC_NAME = "pTopicID";

	/**
	 * Default Request Param for Topic Content.
	 */
	public static final String TOPIC_CONTENT ="topicConent";

	/**
	 * Default Request Param for Search Keyword
	 * @see TopicVO
	 * @see GeneralDAO#searchTopic(java.lang.String) 
	 */
	public static final String TOPICS_SEARCH_KEY = "query";

	/**
	 * Default Request Param for TopicHistory ID.
	 * @see TopicHistoryVO
	 */
	public static final String TOPIC_HISTORY_ID = "hTopicID";


}
