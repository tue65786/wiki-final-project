<%-- 
    Document   : getPage
    Created on : Mar 22, 2016, 5:03:48 PM
    Author     : CAP
--%>

<%@page import="edu.temple.cis3238.wiki.vo.TagsVO"%>
<%@page import="edu.temple.cis3238.wiki.vo.TopicVO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="edu.temple.cis3238.wiki.dao.GeneralDAO"%>
<%@page import="edu.temple.cis3238.wiki.dao.IGeneralDAO"%>
<%@page import="edu.temple.cis3238.wiki.sql.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>
    <head>

        <link href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css" rel="stylesheet" type="text/css"/>
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.4/jquery.min.js"></script>
        <script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/jquery-ui.min.js"></script>



        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>getPage</title>
    </head>
    <body>


        <%
            DbConnection dbc = new DbConnection();
            IGeneralDAO g = new GeneralDAO(dbc);
            ArrayList<TopicVO> allTopics = g.getTopics();
            String current = null;
        %>

        <form action='/WikiWikiWeb/Logout' method='get'>
            <button>Logout</button>
        </form>

        <h2> TOPICS </h2>

        <script>
            var source = [];
        </script>

        <label> Quick Search :  <input id="autocomplete" /> </label>

        <% for (int i = 0; i < allTopics.size(); i += 1) {
                current = allTopics.get(i).getTopicName();%>
        <script> source.push({value: 'wiki.jsp?topic=<%= current%>', label: '<%= current%>'});</script>
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
            <% for (int i = 0; i < allTopics.size(); i += 1) { %>

            <% current = allTopics.get(i).getTopicName();%>
            <tr>
                <td><a href = "wiki.jsp?topic=<%= current%>"> <%= current%> </a></td>
            </tr>

            <% } %>  

        </table>


        <% dbc.close();%>

    </body>
</html>
