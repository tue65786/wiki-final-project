<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="edu.temple.cis3238.wiki.vo.TagsVO"%>
<%@page import="edu.temple.cis3238.wiki.vo.TopicVO"%>
<%@page import="java.util.ArrayList"%> 
<%@page import="edu.temple.cis3238.wiki.dao.GeneralDAO"%>
<%@page import="edu.temple.cis3238.wiki.dao.IGeneralDAO"%>
<%@page import="edu.temple.cis3238.wiki.sql.*"%>
<jsp:useBean id="currentUser" class="edu.temple.cis3238.wiki.ui.beans.CurrentUser" scope="session" />
<!DOCTYPE html>
<html>
    <head>

        <link href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css" rel="stylesheet" type="text/css"/>
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.4/jquery.min.js"></script>
        <script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/jquery-ui.min.js"></script>
        <link type="text/css" rel="stylesheet" href="index.css" />

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>decision</title>
    </head>
    <body> 

        <%if (request.getParameter("createSuccess") != null) {%>
		<h1 id='createSuccess'>You have successfully created a wiki: <%=request.getParameter("createSuccess")%></h1>
		<script>
			$(document).ready(function () {
				$('#createSuccess').slideDown('slow');
			});
		</script>
		<% }%>
		<%
			if (request.getParameter("login") != null) {
				String username = request.getParameter("username");
				currentUser.setUsername(username);
			}
			String current = null;
			DbConnection dbc = new DbConnection();
			IGeneralDAO g = new GeneralDAO(dbc);
			ArrayList<TagsVO> allTags = g.getTags();
			out.println("<p>Welcome " + currentUser.getUsername() + "</p>");
        %>

        <br></br>
        <form action="getPage.jsp">
            <button> getWiki </button>
        </form>

        <form action="editor.jsp?username=${currentUser}">
            <button> createWiki </button>
        </form>

        <form action="${pageContext.request.contextPath}/Logout" method='get'>
            <button>Logout</button>
        </form>

        <h3> search by TAGS </h3>

        <script>
			var source = [];
        </script>

        <label> Quick Search by tags :  <input id="autocomplete" /> </label>

		<h4>ALL TAGS</h4>
        <% for (int i = 0; i < allTags.size(); i += 1) {
				current = allTags.get(i).getTagName();%>
        <script> source.push({value: 'tag.jsp?tag=<%= current%>', label: '<%= current%>'});</script>
        <% } %>

        <script>
			$(document).ready(function () {
				$("input#autocomplete").autocomplete({
					source: source,
					select: function (event, ui) {
						window.location.href = ui.item.value;
					}
				});
			});
        </script>

        <table>
            <% for (int i = 0; i < allTags.size(); i += 1) { %>

            <% current = allTags.get(i).getTagName();%>
            <tr> 
                <td><a href = "tag.jsp?tag=<%= current%>"> <%= current%> </a></td>
            </tr>

            <% } %>
        </table>

        <% dbc.close();%>
</html>
