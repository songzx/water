/** 
 *  Generated by OpenJPA MetaModel Generator Tool.
**/

package com.water.metamodel.account;

import java.util.Date;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;

@javax.persistence.metamodel.StaticMetamodel
(value=com.water.metamodel.account.AccountLog.class)
@javax.annotation.Generated
(value="org.apache.openjpa.persistence.meta.AnnotationProcessor6",date="Wed Jul 17 13:16:01 CST 2013")
public class AccountLog_ {
    public static volatile SingularAttribute<AccountLog,Account> account;
    public static volatile SetAttribute<AccountLog,AccountOperatorLog> accountOperatorLogs;
    public static volatile SingularAttribute<AccountLog,String> id;
    public static volatile SingularAttribute<AccountLog,Date> logindate;
    public static volatile SingularAttribute<AccountLog,String> loginip;
    public static volatile SingularAttribute<AccountLog,String> loginmac;
    public static volatile SingularAttribute<AccountLog,Date> logoutdate;
}
