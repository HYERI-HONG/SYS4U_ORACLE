package version2.kr.sys4u.jdbc.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import version2.kr.sys4u.jdbc.domain.EmployeeDTO;

public class EmployeeDAOImpl implements EmployeeDAO {

	private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeDAOImpl.class);
	private static final boolean IS_ERROR_ENABLED = LOGGER.isErrorEnabled();
	private static final int ROW_PER_PAGE = 5;
	
	private ConnectionPool connectionPool;
	private EmployeeQuery query;
	private int successed;
	private EmployeeDTO emp;
	private List<EmployeeDTO> list;
	
	public EmployeeDAOImpl(ConnectionPool connectionPool) {
		
		this.successed = 0;
		this.connectionPool = connectionPool;
		this.query = new EmployeeQuery();
		this.emp = null;
		this.list = new ArrayList<>();
		
	}
	
	@Override
	public int InsertEmployee(EmployeeDTO emp) {
		
		PreparedStatement statement = null;
		try {
			
			if(readEmployee(emp.getEmpNo())!=null) {
				LOGGER.info("이미 동일한 사원번호를 가진 사원이 존재함");
				successed = 0;
			}else {
				statement = connectionPool.getConnection().prepareStatement(query.getQuery("INSERT"));
				
				statement.setInt(1, emp.getEmpNo());
				statement.setString(2, emp.geteName());
				statement.setString(3, emp.getJob());
				statement.setInt(4, emp.getMgr());
				statement.setInt(5, emp.getSal());
				statement.setInt(6, emp.getComm());
				statement.setInt(7, emp.getDeptNo());
			
				successed = statement.executeUpdate();
			}
			
		} catch (SQLException e) {
			if (IS_ERROR_ENABLED) {
				LOGGER.error("Fail To Insert :: " + e);
			}
		}finally {
			try {
				statement.close();
				connectionPool.close();
			} catch (SQLException e) {
				if (IS_ERROR_ENABLED) {
					LOGGER.error("Fail To Close :: " + e);
				}
			}
		}
		return successed;
		
	}

	@Override
	public List<EmployeeDTO> readAllEmployeeList() {

		ResultSet rs = null;

		try {

			rs = connectionPool.getConnection()
					.prepareStatement(query.getQuery("SELECT_ALL"))
					.executeQuery();

			while (rs.next()) {
				emp = new EmployeeDTO();
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
				LOGGER.error("Fail To Read All :: " + e);
			}
		}finally {
			try {
				rs.close();
				connectionPool.close();
			} catch (SQLException e) {
				if (IS_ERROR_ENABLED) {
					LOGGER.error("Fail To Close :: " + e);
				}
			}
		}
		return list;
	}

	@Override
	public List<EmployeeDTO> readSomeEmployeeList(int page) {
		
		Map<String, Integer> rowInfo = getPageRowInfo(page);
		
		PreparedStatement statement = null;
		ResultSet rs = null;

		try {
			
			statement = connectionPool.getConnection()
					.prepareStatement(query.getQuery("SELECT_SOME"));
			statement.setInt(1,rowInfo.get("beginRow"));
			statement.setInt(2,rowInfo.get("endRow"));
			rs = statement.executeQuery();

			while (rs.next()) {
				emp = new EmployeeDTO();
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
				LOGGER.error("Fail To Read All :: " + e);
			}
		}finally {
			try {
				rs.close();
				statement.close();
				connectionPool.close();
			} catch (SQLException e) {
				if (IS_ERROR_ENABLED) {
					LOGGER.error("Fail To Close :: " + e);
				}
			}
		}
		return list;
		
		
	}

	@Override
	public EmployeeDTO readEmployee(int empno) {
		

		PreparedStatement statement = null;
		ResultSet rs = null;

		try {
			statement = connectionPool.getConnection()
					.prepareStatement(query.getQuery("SELECT_ONE"));
			statement.setInt(1, empno);
			rs = statement.executeQuery();

			while (rs.next()) {
				emp.setEmpNo(rs.getInt("EMPNO"));
				emp.seteName(rs.getString("ENAME"));
				emp.setJob(rs.getString("JOB"));
				emp.setMgr(rs.getInt("MGR"));
				emp.setHireDate(rs.getString("HIREDATE"));
				emp.setSal(rs.getInt("SAL"));
				emp.setComm(rs.getInt("COMM"));
				emp.setDeptNo(rs.getInt("DEPTNO"));
				
			}

		} catch (SQLException e) {
			if (IS_ERROR_ENABLED) {
				LOGGER.error("Fail To Read One :: " + e);
			}
		}finally {
			try {
				rs.close();
				statement.close();
				connectionPool.close();
			} catch (SQLException e) {
				if (IS_ERROR_ENABLED) {
					LOGGER.error("Fail To Close :: " + e);
				}
			}
		}
		return emp;
	}

	@Override
	public int updateEmployee(EmployeeDTO emp) {
		
		PreparedStatement statement = null;
		
		try {
			statement = connectionPool.getConnection().prepareStatement(query.getQuery("UPDATE"));
			statement.setString(1, emp.geteName());
			statement.setInt(2, emp.getEmpNo());
			successed = statement.executeUpdate();

		} catch (SQLException e) {
			if (IS_ERROR_ENABLED) {
				LOGGER.error("Fail To Update :: " + e);
			}
		}finally {
			try {
				statement.close();
				connectionPool.close();
			} catch (SQLException e) {
				if (IS_ERROR_ENABLED) {
					LOGGER.error("Fail To Close :: " + e);
				}
			}
		}
		return successed;
	}

	@Override
	public int deleteEmployee(EmployeeDTO emp) {
		
		PreparedStatement statement = null;
		
		try {
			
			statement = connectionPool.getConnection().prepareStatement(query.getQuery("DELETE"));
			statement.setInt(1, emp.getEmpNo());
			successed = statement.executeUpdate();
			
		} catch (SQLException e) {
			if (IS_ERROR_ENABLED) {
				LOGGER.error("Fail To Delete :: " + e);
			}
		}finally {
			try {
				statement.close();
				connectionPool.close();
			} catch (SQLException e) {
				if (IS_ERROR_ENABLED) {
					LOGGER.error("Fail To Close :: " + e);
				}
			}
		}
		return successed;
	}

	@Override
	public int countEmployee() {
		
		ResultSet rs = null;
		int count = 0;

		try {
			rs = connectionPool.getConnection()
					.prepareStatement(query.getQuery("COUNT"))
					.executeQuery();
			while(rs.next()) {
				count = rs.getInt("COUNT");
			}

		} catch (SQLException e) {
			if (IS_ERROR_ENABLED) {
				LOGGER.error("Fail To Count :: " + e);
			}
		}finally {
			try {
				rs.close();
				connectionPool.close();
			} catch (SQLException e) {
				if (IS_ERROR_ENABLED) {
					LOGGER.error("Fail To Close :: " + e);
				}
			}
		}
		return count;
	}

	@Override
	public Map<String, Integer> getPageRowInfo(int page) {
		
		Map<String, Integer> rowInfo = new HashMap<>();
		
		int rowCount = countEmployee();
		int beginPage = 1;
		int lastPage = ( rowCount / ROW_PER_PAGE ) + (rowCount % ROW_PER_PAGE > 0 ? 1 : 0);
		
		if(page<beginPage) {
			page = beginPage;
		}else if(page>lastPage) {
			page = lastPage;
		}
		
		int beginRow = ROW_PER_PAGE*(page-1)+1;
		int endRow = ROW_PER_PAGE * page;
		
		endRow = (endRow > rowCount)? rowCount: endRow;
		
		rowInfo.put("beginRow", beginRow);
		rowInfo.put("endRow", endRow);
		
		return rowInfo;
	}

}
