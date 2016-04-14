<%@page import="edu.temple.cis3238.wiki.ui.tags.helpers.TopicByTopicIDPredicate"%>
<%@page import="edu.temple.cis3238.wiki.vo.TopicVO"%>
<%@page import="edu.temple.cis3238.wiki.utils.ServletHelpers"%>
<%@page import="java.util.Collections"%>
<%@page import="java.util.Collections"%>
<%@page import="edu.temple.cis3238.wiki.vo.TagsVO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="edu.temple.cis3238.wiki.dao.GeneralDAO"%>
<%@page import="edu.temple.cis3238.wiki.sql.DbConnection"%>
<%@page import="edu.temple.cis3238.wiki.ui.tags.helpers.TagsTagSettings"%>
<jsp:useBean id="tagsCollection" class="edu.temple.cis3238.wiki.ui.beans.TagsCollection" scope="session"/>
<jsp:useBean id="topicCollection" class="edu.temple.cis3238.wiki.ui.beans.TopicCollection" scope="session"/>
<jsp:setProperty name="topicCollection" property="*"/>
<jsp:setProperty name="tagsCollection" property="*"/>
<%@taglib prefix="wiki" uri="/WEB-INF/tlds/wiki.tld"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
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
	boolean viewSingle = false;
	boolean viewList = false;
	boolean editSingle = false;
	boolean validView = false;
	String title = "";
	dbc = new DbConnection();
	dao = new GeneralDAO(dbc);

	if (requestTopicID == 0 && !requestTopic.isEmpty()) {
		TopicVO voTemp = dao.getTopicByName(requestTopic);
		if (voTemp != null) {
			requestTopicID = voTemp.getTopicID();
		} else {
			requestMessages = "<div style='color:red;font-weight:bold;'>Unable to load that topic.</div>";
		}
	} else if (!requestTag.isEmpty()) {
		requestTagId = dao.getTagByName(requestTag).getTagID();
	}
	if (requestTopicID > 0) {
		if (requestAction.equals("edit")) {
			//editing --> editmode
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
			.navigateURL("SampleTagLibAndBean.jsp")
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
				<li><a href='#'>Home</a></li>
				<li><a href='#'>Index</a></li>
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
						<%} else if (editSingle) {%>
						<form action="${pageContext.request.contextPath}" method="get">
							Topic Name:<input id="topicName" name="topicName" />
							<textarea id="editor" name="editor"></textarea><br />
							<a href="decison.jsp">Cancel</a>
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
