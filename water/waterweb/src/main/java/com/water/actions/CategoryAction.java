package com.water.actions;

import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionSupport;
import com.water.basictool.ValidateUtil;
import com.water.metamodel.tree.Category;
import com.water.services.ICategoryService;

@Component("categoryAction")
@Scope("prototype")
public class CategoryAction extends ActionSupport {

	private static Logger logger = LoggerFactory.getLogger(CategoryAction.class);

	private Category category;
	@Resource
	private ICategoryService categoryService;

	/**
	 * 根据用户权限获取用户栏目信息
	 * 
	 * @param request
	 * @param response
	 * @param entityManager
	 * @throws Exception
	 */
	public String cagetorylistbyaccount() throws Exception {
		Object accountid = ServletActionContext.getRequest().getSession().getAttribute("accountid");
		String parentid = (String) ServletActionContext.getRequest().getParameter("parentid");
		if (accountid == null) {
			return "nologin";
		}

		List<Category> categorys = categoryService.cagetorylistbyaccount(accountid.toString(), parentid);
		ServletActionContext.getRequest().setAttribute("categorys", categorys);
		return SUCCESS;
	}

	/**
	 * 分页查询
	 * 
	 * @return
	 * @throws Exception
	 */
	public String searchcategorytopage() throws Exception {
		Object accountid = ServletActionContext.getRequest().getSession().getAttribute("accountid");
		String parentid = (String) ServletActionContext.getRequest().getParameter("parentid");
		if (accountid == null) {
			return "nologin";
		}

		List<Category> categorys = categoryService.cagetorylistbyaccount(accountid.toString(), parentid);
		ServletActionContext.getRequest().setAttribute("categorys", categorys);
		return SUCCESS;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public ICategoryService getCategoryService() {
		return categoryService;
	}

	public void setCategoryService(ICategoryService categoryService) {
		this.categoryService = categoryService;
	}

	public String list(){
		PageBean pageBean = new PageBean();
		pageBean.setPageSize(ValidateUtil.getInstance().checkInt(ServletActionContext.getRequest().getParameter("pageSize"), 2, 100));
		pageBean.setCurPage(ValidateUtil.getInstance().checkInt(ServletActionContext.getRequest().getParameter("curPage"), 1));
		pageBean.getParams().putAll(ServletActionContext.getRequest().getParameterMap());
		
		this.getCategoryService().list(pageBean);
		ServletActionContext.getRequest().setAttribute("pageBean", pageBean);
		return "list";
	}
}
