/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import entity.Candy;
import entity.CustomerOrder;
import entity.OrderLine;
import entity.User;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Mads
 */
public class TestFacade {
    public static void main(String[] args) {
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        EntityManager em = emf.createEntityManager();
    
        em.getTransaction().begin();
            CustomerOrder order = new CustomerOrder();
            order.addOrderLine(new OrderLine(555, new Candy(1)));
            order.addOrderLine(new OrderLine(555, new Candy(2)));
            User user = em.find(User.class, 1);
            order.setUser(user);
            user.addOrder(order);
            em.persist(order);
        em.getTransaction().commit();
        em.close();
    }
}
