/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.temple.cis3238.wiki.dao;

/**
 *
 * @author (c)2016 Guiding Technologies
 */
public class DB_STRINGS {

//Tags
public static final String TAG_ADD = "{call dbo.spTagsInsert(?,?,?)}";
public static final String TAG_DELETE = "{call dbo.spTagsDelete(?)}";
public static final String TAG_SELECT= "{call dbo.spTagsSelect(?,?)}";
public static final String TAG_SELECT_BY_TOPIC= "{call dbo.spTagsSelectByTopic(?)}";
public static final String TAG_UPDATE= "{call dbo.spTagsUpdate(?,?,?)}";

//History
public static final String TOPICHISTORY_GET_BY_TOPIC = "{call dbo.spTopicHistorySelectByTopic(?)}";

//Topic
public static final String TOPIC_ADD = "{call dbo.spTopicInsert(?,?,?)}";
public static final String TOPIC_CHANGE_TAG_BINDING = "{call dbo.spTopicChangeTagBinding(?,?,?)}";
public static final String TOPIC_SEARCH_BY_KEYWORD = "{call dbo.spTopicSearchByKeyword(?)}";
public static final String TOPIC_SELECT = "{call dbo.spTopicSelect(?,?)}";
public static final String TOPIC_SELECT_BY_TAG = "{call dbo.spTopicSelectByTag(?)}";
public static final String TOPIC_UPDATE = "{call dbo.spTopicUpdate(?,?,?)}";

//User
public static final String USER_DELETE = "{call dbo.spUsersDelete(?)}";
public static final String USER_INSERT = "{call dbo.spUsersInsert(?,?,?,?,?)}";
public static final String USER_SELECT_BY_ID = "{call dbo.spUsersSelectByID(?)}";
public static final String USER_SELECT_BY_USERNAME_PASSWORD = "{call dbo.spUsersSelectByUserNameAndPassword(?,?)}";
public static final String USER_UPDATE = "{call dbo.spUsersUpdate(?,?,?,?,?)}";



}

