<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Log In | WikiWikiWeb</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
        <form action="${pageContext.request.contextPath}/Example" method="get">
            Username: <input name="user" /><br />
            Password: <input name="pass" /><br />
            <button>Submit</button>
        </form>
    </body>
</html>