<%-- 
    Document   : SampleTagLibAndBean
    Created on : Mar 31, 2016, 11:00:18 AM
    Author     : 
--%>

<%@page import="edu.temple.cis3238.wiki.utils.ServletHelpers"%>
<%@page import="java.util.ArrayList"%>
<%@page import="edu.temple.cis3238.wiki.vo.TopicVO"%>
<%@page import="edu.temple.cis3238.wiki.dao.GeneralDAO"%>
<%@page import="edu.temple.cis3238.wiki.sql.DbConnection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="topicCollection" class="edu.temple.cis3238.wiki.ui.beans.TopicCollection" scope="session"/>
<jsp:setProperty name="topicCollection" property="*"/>
<%@ taglib prefix="wiki" uri="/WEB-INF/tlds/wiki.tld"%>
<%
   DbConnection dbc;
   GeneralDAO dao;
   ServletHelpers web;
   String requestMessages = "";
   ArrayList<TopicVO> topics;

   dbc = new DbConnection();
   dao = new GeneralDAO( dbc );
   web = new ServletHelpers( request, response );
   topics = dao.getTopics();
   topicCollection.setTopics( topics );
   
   //Safely get request params
   String requestTopic = web.getStrParameter( "pTopicID", "" );  
   int requestTopicID = web.getIntParameter( "topicPK", 0 );
   String requestTag = web.getStrParameter( "pTagID", "" );
   int requestTagId = web.getIntParameter( "tagPK", 0 );
   String listType = web.getStrParameter( "type", "LIST" );
   //topicCollection.setListType( listType);
   if (requestTagId >0){
	  requestMessages = "<h3>SELECTED TAG " + requestTag+"</h3>";
   }
   else if (requestTopicID>0){
	  requestMessages = "<h3>SELECTED TOPIC " + requestTopic+"</h3>";
   }
   dbc.close();
%>
<!DOCTYPE html>
<html>
    <head>
		<style>
			table.topicTable,table.topicTable td {border:1px solid black;padding:0;margin:0;}
			table.topicTable tr.oddrow td{background-color:#eee;}
			table.topicTable th{background-color:black;color:white;}
			ul.topiclist {list-style-type: none;width:75%;}
			ul.topiclist li h4{display: block;font-size: 14px;line-height: 22px;margin: 0;padding: 0 0 0 24px;}
			li p.content{margin: 3px 0 0 25px;padding: 0;line-height: 15px;font-size: 12px;}
			li p.tags {font-size:11px;font-style: italic;margin: 3px 0 0 25px;padding:2px 0;}
			li p.stats{font-size: 11px;color: #666; line-height: normal;margin: 3px 0 1.5em 25px;padding: 0;border-bottom: 1px dotted lightgrey;}
		</style>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Tag Lib Demo</h1>
		<%=requestMessages%>
		<h5>TABLE</h5>
		<wiki:TopicList topicsList="${topicCollection}" 
						listStyle="TABLE"  
						sortField="" 
						tagLinkPage="SampleTagLibAndBean.jsp" 
						tagLinkRequestParam="pTagID" 
						topicLinkPage="SampleTagLibAndBean.jsp" 
						topicLinkRequestParam="pTopicID">
			
		</wiki:TopicList> 
		<hr/>
		<h5>LIST</h5>
		<wiki:TopicList topicsList="${topicCollection}" 
						listStyle="LIST"  
						sortField="" 
						tagLinkPage="SampleTagLibAndBean.jsp" 
						tagLinkRequestParam="pTagID" 
						topicLinkPage="SampleTagLibAndBean.jsp" 
						topicLinkRequestParam="pTopicID">
			
		</wiki:TopicList> 
    </body>
</html>
