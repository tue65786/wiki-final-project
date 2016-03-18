/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.temple.cis3238.wiki.vo;

import java.io.*;
import java.util.*;
import java.util.logging.*;

/**
 *
 * @author (c)2016 Guiding Technologies
 */
public class TopicVO implements Serializable {

private static final Logger LOG = Logger.getLogger( TopicVO.class.getName() );

private static final long serialVersionUID = -4450521690544636648L;
private int revisions;
private ArrayList<TagsVO> tagsCollection;
private String topicContent;
private String topicCreated;
private ArrayList<TopicHistoryVO> topicHistoryCollection;
private int topicID;
private String topicModified;
private String topicName;

public static TopicVO newInstance(TopicVO vo) {
   return new TopicVOBuilder().setTopicID( vo.getTopicID() ).setTopicName( vo.getTopicName() ).setTopicContent( vo.getTopicContent() ).setTopicCreated( vo.getTopicCreated() ).setTopicModified( vo.getTopicModified() ).setRevisions( vo.getRevisions() ).setTagsCollection( vo.getTagsCollection() ).set_topicHistoryCollection( vo.getTopicHistoryCollection() ).build();
}

/**
 * @return the revisions
 */
public int getRevisions() {
   return revisions;
}

/**
 * @param revisions the revisions to set
 */
public void setRevisions(int revisions) {
   this.revisions = revisions;
}

/**
 * @return the tagsCollection
 */
public ArrayList<TagsVO> getTagsCollection() {
   return tagsCollection;
}

/**
 * @param tagsCollection the tagsCollection to set
 */
public void setTagsCollection(ArrayList<TagsVO> tagsCollection) {
   this.tagsCollection = tagsCollection;
}

/**
 * @return the topicContent
 */
public String getTopicContent() {
   return topicContent;
}

/**
 * @param topicContent the topicContent to set
 */
public void setTopicContent(String topicContent) {
   this.topicContent = topicContent;
}

/**
 * @return the topicCreated
 */
public String getTopicCreated() {
   return topicCreated;
}

/**
 * @param topicCreated the topicCreated to set
 */
public void setTopicCreated(String topicCreated) {
   this.topicCreated = topicCreated;
}

/**
 * @return the topicHistoryCollection
 */
public ArrayList<TopicHistoryVO> getTopicHistoryCollection() {
   return topicHistoryCollection;
}

/**
 * @param topicHistoryCollection the topicHistoryCollection to set
 */
public void setTopicHistoryCollection(ArrayList<TopicHistoryVO> topicHistoryCollection) {
   this.topicHistoryCollection = topicHistoryCollection;
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

/**
 * @return the topicModified
 */
public String getTopicModified() {
   return topicModified;
}

/**
 * @param topicModified the topicModified to set
 */
public void setTopicModified(String topicModified) {
   this.topicModified = topicModified;
}

/**
 * @return the topicName
 */
public String getTopicName() {
   return topicName;
}

/**
 * @param topicName the topicName to set
 */
public void setTopicName(String topicName) {
   this.topicName = topicName;
}

public TopicVO(int _topicID, String _topicName, String _topicContent, String _topicCreated, String _topicModified, int _revisions) {
   this.topicID = _topicID;
   this.topicName = _topicName;
   this.topicContent = _topicContent;
   this.topicCreated = _topicCreated;
   this.topicModified = _topicModified;
   this.revisions = _revisions;
}

public TopicVO(int _topicID, String _topicName, String _topicContent, String _topicCreated, String _topicModified, int _revisions, ArrayList<TagsVO> _tagsCollection, ArrayList<TopicHistoryVO> _topicHistoryCollection) {
   this.topicID = _topicID;
   this.topicName = _topicName;
   this.topicContent = _topicContent;
   this.topicCreated = _topicCreated;
   this.topicModified = _topicModified;
   this.revisions = _revisions;
   this.tagsCollection = _tagsCollection;
   this.topicHistoryCollection = _topicHistoryCollection;
}

}
