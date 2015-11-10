/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;
import dtos.AttendantDTO;
import ejbs.AttendantBean;
import ejbs.CategoryBean;
import ejbs.EventBean;
import entities.Administrator;
import entities.Attendant;
import entities.Category;
import entities.Event;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIParameter;
import javax.faces.event.ActionEvent;

@ManagedBean
@SessionScoped
public class AttendantManager {
    
    @EJB
    private AttendantBean attendantBean;
    @EJB
    private EventBean eventBean;
    @EJB
    private CategoryBean categoryBean;

    private AttendantDTO newAttendant;
    private AttendantDTO currentAttendant;

    /*
    public String createAttendant(){
        try {
            attendantBean.createAttendant(atUserName, atPassword, atName, atEmail);
            clearNewAttendant();
            //escolher acção
            //return (String) "index?faces-redirect=true";
            return (String) "Vai para criação de Attendant";
        } catch (Exception ex) {
            throw new EJBException(ex.getMessage());
        }  
    }
    
    public List<Attendant> getAllAttendants(){
        try {
            this.attendantsM = attendantBean.getAllAttendants();
            return attendantsM; 
        } catch (Exception ex) {
            throw new EJBException(ex.getMessage());       
        }
    }
    
    public String updateAttendat(){
        try {
            attendantBean.updateAttendant(atId, atName, atEmail, atUserName, atPassword);
            //escolher acção
            //return (String) "index?faces-redirect=true";
            return (String) "Faz update a Attendant";
        } catch (NumberFormatException ex) {
            throw new EJBException(ex.getMessage()); 
        }
    }

    public void removeAttendant(ActionEvent event){
        try {
            UIParameter param = (UIParameter) event.getComponent().findComponent("deleteAttendantId");
            Long id = (Long) param.getValue();
            attendantBean.removeAttendant(id);
        } catch (NumberFormatException ex) {
            throw new EJBException(ex.getMessage()); 
        }
    }
   
    private void clearNewAttendant() {
        atName = null;
        atEmail = null;
        atUserName = null;
        atPassword = null;
    }
    
    public String createEvent(){
        try {
            eventBean.createEvent(evName,evDescription, evStartDate, evFinishDate);
            clearNewEvent();
            //escolher acção
            //return (String) "index?faces-redirect=true";
            return (String) "Vai para criação de Event";
        } catch (Exception ex) {
            throw new EJBException(ex.getMessage());
        }  
    }
    
    public List<Event> getAllEvents(){
        try {
            this.eventsM = eventBean.getAllEvents();
            return eventsM; 
        } catch (Exception ex) {
            throw new EJBException(ex.getMessage());       
        }
    }
    
    public String updateEvent(){
        try {
            eventBean.updateEvent(evId, evName, evStartDate, evFinishDate);
            //escolher acção
            //return (String) "index?faces-redirect=true";
            return (String) "Faz update a Event";
        } catch (NumberFormatException ex) {
            throw new EJBException(ex.getMessage()); 
        }
    }

    public void removeEvent(ActionEvent event){
        try {
            UIParameter param = (UIParameter) event.getComponent().findComponent("deleteEventId");
            Long id = (Long) param.getValue();
            eventBean.removeEvent(id);
        } catch (NumberFormatException ex) {
            throw new EJBException(ex.getMessage()); 
        }
    }
   
    private void clearNewEvent() {
        evName = null;
        evStartDate = null;
        evFinishDate = null;
    }
    
    public String createCategory(){
        try {
            categoryBean.createCategory(catName);
            clearNewCategory();
            //escolher acção
            //return (String) "index?faces-redirect=true";
            return (String) "Vai para criação de Category";
        } catch (Exception ex) {
            throw new EJBException(ex.getMessage());
        }  
    }
    
    public List<Category> getAllCategories(){
        try {
            this.categoriesM = categoryBean.getAllCategories();
            return categoriesM; 
        } catch (Exception ex) {
            throw new EJBException(ex.getMessage());       
        }
    }
    
    public String updateCategory(){
        try {
            categoryBean.updateCategory(catId, catName);
            //escolher acção
            //return (String) "index?faces-redirect=true";
            return (String) "Faz update a Category";
        } catch (NumberFormatException ex) {
            throw new EJBException(ex.getMessage()); 
        }
    }

    public void removeCategory(ActionEvent event){
        try {
            UIParameter param = (UIParameter) event.getComponent().findComponent("deleteCategoryId");
            Long id = (Long) param.getValue();
            categoryBean.removeCategory(id);
        } catch (NumberFormatException ex) {
            throw new EJBException(ex.getMessage()); 
        }
    }
   
    private void clearNewCategory() {
        catName = null;
    }
    /*
    public List<Event> getAllEventsOfCurrentManager(Manager currentManager){
        try {
            this.eventsM = managerBean.getAllEventsOfManager(currentManager);
            return eventsM; 
        } catch (Exception ex) {
            throw new EJBException(ex.getMessage());       
        }
    }
    
    public List<Event> getAllEventsOfCurrentAttendant(Attendant currentAttendant) {
        try {
            this.eventsM = attendantBean.getAllEventsOfAttendant(currentAttendant);
            return eventsM;  
        } catch (Exception ex) {
            throw new EJBException(ex.getMessage());
        }
    }
    
    public List<Category> getAllCategoriesOfCurrentAttendant(Attendant currentAttendant) {
        try {
            this.categoriesM = attendantBean.getAllCategoriesOfAttendant(currentAttendant);
            return categoriesM;   
        } catch (Exception ex) {
            throw new EJBException(ex.getMessage());
        }
    }
    
    public List<Category> getAllCategoriesOfCurrentEvent(Event currentEvent) {
        try {
            this.categoriesM = eventBean.getAllCategoriesOfEvent(currentEvent);
            return categoriesM;  
        } catch (Exception ex) {
            throw new EJBException(ex.getMessage());
        }
    }
    
    
    public List<ManagerDTO> getAllManagers(){
        try {
            return managerBean.getAllManagers();
        } catch (Exception ex) {
            throw new EJBException(ex.getMessage());       
        }
    }
    
    
    public List<Event> getAllEventsOfCurrentManager(){
        try {
            return managerBean.getAllEventsOfManager(currentManager.getId());
        } catch (Exception ex) {
            throw new EJBException(ex.getMessage());       
        }
    }
    
    
}








public class AdministratorManager {

    @EJB
    private StudentBean studentBean;
    @EJB
    private CourseBean courseBean;
    @EJB
    private SubjectBean subjectBean;
    private static final Logger logger = Logger.getLogger("web.AdministratorManager");
    private StudentDTO newStudent;
    private StudentDTO currentStudent;
    private CourseDTO newCourse;
    private CourseDTO currentCourse;
    private SubjectDTO newSubject;
    private SubjectDTO currentSubject;
    private UIComponent component;

    public AdministratorManager() {
        newStudent = new StudentDTO();
        newCourse = new CourseDTO();
        newSubject = new SubjectDTO();
    }

    /////////////// STUDENTS /////////////////
    public String createStudent() {
        try {
            studentBean.create(
                    newStudent.getUsername(),
                    newStudent.getPassword(),
                    newStudent.getName(),
                    newStudent.getEmail(),
                    newStudent.getCourseCode());
            newStudent.reset();
            return "index?faces-redirect=true";
        } catch (EntityAlreadyExistsException | EntityDoesNotExistsException | MyConstraintViolationException e) {
            FacesExceptionHandler.handleException(e, e.getMessage(), component, logger);
        } catch (Exception e) {
            FacesExceptionHandler.handleException(e, "Unexpected error! Try again latter!", component, logger);
        }
        return null;
    }

    public List<StudentDTO> getAllStudents() {
        try {
            return studentBean.getAll();
        } catch (Exception e) {
            FacesExceptionHandler.handleException(e, "Unexpected error! Try again latter!", logger);
            return null;
        }
    }

    public String updateStudent() {
        try {
            studentBean.update(
                    currentStudent.getUsername(),
                    currentStudent.getPassword(),
                    currentStudent.getName(),
                    currentStudent.getEmail(),
                    currentStudent.getCourseCode());
            return "index?faces-redirect=true";
            
        } catch (EntityDoesNotExistsException | MyConstraintViolationException e) {
            FacesExceptionHandler.handleException(e, e.getMessage(), logger);
        } catch (Exception e) {
            FacesExceptionHandler.handleException(e, "Unexpected error! Try again latter!", logger);
        }
        return "admin_students_update";
    }

    public void removeStudent(ActionEvent event) {
        try {
            UIParameter param = (UIParameter) event.getComponent().findComponent("studentUsername");
            String id = param.getValue().toString();
            studentBean.remove(id);
        } catch (EntityDoesNotExistsException e) {
            FacesExceptionHandler.handleException(e, e.getMessage(), logger);
        } catch (Exception e) {
            FacesExceptionHandler.handleException(e, "Unexpected error! Try again latter!", logger);
        }
    }

    public List<SubjectDTO> getCurrentStudentSubjects() {
        try {
            return subjectBean.getStudentSubjects(currentStudent.getUsername());
        } catch (Exception e) {
            FacesExceptionHandler.handleException(e, "Unexpected error! Try again latter!", logger);
            return null;
        }
    }
    */
 }

