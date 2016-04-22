/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


package edu.temple.cis3238.wiki;

import edu.temple.cis3238.constants.PAGE_COMMAND;
import edu.temple.cis3238.wiki.ui.beans.CurrentUser;
import edu.temple.cis3238.wiki.vo.*;
import java.io.Serializable;
import java.util.*;
import javax.servlet.http.HttpSession;

/**
 * Home Controller Engine
 * @author d
 */
public class WikiEngine implements Serializable {  
   private static final long serialVersionUID = 8139564225677306305L;

   private static Map<String, HttpSession> sessions = new HashMap<String, HttpSession>();
   private static Map<String, CurrentUser> users = new HashMap<String, CurrentUser>();
   private static Map<String, String> usernameMapToSession;
   private final TopicVO HOME_TOPIC;
		   
  private TopicVO currentWikiTopic;
  private ArrayList<TopicVO> allTopics;
  private PAGE_COMMAND viewMode;
  private UsersVO currentUser;
  
  public WikiEngine(){
	  
	 HOME_TOPIC = new TopicVOBuilder().setTopicID(1).build();
	 
  }
  
   
}
