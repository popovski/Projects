package mk.com.codefactory;

import mk.com.codefactory.basic.Role;
import mk.com.codefactory.basic.User;
import mk.com.codefactory.util.JpaUtil;
import mk.com.codefactory.util.TestDataGenerator;

import static mk.com.codefactory.util.JpaUtil.performWithinPersistenceContext;

import java.util.List;

public class OneToManyRelation {

    public static void main(String[] args) {
        JpaUtil.init("BasicEntitiesMysql");

        User user = TestDataGenerator.generateUser();
        List<Role> roles = TestDataGenerator.generateRoleList();
        System.out.println(roles);

        System.out.println("Generated user: " + user);
        saveUserWithRoles(user, roles);

        printUserById(user.getId());

        JpaUtil.close();
    }

    private static void saveUserWithRoles(User user, List<Role> roleList) {
        performWithinPersistenceContext(em -> {
            em.persist(user);
            user.addRoles(roleList);
            roleList.forEach(em::persist);
            // the relation between User and Role is managed on the child side (Role side)
            // we use method addRoles() that setup relation on the child side (it sets user for each role)
        });
    }

    private static void printUserById(Long userId) {
        performWithinPersistenceContext(em -> {
            User persistedUser = em.find(User.class, userId);
            System.out.println("Persisted user: " + persistedUser);
        });

    }
}
