package com.water.metamodel.account;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(AccountWeb.class)
public abstract class AccountWeb_ {

	public static volatile SingularAttribute<AccountWeb, String> id;
	public static volatile SingularAttribute<AccountWeb, String> truename;
	public static volatile SingularAttribute<AccountWeb, String> email;
	public static volatile SingularAttribute<AccountWeb, Account> account;
	public static volatile SingularAttribute<AccountWeb, String> telephone;
	public static volatile SingularAttribute<AccountWeb, String> mobile;

}

