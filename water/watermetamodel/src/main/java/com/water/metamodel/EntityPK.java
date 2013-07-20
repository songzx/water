package com.water.metamodel;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@Embeddable
public class EntityPK implements Serializable {
	@Id
	@Column(length = 50)
	private String id = UUID.randomUUID().toString().replace("-", "");

	@Override
	public int hashCode() {
		return id.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof EntityPK)) {
			return false;
		}
		EntityPK other = (EntityPK) obj;
		return getId().equals(other.getId());
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
}
