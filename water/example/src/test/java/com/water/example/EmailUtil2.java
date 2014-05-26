package com.water.example;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.junit.Test;

public class EmailUtil2 {
	
	public static void main(String[] args) throws EmailException {
		Email email = new SimpleEmail();
		email.setHostName("smtp.sina.com.cn");
		email.setSmtpPort(25);
		email.setAuthenticator(new DefaultAuthenticator("suigui1000@sina.com", "ss13427618207"));
		//email.setSSL(true);
		email.setFrom("suigui1000@sina.com");
		email.setSubject("TestMail");
		email.setMsg("This is a test mail .ss.. :-)");
		email.addTo("360898142@qq.com");
		System.out.println(email.send());
		
	}
	
	@Test
	public void testsend() throws EmailException{
		Email email = new SimpleEmail();
		email.setSmtpPort(25);
		email.setHostName("mail.ucap.com.cn");
		
		email.setAuthenticator(new DefaultAuthenticator("liaowq@ucap.com.cn", "liaowq"));
		email.setSSL(true);
		email.setFrom("liaowq@ucap.com.cn");
		email.setSubject("TestMail");
		email.setMsg("This is a test mail .ss.. :-)");
		email.addTo("360898142@qq.com");
		System.out.println(email.send());
	}
}
