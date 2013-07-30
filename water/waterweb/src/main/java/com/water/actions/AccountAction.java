package com.water.actions;

import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
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

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.water.basictool.OnlineAccount;
import com.water.metamodel.account.Account;
import com.water.metamodel.account.AccountLog;
import com.water.metamodel.tree.Category;
import com.water.services.AccountService;


public class AccountAction extends ActionSupport {

	private static Logger logger = LoggerFactory.getLogger(AccountAction.class);


	/**
	 * 登陆
	 * 登陆的业务逻辑（记录日志，操作日志，操作权限（菜单，按键权限),角色等)通过spring进行注入
	 * @param request
	 * @param response
	 * @param entityManager
	 * @throws Exception
	 */
	public void login(HttpServletRequest request, HttpServletResponse response, EntityManager entityManager) throws Exception {
		Object obj = request.getSession().getAttribute("loginaccount");
		if(obj != null){
			//获取用户的栏目权限
			List<Category> categorys = entityManager.createQuery("select category from Category category where category.parentid = '0'", Category.class).getResultList();
			request.setAttribute("categorys", categorys);
			
			request.getRequestDispatcher("/admin/index.jsp").forward(request, response);
			return;
		}
		
		String logincode = request.getParameter("logincode");
		String loginpasswd = request.getParameter("loginpasswd");

		Account account = loginbusiness(logincode, loginpasswd, entityManager);
		if(account == null){
			request.getRequestDispatcher("/admin/login.jsp").forward(request, response);
			return;
		}
		
		//获取用户的栏目权限
		List<Category> categorys = entityManager.createQuery("select category from Category category where category.parentid = '0'", Category.class).getResultList();
		request.setAttribute("categorys", categorys);
		
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
		
		
		request.getRequestDispatcher("/admin/index.jsp").forward(request, response);
	}

	// 登陆业务逻辑
	private Account loginbusiness(String logincode, String loginpasswd, EntityManager entityManager) {
		return null;
	}

	/**
	 * 退出
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public String loginout() throws Exception {
		ServletActionContext.getRequest().getSession().removeAttribute("loginaccount");
		ServletActionContext.getRequest().getSession().removeAttribute("currentwebUser");
		ServletActionContext.getRequest().getSession().invalidate();
		return "loginout";
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
			request.getRequestDispatcher("/admin/index.jsp").forward(request, response);
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
		return;
	}
}
