<%-- 
    Document   : UploadForm
    Created on : Apr 5, 2016, 6:41:07 AM
    Author     : Doreen, Christian, Dan
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="edu.temple.cis3238.wiki.vo.TopicVO"%>
<%@page import="edu.temple.cis3238.wiki.dao.GeneralDAO"%>
<%@page import="edu.temple.cis3238.wiki.sql.DbConnection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="topicCollection" class="edu.temple.cis3238.wiki.ui.beans.TopicCollection" scope="session"/>
<jsp:setProperty name="topicCollection" property="*"/>

<%
   DbConnection dbc = new DbConnection();
   GeneralDAO dao = new GeneralDAO( dbc );
   ArrayList<TopicVO> topics = dao.getTopics();
   TopicVO topic = topics.get( 0 );   
   
   dbc.close();
   
  //Populate topic collection bean (req'd for file upload servlet)
   topicCollection.setTopics( topics );
   topicCollection.setCurrentTopic( topic );
   
   
   
%>
<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form method="post" action="Uploader" enctype="multipart/form-data">
			<input id="uploadFile" type="file" name="uploadFile" /> 
			<br/>
			<input id="submitBtn" type="submit" value="Upload" />
		</form>
    </body>
</html>
