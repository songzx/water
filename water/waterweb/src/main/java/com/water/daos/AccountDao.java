package com.water.daos;

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

import org.springframework.stereotype.Repository;

import com.water.metamodel.account.Account;
import com.water.metamodel.tree.Category;

@Repository
public class AccountDao extends JpaBasiceDao{

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

		criteriaQuery.select(root)
		.where(criteriaBuilder.and(plogincode, ploginpasswd))
		.orderBy(criteriaBuilder.desc(root.get("id")));
		List<Account> accounts = entityManager.createQuery(criteriaQuery).getResultList();
		if(accounts == null || accounts.size() == 0){
			return null;
		}
		
		return accounts.get(0);
	}

	public List<Category> getCategories(String accountid, String parentid) {
		
		List<Category> categorys = entityManager.createQuery("select category from Category category where category.parentid = '0'", Category.class).getResultList();
		return categorys;
	}

}
