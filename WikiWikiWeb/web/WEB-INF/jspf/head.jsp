<%@page import="edu.temple.cis3238.wiki.utils.StringUtils"%>
<%@page import="edu.temple.cis3238.wiki.ui.beans.TopicCollection"%>
<link href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css" rel="stylesheet" type="text/css"/>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.4/jquery.min.js"></script>
<script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/jquery-ui.min.js"></script>
<link href="theme/layout.min.css" rel="stylesheet" type="text/css"/>
<link href="theme/jqwidgets/jqwidgets/styles/jqx.base.css" rel="stylesheet" type="text/css"/>
<script src="theme/jqwidgets/jqwidgets/jqxcore.js" type="text/javascript"></script>
<script src="theme/jqwidgets/jqwidgets/jqxdata.js" type="text/javascript"></script>
<script src="theme/jqwidgets/jqwidgets/jqxdata.js" type="text/javascript"></script>
<script src="theme/jqwidgets/jqwidgets/jqxbuttons.js" type="text/javascript"></script>
<script src="theme/jqwidgets/jqwidgets/jqxnotification.js" type="text/javascript"></script>

<%
	TopicCollection collection = (TopicCollection) session.getAttribute("topicCollection");
	String title = "";

	if (collection != null && collection.getCurrentTopic() != null) {
		title = StringUtils.fromCamelCase(StringUtils.toS(
				collection.getCurrentTopic().getTopicName()));
	} else if (request.getParameter("title") != null) {
		title = request.getParameter("title");
	}
%>
<title> WikiWiki Web .:. <%=title%></title>




