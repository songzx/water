package com.water.services;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

import com.water.basictool.MD5Util;
import com.water.metamodel.account.Account;
import com.water.metamodel.account.AccountLog;

public class AccountServiceImpl implements AccountService {

	@Override
	public List<Account> getAccountList(EntityManager entityManager) {
		return entityManager.createQuery("select account from Account account", Account.class).getResultList();
	}

	@Override
	public Account getAccount(String id, EntityManager entityManager) {
		/*Account account = entityManager.find(Person.class, id);
		if (account == null) {
			account = entityManager.find(Enterprise.class, id);
		}
		return account;*/
		return null;
	}

	@Override
	public Account getAccount(String logincode, String loginpasswd, EntityManager entityManager) {
		/*Account account = entityManager.createQuery("select account from Person account where account.logincode = ?", Person.class).setParameter(1, logincode).getSingleResult();
		if(account == null){
			account = entityManager.createQuery("select account from Enterprise account where account.logincode = ?", Enterprise.class).setParameter(1, logincode).getSingleResult();
		}
		
		if(account != null){
			if(account instanceof Person){
				loginpasswd = MD5Util.getInstance().compileWithSalt(loginpasswd, ((Person)account).getIdcard());
				account = entityManager.createQuery("select account from Person account where account.logincode = ? and account.loginpasswd = ?", Person.class).setParameter(1, logincode).setParameter(2, loginpasswd).getSingleResult();
			}else if(account instanceof Enterprise){
				loginpasswd = MD5Util.getInstance().compileWithSalt(loginpasswd, ((Enterprise)account).getOrganizationcode());
				account = entityManager.createQuery("select account from Enterprise account where account.logincode = ? and account.loginpasswd = ?", Enterprise.class).setParameter(1, logincode).setParameter(2, loginpasswd).getSingleResult();
			}
		}
		return account;*/
		return null;
	}

	@Override
	public boolean updateLogindate(String id, EntityManager entityManager) {
		entityManager.createQuery("update Account account set account.lastlogindate = :lastlogindate").setParameter("lastlogindate", new Date()).executeUpdate();
		return false;
	}

	@Override
	public boolean updateLogincount(String id, EntityManager entityManager) {
		entityManager.createQuery("update Account account set account.logincount = account.logincount + 1").executeUpdate();
		return false;
	}

	@Override
	public boolean recodelog(AccountLog accountLog, EntityManager entityManager) {
		/*String runsql = "在EntityManager作文章";
		accountLog.setRunsql(runsql);
		return false;*/
		return false;
	}

	@Override
	public Account createAccount(Account account, EntityManager entityManager) {
		/*if (account instanceof Person) {
			Person person = (Person)account;
			person.setLoginpasswd(MD5Util.getInstance().compileWithSalt(person.getLoginpasswd(), person.getIdcard()));
			entityManager.persist((Person) account);
		} else if (account instanceof Enterprise) {
			Enterprise enterprise = (Enterprise)account;
			enterprise.setLoginpasswd(MD5Util.getInstance().compileWithSalt(enterprise.getLoginpasswd(), enterprise.getOrganizationcode()));
			entityManager.persist((Enterprise) account);
		}
		return account;*/
		return null;
	}


	@Override
	public boolean checklogincode(String logincode, EntityManager entityManager) {
		String result = entityManager.createQuery("select count(account) from Account account where account.logincode = ?").setParameter(1, logincode).getSingleResult().toString();
		return "0".equals(result) ? false : true;
	}

}
