/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.temple.cis3238.wiki.ui.tags;

import edu.temple.cis3238.constants.QUERY_PARAMS;
import edu.temple.cis3238.wiki.ui.beans.*;
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
	private CurrentUser currentUser;
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
			"<input type=\"hidden\" name=\"" + "command" + "\" id=\"" + "command" + "\" value=\"edit\"/>" +
			"<input type=\"hidden\" name=\"" + QUERY_PARAMS.TOPIC_ID + "\" id=\"" + QUERY_PARAMS.TOPIC_ID + "\" value=\"[[[ID]]]\"/>" +
			"[[[EDIT]]]" +
//			"<input name=\"action\" type=\"button\" id=\"btnSave\" value=\"save\"/>" +
//			"<input type=\"button\" name=\"action\"  value=\"cancel\" id=\"btnCancel\"/>" +
			"</p>" +
			"</form>";
	private static final String JAVASCRIPTS = "" +//
			"<script type=\"text/javascript\">\n" +
			"$(document).ready(function () { \n" +
//			" $(\"#btnSave\").jqxButton({width: 100, height: 40,template: \"primary\"});\n" +
			" $(\"#btnEdit\").jqxButton({width: 90, height: 30,template: \"info\"});\n" +
//			" $(\"#btnCancel\").jqxButton({width: 80, height: 40,template: \"info\"});" +
			"  $(\"#btnEdit\").click(function (e){\n" +
			"	e.preventDefault();\n" +
			"	$(\"#command\").val($(this).val());\n" +
			"      	$(\"#topicForm\").submit();\n" +
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
						"<p>Topic not found. <a href='View.jsp?command=edit&pTopicID=" 
								+ topicVO.getTopicName() + "id='addTopic'>Click </a> to add it!</p>");
			} else {
				if (currentUser != null &&currentUser.isLoggedIn()){
				out.println(JAVASCRIPTS);
				}
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
	 * @param currentUser the currentUser to set
	 */
	public void setCurrentUser(CurrentUser currentUser) {
		this.currentUser = currentUser;
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
		String loggedIn = "<button name=\"action\" style='cursor:pointer;font-variant:all-caps;' id=\"btnEdit\" value=\"edit\">Edit</button>";
		return ret.
				replace("[[[CONTENT]]]", edu.temple.cis3238.parser.Parser.parseAndAnnotate(
						vo.getTopicContent(), "View.jsp", QUERY_PARAMS.TOPIC_NAME, "View.jsp",
						QUERY_PARAMS.TAG_NAME))
				.replace("[[[PAGENAME]]]", StringUtils.fromCamelCase(vo.getTopicName()))
				.replace("[[[STATS]]]", TopicHTMLFactory.getTopicStats(vo))
				.replace("[[[TAGS]]]", TopicHTMLFactory.makeTagsCSV(vo, QUERY_PARAMS.TAG_ID,
						tagURLPrefix))
				.replace("[[[ID]]]",vo.getTopicID()+"")
				.replace("[[[CSSTAGS]]]", getCssTagListClass())
				.replace("[[[CSSBODY]]]", getCssTopicBodyClass())
				.replace("[[[URL]]]",topicViewRequestParam)
				.replace("[[[EDIT]]]", currentUser != null && currentUser.isLoggedIn() ? loggedIn : "<br/><a href='index.jsp'>Login</a> to edit topic.");
	}
}
