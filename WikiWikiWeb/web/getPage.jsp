<%-- 
    Document   : getPage
    Created on : Mar 22, 2016, 5:03:48 PM
    Author     : CAP
--%>

<%@page import="edu.temple.cis3238.wiki.vo.TagsVO"%>
<%@page import="edu.temple.cis3238.wiki.vo.TopicVO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="edu.temple.cis3238.wiki.dao.GeneralDAO"%>
<%@page import="edu.temple.cis3238.wiki.dao.IGeneralDAO"%>
<%@page import="edu.temple.cis3238.wiki.sql.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>getPage</title>
    </head>
    <body>
        
        
        <%
            DbConnection dbc = new DbConnection();
            IGeneralDAO  g = new GeneralDAO(dbc);
            ArrayList<TopicVO> allTopics = g.getTopics();
            ArrayList<TagsVO> allTags = g.getTags();
            
            String test ="8";
            
            ArrayList<String> testTopics = new ArrayList();
            testTopics.add("Topic1");
            testTopics.add("Topic2");
            testTopics.add("Topic3");
            testTopics.add("Topic4");
            testTopics.add("Topic5");
            %>
        
     
            <h2> TOPICS </h2>
        
            <%String current = null;%>
        
        <% for(int i = 0; i < allTopics.size(); i+=1) { %>
        
            <% current = allTopics.get(i).getTopicName();%>
            <br>
            <a href = "wiki.jsp?id=<%= current %>"> <%= current %> </a>
            </br>
            
            <% } %>  

                <h3> TAGS </h3>
            
        <% for(int i = 0; i < allTags.size(); i+=1) { %>
        
            <% current = allTags.get(i).getTagName();%>
            <br>
            <a href = "wiki.jsp?tag=<%= current %>"> <%= current %> </a>
            </br>
            
            <% } %> 
            
        <!-- testing 1,2,3.. -->
       
        <%-- for(int i = 0; i < testTopics.size(); i+=1) { %>
        
            <% String current = testTopics.get(i);%>
            <br>
            <a href = "wiki.jsp?id=<%= current %>"> <%= current %> </a>
            </br>
            <% } --%>  
            
        <!-- SUCCESS! -->   
        
        <% dbc.close(); %>
 
    </body>
</html>
