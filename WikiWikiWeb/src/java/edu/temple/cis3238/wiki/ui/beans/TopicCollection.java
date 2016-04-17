/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.temple.cis3238.wiki.ui.beans;

import edu.temple.cis3238.wiki.vo.*;
import java.io.*;
import java.util.*;
import java.util.logging.*;

/**
 * In-memory {@linkplain HttpSession} store for Topics
 *
 * @author Christian, Doreen, Dan
 * @see TopicVO
 * @see HttpSession
 * @see HttpServletRequest
 */
public class TopicCollection implements Serializable {

	private static final Logger LOG = Logger.getLogger(TopicCollection.class.getName());
	private static final long serialVersionUID = 7059187297992104099L;

	public String listType;
	private TopicVO currentTopic;
	private ArrayList<TopicVO> topics;

	/**
	 * @return the currentTopic
	 */
	public TopicVO getCurrentTopic() {
		return currentTopic;
	}

	public boolean isCurrentTopicHistoryLoaded() {
		return currentTopic != null 
				&& currentTopic.getTopicHistoryCollection() != null 
				&& !currentTopic.getTopicHistoryCollection().isEmpty();
	}

	/**
	 * @param currentTopic the currentTopic to set
	 */
	public void setCurrentTopic(TopicVO currentTopic) {
		this.currentTopic = currentTopic;
	}

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

	public TopicCollection() {
		topics = new ArrayList<>();
		currentTopic = new TopicVO("", "");
		listType = "TABLE";
	}
}
