<%-- 
    Document   : decison
    Created on : Mar 22, 2016, 5:24:11 PM
    Author     : CAP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>decision</title>
    </head>
    <body>
        <% String username = request.getParameter("username");%>
        
    <form action="getPage.jsp">
        <button> getWiki </button>
        </form>       
        
    <form action="editor.jsp?username=<%=username%>">
        <button> createWiki </button>
        </form>     </body>
</html>
