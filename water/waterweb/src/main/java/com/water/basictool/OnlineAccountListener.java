package com.water.basictool;


import java.util.Enumeration;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 
 * @Title: OnlineUserListener.java 
 * @Package com.ucap.desktop.onlineuser 
 * @Description: 在线用户监听(只针对浏览器的局部session)
 * @author 0000
 * @date 2012-5-7 下午4:04:59 
 * @version V1.0
 * 
 * ,HttpSessionAttributeListener 
 */
public class OnlineAccountListener implements HttpSessionListener{
	private static Logger logger = null;
	
	public OnlineAccountListener(){
	}
	
	/**
	 * 创建session之前
	 */
	public void sessionCreated(HttpSessionEvent event) {
		/*HttpSession session = event.getSession();
		if(session.getAttribute("SESSION_SYS_USERID") != null){
			event.getSession().setAttribute("currentUser",new String[]{session.getId(),"aaa"+session.getId()});
			OnlineUsers.getInstance().addUser(new String[]{session.getId(),"aaa"+session.getId()});
		}
		System.out.println("用户人数create:"+OnlineUsers.getInstance().getUsers().size()+"  "+session.getAttribute("")+"  ========  "+event.getSource().toString());*/
		//HttpSession session = event.getSession();
		//logger.info("用户人数create:"+OnlineUsers.getInstance().getUsers().size()+"  "+session.getAttribute("")+"  ========  "+event.getSource().toString());
	}

	/**
	 * 撤销session之后
	 * 触发条件:session.invalidate();
	 */
	public void sessionDestroyed(HttpSessionEvent event) {
		if(logger == null){
			logger = LoggerFactory.getLogger(OnlineAccountListener.class);
		}
		HttpSession session = event.getSession();
		Enumeration<String> e = session.getAttributeNames();
		while(e.hasMoreElements()){
			String key = e.nextElement();
			if("SESSION_SYS_USERID".equals(key) && session.getAttribute("currentUser") != null){
				logger.info("删除用户:"+session.getAttribute("currentUser").toString());
				
				session.removeAttribute("SESSION_SYS_USERID");
				OnlineAccount.getInstance().removeUser(event.getSession().getAttribute("currentUser").toString());
				session.invalidate();
				break;
			}
			if ("WEBUSER".equals(key) && session.getAttribute("currentwebUser") != null) {
				logger.info("删除用户:" + session.getAttribute("currentwebUser").toString());
				session.removeAttribute("WEBUSER");
				OnlineAccount.getInstance().removeWebUser(event.getSession().getAttribute("currentwebUser").toString());
				
				session.invalidate();
				break;
			}
		}
		logger.info("后台用户人数:"+OnlineAccount.getInstance().getUsers().size()+" 前台用户人数:"+OnlineAccount.getInstance().getWebUsers().size());
	}
	
}