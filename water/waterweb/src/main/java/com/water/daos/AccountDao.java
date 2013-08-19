package com.water.daos;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.FlushModeType;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.SingularAttribute;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Repository;

import com.water.actions.PageBean;
import com.water.metamodel.account.Account;
import com.water.metamodel.account.AccountAdmin;
import com.water.metamodel.account.AccountWeb;
import com.water.metamodel.tree.Category;

@Repository
public class AccountDao extends JpaBasiceDao {

	@Override
	@PersistenceContext(unitName = "authorityunit")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}

	/**
	 * 根据帐号与密码获取用户信息
	 * 
	 * @param logincode
	 * @param loginpasswd
	 * @return
	 */
	public Account getAccount(String logincode, String loginpasswd) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Account> criteriaQuery = criteriaBuilder.createQuery(Account.class);

		Root<Account> root = criteriaQuery.from(Account.class);
		Predicate ploginpasswd = criteriaBuilder.equal(root.get("loginpasswd"), loginpasswd);
		Predicate plogincode = criteriaBuilder.equal(root.get("logincode"), logincode);

		criteriaQuery.select(root).where(criteriaBuilder.and(plogincode, ploginpasswd)).orderBy(criteriaBuilder.desc(root.get("id")));
		List<Account> accounts = entityManager.createQuery(criteriaQuery).getResultList();
		if (accounts == null || accounts.size() == 0) {
			return null;
		}

		return accounts.get(0);
	}

	public List<Category> getCategories(String accountid, String parentid) {
		List<Category> categorys = entityManager.createQuery("select category from Category category where category.parentid = '0'", Category.class).getResultList();
		return categorys;
	}
	

	public PageBean list(PageBean pageBean) {
		try {
			Object count = entityManager.createQuery("select count(account) from AccountAdmin account where account.logincode like :logincode or account.email like :email")
					.setParameter("logincode", "%"+pageBean.getParams().get("keyword")+"%")
					.setParameter("email", "%"+pageBean.getParams().get("keyword")+"%").
					getSingleResult();
			pageBean.setTotalrows(Integer.parseInt(count == null ? "0" : count.toString()));

			List result = entityManager.createQuery("select account from AccountAdmin account where account.logincode like :logincode or account.email like :email")
					.setParameter("logincode", "%"+(pageBean.getParams().get("keyword")==null?"":pageBean.getParams().get("keyword"))+"%")
					.setParameter("email", "%"+(pageBean.getParams().get("keyword")==null?"":pageBean.getParams().get("keyword"))+"%").setFirstResult(pageBean.getStartrows()).setMaxResults(pageBean.getPageSize()).getResultList();
			// List<Map<String,Object>> tresult = new
			// ArrayList<Map<String,Object>>();
			// for(Account account : result){
			// tresult.add(BeanUtils.describe(account));
			// }
			pageBean.setResult(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pageBean;
	}

	public boolean add(Account account) {
		if(account instanceof AccountAdmin){
			entityManager.persist((AccountAdmin)account);
		}else if(account instanceof AccountWeb){
			entityManager.persist((AccountWeb)account);
		}
		return account.getId() == null ? false : true;
	}

	public AccountAdmin get(String id, Class<AccountAdmin> clazz) {
		return entityManager.find(clazz, id);
	}

	public boolean modify(AccountAdmin accountAdmin) {
		entityManager.merge(accountAdmin);
		return true;
	}

	public boolean remove(String id) {
		AccountAdmin admin = entityManager.find(AccountAdmin.class, id);
		entityManager.remove(admin);
		return true;
	}

}
