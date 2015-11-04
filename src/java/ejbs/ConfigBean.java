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
            
            
            
            administratorBean.createAdministrator("Administrator_Name_01", "administrator_email_01@email.com", "Administrator_username_01", "12345678");
            administratorBean.createAdministrator("Administrator_Name_02", "administrator_email_02@email.com", "Administrator_username_02", "12345678");
            administratorBean.createAdministrator("Administrator_Name_03", "administrator_email_03@email.com", "Administrator_username_03", "12345678");
            administratorBean.createAdministrator("Administrator_Name_04", "administrator_email_04@email.com", "Administrator_username_04", "12345678");
            
            managerBean.createManager("Manager_Name_01", "manager_email_01@email.com", "Manager_username_01", "12345678");
            managerBean.createManager("Manager_Name_02", "manager_email_02@email.com", "Manager_username_02", "12345678");

           
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
