/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entity.CustomerOrder;
import entity.User;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author alber
 */
public class OrderFacade {
    
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
    
    public List<CustomerOrder> getAllOrders() {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Query query = em.createQuery("SELECT o FROM CustomerOrder o");
        List<CustomerOrder> orders = (List<CustomerOrder>) query.getResultList();
        em.close();
        return orders;

    }
    
    public CustomerOrder getOrderByOrderId(int id) {
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("SELECT o FROM CustomerOrder o WHERE o.idorders =:idorders");
        query.setParameter("idorders", id);
        CustomerOrder order = (CustomerOrder) query.getSingleResult();
        em.close();
        return order;
    }

    public CustomerOrder createOrder(int userid, CustomerOrder order){
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        User user = em.find(User.class, userid);
        order.setUser(user);
        em.persist(order);
        em.getTransaction().commit();
        em.close();
        return order;
    }
}
