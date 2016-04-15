package WikiWikiWeb;

import edu.temple.cis3238.wiki.dao.GeneralDAO;
import edu.temple.cis3238.wiki.sql.DbConnection;
import edu.temple.cis3238.wiki.vo.TagsVO;
import edu.temple.cis3238.parser.Parser;
import edu.temple.cis3238.wiki.parser.TagsFromContentParser;
import edu.temple.cis3238.wiki.ui.beans.CurrentUser;
import edu.temple.cis3238.wiki.utils.*;
import static edu.temple.cis3238.wiki.utils.StringUtils.toS;
import edu.temple.cis3238.wiki.vo.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "Logger", urlPatterns = {"/Logger"})
public class Logger extends HttpServlet {

	protected void createNewWiki(HttpServletRequest request,
								 HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");

		CurrentUser currentUser = new CurrentUser("NaU");

		try (PrintWriter out = response.getWriter()) {

			try {
				currentUser = (CurrentUser) request.getSession().getAttribute("currentUser");
			} catch (NullPointerException e) {
				if (request != null && request.getCookies() != null) {
					try {
						ServletHelpers helpers = new ServletHelpers(request, response);
						if (helpers.getCookie("currentuser") != null && !toS(helpers.getCookie(
								"currentuser").getValue()).isEmpty()) {
							currentUser = new CurrentUser(
									helpers.getCookie("currentuser").getValue());
						}

					} catch (Exception er) {
						er.printStackTrace();
					}
				}
			} catch (Exception e) {
				e.printStackTrace();

			}

			/*
             * Gets the username, topic name, and tag name(s) and prints them out
			 * Only for testing
			 */
			String topicContent = request.getParameter("editorClean");
			topicContent = cleanTopicContent(topicContent);
			String topicTitle = request.getParameter("pTopicID");
			TagsFromContentParser tagsFromContentParser = TagsFromContentParser.create(topicContent);
			System.out.println("WikiWikiWeb.Logger.createNewWiki() - Found tags:\n" +
					tagsFromContentParser.getTagNameCSV() + "\n----------------\n");

			/*
             * Adds or Updates topic and tags to database;
			 */
			DbConnection dbc = new DbConnection();
			GeneralDAO d = new GeneralDAO(dbc);

			if (request.getParameter("editorMode").equals("update")) {
				System.out.println("WikiWikiWeb.Logger.createNewWiki() - mode = update");
				int topicID = new Integer(request.getParameter("topicPK"));
				TopicVO topicVO = new TopicVO(topicID, topicTitle, topicContent, "",
						"", 0);
				boolean updated = d.updateTopic(topicVO);
				if (!tagsFromContentParser.getTagNameCSV().isEmpty()) {
					d.assignOnlyTopicsTagNamesFromTagCSV(topicVO,
							tagsFromContentParser.getTagNameCSV());
				}
			} else if (request.getParameter("editorMode").equals("insert")) {
				System.out.println("WikiWikiWeb.Logger.createNewWiki() - mode = Insert");
				TopicVO newTopic = new TopicVO(topicTitle, topicContent);
				int topicId = d.addTopic(newTopic);
				newTopic.setTopicID(topicId);
				if (!tagsFromContentParser.getTagNameCSV().isEmpty()) {
					d.assignOnlyTopicsTagNamesFromTagCSV(newTopic,
							tagsFromContentParser.getTagNameCSV());
				}
			}

			dbc.close();
			response.sendRedirect(
					"/WikiWikiWeb/View.jsp");
		}
	}

	private String cleanTopicContent(String topicContent) {
		topicContent = StringUtils.toS(topicContent);
		topicContent = org.apache.commons.lang3.StringUtils.removeStart(topicContent, "<div>");
		topicContent = org.apache.commons.lang3.StringUtils.removeEnd(topicContent, "</div>");
		topicContent = topicContent.replaceAll("\\p{C}|\\p{Cc}|\\p{Cntrl}", "");
		topicContent = org.apache.commons.lang3.StringUtils.stripAccents(topicContent);
		return topicContent;
	}

	@Override
	protected void doGet(HttpServletRequest request,
						 HttpServletResponse response)
			throws ServletException, IOException {
		createNewWiki(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,
						  HttpServletResponse response)
			throws ServletException, IOException {
		createNewWiki(request, response);
	}

	@Override
	public String getServletInfo() {
		return "Short description";
	}// </editor-fold>

}
