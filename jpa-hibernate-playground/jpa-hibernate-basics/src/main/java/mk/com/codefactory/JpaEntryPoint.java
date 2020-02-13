package mk.com.codefactory;

import mk.com.codefactory.Account;
import mk.com.codefactory.util.JpaUtil;
import mk.com.codefactory.util.TestDataGenerator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * {@link JpaEntryPoint} shows an example of using basic JPA classes {@link EntityManagerFactory} and {@link EntityManager}.
 * <p>
 * {@link EntityManager} allows to perform database operations with JPA entities. It represents a db session. E.g. each
 * user should get a new instance of {@link EntityManager} each time to perform db operations on JPA entity.
 * <p>
 * {@link EntityManagerFactory} is a thread-safe factory that allow to create {@link EntityManager} instances.
 */
public class JpaEntryPoint {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Util.createEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        Account account = TestDataGenerator.generateAccount();

        entityManager.getTransaction().begin();
        entityManager.persist(account);
        entityManager.getTransaction().commit();

        System.out.println(account);

        entityManager.close();
        entityManagerFactory.close();
    }
}
