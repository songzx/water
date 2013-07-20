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

@Entity
@Table(name = "AUTH_OPERATOR_LOG")
public class AccountOperatorLog {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(length = 50)
	private String id;
	private String content;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "accountlogid", nullable = false)
	@Column(length = 50)
	private AccountLog accountLog;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public AccountLog getAccountLog() {
		return accountLog;
	}

	public void setAccountLog(AccountLog accountLog) {
		this.accountLog = accountLog;
	}
}
