package WikiWikiWeb;

import edu.temple.cis3238.parser.WikiMarkup;
import edu.temple.cis3238.wiki.dao.GeneralDAO;
import edu.temple.cis3238.wiki.dao.IGeneralDAO;
import edu.temple.cis3238.wiki.sql.DbConnection;
import edu.temple.cis3238.wiki.vo.TagsVO;
import edu.temple.cis3238.wiki.vo.TopicVO;
import edu.temple.cis3238.wiki.vo.UsersVO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "Example", urlPatterns = {"/Example"})
public class Example extends HttpServlet {

    protected void processLogIn(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            //my code
            String userName = request.getParameter("user");
            String password = request.getParameter("pass");
            boolean authenticated = true;
//            int hash = Crypto.getHash(userName, password);
//            
//            checkHash(hash);

            //
            UsersVO user = new UsersVO(userName, password);
            IGeneralDAO d = new GeneralDAO(new DbConnection());

//            if (d.findUserByUserNameAndPassword(user.getUserName(), user.getPassword()) == null)
//                request.getRequestDispatcher("/index.jsp");
            /*
             now we check if the user name and password works out
             */
            if (authenticated) {
                request.getRequestDispatcher("/decison.jsp").forward(request, response);
            } else {
                request.getRequestDispatcher("/index.jsp").forward(request, response);
            }

        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processLogIn(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
            /*
             * Gets the topic name and tag name(s)
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
            out.println(topicName + "\n" + tagNames.toString());
            
            /*
             * Adds topic and tags to database
            */
//            DbConnection dbc = new DbConnection();
//            GeneralDAO d = new GeneralDAO(dbc);
//            ArrayList<TagsVO> tagVOs = new ArrayList<>();
//            TagsVO tag = null;
//
//            TopicVO topic = new TopicVO(topicName, topicContent);
//            for (String tagName : tagNames) {
//                tag = new TagsVO(d.getTags().size(), tagName, d.getTopicByName(topic.getTopicName()).getTopicID());
//                tagVOs.add(tag);
//                d.addTag(tag);
//            }
//            d.assignTopicTags(topic, tagVOs);
//
//            dbc.close();
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
