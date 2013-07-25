package com.water.metamodel.account;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * 用户对象
 * 
 * @todo TODO
 * @author 0000
 * @date Feb 14, 2013 1:45:30 AM
 * @version 1.0
 * @classname User
 */
@Entity
@Table(name = "AUTH_ACCOUNT")
public class Account implements Serializable {
	public static final int ACCOUNT_TYPE_ADMIN = 0;
	public static final int ACCOUNT_TYPE_WEB_PERSON = 1;
	public static final int ACCOUNT_TYPE_WEB_ENTERPRISE = 2;

	private final static int ACCOUNT_STATUS_DOWN = 0;// 禁用
	private final static int ACCOUNT_STATUS_UP = 1;// 雇用
	private final static int ACCOUNT_STATUS_DATED = 2;// 过期
	private final static int ACCOUNT_STATUS_REMOVE = 3;// 删除

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO,generator="uuid-hex")
	@Column(length = 50)
	private String id ;
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
	private Date lastlogindate= new Date();
	private String accountfrom = "water";// 默认注册来源“water”

	private String creater;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false)
	private Date createdate = new Date();
	private String updater;
	private Date updatedate;

	@OneToOne(cascade = CascadeType.ALL)
	@MapsId
	private AccountAdmin accountAdmin;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@MapsId
	public AccountWeb accountWeb;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "account", cascade = { CascadeType.PERSIST, CascadeType.REMOVE })
	private Set<AccountLog> accountLogs = new HashSet<AccountLog>();;

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

	public AccountWeb getAccountWeb() {
		return accountWeb;
	}

	public void setAccountWeb(AccountWeb accountWeb) {
		this.accountWeb = accountWeb;
	}

	public Set<AccountLog> getAccountLogs() {
		return accountLogs;
	}

	public void setAccountLogs(Set<AccountLog> accountLogs) {
		this.accountLogs = accountLogs;
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

	public AccountAdmin getAccountAdmin() {
		return accountAdmin;
	}

	public void setAccountAdmin(AccountAdmin accountAdmin) {
		this.accountAdmin = accountAdmin;
	}

}