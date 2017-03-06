/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entyty.RegUser;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import session.AuthBean;
import session.RegUserFacade;


/**
 *
 * @author jvm
 */
@WebServlet(name = "LoginController", urlPatterns = {"/login"})
public class LoginController extends HttpServlet {

    @EJB RegUserFacade regUserFacade;
    @EJB AuthBean authBean;
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
        String userPath=request.getServletPath();
        if("/login".equals(request.getServletPath())){
            String login=request.getParameter("login");
            String password=request.getParameter("password");
            String path = request.getParameter("path");
            RegUser regUser = authBean.getAuthorizationRegUser(login, password);
            if(regUser != null){
                HttpSession session=request.getSession(true);
                session.setAttribute("regUser", regUser);
                response.sendRedirect(path);
                return;
            }else{
                request.setAttribute("info", "Неправильный логин или пароль!<br><a href=\"newuser\">зарегистрироваться</a>");
                request.setAttribute("path", path);
                request.getServletContext().getRequestDispatcher("/authForm/login.jsp").forward(request, response);
            }
        }
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
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
    }
}
