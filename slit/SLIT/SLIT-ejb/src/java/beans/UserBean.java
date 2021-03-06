/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package beans;

import Entities.User;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import javax.ejb.Stateless;

/**
 *
 * @author olama
 */
@Stateless
public class UserBean implements UserBeanRemote {

    @PersistenceContext
    EntityManager em;
    
    /**
     *
     * @param username
     * @param password
     * @return
     */
    @Override
    public void addUser(String username, String password){
        
        User u = new User();
        u.setUN(username);
        u.setPW(password);
        
        em.persist(u);
        
    }
    
    public ArrayList<String>listUsers(){
        TypedQuery<User> q =
                em.createQuery("select u from User as u", User.class);
        List<User> users = q.getResultList();
        ArrayList<String> result = new ArrayList<String>();
        
        for(User user : users){
            result.add(user.getUN());
        }
        return result;
    }
    
    
}
