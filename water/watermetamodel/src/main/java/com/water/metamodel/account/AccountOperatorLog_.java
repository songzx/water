package com.water.metamodel.account;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(AccountOperatorLog.class)
public abstract class AccountOperatorLog_ {

	public static volatile SingularAttribute<AccountOperatorLog, String> id;
	public static volatile SingularAttribute<AccountOperatorLog, String> runsql;
	public static volatile SingularAttribute<AccountOperatorLog, AccountLog> accountLog;
	public static volatile SingularAttribute<AccountOperatorLog, String> logdesc;
	public static volatile SingularAttribute<AccountOperatorLog, String> logresult;

}

