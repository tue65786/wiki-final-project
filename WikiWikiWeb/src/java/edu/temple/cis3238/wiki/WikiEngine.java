/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


package edu.temple.cis3238.wiki;

import edu.temple.cis3238.constants.WikiViewState;
import edu.temple.cis3238.wiki.vo.*;
import java.io.Serializable;
import java.util.ArrayList;


public class WikiEngine implements Serializable {
   ////////////////////
   //// Incomplete
   //////////////////////
   private static final long serialVersionUID = 8139564225677306305L;

   
   private final TopicVO HOME_TOPIC;
		   
  private TopicVO currentWikiTopic;
  private ArrayList<TopicVO> allTopics;
  private WikiViewState viewMode;
  private UsersVO currentUser;
  
  public WikiEngine(){
	 HOME_TOPIC = new TopicVOBuilder().setTopicID(1).build();
	 
  }
  
   
}
