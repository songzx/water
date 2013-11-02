package com.water.example.data2view;

import java.io.InputStream;

/**
 * 操作权限标签
 * 
 * @author 0000
 * @date Nov 2, 2013
 * @version V1.0
 */
public abstract class Authz {

	/**
	 * 检查权限输出
	 * 
	 * @return
	 */
	public InputStream check(){
		
		return null;
	}
	
	public abstract void rule();
}
