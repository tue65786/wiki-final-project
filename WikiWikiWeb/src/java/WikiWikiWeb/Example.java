package WikiWikiWeb;

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
            request.getRequestDispatcher("/editor.jsp").forward(request, response);
        }
    }
    
    protected void processEditor(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<h1>You typed:" + request.getParameter("test") + "</h1>"
                    + "<form action='/WikiWikiWeb/Example' method=\"get\">"
                    + "<button>Go Back</button>"
                    + "</form><hr>"
                    + "<form action='/WikiWikiWeb/Logout' method='get'>"
                    + "<button>Logout</button>"
                    + "</form>");
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
        processEditor(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
