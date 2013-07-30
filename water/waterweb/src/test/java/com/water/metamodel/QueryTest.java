package com.water.metamodel;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
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

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.water.basictool.MD5Util;
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
		try {
			entityManagerFactory = Persistence.createEntityManagerFactory("authorityunit");
			entityManager = entityManagerFactory.createEntityManager();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testQuery1() {
		/*
		try {
			
			for (int i = 0; i < 5; i++) {
				entityManager = entityManagerFactory.createEntityManager();
				entityManager.getTransaction().begin();
				Account account = new Account();
				AccountAdmin accountAdmin = new AccountAdmin();
				account.setLogincode("admin" + i);
				account.setLoginpasswd(MD5Util.getInstance().compileWithSalt("passwd", "admin"+ i));
				account.setAccounttype(Account.ACCOUNT_TYPE_ADMIN);

				accountAdmin.setEmail("360898142@qq.com");
				//accountAdmin.setAccount(account);

				entityManager.persist(accountAdmin);
				
				Category category = new Category();
				category.setCode("systemsetup"+i);
				category.setName("系统设置"+i);
				category.setParentid("0");
				entityManager.persist(category);
				
				
				entityManager.getTransaction().commit();
			}
			
			//entityManager.flush();
			//entityManager.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}
	
	@Test
	public void testQuery2() {
		/*
		try {
			
			String strs[] = new String[25];
			strs[0] = "4ECE78C0F12E11E2BF63E4517F000001";
			strs[1] = "4EE38761F12E11E2BF63E4517F000001";
			strs[2] = "4EE694A0F12E11E2BF63E4517F000001";
			strs[3] = "4EE81B41F12E11E2BF63E4517F000001";
			strs[4] = "4EE9C8F1F12E11E2BF63E4517F000001";
			strs[5] = "FFA5CE70F14511E2877C29617F000001";
			strs[6] = "FFA86680F14511E2877C29617F000001";
			strs[7] = "165E26D0F14611E2B14F36817F000001";
			strs[8] = "167ECE30F14611E2B14F36817F000001";
			strs[9] = "16807BE0F14611E2B14F36817F000001";
			int count = 10;
			
			for (int i = 5; i < 500; i++) {
				String parentid = "";
				int index = new Random().nextInt(500);
				if(count > index){
					parentid = strs[index];
					System.out.println(i+"["+index+"]"+"["+parentid+"]");
				}else{
					continue;
				}
				
				entityManager = entityManagerFactory.createEntityManager();
				entityManager.getTransaction().begin();
				
				Category category = new Category();
				category.setCode(RandData.getInstance().get0_z(10));
				category.setName(RandData.getInstance().getZh(5));
				
				category.setParentid(parentid);
				entityManager.persist(category);
				
				entityManager.getTransaction().commit();
				strs[count] = category.getId();
				count ++;
			}
			
			//entityManager.flush();
			//entityManager.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}
}
