package com.water.basictool;

import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

/**
 * 在线用户统计
 */
public class OnlineAccount implements HttpSessionBindingListener {

	private Map<String, String[]> users = new HashMap<String, String[]>();
	private Map<String,String[]> webusers = new HashMap<String, String[]>();

	private static OnlineAccount onlineaccount = new OnlineAccount();

	public static OnlineAccount getInstance() {
		return onlineaccount;
	}

	/**
	 * @return
	 */
	public Collection getUsers() {
		return users.values();
	}
	/**
	 * @return
	 */
	public Collection getWebUsers() {
		return webusers.values();
	}
	
	public Map<String,String[]> getWebUsersMap(){
		return webusers;
	}

	public void addUser(String[] userinfo) {
		users.put(userinfo[0], userinfo);
	}
	public void addWebUser(String[] userinfo) {
		webusers.put(userinfo[0], userinfo);
	}
	

	public void removeUser(String userId) {
		if (users.containsKey(userId)) {
			users.remove(userId);
		}
	}
	public void removeWebUser(String userId) {
		if (webusers.containsKey(userId)) {
			webusers.remove(userId);
		}
	}

	/**
	 * 绑定session之后
	 * 
	 * 对象实例(即OnlineUserListener的实例)作为一个属性被设置到session的
	 * 
	 * 时候，会调用本方法，这种情况一般发生在点击登录按钮以后的处理过程中 设置
	 * 
	 * @see javax.servlet.http.HttpSessionBindingListener#valueBound(javax.servlet.http.HttpSessionBindingEvent)
	 */
	public void valueBound(HttpSessionBindingEvent event) {
		// 现在暂时不需要额外处理，你可以在这里记录日志等		System.out.println("===============");
	}

	/**
	 * 注销session之前
	 * 
	 * 当Session超时，或本实例被从session中移除的时候被调用，这种情况一般 发生在注销方法的处理过程中
	 * 
	 * @see javax.servlet.http.HttpSessionBindingListener#valueUnbound(javax.servlet.http.HttpSessionBindingEvent)
	 */
	public void valueUnbound(HttpSessionBindingEvent event) {
		HttpSession session = event.getSession();
		java.util.Enumeration<String> e = session.getAttributeNames();
		while (e.hasMoreElements()) {
			String key = e.nextElement();
			if ("SESSION_SYS_USERID".equals(key) && session.getAttribute("currentUser") != null) {
				System.out.println("删除用户:" + session.getAttribute("currentUser").toString());
				session.removeAttribute("SESSION_SYS_USERID");
				OnlineAccount.getInstance().removeUser(event.getSession().getAttribute("currentUser").toString());
				session.invalidate();
				break;
			}
			if ("WEBUSER".equals(key) && session.getAttribute("currentwebUser") != null) {
				System.out.println("删除用户:" + session.getAttribute("currentwebUser").toString());
				session.removeAttribute("WEBUSER");
				OnlineAccount.getInstance().removeWebUser(event.getSession().getAttribute("currentwebUser").toString());
				session.invalidate();
				break;
			}
		}
		System.out.println("用户人数destroyed:" + OnlineAccount.getInstance().getUsers().size());
	}

}