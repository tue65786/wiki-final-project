<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="currentUser" class="edu.temple.cis3238.wiki.ui.beans.CurrentUser" scope="session" />
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create or Update a Wiki | WikiWikiWeb</title>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.0/jquery.min.js"></script>
        <script type="text/javascript" src="editor.js"></script>
        <link type="text/css" rel="stylesheet" href="index.css" />
    </head>
    <body>
        <div>
            <form action="${pageContext.request.contextPath}/Logger?username=${currentUser.getUsername()}" method="post">
                <%
                    if (currentUser.getUsername() != null) {
                        out.println("<h1>Username: " + currentUser.getUsername() + "</h1>");
                        out.println("<hr>");
                    }
                %>
                <input type="button" value="Topic" onmousedown="markUp('boldlink');" title="Bold HyperLink (Ctrl+Shift+L)" />
                <input type="button" value="Tag" onmousedown="markUp('link');" title="HyperLink (Ctrl+L)" /><br />
                <textarea id="editor" name="editor"></textarea><br />
                <button>Cancel</button>
                <button>Submit</button>
            </form>
        </div>
                
        <form action='/WikiWikiWeb/Logout' method='get'>
            <button>Logout</button>
        </form>
    </body>
</html>
