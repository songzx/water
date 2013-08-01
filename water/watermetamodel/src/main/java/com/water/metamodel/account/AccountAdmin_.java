package com.water.metamodel.account;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(AccountAdmin.class)
public abstract class AccountAdmin_ {

	public static volatile SingularAttribute<AccountAdmin, String> id;
	public static volatile SingularAttribute<AccountAdmin, String> email;
	public static volatile SingularAttribute<AccountAdmin, Account> account;
	public static volatile SingularAttribute<AccountAdmin, String> telephone;
	public static volatile SingularAttribute<AccountAdmin, String> mobile;

}

