/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs;

import dtos.CategoryDTO;
import entities.Category;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class CategoryBean {

    @PersistenceContext
    private EntityManager em;
    
    public void createCategory (String name){
        try {
            Category c = new Category (name);
            em.persist(c);   
        } catch (Exception ex) {
            throw new EJBException(ex.getMessage());
        }
    }
    
     public List<Category> getAllCategories() {
        try {
            List<Category> categories = (List<Category>) em.createNamedQuery("getAllCategories").getResultList();
            return categories;
        } catch (Exception ex) {
            throw new EJBException(ex.getMessage());
        }
    }
  
    public void updateCategory(Long id, String name) {
        try {
            Category cUpdate = em.find(Category.class, id);
            if (cUpdate == null) {
                return;
            }
            cUpdate.setName(name);
            em.merge(cUpdate);
        } catch (Exception ex) {
            throw new EJBException(ex.getMessage());
        }
    }
    
      

    public void removeCategory (Long id) {
        try {
            Category cRemove = em.find(Category.class, id);
            if (cRemove == null) {
                return;
            }
            em.remove(cRemove);
        } catch (Exception ex) {
            throw new EJBException(ex.getMessage());
        }
    }
    
    /*
    public List<CategoryDTO> getAllCategories() {
        try {
            List<Category> categories = (List<Category>) em.createNamedQuery("getAllCategories").getResultList();
            return categoriesToDTO(categories);
        } catch (Exception ex) {
            throw new EJBException(ex.getMessage());
        }
    }
    */
    
    private List<CategoryDTO> categoriesToDTO(List<Category> categories) {
        List<CategoryDTO> dtos = new ArrayList<>();
        for (Category c : categories) {
            dtos.add(new CategoryDTO(c.getId(), c.getName()));            
        }
        return dtos;
    }
}
