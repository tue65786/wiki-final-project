<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Log In | WikiWikiWeb</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
        <%
            if (request.getParameter("newUser") != null) {
                out.println("<h1>Congratulations! You have successfully signed up, please login with your newly created credentials</h1>");
                } 
            else if (request.getParameter("logout") != null) {
                out.println("<h1>You have successfully logged out</h1>");
                } 
            else if (request.getParameter("invalidCreds") != null){
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