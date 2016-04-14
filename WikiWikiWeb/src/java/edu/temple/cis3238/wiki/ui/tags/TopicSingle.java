/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.temple.cis3238.wiki.ui.tags;

import edu.temple.cis3238.constants.QUERY_PARAMS;
import edu.temple.cis3238.wiki.ui.beans.TopicCollection;
import edu.temple.cis3238.wiki.ui.tags.helpers.TopicHTMLFactory;
import edu.temple.cis3238.wiki.utils.StringUtils;
import edu.temple.cis3238.wiki.vo.*;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 *
 */
public class TopicSingle extends SimpleTagSupport {
	////////////////////
	//// Incomplete
	//////////////////////

	private TopicVO topicVO;
	private boolean showTags;
	private String tagURLPrefix;
	private String cssTopicTitleClass;
	private String cssTopicBodyClass;
	private String cssTagListClass;
	private String topicViewRequestParam;
	private TopicCollection topicCollection;
	private static final String ITEM_TEMPLATE = "<form id=\"topicForm\" action=\"[[[URL]]]\" method=\"get\">" +
			"<h4>[[[PAGENAME]]]</h4>" +
			"<p class='[[[CSSBODY]]]'>[[[CONTENT]]]</p>" +
			"<p class='[[[CSSTAGS]]]'>[[[TAGS]]]</p>" +
			"<p class='stats'>[[[STATS]]]</p>" +
			"<p class='actions'>" +
			"<input type=\"hidden\" name=\"" + "command" + "\" id=\"" + "command" + "\" value=\"view\"/>" +
			"<input type=\"hidden\" name=\"" + QUERY_PARAMS.TOPIC_ID + "\" id=\"" + QUERY_PARAMS.TOPIC_ID + "\" value=\"[[[ID]]]\"/>" +
			"<input type=\"button\" name=\"action\" id=\"btnEdit\" value=\"edit\">Edit</input>" +
			"<input name=\"action\" type=\"button\" id=\"btnSave\" value=\"save\">Save</input>" +
			"<input type=\"button\" name=\"action\" value=\"cancel\" id=\"btnCancel\">Cancel</input></p>" +
			"</form>";
	private static final String JAVASCRIPTS = " <link rel=\"stylesheet\" href=\"theme/jqwidgets/jqwidgets/styles/jqx.base.css\" type=\"text/css\" />" +//
			"<script type=\"text/javascript\" src=\"theme/jqwidgets/jqwidgets/jqxcore.js\"></script>" +//
			"<script type=\"text/javascript\" src=\"theme/jqwidgets/jqwidgets/jqxbuttons.js\"></script>" +
			"<script type=\"text/javascript\">" +
			"$(document).ready(function () {  " +
			
			" $(\"#btnSave\").jqxButton({width: 120, height: 40,template: \"primary\"});\n" +
			" $(\"#btnEdit\").jqxButton({width: 120, height: 40,template: \"primary\"});\n" +
			" $(\"#btnCancel\").jqxButton({width: 120, height: 40,template: \"info\"});" +
			"  $(\"#btnSave,#btnEdit,#btnCancel\").on('click', function (e){\n" +
			"\te.preventDefault();\n" +
			"\t$(\"#command\").val($(this).val());\n      " +
			"\t$(\"#topicForm\").submit();\n" +
			" });" +
			"});" +
			"</script>";

	/**
	 * Called by the container to invoke this tag. The implementation of this method is provided by
	 * the tag library developer, and handles all tag processing, body iteration, etc.
	 */
	@Override
	public void doTag() throws JspException {
		JspWriter out = getJspContext().getOut();

		try {
			if (topicVO == null) {
				out.println(
						"<p>Topic not found. <a href='#' id='addTopic'>Click </a> to add it!</p>");
			} else {
				out.println(JAVASCRIPTS);
				out.print(makeTopicItem(topicVO));
			}
			JspFragment f = getJspBody();
			if (f != null) {
				f.invoke(out);
			}
		} catch (java.io.IOException ex) {
			throw new JspException("Error in topic tag", ex);
		}
	}

	/**
	 * @param topicCollection the topicCollection to set
	 */
	public void setTopicCollection(TopicCollection topicCollection) {
		this.topicCollection = topicCollection;
		setTopicVO(topicCollection.getCurrentTopic());
	}

	public void setTopicVO(TopicVO TopicVO) {
		this.topicVO = TopicVO;
	}

	public void setShowTags(boolean showTags) {
		this.showTags = showTags;
	}

	public void setTagURLPrefix(String tagURLPrefix) {
		this.tagURLPrefix = tagURLPrefix;
	}

	public void setCssTopicTitleClass(String CSSClassTopicTitle) {
		this.cssTopicTitleClass = CSSClassTopicTitle;
	}

	public void setCssTopicBodyClass(String CSSClassTopicBody) {
		this.cssTopicBodyClass = CSSClassTopicBody;
	}

	public void setCssTagListClass(String CSSClassTagList) {
		this.cssTagListClass = CSSClassTagList;
	}

	/**
	 * @param topicViewRequestParam the topicViewRequestParam to set
	 */
	public void setTopicViewRequestParam(String topicViewRequestParam) {
		this.topicViewRequestParam = topicViewRequestParam;
	}

	private String getCssTopicBodyClass() {
		return cssTopicBodyClass != null ? cssTopicBodyClass : "wikicontent";
	}

	private String getCssTagListClass() {
		return cssTagListClass != null ? cssTagListClass : "tags";
	}

	private String makeTopicItem(TopicVO vo) {
		String ret = ITEM_TEMPLATE + "";
		return ret.
				replace("[[[CONTENT]]]", vo.getTopicContent())
				.replace("[[[PAGENAME]]]", StringUtils.fromCamelCase(vo.getTopicName()))
				.replace("[[[STATS]]]", TopicHTMLFactory.getTopicStats(vo))
				.replace("[[[TAGS]]]", TopicHTMLFactory.makeTagsCSV(vo, QUERY_PARAMS.TAG_ID,
						tagURLPrefix))
				.replace("[[[CSSTAGS]]]", getCssTagListClass())
				.replace("[[[CSSBODY]]]", getCssTopicBodyClass());
	}
}
