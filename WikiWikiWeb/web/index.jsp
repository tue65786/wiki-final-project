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
        <meta ht
			  tp-equiv="Content-Type" content="text/html; charset=UTF-8">
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
            <h3 id="newUser">Congratulations! You have successfully signed up, please login with your newly created credentials</h3>
            <h3 id="logout">You have successfully logged out</h3>
            <h3 id="invalidCreds">Invalid username or password</h3>

            <form action="${pageContext.request.contextPath}/Login" method="post">
                <div>Username:</div>
                <input name="user" id="username" /><br />
                <div>Password:</div>
                <input type="password" name="pass" /><br />
                <button>Submit</button><br />
                <a href="signup.jsp">New User</a>
            </form>
        </div>
		<script type="text/javascript">
			$(document).ready(function () {
			$("#username").focus();
		});
		</script>
    </body>
</html>