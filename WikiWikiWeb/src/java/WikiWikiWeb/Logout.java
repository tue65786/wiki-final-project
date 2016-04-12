package WikiWikiWeb;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "Logout", urlPatterns = {"/Logout"})
public class Logout extends HttpServlet {

    protected void processLogOut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
            HttpSession session  = request.getSession(false);
            try
            {      
                session.removeAttribute("logonSessData");
                session.invalidate();                               
                response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
                response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
                response.setHeader("Expires", "0"); // Proxies.
                response.sendRedirect("/WikiWikiWeb/index.jsp?logout=true");           
            }
            catch (Exception sqle)
            {
                System.out.println("error UserValidateServlet message : " + sqle.getMessage());
                System.out.println("error UserValidateServlet exception : " + sqle);
            }
        }
//HttpSession session = request.getSession(false);
//        if (session != null) {
//            session.invalidate();
//        }
//        
//        
//        getServletContext().getRequestDispatcher("/index.jsp").forward(request  , response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processLogOut(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processLogOut(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
