<%-- 
    Document   : getPage
    Created on : Mar 22, 2016, 5:03:48 PM
    Author     : CAP
--%>

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
            IGeneralDAO  g = new GeneralDAO(new DbConnection());
            ArrayList<TopicVO> allTopics = g.getTopics();
            %>
        
        <h1>Hello World!</h1>
        <% for(int i = 0; i < 4; i++) { %>
        <tr>      
            <%--  <td><%=allTopics.get(i).getTopicName()%></td> --%>
           
        </tr>
    <% } %>
    </body>
</html>
