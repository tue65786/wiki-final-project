<%@page isErrorPage="true" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Error Page</title>
        <style>

            #contentWr {
				position: fixed !important;
				top: 50px;
				right: 0px;
				bottom: 20px;
				left: 0px;
				border-radius: 6px;
				border: 2px double #666;
				background-color: #71B1F1;
				margin: 6px;
				padding: 5px;
			}#contentInner {
				border-radius: 6px;
				border: 1px solid #DEF0A5;
				background-color: #FFF;
				margin: 7px;
				padding: 10px;
				height: 95%;
			}
        </style>
    </head>
    <body>
        <div id="contentWr">
            <div id="contentInner">
                <div style="padding:10px;margin:10px;">
                    <%
						int code = 0;
						if (request.getParameter("errorcode") != null){ 
							code = 404; 
						}else{
						try {
							code = pageContext.getErrorData().getStatusCode();
						} catch (Exception e) {

						}
						}
					%> 

					<% if (code == 404) {%>
                    <h1>Page not found</h1> 
                    <%} else{%>
                    <h1>Whoops! Something went wrong.</h1>
					<table width="600" style="border:0px;">
						<tr valign="top">
							<td ><b>Error:</b></td>
							<td>${pageContext.exception}</td>
						</tr>
						<tr valign="top">
							<td><b>URI:</b></td>
							<td>${pageContext.errorData.requestURI}</td>
						</tr>
						<tr valign="top">
							<td><b>Status code:</b></td>
							<td>${pageContext.errorData.statusCode}</td>
						</tr>
						<tr valign="top">
							<td><b>Details:</b></td>
							<td> 
								<%
									if (pageContext != null && pageContext.getException() != null) {
										StackTraceElement[] ste = pageContext.getException().getStackTrace();
										if (ste == null || ste.length == 0) {

										} else {
											for (StackTraceElement anSte : ste) {
												out.print(anSte.toString());
												out.print("<br/>");
											}
										}
									}
								%>
							</td>
						</tr>
					</table>
					<%}%>
               <h3><a href="View.jsp">Click</a> to go back.</h3>
				</div>
            </div>
        </div>
    </body>
</html>
