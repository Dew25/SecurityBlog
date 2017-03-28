/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entyty.Group;
import entyty.User;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import security.AuthBean;
import session.GroupFacade;
import session.UserFacade;

/**
 *
 * @author jvm
 */
@WebServlet(name = "AdminController", urlPatterns = {"/admin","/newGroup","/addToGroup",
                                                        "/listGroups","/deleteGroup"})
public class AdminController extends HttpServlet {
    @EJB AuthBean authBean;
    @EJB UserFacade userFacade;
    @EJB GroupFacade groupFacade;

    
     private final static Logger LOGGER = 
            Logger.getLogger(AdminController.class.getCanonicalName());
    
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
        User regUser = authBean.getSessionUser(request);          
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
                        User selectUser = userFacade.find(new Long(selectUserId));
                        Group group = groupFacade.find(new Long(groupId));
                        if(request.getParameter("add") != null){
                            try {
                                selectUser.getGroups().add(group);
                                userFacade.edit(selectUser);
                            } catch (Exception e) {
                                request.setAttribute("info", "Неудалось добавить пользователя в группу!");
                                request.getServletContext().getRequestDispatcher("/WEB-INF/admin/admin.jsp").forward(request, response);

                            }
                        }else if(request.getParameter("remove") != null){
                            try {
                                selectUser.getGroups().remove(group);
                                userFacade.edit(selectUser);
                            } catch (Exception e) {
                                request.setAttribute("info", "Неудалось удалить пользователя из группы!");
                                request.getServletContext().getRequestDispatcher("/WEB-INF/admin/admin.jsp").forward(request, response);
                            }
                        }
                    }else if("/listGroups".equals(userPath)){
                        String groupId = request.getParameter("selectedGroup");
                        try {
                            List<User> usersInGroup=groupFacade.getUsersInGroup(new Long(groupId));
                            request.setAttribute("usersInGroup", usersInGroup);
                        } catch (Exception e) {
                            request.setAttribute("users",userFacade.findAll());
                            request.setAttribute("groups", groupFacade.findAll());
                            request.setAttribute("info", "Неудалось показать пользователей группы!");
                            request.getServletContext().getRequestDispatcher("/WEB-INF/admin/admin.jsp").forward(request, response);
                        }
                    }else if("/deleteGroup".equals(userPath)){
                        Boolean notDeleteGUESTS = true;
                        String groupId = request.getParameter("deleteGroup");
                        Group groupGUESTS = groupFacade.getGroupGUESTS();
                        //Находим пользователей, которые в указанной группе
                        List<User> usersInGroup = userFacade.findUsersInGroupByGroupId(new Long(groupId));
                        for (int i = 0; i < usersInGroup.size(); i++) {//проходим по usersInGroup
                            User user = usersInGroup.get(i);
                            for (int j = 0; j < user.getGroups().size(); j++) {
                                //у каждого user проходим по списку групп
                                Group group = user.getGroups().get(j);
                                //если id найденной группы совпадает с полученным groupId из формы
                                // и имя найденной группы не "GUESTS",
                                // удаляем найденную группу у этого пользователя 
                                // и добавляем ему группу "GUESTS"
                                if(groupId.equals(group.getId().toString()) && !"GROUPS".equals(group.getGroupName())){
                                    if(user.getGroups().remove(group)){
                                        userFacade.edit(user);
                                        LOGGER.log(Level.INFO, "Пользователь {0} удален из группы  {1}", 
                                                 new Object[]{user,group});
                                    }else{
                                        LOGGER.log(Level.INFO, "Пользователя {0} неудалось удалить из группы  {1}", 
                                                 new Object[]{user,group});
                                    }
                                    if(user.getGroups().add(groupGUESTS)){
                                        userFacade.edit(user);
                                        LOGGER.log(Level.INFO, "Пользователю {0} добавлена группа  {1}", 
                                                 new Object[]{user,group});
                                    }else{
                                        LOGGER.log(Level.INFO, "Пользователя {0} не удалось добавить в группу  {1}", 
                                                 new Object[]{user,group});
                                    }
                                }else{
                                    //группы указанной нет или имя указанной "GUESTS"
                                    notDeleteGUESTS = false;
                                }
                            }
                        }
                        if(notDeleteGUESTS){
                            //Удаляем пустую группу, если она не "GUESTS"
                            groupFacade.removeGroupById(new Long(groupId));
                        }
                        
                        
                    }
                    request.setAttribute("users",userFacade.findAll());
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
     * @return a User containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
