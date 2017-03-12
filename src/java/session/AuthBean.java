/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entyty.Group;
import entyty.User;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import util.EncriptPass;

/**
 *
 * @author jvm
 */
@Stateless
public class AuthBean {

    @EJB UserFacade userFacade;
    private User regUser;

    public AuthBean() {

    }

    
    public  User getSessionUser(HttpServletRequest request) {

        HttpSession session = request.getSession(false);
        if (session != null) {
            return (User) session.getAttribute("regUser");
        }else{
            return null;
        }
        
    }
    /**
     * 
     * @param login
     * @param password
     * @return User or null
     */

    public User getAuthorizationRegUser(String login, String password) {
        try {
            User loginUser = userFacade.findRegUserByName(login);
            EncriptPass encriptPass = new EncriptPass(password, loginUser.getSalts());
             
            if(encriptPass.getEncriptPassword().equals(loginUser.getPassword())){
                
                return loginUser;
            }else{
                return null;
            }
            
        } catch (Exception e) {
            return null;
        }
        
    }
    
    
    public User addNewUser(String login, String password, String name, String surname, String phone, String email) throws NoSuchAlgorithmException {
       
        EncriptPass encriptPass = new EncriptPass();
        String salts = encriptPass.getSalts();
        encriptPass.setEncriptPassword(password,salts);
        password = encriptPass.getEncriptPassword();
        Group groupGuests=null;
        List<User> regUsers = userFacade.findAll();
        if(regUsers.isEmpty()){
            groupGuests = new Group("ADMINS");
        }else{
            groupGuests = new Group("USERS");
        }
        
        List<Group> groups = new ArrayList<>();
       // groups.add(groupGuests);
        User newUser = new User(login,password,salts,groups,name,surname,phone,email);
        try {
            if(userFacade.findRegUserByName(login)==null){
                userFacade.create(newUser);
                newUser=userFacade.findRegUserByName(newUser.getLogin());
                newUser.getGroups().add(groupGuests);
                userFacade.edit(newUser);
            }
            return userFacade.findRegUserByName(login);
        } catch (Exception e) {
            System.out.println("Not create newUser: "+newUser.toString());
            return null;
        }
        
    }
    public Boolean accessOn(User regUser, String groupName){
        for (int i = 0; i < regUser.getGroups().size(); i++) {
            Group group = regUser.getGroups().get(i);
            if(groupName.equals(group.getGroupName())){
                return true;
            }
        }
        return false;
    }
    
}
