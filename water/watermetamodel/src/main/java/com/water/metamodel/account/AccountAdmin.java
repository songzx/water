package com.water.metamodel.account;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "AUTH_ACCOUNT_ADMIN")
public class AccountAdmin implements Serializable {

	@Id
	@Column(length = 50)
	private String accountid;
	@Column(length=50)
	private String email;// 邮件
	@Column(length=11)
	private String mobile;// 手机
	@Column(length=12)
	private String telephone;// 固话
	
	public String getAccountid() {
		return accountid;
	}
	public void setAccountid(String accountid) {
		this.accountid = accountid;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
}
