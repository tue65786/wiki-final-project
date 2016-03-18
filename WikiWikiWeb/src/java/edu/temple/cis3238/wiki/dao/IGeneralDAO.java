/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.temple.cis3238.wiki.dao;

import edu.temple.cis3238.wiki.vo.*;
import java.util.*;

/**
 *
 * @author (c)2016 Guiding Technologies
 */
public interface IGeneralDAO {


//Topic Methods
public TopicVO getTopicByName(String _name);
public TopicVO getTopicByID(int _id);
public ArrayList<TopicVO> getTopics();
public ArrayList<TopicVO> getTopicsByTagName(String _name);
public ArrayList<TopicVO> getTopicsByTagID(int _id);
public ArrayList<TopicVO> searchTopic(String _query);
public int addTopic(TopicVO vo);
public boolean updateTopic(TopicVO vo);

//TopicHistory Methods
public ArrayList<TopicHistoryVO> getTopicHistoryByTopicID(int _id);
public ArrayList<TopicHistoryVO> getTopicHistoryByTopicName(String _name);
public TopicVO revertTopicFromHistory(TopicHistoryVO vo);


//Tag Methods
public TagsVO getTagByID(int _id);
public ArrayList<TagsVO> getTags();
public ArrayList<TagsVO> getTagsByTopicID(int _topicid);
public boolean assignTopicTags(TopicVO topicVO, ArrayList<TagsVO> tagsVO);
public boolean unassignTopicTags(TopicVO topicVO, ArrayList<TagsVO> tagsVO);
public int addTag(TagsVO vo);

//User Method
public UsersVO findUserByUserNameAndPassword(String _username, String _password);
public ArrayList<UsersVO> getUsers();
public int addUser(UsersVO vo);
public boolean updateUser(UsersVO vo);

   
}
