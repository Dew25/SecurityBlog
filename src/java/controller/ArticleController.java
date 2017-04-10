/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entyty.Article;
import entyty.User;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import session.ArticleFacade;
import security.AuthBean;


/**
 *
 * @author jvm
 */
@WebServlet(name = "ArticleController", urlPatterns = {"/newarticle","/addarticle","/deletearticle","/uploadPage"})
public class ArticleController extends HttpServlet {
@EJB AuthBean authBean;
@EJB ArticleFacade articleFacade;
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

        String userPath=request.getServletPath();
        HttpSession session = request.getSession(false);
        if(session != null){
            User regUser = (User) session.getAttribute("regUser");
            String username =regUser.getName()+" "+regUser.getSurname();
            request.setAttribute("username", username);
            if(authBean.accessOn(regUser,"ADMINS")){
               if("/addarticle".equals(userPath)){
                    String title = request.getParameter("title");
                    String article = request.getParameter("article");
                    Date date=new Date();
                    Article newArticle = new Article(title, article, regUser.getLogin(), date);
                    try {
                       articleFacade.create(newArticle);
                       request.setAttribute("info", "Статья успешно добавлена.");
                   } catch (Exception e) {
                       request.setAttribute("info", "Статья не добавлена. Вероятно повтор заголовка статьи");
                   }
                    
                }else if("/deletearticle".equals(userPath)){
                    String id = request.getParameter("id");
                    Article delArticle = articleFacade.find(new Long(id));
                    articleFacade.remove(delArticle);
                }else if ("/uploadPage".equals(userPath)){
                    request.getRequestDispatcher("/WEB-INF/admin/uploadFile.jsp").forward(request, response);
                    return;
                }
                List<Article> articles = articleFacade.findAll();
                request.setAttribute("articles", articles);
                request.getServletContext().getRequestDispatcher("/WEB-INF/admin/newArticle.jsp").forward(request, response);
                return;
            }
            request.setAttribute("path", "newarticle");
            request.setAttribute("info", "У Вас, "+regUser.getLogin()+", нет права зайти на этот ресурс");
            request.getServletContext().getRequestDispatcher("/authForm/login.jsp").forward(request, response);
        }else{
            //session == null)
            request.setAttribute("path", "newarticle");
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
     * @return a User containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
