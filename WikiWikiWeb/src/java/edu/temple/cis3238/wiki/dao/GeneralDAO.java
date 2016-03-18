/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.temple.cis3238.wiki.dao;

import edu.temple.cis3238.wiki.sql.*;
import edu.temple.cis3238.wiki.vo.*;
import java.util.*;
import java.util.logging.*;

/**
 *
 * @author (c)2016 Guiding Technologies
 */
public class GeneralDAO implements IGeneralDAO {
private static final Logger LOG = Logger.getLogger( GeneralDAO.class.getName() );

private DbConnection dbc;


@Override
public int addTag(TagsVO _vo) {
   throw new UnsupportedOperationException( "Not supported yet." ); //To change body of generated methods, choose Tools | Templates.
}

@Override
public int addTopic(TopicVO _vo) {
   throw new UnsupportedOperationException( "Not supported yet." ); //To change body of generated methods, choose Tools | Templates.
}

@Override
public int addUser(UsersVO _vo) {
   throw new UnsupportedOperationException( "Not supported yet." ); //To change body of generated methods, choose Tools | Templates.
}

@Override
public boolean assignTopicTags(TopicVO _topicVO, ArrayList<TagsVO> _tagsVO) {
   throw new UnsupportedOperationException( "Not supported yet." ); //To change body of generated methods, choose Tools | Templates.
}

@Override
public UsersVO findUserByUserNameAndPassword(String _username, String _password) {
   throw new UnsupportedOperationException( "Not supported yet." ); //To change body of generated methods, choose Tools | Templates.
}

@Override
public TagsVO getTagByID(int _id) {
   throw new UnsupportedOperationException( "Not supported yet." ); //To change body of generated methods, choose Tools | Templates.
}

@Override
public ArrayList<TagsVO> getTags() {
   throw new UnsupportedOperationException( "Not supported yet." ); //To change body of generated methods, choose Tools | Templates.
}

@Override
public ArrayList<TagsVO> getTagsByTopicID(int _topicid) {
   throw new UnsupportedOperationException( "Not supported yet." ); //To change body of generated methods, choose Tools | Templates.
}

@Override
public TopicVO getTopicByID(int _id) {
   throw new UnsupportedOperationException( "Not supported yet." ); //To change body of generated methods, choose Tools | Templates.
}

@Override
public TopicVO getTopicByName(String _name) {
   throw new UnsupportedOperationException( "Not supported yet." ); //To change body of generated methods, choose Tools | Templates.
}

@Override
public ArrayList<TopicHistoryVO> getTopicHistoryByTopicID(int _id) {
   throw new UnsupportedOperationException( "Not supported yet." ); //To change body of generated methods, choose Tools | Templates.
}

@Override
public ArrayList<TopicHistoryVO> getTopicHistoryByTopicName(String _name) {
   throw new UnsupportedOperationException( "Not supported yet." ); //To change body of generated methods, choose Tools | Templates.
}

@Override
public ArrayList<TopicVO> getTopics() {
   throw new UnsupportedOperationException( "Not supported yet." ); //To change body of generated methods, choose Tools | Templates.
}

@Override
public ArrayList<TopicVO> getTopicsByTagID(int _id) {
   throw new UnsupportedOperationException( "Not supported yet." ); //To change body of generated methods, choose Tools | Templates.
}

@Override
public ArrayList<TopicVO> getTopicsByTagName(String _name) {
   throw new UnsupportedOperationException( "Not supported yet." ); //To change body of generated methods, choose Tools | Templates.
}

@Override
public ArrayList<UsersVO> getUsers() {
   throw new UnsupportedOperationException( "Not supported yet." ); //To change body of generated methods, choose Tools | Templates.
}

@Override
public TopicVO revertTopicFromHistory(TopicHistoryVO _vo) {
   throw new UnsupportedOperationException( "Not supported yet." ); //To change body of generated methods, choose Tools | Templates.
}

@Override
public ArrayList<TopicVO> searchTopic(String _query) {
   throw new UnsupportedOperationException( "Not supported yet." ); //To change body of generated methods, choose Tools | Templates.
}

@Override
public boolean unassignTopicTags(TopicVO _topicVO, ArrayList<TagsVO> _tagsVO) {
   throw new UnsupportedOperationException( "Not supported yet." ); //To change body of generated methods, choose Tools | Templates.
}

@Override
public boolean updateTopic(TopicVO _vo) {
   throw new UnsupportedOperationException( "Not supported yet." ); //To change body of generated methods, choose Tools | Templates.
}

@Override
public boolean updateUser(UsersVO _vo) {
   throw new UnsupportedOperationException( "Not supported yet." ); //To change body of generated methods, choose Tools | Templates.
}
public GeneralDAO(DbConnection _dbc) {
   super();
   this.dbc = _dbc;
}

}
