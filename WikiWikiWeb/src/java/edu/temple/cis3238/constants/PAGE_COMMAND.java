/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.temple.cis3238.constants;

import static edu.temple.cis3238.wiki.utils.StringUtils.toS;

/**
 *
 */
public enum PAGE_COMMAND {
	/**
	 *
	 */
	TOPIC_VIEW_SINGLE_MODE(1, "Wiki.jsp",
			varargToArray(QUERY_PARAMS.TOPIC_NAME,
					QUERY_PARAMS.TOPIC_CONTENT),
			QUERY_PARAMS.TOPIC_ID,
			null),
	/**
	 *
	 */
	TOPICS_ALL_TOPICS_MODE(10, "Wiki.jsp",
			varargToArray(QUERY_PARAMS.TOPIC_NAME,
					QUERY_PARAMS.TOPIC_CONTENT),
			QUERY_PARAMS.TOPIC_ID,
			null),
	/**
	 *
	 */
	TOPICS_SEARCH_MODE(30, "Wiki.jsp",
			varargToArray(QUERY_PARAMS.TOPICS_SEARCH_KEY),
			null,
			null),
	/**
	 *
	 */
	TOPICS_SEARCH_BY_TAG_MODE(35, "Wiki.jsp",
			varargToArray(QUERY_PARAMS.TAG_ID, QUERY_PARAMS.TAG_NAME),
			null,
			varargToArray(QUERY_PARAMS.TOPICS_SEARCH_KEY)),
	/**
	 *
	 */
	TOPIC_UPDATE_MODE(50, "Wiki.jsp",
			varargToArray(QUERY_PARAMS.TOPIC_ID,
					QUERY_PARAMS.TOPIC_NAME,
					QUERY_PARAMS.TOPIC_CONTENT),
			QUERY_PARAMS.TOPIC_ID,
			null),
	/**
	 *
	 */
	TOPIC_INSERT_MODE(60, "Wiki.jsp",
			varargToArray(QUERY_PARAMS.TOPIC_NAME,
					QUERY_PARAMS.TOPIC_CONTENT),
			QUERY_PARAMS.TOPIC_ID,
			null),
	/**
	 *
	 */
	TOPIC_ROLLBACK(70, "Wiki.jsp",
			varargToArray(QUERY_PARAMS.TOPIC_ID),
			QUERY_PARAMS.TOPIC_HISTORY_ID,
			null),
	/**
	 *
	 */
	TAG_VIEW(100, "Wiki.jsp",
			varargToArray(QUERY_PARAMS.TOPIC_ID,
					QUERY_PARAMS.TOPIC_NAME,
					QUERY_PARAMS.TOPIC_CONTENT),
			QUERY_PARAMS.TOPIC_ID,
			null);

	final int COMMAND_ACTION_CODE;
	String JSP_SOURCE;
	final String[] PARAMS_REQUIRED;
	final String PARAM_PRIMARY_KEY;
	final String[] PARAMS_OPTIONAL;

	/**
	 *
	 * @param actionCodeID
	 * @param sourcePage
	 * @param requestParams
	 * @param primaryKeyParam
	 * @param valOpsRequestionParams
	 */
	private PAGE_COMMAND(int actionCodeID,
						 String sourcePage,
						 String[] requestParams,
						 String primaryKeyParam,
						 String[] valOpsRequestionParams) {
		this.COMMAND_ACTION_CODE = actionCodeID;
		this.JSP_SOURCE = sourcePage;
		this.PARAMS_OPTIONAL = valOpsRequestionParams;
		this.PARAM_PRIMARY_KEY = primaryKeyParam;
		this.PARAMS_REQUIRED = requestParams;

	}

	public int GETTER_COMMAND_ACTION_CODE() {
		return COMMAND_ACTION_CODE;
	}

	public String GETTER_JSP_SOURCE() {
		JSP_SOURCE = toS(JSP_SOURCE).substring(JSP_SOURCE.lastIndexOf(
				"/") + 1, JSP_SOURCE.length());
		if (JSP_SOURCE.contains("?")) {
			JSP_SOURCE.substring(0, JSP_SOURCE.indexOf("?"));
		}
		return JSP_SOURCE;
	}

	public String[] GETTER_PARAMS_REQUIRED() {
		return PARAMS_REQUIRED;
	}

	public String GETTER_PARAM_PRIMARY_KEY() {
		return PARAM_PRIMARY_KEY;
	}

	public String[] GETTER_PARAMS_OPTIONAL() {
		return PARAMS_OPTIONAL;
	}

	private final static String[] varargToArray(String... args) {
		int len = args.length;
		String[] p = new String[len];
		for (int i = 0; i < len; i++) {
			p[i] = "" + args[i];
		}
		return p;
	}
}
