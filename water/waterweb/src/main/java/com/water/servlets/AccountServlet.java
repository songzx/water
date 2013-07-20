package com.water.servlets;

import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.water.basictool.OnlineAccount;
import com.water.metamodel.account.Account;
import com.water.metamodel.account.AccountLog;
import com.water.services.AccountService;
import com.water.services.AccountServiceImpl;


public class AccountServlet extends HttpServlet {

	private EntityManager entityManager;
	private static AccountService accountService;
	private static EntityManagerFactory entityManagerFactory;
	private static Logger logger = LoggerFactory.getLogger(AccountServlet.class);

	@Override
	public void init() throws ServletException {
		super.init();
		accountService = new AccountServiceImpl();
		entityManagerFactory = Persistence.createEntityManagerFactory("com.ucap.datasource.web");
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String strmethod = request.getParameter("method");
		String strsingemethod = request.getParameter("singemethod");
		if (null == strmethod && null == strsingemethod) {
			response.getWriter().println("error : not exist method");
			return;
		}

		Class<?> clazz = null;
		try {
			clazz = this.getClass();
			if (null != strmethod) {
				entityManager = entityManagerFactory.createEntityManager();
				entityManager.getTransaction().begin();
				Method method = clazz.getMethod(strmethod, HttpServletRequest.class, HttpServletResponse.class, EntityManager.class);
				method.invoke(clazz.newInstance(), request, response, entityManager);
				entityManager.getTransaction().commit();
			} else if (null != strsingemethod) {
				Method method = clazz.getMethod(strsingemethod, HttpServletRequest.class, HttpServletResponse.class);
				method.invoke(clazz.newInstance(), request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
			entityManager.getTransaction().rollback();
			response.getWriter().println("error : execute method " + strmethod + " not success,please check your inputs");
			return;
		} finally {
			if (entityManager != null && entityManager.isOpen()) {
				entityManager.close();
			}
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

	/**
	 * 登陆
	 * 
	 * @param request
	 * @param response
	 * @param entityManager
	 * @throws Exception
	 */
	public void login(HttpServletRequest request, HttpServletResponse response, EntityManager entityManager) throws Exception {
		Object obj = request.getSession().getAttribute("loginaccount");
		if(obj != null){
			request.getRequestDispatcher("/account/index.jsp").forward(request, response);
			return;
		}
		
		String logincode = request.getParameter("logincode");
		String loginpasswd = request.getParameter("loginpasswd");

		Account account = loginbusiness(logincode, loginpasswd, entityManager);
		loginWebBusiness(request, response, account);
	}

	// 登陆之后，前台的业务处理
	private void loginWebBusiness(HttpServletRequest request, HttpServletResponse response, Account account) throws ServletException, IOException {
		Map<String, String> loginaccount = new HashMap<String, String>();
		loginaccount.put("id", account.getId());
		loginaccount.put("logincode", account.getLogincode());
		loginaccount.put("username", account.getUsername());
		loginaccount.put("logincount", account.getLogincount() + "");
		loginaccount.put("lastlogindate", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(account.getLastlogindate()));
		//loginaccount.put("regedittype", account.getRegedittype());
		//loginaccount.put("accountlevel", account.getAccountlevel().getName());
		request.getSession().setAttribute("loginaccount", loginaccount);

		//添加在线人数
		if(request.getSession().getAttribute("loginaccount") != null && null == request.getSession().getAttribute("currentwebUser")){
			String uuid = UUID.randomUUID().toString().replace("-", "");
			request.getSession().setAttribute("currentwebUser", uuid);
			OnlineAccount.getInstance().addWebUser(new String[]{account.getId(),uuid,account.getUsername(),account.getLogincode(),System.currentTimeMillis()+""});
		}
		
		request.getRequestDispatcher("/account/index.jsp").forward(request, response);
	}

	// 登陆业务逻辑
	private Account loginbusiness(String logincode, String loginpasswd, EntityManager entityManager) {
		// 业务逻辑，提到servlet里？？,还是新建一个业务逻辑层，而Account实体的基本操作推到下一层。？
		Account account = accountService.getAccount(logincode, loginpasswd, entityManager);
		accountService.updateLogincount(account.getId(), entityManager);
		accountService.updateLogindate(account.getId(), entityManager);

		AccountLog accountLog = new AccountLog();
		accountLog.setAccount(account);
		accountLog.setAccountname(account.getUsername());
		account.getAccountLogs().add(accountLog);

		return account;
	}

	/**
	 * 退出
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void loginout(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.getSession().removeAttribute("loginaccount");
		request.getSession().removeAttribute("currentwebUser");
		request.getSession().invalidate();
		request.getRequestDispatcher("/account/login.jsp").forward(request, response);
	}

	/**
	 * 注册用户
	 * 
	 * @param request
	 * @param response
	 * @param entityManager
	 * @throws Exception
	 */
	public void regedit(HttpServletRequest request, HttpServletResponse response, EntityManager entityManager) throws Exception {
		Object obj = request.getSession().getAttribute("loginaccount");
		if(obj != null){
			request.getRequestDispatcher("/account/index.jsp").forward(request, response);
			return;
		}
		
		String logincode = request.getParameter("logincode");
		String loginpasswd = request.getParameter("loginpasswd");
		String username = request.getParameter("username");
		String idcard = request.getParameter("idcard");
		String regedittype = request.getParameter("regedittype");

		// 组装实体类
		/*Account account = null;
		Person person = null;
		if (Account.ACCOUNT_REGEDITTYPE_PERSON.equalsIgnoreCase(regedittype)) {
			person = new Person();
			person.setIdcard(idcard);
			
			person.setLogincode(logincode);
			person.setCreatedate(new Date());
			person.setLastlogindate(new Date());
			person.setUsername(username);
			person.setLogincount(1);
			person.setLoginpasswd(loginpasswd);
			person.setRegedittype(regedittype);
			person.setStatus(Account.ACCOUNT_STATUS_ENABLE);
			person.setUpdatedate(new Date());
			account = person;
		} else if (Account.ACCOUNT_REGEDITTYPE_ENTERPRISE.equalsIgnoreCase(regedittype)) {
			// account.setEnterprise(enterprise);
		} else {
			logger.error("注册类型非法：" + regedittype);
			request.getRequestDispatcher("/error/500.jsp").forward(request, response);
			return;
		}
		//AccountLevel accountLevel = new AccountLevel();
		//accountLevel.setName("一级用户");
		//account.setAccountlevel(accountLevel);
		
		person = (Person)accountService.createAccount(person, entityManager);
		if (account != null) {
			loginWebBusiness(request, response, account);
		}*/
	}
	
	/**
	 * 登陆
	 * 
	 * @param request
	 * @param response
	 * @param entityManager
	 * @throws Exception
	 */
	public void list(HttpServletRequest request, HttpServletResponse response, EntityManager entityManager) throws Exception {
		List<Account> accounts = accountService.getAccountList(entityManager);
		request.setAttribute("accounts", accounts);
		request.getRequestDispatcher("/account/manger.jsp").forward(request, response);
		return;
	}
}
