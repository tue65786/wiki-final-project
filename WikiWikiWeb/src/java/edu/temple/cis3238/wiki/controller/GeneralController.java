/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.temple.cis3238.wiki.controller;

import edu.temple.cis3238.constants.QUERY_PARAMS;
import edu.temple.cis3238.wiki.parser.TagsFromContentParser;
import edu.temple.cis3238.wiki.parser.TagsFromContentParser.TagsVOAdapter;
import edu.temple.cis3238.wiki.utils.StringUtils;
import edu.temple.cis3238.wiki.vo.*;
import java.io.Serializable;
import java.util.*;
import java.util.regex.*;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author DAn
 */
public class GeneralController implements Serializable {

	private static final long serialVersionUID = -8974258319597869195L;

	HttpServletRequest request;
	private String action;

	private Class clazz;

	////////////////////
	//// Incomplete
	//////////////////////
	public boolean ProcessingStrategy() {
		if (validateRequest()) {
			String actionParam = getStringParam("action", "").toLowerCase();
			switch (actionParam) {
				case "insert":
					break;
				case "update":
					break;
				case "delete":
					break;
				default:
					break;
			}
			return true;
		}

		return false;
	}

	private TopicVO getTopic() {
		if (getStringParam(QUERY_PARAMS.TOPIC_NAME, "").isEmpty()) {
			throw new NullPointerException(
					"edu.temple.cis3238.wiki.controller.GeneralController.getTopic() -- Invalid TopicName");

		}
		TopicVO topic;
		TopicVOBuilder builder = new TopicVOBuilder();
		builder.setTopicName(getStringParam(QUERY_PARAMS.TOPIC_NAME,
				""));
		builder.setTopicContent(getStringParam(
				QUERY_PARAMS.TOPIC_CONTENT, ""));
		builder.setTopicID(getIntParam(QUERY_PARAMS.TOPIC_ID, 0));
		topic = builder.build();
		topic.setTagsCollection(extractTagsFromTopic(topic));
		return topic;
	}

	private TagsVO getTag() {
		if (getStringParam(QUERY_PARAMS.TAG_NAME, "").isEmpty()) {
			throw new NullPointerException(
					"edu.temple.cis3238.wiki.controller.GeneralController.getTag() -- Invalid TagName");
		}
		TagsVO tag = new TagsVO();
		tag.setTagID(getIntParam(QUERY_PARAMS.TAG_ID, 0));
		tag.setTagName(getStringParam(QUERY_PARAMS.TAG_NAME, ""));
		return tag;
	}

	private boolean validateRequest() {
		return !(request == null || getParamMap() == null || getParamMap().isEmpty() || !getStringParam(
				"action", "").isEmpty());
	}

	private Map<String, String[]> getParamMap() {
		return request.getParameterMap();
	}

	private boolean validateIntRequestParam(String param,
											int minLength) {
		return getStringParam(param, "").length() <= minLength;
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

	private boolean validateIntRequestParam(String param) {
		return getIntParam(param, 0) > 0;
	}

	private int getIntParam(String param,
							int defaultVal) {
		try {
			if (getParamMap().containsKey(param)) {
				return Integer.parseInt(request.getParameter(param));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return defaultVal;
	}

	private Set<String> extractTagsNamesFromTopic(TopicVO topic) {
		//  ArrayList<TagsVO> matchArrayList = new ArrayList<TagsVO>();

		TagsFromContentParser tagsFromContentParser = TagsFromContentParser.create(
				topic.getTopicContent());
		return tagsFromContentParser.getTagNameSet();
	}

	private ArrayList<TagsVO> extractTagsFromTopic(TopicVO topic) {
		Set<String> set = extractTagsNamesFromTopic(topic);
		return TagsVOAdapter.generateFromNames(set);
	}
}
