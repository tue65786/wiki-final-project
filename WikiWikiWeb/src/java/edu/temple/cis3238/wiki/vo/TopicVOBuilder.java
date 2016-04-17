/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.temple.cis3238.wiki.vo;

import java.util.*;
import java.util.concurrent.*;
import java.util.logging.*;

/**
 * Builds Topic VO
 * @author Dan
 */
public class TopicVOBuilder {

private int _topicID;
private String _topicName;
private String _topicContent;
private String _topicCreated;
private String _topicModified;
private int _revisions;
private CopyOnWriteArrayList<TagsVO> _tagsCollection;
private ArrayList<TopicHistoryVO> _topicHistoryCollection;

	/**
	 *
	 */
	public TopicVOBuilder() {
   super();
}

	/**
	 * Sets topicID
	 * @param _topicID
	 * @return
	 */
	public TopicVOBuilder setTopicID(int _topicID) {
   this._topicID = _topicID;
   return this;
}

	/**
	 *
	 * @param _topicName
	 * @return
	 */
	public TopicVOBuilder setTopicName(String _topicName) {
   this._topicName = _topicName;
   return this;
}

	/**
	 *
	 * @param _topicContent
	 * @return
	 */
	public TopicVOBuilder setTopicContent(String _topicContent) {
   this._topicContent = _topicContent;
   return this;
}

	/**
	 *
	 * @param _topicCreated
	 * @return
	 */
	public TopicVOBuilder setTopicCreated(String _topicCreated) {
   this._topicCreated = _topicCreated;
   return this;
}

	/**
	 *
	 * @param _topicModified
	 * @return
	 */
	public TopicVOBuilder setTopicModified(String _topicModified) {
   this._topicModified = _topicModified;
   return this;
}

	/**
	 *
	 * @param _revisions
	 * @return
	 */
	public TopicVOBuilder setRevisions(int _revisions) {
   this._revisions = _revisions;
   return this;
}

	/**
	 *
	 * @param _tagsCollection
	 * @return
	 */
	public TopicVOBuilder setTagsCollection(CopyOnWriteArrayList<TagsVO> _tagsCollection) {
   if ( _tagsCollection != null && !_tagsCollection.isEmpty() ) {
	  try {
		 
		 this._tagsCollection = new CopyOnWriteArrayList<TagsVO>();
		 this._tagsCollection.addAll( _tagsCollection );
	  } catch (Exception e) {
		 LOG.log(Level.WARNING,"Collection set err",e);
	  }
   }
   return this;
}

	/**
	 *
	 * @param _tagsCollection
	 * @return
	 */
	public TopicVOBuilder setTagsCollection(ArrayList<TagsVO> _tagsCollection) {
   if ( _tagsCollection != null && !_tagsCollection.isEmpty() ) {
	  try {
		 Collections.sort( _tagsCollection );
		 this._tagsCollection = new CopyOnWriteArrayList<TagsVO>();
		 this._tagsCollection.addAll( _tagsCollection );
	  } catch (Exception e) {
		 LOG.log(Level.WARNING,"Converted Collection set err",e);
	  }
   }
   return this;
}

	/**
	 *
	 * @param _topicHistoryCollection
	 * @return
	 */
	public TopicVOBuilder set_topicHistoryCollection(ArrayList<TopicHistoryVO> _topicHistoryCollection) {
   this._topicHistoryCollection = _topicHistoryCollection;
   return this;
}

	/**
	 *
	 * @return
	 */
	public TopicVO build() {
   return new TopicVO( _topicID, _topicName, _topicContent, _topicCreated, _topicModified, _revisions, _tagsCollection, _topicHistoryCollection );
}
   private static final Logger LOG = Logger.getLogger( TopicVOBuilder.class.getName() );

}
