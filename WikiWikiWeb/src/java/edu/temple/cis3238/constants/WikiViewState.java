/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.temple.cis3238.constants;

/**
 *
 */
public enum WikiViewState {

   TOPIC_VIEW_SINGLE_MODE(1),
   TOPICS_ALL_TOPICS_MODE(10),
   TOPICS_SEARCH_MODE(30),
   TOPICS_SEARCH_BY_TAG_MODE(35),
   TOPIC_UPDATE_MODE(50),
   TOPIC_INSERT_MODE(60),
   TOPIC_ROLLBACK(70),
   TAG_VIEW(100);

   
   private final int modeCoded;

   private WikiViewState(int coded) {
	  modeCoded = coded;
   }

}
