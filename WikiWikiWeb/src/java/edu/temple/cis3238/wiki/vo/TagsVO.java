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
import org.apache.commons.lang3.builder.*;

/**
 *
 * @author (c)2016 Doreen, Dan, Christian
 */
public class TagsVO implements Serializable, Comparable<TagsVO>, IValueObject<TagsVO>, Comparator<TagsVO> {

   private static final Logger LOG = Logger.getLogger(TagsVO.class.getName());

   private static final long serialVersionUID = -6547517199465130892L;
   private int parentTagId;
   private int tagID;
   public String tagName;
   private int topicCount;

   public static TagsVO newInstance(TagsVO vo) {
	  return new TagsVO(vo.getTagID(), vo.getTagName(), vo.getParentTagId(), vo.getTopicCount());
   }

   @Override
   public int compare(TagsVO _left, TagsVO _right) {
	  if (_left == null && _right == null) {
		 return 1;
	  }
	  return new CompareToBuilder()
			  .append(_left.getTagName().toLowerCase(), _right.getTagName().toLowerCase())
			  .build();
   }

   @Override
   public int compareTo(TagsVO _that) {
	  if (_that == null) {
		 return 1;
	  }
	  return new CompareToBuilder()
			  .append(this.getTagName().toLowerCase(), _that.getTagName().toLowerCase())
			  .append(this.getTagID(), _that.getTagID())
			  .build();
   }

   @Override
   public boolean equals(Object obj) {
	  if (this == obj) {
		 return true;
	  }
	  if (obj == null) {
		 return false;
	  }
	  if (getClass() != obj.getClass()) {
		 return false;
	  }
	  final TagsVO other = (TagsVO) obj;

	  if (!Objects.equals(this.getTagName().toLowerCase().trim(), other.getTagName().toLowerCase())) {
		 return false;
	  }
	  return true;
   }

   /**
    * @return the parentTagId
    */
   public int getParentTagId() {
	  return parentTagId;
   }

   /**
    * @param parentTagId the parentTagId to set
    */
   public void setParentTagId(int parentTagId) {
	  this.parentTagId = parentTagId;
   }

   /**
    * @return the tagID
    */
   public int getTagID() {
	  return tagID;
   }

   /**
    * @param tagID the tagID to set
    */
   public void setTagID(int tagID) {
	  this.tagID = tagID;
   }

   /**
    * @return the tagName
    */
   public String getTagName() {
	  return StringUtils.toS(tagName);
   }

   /**
    * @param tagName the tagName to set
    */
   public void setTagName(String tagName) {
	  this.tagName = tagName;
   }

   /**
    * @return the topicCount
    */
   public int getTopicCount() {
	  return topicCount;
   }

   /**
    * @param topicCount the topicCount to set
    */
   public void setTopicCount(int topicCount) {
	  this.topicCount = topicCount;
   }

   @Override
   public int hashCode() {
	  int hash = 7;
	  hash = 89 * hash + Objects.hashCode(this.getTagName().toLowerCase().trim());
	  return hash;
   }

   @Override
   public String toString() {
	  return "TagsVO{" + "tagID=" + tagID + ", tagName=" + getTagName() + " , topicCount=" + getTopicCount() + ", parentTagId=" + parentTagId + '}';
   }

   @Override
   public String toTableRow() {
	  return "<tr><td>" + tagID + "</td><td>" + getTagName() + "</td><td>" + getTopicCount() + "</td><td>" + parentTagId + "</td></tr>";
   }

   public TagsVO() {
	  super();
   }

   public TagsVO(int _tagID, String _tagName, int _parentTagId) {
	  super();
	  this.tagID = _tagID;
	  this.tagName = _tagName;
	  this.parentTagId = _parentTagId;
   }

   public TagsVO(int _tagID, String _tagName, int _parentTagId, int _topicCount) {
	  super();
	  this.tagID = _tagID;
	  this.tagName = _tagName;
	  this.parentTagId = _parentTagId;
	  this.topicCount = _topicCount;
   }
}
