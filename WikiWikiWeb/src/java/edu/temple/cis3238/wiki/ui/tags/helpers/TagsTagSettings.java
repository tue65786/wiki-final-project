/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.temple.cis3238.wiki.ui.tags.helpers;

import edu.temple.cis3238.wiki.vo.*;
import java.io.*;
import java.util.*;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * Defines settings for TagList markup TagLib
 * @see edu.temple.cis3238.wiki.ui.tags.TagsList
 * @see TagsVO
 * @see TagsListFactory
 * @see TagSupport
 */
public class TagsTagSettings implements Serializable {

	private static final long serialVersionUID = -8390170391637055790L;

	private String navigateURL;
	private String queryStringParam;
	private String style;
	private ArrayList<TagsVO> tagsVOList;
	private String widthpx = "100px";

	/**
	 *
	 * @return
	 */
	public String getNavigateURL() {
		return navigateURL;
	}

	/**
	 *
	 * @param _navigateURL
	 */
	public void setNavigateURL(String _navigateURL) {
		this.navigateURL = _navigateURL;
	}

	/**
	 *
	 * @return
	 */
	public String getQueryStringParam() {
		return queryStringParam;
	}

	/**
	 *
	 * @param _queryStringParam
	 */
	public void setQueryStringParam(String _queryStringParam) {
		this.queryStringParam = _queryStringParam;
	}

	/**
	 *
	 * @return
	 */
	public String getStyle() {
		return style;
	}

	/**
	 * Style setting for Factory
	 * @param _style
	 */
	public void setStyle(String _style) {
		this.style = _style;
	}

	/**
	 *
	 * @return
	 */
	public ArrayList<TagsVO> getTagsVOList() {
		return tagsVOList;
	}

	/**
	 *
	 * @param _tagsVOList
	 */
	public void setTagsVOList(ArrayList<TagsVO> _tagsVOList) {
		this.tagsVOList = _tagsVOList;
	}

	/**
	 * @return the widthpx
	 */
	public String getWidthpx() {
		return widthpx;
	}

	/**
	 * @param widthpx the widthpx to set
	 */
	public void setWidthpx(String widthpx) {
		this.widthpx = widthpx;
	}

	/**
	 *
	 * @return
	 */
	public boolean validateParams() {
		return style != null && navigateURL != null && queryStringParam != null && tagsVOList != null && !tagsVOList.isEmpty();
	}

	/**
	 *
	 * @param _navigateURL
	 * @param _queryStringParam
	 * @param _style
	 * @param _tagsVOList
	 * @param _widthpx
	 */
	public TagsTagSettings(String _navigateURL,
						   String _queryStringParam,
						   String _style,
						   ArrayList<TagsVO> _tagsVOList,
						   String _widthpx) {
		this.navigateURL = _navigateURL;
		this.queryStringParam = _queryStringParam;
		this.style = _style;
		this.tagsVOList = _tagsVOList;
		this.widthpx = _widthpx;
	}

	/**
	 * Settings Builder Builds settings for {@linkplain edu.temple.cis3238.wiki.ui.tags.TagsList}
	 *
	 * @see edu.temple.cis3238.wiki.ui.tags.TagsList
	 */
	public static class Builder {

		private String navigateURL;
		private String queryStringParam;
		private String style;
		private ArrayList<TagsVO> tagsVOList;
		private String widthpx = "100px";

		private Builder() {
		}

		/**
		 *
		 * @return
		 */
		public TagsTagSettings build() {
			return new edu.temple.cis3238.wiki.ui.tags.helpers.TagsTagSettings(navigateURL,
					queryStringParam, style, tagsVOList, widthpx);
		}

		/**
		 *
		 * @param value
		 * @return
		 */
		public Builder navigateURL(final String value) {
			this.navigateURL = value;
			return this;
		}

		/**
		 *
		 * @param value
		 * @return
		 */
		public Builder queryStringParam(final String value) {
			this.queryStringParam = value;
			return this;
		}

		/**
		 *
		 * @param value
		 * @return
		 */
		public Builder style(final String value) {
			this.style = value;
			return this;
		}

		/**
		 *
		 * @param value
		 * @return
		 */
		public Builder tagsVOList(final ArrayList<TagsVO> value) {
			this.tagsVOList = value;
			return this;
		}

		/**
		 *
		 * @param value
		 * @return
		 */
		public Builder widthPx(final String value) {
			this.widthpx = value;
			return this;
		}
	}

	/**
	 *
	 * @return
	 */
	public static TagsTagSettings.Builder builder() {
		return new TagsTagSettings.Builder();
	}

	@Override
	public String toString() {
		return "Builder{" + "navigateURL=" + navigateURL + ", queryStringParam=" 
				+ queryStringParam + ", style=" + style + ", tagsVOList=" 
				+ tagsVOList + ", widthpx=" + widthpx + '}';
	}
}
