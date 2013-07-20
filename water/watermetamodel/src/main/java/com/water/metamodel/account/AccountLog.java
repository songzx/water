package com.water.metamodel.account;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "AUTH_ACCOUNT_LOG")
public class AccountLog {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(length = 50)
	private String id;
	private Date logindate;
	private Date logoutdate;
	@Column(length = 15)
	private String loginip;
	@Column(length = 20)
	private String loginmac;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "accountid", nullable = false)
	@Column(length=50)
	private Account account;

	@OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY, mappedBy = "accountLog")
	private Set<AccountOperatorLog> accountOperatorLogs;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getLogindate() {
		return logindate;
	}

	public void setLogindate(Date logindate) {
		this.logindate = logindate;
	}

	public Date getLogoutdate() {
		return logoutdate;
	}

	public void setLogoutdate(Date logoutdate) {
		this.logoutdate = logoutdate;
	}

	public String getLoginip() {
		return loginip;
	}

	public void setLoginip(String loginip) {
		this.loginip = loginip;
	}

	public String getLoginmac() {
		return loginmac;
	}

	public void setLoginmac(String loginmac) {
		this.loginmac = loginmac;
	}

	public Set<AccountOperatorLog> getAccountOperatorLogs() {
		return accountOperatorLogs;
	}

	public void setAccountOperatorLogs(Set<AccountOperatorLog> accountOperatorLogs) {
		this.accountOperatorLogs = accountOperatorLogs;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

}
