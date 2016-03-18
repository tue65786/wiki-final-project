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
public class TopicHistoryVO implements Serializable {

   private static final long serialVersionUID = 2051694591563091203L;
  private int topicHistoryID;
   private String topicConent;
   private String topicHistoryCreated;
   private int topicID;
   private static final Logger LOG = Logger.getLogger( TopicHistoryVO.class.getName() );

   
   public TopicHistoryVO(int _topicHistoryID, String _topicConent, String _topicHistoryCreated, int _topicID) {
	  this.topicHistoryID = _topicHistoryID;
	  this.topicConent = _topicConent;
	  this.topicHistoryCreated = _topicHistoryCreated;
	  this.topicID = _topicID;
   }
}
