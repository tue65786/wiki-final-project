/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.temple.cis3238.wiki.dao;

import edu.temple.cis3238.wiki.vo.*;
import java.util.*;
import java.util.concurrent.*;

/**
 *
 * @author (c)2016 Doreen, Dan, Christian
 */
public interface IGeneralDAO {

//Topic Methods
/**
 * Retrieves single topic
 *
 * @param _name should be as appears on screen. case does not matter
 * @return Topic Object
 */
public TopicVO getTopicByName(String _name);

/**
 * Retrieves single topic
 *
 * @param _id primary key
 * @return
 */
public TopicVO getTopicByID(int _id);

/**
 * Retrieves all topics
 * <br/>By default, assigned tag(s) collection <b><u>are</u></b> populated. <i>Populating tags takes extra time.</i>
 * @return Array of Topic Objects
 */
public ArrayList<TopicVO> getTopics();
/**
 * Retrieves all topics
 * @param populateTopicTags True retrieves assigned TagsVO  stores in  
 * {@linkplain TopicVO#tagsCollection} ( {@linkplain CopyOnWriteArrayList}  &lt;{@linkplain TagsVO}&gt; ).
 * @return ArrayList of TopicVO Objects
 */
public ArrayList<TopicVO> getTopics(boolean populateTopicTags);

/**
 * Retrieves all topics assigned to tag
 * @param _name name of tag
 * @return
 */
public ArrayList<TopicVO> getTopicsByTagName(String _name);

/**
 * Retrieves all topics assigned to tag
 * @param _id primary key
 * @return
 */
public ArrayList<TopicVO> getTopicsByTagID(int _id);
/**
 * Retrieves topics match search query (case insensitive)
 * <br>By default, assigned tag(s) collection <b><u>are</u></b> populated. <i>Populating tags takes extra time.</i>
 * <br>Search includes:
 * <ol>
 * <li>Topic Name</li>
 * <li>Topic Content</li>
 * <li>Assigned Tags</li>
 * <li>Full text search ranked by inflection and similarity  -- top 10 results</li>
 * </ol>
 * @param _query
 * @return 
 */
public ArrayList<TopicVO> searchTopic(String _query);
/**
 * Retrieves topics match search query (case insensitive)
 * <br>Search includes:
 * <ol>
 * <li>Topic Name</li>
 * <li>Topic Content</li>
 * <li>Assigned Tags</li>
 * <li>Full text search ranked by inflection and similarity  -- top 10 results</li>
 * </ol>
 * @param _query 
 * @param loadCollections True retrieves assigned TagsVO  stores in  
 * {@linkplain TopicVO#tagsCollection} ( {@linkplain CopyOnWriteArrayList}  &lt;{@linkplain TagsVO}&gt; ).
 * @return 
 */
public ArrayList<TopicVO> searchTopic(String _query,boolean loadCollections);

/**
 * Insert Topic
 *
 * @param vo
 * @return
 */
public int addTopic(TopicVO vo);

/**
 * Update existing topic
 *
 * @param vo
 * @return success
 */
public boolean updateTopic(TopicVO vo);

//TopicHistory Methods
/**
 * Retrieves audit list of updates to Topic 
 * @param _topicId The id of the {@linkplain TopicVO}
 * @return  ArrayList of {@linkplain TopicHistory} Objects
 */
public ArrayList<TopicHistoryVO> getTopicHistoryByTopicID(int _topicId);
/**
 * Retrieves audit list of updates to Topic 
 * @param _topicName The name of the {@linkplain TopicVO}
 * @return  ArrayList of {@linkplain TopicHistory} Objects
 */
public ArrayList<TopicHistoryVO> getTopicHistoryByTopicName(String _topicName);
/**
 * Reverts topic content from TopicHistory
 * @param vo The TopicHistory Object
 * @return success
 */
public boolean revertTopicFromHistory(TopicHistoryVO vo);

//Tag Methods
/**
 * Retrieves single tag
 * @param _id
 * @return 
 */
public TagsVO getTagByID(int _id);
/**
 * Retrieves single tag
 * @param _name
 * @return 
 */
public TagsVO getTagByName(String _name);

/**
 * Retrieves all tags in database
 *
 * @return
 */
public ArrayList<TagsVO> getTags();

/**
 * Retrieves tags assigned to specified topic
 *
 * @param _topicid {@linkplain TopicVO} primary key
 * @return
 */
public ArrayList<TagsVO> getTagsByTopicID(int _topicid);

/**
 * Unassign specified list tags from topic
 *
 * @param topicVO    The topic
 * @param tagsVOList List of tags
 * @return success
 */
public boolean assignTopicTags(TopicVO topicVO, ArrayList<TagsVO> tagsVOList);

/**
 * Assign tags to topic
 *
 * @param topicVO    The topic
 * @param tagsVOList List of tags
 * @return success
 */
public boolean unassignTopicTags(TopicVO topicVO, ArrayList<TagsVO> tagsVOList);

/**
 * Insert new Tag
 *
 * @param vo Tag to insert
 * @return inserted ID
 */
public int addTag(TagsVO vo);

public boolean deleteTag(TagsVO vo);

//User Method
public UsersVO findUserByUserNameAndPassword(String _username, String _password);

public UsersVO findUserByName(String _username);
/**
 * Retrieves all users
 * @return 
 */
public ArrayList<UsersVO> getUsers();

public UsersVO getUserByID(int _id);//USER_SELECT_BY_ID

/**
 * Add new user
 * @param vo
 * @return inserted ID 
 */
public int addUser(UsersVO vo);
/**
 * Deletes a User
 * @param _vo
 * @return  success
 */
public boolean deleteUser(UsersVO _vo);
/**
 * Update existing user
 * @param _vo UserVO to update. Nulls are ignored
 * @return success
 */
public boolean updateUser(UsersVO _vo);

}
