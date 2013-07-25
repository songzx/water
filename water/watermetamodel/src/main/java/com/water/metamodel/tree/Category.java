package com.water.metamodel.tree;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

/**
 * 树，栏目
 * 
 * @author 0000
 * @date Jul 17, 2013
 * @version V1.0
 */
@Entity
@Table(name = "WATER_CATEGORY")
public class Category implements Serializable {
	public static int CATEGORYTYPE_CATEGORY = 0;// 普通栏目
	public static int CATEGORYTYPE_MENU = 1;// 菜单

	public static int CATEGORYSTATUS_DISABLED = 0;// 禁用
	public static int CATEGORYSTATUS_ENABLE = 1;// 启用

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "uuid-hex")
	@Column(length = 50)
	private String id;
	@Column(nullable = false, unique = true, length = 50)
	private String code;// 代码
	@Column(length = 100)
	private String name;// 帐号
	@Column(length = 50)
	private String abbreviation;// 简称
	private String status;
	private String categorytype;

	@Column(length = 100)
	private String imgsrc;
	@Column(length = 100)
	private String url;

	@Column(nullable = false, length = 50)
	private String parentid = "0";

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAbbreviation() {
		return abbreviation;
	}

	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}

	public String getStatus() {
		return status;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCategorytype() {
		return categorytype;
	}

	public void setCategorytype(String categorytype) {
		this.categorytype = categorytype;
	}

	public String getImgsrc() {
		return imgsrc;
	}

	public void setImgsrc(String imgsrc) {
		this.imgsrc = imgsrc;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getParentid() {
		return parentid;
	}

	public void setParentid(String parentid) {
		this.parentid = parentid;
	}
}
