package com.water.metamodel.account;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(AccountLog.class)
public abstract class AccountLog_ {

	public static volatile SetAttribute<AccountLog, AccountOperatorLog> accountOperatorLogs;
	public static volatile SingularAttribute<AccountLog, String> id;
	public static volatile SingularAttribute<AccountLog, String> accountname;
	public static volatile SingularAttribute<AccountLog, Date> logindate;
	public static volatile SingularAttribute<AccountLog, Date> logoutdate;
	public static volatile SingularAttribute<AccountLog, Account> account;
	public static volatile SingularAttribute<AccountLog, String> loginmac;
	public static volatile SingularAttribute<AccountLog, String> loginip;

}

