package com.water.mogodb;

import java.net.UnknownHostException;
import java.util.Iterator;

import org.junit.Assert;
import org.junit.Test;

import com.mongodb.MongoClient;

public class MogodbExample {

	@Test
	public void testConneect(){
		try {
			MongoClient mongoClient = new MongoClient("192.168.1.9", 27017);
			for(Iterator<String> it = mongoClient.getDatabaseNames().iterator();it.hasNext();){
				System.out.println("数据库："+it.next());
			}
			mongoClient.getDB("gsmpro").authenticate("", "".toCharArray());
			
			System.out.println();
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}
	
}
