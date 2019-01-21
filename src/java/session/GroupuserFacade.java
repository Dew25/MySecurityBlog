/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.Groupuser;
import entity.Users;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Melnikov
 */
@Stateless
public class GroupuserFacade extends AbstractFacade<Groupuser> {

    @PersistenceContext(unitName = "MySecurityBlogPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public GroupuserFacade() {
        super(Groupuser.class);
    }

    public Groupuser findByUser(Users user) {
        try {
            return (Groupuser) em.createQuery("SELECT gr FROM Groupuser gr WHERE gr.usersLogin=:user")
                    .setParameter("user", user)
                    .getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
    
}
