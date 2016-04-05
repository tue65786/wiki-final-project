<%-- 
    Document   : UploadSuccess
    Created on : Apr 5, 2016, 6:44:49 AM
    Author     : (c)2016 Temple
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="topicCollection" class="edu.temple.cis3238.wiki.ui.beans.TopicCollection" scope="session"/>
<jsp:setProperty name="topicCollection" property="*"/>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
		
    </head>
    <body>
        <h1>${pageContext.request.getAttribute("message")}</h1>
    </body>
</html>
