package WikiWikiWeb;

import edu.temple.cis3238.security.*;
import edu.temple.cis3238.wiki.dao.GeneralDAO;
import edu.temple.cis3238.wiki.dao.IGeneralDAO;
import edu.temple.cis3238.wiki.sql.DbConnection;
import edu.temple.cis3238.wiki.vo.UsersVO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.regex.Pattern;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/Signup"})
public class Signup extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            String userName = request.getParameter("user");
            String password = request.getParameter("pass");
            boolean newUser = true;
            boolean validPassword = true;
            boolean validUsername = true;

            DbConnection dbc = new DbConnection();
            IGeneralDAO g = new GeneralDAO(dbc);

            ArrayList<UsersVO> users = g.getUsers();
            Iterator<UsersVO> iter = users.iterator();

            while (iter.hasNext()) {
                if (iter.next().getUserName().equals(userName)) {
                    newUser = false;
                    request.getRequestDispatcher("/signup.jsp?errorMessageUserName=true").forward(request, response);
                }
            }

            if (!Password.isValidUsername(userName)) {
                validUsername = false;
                request.getRequestDispatcher("/signup.jsp?invalidUsername=yes").forward(request, response);
            }

            if (!Password.isValidPassword(password)) {
                validUsername = false;
                request.getRequestDispatcher("/signup.jsp?invalidPassword=yes").forward(request, response);
            }

            if (newUser && validPassword && validUsername) {
                g.addUser(new UsersVO(userName, password));
            }

            dbc.close();

            request.getRequestDispatcher("/index.jsp?newUser=true").forward(request, response);

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
