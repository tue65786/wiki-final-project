/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


package edu.temple.cis3238.wiki.ui.beans;

import edu.temple.cis3238.wiki.vo.*;
import java.io.*;
import java.util.*;

/**
 *
 * @author (c)2016 
 */
public class TopicCollection implements Serializable{

   private static final long serialVersionUID = 7059187297992104099L;

   /**
    * @return the listType
    */
   public String getListType() {
	  return listType;
   }

   /**
    * @param listType the listType to set
    */
   public void setListType(String listType) {
	  this.listType = listType;
   }
public TopicCollection(){
   
}
   private ArrayList<TopicVO> topics;
   public String listType = "TABLE";
   /**
    * @return the topics
    */
   public ArrayList<TopicVO> getTopics() {
	  return topics;
   }

   /**
    * @param topics the topics to set
    */
   public void setTopics(ArrayList<TopicVO> topics) {
	  this.topics = topics;
   }
}
