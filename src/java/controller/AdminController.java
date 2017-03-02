/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entyty.Group;
import entyty.RegUser;
import java.io.IOException;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import session.AuthBean;
import session.GroupFacade;
import session.RegUserFacade;

/**
 *
 * @author jvm
 */
@WebServlet(name = "AdminController", urlPatterns = {"/admin","/newGroup","/addToGroup","/listGroups"})
public class AdminController extends HttpServlet {
    @EJB AuthBean authBean;
    @EJB RegUserFacade regUserFacade;
    @EJB GroupFacade groupFacade;
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
        String userPath=request.getServletPath();
        RegUser regUser = authBean.getSessionUser(request);          
            if( regUser != null){
                if(authBean.accessOn(regUser,"ADMINS")){
                    String username =regUser.getName()+" "+regUser.getSurname();
                    request.setAttribute("username", username);
                    if("/newGroup".equals(userPath)){
                        String newGroup=request.getParameter("new_group");
                        try {
                            groupFacade.create(new Group(newGroup));
                            request.setAttribute("groups", groupFacade.findAll());
                            request.setAttribute("info", "Группа \""+newGroup+"\" добавлена!");
                        } catch (Exception e) {
                            System.out.println("Group not add");
                            request.setAttribute("info", "Группу добавить неудалось!");
                            request.getServletContext().getRequestDispatcher("/WEB-INF/admin/admin.jsp").forward(request, response);
                        }
                    }else if("/addToGroup".equals(userPath)){
                        String selectUserId = request.getParameter("select_user");
                        String groupId = request.getParameter("group");
                        RegUser selectUser = regUserFacade.find(new Long(selectUserId));
                        Group group = groupFacade.find(new Long(groupId));
                        if(request.getParameter("add") != null){
                            try {
                                selectUser.getGroups().add(group);
                                regUserFacade.edit(selectUser);
                            } catch (Exception e) {
                                request.setAttribute("info", "Неудалось добавить пользователя в группу!");
                                request.getServletContext().getRequestDispatcher("/WEB-INF/admin/admin.jsp").forward(request, response);

                            }
                        }else if(request.getParameter("remove") != null){
                            try {
                                selectUser.getGroups().remove(group);
                                regUserFacade.edit(selectUser);
                            } catch (Exception e) {
                                request.setAttribute("info", "Неудалось удалить пользователя из группы!");
                                request.getServletContext().getRequestDispatcher("/WEB-INF/admin/admin.jsp").forward(request, response);
                            }
                        }
                    }else if("/listGroups".equals(userPath)){
                        String groupId = request.getParameter("selectedGroup");
                        try {
                            List<RegUser> usersInGroup=groupFacade.getUsersInGroup(new Long(groupId));
                            request.setAttribute("usersInGroup", usersInGroup);
                        } catch (Exception e) {
                            request.setAttribute("users",regUserFacade.findAll());
                            request.setAttribute("groups", groupFacade.findAll());
                            request.setAttribute("info", "Неудалось показать пользователей группы!");
                            request.getServletContext().getRequestDispatcher("/WEB-INF/admin/admin.jsp").forward(request, response);
                        }
                    }
                    request.setAttribute("users",regUserFacade.findAll());
                    request.setAttribute("groups", groupFacade.findAll());
                    request.getServletContext().getRequestDispatcher("/WEB-INF/admin/admin.jsp").forward(request, response);

                }else{
                    //regUser != "ADMINS"
                    request.setAttribute("path", "admin");
                    request.setAttribute("info", "У Вас, "+regUser.getLogin()+", нет права зайти на этот ресурс");
                    request.getServletContext().getRequestDispatcher("/authForm/login.jsp").forward(request, response);
                }
            }else{
                //regUser == null)
                request.setAttribute("path", "admin");
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
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
