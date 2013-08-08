package com.water.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.water.actions.PageBean;
import com.water.daos.AccountDao;
import com.water.daos.CategoryDao;
import com.water.metamodel.account.Account;
import com.water.metamodel.account.AccountAdmin;
import com.water.metamodel.account.AccountLog;
import com.water.metamodel.tree.Category;

@Service
public class AccountService implements IAccountService {

	@Resource
	private AccountDao accountDao;
	@Resource
	private CategoryDao categoryDao;

	public AccountDao getAccountDao() {
		return accountDao;
	}

	public void setAccountDao(AccountDao accountDao) {
		this.accountDao = accountDao;
	}

	@Override
	public List<Account> getAccountPage(int startrow, int endrow, Map<String, Object> params) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Account getAccount(String id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		return (Account) this.getAccountDao().getSingleObject(Account.class, params);
	}

	@Override
	public Account getAccount(String logincode, String loginpasswd) {
		return this.getAccountDao().getAccount(logincode, loginpasswd);
	}

	@Override
	public boolean updateLogindate(String id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateLogincount(String id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean recodelog(AccountLog accountLog) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Account createAccount(Account account) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean checklogincode(String logincode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Category> getCategories(String accountid, String parentid) {
		return this.getCategoryDao().cagetorylistbyaccount(accountid, parentid);
	}

	public CategoryDao getCategoryDao() {
		return categoryDao;
	}

	public void setCategoryDao(CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}

	@Override
	public PageBean list(PageBean pageBean) {
		return this.getAccountDao().list(pageBean);
	}

	@Override
	public boolean add(Account account) {
		return this.getAccountDao().add(account);
	}

	@Override
	public AccountAdmin get(String id, Class<AccountAdmin> clazz) {
		return this.getAccountDao().get(id,clazz);
	}

	
	@Override
	public boolean modify(AccountAdmin accountAdmin) {
		return this.getAccountDao().modify(accountAdmin);
	}

	@Override
	public boolean remove(String id) {
		return this.getAccountDao().remove(id);
	}

}
