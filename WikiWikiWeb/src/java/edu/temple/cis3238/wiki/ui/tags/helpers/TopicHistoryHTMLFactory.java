/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.temple.cis3238.wiki.ui.tags.helpers;

import com.google.gson.*;
import edu.temple.cis3238.wiki.utils.StringUtils;
import edu.temple.cis3238.wiki.vo.*;
import java.util.ArrayList;
import java.util.logging.Logger;

/**
 *
 * Markup for topic history.
 */
public class TopicHistoryHTMLFactory {

	private static final String ALTROW = "[[[AROW]]]";
	private static final String INDEX = "[[[INDEX]]]";
	private static final String DATE = "[[[DATE]]]";
	private static final String HISTORYID = "[[[IDHIST]]]";
	private static final String TOPICID = "[[[IDTOPIC]]]";
	private static final String HISTORY_ROW = "<tr ><td style='border-bottom:1px solid #e5e5e5;' class='infoTD [[[AROW]]]'>[[[INDEX]]].&nbsp;[[[DATE]]]</td><td style='border-bottom:1px solid #e5e5e5;' class='diffTD [[[AROW]]]'><a href='#' onclick='loadDiffByTopicHistoryID([[[IDHIST]]]);'>view diff</a></td><td style='border-bottom:1px solid #e5e5e5;' class='revertTD [[[AROW]]]'><a href=\"View.jsp?topicHistoryPK=[[[IDHIST]]]&topicPK=[[[IDTOPIC]]]\">restore this version</a></td></tr>";
	//background-color: #f5f5f5;  - dark
	//border-bottom: 1px solid #e5e5e5;

	private static String createHistoryList(ArrayList<TopicHistoryVO> voList) {
		if (voList == null || voList.isEmpty()) {
			return "<b>This topic has not been modified. <u>History unavailable</u></b>";
		}
		StringBuilder sb = new StringBuilder("<table class='topicHistoryTBL'><tr><th colspan='3'>Revisions</th></tr>");
		for (int i = 0; i < voList.size(); i++) {
			sb.append(createHistoryItem(voList.get(i), i + 1));
		}
		sb.append("</table>");
		return sb.toString();
	}

	private static String createHistoryItem(TopicHistoryVO vo,
											int row) {
		boolean evenrow = row % 2 == 0;

		return HISTORY_ROW
				.replace(ALTROW, evenrow ? "evenrow" : "")
				.replace(INDEX, row + "")
				.replace(DATE, StringUtils.formatDate(vo.getTopicHistoryCreated(),
						"EEE, d MMM yyyy hh:mm aaa"))
				.replace(HISTORYID, vo.getTopicHistoryID() + "")
				.replace(TOPICID, vo.getTopicID() + "");

	}

	private static String createDiffLegend() {
		return "<div class=\"footerItemText\"><div class=\"legend\"><ul><li><span class=\"wikEdDiffMarkRight\" title=\"Moved block\" id=\"wikEdDiffMark999\" onmouseover=\"wikEdDiffBlockHandler(undefined, this, 'mouseover');\"></span> Original block position</li>\n<li><span title=\"+\" class=\"wikEdDiffInsert\">Inserted<span class=\"wikEdDiffSpace\"><span class=\"wikEdDiffSpaceSymbol\"></span> </span>text<span class=\"wikEdDiffNewline\"> </span></span></li><li><span title=\"−\" class=\"wikEdDiffDelete\">Deleted<span class=\"wikEdDiffSpace\"><span class=\"wikEdDiffSpaceSymbol\"></span></span>text<span class=\"wikEdDiffNewline\"> </span></span></li><li><span class=\"wikEdDiffBlockLeft\" title=\"◀\" id=\"wikEdDiffBlock999\" onmouseover=\"wikEdDiffBlockHandler(undefined, this, 'mouseover');\">Moved<span class=\"wikEdDiffSpace\"><span class=\"wikEdDiffSpaceSymbol\"></span> </span>block<span class=\"wikEdDiffNewline\"> </span></span></li><li><span class=\"newlineSymbol\">¶</span> Newline <span class=\"spaceSymbol\">·</span> Space <span class=\"tabSymbol\">→</span> Tab</li></ul>	</div></div>";
	}

	private static String createIncludes() {
		return "<script type=\"text/javascript\" src=\"http://guidemecoach.com/gains-mcc/js/json2.js\"></script>\n" +
				"<script type=\"text/javascript\" src=\"http://underscorejs.org/underscore-min.js\"></script>\n" +
				"<script type=\"text/javascript\" src=\"theme/diff.js\"></script>\n" +
				"<script type=\"text/javascript\" src=\"theme/wikEd-diff-tool.js\"></script>\n" +
				"<link rel=\"stylesheet\" type=\"text/css\" href=\"theme/wikEd-diff-tool.css\"/>";
	}

	private static String createScriptBlock(TopicVO vo,
											ArrayList<TopicHistoryVO> voList) {
		String topicHistoryJSON;
		Gson gson;
		gson = new GsonBuilder().serializeNulls().create();
		topicHistoryJSON = gson.toJson(voList);
		topicHistoryJSON = topicHistoryJSON.replaceAll("\\p{C}|\\p{Cc}|\\p{Cntrl}", "");
		return "<script type=\"text/javascript\">\n" +
				"			var topicHistoryArray = " + topicHistoryJSON + ";\n" +
				"			function loadDiffByTopicHistoryID(id) {\n" +
				"				var topicHist;\n" +
				"				topicHist = _.findWhere(topicHistoryArray, {topicHistoryID: id});\n" +
				"				WikEdDiffTool.diff('" +
				 vo.getTopicContent().replaceAll("\\p{C}|\\p{Cc}|\\p{Cntrl}", "") +
				 "', topicHist.topicConent, \"diffDiv\");\n" +
				"var container = $('#content'),\n" +
"    scrollTo = $('#diffDiv');\n"+
				"container.animate({\n" +
"    scrollTop: scrollTo.offset().top - container.offset().top + container.scrollTop()\n" +
"});"+
				"			}\n" +
				"		</script>";
	}

	public static String create(TopicVO vo,ArrayList<TopicHistoryVO> voList) {
		return createIncludes()
//				+"<h3>Current</h3>\n<div style='border: #bbb solid;" +
//				"border-width: 1px 1px 1px 0.5em;border-radius: 0.5em;" +
//				"box-shadow: 2px 2px 2px #ddd;line-height: 1.6;padding: 1em;'>" 
//				+ vo.getTopicContent().replaceAll("\\p{C}|\\p{Cc}|\\p{Cntrl}", "") 
//				+ "</div>\n" 
				+"<hr/>\n" 
				+createHistoryList(voList)
				+ "\n<h3>Comparison (diff) </h3>\n" 
				+ "	<div id=\"diffDiv\">\n" 
				+ "	</div>\n" 
				+ createDiffLegend()
				+ createScriptBlock(vo, voList);
	}
	private static final Logger LOG = Logger.getLogger(TopicHistoryHTMLFactory.class.getName());
}
