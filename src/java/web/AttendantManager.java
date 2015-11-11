
package web;

import dtos.AttendantDTO;
import ejbs.AttendantBean;
import ejbs.CategoryBean;
import ejbs.EventBean;
import entities.Attendant;
import entities.Category;
import entities.Event;
import exceptions.EntityAlreadyExistsException;
import exceptions.EntityDoesNotExistsException;
import exceptions.MyConstraintViolationException;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
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
    
    private static final Logger attendantLogger = Logger.getLogger("web.AttendantManager");
    
    private AttendantDTO newAttendant;
    private AttendantDTO currentAttendant;
    
    //para usar ou adaptar???
    //private EventDTO newEvent;
    //private EventDTO currentEvent;
    //private CategoryDTO newCategory;
    //private CategotyDTO currentCategory;
    
    
    private UIComponent attendantComponent;
    
    
    public AttendantManager() {
        newAttendant = new AttendantDTO();
        //newEvent = new EventDTO();
        //newCategory = new CategoryDTO();
    }
    
    
    /////////////// ATTENDANTS /////////////////
    
    public String createAttendandt() {
        try {
            attendantBean.createAttendant(
                    newAttendant.getUsername(),
                    newAttendant.getPassword(),
                    newAttendant.getName(),
                    newAttendant.getEmail());
            newAttendant.reset();
            return "attendant_panel?faces-redirect=true";
        } catch (EntityAlreadyExistsException | EntityDoesNotExistsException | MyConstraintViolationException e) {
            FacesExceptionHandler.handleException(e, e.getMessage(), attendantComponent, attendantLogger);
        } catch (Exception e) {
            FacesExceptionHandler.handleException(e, "Unexpected error! Try again latter!", attendantComponent, attendantLogger);
        }
        return null;
    }
    
    public List<AttendantDTO> getAllAttendants() {
        try {
            return attendantBean.getAllAttendants();
        } catch (Exception e) {
            FacesExceptionHandler.handleException(e, "Unexpected error! Try again latter!", attendantLogger);
            return null;
        }
    }

    public String updateAttendant() {
        try {
            attendantBean.updateAttendant(
                    currentAttendant.getId(),
                    currentAttendant.getUsername(),
                    currentAttendant.getPassword(),
                    currentAttendant.getName(),
                    currentAttendant.getEmail());
            return "attendant_panel?faces-redirect=true";
            
        } catch (EntityDoesNotExistsException | MyConstraintViolationException e) {
            FacesExceptionHandler.handleException(e, e.getMessage(), attendantLogger);
        } catch (Exception e) {
            FacesExceptionHandler.handleException(e, "Unexpected error! Try again latter!", attendantLogger);
        }
        return "attendant_update";
    }

    public void removeAttendant(ActionEvent event) {
        try {
            UIParameter param = (UIParameter) event.getComponent().findComponent("attendantId");
            Long id = Long.parseLong(param.getValue().toString());
            attendantBean.removeAttendant(id);
        } catch (EntityDoesNotExistsException e) {
            FacesExceptionHandler.handleException(e, e.getMessage(), attendantLogger);
        } catch (Exception e) {
            FacesExceptionHandler.handleException(e, "Unexpected error! Try again latter!", attendantLogger);
        }
    }

    /*
    public List<AttendantDTO> getCurrentAttendantEvents() {
        try {
            return eventBean.getAttendant(currentAttendant.getUsername());
        } catch (Exception e) {
            FacesExceptionHandler.handleException(e, "Unexpected error! Try again latter!", attendantLogger);
            return null;
        }
    }
    
     public List<AttendantDTO> getCurrentAttendantCategories() {
        try {
            return categoryBean.getAttendant(currentAttendant.getUsername());
        } catch (Exception e) {
            FacesExceptionHandler.handleException(e, "Unexpected error! Try again latter!", attendantLogger);
            return null;
        }
    }
    
    public void enrollAttendantIntoEvent(ActionEvent event) {
        try {
            UIParameter param = (UIParameter) event.getComponent().findComponent("attendantId");
            Long id = Long.parseLong(param.getValue().toString());
            attendantBean.enrollAttendantIntoEvent(id, currentEvent.getName());
        } catch (EntityDoesNotExistsException e) {
            FacesExceptionHandler.handleException(e, e.getMessage(), attendantLogger);
        } catch (Exception e) {
            FacesExceptionHandler.handleException(e, "Unexpected error! Try again latter!", attendantLogger);
        }
    }
    */
}