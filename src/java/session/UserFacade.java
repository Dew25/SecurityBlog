/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entyty.Group;
import entyty.User;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author jvm
 */
@Stateless
public class UserFacade extends AbstractFacade<User> {
@EJB GroupFacade groupFacade;
    @PersistenceContext(unitName = "SecurityBlogPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UserFacade() {
        super(User.class);
    }
    public User findRegUserByName(String login){
        Query query = getEntityManager().createQuery("SELECT u FROM User u WHERE u.login=:login")
                .setParameter("login", login);
        try {
           return (User) query.getSingleResult();
        } catch (Exception e) {
           return null;
        }
    }
    public List<User> findUsersInGroupByGroupId(Long groupId){
        Group group = groupFacade.find(groupId);
        List<User> usersInGroup = new ArrayList<>();
        List<User> users = this.findAll();
        for (int i = 0; i < users.size(); i++) {
            User user = users.get(i);
            if(user.getGroups().contains(group)){
                usersInGroup.add(user);
            }
            
        }
        return usersInGroup;
    }
    
    
}
