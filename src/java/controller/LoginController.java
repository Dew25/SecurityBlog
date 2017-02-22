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
import session.RegUserFacade;

import util.EncriptPass;

/**
 *
 * @author jvm
 */
@WebServlet(name = "LoginController", urlPatterns = {"/login"})
public class LoginController extends HttpServlet {

    @EJB RegUserFacade regUserFacade;
   
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
            String username=request.getParameter("username");
            String password=request.getParameter("password");
            String path = request.getParameter("path");

            RegUser regUser = regUserFacade.findRegUserByName(username);
            if(regUser == null){
                   response.sendRedirect("/SecurityBlog/authForm/login.jsp");
            }else{
                String securePassword="";
                try {
                    EncriptPass encriptPass = new EncriptPass(password, regUser.getSalts());
                    securePassword = encriptPass.getEncriptPassword();
                } catch (NoSuchAlgorithmException ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                    response.sendRedirect("/SecurityBlog/errorPage.jsp");
                }
                if(!securePassword.equals(regUser.getPassword())){
                    response.sendRedirect("/SecurityBlog/authForm/loginError.jsp");
                }else{
                    HttpSession session = request.getSession(true);
                    session.setAttribute("regUser", regUser);
                    response.sendRedirect(path);
                }
            }
        
        }else if("/errorPage".equals(request.getServletPath())){
            response.sendRedirect("/SecurityBlog/errorPage.jsp");
        }else if("/errorLogin".equals(request.getServletPath())){
            response.sendRedirect("/SecurityBlog/authForm/loginError.jsp");
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

}
