<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create or Update a Wiki | WikiWikiWeb</title>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.0/jquery.min.js"></script>
        <script type="text/javascript" src="index.js"></script>
    </head>
    <body>
        <form action="${pageContext.request.contextPath}/Logger?username=<%=request.getParameter("username")%>" method="post">
            <%
                if (request.getParameter("username") != null) {
                    out.println("<h1>Username: " + request.getParameter("username") + "</h1>");
                    out.println("<hr>");
                }
            %>
            <input type="button" value="Topic" onmousedown="markUp('boldlink');" title="Bold HyperLink (Ctrl+Shift+L)" />
            <input type="button" value="Tag" onmousedown="markUp('link');" title="HyperLink (Ctrl+L)" /><br />
            <textarea class="editor" name="editor"></textarea><br />
            <button>Cancel</button>
            <button>Submit</button>
        </form>

        <!-- -->
        <hr>
        Username: <input name="user" /><br />
        <button>Log</button>
        <!-- -->

        <form action='/WikiWikiWeb/Logout' method='get'>
            <button>Logout</button>
        </form>
    </body>
</html>
