/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.temple.cis3238.wiki.controller;

import edu.temple.cis3238.constants.QUERY_PARAMS;
import edu.temple.cis3238.wiki.utils.StringUtils;
import edu.temple.cis3238.wiki.vo.*;
import java.util.*;
import java.util.regex.*;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author DAn
 */
public class GeneralController {

   HttpServletRequest request;
   private TopicVO topic;
 
   ////////////////////
   //// Incomplete
   //////////////////////
   

   private void setTopic() {
	  TopicVOBuilder builder = new TopicVOBuilder();
	  builder.setTopicName(getStringParam(QUERY_PARAMS.TOPIC_NAME, ""));
	  builder.setTopicContent(getStringParam(QUERY_PARAMS.TOPIC_CONTENT, ""));
	  builder.setTopicID(getIntParam(QUERY_PARAMS.TOPIC_ID, 0));
	  topic = builder.build();
	  topic.setTagsCollection(extractTagsFromTopic());

   }

   private Map<String, String[]> getParamMap() {
	  return request.getParameterMap();
   }

   private String getStringParam(String param, String defaultVal) {
	  try {
		 if (getParamMap().containsKey(param)) {
			return request.getParameter(param);
		 }
	  } catch (Exception e) {
		 e.printStackTrace();
	  }
	  return StringUtils.toS(defaultVal, "");
   }

   private int getIntParam(String param, int defaultVal) {
	  try {
		 if (getParamMap().containsKey(param)) {
			return Integer.parseInt(request.getParameter(param));
		 }
	  } catch (Exception e) {
		 e.printStackTrace();
	  }
	  return defaultVal;
   }

   private ArrayList<TagsVO> extractTagsFromTopic() {
	  ArrayList<TagsVO> matchArrayList = new ArrayList<TagsVO>();
	  try {
		 Pattern regex = Pattern.compile("(\\{\\{)(.+?)(\\}\\})");
		 Matcher regexMatcher = regex.matcher(topic.getTopicContent());
		 while (regexMatcher.find()) {
			matchArrayList.add(TagsVO.newInstance(new TagsVO((matchArrayList.size() + 1) * -1,
					regexMatcher.group(2), 0)));
		 }
	  } catch (PatternSyntaxException ex) {
		 // Syntax error in the regular expression
	  }
	  return matchArrayList;
   }
}
