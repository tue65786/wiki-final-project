<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.0/jquery.min.js"></script>
        <link type="text/css" rel="stylesheet" href="index.css" />
        <title>Signup</title>
    </head>
    <body>

        <script>
			$(document).ready(function () {
            <% if (request.getParameter("errorMessageUserName") != null) { %>
				$("#errorMessageUserName").slideDown("slow");
            <% } else if (request.getParameter("invalidPassword") != null) { %>
				$("#invalidPassword").slideDown("slow");
            <% } else if (request.getParameter("invalidUsername") != null) { %>
				$("#invalidUsername").slideDown("slow");
            <% }%>
            <% if (!request.getParameterMap().isEmpty()) { %>
				$("#initialMsg").css("display", "none");
            <% }%>
			});
        </script>

        <div id='bg-image'></div>
        <div id="form-div">
            <h1 id='errorMessageUserName'> User name already taken! try something else. </h1>
            <h2 id='invalidPassword'> Invalid password.  Must contain at least 8 characters, 2 digits, and 1 special character </h2>
            <h3 id='invalidUsername'> Invalid username. Must contain at least 4 characters, no special characters </h3>
            <p id='initialMsg'> Please enter a username and password </p>

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
