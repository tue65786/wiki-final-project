<%-- 
    Document   : SampleTopicHistory
    Created on : Apr 14, 2016, 5:05:49 PM
    Author     : 
--%>

<%@page import="edu.temple.cis3238.wiki.utils.StringUtils"%>
<%@page import="edu.temple.cis3238.wiki.vo.TopicVO"%>
<%@page import="com.google.gson.GsonBuilder"%>
<%@page import="com.google.gson.Gson"%>
<%@page import="edu.temple.cis3238.wiki.vo.TopicHistoryVO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="edu.temple.cis3238.wiki.sql.DbConnection"%>
<%@page import="edu.temple.cis3238.wiki.dao.GeneralDAO"%>
<%@page import="edu.temple.cis3238.wiki.utils.ServletHelpers"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
	DbConnection dbc;
	GeneralDAO dao;
	ServletHelpers web;
	dbc = new DbConnection();
	dao = new GeneralDAO(dbc);
	String topicHistoryJSON = "";
	String topicContent = "";
	TopicVO topic;
	ArrayList<TopicHistoryVO> topicHistory;
	topicHistory = dao.getTopicHistoryByTopicID(1);
	topic = dao.getTopicByID(1);
	topicContent = topic.getTopicContent();
	Gson gson;
	gson = new GsonBuilder().serializeNulls().setPrettyPrinting().create();
	topicHistoryJSON = gson.toJson(topicHistory);
	topicHistoryJSON = topicHistoryJSON.replaceAll("\\p{C}|\\p{Cc}|\\p{Cntrl}", "");
	String tbl = "";
	for (int i = 0; i < topicHistory.size(); i++) {
		TopicHistoryVO vo = topicHistory.get(i);
		tbl += (i + 1) + ". " + StringUtils.formatDate(
				vo.getTopicHistoryCreated()) +
				 "&nbsp;<a href='#' onclick='loadDiffByTopicHistoryID(" + vo.getTopicHistoryID() + ")'>" + "view diff" + "</a>&nbsp;<a href='#'>revert topic</a><br/>";
	}
	dbc.close();
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
		<link href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css" rel="stylesheet" type="text/css"/>
		<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.4/jquery.min.js"></script>
		<script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/jquery-ui.min.js"></script>
		<script type="text/javascript" src="http://guidemecoach.com/gains-mcc/js/json2.js"></script>
		<script type="text/javascript" src="http://underscorejs.org/underscore-min.js"></script>
		<script type="text/javascript" src="theme/diff.js"></script>
		<script type="text/javascript" src="theme/wikEd-diff-tool.js"></script>
		<link rel="stylesheet" type="text/css" href="theme/wikEd-diff-tool.css"/>
    </head>
    <body>
		<h3>Current Topic Content</h3>
		<%=topic.getTopicContent()%>
		<p/><hr/><p/>
		<%=tbl%>

		<h3>Comparison (diff) </h3>
		<div id="diffDiv">
		</div>
		
		<div class="footerItemText"><div class="legend"><ul><li><span class="wikEdDiffMarkRight" title="Moved block" id="wikEdDiffMark999" onmouseover="wikEdDiffBlockHandler(undefined, this, 'mouseover');"></span> Original block position</li>
<li><span title="+" class="wikEdDiffInsert">Inserted<span class="wikEdDiffSpace"><span class="wikEdDiffSpaceSymbol"></span> </span>text<span class="wikEdDiffNewline"> </span></span></li><li><span title="−" class="wikEdDiffDelete">Deleted<span class="wikEdDiffSpace"><span class="wikEdDiffSpaceSymbol"></span></span>text<span class="wikEdDiffNewline"> </span></span></li><li><span class="wikEdDiffBlockLeft" title="◀" id="wikEdDiffBlock999" onmouseover="wikEdDiffBlockHandler(undefined, this, 'mouseover');">Moved<span class="wikEdDiffSpace"><span class="wikEdDiffSpaceSymbol"></span> </span>block<span class="wikEdDiffNewline"> </span></span></li><li><span class="newlineSymbol">¶</span> Newline <span class="spaceSymbol">·</span> Space <span class="tabSymbol">→</span> Tab</li></ul>	</div></div>
		<script type="text/javascript">
			var topicHistoryArray = <%=topicHistoryJSON%>;
			function loadDiffByTopicHistoryID(id) {
				var topicHist;
				topicHist = _.findWhere(topicHistoryArray, {topicHistoryID: id});
				WikEdDiffTool.diff('<%=topicContent%>', topicHist.topicConent, "diffDiv");
			}
		</script>
	</body>
</html>
