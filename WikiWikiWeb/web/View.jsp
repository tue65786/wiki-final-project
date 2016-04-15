<%@page import="edu.temple.cis3238.wiki.ui.tags.helpers.TopicByTopicIDPredicate"%>
<%@page import="edu.temple.cis3238.wiki.vo.TopicVO"%>
<%@page import="edu.temple.cis3238.wiki.utils.ServletHelpers"%>
<%@page import="java.sql.Timestamp"%>
<%@page import="java.util.Collections"%>
<%@page import="java.util.Collections"%>
<%@page import="java.util.Date"%>
<%@page import="edu.temple.cis3238.wiki.vo.TagsVO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="edu.temple.cis3238.wiki.dao.GeneralDAO"%>
<%@page import="edu.temple.cis3238.wiki.sql.DbConnection"%>
<%@page import="edu.temple.cis3238.wiki.ui.tags.helpers.TagsTagSettings"%>
<%@page import="WikiWikiWeb.Logger"%>
<jsp:useBean id="tagsCollection" class="edu.temple.cis3238.wiki.ui.beans.TagsCollection" scope="session"/>
<jsp:useBean id="topicCollection" class="edu.temple.cis3238.wiki.ui.beans.TopicCollection" scope="session"/>
<jsp:useBean id="currentUser" class="edu.temple.cis3238.wiki.ui.beans.CurrentUser" scope="session" />
<jsp:setProperty name="topicCollection" property="*"/>
<jsp:setProperty name="tagsCollection" property="*"/>
<%@taglib prefix="wiki" uri="/WEB-INF/tlds/wiki.tld"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
	if (request.getParameter("login") != null) {
		currentUser.setUsername(request.getParameter("username"));
	}
	ServletHelpers web;
	String requestMessages = "";
	ArrayList<TopicVO> topics;
	DbConnection dbc;
	GeneralDAO dao;
	ArrayList<TagsVO> tags;
	TagsTagSettings settings;

	web = new ServletHelpers(request, response);
	String requestAction = web.getStrParameter("command", "list");
	String keywordSearch = web.getStrAttribute("query", "all");
	String requestTopic = web.getStrParameter("pTopicID", "");
	int requestTopicID = web.getIntParameter("topicPK", 0);
	String requestTag = web.getStrParameter("pTagID", "");
	int requestTagId = web.getIntParameter("tagPK", 0);
	String type = web.getStrParameter("type", "LIST");
	String editorMode = web.getStrParameter("editorMode", "");
	String editorTopicContent = "";
	String editorTopicName = "";
	boolean viewSingle = false;
	boolean viewList = false;
	boolean editSingle = false;
	boolean insertSingle = false;
	boolean validView = false;
	String title = "";
	dbc = new DbConnection();
	dao = new GeneralDAO(dbc);

	if (requestTopicID == 0 && !requestTopic.isEmpty()) {
		TopicVO voTemp = dao.getTopicByName(requestTopic);
		if (voTemp != null) {
			requestTopicID = voTemp.getTopicID();
		} else {
			insertSingle = true;
			//requestMessages = "<div style='color:red;font-weight:bold;'>Unable to load that topic.</div>";
		}
	} else if (requestTagId == 0 && !requestTag.isEmpty()) {
		TagsVO voTemp = dao.getTagByName(requestTag);
		if (voTemp != null) {
			requestTagId = voTemp.getTagID();
		} else {
			insertSingle = true;
			requestMessages = "<div style='color:red;font-weight:bold;'>Unable to load topics for <u>" + requestTag + "</u>.</div>";
		}
	}
	if (requestTopicID > 0) {
		if (requestAction.equals("edit")) {
			//editing --> editmode
			editSingle = true;
		} //save changes  --> back to viewmode
		else if (requestAction.equals("save")) {
			viewSingle = true;
		} // ----> back to viewmode
		else if (requestAction.equals("cancel")) {
			viewSingle = true;
		} else {// --> default view mode
			viewSingle = true;
		}
	} // ----> search results
	else if (!keywordSearch.isEmpty()) {
		viewList = true;
	} else if (requestTagId > 0) {
		viewList = true;
	}

	//view state found
	validView = viewList || viewSingle || editSingle;

	//populate tags collection
	tags = dao.getTags();
	Collections.sort(tags);
	settings = TagsTagSettings.builder()
			.queryStringParam("tagPK")
			.navigateURL("View.jsp")
			.style("cloud")
			.widthPx("90%")
			.tagsVOList(tags).build();
	tagsCollection.setSettings(settings);

	//populate topic collection
	if (requestTagId > 0) {
		//tag search 
		topics = dao.getTopicsByTagID(requestTagId);
	} else if (viewList && !keywordSearch.equals("all")) {
		//keyword search
		topics = dao.searchTopic(keywordSearch);
	} else {
		//show all
		topics = dao.getTopics();

	}
	topicCollection.setTopics(topics);
	topicCollection.setListType(type);
	if (requestTopicID > 0) {
		TopicByTopicIDPredicate currentTopicPredicate;
		currentTopicPredicate = TopicByTopicIDPredicate.create(topics, requestTopicID);
		topicCollection.setCurrentTopic(currentTopicPredicate.apply());
		if (topicCollection.getCurrentTopic() != null) {
			editorTopicContent = topicCollection.getCurrentTopic().getTopicContent();
			editorTopicName = topicCollection.getCurrentTopic().getTopicName();
		}
	} else {
		// Topic is new and will be inserted
		editorTopicName = requestTopic;
	}

	dbc.close();
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<jsp:include page="WEB-INF/jspf/head.jsp">
			<jsp:param name="title" value="<%=title%>"/>
		</jsp:include>
    </head>
    <body>
        <div id="header">
			<h1>WikiWikiWeb</h1>
			<h2 class="centered"><%=title%></h2>
			<ul>
				<li><a href='View.jsp'>Home</a></li>
				<li><a href='View.jsp'>Index</a></li>
				<li><a href='#'>Logout</a></li>
			</ul>
		</div>
		<div class="colmask rightmenu">
			<div class="colleft">
				<div class="col1">
					<!-- Main div -->
					<div id="content">
						<%=requestMessages%>
						<% if (viewSingle && !editSingle) {%>
						<wiki:topic topicCollection="${topicCollection}" 
									tagURLPrefix = "View.jsp" 
									topicViewRequestParam = "View.jsp">
						</wiki:topic>
						<%} else if (editSingle || insertSingle) {%>

						<!-- EDITOR -- insert/update -->
						<form action="${pageContext.request.contextPath}/Logger" method="post">
							<input type="hidden" name="editorMode" id="editorMode" value="<%= insertSingle ? "insert" : "update"%>" /> 
							<input type="hidden" name="topicPK" id="topicPK" value="<%=requestTopicID + ""%>" />
							<input type="hidden" name="pTopicID" id="pTopicID" value="<%=editorTopicName%>" />
							<div>Topic Name: <b><%=editorTopicName%></b></div>
							<textarea id="editor" name="editor">
								<%=editorTopicContent%>
							</textarea><br />
							<a href="View.jsp?command=view&pTopicID=<%=editorTopicName%>&topicPK=<%=requestTopicID%>">Cancel</a>	
							<button>Submit</button>
						</form>

						<%} else if (viewList) {%>
						<wiki:TopicList topicsList="${topicCollection}" 
										listStyle="LIST"  
										sortField="" 
										tagLinkPage="View.jsp" 
										tagLinkRequestParam="pTagID" 
										topicLinkPage="View.jsp" 
										topicLinkRequestParam="pTopicID">
						</wiki:TopicList> 
						<%}%>
					</div>
					<!-- Main div end-->
				</div>
				<div class="col2">
					<!-- Sidebar div -->
					<div style="display:flex;float:left;margin-left:20%;">
						<input style="width:50%" name="query" id="query" type="text">
						<button>search</button>
					</div>
					<br/>
					<wiki:TagsList tagsCollectionBeans="${tagsCollection}" />
					<!--end sidebar-->
				</div>
			</div>
		</div>
		<div id="footer">
			&male; 2016. Version 3.2.38 
		</div>
	</body>
</html>
