package com.playground;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import com.playground.model.Table1;
import com.playground.model.Table2;
import com.playground.util.Util;

public class AppOneToMany {
	public static void main(String[] args) {
		try {
			EntityManagerFactory entityManagerFactory = Util.createEntityManagerFactory();
			EntityManager entityManager = entityManagerFactory.createEntityManager();

			Table1 table1 = new Table1();
			Table2 table2 = new Table2();
			table2.setValue("Value Table 2");
						
			table1.setName("Nikola");
			List<Table2> lista = new ArrayList<>();
			lista.add(table2);
			
			table1.setComments(lista);
			
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
