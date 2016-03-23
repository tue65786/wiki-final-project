<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create or Update a Wiki | WikiWikiWeb</title>
    </head>
    <body>
        <form action="${pageContext.request.contextPath}/Example" method="post">
            <%
                if (request.getParameter("user") != null) {
                    out.println("<h1>Username: " + request.getParameter("user") + "<br />Password: " + request.getParameter("pass") + "</h1>");
                } else {
                    out.println("You were redirected");
                }
                out.println("<hr>");
            %>
            <textarea name="test"></textarea><br />
            <button>Cancel</button>
            <!--button>Submit</button-->
            
            <!-- -->
            <form action="${pageContext.request.contextPath}/Logger" method="get">
            Username: <input name="user" /><br />
            <button>Submit</button>
            </form>
            <!-- -->
            
        </form>
        <hr>
        <form action='/WikiWikiWeb/Logout' method='get'>
            <button>Logout</button>
        </form>
    </body>
</html>
