/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.temple.cis3238.wiki.dao;

import WikiWikiWeb.Login;
import edu.temple.cis3238.wiki.vo.*;

/**
 * Stored Procedure definitions for data abstraction (GeneralDAO)
 *
 * @author (c)2016 Doreen, Dan, Christian
 * @see GeneralDAO
 * @see IGeneralDAO
 */
public class DB_STRINGS {

//Tags
	/**
	 * Insert New Tag
	 *
	 * @see TagsVO
	 */
	public static final String TAG_ADD = "{call dbo.spTagsInsert(?,?,?)}";

	/**
	 * Delete Specified Tag
	 *
	 * @see TagsVO
	 */
	public static final String TAG_DELETE = "{call dbo.spTagsDelete(?)}";
	/**
	 * TagID and TagName are Nullable
	 */
	public static final String TAG_SELECT = "{call dbo.spTagsSelect(?,?)}";

	/**
	 * Select Tags For Specified Topic
	 *
	 * @see TagsVO
	 * @see TopicVO
	 */
	public static final String TAG_SELECT_BY_TOPIC_ID = "{call dbo.spTagsSelectByTopic(?)}";

	/**
	 * Updates Tag.
	 *
	 * @see TagsVO
	 */
	public static final String TAG_UPDATE = "{call dbo.spTagsUpdate(?,?,?)}";

//History
	/**
	 * Retrieves Topic History For Specified TopicID
	 *
	 * @see TopicVO
	 * @see TopicHistoryVO
	 */
	public static final String TOPICHISTORY_GET_BY_TOPIC_ID = "{call dbo.spTopicHistorySelectByTopic(?)}";

	/**
	 * Retrieves Topic History For Specified TopicName
	 *
	 * @see TopicVO
	 * @see TopicHistoryVO
	 */
	public static final String TOPICHISTORY_GET_BY_TOPIC_NAME = "{call dbo.spTopicHistorySelectByTopicName(?)}";

	/**
	 *
	 */
	public static final String TOPICHISTORY_REVERT_TOPIC_BY_HISTORY_ID = "{call dbo.spTopicHistoryRevertTopicByTopicHistoryID(?)}";

//Topic
	/**
	 * Add new Topic
	 *
	 * @see TopicVO
	 */
	public static final String TOPIC_ADD = "{call dbo.spTopicInsert(?,?,?)}";

	/**
	 * Assign ArrayList of Tags to Topic
	 *
	 * @see TopicVO
	 * @see TagsVO
	 */
	public static final String TOPIC_CHANGE_TAG_BINDING = "{call dbo.spTopicChangeTagBinding(?,?,?)}";

	/**
	 * Assign CSV List of TagIDs to Topic
	 * <br>Removes tags not included in CSV
	 *
	 * @see TopicVO
	 * @see TagsVO
	 */
	public static final String TOPIC_ASSIGN_ONLY_TAGS_CSV = "{call [spTopicAssignAllTagBindingByCSV](?,?)}";

	/**
	 * Assign CSV List of Tag Names to Topic
	 * <br>Removes tags not included in CSV
	 *
	 * @see TopicVO
	 * @see TagsVO
	 */
	public static final String TOPIC_ASSIGN_ONLY_TAG_TAG_NAME_CSV = "{call dbo.spTopicAssignAllTagNamesByCSV(?,?)}";

	/**
	 *
	 */
	public static final String TOPIC_SEARCH_BY_KEYWORD = "{call dbo.spTopicSearchByKeyword(?)}";

	/**
	 * <ol><li>p1 - keyword
	 * <ol><li>Multiple keywords sent to full text search as 'OR'</li>
	 * <li>Single keyword (or first word when multiple) checks INFLECTIONS
	 * (Party=Parties=Partied=Partying)</li>
	 * <li>Checks topic's assigned tags tag names.</li>
	 * </ol>
	 * <li>p2 - sort column (accepts name or date)</li>
	 * <li>p3 - max results</li>
	 * </ol>
	 *
	 */
	public static final String TOPIC_SEARCH_BY_KEYWORD_SORTED_PAGED = "{call dbo.[spTopicSearchByKeywordSorted](?,?,?)}";
	/**
	 * TopicID and TopicName are Nullable
	 */
	public static final String TOPIC_SELECT = "{call dbo.spTopicSelect(?,?)}";

	/**
	 * Selects Topics Assigned to Specified Tag
	 *
	 * @see TagsVO
	 * @see TopicVO
	 */
	public static final String TOPIC_SELECT_BY_TAG = "{call dbo.spTopicSelectByTag(?)}";

	/**
	 * Updates Selected Topic
	 *
	 * @see TopicVO
	 */
	public static final String TOPIC_UPDATE = "{call dbo.spTopicUpdate(?,?,?)}";

	/**
	 * Deletes Specified Topic
	 *
	 * @see TopicVO
	 */
	public static final String TOPIC_DELETE = "{call dbo.spTopicDelete(?)}";//[]
//User

	/**
	 * Deletes Specified User
	 *
	 * @see UsersVO
	 */
	public static final String USER_DELETE = "{call dbo.spUsersDelete(?)}";

	/**
	 * Adds new user.
	 *
	 * @see UsersVO
	 */
	public static final String USER_INSERT = "{call dbo.spUsersInsert(?,?,?,?,?)}";

	/**
	 * Select User by {@linkplain UsersVO#userID}
	 *
	 * @see UsersVO
	 */
	public static final String USER_SELECT_BY_ID = "{call dbo.spUsersSelectByID(?)}";

	/**
	 * Select User by {@linkplain UsersVO#userName} and {@linkplain UsersVO#password}
	 * <p>
	 * Verifies user access</p>
	 *
	 * @see UsersVO
	 * @see Login
	 */
	public static final String USER_SELECT_BY_USERNAME_PASSWORD = "{call dbo.spUsersSelectByUserNameAndPassword(?,?)}";
	/**
	 * UserName is Nullable
	 */
	public static final String USERS_SELECT_BY_USERNAME = "{call dbo.spUsersSelect(?)}";

	/**
	 * Updates specified User
	 *
	 * @see UsersVO
	 */
	public static final String USER_UPDATE = "{call dbo.spUsersUpdate(?,?,?,?,?)}";

}
