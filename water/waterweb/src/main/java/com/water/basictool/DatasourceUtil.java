package com.water.basictool;

import java.io.File;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.dbcp.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.water.basictool.exception.ConfigFileNoExistException;

public class DatasourceUtil {

	private static DatasourceUtil datasourceUtil;
	private static Map<String, DataSource> datasourceMap = new HashMap<String, DataSource>();
	private static Logger logger = LoggerFactory.getLogger(DatasourceUtil.class);
	private static boolean reloadflag = false;
	private static String defaultdatasource = null;

	private DatasourceUtil() {
	};

	public static DatasourceUtil getInstance(String propertiesfilepath) {
		synchronized (datasourceMap) {
			try {
				// 重新加载时,关闭所有连接池
				if (reloadflag) {
					for (Iterator<DataSource> it = datasourceMap.values().iterator(); it.hasNext();) {
						((BasicDataSource) it.next()).close();
					}
				}

				if (datasourceUtil == null || reloadflag) {
					logger.info("初始化连接池的属性文件：" + propertiesfilepath);
					File propertiesfile = new File(propertiesfilepath);
					if (!propertiesfile.exists()) {
						throw new ConfigFileNoExistException();
					}

					datasourceUtil = new DatasourceUtil();
					PropertiesConfiguration propertiesConfiguration = new PropertiesConfiguration();
					propertiesConfiguration.setEncoding("UTF-8");
					propertiesConfiguration.load(propertiesfile);
					
					defaultdatasource = propertiesConfiguration.getString("datasource.defaultname");
					
					String datasources[] = propertiesConfiguration.getStringArray("datasource.name") == null ? new String[0] : propertiesConfiguration.getStringArray("datasource.name");
					for (String tempdatasourc : datasources) {
						datasourceMap.put(tempdatasourc.toUpperCase(), initDataSource(propertiesConfiguration.getString(tempdatasourc + ".driverClassName"), propertiesConfiguration.getString(tempdatasourc + ".url"), propertiesConfiguration.getString(tempdatasourc + ".username"), propertiesConfiguration.getString(tempdatasourc + ".password"), propertiesConfiguration.getInt(tempdatasourc + ".maxActive", 100), propertiesConfiguration.getInt(tempdatasourc + ".maxIdle", 50), propertiesConfiguration.getInt(tempdatasourc + ".maxWait", 2000)));
					}

					propertiesConfiguration.clear();
				}
			} catch (ConfigurationException e) {
				logger.error("配置文件不能正确读取");
				e.printStackTrace();
			} catch (SQLException e) {
				logger.error("重载连接池时，关闭连接出错！");
				e.printStackTrace();
			} catch (ConfigFileNoExistException e) {
				logger.error("连接池的属性文件不存在!");
				e.printStackTrace();
			}
		}
		return datasourceUtil;
	}

	public static DatasourceUtil getInstance() {
		String propertiesfilepath = WebappPropertyUtil.getInstance().getWebappPath() + "WEB-INF/resources/basicconfig/datasource.properties";
		return getInstance(propertiesfilepath);
	}

	public DataSource getDataSource(String datasourcename) {
		if (datasourceMap.containsKey(datasourcename.toUpperCase())) {
			return datasourceMap.get(datasourcename.toUpperCase());
		}
		return null;
	}

	public DataSource getDataSource() {
		if(datasourceMap.containsKey(defaultdatasource.toUpperCase())){
			return datasourceMap.get(defaultdatasource.toUpperCase());
		}
		return null;
	}

	/**
	 * 根据连接池名关闭连接池所有connection
	 * 
	 * @param datasourcename
	 */
	public void closeDataSource(String datasourcename) {
		if (datasourceMap.containsKey(datasourcename)) {
			BasicDataSource basicDataSource = (BasicDataSource) datasourceMap.get(datasourcename);
			try {
				basicDataSource.close();
			} catch (SQLException e) {
				logger.error(e.getMessage());
			}
		}
	}

	/**
	 * 重新加载配置文件
	 * 
	 * @throws Exception
	 */
	public void reload() throws Exception {
		reloadflag = true;
		getInstance();
	}

	/**
	 * 重新加载配置文件
	 * 
	 * @param propertiesfilepath
	 * @throws Exception
	 */
	public void reload(String propertiesfilepath) {
		reloadflag = true;
		getInstance(propertiesfilepath);
	}

	/**
	 * 初始化链接池 logintimeout参数,apache-dbcp暂不支持
	 * 
	 * @param driverClassName
	 * @param url
	 * @param username
	 * @param password
	 * @return
	 * @throws SQLException
	 */
	private static DataSource initDataSource(String driverClassName, String url, String username, String password, int maxActive, int maxIdle, int maxWait) throws SQLException {
		BasicDataSource basicDataSource = new BasicDataSource();
		basicDataSource.setDriverClassName(driverClassName);
		basicDataSource.setUrl(url);
		basicDataSource.setUsername(username);
		basicDataSource.setPassword(password);
		basicDataSource.setInitialSize(10);
		basicDataSource.setMaxActive(maxActive);
		basicDataSource.setMaxIdle(maxIdle);
		// basicDataSource.setLoginTimeout(maxWait);
		return basicDataSource;
	}

}
