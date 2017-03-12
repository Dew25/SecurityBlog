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

/**
 *
 * @author jvm
 */
@Stateless
public class GroupFacade extends AbstractFacade<Group> {

    @EJB UserFacade regUserFacade;

    @PersistenceContext(unitName = "SecurityBlogPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public GroupFacade() {
        super(Group.class);
    }
    public List<User> getUsersInGroup(Long groupId){
        Group groupById = this.find(groupId);
        List<User> allUsers=regUserFacade.findAll();
        List<User> usersInGroup = new ArrayList<>();
        for (int i = 0; i < allUsers.size(); i++) {
            User user = allUsers.get(i);
            if(user.getGroups().contains(groupById)){
                usersInGroup.add(user);
            }
        }
        return usersInGroup;
    }
    
}
