/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.temple.cis3238.wiki.vo;

import edu.temple.cis3238.wiki.utils.*;
import java.io.*;
import java.util.*;
import java.util.logging.*;

/**
 *
 * @author (c)2016 Guiding Technologies
 */
public class TopicHistoryVO implements Serializable {

private static final Logger LOG = Logger.getLogger( TopicHistoryVO.class.getName() );

private static final long serialVersionUID = 2051694591563091203L;
private String topicConent;
private String topicHistoryCreated;
private int topicHistoryID;
private int topicID;

public static TopicHistoryVO newInstance(TopicHistoryVO _vo) {
   return new TopicHistoryVO( _vo.getTopicHistoryID(), _vo.getTopicConent(), _vo.getTopicHistoryCreated(), _vo.getTopicID() );
}

@Override
public boolean equals(Object _obj) {
   if ( this == _obj ) {
	  return true;
   }
   if ( _obj == null ) {
	  return false;
   }
   if ( getClass() != _obj.getClass() ) {
	  return false;
   }
   final TopicHistoryVO other = (TopicHistoryVO) _obj;
   if ( this.getTopicHistoryID() != other.getTopicHistoryID() ) {
	  return false;
   }
   if ( this.getTopicID() != other.getTopicID() ) {
	  return false;
   }
   if ( !Objects.equals( this.topicConent, other.topicConent ) ) {
	  return false;
   }
   return true;
}

/**
 * @return the topicConent
 */
public String getTopicConent() {
   return StringUtils.toS( topicConent );
}

/**
 * @param topicConent the topicConent to set
 */
public void setTopicConent(String topicConent) {
   this.topicConent = topicConent;
}

/**
 * @return the topicHistoryCreated
 */
public String getTopicHistoryCreated() {
   return StringUtils.toS( topicHistoryCreated );
}

/**
 * @param topicHistoryCreated the topicHistoryCreated to set
 */
public void setTopicHistoryCreated(String topicHistoryCreated) {
   this.topicHistoryCreated = topicHistoryCreated;
}

/**
 * @return the topicHistoryID
 */
public int getTopicHistoryID() {
   return topicHistoryID;
}

/**
 * @param topicHistoryID the topicHistoryID to set
 */
public void setTopicHistoryID(int topicHistoryID) {
   this.topicHistoryID = topicHistoryID;
}

/**
 * @return the topicID
 */
public int getTopicID() {
   return topicID;
}

/**
 * @param topicID the topicID to set
 */
public void setTopicID(int topicID) {
   this.topicID = topicID;
}

@Override
public int hashCode() {
   int hash = 3;
   hash = 73 * hash + this.getTopicHistoryID();
   hash = 73 * hash + Objects.hashCode( this.getTopicConent() );
   hash = 73 * hash + this.getTopicID();
   return hash;
}

@Override
public String toString() {
   return "TopicHistoryVO{" + "topicHistoryID=" + getTopicHistoryID() + ", topicConent=" + getTopicConent() + ", topicHistoryCreated=" + getTopicHistoryCreated() + ", topicID=" + getTopicID() + '}';
}

public TopicHistoryVO(int _topicHistoryID, String _topicConent, String _topicHistoryCreated, int _topicID) {
   this.topicHistoryID = _topicHistoryID;
   this.topicConent = _topicConent;
   this.topicHistoryCreated = _topicHistoryCreated;
   this.topicID = _topicID;
}
}
