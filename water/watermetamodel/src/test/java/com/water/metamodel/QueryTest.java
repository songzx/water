package com.water.metamodel;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Tuple;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.SetJoin;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;

import org.apache.openjpa.persistence.criteria.ComparisonStyle;
import org.apache.openjpa.persistence.criteria.CriteriaBuilderImpl;
import org.apache.openjpa.persistence.meta.MetamodelImpl;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.water.metamodel.account.Account;
import com.water.metamodel.account.AccountAdmin;

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
		entityManagerFactory = Persistence.createEntityManagerFactory("authorityunit");
		entityManager = entityManagerFactory.createEntityManager();
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
}
