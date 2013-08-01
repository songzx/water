package com.water.daos;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class JpaBasiceDao {
	protected EntityManager entityManager;

	/**
	 * 动态获取用户对象
	 * 
	 * @param clazz
	 * @param params
	 * @return
	 */
	public List getObject(Class clazz, Map<String, Object> params) {
		if (params == null || params.size() <= 0) {
			return null;
		}

		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		// where,select,group by,having,order by,distinst
		CriteriaQuery criteriaQuery = criteriaBuilder.createQuery(clazz);
		// 左关联，右关联，交叉关联
		Root root = criteriaQuery.from(clazz);

		// 只实现and,String 对象，但是日期及其它对象实现不了。
		Predicate predicates[] = new Predicate[params.size()];
		int index = 0;
		for (Iterator<String> it = params.keySet().iterator(); it.hasNext();) {
			String key = it.next();
			predicates[index] = criteriaBuilder.equal(root.get(key), params.get(key));
		}
		criteriaQuery.select(root).where(criteriaBuilder.and(predicates));

		return entityManager.createQuery(criteriaQuery).getResultList();
	}
	
	/**
	 * 动态获取用户对象
	 * 
	 * @param clazz
	 * @param params
	 * @return
	 */
	public Object getSingleObject(Class clazz, Map<String, Object> params) {
		if (params == null || params.size() <= 0) {
			return null;
		}

		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		// where,select,group by,having,order by,distinst
		CriteriaQuery criteriaQuery = criteriaBuilder.createQuery(clazz);
		// 左关联，右关联，交叉关联
		Root root = criteriaQuery.from(clazz);

		// 只实现and,String 对象，但是日期及其它对象实现不了。
		Predicate predicates[] = new Predicate[params.size()];
		int index = 0;
		for (Iterator<String> it = params.keySet().iterator(); it.hasNext();) {
			String key = it.next();
			predicates[index] = criteriaBuilder.equal(root.get(key), params.get(key));
		}
		criteriaQuery.select(root).where(criteriaBuilder.and(predicates));

		return entityManager.createQuery(criteriaQuery).getSingleResult();
	}

	/**
	 * 查询单表
	 * 动态条件，模糊查询
	 * @param clazz
	 * @param startrow
	 * @param endrow
	 * @param params
	 * @return
	 */
	public List searchObject(Class clazz, int startrow, int endrow, Map<String, Object> params) {
		return null;
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
}
