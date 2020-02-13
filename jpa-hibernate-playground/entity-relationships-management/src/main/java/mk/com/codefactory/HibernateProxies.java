package mk.com.codefactory;


import mk.com.codefactory.basic.Address;
import mk.com.codefactory.basic.Role;
import mk.com.codefactory.basic.User;
import mk.com.codefactory.util.JpaUtil;
import mk.com.codefactory.util.TestDataGenerator;

import static mk.com.codefactory.util.JpaUtil.performWithinPersistenceContext;

import java.util.List;

/**
 * This example demonstrates how Hibernate creates proxies around entities.
 */
public class HibernateProxies {

    public static void main(String[] args) {
        JpaUtil.init("BasicEntitiesH2");


        User user = TestDataGenerator.generateUser();
        List<Role> roles = TestDataGenerator.generateRoleList();
        Address address = TestDataGenerator.generateAddress();

        saveUser(user, address, roles);
        printUserProxyClassByUserId(user.getId());
        printRoleSetProxyClassByUserId(user.getId());

        JpaUtil.close();
    }

    private static void saveUser(User user, Address address, List<Role> roles) {
        performWithinPersistenceContext(em -> {
           // user.setAddress(address);
            user.addRoles(roles);
            em.persist(user);
        });
    }

    /**
     * This examples uses {@link javax.persistence.EntityManager#getReference(Class, Object)} to create a user proxy
     *
     * @param userId
     */
    private static void printUserProxyClassByUserId(Long userId) {
        performWithinPersistenceContext(em -> {
            User userProxy = em.getReference(User.class, userId);
            System.out.println("\nUser proxy class is " + userProxy.getClass());
        });
    }


    private static void printRoleSetProxyClassByUserId(Long userId) {
        performWithinPersistenceContext(em -> {
            User user = em.find(User.class, userId);
            System.out.println("\nUser class is " + user.getClass());
            System.out.println("\nUser roles proxy class is " + user.getRoles().getClass());
        });
    }

}
