package com.water.servlets;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.water.metamodel.tree.Category;

public class CategoryServlet extends HttpServlet {

	private EntityManager entityManager;
	private static EntityManagerFactory entityManagerFactory;
	private static Logger logger = LoggerFactory.getLogger(AccountServlet.class);

	@Override
	public void init() throws ServletException {
		super.init();
		entityManagerFactory = Persistence.createEntityManagerFactory("authorityunit");
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String strmethod = request.getParameter("method");
		String strsingemethod = request.getParameter("singemethod");
		if (null == strmethod && null == strsingemethod) {
			response.getWriter().println("error : not exist method");
			return;
		}

		Class<?> clazz = null;
		try {
			clazz = this.getClass();
			if (null != strmethod) {
				entityManager = entityManagerFactory.createEntityManager();
				entityManager.getTransaction().begin();
				Method method = clazz.getMethod(strmethod, HttpServletRequest.class, HttpServletResponse.class, EntityManager.class);
				method.invoke(clazz.newInstance(), request, response, entityManager);
				entityManager.getTransaction().commit();
			} else if (null != strsingemethod) {
				Method method = clazz.getMethod(strsingemethod, HttpServletRequest.class, HttpServletResponse.class);
				method.invoke(clazz.newInstance(), request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
			entityManager.getTransaction().rollback();
			response.getWriter().println("error : execute method " + strmethod + " not success,please check your inputs");
			return;
		} finally {
			if (entityManager != null && entityManager.isOpen()) {
				entityManager.close();
			}
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

	/**
	 * 根据用户权限获取用户栏目信息
	 * 
	 * @param request
	 * @param response
	 * @param entityManager
	 * @throws Exception
	 */
	public void cagetorylistbyaccount(HttpServletRequest request, HttpServletResponse response, EntityManager entityManager) throws Exception {
		String meunid = request.getParameter("menuid")==null?"":request.getParameter("menuid");
		if(meunid == null){
			return;
		}
		List<Category> categorys = entityManager.createQuery("select category from Category category ", Category.class).getResultList();
		request.setAttribute("categorys", categorys);
		request.getRequestDispatcher("/admin/navmenu.jsp").forward(request, response);
	}
}
