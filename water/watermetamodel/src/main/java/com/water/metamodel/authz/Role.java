package com.water.metamodel.authz;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.water.metamodel.account.Account;
import com.water.metamodel.tree.Category;

@Entity
@Table(name="AUTHZ_ROLE")
public class Role implements Serializable {
	public static int ROLE_TYPE_CATEGORY = 0;// url
	public static int ROLE_TYPE_RESOURCE = 1;// 数据过滤
	public static int ROLE_TYPE_DEPARTMENT = 2; //部门对应角色
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO,generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy = "uuid")
	@Column(length = 50)
	private String id;
	private int enabled;
	private String name;
	private String description;
	private String type;
	
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(name="AUTHZ_ACCOUNT_ROLE")
	private List<Account> accounts = new ArrayList<Account>();
	
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(name="AUTHZ_ROLE_RESOURCE")
	private List<Resource> resources = new ArrayList<Resource>();
	
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(name="AUTHZ_ROLE_CATEGORY")
	private List<Category> categories = new ArrayList<Category>();
	
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getEnabled() {
		return enabled;
	}
	public void setEnabled(int enabled) {
		this.enabled = enabled;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Account> getAccounts() {
		return accounts;
	}
	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public List<Resource> getResources() {
		return resources;
	}
	public void setResources(List<Resource> resources) {
		this.resources = resources;
	}
	public List<Category> getCategories() {
		return categories;
	}
	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}
}
