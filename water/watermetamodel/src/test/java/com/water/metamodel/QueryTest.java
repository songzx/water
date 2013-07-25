package com.water.metamodel;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

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
}
