<%-- 
    Document   : SampleTagsTagLib
    Created on : Apr 8, 2016, 7:41:36 AM
    Author     : (c)2016 
--%>
<%@page import="java.util.Collections"%>
<%@page import="edu.temple.cis3238.wiki.utils.ServletHelpers"%>
<%@page import="java.util.ArrayList"%>
<%@page import="edu.temple.cis3238.wiki.vo.TagsVO"%>
<%@page import="edu.temple.cis3238.wiki.dao.GeneralDAO"%>
<%@page import="edu.temple.cis3238.wiki.sql.DbConnection"%>
<%@page import="edu.temple.cis3238.wiki.ui.tags.helpers.TagsTagSettings"%>
<jsp:useBean id="tagsCollection" class="edu.temple.cis3238.wiki.ui.beans.TagsCollection" scope="session"/>
<jsp:setProperty name="tagsCollection" property="*"/>
<%@ taglib prefix="wiki" uri="/WEB-INF/tlds/wiki.tld"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
   DbConnection dbc;
   GeneralDAO dao;
   ArrayList<TagsVO> tags;
   TagsTagSettings settings;

   dbc = new DbConnection();
   dao = new GeneralDAO( dbc );
   
   tags = dao.getTags();
   Collections.sort(tags);
   settings = TagsTagSettings.builder()
		   .queryStringParam( "tagPK" )
		   .navigateURL( "SampleTagLibAndBean.jsp" )
		   .style( "cloud" )
		   .widthPx( "200px")
		   .tagsVOList( tags ).build();
   tagsCollection.setSettings( settings );
   dbc.close();
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Tag Cloud</title>
    </head>
    <body>
        <wiki:TagsList tagsCollectionBeans="${tagsCollection}"></wiki:TagsList>
    </body>
</html>
