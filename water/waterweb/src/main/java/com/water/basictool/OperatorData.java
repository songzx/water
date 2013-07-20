package com.water.basictool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.ResultSetHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 数据库操作
 * 
 * @author 0000
 * 
 */
public abstract class OperatorData {
	private Logger logger = LoggerFactory.getLogger(OperatorData.class);
	
	protected String inj_str = "'|and|exec|insert|select|delete|update|count|*|%|chr|mid|master|truncate|char|declare|; |or|-|+|,";
	
	private Map<String,Object> executeSql(Map<String,Object> params){
		Map<String,Object> result = new HashMap<String, Object>();
		for(Iterator<String> it = params.keySet().iterator(); it.hasNext();){
			String tempkey = it.next();
			Object obj = params.get(tempkey);
			if(obj instanceof String){
				if(sql_inj(obj.toString())){
					logger.info("匹配防注入的输入:"+obj.toString());
					
				}
			}
		}
		
		return result;
	}
	
	private boolean sql_inj(String str) {
		String inj_str = "'|drop|and|exec|insert|select|delete|update|count|*|%|chr|mid|master|truncate|char|declare|; |or|-|+|,";// 这里的东西还可以自己添加
		String[] inj_stra = inj_str.split("\\|");
		for (int i = 0; i < inj_stra.length; i++) {
			if (str.toLowerCase().indexOf(" " + inj_stra[i] + " ") >= 0) {
				logger.error("防注入匹配字符："+inj_stra[i] +"   原字符："+str);
				return true;
			}
		}
		return false;
	}
	
	// 查询
	public Object exeucteQuery(ResultSetHandler resultSetHandler,Map<String, Object> params) {
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Object result = null;
		try {
			connection = DatasourceUtil.getInstance().getDataSource("htgzjd").getConnection();
			connection.setAutoCommit(false);
			rs = (ResultSet)query(connection, pstmt, rs, params);
			if(rs == null){
				return null;
			}
			result = resultSetHandler.handle(rs);
			connection.commit();
			//return result;
		} catch (Exception e) {
			DbUtils.rollbackAndCloseQuietly(connection);
			logger.error(e.getMessage());
			e.printStackTrace();
		} finally {
			DbUtils.closeQuietly(connection, pstmt, rs);
		}
		return result;
	}

	public Object query(Connection connection, PreparedStatement pstmt, ResultSet rs,Map<String, Object> params) throws SQLException {
		return null;
	}

	//增，删，改
	public Object update(Connection connection, PreparedStatement pstmt, ResultSet rs,Map<String, Object> params) throws SQLException {
		return null;
	}


	// 更新
	public Object exeucteUpdate(Map<String, Object> params) {
		Connection connection = null;
		PreparedStatement pstmt = null;
		Object result = null;
		ResultSet rs = null;
		try {
			connection = DatasourceUtil.getInstance().getDataSource("htgzjd").getConnection();
			connection.setAutoCommit(false);
			result = update(connection, pstmt,rs,params);
			connection.commit();
			//return result;
		} catch (Exception e) {
			DbUtils.rollbackAndCloseQuietly(connection);
			logger.error(e.getMessage());
			e.printStackTrace();
		} finally {
			DbUtils.closeQuietly(connection, pstmt,null);
		}
		return result;
	}
}
