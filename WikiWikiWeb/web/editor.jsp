<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create a Wiki | WikiWikiWeb</title>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.2/jquery.min.js"></script>
        <script type="text/javascript" src="index.js"></script>
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
            <input type="button" value="&#x1f517" onmousedown="markUp('link');" title="HyperLink (Ctrl+L)"/><br />
            <textarea class="editor"></textarea><br />
            <button>Cancel</button>
            <button>Submit</button>
        </form>
        <hr>
        <form action='/WikiWikiWeb/Logout' method='get'>
            <button>Logout</button>
        </form>
    </body>
</html>
