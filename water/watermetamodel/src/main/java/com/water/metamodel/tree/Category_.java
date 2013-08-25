package com.water.metamodel.tree;

import com.water.metamodel.authz.Role;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Category.class)
public abstract class Category_ {

	public static volatile SingularAttribute<Category, String> id;
	public static volatile SingularAttribute<Category, Integer> enabled;
	public static volatile ListAttribute<Category, Role> roles;
	public static volatile SingularAttribute<Category, String> name;
	public static volatile SingularAttribute<Category, String> imgsrc;
	public static volatile SingularAttribute<Category, String> parentid;
	public static volatile SingularAttribute<Category, String> otherproperties;
	public static volatile SingularAttribute<Category, String> code;
	public static volatile SingularAttribute<Category, String> type;
	public static volatile SingularAttribute<Category, String> abbreviation;
	public static volatile SingularAttribute<Category, String> categorytype;
	public static volatile SingularAttribute<Category, String> url;

}

