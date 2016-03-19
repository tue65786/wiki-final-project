/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


package edu.temple.cis3238.wiki.vo;

import java.util.*;


public class TopicVOBuilder {

   private int _topicID;
   private String _topicName;
   private String _topicContent;
   private String _topicCreated;
   private String _topicModified;
   private int _revisions;
   private ArrayList<TagsVO> _tagsCollection;
   private ArrayList<TopicHistoryVO> _topicHistoryCollection;

   public TopicVOBuilder() {
	  super();
   }

   public TopicVOBuilder setTopicID(int _topicID) {
	  this._topicID = _topicID;
	  return this;
   }

   public TopicVOBuilder setTopicName(String _topicName) {
	  this._topicName = _topicName;
	  return this;
   }

   public TopicVOBuilder setTopicContent(String _topicContent) {
	  this._topicContent = _topicContent;
	  return this;
   }

   public TopicVOBuilder setTopicCreated(String _topicCreated) {
	  this._topicCreated = _topicCreated;
	  return this;
   }

   public TopicVOBuilder setTopicModified(String _topicModified) {
	  this._topicModified = _topicModified;
	  return this;
   }

   public TopicVOBuilder setRevisions(int _revisions) {
	  this._revisions = _revisions;
	  return this;
   }

   public TopicVOBuilder setTagsCollection(ArrayList<TagsVO> _tagsCollection) {
	  this._tagsCollection = _tagsCollection;
	  return this;
   }

   public TopicVOBuilder set_topicHistoryCollection(ArrayList<TopicHistoryVO> _topicHistoryCollection) {
	  this._topicHistoryCollection = _topicHistoryCollection;
	  return this;
   }

   public TopicVO build() {
	  return new TopicVO( _topicID, _topicName, _topicContent, _topicCreated, _topicModified, _revisions, _tagsCollection, _topicHistoryCollection );
   }

}