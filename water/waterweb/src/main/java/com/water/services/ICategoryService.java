package com.water.services;

import java.util.List;

import com.water.actions.PageBean;
import com.water.metamodel.tree.Category;

public interface ICategoryService {

	/**
	 * 获取登陆用户的菜单权限或查询某用户的菜单权限
	 * 
	 * @return
	 */
	List<Category> cagetorylistbyaccount(String accountid,String parentid);

	PageBean list(PageBean pageBean);

}
