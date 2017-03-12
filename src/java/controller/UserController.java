/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entyty.Article;
import entyty.Comment;
import entyty.User;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import session.ArticleFacade;
import session.AuthBean;
import session.CommentFacade;

/**
 *
 * @author jvm
 */
@WebServlet(name = "UserController", urlPatterns = {"/user", "/addNewUser","/newuser","/addComment","/deleteComment"})
public class UserController extends HttpServlet {
@EJB AuthBean authBean;
@EJB ArticleFacade articleFacade;
@EJB CommentFacade commentFacade;

    

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
        request.setCharacterEncoding("UTF-8");
        
        if("/addNewUser".equals(request.getServletPath())){
            String name =request.getParameter("name");
            String surname =request.getParameter("surname");
            String phone =request.getParameter("phone");
            String email =request.getParameter("email");
            String login =request.getParameter("login");
            String password=request.getParameter("password");
            try {
                authBean.addNewUser(login, password, name, surname, phone, email);
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
            response.sendRedirect("index.jsp");
            return;
        }
        HttpSession session = request.getSession(false);
        if(session != null){
            User regUser = (User) session.getAttribute("regUser");
            if(regUser != null){
                String username =regUser.getName()+" "+regUser.getSurname();
                request.setAttribute("username", username);
                if(authBean.accessOn(regUser,"USERS") || authBean.accessOn(regUser,"ADMINS")){
                    if("/addComment".equals(request.getServletPath())){
                        String commentTitle=(String) request.getParameter("comment_title");
                        String commentText=(String) request.getParameter("comment_text");
                        String article_id = (String) request.getParameter("article_id");
                        Article article = articleFacade.find(new Long(article_id));
                        Comment newComment = new Comment(regUser.getLogin(), commentTitle, commentText, new Date());
                        article.getComments().add(newComment);
                        articleFacade.edit(article);
                    }
                    String articleId = (String) request.getParameter("article_id");
                    if(articleId != null){
                        request.setAttribute("article", articleFacade.find(new Long(articleId)));
                        if(authBean.accessOn(regUser,"ADMINS")){
                           if("/deleteComment".equals(request.getServletPath())){
                               String commentId=request.getParameter("comment_id");
                               Article article = articleFacade.find(new Long(articleId));
                               Comment delComment = commentFacade.find(new Long(commentId));
                               article.getComments().remove(delComment);
                               articleFacade.edit(article);
                               request.setAttribute("article",articleFacade.find(new Long(articleId)));
                           }
                           request.setAttribute("userGroup", "ADMINS"); 
                        }
                    }
                    
                    request.getRequestDispatcher("/WEB-INF/user/user.jsp").forward(request, response);
                    return;
                }else{
                    String queryString = "?"+request.getQueryString();
                    request.setAttribute("path", "user"+queryString);
                    request.setAttribute("info", "У Вас, нет права зайти на этот ресурс");
                    request.getServletContext().getRequestDispatcher("/authForm/login.jsp").forward(request, response);
                }
                
                
            }else{
                String queryString = "?"+request.getQueryString();
                request.setAttribute("path", "user"+queryString);
                request.setAttribute("info", "У Вас, нет права зайти на этот ресурс");
                request.getServletContext().getRequestDispatcher("/authForm/login.jsp").forward(request, response);
            }
        }else{
            //regUser == null)
            String queryString = "?"+request.getQueryString();
            request.setAttribute("path", "user"+queryString);
            request.getServletContext().getRequestDispatcher("/authForm/login.jsp").forward(request, response);
            
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
        
        
        if("/newuser".equals(request.getServletPath())){
            request.getServletContext().getRequestDispatcher("/authForm/registration.jsp").forward(request, response);
        }else{
            processRequest(request, response);
        }
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
     * @return a User containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
