/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entyty.RegUser;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author jvm
 */
@Stateless
public class RegUserFacade extends AbstractFacade<RegUser> {

    @PersistenceContext(unitName = "SecurityBlogPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RegUserFacade() {
        super(RegUser.class);
    }
    public RegUser findRegUserByName(String username){
        Query query = getEntityManager().createQuery("SELECT ru FROM RegUser ru WHERE ru.username=:username")
                .setParameter("username", username);
        try {
           return (RegUser) query.getSingleResult();
        } catch (Exception e) {
           return null;
        }
    }
    
}
