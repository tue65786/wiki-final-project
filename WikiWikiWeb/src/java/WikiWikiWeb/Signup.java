package WikiWikiWeb;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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

/**
 *
 * @author CAP
 */
@WebServlet(urlPatterns = {"/Signup"})
public class Signup extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
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
            
            while(iter.hasNext()){
                if(iter.next().getUserName().equals(userName)){
                    newUser = false;
                    request.getRequestDispatcher("/signup.jsp?errorMessageUserName=true").forward(request, response);
                }
            }
            
            if(!Password.isValidUsername(userName)){
                validUsername = false;
                request.getRequestDispatcher("/signup.jsp?invalidUsername=yes").forward(request, response);
            }
            
            if(!Password.isValidPassword(password)){
                validUsername = false;
                request.getRequestDispatcher("/signup.jsp?invalidPassword=yes").forward(request, response);
            }

            if(newUser && validPassword && validUsername){
                g.addUser(new UsersVO(userName, password));
            }
            
            dbc.close();
            

            
           request.getRequestDispatcher("/index.jsp?newUser=true").forward(request, response);
            
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
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
