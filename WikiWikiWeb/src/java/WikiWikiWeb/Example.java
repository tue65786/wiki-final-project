package WikiWikiWeb;

import edu.temple.cis3238.wiki.dao.GeneralDAO;
import edu.temple.cis3238.wiki.dao.IGeneralDAO;
import edu.temple.cis3238.wiki.sql.DbConnection;
import edu.temple.cis3238.wiki.vo.UsersVO;
import java.io.IOException;
import java.io.PrintWriter;
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
            out.println("<h1>You typed: " + request.getParameter("editor") + "</h1>"
                    + "<form action='/WikiWikiWeb/Example' method=\"get\">"
                    + "<button>Go Back</button>"
                    + "</form><hr>"
                    + "<form action='/WikiWikiWeb/Logout' method='get'>"
                    + "<button>Logout</button>"
                    + "</form>");
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
