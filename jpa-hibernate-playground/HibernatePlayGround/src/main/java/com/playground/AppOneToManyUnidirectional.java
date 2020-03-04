package com.playground;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import com.playground.bidirectional.Table1;
import com.playground.bidirectional.Table2;
import com.playground.util.Util;
// https://vladmihalcea.com/the-best-way-to-map-a-onetomany-association-with-jpa-and-hibernate/
public class AppOneToManyUnidirectional {
	public static void main(String[] args) {
		try {
			EntityManagerFactory entityManagerFactory = Util.createEntityManagerFactory();
			EntityManager entityManager = entityManagerFactory.createEntityManager();

			Table1 table1 = new Table1();
			Table2 table2 = new Table2();
			table2.setValue("Value Table 2");
			table2.setTable1(table1);
			
			table1.setName("Nikola");
			
			table1.getComments().add(table2);
			
			entityManager.getTransaction().begin();
			entityManager.persist(table1);
			entityManager.getTransaction().commit();

			entityManager.close();
			entityManagerFactory.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
