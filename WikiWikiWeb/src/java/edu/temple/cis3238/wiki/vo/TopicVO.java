/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.temple.cis3238.wiki.vo;

import edu.temple.cis3238.wiki.utils.*;
import java.io.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.logging.*;
import org.apache.commons.lang3.builder.*;

/**
 * Topic Entity
 * @author (c)2016 Doreen, Dan, Christian
 */
public class TopicVO implements Serializable, Comparable<TopicVO>, IValueObject<TopicVO> {

private static final Logger LOG = Logger.getLogger( TopicVO.class.getName() );
private static final long serialVersionUID = -4450521690544636648L;
private int revisions;
private TagsVO[] tagsArray;
private CopyOnWriteArrayList<TagsVO> tagsCollection;
private String topicContent;
private String topicCreated;
private ArrayList<TopicHistoryVO> topicHistoryCollection;
private int topicID;
private String topicModified;
private String topicName;

	/**
	 *
	 * @param vo
	 * @return
	 */
	public static TopicVO newInstance(TopicVO vo) {
   return new TopicVOBuilder()
		   .setTopicID( vo.getTopicID() )
		   .setTopicName( vo.getTopicName() )
		   .setTopicContent( vo.getTopicContent() )
		   .setTopicCreated( vo.getTopicCreated() )
		   .setTopicModified( vo.getTopicModified() )
		   .setRevisions( vo.getRevisions() )
		   .setTagsCollection( vo.getTagsCollection() )
		   .set_topicHistoryCollection( vo.getTopicHistoryCollection() )
		   .build();
}

@Override
public int compareTo(TopicVO _that) {
   if ( _that == null ) {
	  return 1;
   }
   CompareToBuilder ctb = new CompareToBuilder();
   ctb.append( this.getTopicID() > 0, _that.getTopicID() > 0 );
   ctb.append( this.getTopicID(), _that.getTopicID() );
   ctb.append( this.getTopicName().toLowerCase().trim(), _that.getTopicName().toLowerCase().trim() );
   return ctb.build();
}

@Override
public boolean equals(Object obj) {
   if ( this == obj ) {
	  return true;
   }
   if ( obj == null ) {
	  return false;
   }
   if ( getClass() != obj.getClass() ) {
	  return false;
   }
   final TopicVO other = (TopicVO) obj;
   if ( this.topicID != other.topicID ) {
	  return false;
   }
   if ( !Objects.equals( this.getTopicContent(), other.getTopicContent() ) ) {
	  return false;
   }
   if ( !Objects.equals( this.getTopicName(), other.getTopicName() ) ) {
	  return false;
   }
   return true;
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
public CopyOnWriteArrayList<TagsVO> getTagsCollection() {
   return tagsCollection;
}

	/**
	 *
	 * @param tagsCollection
	 */
	public void setTagsCollection(CopyOnWriteArrayList<TagsVO> tagsCollection) {
   if ( tagsCollection != null && !tagsCollection.isEmpty() ) {
	  this.tagsCollection = new CopyOnWriteArrayList<TagsVO>();
	  tagsCollection.sort( new TagsVO() );
	  this.tagsCollection.addAll( tagsCollection );
   }
}
/**
 * @param tagsCollection the tagsCollection to set
 */
public void setTagsCollection(ArrayList<TagsVO> tagsCollection) {
   if ( tagsCollection != null && !tagsCollection.isEmpty() ) {
	  this.tagsCollection = new CopyOnWriteArrayList<TagsVO>();
	  Collections.sort( tagsCollection );
	  this.tagsCollection.addAll( tagsCollection );
   }
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
   return StringUtils.stripInvalidChars( topicName );
}

/**
 * @param topicName the topicName to set
 */
public void setTopicName(String topicName) {
   this.topicName = StringUtils.stripInvalidChars( topicName );
}

	/**
	 *
	 * @return
	 */
	public boolean hasTagsCollection() {
   return ( tagsCollection != null && !tagsCollection.isEmpty() );
}
@Override
public int hashCode() {
   int hash = 5;
   hash = 59 * hash + this.revisions;
   hash = 59 * hash + Objects.hashCode( this.getTopicContent() );
   hash = 59 * hash + this.topicID;
   hash = 59 * hash + Objects.hashCode( this.getTopicName() );
   return hash;
}

@Override
public String toString() {
   return "TopicVO{" + "revisions=" + revisions + ", topicContent=" + getTopicContent() + ", topicCreated=" + getTopicCreated() + ", topicID=" + topicID + ", topicModified=" + getTopicModified() + ", topicName=" + getTopicName() + '}';
}

	/**
	 *
	 * @return
	 */
	@Override
public String toTableRow() {
   return "<tr><td>" + revisions + "</td><td>" + getTopicContent() + "</td><td>" + getTopicCreated() + "</td><td>" + topicID + "</td><td>" + getTopicModified() + "</td><td>" + getTopicName() + "</td></tr>";
}

	/**
	 *
	 * @param _topicID
	 * @param _topicName
	 * @param _topicContent
	 * @param _topicCreated
	 * @param _topicModified
	 * @param _revisions
	 */
	public TopicVO(int _topicID, String _topicName, String _topicContent, String _topicCreated, String _topicModified, int _revisions) {
   this.topicID = _topicID;
   this.topicName = _topicName;
   this.topicContent = _topicContent;
   this.topicCreated = _topicCreated;
   this.topicModified = _topicModified;
   this.revisions = _revisions;
}

	/**
	 *
	 * @param _topicID
	 * @param _topicName
	 * @param _topicContent
	 * @param _topicCreated
	 * @param _topicModified
	 * @param _revisions
	 * @param _tagsCollection
	 * @param _topicHistoryCollection
	 */
	public TopicVO(int _topicID, String _topicName, String _topicContent, String _topicCreated, String _topicModified, int _revisions, ArrayList<TagsVO> _tagsCollection, ArrayList<TopicHistoryVO> _topicHistoryCollection) {
   this.topicID = _topicID;
   this.topicName = _topicName;
   this.topicContent = _topicContent;
   this.topicCreated = _topicCreated;
   this.topicModified = _topicModified;
   this.revisions = _revisions;
   if ( _tagsCollection != null && !_tagsCollection.isEmpty() ) {
	  try {
		 this.tagsCollection = new CopyOnWriteArrayList<TagsVO>( _tagsCollection );
	  } catch (Exception e) {

	  }
   }
   this.topicHistoryCollection = _topicHistoryCollection;
}

	/**
	 *
	 * @param _topicID
	 * @param _topicName
	 * @param _topicContent
	 * @param _topicCreated
	 * @param _topicModified
	 * @param _revisions
	 * @param _tagsCollection
	 * @param _topicHistoryCollection
	 */
	public TopicVO(int _topicID, String _topicName, String _topicContent, String _topicCreated, String _topicModified, int _revisions, CopyOnWriteArrayList<TagsVO> _tagsCollection, ArrayList<TopicHistoryVO> _topicHistoryCollection) {
   this.topicID = _topicID;
   this.topicName = _topicName;
   this.topicContent = _topicContent;
   this.topicCreated = _topicCreated;
   this.topicModified = _topicModified;
   this.revisions = _revisions;
   this.tagsCollection = _tagsCollection;
   this.topicHistoryCollection = _topicHistoryCollection;
}

/**
 * Constructs a topic for INSERT
 *
 * @param _topicName
 * @param _topicContent
 */
public TopicVO(String _topicName, String _topicContent) {
   this.topicContent = _topicContent;
   this.topicName = _topicName;
   this.topicCreated = "";
   this.topicModified = "";
   this.revisions = 0;
}

}
