package version3.kr.sys4u.jdbc.dao;

import java.sql.Connection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.mchange.v2.c3p0.ComboPooledDataSource;

import version3.kr.sys4u.jdbc.exception.EmployeeException;

public class ConnectionPool {

	private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeDAOImpl.class);
	private static final boolean IS_ERROR_ENABLED = LOGGER.isErrorEnabled();

	private ComboPooledDataSource cpds;
	private Connection connection;

	public ConnectionPool() throws Exception {
		
		this.cpds = new ComboPooledDataSource();
		this.connection = null;

		cpds.setDriverClass(DBConstant.ORACLE_DRIVER);
		cpds.setJdbcUrl(DBConstant.ORACLE_CONNECTION_URL);
		cpds.setUser(DBConstant.USERNAME);
		cpds.setPassword(DBConstant.PASSWORD);
		cpds.setMinPoolSize(10);
		cpds.setInitialPoolSize(10);
		cpds.setMaxPoolSize(30);

	}

	public Connection getConnection() {
		try {
			connection = cpds.getConnection();
		} catch (Exception e) {
			throw new EmployeeException(e);
		}
		return connection;
	}

	public void close() {
		if (connection != null) {
			try {
				connection.close();
			} catch (Exception e) {
				throw new EmployeeException(e);
			}
		}
	}
}
