package WikiWikiWeb;

import edu.temple.cis3238.parser.WikiMarkup;
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

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            /*
             * Prints the username; for testing
            */
            String userName = request.getParameter("username");
            out.println(userName + "<br />");

            /*
             * Gets the topic name and tag name(s) and prints them out
             */
            String topicContent = request.getParameter("editor");
            Scanner scanner = new Scanner(topicContent);
            String s = null;
            String topicName = null;
            ArrayList<String> tagNames = new ArrayList<>();
            int end = 0;

            while (scanner.hasNext()) {
                s = scanner.next();
                System.out.println(s);
                if (s.substring(0, 2).equals(WikiMarkup.FRONT_TOPIC.toString())) {
                    //we know we are in a topic expression
                    // should only have ONE topic name
                    end = s.indexOf(WikiMarkup.BACK_TOPIC.toString());
                    topicName = s.substring(2, end);

                } else if (s.substring(0, 2).equals(WikiMarkup.FRONT_TAG.toString())) {
                    //we know we are in a topic expression
                    end = s.indexOf(WikiMarkup.BACK_TAG.toString());
                    tagNames.add(s.substring(2, end));

                } //add more else if for more regexes
                else {
                }
            }
            out.println(topicName + "<br />" + tagNames.toString());
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
