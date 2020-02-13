package mk.com.codefactory;


import static mk.com.codefactory.util.JpaUtil.*;

import mk.com.codefactory.Account;
import mk.com.codefactory.util.TestDataGenerator;

/**
 * This tutorial provides examples of different JPA entity states. {@link Account} instance, which is a JPA entity
 * is moved from state TRANSIENT to state PERSISTENT, then it becomes DETACHED. After it again becomes PERSISTENT
 * and soon REMOVED.
 */
public class JpaEntityStates {
    public static void main(String[] args) {
        init("BasicEntitiesH2");

        Account account = TestDataGenerator.generateAccount();

        printAccountInTransientState(account);
        printAccountInPersistentState(account);
        printAccountInDetachedState(account);
        printAccountInRemovedState(account);

        close();
    }

    /**
     * Receives a new account instance. It doesn't have associated id. It is not stored in the database.
     *
     * @param account account in transient state
     */
    private static void printAccountInTransientState(Account account) {
        System.out.printf("Account in TRANSIENT state: %s%n", account);
    }

    /**
     * Receives an account in transient state. Opens a persistence context. Stores account into database, and prints
     * an account in persistent state. Persistent account that has associated id, is stored in the database and
     * has is associated with a persistence context.
     *
     * @param account account in transient state
     */
    private static void printAccountInPersistentState(Account account) {
        // performs logic in scope of persistence context
        performWithinPersistenceContext(entityManager -> {
            entityManager.persist(account); // stores an account in the database (makes it persistent)
            System.out.printf("Account in PERSISTENT state: %s%n", account);
        });
    }

    /**
     * Receives an account in DETACHED state and prints it. Detached account is an account that has associated id, has
     * corresponding database record, but does not associated with a persistent context.
     *
     * @param account account in DETACHED state
     */
    private static void printAccountInDetachedState(Account account) {
        System.out.printf("Account in DETACHED state: %s%n", account);
    }

    /**
     * Receives an account in detached state. Opens a persistence context, merges an account to make it maneged (persistent)
     * and then removes an account. Prints account in REMOVED state
     *
     * @param account account in DETACHED state
     */
    private static void printAccountInRemovedState(Account account) {
        performWithinPersistenceContext(entityManager -> {
            Account mergedAccount = entityManager.merge(account);
            entityManager.remove(mergedAccount);
            System.out.printf("Account in REMOVED state: %s%n", mergedAccount);

        });
    }
}
