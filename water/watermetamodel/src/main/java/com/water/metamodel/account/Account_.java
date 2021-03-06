package com.water.metamodel.account;

import com.water.metamodel.authz.Role;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Account.class)
public abstract class Account_ {

	public static volatile SingularAttribute<Account, String> updater;
	public static volatile SingularAttribute<Account, Date> activeenddate;
	public static volatile SingularAttribute<Account, Date> lastlogindate;
	public static volatile SingularAttribute<Account, Department> department;
	public static volatile SingularAttribute<Account, Date> createdate;
	public static volatile SingularAttribute<Account, String> creater;
	public static volatile SingularAttribute<Account, Integer> accountstatus;
	public static volatile ListAttribute<Account, AccountLog> accountLogs;
	public static volatile SingularAttribute<Account, String> id;
	public static volatile SingularAttribute<Account, String> loginpasswd;
	public static volatile SingularAttribute<Account, String> username;
	public static volatile SingularAttribute<Account, Date> activestartdate;
	public static volatile ListAttribute<Account, Role> roles;
	public static volatile SingularAttribute<Account, String> aliasname;
	public static volatile SingularAttribute<Account, String> logincode;
	public static volatile SingularAttribute<Account, String> accountfrom;
	public static volatile SingularAttribute<Account, Integer> accounttype;
	public static volatile SingularAttribute<Account, Date> updatedate;
	public static volatile SingularAttribute<Account, Long> logincount;

}

