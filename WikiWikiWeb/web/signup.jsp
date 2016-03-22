<%-- 
    Document   : signup
    Created on : Mar 22, 2016, 4:10:24 PM
    Author     : CAP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Signup</title>
    </head>
    <body>
    <form action="${pageContext.request.contextPath}/Signup" method="get">
            Username: <input name="user" /><br />
            Password: <input name="pass" /><br />
            <button>Submit</button>
        </form>
    </body>
</html>
