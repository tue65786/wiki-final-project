/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.temple.cis3238.wiki.dao;

/**
 *
 * @author (c)2016 Doreen, Dan, Christian
 */
public class DB_STRINGS {

//Tags
public static final String TAG_ADD = "{call dbo.spTagsInsert(?,?,?)}";
public static final String TAG_DELETE = "{call dbo.spTagsDelete(?)}";
/**
 * TagID and TagName are Nullable
 */
public static final String TAG_SELECT= "{call dbo.spTagsSelect(?,?)}";
public static final String TAG_SELECT_BY_TOPIC_ID= "{call dbo.spTagsSelectByTopic(?)}";
public static final String TAG_UPDATE= "{call dbo.spTagsUpdate(?,?,?)}";

//History
public static final String TOPICHISTORY_GET_BY_TOPIC_ID = "{call dbo.spTopicHistorySelectByTopic(?)}";
public static final String TOPICHISTORY_GET_BY_TOPIC_NAME = "{call dbo.spTopicHistorySelectByTopicName(?)}";
public static final String TOPICHISTORY_REVERT_TOPIC_BY_HISTORY_ID= "{call dbo.spTopicHistoryRevertTopicByTopicHistoryID(?)}";

//Topic
public static final String TOPIC_ADD = "{call dbo.spTopicInsert(?,?,?)}";
public static final String TOPIC_CHANGE_TAG_BINDING = "{call dbo.spTopicChangeTagBinding(?,?,?)}";
public static final String TOPIC_ASSIGN_ONLY_TAGS_CSV = "{call [spTopicAssignAllTagBindingByCSV](?,?)}";
public static final String TOPIC_ASSIGN_ONLY_TAG_TAG_NAME_CSV = "{call dbo.spTopicAssignAllTagNamesByCSV(?,?)}";

public static final String TOPIC_SEARCH_BY_KEYWORD = "{call dbo.spTopicSearchByKeyword(?)}";

/**
 * <ol><li>p1 - keyword 
 * <ol><li>Multiple keywords sent to full text search as 'OR'</li>
 * <li>Single keyword (or first word when multiple) checks INFLECTIONS (Party=Parties=Partied=Partying)</li>
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
public static final String TOPIC_SELECT_BY_TAG = "{call dbo.spTopicSelectByTag(?)}";
public static final String TOPIC_UPDATE = "{call dbo.spTopicUpdate(?,?,?)}";
public static final String TOPIC_DELETE = "{call dbo.spTopicDelete(?)}";//[]
//User
public static final String USER_DELETE = "{call dbo.spUsersDelete(?)}";
public static final String USER_INSERT = "{call dbo.spUsersInsert(?,?,?,?,?)}";
public static final String USER_SELECT_BY_ID = "{call dbo.spUsersSelectByID(?)}";
public static final String USER_SELECT_BY_USERNAME_PASSWORD = "{call dbo.spUsersSelectByUserNameAndPassword(?,?)}";
/**
 * UserName is Nullable
 */
public static final String USERS_SELECT_BY_USERNAME = "{call dbo.spUsersSelect(?)}";
public static final String USER_UPDATE = "{call dbo.spUsersUpdate(?,?,?,?,?)}";



}

