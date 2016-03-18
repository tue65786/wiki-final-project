/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.temple.cis3238.wiki.vo;

import java.io.*;
import java.util.logging.*;

/**
 *
 * @author (c)2016 Guiding Technologies
 */
public class TagsVO implements Serializable {

private static final long serialVersionUID = -6547517199465130892L;
private int tagID;
private String tagName;
private int parentTagId;
private static final Logger LOG = Logger.getLogger( TagsVO.class.getName() );

public static TagsVO newInstance(TagsVO vo){
   return new TagsVO(vo.getTagID(), vo.getTagName(), vo.getParentTagId());
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
	  return tagName;
   }

   /**
    * @param tagName the tagName to set
    */
   public void setTagName(String tagName) {
	  this.tagName = tagName;
   }
public TagsVO(){
   super();
}
   public TagsVO(int _tagID, String _tagName, int _parentTagId) {
	  super();
	  this.tagID = _tagID;
	  this.tagName = _tagName;
	  this.parentTagId = _parentTagId;
   }
}
