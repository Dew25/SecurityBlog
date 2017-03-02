/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entyty.Group;
import entyty.RegUser;
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

    @EJB RegUserFacade regUserFacade;
    private RegUser regUser;

    public AuthBean() {

    }

    
    public  RegUser getSessionUser(HttpServletRequest request) {

        HttpSession session = request.getSession(false);
        if (session != null) {
            return (RegUser) session.getAttribute("regUser");
        }else{
            return null;
        }
        
    }
    /**
     * 
     * @param login
     * @param password
     * @return RegUser or null
     */

    public RegUser getAuthorizationRegUser(String login, String password) {
        try {
            RegUser loginUser = regUserFacade.findRegUserByName(login);
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
    
    
    public RegUser addNewUser(String login, String password, String name, String surname, String phone, String email) throws NoSuchAlgorithmException {
       
        EncriptPass encriptPass = new EncriptPass();
        String salts = encriptPass.getSalts();
        encriptPass.setEncriptPassword(password,salts);
        password = encriptPass.getEncriptPassword();
        Group groupGuests = new Group("GUESTS");
        List<Group> groups = new ArrayList<>();
       // groups.add(groupGuests);
        RegUser newUser = new RegUser(login,password,salts,groups,name,surname,phone,email);
        try {
            if(regUserFacade.findRegUserByName(login)==null){
                regUserFacade.create(newUser);
                
                newUser=regUserFacade.findRegUserByName(newUser.getLogin());
                newUser.getGroups().add(groupGuests);
                regUserFacade.edit(newUser);
            }
            return regUserFacade.findRegUserByName(login);
        } catch (Exception e) {
            System.out.println("Not create newUser: "+newUser.toString());
            return null;
        }
        
    }
    public Boolean accessOn(RegUser regUser, String groupName){
        for (int i = 0; i < regUser.getGroups().size(); i++) {
            Group group = regUser.getGroups().get(i);
            if(groupName.equals(group.getGroupName())){
                return true;
            }
        }
        return false;
    }
    
}
