package com.water.metamodel.account;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 操作日志，使用队列的形式进行；
 * 步骤:登陆系统后，有登陆日志ID，在帐号session有效期内把用户操作日志交给队列进行入库。退出后去掉日志ID，并记录退出日志
 * 
 * @author 0000
 * @date Jul 20, 2013
 * @version V1.0
 */
@Entity
@Table(name = "AUTH_OPERATOR_LOG")
public class AccountOperatorLog {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO,generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy = "uuid")
	@Column(length = 50)
	private String id;
	private String logdesc;
	private String logresult;
	private String runsql;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "accountlogid", nullable = false)
	private AccountLog accountLog;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public AccountLog getAccountLog() {
		return accountLog;
	}

	public void setAccountLog(AccountLog accountLog) {
		this.accountLog = accountLog;
	}

	public String getLogdesc() {
		return logdesc;
	}

	public void setLogdesc(String logdesc) {
		this.logdesc = logdesc;
	}

	public String getLogresult() {
		return logresult;
	}

	public void setLogresult(String logresult) {
		this.logresult = logresult;
	}

	public String getRunsql() {
		return runsql;
	}

	public void setRunsql(String runsql) {
		this.runsql = runsql;
	}
}
