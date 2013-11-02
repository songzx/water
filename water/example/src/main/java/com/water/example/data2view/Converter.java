package com.water.example.data2view;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.FlushModeType;
import javax.persistence.Persistence;

import org.junit.Before;

/**
 * 合成jsp 根据对象属性值，正则表达式判断输入合法性，表单类型，JMS的主题选择
 * 
 * @author 0000
 * @date Nov 2, 2013
 * @version V1.0
 */
public class Converter {
	private static EntityManager entityManager;
	private static EntityManagerFactory entityManagerFactory;

	public static void main(String[] args) throws Exception {
		entityManagerFactory = Persistence.createEntityManagerFactory("authorityunit");
		entityManager = entityManagerFactory.createEntityManager();
		entityManager.setFlushMode(FlushModeType.AUTO);
		System.out.println(entityManager);
		long stime = System.currentTimeMillis();
		entityManager.getTransaction().begin();
		for (int i = 0; i < 100000; i++) {
			entityManager.createNativeQuery("insert into table1(COLUMN11,COLUMN2)values(?,?)").setParameter(1, "text" + i).setParameter(2, "text" + i).executeUpdate();
		}
		entityManager.getTransaction().commit();
		long etime = System.currentTimeMillis();
		System.out.println(etime - stime);

		stime = System.currentTimeMillis();
		File file = null;
		OutputStream outputStream = null;
		for (int i = 0; i < 100000; i++) {
			file = new File("/home/0000/bb/" + i);
			outputStream = new FileOutputStream(file);
			outputStream.write(("<xml><text>text" + i + "</text><text>name" + i + "</text></xml>").getBytes("UTF-8"));
			outputStream.flush();
			outputStream.close();
		}
		etime = System.currentTimeMillis();
		System.out.println(etime - stime);
	}
}
