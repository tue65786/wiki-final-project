/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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
            //my code
            
            String userName = request.getParameter("user");
            String password = request.getParameter("password");
            
            IGeneralDAO g = new GeneralDAO(new DbConnection());
            
            //check if this username is already taken
//            ArrayList<UsersVO> users = g.getUsers();
//            Iterator<UsersVO> iter = users.iterator();
//            while(iter.hasNext()){
//                if (iter.next().getUserName().equals(userName)){                    
//                    request.getRequestDispatcher("/index.jsp").forward(request,response);
//                }
//            }
            
            //checkPasswordCriteria (i.e. does it have upper case letter, number, special symbol)
        
        Pattern [] passwordRegexes = new Pattern[4];
        passwordRegexes[0] = Pattern.compile(".*[A-Z].*");
        passwordRegexes[1] = Pattern.compile(".*[a-z].*");
        passwordRegexes[2] = Pattern.compile(".*\\d.*");
        passwordRegexes[3] = Pattern.compile(".*[~!].*");

        for(int i = 0; i < passwordRegexes.length; i++){
                    if(!passwordRegexes[i].matcher(password).matches())
                        //bad password
                        request.getRequestDispatcher("/index.jsp").forward(request,response);

                        
                        }
           
            
           //if username not already taken && valid pasword
                    
                    
            g.addUser(new UsersVO(userName, password));
            
           request.getRequestDispatcher("/decison.jsp").forward(request, response);
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
