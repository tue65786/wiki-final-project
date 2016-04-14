/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.temple.cis3238.wiki.ui.tags.helpers;

import edu.temple.cis3238.wiki.parser.IPredicate;
import edu.temple.cis3238.wiki.utils.CollectionsUtilities;
import edu.temple.cis3238.wiki.vo.TopicVO;
import java.util.ArrayList;
import java.util.logging.*;

/**
 *
 * Filters topics to single {@linkplain TopicVO} matching specified {@linkplain TopicVO#topicID }
 */

public class TopicByTopicIDPredicate implements IPredicate<TopicVO> {
	private static final Logger LOG = Logger.getLogger(TopicByTopicIDPredicate.class.getName());
	private int topicID;
	private ArrayList<TopicVO> voList;
	/**
	 * Filter topic array by ID factory method
	 * @param _voList topicVO array
	 * @param _topicID key
	 * @return Prepared instance. Usage: call {@linkplain #apply()} 
	 */
	public static TopicByTopicIDPredicate create(ArrayList<TopicVO> _voList, int _topicID) {
		return new TopicByTopicIDPredicate(_voList, _topicID);
	}

	private TopicByTopicIDPredicate(ArrayList<TopicVO> _voList,  int _topicID) {
		this.voList = _voList;
		this.topicID = _topicID;
	}
/**
 * Filter Topics 
 * @return Matched topic
 */	
   public TopicVO apply(){
	   ArrayList<TopicVO> filterVOList;
     if (voList == null || voList.isEmpty() || topicID <= 0){
		 throw new NullPointerException("edu.temple.cis3238.wiki.ui.tags.helpers.TopicByTopicIDPredicate.apply()" +
				 " - Can not filter. Either array is null or topicID not specified.");
	 }
	 
	   filterVOList = CollectionsUtilities.filterList(voList, this);
	if (filterVOList.isEmpty()){
		LOG.logp(Level.WARNING, this.getClass().getName()
				, "apply()"
				, new StringBuilder()
						.append("Could not find topic for topicID[ ")
						.append(topicID).append(" ].")
						.toString());
	return null;
	} 
	return filterVOList.get(0);
   }
   
	@Override
	public boolean apply(TopicVO _type) {
		return (topicID == _type.getTopicID());
	}

}
