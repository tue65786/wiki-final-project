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
 * @author (c)2016 Guiding Technologies
 */
public class TagsVO implements Serializable, Comparable<TagsVO>,IValueObject<TagsVO> {
private static final Logger LOG = Logger.getLogger( TagsVO.class.getName() );

private static final long serialVersionUID = -6547517199465130892L;
private int parentTagId;
private int tagID;
private String tagName;

public static TagsVO newInstance(TagsVO vo) {
   return new TagsVO( vo.getTagID(), vo.getTagName(), vo.getParentTagId() );
}

@Override
public int compareTo(TagsVO _that) {
   if ( _that == null ) {
	  return 1;
   }
   return new CompareToBuilder()
		   .append( this.getTagID(), _that.getTagID() )
		   .append( this.getTagName(), _that.getTagName() )
		   .build();
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
   final TagsVO other = (TagsVO) obj;
   if ( this.tagID != other.tagID ) {
	  return false;
   }
   if ( !Objects.equals( this.getTagName(), other.getTagName() ) ) {
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
   return StringUtils.toS( tagName );
}

/**
 * @param tagName the tagName to set
 */
public void setTagName(String tagName) {
   this.tagName = tagName;
}

@Override
public int hashCode() {
   int hash = 7;
   hash = 89 * hash + this.tagID;
   hash = 89 * hash + Objects.hashCode( this.getTagName() );
   return hash;
}

@Override
public String toString() {
   return "TagsVO{" + "tagID=" + tagID + ", tagName=" + getTagName() + ", parentTagId=" + parentTagId + '}';
}

public String toTableRow() {
   return "<tr><td>" + tagID + "</td><td>" + getTagName() + "</td><td>" + parentTagId + "</td></tr>";
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
}
