package com.water.actions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PageBean {

	private Map<String, String> params = new HashMap<String, String>();
	private int curPage = 1;
	private int pageSize = 10;
	private int totalPages = 1;
	private int totalrows = 0;
	private int startrows = 0;
	private int endrows = 0; 
	private Map<String, String> keys = new HashMap<String, String>();
	private List<Object> result = new ArrayList<Object>();

	public Map<String, String> getParams() {
		return params;
	}

	public void setParams(Map<String, String> params) {
		this.params = params;
	}

	public int getCurPage() {
		curPage = curPage == 0 ? 1 : curPage;
		return curPage;
	}

	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}

	public int getPageSize() {
		pageSize = 2;
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalPages() {
		totalPages = totalrows % pageSize == 0 ? totalrows / pageSize : totalrows / pageSize + 1;
		return totalPages == 0 ? 1 : totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public int getStartrows() {
		startrows = (curPage - 1) * pageSize;
		return startrows;
	}

	public void setStartrows(int startrows) {
		this.startrows = startrows;
	}

	public int getEndrows() {
		endrows = curPage * pageSize;
		return endrows > totalPages ? totalPages : endrows;
	}

	public void setEndrows(int endrows) {
		this.endrows = endrows;
	}

	public List<Object> getResult() {
		return result;
	}

	public void setResult(List<Object> result) {
		this.result = result;
	}

	public Map<String, String> getKeys() {
		return keys;
	}

	public void setKeys(Map<String, String> keys) {
		this.keys = keys;
	}

	public int getTotalrows() {
		return totalrows;
	}

	public void setTotalrows(int totalrows) {
		this.totalrows = totalrows;
	}

}
