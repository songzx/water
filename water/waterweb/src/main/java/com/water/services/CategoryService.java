package com.water.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.water.daos.CategoryDao;
import com.water.metamodel.tree.Category;

@Service
public class CategoryService implements ICategoryService{

	@Resource
	private CategoryDao categoryDao;
	
	@Override
	public List<Category> cagetorylistbyaccount(String accountid,String parentid) {
		return this.getCategoryDao().cagetorylistbyaccount(accountid,parentid);
	}

	public CategoryDao getCategoryDao() {
		return categoryDao;
	}

	public void setCategoryDao(CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}

}
