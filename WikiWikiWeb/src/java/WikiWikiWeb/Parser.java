package WikiWikiWeb;

import edu.temple.cis3238.parser.*;
import edu.temple.cis3238.wiki.dao.GeneralDAO;
import edu.temple.cis3238.wiki.dao.IGeneralDAO;
import edu.temple.cis3238.wiki.sql.DbConnection;
import edu.temple.cis3238.wiki.vo.TagsVO;
import edu.temple.cis3238.wiki.vo.TopicVO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "Parser", urlPatterns = {"/Parser"})
public class Parser extends HttpServlet {

   /**
    * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
    *
    * @param request  servlet request
    * @param response servlet response
    * @throws ServletException if a servlet-specific error occurs
    * @throws IOException      if an I/O error occurs
    */
   protected void processParser(HttpServletRequest request, HttpServletResponse response)
		   throws ServletException, IOException {
	  response.setContentType("text/html;charset=UTF-8");
	  try (PrintWriter out = response.getWriter()) {
		 //my code
		 //get the id
		 String id = request.getParameter("id");
		 //ALWAYS TOPIC

//String wikiText = "Hi this will be our sample wikiText.  The [[dog]] ate the {{cat}}";
		 String wikiText = "osdufyg [[bfhewlekr]] jhgfjdksdfhbvj {{dksla}} djfhgfj {{dsasd}} jfghgfjdks";

		 //get the wiki text from dan's database
		 /*
                1. find the topic or tag that matches the String id
                2. Then get that topic or tag wikiText  
		  */
		 DbConnection dbc = new DbConnection();
		 IGeneralDAO d = new GeneralDAO(dbc);

		 dbc.close();
		 TopicVO topic = null;

		 try {
			topic = d.getTopicByID(1);
//            topic = d.getTopicByName(id);
			wikiText = topic.getTopicContent();
		 } catch (Exception e) {
			response.sendRedirect("getPage.jsp?errorMsg=Page Does Not Exist");
		 }

//            try{
//             if((topic = d.getTopicByName(id)) == null){
//                tag = d.getTagByName(id);
//                //wikiText = tapg.getWikiText();
//             }
//             else{
//                 //wikiText = topic.getWikiText();
//                 
//             }
//                     
//            } catch(Exception e){
//                    //page not found
//                    //we should return the user to getPage.jsp
//            }
		 //call the parser method
		 out.println("<!DOCTYPE html>");
		 out.println("<html>");
		 out.println("<head>");
		 out.println("<title>Servlet NewServlet</title>");
		 out.println("</head>");
		 out.println("<body>");
		 out.println("<h1>Servlet NewServlet at " + request.getParameter("id") + "</h1>");
		 out.println("<p>" + edu.temple.cis3238.parser.Parser.parseAndAnnotate(wikiText) + "</p>");
		 out.println("<h1>WIKI</h1><p/>" + edu.temple.cis3238.parser.Parser.parseAndAnnotate(
				 wikiText));

		 out.println("<form action='/WikiWikiWeb/Logout' method='get'>"
				 + " <button>Logout</button>"
				 + "</form>");
		 out.println("</body>");
		 out.println("</html>");

	  }
   }

   // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
   /**
    * Handles the HTTP <code>GET</code> method.
    *
    * @param request  servlet request
    * @param response servlet response
    * @throws ServletException if a servlet-specific error occurs
    * @throws IOException      if an I/O error occurs
    */
   @Override
   protected void doGet(HttpServletRequest request, HttpServletResponse response)
		   throws ServletException, IOException {
	  processParser(request, response);
   }

   /**
    * Handles the HTTP <code>POST</code> method.
    *
    * @param request  servlet request
    * @param response servlet response
    * @throws ServletException if a servlet-specific error occurs
    * @throws IOException      if an I/O error occurs
    */
   @Override
   protected void doPost(HttpServletRequest request, HttpServletResponse response)
		   throws ServletException, IOException {
	  processParser(request, response);
   }

   /**
    * Returns a short description of the servlet.
    *
    * @return a String containing servlet description
    */
   @Override
   public String getServletInfo() {
	  return "Short description";
   }// </editor-fold>

}
