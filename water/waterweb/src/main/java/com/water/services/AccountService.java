package com.water.services;

import java.util.List;

import javax.persistence.EntityManager;

import com.water.metamodel.account.Account;
import com.water.metamodel.account.AccountLog;


/**
 * 如何组装update方法；即业务方法包含多个基础方法，如何去衡量？？
 * @author 0000
 * @date Apr 25, 2013
 * @version V1.0
 */
public interface AccountService {

	List<Account> getAccountList(EntityManager entityManager);
	
	Account getAccount(String id,EntityManager entityManager);
	
	Account getAccount(String logincode,String loginpasswd,EntityManager entityManager);

	boolean updateLogindate(String id,EntityManager entityManager);
	boolean updateLogincount(String id,EntityManager entityManager);
	boolean recodelog(AccountLog accountLog,EntityManager entityManager);

	Account createAccount(Account account,EntityManager entityManager);
	
	boolean checklogincode(String logincode,EntityManager entityManager);
}
