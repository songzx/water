package com.water.actions;

import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.junit.runner.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.water.basictool.OnlineAccount;
import com.water.basictool.ValidateUtil;
import com.water.metamodel.account.Account;
import com.water.metamodel.account.AccountAdmin;
import com.water.metamodel.account.AccountLog;
import com.water.metamodel.tree.Category;
import com.water.services.AccountService;
import com.water.services.IAccountService;

@Component("accountAction")
@Scope("prototype")
public class AccountAction extends ActionSupport {

	private static Logger logger = LoggerFactory.getLogger(AccountAction.class);
	@Resource
	private IAccountService accountService;
	private Account account;

	public String login() {
		//String logincode = ServletActionContext.getRequest().getParameter("logincode");
		//String loginpasswd = ServletActionContext.getRequest().getParameter("loginpasswd");
		
		Object obj = ServletActionContext.getRequest().getSession().getAttribute("loginaccount");
		//entityManager.createQuery("select category from Category category where category.parentid = '0'", Category.class).getResultList()
		if (obj != null) {
			// 获取用户的栏目权限
			String accountid = ServletActionContext.getRequest().getSession().getAttribute("accountid").toString();
			List<Category> categories = this.getAccountService().getCategories(accountid,"0");
			ServletActionContext.getRequest().setAttribute("categories", categories);
			return SUCCESS;
		}
		
		Account taccount = this.getAccountService().getAccount(account.getLogincode(), account.getLoginpasswd());
		if (taccount == null) {
			return "login";
		}
		List<Category> categories = this.getAccountService().getCategories(taccount.getId(),"0");
		ServletActionContext.getRequest().setAttribute("categories", categories);
		
		loginWebBusiness(taccount);
		return SUCCESS;
	}

	// 登陆之后，前台的业务处理
	private void loginWebBusiness(Account account) {
		Map<String, String> loginaccount = new HashMap<String, String>();
		loginaccount.put("id", account.getId());
		loginaccount.put("logincode", account.getLogincode());
		loginaccount.put("username", account.getUsername());
		loginaccount.put("logincount", account.getLogincount() + "");
		loginaccount.put("lastlogindate", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(account.getLastlogindate()));
		// loginaccount.put("regedittype", account.getRegedittype());
		// loginaccount.put("accountlevel",
		// account.getAccountlevel().getName());
		ServletActionContext.getRequest().getSession().setAttribute("loginaccount", loginaccount);
		ServletActionContext.getRequest().getSession().setAttribute("accountid",account.getId());
		
		// 添加在线人数
		if (ServletActionContext.getRequest().getSession().getAttribute("loginaccount") != null && null == ServletActionContext.getRequest().getSession().getAttribute("currentwebUser")) {
			String uuid = UUID.randomUUID().toString().replace("-", "");
			ServletActionContext.getRequest().getSession().setAttribute("currentwebUser", uuid);
			OnlineAccount.getInstance().addWebUser(new String[] { account.getId(), uuid, account.getUsername(), account.getLogincode(), System.currentTimeMillis() + "" });
		}
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

	
	public String list() throws Exception {
		PageBean pageBean = new PageBean();
		pageBean.setPageSize(ValidateUtil.getInstance().checkInt(ServletActionContext.getRequest().getParameter("pageSize"), 2, 100));
		pageBean.setCurPage(ValidateUtil.getInstance().checkInt(ServletActionContext.getRequest().getParameter("curPage"), 1));
		
		pageBean.getParams().put("keyword", ServletActionContext.getRequest().getParameter("keyword")==null?"":ServletActionContext.getRequest().getParameter("keyword"));
		this.getAccountService().list(pageBean);
		ServletActionContext.getRequest().setAttribute("pageBean", pageBean);
		return "list";
	}

	public String add() throws Exception {
		AccountAdmin accountAdmin = new AccountAdmin();
		//BeanUtils.populate(account, ServletActionContext.getRequest().getParameterMap());
		accountAdmin.setLogincode(ServletActionContext.getRequest().getParameter("logincode"));
		accountAdmin.setLoginpasswd(ServletActionContext.getRequest().getParameter("loginpasswd"));
		
		accountAdmin.setEmail(ServletActionContext.getRequest().getParameter("email"));
		
		boolean flag = this.getAccountService().add(accountAdmin);
		return flag ? list() : "add";
		
	}

	public String premodify() throws Exception {
		String id = ServletActionContext.getRequest().getParameter("id");
		AccountAdmin accountAdmin = this.getAccountService().get(id,AccountAdmin.class);
		ServletActionContext.getRequest().setAttribute("accountAdmin", accountAdmin);
		return "modify";
	}

	public String modify() throws Exception {
		AccountAdmin accountAdmin = new AccountAdmin();
		//BeanUtils.populate(account, ServletActionContext.getRequest().getParameterMap());
		
		accountAdmin.setId(ServletActionContext.getRequest().getParameter("id"));
		accountAdmin.setLogincode(ServletActionContext.getRequest().getParameter("logincode"));
		accountAdmin.setLoginpasswd(ServletActionContext.getRequest().getParameter("loginpasswd"));
		
		accountAdmin.setEmail(ServletActionContext.getRequest().getParameter("email"));
		
		boolean flag = this.getAccountService().modify(accountAdmin);
		return flag ? list() : "modify";
	}

	public String read() throws Exception {
		String id = ServletActionContext.getRequest().getParameter("id");
		AccountAdmin accountAdmin = this.getAccountService().get(id,AccountAdmin.class);
		ServletActionContext.getRequest().setAttribute("accountAdmin", accountAdmin);
		return "read";
	}
	public String remove() throws Exception {
		String id = ServletActionContext.getRequest().getParameter("id");
		boolean flag = this.getAccountService().remove(id);
		return flag ? list() : "remove";
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
		if (obj != null) {
			request.getRequestDispatcher("/admin/index.jsp").forward(request, response);
			return;
		}

		String logincode = request.getParameter("logincode");
		String loginpasswd = request.getParameter("loginpasswd");
		String username = request.getParameter("username");
		String idcard = request.getParameter("idcard");
		String regedittype = request.getParameter("regedittype");

		// 组装实体类
		/*
		 * Account account = null; Person person = null; if
		 * (Account.ACCOUNT_REGEDITTYPE_PERSON.equalsIgnoreCase(regedittype)) {
		 * person = new Person(); person.setIdcard(idcard);
		 * 
		 * person.setLogincode(logincode); person.setCreatedate(new Date());
		 * person.setLastlogindate(new Date()); person.setUsername(username);
		 * person.setLogincount(1); person.setLoginpasswd(loginpasswd);
		 * person.setRegedittype(regedittype);
		 * person.setStatus(Account.ACCOUNT_STATUS_ENABLE);
		 * person.setUpdatedate(new Date()); account = person; } else if
		 * (Account
		 * .ACCOUNT_REGEDITTYPE_ENTERPRISE.equalsIgnoreCase(regedittype)) { //
		 * account.setEnterprise(enterprise); } else { logger.error("注册类型非法：" +
		 * regedittype);
		 * request.getRequestDispatcher("/error/500.jsp").forward(request,
		 * response); return; } //AccountLevel accountLevel = new
		 * AccountLevel(); //accountLevel.setName("一级用户");
		 * //account.setAccountlevel(accountLevel);
		 * 
		 * person = (Person)accountService.createAccount(person, entityManager);
		 * if (account != null) { loginWebBusiness(request, response, account);
		 * }
		 */
	}


	public IAccountService getAccountService() {
		return accountService;
	}

	public void setAccountService(IAccountService accountService) {
		this.accountService = accountService;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}
}
