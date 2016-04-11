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
        <link type="text/css" rel="stylesheet" href="index.css" />
        <title>Signup</title>
    </head>
    <body>
        <div id="msg-div">
            <% if (request.getParameter("errorMessageUserName") != null) { %>
            <h1> User name already taken! try something else. </h1>
            <% } %>
            
            <% if (request.getParameter("invalidPassword") != null) { %>
            <h2> Invalid password.  Must contain at least 8 characters, 2 digits, and 1 special character </h2>
            <% } %>

            <% if (request.getParameter("invalidUsername") != null) { %>
            <h3> Invalid username. Must contain at least 4 characters, no special characters </h3>
            <% } %>

            <% if (request.getParameterMap().isEmpty()) { %>
            <p> Please enter a username and password </p>
            <% } %>
        </div>

        <div id="form-div">
            <form action="${pageContext.request.contextPath}/Signup" method="post">
                <div>Username:</div>
                <input name="user" size ="25"/><br />
                <div>Password:</div>
                <input type="password" size ="25" name="pass" /><br />
                <button>Submit</button>
            </form>
        </div>
    </body>
</html>
