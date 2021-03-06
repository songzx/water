package com.water.metamodel.account;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SecondaryTable;
import javax.persistence.SecondaryTables;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.cxf.staxutils.DepthXMLStreamReader;
import org.hibernate.annotations.GenericGenerator;

import com.water.metamodel.authz.Role;
import com.water.metamodel.tree.Category;

/**
 * 
 * @author 0000
 * @date Aug 25, 2013
 * @version V1.0
 */
@Entity
@Table(name = "AUTH_ACCOUNT")
/*
 * @SecondaryTables({
 * 
 * @SecondaryTable(name="AUTH_ACCOUNT_ADMIN",pkJoinColumns=@PrimaryKeyJoinColumn(
 * name="id",referencedColumnName="id")),
 * 
 * @SecondaryTable(name="AUTH_ACCOUNT_WEB",pkJoinColumns=@PrimaryKeyJoinColumn(
 * referencedColumnName="id",name="id")) })
 */
@Inheritance(strategy = InheritanceType.JOINED)
public class Account implements Serializable {
	public static final int ACCOUNT_TYPE_ADMIN = 0;
	public static final int ACCOUNT_TYPE_WEB_PERSON = 1;
	public static final int ACCOUNT_TYPE_WEB_ENTERPRISE = 2;

	private final static int ACCOUNT_STATUS_DOWN = 0;// 禁用
	private final static int ACCOUNT_STATUS_UP = 1;// 雇用
	private final static int ACCOUNT_STATUS_DATED = 2;// 过期
	private final static int ACCOUNT_STATUS_REMOVE = 3;// 删除

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(length = 50)
	private String id;
	@Column(nullable = false, unique = true, length = 50)
	private String logincode;// 帐号
	@Column(length = 50)
	private String username;
	@Column(length = 50)
	private String aliasname;// 别名
	@Column(nullable = false, length = 50)
	private String loginpasswd;// 密码
	@Column(nullable = false)
	private int accounttype;// 帐号类型
	@Column(nullable = false)
	private long logincount = 0;// 登陆次数
	@Column(nullable = false)
	private int accountstatus = 1;// 帐号状态
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastlogindate = new Date();
	private String accountfrom = "water";// 默认注册来源“water”
	@Temporal(TemporalType.TIMESTAMP)
	private Date activestartdate;
	@Temporal(TemporalType.TIMESTAMP)
	private Date activeenddate;

	private String creater;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false)
	private Date createdate = new Date();
	private String updater;
	private Date updatedate;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "account", cascade = { CascadeType.PERSIST, CascadeType.REMOVE })
	private List<AccountLog> accountLogs = new ArrayList<AccountLog>();;

	@ManyToMany(fetch = FetchType.EAGER, mappedBy = "accounts")
	private List<Role> roles = new ArrayList<Role>();

	@ManyToOne(fetch = FetchType.LAZY)
	private Department department;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getAliasname() {
		return aliasname;
	}

	public void setAliasname(String aliasname) {
		this.aliasname = aliasname;
	}

	public int getAccounttype() {
		return accounttype;
	}

	public void setAccounttype(int accounttype) {
		this.accounttype = accounttype;
	}

	public long getLogincount() {
		return logincount;
	}

	public void setLogincount(long logincount) {
		this.logincount = logincount;
	}

	public int getAccountstatus() {
		return accountstatus;
	}

	public void setAccountstatus(int accountstatus) {
		this.accountstatus = accountstatus;
	}

	public String getLogincode() {
		return logincode;
	}

	public void setLogincode(String logincode) {
		this.logincode = logincode;
	}

	public String getLoginpasswd() {
		return loginpasswd;
	}

	public void setLoginpasswd(String loginpasswd) {
		this.loginpasswd = loginpasswd;
	}

	public Date getLastlogindate() {
		return lastlogindate;
	}

	public void setLastlogindate(Date lastlogindate) {
		this.lastlogindate = lastlogindate;
	}

	public String getAccountfrom() {
		return accountfrom;
	}

	public void setAccountfrom(String accountfrom) {
		this.accountfrom = accountfrom;
	}

	public String getCreater() {
		return creater;
	}

	public void setCreater(String creater) {
		this.creater = creater;
	}

	public Date getCreatedate() {
		return createdate;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}

	public String getUpdater() {
		return updater;
	}

	public void setUpdater(String updater) {
		this.updater = updater;
	}

	public Date getUpdatedate() {
		return updatedate;
	}

	public void setUpdatedate(Date updatedate) {
		this.updatedate = updatedate;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<AccountLog> getAccountLogs() {
		return accountLogs;
	}

	public void setAccountLogs(List<AccountLog> accountLogs) {
		this.accountLogs = accountLogs;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Date getActivestartdate() {
		return activestartdate;
	}

	public void setActivestartdate(Date activestartdate) {
		this.activestartdate = activestartdate;
	}

	public Date getActiveenddate() {
		return activeenddate;
	}

	public void setActiveenddate(Date activeenddate) {
		this.activeenddate = activeenddate;
	}
}
