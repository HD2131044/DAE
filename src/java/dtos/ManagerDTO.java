/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

import java.io.Serializable;

public class ManagerDTO extends UserDTO implements Serializable{
    
    public ManagerDTO() {
    }    
    
    public ManagerDTO(Long id, String username, String password, String name, String email) {
        super(id, username, password, name, email);
    }
    
}
