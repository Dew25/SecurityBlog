/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entyty.RegUser;
import java.security.NoSuchAlgorithmException;
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
    
    
    public RegUser setRegistration(String login, String password, String name, String surname, String phone, String email) throws NoSuchAlgorithmException {
       
        EncriptPass encriptPass = new EncriptPass();
        String salts = encriptPass.getSalts();
        encriptPass.setEncriptPassword(password,salts);
        password = encriptPass.getEncriptPassword();
        RegUser newUser = new RegUser(login,password,salts,"GUEST",name,surname,phone,email);
        try {
            regUserFacade.create(newUser);
            return regUserFacade.findRegUserByName(login);
        } catch (Exception e) {
            System.out.println("Not create newUser: "+newUser.toString());
            return null;
        }
        
    }
    
}