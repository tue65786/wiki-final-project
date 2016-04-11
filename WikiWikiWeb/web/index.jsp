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
    </head>
    <body>


        <%
            if (request.getParameter("newUser") != null) {
                out.println("<h1>Congratulations! You have successfully signed up, please login with your newly created credentials</h1>");
            } else if (request.getParameter("logout") != null) {
                out.println("<h1>You have successfully logged out</h1>");
            } else if (request.getParameter("invalidCreds") != null) {
                out.println("<h1>Invalid username or password</h1>");
            }
        %>



        <form action="${pageContext.request.contextPath}/Login" method="post">
            Username: <input name="user" /><br />
            Password: <input type="password" name="pass" /><br />
            <button>Submit</button>
        </form>

        <a href="signup.jsp">New User</a>
    </body>
</html>