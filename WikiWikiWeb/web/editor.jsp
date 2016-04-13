<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="currentUser" class="edu.temple.cis3238.wiki.ui.beans.CurrentUser" scope="session" />
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create or Update a Wiki | WikiWikiWeb</title>
        <link rel="stylesheet" href="http://www.jqwidgets.com/jquery-widgets-documentation/jqwidgets/styles/jqx.base.css" type="text/css" />
        <link type="text/css" rel="stylesheet" href="index.css" />
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.0/jquery.min.js"></script>
        <script type="text/javascript" src="http://www.jqwidgets.com/jquery-widgets-documentation/jqwidgets/jqxcore.js"></script>
        <script type="text/javascript" src="http://www.jqwidgets.com/jquery-widgets-documentation/jqwidgets/jqxbuttons.js"></script>
        <script type="text/javascript" src="http://www.jqwidgets.com/jquery-widgets-documentation/jqwidgets/jqxscrollbar.js"></script>
        <script type="text/javascript" src="http://www.jqwidgets.com/jquery-widgets-documentation/jqwidgets/jqxlistbox.js"></script>
        <script type="text/javascript" src="http://www.jqwidgets.com/jquery-widgets-documentation/jqwidgets/jqxdropdownlist.js"></script>
        <script type="text/javascript" src="http://www.jqwidgets.com/jquery-widgets-documentation/jqwidgets/jqxdropdownbutton.js"></script>
        <script type="text/javascript" src="http://www.jqwidgets.com/jquery-widgets-documentation/jqwidgets/jqxcolorpicker.js"></script>
        <script type="text/javascript" src="http://www.jqwidgets.com/jquery-widgets-documentation/jqwidgets/jqxwindow.js"></script>
        <script type="text/javascript" src="http://www.jqwidgets.com/jquery-widgets-documentation/jqwidgets/jqxeditor.js"></script>
        <script type="text/javascript" src="http://www.jqwidgets.com/jquery-widgets-documentation/jqwidgets/jqxtooltip.js"></script>
        <script type="text/javascript" src="http://www.jqwidgets.com/jquery-widgets-documentation/jqwidgets/jqxcheckbox.js"></script>
        <script type="text/javascript" src="editor.js"></script>
    </head>
    <body>
        <div>
            <form action="${pageContext.request.contextPath}/Logger?username=${currentUser.getUsername()}" method="post">
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
