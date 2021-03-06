
package ejbs;

import dtos.AttendantDTO;
import entities.Category;
import entities.Event;
import entities.Attendant;
import exceptions.AttendantEnrolledException;
import exceptions.AttendantNotEnrolledException;
import exceptions.EntityAlreadyExistsException;
import exceptions.EntityDoesNotExistsException;
import exceptions.MyConstraintViolationException;
import exceptions.Utils;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintViolationException;

@Stateless
public class AttendantBean {

    @PersistenceContext
    private EntityManager em;
    
    public void createAttendant (String username, String password, String name, String email) throws EntityAlreadyExistsException, EntityDoesNotExistsException, MyConstraintViolationException {
        //try {
            //if (em.find(Attendant.class, username) != null) {
            //    throw new EntityAlreadyExistsException("A student with that username already exists.");
            //}
            Attendant attendant = new Attendant (username, password, name, email);
            em.persist(attendant);
       
    }
    
    public List<AttendantDTO> getAllAttendants() {
        try {
            List<Attendant> attendants = (List<Attendant>) em.createNamedQuery("getAllAttendants").getResultList();
            return attendantsToDTOs(attendants);
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }
    
    public Attendant getAttendant(String username) {
        try {
            Attendant attendant = em.find(Attendant.class, username);
            return attendant;
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }

    public void updateAttendant (Long id, String username, String password, String name, String email)throws EntityDoesNotExistsException, MyConstraintViolationException{
        try {
            Attendant attendant = em.find(Attendant.class, id);
            if (attendant == null){
                throw new EntityDoesNotExistsException("There is no attendant with that id.");
            }
            if (em.find(Attendant.class, username) != null) {
                throw new EntityAlreadyExistsException("That username already exists.");
            }
            attendant.setUsername(username);
            attendant.setPassword(password);
            attendant.setName(name);
            attendant.setEmail(email);
            em.merge(attendant);   
        } catch (EntityDoesNotExistsException e) {
            throw e;
        } catch (ConstraintViolationException e) {
            throw new MyConstraintViolationException(Utils.getConstraintViolationMessages(e));            
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }
    
    public void removeAttendant(Long id) throws EntityDoesNotExistsException {
        try {
            Attendant attendant = em.find(Attendant.class, id);
            if (attendant == null) {
                throw new EntityDoesNotExistsException("There is no attendant with that id.");
            }

            for (Event event : attendant.getEvents()) {
                event.removeAttendant(attendant);
            }
            
            for (Category category : attendant.getCategories()){
                category.removeAttendant(attendant);
            }
            
            em.remove(attendant);
        
        } catch (EntityDoesNotExistsException e) {
            throw e;
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }
    
    public void enrollAttendantIntoEvent(String username, String eventName) throws EntityDoesNotExistsException, AttendantEnrolledException{
        try {
            Attendant attendant = em.find(Attendant.class, username);
            if (attendant == null) {
                throw new EntityDoesNotExistsException("There is no attendant with that username.");
            }

            Event event = em.find(Event.class, eventName);
            if (event == null) {
                throw new EntityDoesNotExistsException("There is no event with that name.");
            }

            if (event.getAttendants().contains(attendant)) {
                throw new AttendantEnrolledException("Attendant is already enrolled in that event.");
            }

            event.addAttendant(attendant); 
            attendant.addEvent(event);

        } catch (EntityDoesNotExistsException | AttendantEnrolledException e) {
            throw e;
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }
    
    public void unrollStudentFromEvent(String username, String eventName) throws EntityDoesNotExistsException, AttendantNotEnrolledException {
        try {
            Event event = em.find(Event.class, eventName);
            if(event == null){
                throw new EntityDoesNotExistsException("There is no event with that name.");
            }            
            
            Attendant attendant = em.find(Attendant.class, username);
            if(attendant == null){
                throw new AttendantNotEnrolledException("There is no attendant with that username.");
            }
            
            if(!event.getAttendants().contains(attendant)){
                throw new AttendantNotEnrolledException();
            }
          
            event.removeAttendant(attendant);
            attendant.removeEvent(event);

        } catch (EntityDoesNotExistsException | AttendantNotEnrolledException e) {
            throw e;             
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }
    
    public List<AttendantDTO> getEnrolledAttendantsInEvents(String eventName) throws EntityDoesNotExistsException{
        try {
            Event event = em.find(Event.class, eventName);
            if( event == null){
                throw new EntityDoesNotExistsException("There is no event with that name.");
            }            
            List<Attendant> attendants = (List<Attendant>) event.getAttendants();
            return attendantsToDTOs(attendants);
        } catch (EntityDoesNotExistsException e) {
            throw e;             
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }

    public List<AttendantDTO> getUnrolledAttendantsFromEvents(String eventName) throws EntityDoesNotExistsException{
        try {
            Event event = em.find(Event.class, eventName);
            if( event == null){
                throw new EntityDoesNotExistsException("There is no event with that name.");
            }            
            //nao sei se este código está correcto??
            List<Attendant> attendants = (List<Attendant>) em.createNamedQuery("getAllEventAttendants")
                    .setParameter("eventCode", event.getId())
                    .getResultList();
            //-----------------------------------------------------------------------------------------
            List<Attendant> enrolled = em.find(Event.class, eventName).getAttendants();
            attendants.removeAll(enrolled);
            return attendantsToDTOs(attendants);
        } catch (EntityDoesNotExistsException e) {
            throw e;             
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }

    public void enrollAttendantIntoCategory(String username, String categorytName) throws EntityDoesNotExistsException, AttendantEnrolledException{
        try {
            Attendant attendant = em.find(Attendant.class, username);
            if (attendant == null) {
                throw new EntityDoesNotExistsException("There is no attendant with that username.");
            }

            Category category = em.find(Category.class, categorytName);
            if (category == null) {
                throw new EntityDoesNotExistsException("There is no categoty with that name.");
            }

            if (category.getAttendants().contains(attendant)) {
                throw new AttendantEnrolledException("Attendant is already enrolled in that category.");
            }

            category.addAttendant(attendant); 
            attendant.addCategory(category);

        } catch (EntityDoesNotExistsException | AttendantEnrolledException e) {
            throw e;
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }
    
    public void unrollStudentFromCategory(String username, String categoryName) throws EntityDoesNotExistsException, AttendantNotEnrolledException {
        try {
            Category category = em.find(Category.class, categoryName);
            if(category == null){
                throw new EntityDoesNotExistsException("There is no category with that name.");
            }            
            
            Attendant attendant = em.find(Attendant.class, username);
            if(attendant == null){
                throw new AttendantNotEnrolledException("There is no attendant with that username.");
            }
            
            if(!category.getAttendants().contains(attendant)){
                throw new AttendantNotEnrolledException();
            }
          
            category.removeAttendant(attendant);
            attendant.removeCategory(category);

        } catch (EntityDoesNotExistsException | AttendantNotEnrolledException e) {
            throw e;             
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }
    
    public List<AttendantDTO> getEnrolledAttendantsInCategories(String categoryName) throws EntityDoesNotExistsException{
        try {
            Category category = em.find(Category.class, categoryName);
            if( category == null){
                throw new EntityDoesNotExistsException("There is no category with that name.");
            }            
            List<Attendant> attendants = (List<Attendant>) category.getAttendants();
            return attendantsToDTOs(attendants);
        } catch (EntityDoesNotExistsException e) {
            throw e;             
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }

    public List<AttendantDTO> getUnrolledAttendantsFromCategories(String categoryName) throws EntityDoesNotExistsException{
        try {
            Category category = em.find(Category.class, categoryName);
            if( category == null){
                throw new EntityDoesNotExistsException("There is no category with that name.");
            }            
            //nao sei se este código está correcto??
            List<Attendant> attendants = (List<Attendant>) em.createNamedQuery("getAllCategoryAttendants")
                    .setParameter("categoryCode", category.getId())
                    .getResultList();
            //-----------------------------------------------------------------------------------------
            List<Attendant> enrolled = em.find(Category.class, categoryName).getAttendants();
            attendants.removeAll(enrolled);
            return attendantsToDTOs(attendants);
        } catch (EntityDoesNotExistsException e) {
            throw e;             
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }
   
    AttendantDTO attendantToDTO(Attendant attendant) {
        return new AttendantDTO(
                attendant.getId(),
                attendant.getUserName(),
                null,
                attendant.getName(),
                attendant.getEmail());
    }

    List<AttendantDTO> attendantsToDTOs(List<Attendant> attendants) {
        List<AttendantDTO> dtos = new LinkedList<>();
        for (Attendant a : attendants) {
            dtos.add(attendantToDTO(a));
        }
        return dtos;
    }
   
}
