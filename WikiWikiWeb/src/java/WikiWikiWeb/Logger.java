package WikiWikiWeb;

import edu.temple.cis3238.parser.WikiMarkup;
import edu.temple.cis3238.wiki.dao.GeneralDAO;
import edu.temple.cis3238.wiki.sql.DbConnection;
import edu.temple.cis3238.wiki.vo.TagsVO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "Logger", urlPatterns = {"/Logger"})
public class Logger extends HttpServlet {

   protected void createNewWiki(HttpServletRequest request, HttpServletResponse response)
		   throws ServletException, IOException {
	  response.setContentType("text/html;charset=UTF-8");
	  try (PrintWriter out = response.getWriter()) {

		 /*
          * Prints the username; for testing
          * Logs the changes made by user
		  */
		 String userName = request.getParameter("username");
		 out.println(userName + "<br />");
		 logToHistory(userName);

		 /*
          * Gets the topic name and tag name(s) and prints them out
		  */
		 String topicContent = request.getParameter("editor");
		 Scanner scanner = new Scanner(topicContent);
		 String s = null;
		 String topicName = null;
		 ArrayList<String> tagNames = new ArrayList<>();
		 int end = 0;

		 /*
		  * Single letter words crash substring() because of StringIndexOutOfBoundsException
		  * Single letter words should never contain wiki markup, so OK to skip over
		  */
		 while (scanner.hasNext()) {
			s = scanner.next();
			System.out.println(s);
			if (s.length() > 1) {
			   if (s.substring(0, 2).equals(WikiMarkup.FRONT_TOPIC.toString())) {
				  //we know we are in a topic expression
				  // should only have ONE topic name
				  end = s.indexOf(WikiMarkup.BACK_TOPIC.toString());
				  topicName = s.substring(2, end);

			   } else if (s.substring(0, 2).equals(WikiMarkup.FRONT_TAG.toString())) {
				  //we know we are in a topic expression
final 				  end = s.indexOf(WikiMarkup.BACK_TAG.toString());
				  tagNames.add(s.substring(2, end));

			   } //add more else if for more regexes
			   else {
			   }
			}
		 }
		 out.println(topicName + "<br />" + tagNames.toString());

		 /*
          * Adds topic and tags to database
		  */
		 DbConnection dbc = new DbConnection();
		 GeneralDAO d = new GeneralDAO(dbc);
		 ArrayList<TagsVO> tagVOs = new ArrayList<>();
		 TagsVO tag = null;

//            TopicVO topic = new TopicVO(topicName, topicContent);
//            for (String tagName : tagNames) {
//                tag = new TagsVO(0, tagName, /*parentTag*/);
//                tagVOs.add(tag);
//                d.addTag(tag);
//            }
//            d.assignTopicTags(topic, tagVOs);
		 dbc.close();
	  }
   }

   private void logToHistory(String username) {
   }

   @Override
   protected void doGet(HttpServletRequest request, HttpServletResponse response)
		   throws ServletException, IOException {
	  createNewWiki(request, response);
   }

   @Override
   protected void doPost(HttpServletRequest request, HttpServletResponse response)
		   throws ServletException, IOException {
	  createNewWiki(request, response);
   }

   @Override
   public String getServletInfo() {
	  return "Short description";
   }// </editor-fold>

}
