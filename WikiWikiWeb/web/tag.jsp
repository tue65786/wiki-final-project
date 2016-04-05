<%-- 
    Document   : tag
    Created on : Apr 4, 2016, 8:23:21 PM
    Author     : CAP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="edu.temple.cis3238.wiki.vo.TagsVO"%>
<%@page import="edu.temple.cis3238.wiki.vo.TopicVO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="edu.temple.cis3238.wiki.dao.GeneralDAO"%>
<%@page import="edu.temple.cis3238.wiki.dao.IGeneralDAO"%>
<%@page import="edu.temple.cis3238.wiki.sql.*"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <% 
            String tag = request.getParameter("tag");
        
            String current = null;
            
            DbConnection dbc = new DbConnection();
            IGeneralDAO  g = new GeneralDAO(dbc);
            ArrayList<TopicVO> allTopics = g.getTopicsByTagName(tag);
               
        %>
        
        <% for(int i = 0; i < allTopics.size(); i+=1) { %>
        
            <% current = allTopics.get(i).getTopicName();%>
            <br>
            <a href = "wiki.jsp?topic=<%= current %>"> <%= current %> </a>
            </br>
            
            <% } %>
            
            <% dbc.close();%>
        
    </body>
</html>
