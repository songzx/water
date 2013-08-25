package com.water.metamodel.authz;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.water.metamodel.account.Account;

/**
 * 权限
 * 
 * @author 0000
 * @date Aug 19, 2013
 * @version V1.0
 */
@Entity
@Table(name = "AUTH_MODULE")
public class Module implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(length = 50)
	private String id;
	private int enabled;
	private String modulename;
	private String moduledesc;

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

	public String getModulename() {
		return modulename;
	}

	public void setModulename(String modulename) {
		this.modulename = modulename;
	}

	public String getModuledesc() {
		return moduledesc;
	}

	public void setModuledesc(String moduledesc) {
		this.moduledesc = moduledesc;
	}
}
