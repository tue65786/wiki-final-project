<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Welcome | WikiWikiWeb</title>
    </head>
    <body>
        <form action="getPage.jsp">
            <button>Browse Wiki</button>
        </form>       
        <form action="editor.jsp">
            <button>Create Wiki</button>
        </form>
        <hr>
        <form action='/WikiWikiWeb/Logout' method='get'>
            <button>Logout</button>
        </form>
    </body>
</html>
