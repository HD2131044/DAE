/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;

@Singleton
@Startup
public class ConfigBean {

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
    
    
    @PostConstruct
    public void populateDB() {
        try {
            
            administratorBean.createAdministrator("Administrator_username_01", "12345678", "Administrator_Name_01", "administrator_email_01@email.com");
            administratorBean.createAdministrator("Administrator_username_02", "12345678", "Administrator_Name_02", "administrator_email_02@email.com");
            administratorBean.createAdministrator("Administrator_username_03", "12345678", "Administrator_Name_03", "administrator_email_03@email.com");
            administratorBean.createAdministrator("Administrator_username_04", "12345678", "Administrator_Name_04", "administrator_email_04@email.com");
            
            managerBean.createManager("Manager_username_01", "12345678", "Manager_Name_01", "manager_email_01@email.com");
            managerBean.createManager("Manager_username_02", "12345678", "Manager_Name_02", "manager_email_02@email.com");
            
            attendantBean.createAttendant("Attendant_username_01", "12345678", "Attendant_Name_01", "attendant_email_01@email.com");
            attendantBean.createAttendant("Attendant_username_02", "12345678", "Attendant_Name_02", "attendant_email_02@email.com");
            attendantBean.createAttendant("Attendant_username_03", "12345678", "Attendant_Name_03", "attendant_email_03@email.com");
            attendantBean.createAttendant("Attendant_username_04", "12345678", "Attendant_Name_04", "attendant_email_04@email.com");
            attendantBean.createAttendant("Attendant_username_05", "12345678", "Attendant_Name_05", "attendant_email_05@email.com");
            attendantBean.createAttendant("Attendant_username_06", "12345678", "Attendant_Name_06", "attendant_email_06@email.com");
            attendantBean.createAttendant("Attendant_username_07", "12345678", "Attendant_Name_07", "attendant_email_07@email.com");
            attendantBean.createAttendant("Attendant_username_08", "12345678", "Attendant_Name_08", "attendant_email_08@email.com");
            attendantBean.createAttendant("Attendant_username_09", "12345678", "Attendant_Name_09", "attendant_email_09@email.com");

           eventBean.createEvent("Evento_1","Descricao Evento_1", "12:00 01-10-2015", "13:00 01-10-2015");
           eventBean.createEvent("Evento_2","Descricao Evento_2", "12:00 08-10-2015", "13:00 08-10-2015");
           eventBean.createEvent("Evento_3","Descricao Evento_3", "12:00 09-10-2015", "13:00 09-10-2015");
           eventBean.createEvent("Evento_4","Descricao Evento_4", "12:00 01-11-2015", "13:00 01-11-2015");
           eventBean.createEvent("Evento_5","Descricao Evento_5", "15:00 01-10-2015", "17:00 01-10-2015");
           eventBean.createEvent("Evento_6","Descricao Evento_6", "12:00 01-11-2015", "12:00 02-11-2015");
           eventBean.createEvent("Evento_7","Descricao Evento_7", "12:00 01-10-2015", "13:00 01-10-2015");
           eventBean.createEvent("Evento_8","Descricao Evento_8", "16:00 01-10-2015", "17:00 01-10-2015");
           eventBean.createEvent("Evento_9","Descricao Evento_9", "12:00 01-10-2015", "13:00 01-10-2015");
           eventBean.createEvent("Evento_10","Descricao Evento_10", "18:00 01-10-2015", "19:00 01-10-2015");
           eventBean.createEvent("Evento_11","Descricao Evento_11", "20:00 01-10-2015", "23:00 01-10-2015");
           eventBean.createEvent("Evento_12","Descricao Evento_12", "14:00 01-11-2015", "18:00 01-11-2015");
           
           categoryBean.createCategory("Categoria_1");
           categoryBean.createCategory("Categoria_2");
           categoryBean.createCategory("Categoria_3");
           categoryBean.createCategory("Categoria_4");
           categoryBean.createCategory("Categoria_5");
           categoryBean.createCategory("Categoria_6");
           categoryBean.createCategory("Categoria_7");
           
           Long managerID = Long.parseLong("5");
           Long eventID = Long.parseLong("18");
           managerBean.enrollManagerInEvent(managerID, eventID);
     
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
