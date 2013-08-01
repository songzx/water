package com.water.metamodel.account;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "AUTH_ACCOUNT_WEB")
public class AccountWeb {
	@Id
	@Column(length = 50)
	private String id;
	@Column(length = 50)
	private String email;// 邮件
	@Column(length = 11)
	private String mobile;// 手机
	@Column(length = 12)
	private String telephone;// 固话
	@Column(length = 20)
	private String truename;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@MapsId
	//@JoinColumn(name="id")
	//@OneToOne(targetEntity=Account.class, cascade={CascadeType.ALL}, mappedBy="accountAdmin")  
    @PrimaryKeyJoinColumn(name="id", referencedColumnName="id") 
	public Account account;


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getTruename() {
		return truename;
	}

	public void setTruename(String truename) {
		this.truename = truename;
	}
	
}
