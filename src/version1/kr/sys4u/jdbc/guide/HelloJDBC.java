package version1.kr.sys4u.jdbc.guide;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import version1.kr.sys4u.jdbc.dao.DBConstant;

public class HelloJDBC {

	private static final ComboPooledDataSource CPDS_POOL = new ComboPooledDataSource();

	private static final String SQL1 = "SELECT 'HELLO WORLD' AS MESSAGE FROM DUAL";

	public static void main(String[] args) {

		Connection connection = null;
		String result = null;

		long startTime = System.nanoTime();

		try {
			initializeConnectionPool();
			System.out.println("Connection Pool Creating Time = " + (System.nanoTime() - startTime) / 1000_000 + "ms");
			startTime = System.nanoTime();

			connection = CPDS_POOL.getConnection();
			System.out.println("Connection Getting Time = " + (System.nanoTime() - startTime) / 1000_000 + "ms");
			startTime = System.nanoTime();

			result = getResult(connection);
			System.out.println("result = "+result);
			System.out.println("Result Getting Time = " + (System.nanoTime() - startTime) / 1000_000 + "ms");
	        startTime = System.nanoTime();
	
		} catch (SQLException e) {

		} catch (Exception e) {

		} finally {
			close(connection);
		}
	}

	private static void initializeConnectionPool() throws Exception {

		CPDS_POOL.setDriverClass(DBConstant.ORACLE_DRIVER);
		CPDS_POOL.setJdbcUrl(DBConstant.ORACLE_CONNECTION_URL);
		CPDS_POOL.setUser(DBConstant.USERNAME);
		CPDS_POOL.setPassword(DBConstant.PASSWORD);
		CPDS_POOL.setMinPoolSize(10);
		CPDS_POOL.setInitialPoolSize(10);
		CPDS_POOL.setMaxPoolSize(30);

	}
	
	private static String getResult(Connection connection) throws SQLException{
	      PreparedStatement pstmt = null;
	      ResultSet rs = null;
	      
	      try {
	         pstmt = connection.prepareStatement(SQL1);
	         rs = pstmt.executeQuery();
	         
	 		// executeQuery하는 순간 resultset에 값이 들어와 있는것이 아니다. 준비만 하게 하고 next()를 호출해야 결과를
	        // 가져오는것이다.
			// 데이터베이스의 많은 데이터를 한꺼번에 가져오면 메모리가 모자랄 수 있기 때문에, 한번에 보내지 않고 next()를 통해서 하나씩 처리할 수
			// 있도록 했다.
	         if(rs.next()) {
	            return rs.getString("MESSAGE");
	         }
	      
	      } catch(SQLException e) {
	         throw e;
	      } finally {
	         close(rs);
	         close(pstmt);
	      }
	      
	      return "No More Result";
	   }

	private static void close(AutoCloseable closeable) {
		if (closeable != null) {
			try {
				closeable.close();
			} catch (Exception e) {
			}
		}
	}
}
