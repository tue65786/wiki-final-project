<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Log In | WikiWikiWeb</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
        <% if (request.getParameter("user") != null) {
                    out.println("<h1>Username: " + request.getParameter("user") + " is already taken</h1>");
                } else {
                    out.println("You were redirected");
                }
                out.println("<hr>");
                %>
        
        <form action="${pageContext.request.contextPath}/Example" method="get">
            Username: <input name="user" /><br />
            Password: <input type="password" name="pass" /><br />
            <button>Submit</button>
        </form>
            
            <a href="signup.jsp">New User</a>
            
    </body>
</html>