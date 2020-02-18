package mk.com.codefactory.many.to.many;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import mk.com.codefactory.Util;
import mk.com.codefactory.manytomany.Table1;
import mk.com.codefactory.manytomany.Table2;

public class ManyToManyRelation {
    public static void main(String[] args) {
    	//createNewRecords();
    	getTable();
    }
    
    public static void getTable() {
        EntityManagerFactory entityManagerFactory = Util.createEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        
    	CriteriaBuilder cb = entityManager.getCriteriaBuilder();

    	CriteriaQuery<Table1> cq = cb.createQuery(Table1.class);
    	Root<Table1> from = cq.from(Table1.class);

    	cq.select(from);
    	TypedQuery<Table1> q = entityManager.createQuery(cq);
    	List<Table1> allitems = q.getResultList();
    	System.out.println(allitems.size());
    	
        entityManager.close();
        entityManagerFactory.close();
    }
    
    public static void createNewRecords() {
        Table1 table1 = new Table1();
        table1.setName("table1Name");
        
        Table2 table2 = new Table2();
        table2.setName("table2Name");
        
        List<Table2> lista = new ArrayList<>();
        lista.add(table2);
        
        table1.setSelectedTable2(lista);
        EntityManagerFactory entityManagerFactory = Util.createEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        
        entityManager.getTransaction().begin();
        entityManager.persist(table2);
        entityManager.persist(table1);
        entityManager.getTransaction().commit();
        
        entityManager.close();
        entityManagerFactory.close();
    }
}
