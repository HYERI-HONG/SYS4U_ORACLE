package version1.kr.sys4u.jdbc.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import version1.kr.sys4u.jdbc.domain.EmployeeDTO;

public class EmployeeDAOImpl implements EmployeeDAO {

	private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeDAOImpl.class);
	private static final boolean IS_ERROR_ENABLED = LOGGER.isErrorEnabled();
	private ConnectionPool connectionPool;
	private EmployeeQuery query;
	private int successed;
	
	public EmployeeDAOImpl(ConnectionPool connectionPool) {
		
		this.successed = 0;
		this.connectionPool = connectionPool;
		this.query = new EmployeeQuery();
		
	}
	
	@Override
	public int InsertEmployee(EmployeeDTO emp) {
		

		try {
			PreparedStatement statement = connectionPool.getConnection().prepareStatement(query.getQuery("INSERT"));
			
			statement.setInt(1, emp.getEmpNo());
			statement.setString(2, emp.geteName());
			statement.setString(3, emp.getJob());
			statement.setInt(4, emp.getMgr());
			statement.setInt(5, emp.getSal());
			statement.setInt(6, emp.getComm());
			statement.setInt(7, emp.getDeptNo());
		
			successed = statement.executeUpdate();
			
			
		} catch (SQLException e) {
			if (IS_ERROR_ENABLED) {
				LOGGER.error("Fail To Insert :: " + e);
			}
		}finally {
			
		}
		return successed;
		
	}

	@Override
	public List<EmployeeDTO> readAllEmployeeList() {

		EmployeeDTO emp = new EmployeeDTO();
		List<EmployeeDTO> list = new ArrayList<>();

		try {

			ResultSet rs = connectionPool.getConnection()
					.prepareStatement(query.getQuery("SELECT_ALL"))
					.executeQuery();

			while (rs.next()) {
				emp.setEmpNo(rs.getInt("EMPNO"));
				emp.seteName(rs.getString("ENAME"));
				emp.setJob(rs.getString("JOB"));
				emp.setMgr(rs.getInt("MGR"));
				emp.setHireDate(rs.getString("HIREDATE"));
				emp.setSal(rs.getInt("SAL"));
				emp.setComm(rs.getInt("COMM"));
				emp.setDeptNo(rs.getInt("DEPTNO"));
				list.add(emp);
			}

		} catch (SQLException e) {
			if (IS_ERROR_ENABLED) {
				LOGGER.error("Fail To Read :: " + e);
			}
		}finally {
			
		}
		return list;
	}

	@Override
	public List<EmployeeDTO> readSomeEmployeeList(int page) {
		return null;
	}

	@Override
	public EmployeeDTO readEmployee(int empno) {
		return null;
	}

	@Override
	public int updateEmployee(EmployeeDTO emp) {
		
		try {
			PreparedStatement statement = connectionPool.getConnection().prepareStatement(query.getQuery("UPDATE"));
			statement.setString(1, emp.geteName());
			statement.setInt(2, emp.getEmpNo());
			successed = statement.executeUpdate();

		} catch (SQLException e) {
			if (IS_ERROR_ENABLED) {
				LOGGER.error("Fail To Update :: " + e);
			}
		}
		return successed;
	}

	@Override
	public int deleteEmployee(EmployeeDTO emp) {
		
		try {
			
			PreparedStatement statement = connectionPool.getConnection().prepareStatement(query.getQuery("DELETE"));
			statement.setInt(1, emp.getEmpNo());
			successed = statement.executeUpdate();
			
		} catch (SQLException e) {
			if (IS_ERROR_ENABLED) {
				LOGGER.error("Fail To Delete :: " + e);
			}
		}
		
		return successed;
	}

	@Override
	public int countEmployee() {
		
		return 0;
	}

}
