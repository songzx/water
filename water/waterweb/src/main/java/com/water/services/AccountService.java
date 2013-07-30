package com.water.services;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

import com.water.basictool.MD5Util;
import com.water.metamodel.account.Account;
import com.water.metamodel.account.AccountLog;

public class AccountService implements IAccountService {

	@Override
	public List<Account> getAccountList(EntityManager entityManager) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Account getAccount(String id, EntityManager entityManager) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Account getAccount(String logincode, String loginpasswd, EntityManager entityManager) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updateLogindate(String id, EntityManager entityManager) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateLogincount(String id, EntityManager entityManager) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean recodelog(AccountLog accountLog, EntityManager entityManager) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Account createAccount(Account account, EntityManager entityManager) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean checklogincode(String logincode, EntityManager entityManager) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Account> getAccountPage(int startrow, int endrow) {
		// TODO Auto-generated method stub
		return null;
	}


}
