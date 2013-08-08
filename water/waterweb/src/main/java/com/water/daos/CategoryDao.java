package com.water.daos;

import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.water.metamodel.account.Account;
import com.water.metamodel.tree.Category;

@Repository
public class CategoryDao extends JpaBasiceDao{

	@Override
	@PersistenceContext(unitName = "authorityunit")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}

	public List<Category> cagetorylistbyaccount(String accountid,String parentid) {
		if(parentid == null || "".equals(parentid)){
			return entityManager.createQuery("select category from Account account join account.categories category where account.id = :accountid ", Category.class).setParameter("accountid", accountid).getResultList();
		}
		List<Category> categorys = entityManager.createQuery("select category from Account account join account.categories category where account.id = :accountid and category.parentid =:parentid", Category.class).setParameter("parentid", parentid).setParameter("accountid", accountid).getResultList();
		
		
		return categorys;
		/*CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		// where,select,group by,having,order by,distinst
		CriteriaQuery criteriaQuery = criteriaBuilder.createQuery(Category.class);
		// 左关联，右关联，交叉关联
		Root root = criteriaQuery.from(Category.class);
		root.j

		// 只实现and,String 对象，但是日期及其它对象实现不了。
		Predicate predicates[] = new Predicate[params.size()];
		int index = 0;
		for (Iterator<String> it = params.keySet().iterator(); it.hasNext();) {
			String key = it.next();
			predicates[index] = criteriaBuilder.equal(root.get(key), params.get(key));
		}
		criteriaQuery.select(root).where(criteriaBuilder.and(predicates));

		return entityManager.createQuery(criteriaQuery).getResultList();*/
		
	}

	
}
