package com.water.services;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;

import com.water.metamodel.account.Account;
import com.water.metamodel.account.AccountLog;
import com.water.metamodel.tree.Category;

/**
 * 如何组装update方法；即业务方法包含多个基础方法，如何去衡量？？
 * 
 * @author 0000
 * @date Apr 25, 2013
 * @version V1.0
 */
public interface IAccountService {

	/**
	 * 获取用户的分页数
	 * 
	 * @param startrow
	 * @param endrow
	 * @return
	 */
	List<Account> getAccountPage(int startrow, int endrow, Map<String, Object> params);

	Account getAccount(String id);

	Account getAccount(String logincode, String loginpasswd);

	boolean updateLogindate(String id);

	boolean updateLogincount(String id);

	boolean recodelog(AccountLog accountLog);

	Account createAccount(Account account);

	boolean checklogincode(String logincode);

	/**
	 * 获取用户权限的栏目列表，
	 * @param string
	 * @param parentid　parentid为null则获取所有用户权限的栏目
	 * @return
	 */
	List<Category> getCategories(String accountid,String parentid);
}
