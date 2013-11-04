package com.water.metamodel;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.apache.commons.io.IOUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.water.metamodel.account.Account;
import com.water.metamodel.account.AccountAdmin;
import com.water.metamodel.account.AccountWeb;
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
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@Test
	public void testQuery1() {
		try {net.sf.ehcache.util.UpdateChecker
			entityManager.getTransaction().begin();
			AccountAdmin account = new AccountAdmin();
			account.setId("4EE66D90F12E11E2BF63E4517F000001");
			account.setLogincode("admin3224");
			account.setLoginpasswd("passwd3242");
			account.setAccounttype(Account.ACCOUNT_TYPE_ADMIN);
			// account.setAccountWeb(new AccountWeb());
			// entityManager.persist(account);
			account.setEmail("360898142@qq.com");

			entityManager.refresh(account);

			entityManager.getTransaction().commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Assert.fail();
		}
	}

	@Test
	public void testQuery2() {
		entityManager.getTransaction().begin();
		List<Category> categorys = entityManager.createQuery("select category from Category category", Category.class).getResultList();
		entityManager.getTransaction().commit();
	}

	@Test
	public void testQuery3() {
		entityManager.getTransaction().begin();
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Account> criteriaQuery = criteriaBuilder.createQuery(Account.class);

		Root<Account> root = criteriaQuery.from(Account.class);
		// Predicate ploginpasswd =
		// criteriaBuilder.equal(root.get("loginpasswd"), loginpasswd);
		// Predicate plogincode = criteriaBuilder.equal(root.get("logincode"),
		// logincode);

		criteriaQuery.select(root)
		// .where(criteriaBuilder.and(plogincode, ploginpasswd))
		.orderBy(criteriaBuilder.desc(root.get("id")));
		List<Account> accounts = entityManager.createQuery(criteriaQuery).getResultList();
		entityManager.getTransaction().commit();
		System.out.println(accounts);
	}

	@Test
	public void testQuery4() {
		// entityManagerFactory =
		// Persistence.createEntityManagerFactory("authorityunit");
		// entityManager = entityManagerFactory.createEntityManager();
		System.out.println(entityManager);
		// entityManager.getTransaction().begin();
		List<Account> accounts = entityManager.createQuery("select account from Account account", Account.class).getResultList();
		// entityManager.getTransaction().commit();
		System.out.println(accounts);

		accounts = entityManager.createNativeQuery("select * from water.auth_account").getResultList();
		System.out.println(accounts);

		List<Category> categorys = entityManager.createQuery("select categories from Account account join account.categories categories where account.id = :accountid and categories.parentid =:parentid", Category.class).setParameter("parentid", "0").setParameter("accountid", "4EE9C8F0F12E11E2BF63E4517F000001").getResultList();
		System.out.println(categorys);

		//System.out.println(entityManager.find(Account.class, "4EE9C8F0F12E11E2BF63E4517F000001").getCategories());
	}

	@Test
	public void testQuery5() throws IOException {
		List<Category> categorys = entityManager.createQuery("select categories from Account account join account.categories categories where account.id = :accountid and categories.parentid =:parentid", Category.class).setParameter("parentid", "0").setParameter("accountid", "4EE9C8F0F12E11E2BF63E4517F000001").getResultList();
		System.out.println(categorys);

		String path = QueryTest.class.getResource("").getPath() + "tmp/";
		StringBuffer sb = new StringBuffer();
		categorys = entityManager.createQuery("select categories from Account account join account.categories categories where account.id = :accountid and categories.parentid =:parentid", Category.class).setParameter("parentid", "0").setParameter("accountid", "4EE9C8F0F12E11E2BF63E4517F000001").getResultList();
		sb = new StringBuffer();
		sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		sb.append("<root>");
		for (Category category : categorys) {
			if ("0".equals(category.getParentid())) {
				sb.append("<item id=\"" + category.getId() + "\">");
				sb.append("<content>");
				sb.append("<name><![CDATA[" + category.getName() + "]]></name>");
				sb.append("<url><![CDATA[" + (category.getUrl() == null ? "javascript:" : ("/waterweb" + category.getUrl())) + "]]></url>");
				sb.append("</content>");
				sb.append(getcategorytree(categorys, category.getId()));
				sb.append("</item>");
			}
		}
		sb.append("</root>");
		OutputStream outputStream = new FileOutputStream(path + "4EE9C8F0F12E11E2BF63E4517F000001_0.xml");
		IOUtils.write(sb.toString(), outputStream);

	}

	/**
	 * 生成栏目的xml
	 * 
	 * @throws IOException
	 */
	@Test
	public void testQuery6() throws IOException {
		String path = QueryTest.class.getResource("").getPath() + "tmp/";
		List<Category> categorys = entityManager.createQuery("select categories from Category categories ", Category.class).getResultList();
		StringBuffer sb = new StringBuffer();
		sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		sb.append("<root>");
		for (Category category : categorys) {
			if ("0".equals(category.getParentid())) {
				sb.append("<item id=\"" + category.getId() + "\">");
				sb.append("<content>");
				sb.append("<id><![CDATA[" + category.getId() + "]]></id>");
				sb.append("<name><![CDATA[" + category.getName() + "]]></name>");
				sb.append("<url><![CDATA[" + (category.getUrl() == null ? "javascript:" : ("/waterweb" + category.getUrl())) + "]]></url>");
				sb.append("</content>");
				sb.append(getcategorytree(categorys, category.getId()));
				sb.append("</item>");
			}
		}
		sb.append("</root>");
		System.out.println(path);
		OutputStream outputStream = new FileOutputStream(path + "category.xml");
		IOUtils.write(sb.toString(), outputStream);

		categorys = entityManager.createQuery("select categories from Account account join account.categories categories where account.id = :accountid and categories.parentid =:parentid", Category.class).setParameter("parentid", "0").setParameter("accountid", "4EE9C8F0F12E11E2BF63E4517F000001").getResultList();
		sb = new StringBuffer();
		sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		sb.append("<root>");
		for (Category category : categorys) {
			if ("0".equals(category.getParentid())) {
				sb.append("<item id=\"" + category.getId() + "\">");
				sb.append("<content>");
				sb.append("<id><![CDATA[" + category.getId() + "]]></id>");
				sb.append("<name><![CDATA[" + category.getName() + "]]></name>");
				sb.append("<url><![CDATA[" + (category.getUrl() == null ? "javascript:" : ("/waterweb" + category.getUrl())) + "]]></url>");
				sb.append("</content>");
				sb.append(getcategorytree(categorys, category.getId()));
				sb.append("</item>");
			}
		}
		sb.append("</root>");
		outputStream = new FileOutputStream(path + "4EE9C8F0F12E11E2BF63E4517F000001_0.xml");
		IOUtils.write(sb.toString(), outputStream);

		List<Category> categorys2 = entityManager.createQuery("select categories from Category categories ", Category.class).getResultList();
		categorys = entityManager.createQuery("select categories from Account account join account.categories categories where account.id = :accountid ", Category.class).setParameter("accountid", "4EE9C8F0F12E11E2BF63E4517F000001").getResultList();
		for (Category category2 : categorys2) {
			if ("0".equals(category2.getParentid())) {// 跳过父节点为０的栏目，不然被替换
				continue;
			}

			sb = new StringBuffer();
			sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
			sb.append("<root>");
			for (Category category : categorys) {
				if (category2.getParentid().equals(category.getParentid())) {
					sb.append("<item id=\"" + category.getId() + "\">");
					sb.append("<content>");
					sb.append("<id><![CDATA[" + category.getId() + "]]></id>");
					sb.append("<name><![CDATA[" + category.getName() + "]]></name>");
					sb.append("<url><![CDATA[" + (category.getUrl() == null ? "javascript:" : ("/waterweb" + category.getUrl())) + "]]></url>");
					sb.append("</content>");
					sb.append(getcategorytree(categorys, category.getId()));
					sb.append("</item>");
				}
			}
			sb.append("</root>");
			outputStream = new FileOutputStream(path + "4EE9C8F0F12E11E2BF63E4517F000001_" + category2.getParentid() + ".xml");
			IOUtils.write(sb.toString(), outputStream);
		}
	}

	private StringBuffer getcategorytree(List<Category> categorys, String id) {
		StringBuffer sb = new StringBuffer("");
		for (Category category : categorys) {
			if (id.equals(category.getParentid())) {
				sb.append("<item id=\"" + category.getId() + "\">");
				sb.append("<content>");
				sb.append("<id><![CDATA[" + category.getId() + "]]></id>");
				sb.append("<name><![CDATA[" + category.getName() + "]]></name>");
				sb.append("<url><![CDATA[" + (category.getUrl() == null ? "javascript:" : ("/waterweb" + category.getUrl())) + "]]></url>");
				sb.append("</content>");
				sb.append(getcategorytree(categorys, category.getId()));
				sb.append("</item>");
			}
		}
		return sb;
	}

}
