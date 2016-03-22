<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Log In | WikiWikiWeb</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.2/jquery.min.js"></script>
        <script type="text/javascript" src="index.js"></script>
    </head>
    <body>
        <form action="${pageContext.request.contextPath}/Example" method="get">
            Username: <input name="user" /><br />
            Password: <input name="pass" /><br />
            <button>Submit</button>
        </form>
    </body>
</html>