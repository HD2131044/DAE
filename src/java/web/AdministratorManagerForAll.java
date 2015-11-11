
package web;

import dtos.AttendantDTO;
import ejbs.AdministratorBean;
import ejbs.AttendantBean;
import ejbs.CategoryBean;
import ejbs.EventBean;
import ejbs.ManagerBean;
import entities.Administrator;
import entities.Attendant;
import entities.Category;
import entities.Event;
import exceptions.EntityAlreadyExistsException;
import exceptions.EntityDoesNotExistsException;
import exceptions.MyConstraintViolationException;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.component.UIParameter;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

@ManagedBean
@SessionScoped
public class AdministratorManagerForAll {
    
    /*
    @EJB
    private AdministratorBean administratorBean;
    @EJB
    private ManagerBean managerBean;
    @EJB
    private AttendantBean attendantBean;
    @EJB
    private EventBean eventBean;
    @EJB
    private CategoryBean categoryBean;
    
    private static final Logger logger = Logger.getLogger("web.AdministratorManagerForAll");
    
    private AdministratorDTO newAdministrator;
    private AdministratorDTO currentAdministrator;
    private ManagerDTO newManager;
    private ManagerDTO currentManager;
    private AttendantDTO newAttendant;
    private AttendantDTO currentAttendant;
    private EventDTO newEvent;
    private EventDTO currentEvent;
    private CategoryDTO newCategory;
    private CategotyDTO currentCategory;
    
    private UIComponent component;
    
    
    public AdministratorManagerForAll() {
        newAdministrator = new AdministratorDTO();
        newManager = new ManagerDTO();
        newAttendant = new AttendantDTO();
        newEvent = new EventDTO();
        newCategory = new CategoryDTO();
    }
   
    
    
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///////////// ADMINISTRATORS ////////////
    
    
    
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///////////// MANAGERS //////////////////
    
    
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///////////// ATTENDANTS ////////////////
    
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
            FacesExceptionHandler.handleException(e, e.getMessage(), component, logger);
        } catch (Exception e) {
            FacesExceptionHandler.handleException(e, "Unexpected error! Try again latter!", component, logger);
        }
        return null;
    }
    
    public List<AttendantDTO> getAllAttendants() {
        try {
            return attendantBean.getAllAttendants();
        } catch (Exception e) {
            FacesExceptionHandler.handleException(e, "Unexpected error! Try again latter!", logger);
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
            FacesExceptionHandler.handleException(e, e.getMessage(), logger);
        } catch (Exception e) {
            FacesExceptionHandler.handleException(e, "Unexpected error! Try again latter!", logger);
        }
        return "attendant_update";
    }

    public void removeAttendant(ActionEvent event) {
        try {
            UIParameter param = (UIParameter) event.getComponent().findComponent("attendantId");
            Long id = Long.parseLong(param.getValue().toString());
            attendantBean.removeAttendant(id);
        } catch (EntityDoesNotExistsException e) {
            FacesExceptionHandler.handleException(e, e.getMessage(), logger);
        } catch (Exception e) {
            FacesExceptionHandler.handleException(e, "Unexpected error! Try again latter!", logger);
        }
    }

    public List<AttendantDTO> getCurrentAttendantEvents() {
        try {
            return eventBean.getAttendant(currentAttendant.getUsername());
        } catch (Exception e) {
            FacesExceptionHandler.handleException(e, "Unexpected error! Try again latter!", logger);
            return null;
        }
    }
    
     public List<AttendantDTO> getCurrentAttendantCategories() {
        try {
            return categoryBean.getAttendant(currentAttendant.getUsername());
        } catch (Exception e) {
            FacesExceptionHandler.handleException(e, "Unexpected error! Try again latter!", logger);
            return null;
        }
    }
    
    public void enrollAttendantIntoEvent(ActionEvent event) {
        try {
            UIParameter param = (UIParameter) event.getComponent().findComponent("attendantId");
            Long id = Long.parseLong(param.getValue().toString());
            attendantBean.enrollAttendantIntoEvent(id, currentEvent.getName());
        } catch (EntityDoesNotExistsException e) {
            FacesExceptionHandler.handleException(e, e.getMessage(), logger);
        } catch (Exception e) {
            FacesExceptionHandler.handleException(e, "Unexpected error! Try again latter!", logger);
        }
    }  
    
    
    
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///////////// EVENTS ////////////////////
    
    
    
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///////////// CATEGORIES ////////////////
    
    
    

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////GETS AND SETS//////////////

    public AdministratorDTO getNewAdministrator() {
        return newAdministrator;
    }

    public void setNewAdministrator(AdministratorDTO newAdministrator) {
        this.newAdministrator = newAdministrator;
    }

    public AdministratorDTO getCurrentAdministrator() {
        return currentAdministrator;
    }

    public void setCurrentAdministrator(AdministratorDTO currentAdministrator) {
        this.currentAdministrator = currentAdministrator;
    }

    public ManagerDTO getNewManager() {
        return newManager;
    }

    public void setNewManager(ManagerDTO newManager) {
        this.newManager = newManager;
    }

    public ManagerDTO getCurrentManager() {
        return currentManager;
    }

    public void setCurrentManager(ManagerDTO currentManager) {
        this.currentManager = currentManager;
    }

    public AttendantDTO getNewAttendant() {
        return newAttendant;
    }

    public void setNewAttendant(AttendantDTO newAttendant) {
        this.newAttendant = newAttendant;
    }

    public AttendantDTO getCurrentAttendant() {
        return currentAttendant;
    }

    public void setCurrentAttendant(AttendantDTO currentAttendant) {
        this.currentAttendant = currentAttendant;
    }

    public EventDTO getNewEvent() {
        return newEvent;
    }

    public void setNewEvent(EventDTO newEvent) {
        this.newEvent = newEvent;
    }

    public EventDTO getCurrentEvent() {
        return currentEvent;
    }

    public void setCurrentEvent(EventDTO currentEvent) {
        this.currentEvent = currentEvent;
    }

    public CategoryDTO getNewCategory() {
        return newCategory;
    }

    public void setNewCategory(CategoryDTO newCategory) {
        this.newCategory = newCategory;
    }

    public CategotyDTO getCurrentCategory() {
        return currentCategory;
    }

    public void setCurrentCategory(CategotyDTO currentCategory) {
        this.currentCategory = currentCategory;
    }

    public UIComponent getComponent() {
        return component;
    }

    public void setComponent(UIComponent component) {
        this.component = component;
    }
    
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///////////// VALIDATORS ////////////////
    public void validateUsername(FacesContext context, UIComponent toValidate, Object value) {
        try {
            //Your validation code goes here
            String username = (String) value;
            //If the validation fails
            if (username.startsWith("xpto")) {
                FacesMessage message = new FacesMessage("Error: invalid username.");
                message.setSeverity(FacesMessage.SEVERITY_ERROR);
                context.addMessage(toValidate.getClientId(context), message);
                ((UIInput) toValidate).setValid(false);
            }
        } catch (Exception e) {
            FacesExceptionHandler.handleException(e, "Unkown error.", logger);
        }
    }
*/
 }

