package utils;

import entity.Role;
import entity.User;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class SetupTestUsers {

  public static void main(String[] args) {

    EntityManager em = Persistence.createEntityManagerFactory("pu").createEntityManager();
    
    // IMPORTAAAAAAAAAANT!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    // This breaks one of the MOST fundamental security rules in that it ships with default users and passwords
    // CHANGE the three passwords below, before you uncomment and execute the code below
      

    em.getTransaction().begin();
    Role userRole = new Role("user");
    Role adminRole = new Role("admin");
    User user = new User("user", "");
    user.addRole(userRole);
    User admin = new User("admin", "");
    admin.addRole(adminRole);
    User both = new User("user_admin", "");
    both.addRole(userRole);
    both.addRole(adminRole);
    em.persist(userRole);
    em.persist(adminRole);
    em.persist(user);
    em.persist(admin);
    em.persist(both);
    em.getTransaction().commit();
    User user2 = (User) em.createQuery("SELECT u FROM User u WHERE u.userName =:userName").setParameter("userName", "user").getSingleResult();

//    System.out.println("PW: " + user.getUserPass());
    System.out.println("Testing user with OK password: " + user2.verifyPassword("user"));
    System.out.println("Testing user with wrong password: " + user2.verifyPassword(""));
//    System.out.println("Created TEST Users");
  }

}
