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

@Entity
@Table(name="AUTHZ_ROLE")
public class Role implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO,generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy = "uuid")
	@Column(length = 50)
	private String id;
	private int enabled;
	private String name;
	private String description;
	
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(name="AUTHZ_ACCOUNT_ROLE")
	private List<Account> accounts = new ArrayList<Account>();
	
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
}
