package WikiWikiWeb;

import edu.temple.cis3238.wiki.dao.GeneralDAO;
import edu.temple.cis3238.wiki.sql.DbConnection;
import edu.temple.cis3238.wiki.vo.TagsVO;
import edu.temple.cis3238.parser.Parser;
import edu.temple.cis3238.wiki.ui.beans.CurrentUser;
import edu.temple.cis3238.wiki.utils.ServletHelpers;
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
//			String userName = request.getParameter("username");
//			out.println(userName + "<br />");
			String topicContent = request.getParameter("editor");
			topicContent = topicContent.substring("<div>".length(), topicContent.length() - "</div>".length());
			String topicTitle = request.getParameter("pTopicID");
			ArrayList<String>[] topicsAndTags = Parser.parseAndCategorize(topicContent);
			out.println(topicContent + "<br />");
			out.println(topicTitle + " " + topicsAndTags[0] + " " + topicsAndTags[1]);

			/*
             * Adds or Updates topic and tags to database;
			 */
			DbConnection dbc = new DbConnection();
			GeneralDAO d = new GeneralDAO(dbc);

			if (request.getParameter("editorMode").equals("update")) {
				int topicID = new Integer(request.getParameter("topicPK"));
				boolean updated = d.updateTopic(new TopicVO(topicID, topicTitle, topicContent, "",
						"", 0));
//				out.println(updated);
			} else if (request.getParameter("editorMode").equals("insert")) {
				ArrayList<TagsVO> tagVOs = new ArrayList<>();
				TagsVO tag;

				TopicVO newTopic = new TopicVO(topicTitle, topicContent);
				int topicId = d.addTopic(newTopic);
				newTopic.setTopicID(topicId);

				for (String tagName : topicsAndTags[1]) {
					tag = new TagsVO(0, tagName, 0);
					int tagId = d.addTag(tag);
					tag.setTagID(tagId);
					tagVOs.add(tag);
				}
				boolean inserted = d.assignTopicTags(newTopic, tagVOs);
//				out.println(inserted);
			}

			dbc.close();
				response.sendRedirect(
						"/WikiWikiWeb/View.jsp");
		}
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
