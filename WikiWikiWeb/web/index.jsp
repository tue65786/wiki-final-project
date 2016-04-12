<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html><html>
    <head>
        <script>
            window.location.hash = "no-back-button";
            window.location.hash = "Again-No-back-button";//again because google chrome don't insert first hash into history
            window.onhashchange = function () {
                window.location.hash = "no-back-button";
            };
        </script>

        <title>Log In | WikiWikiWeb</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.0/jquery.min.js"></script>
        <link type="text/css" rel="stylesheet" href="index.css" />
    </head>
    <body>

        <script>
            $(document).ready(function () {
            <% if (request.getParameter("newUser") != null) { %>
                $("#newUser").slideDown("slow");
            <% } else if (request.getParameter("logout") != null) { %>
                $("#logout").slideDown("slow");
            <% } else if (request.getParameter("invalidCreds") != null) { %>
                $("#invalidCreds").slideDown("slow");
            <% }%>
            });
        </script>

        <div id="bg-image"></div>
        
        <div id="form-div">
            <ul id="msg-ul">
                <li id="newUser">Congratulations! You have successfully signed up, please login with your newly created credentials</li>
                <li id="logout">You have successfully logged out</li>
                <li id="invalidCreds">Invalid username or password</li>
            </ul>

            <form action="${pageContext.request.contextPath}/Login" method="post">
                <div>Username:</div>
                <input name="user" /><br />
                <div>Password:</div>
                <input type="password" name="pass" /><br />
                <button>Submit</button><br />
                <a href="signup.jsp">New User</a>
            </form>
        </div>

    </body>
</html>