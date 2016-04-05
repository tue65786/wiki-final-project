<%-- 
    Document   : decison
    Created on : Mar 22, 2016, 5:24:11 PM
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
        <title>decision</title>
    </head>
    <body>
    <% 
        
            String username = request.getParameter("username");
            String current = null;
            DbConnection dbc = new DbConnection();
            IGeneralDAO g = new GeneralDAO(dbc);
            ArrayList<TagsVO> allTags = g.getTags();
            out.println("<h1>Welcome " + username + "</h1>");
        %>
        
        <br></br>
    <form action="getPage.jsp">
        <button> getWiki </button>
        </form>       
        
    <form action="editor.jsp?username=<%=username%>">
        <button> createWiki </button>
        </form>     </body>
    
   
           <h3> search by TAGS </h3>
           
           
        <% for(int i = 0; i < allTags.size(); i+=1) { %>
        
            <% current = allTags.get(i).getTagName();%>
            <br>
            <a href = "tag.jsp?tag=<%= current %>"> <%= current %> </a>
            </br>
            
            <% } %>
            
         <% dbc.close(); %>
</html>
