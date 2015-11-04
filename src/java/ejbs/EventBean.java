/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs;

import entities.Category;
import entities.Event;
import java.util.List;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class EventBean {

    @PersistenceContext
    private EntityManager em;
    
    public void createEvent (String name, String startDate, String finishDate){
        try {
            Event e = new Event (name, startDate, finishDate);
            em.persist(e);   
        } catch (Exception ex) {
            throw new EJBException(ex.getMessage());
        }
    }
    
     public List<Event> getAllEvents() {
        try {
            List<Event> events = (List<Event>) em.createNamedQuery("getAllEvents").getResultList();
            return events;
        } catch (Exception ex) {
            throw new EJBException(ex.getMessage());
        }
    }
     
    public void updateEvent(Long id, String name, String startDate, String finishDate) {
        try {
            Event eUpdate = em.find(Event.class, id);
            if (eUpdate == null) {
                return;
            }
            eUpdate.setName(name);
            eUpdate.setStartDate(startDate);
            eUpdate.setFinishDate(finishDate);
        } catch (Exception ex) {
            throw new EJBException(ex.getMessage());
        }
    }

    public void removeEvent (Long id) {
        try {
            Event eRemove = em.find(Event.class, id);
            if (eRemove == null) {
                return;
            }
            em.remove(eRemove);
        } catch (Exception ex) {
            throw new EJBException(ex.getMessage());
        }
    }
    
     public void enrollEventtInCategory(Long idEvent, Long idCategory){
        try {
            Event e = em.find(Event.class, idEvent);
            Category c = em.find(Category.class, idCategory);
        
            c.addEvent(e);
            e.addCategory(c);
        
            em.merge(c);
            em.merge(e);
  
        } catch (Exception ex) {
            throw new EJBException(ex.getMessage());
        }
     }

    public List<Category> getAllCategoriesOfEvent(Event currentEvent) {
        try {
            List<Category> events = currentEvent.getCategories();
            return events; 
        } catch (Exception ex) {
            throw new EJBException(ex.getMessage());
        }
    }
    
}
