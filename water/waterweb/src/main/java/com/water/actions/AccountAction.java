package com.water.actions;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionSupport;
import com.water.basictool.OnlineAccount;
import com.water.basictool.ValidateUtil;
import com.water.metamodel.account.Account;
import com.water.metamodel.account.AccountAdmin;
import com.water.metamodel.tree.Category;
import com.water.services.IAccountService;

@Component("accountAction")
@Scope("prototype")
public class AccountAction extends ActionSupport {

	private static Logger logger = LoggerFactory.getLogger(AccountAction.class);

	@Resource
	private IAccountService accountService;
	private Account account;

	public String login() {
		logger.info("登陆！！１");
		
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
		ServletActionContext.getRequest().getSession().setAttribute("accountid", account.getId());

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

		pageBean.getParams().putAll(ServletActionContext.getRequest().getParameterMap());
		this.getAccountService().list(pageBean);
		ServletActionContext.getRequest().setAttribute("pageBean", pageBean);
		return "list";
	}

	public String add() throws Exception {
		AccountAdmin accountAdmin = new AccountAdmin();
		// BeanUtils.populate(account,
		// ServletActionContext.getRequest().getParameterMap());
		accountAdmin.setLogincode(ServletActionContext.getRequest().getParameter("logincode"));
		accountAdmin.setLoginpasswd(ServletActionContext.getRequest().getParameter("loginpasswd"));

		accountAdmin.setEmail(ServletActionContext.getRequest().getParameter("email"));

		boolean flag = this.getAccountService().add(accountAdmin);
		return flag ? list() : "add";

	}

	public String premodify() throws Exception {
		String id = ServletActionContext.getRequest().getParameter("id");
		AccountAdmin accountAdmin = this.getAccountService().get(id, AccountAdmin.class);
		ServletActionContext.getRequest().setAttribute("accountAdmin", accountAdmin);
		return "modify";
	}

	public String modify() throws Exception {
		AccountAdmin accountAdmin = new AccountAdmin();
		// BeanUtils.populate(account,
		// ServletActionContext.getRequest().getParameterMap());

		accountAdmin.setId(ServletActionContext.getRequest().getParameter("id"));
		accountAdmin.setLogincode(ServletActionContext.getRequest().getParameter("logincode"));
		accountAdmin.setLoginpasswd(ServletActionContext.getRequest().getParameter("loginpasswd"));

		accountAdmin.setEmail(ServletActionContext.getRequest().getParameter("email"));

		boolean flag = this.getAccountService().modify(accountAdmin);
		return flag ? list() : "modify";
	}

	public String read() throws Exception {
		String id = ServletActionContext.getRequest().getParameter("id");
		AccountAdmin accountAdmin = this.getAccountService().get(id, AccountAdmin.class);
		ServletActionContext.getRequest().setAttribute("accountAdmin", accountAdmin);
		return "read";
	}

	public String remove() throws Exception {
		String id = ServletActionContext.getRequest().getParameter("id");
		boolean flag = this.getAccountService().remove(id);
		return flag ? list() : "remove";
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
