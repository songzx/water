package com.water.example;

import java.io.File;
import java.io.IOException;
import java.sql.PreparedStatement;

import org.apache.commons.io.FileUtils;

public class RegUtil {
	public static void main(String[] args) throws IOException {
		String str =FileUtils.readFileToString(new File("D:\\coding\\study\\water\\water\\example\\src\\test\\java\\com\\water\\example\\reg.txt"));
		//System.out.println(str.replaceAll("<((?i)img)[^>]+>", "")+"bbb");
		
		System.out.println(str.split("/n"));
	}
}
