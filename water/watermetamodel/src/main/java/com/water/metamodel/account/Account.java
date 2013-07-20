package com.water.metamodel.account;

import java.io.Serializable;
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
	private final static int ACCOUNT_TYPE_WEB=1;
	private final static int ACCOUNT_TYPE_ADMIN=0;
	
	private final static int ACCOUNT_STATUS_DOWN=0;//禁用
	private final static int ACCOUNT_STATUS_UP=1;//雇用
	private final static int ACCOUNT_STATUS_DATED=2;//过期
	private final static int ACCOUNT_STATUS_REMOVE=3;//删除
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(length=50)
	private String id;
	@Column(nullable=false,unique=true,length=100)
	private String name;//帐号
	@Column(nullable=false,length=20)
	private String aliasname;//别名
	@Column(nullable=false,length=20)
	private String passwd;//密码
	@Column(nullable=false)
	private int accounttype;//帐号类型
	@Column(nullable=false)
	private long logincount = 0;//登陆次数
	@Column(nullable=false)
	private int accountstatus = 1;//帐号状态
	
	
	
	@OneToOne(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@MapsId
	public AccountAdmin accountAdmin;
	
	@OneToOne(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@MapsId
	public AccountWeb accountWeb;
	
	@OneToMany(fetch=FetchType.LAZY,mappedBy="account",cascade=CascadeType.REMOVE)
	private Set<AccountLog> accountLogs;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAliasname() {
		return aliasname;
	}

	public void setAliasname(String aliasname) {
		this.aliasname = aliasname;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
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

	public AccountAdmin getAccountAdmin() {
		return accountAdmin;
	}

	public void setAccountAdmin(AccountAdmin accountAdmin) {
		this.accountAdmin = accountAdmin;
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
	
}
