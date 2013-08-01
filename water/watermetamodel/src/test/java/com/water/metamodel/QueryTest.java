package com.water.metamodel;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.junit.Before;
import org.junit.Test;

import com.water.metamodel.account.Account;
import com.water.metamodel.account.AccountAdmin;
import com.water.metamodel.tree.Category;

/**
 * 生成元模型命令(jdk1.6+):javac -classpath path/to/openjpa-all.jar
 * -Aopenjpa.metamodel=true mypackage/MyEntity.java 注：该命令生成的代码在该运行命令行的目录下
 * 
 * 使用元模型,则使用语句:Root<User>.get(User_.displayname).
 * 否则使用语句:Root<User>.get(entityType
 * .getSingularAttribute("displayname",String.class)
 * 
 * @author ACER
 * 
 */
public class QueryTest {
	private EntityManager entityManager;
	private EntityManagerFactory entityManagerFactory;

	@Before
	public void init() {
		try{
		entityManagerFactory = Persistence.createEntityManagerFactory("authorityunit");
		entityManager = entityManagerFactory.createEntityManager();
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}

	@Test
	public void testQuery1() {
		try {
			entityManager.getTransaction().begin();
			Account account = new Account();
			AccountAdmin accountAdmin = new AccountAdmin();
			account.setLogincode("admin");
			account.setLoginpasswd( "passwd");
			account.setAccounttype(Account.ACCOUNT_TYPE_ADMIN);
			
			accountAdmin.setEmail("360898142@qq.com");
			//account.setAccountAdmin(accountAdmin);
			
			entityManager.persist(account);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testQuery2(){
		entityManager.getTransaction().begin();
		List<Category> categorys = entityManager.createQuery("select category from Category category", Category.class).getResultList();
		entityManager.getTransaction().commit();
	}
	
	@Test
	public void testQuery3(){
		entityManager.getTransaction().begin();
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Account> criteriaQuery = criteriaBuilder.createQuery(Account.class);

		Root<Account> root = criteriaQuery.from(Account.class);
		//Predicate ploginpasswd = criteriaBuilder.equal(root.get("loginpasswd"), loginpasswd);
		//Predicate plogincode = criteriaBuilder.equal(root.get("logincode"), logincode);

		criteriaQuery.select(root)
		//.where(criteriaBuilder.and(plogincode, ploginpasswd))
		.orderBy(criteriaBuilder.desc(root.get("id")));
		List<Account> accounts = entityManager.createQuery(criteriaQuery).getResultList();
		entityManager.getTransaction().commit();
		System.out.println(accounts);
	}
	
	@Test
	public void testQuery4(){
		//entityManagerFactory = Persistence.createEntityManagerFactory("authorityunit");
		//entityManager = entityManagerFactory.createEntityManager();
		System.out.println(entityManager);
		//entityManager.getTransaction().begin();
		List<Account> accounts = entityManager.createQuery("select account from Account account", Account.class).getResultList();
		//entityManager.getTransaction().commit();
		System.out.println(accounts);
		
		
		accounts = entityManager.createNativeQuery("select * from water.auth_account").getResultList();
		System.out.println(accounts);
		
		List<Category> categorys = entityManager.createQuery("select categories from Account account join account.categories categories where account.id = :accountid and categories.parentid =:parentid", Category.class).setParameter("parentid", "0").setParameter("accountid", "4EE9C8F0F12E11E2BF63E4517F000001").getResultList();
		System.out.println(categorys);
		
		System.out.println(entityManager.find(Account.class, "4EE9C8F0F12E11E2BF63E4517F000001").getCategories());
	}
	@Test
	public void testQuery5(){
		List<Category> categorys = entityManager.createQuery("select categories from Account account join account.categories categories where account.id = :accountid and categories.parentid =:parentid", Category.class).setParameter("parentid", "0").setParameter("accountid", "4EE9C8F0F12E11E2BF63E4517F000001").getResultList();
		System.out.println(categorys);
		
	}
	
}
