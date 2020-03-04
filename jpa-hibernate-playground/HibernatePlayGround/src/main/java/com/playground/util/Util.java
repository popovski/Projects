package com.playground.util;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Util {
    /**
     * Creates an instance of {@link EntityManagerFactory}. It uses JPA util class {@link Persistence} that allows
     * to create an instance by its name. All JPA configuration required to create {@link EntityManagerFactory} are
     * located in resources  /META-INF/persistence.xml which is a default location for JPA configs.
     *
     * @return instance of entity manager factory
     */
    public static EntityManagerFactory createEntityManagerFactory() {
        return Persistence.createEntityManagerFactory("BasicEntitiesMysql");
    }
}
