/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs;

import entities.Administrator;
import java.util.List;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@Stateless
public class AdministratorBean {

    @PersistenceContext
    EntityManager em;
    
    public void createAdministrator (String username, String password, String name, String email){
        try {
            Administrator admin = new Administrator (username, password, name, email);
            em.persist(admin);
        } catch (Exception ex) {
            throw new EJBException(ex.getMessage());
        }
    }
    
    public List<Administrator> getAllAdministrators() {
        try {
            List<Administrator> administrators = (List<Administrator>) em.createNamedQuery("getAllAdministrators").getResultList();
            return administrators;
        } catch (Exception ex) {
            throw new EJBException(ex.getMessage());
        }
    }
 
     public void updateAdministrator (Long id, String username, String password, String name, String email){
        try {
            Administrator admUpdate = em.find(Administrator.class, id);
            if (admUpdate == null){
                return;
            }
            admUpdate.setUsername(username);
            admUpdate.setPassword(password);
            admUpdate.setName(name);
            admUpdate.setEmail(email);
            em.merge(admUpdate);   
        } catch (Exception ex) {
            throw new EJBException(ex.getMessage());
        }
     }
     
     public void removeAdministrator(Long id){
        try {
            Administrator admRemove = em.find(Administrator.class, id);
            if (admRemove == null){
                return;
            }
            em.remove(admRemove);
        } catch (Exception ex) {
            throw new EJBException(ex.getMessage());
        } 
     }
}