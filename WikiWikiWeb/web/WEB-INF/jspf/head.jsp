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
<link rel="stylesheet" href="http://www.jqwidgets.com/jquery-widgets-documentation/jqwidgets/styles/jqx.base.css" type="text/css" />
<link type="text/css" rel="stylesheet" href="index.css" />
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.0/jquery.min.js"></script>
<script type="text/javascript" src="http://www.jqwidgets.com/jquery-widgets-documentation/jqwidgets/jqxcore.js"></script>
<script type="text/javascript" src="http://www.jqwidgets.com/jquery-widgets-documentation/jqwidgets/jqxbuttons.js"></script>
<script type="text/javascript" src="http://www.jqwidgets.com/jquery-widgets-documentation/jqwidgets/jqxscrollbar.js"></script>
<script type="text/javascript" src="http://www.jqwidgets.com/jquery-widgets-documentation/jqwidgets/jqxlistbox.js"></script>
<script type="text/javascript" src="http://www.jqwidgets.com/jquery-widgets-documentation/jqwidgets/jqxdropdownlist.js"></script>
<script type="text/javascript" src="http://www.jqwidgets.com/jquery-widgets-documentation/jqwidgets/jqxdropdownbutton.js"></script>
<script type="text/javascript" src="http://www.jqwidgets.com/jquery-widgets-documentation/jqwidgets/jqxcolorpicker.js"></script>
<script type="text/javascript" src="http://www.jqwidgets.com/jquery-widgets-documentation/jqwidgets/jqxwindow.js"></script>
<script type="text/javascript" src="http://www.jqwidgets.com/jquery-widgets-documentation/jqwidgets/jqxeditor.js"></script>
<script type="text/javascript" src="http://www.jqwidgets.com/jquery-widgets-documentation/jqwidgets/jqxtooltip.js"></script>
<script type="text/javascript" src="http://www.jqwidgets.com/jquery-widgets-documentation/jqwidgets/jqxcheckbox.js"></script>
<script type="text/javascript" src="editor.js"></script>
<script type="text/javascript" src="http://guidemecoach.com/gains-mcc/js/json2.js"></script>
<script type="text/javascript" src="http://underscorejs.org/underscore-min.js"></script>

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