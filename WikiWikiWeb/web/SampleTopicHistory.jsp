<%-- 
    Document   : SampleTopicHistory
    Created on : Apr 14, 2016, 5:05:49 PM
    Author     : (c)2016 Guiding Technologies
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
http://cemerick.github.io/jsdifflib/demo.html
		<script>
			function diff() {

				$("#wrapper tr").prettyTextDiff({
					cleanup: true,
					originalContent: "Some text here.",
					changedContent: "Some more text which can be passed to this function.",
					diffContainer: ".diff2"
				});
			}
		</script>
	</body>
</html>
