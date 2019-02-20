package version3.kr.sys4u.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import version3.kr.sys4u.jdbc.dto.Employee;
import version3.kr.sys4u.jdbc.exception.EmployeeException;
import static version3.kr.sys4u.jdbc.dto.DTOFactory.*;
import static version3.kr.sys4u.jdbc.dao.DataResourceCloser.*;

public class EmployeeDAOImpl implements EmployeeDAO {

	private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeDAOImpl.class);
	private static final int ROW_PER_PAGE = 5;

	private Connection connection;
	private EmployeeQuery query;
	private int successed;
	private List<Employee> list;

	public EmployeeDAOImpl(Connection connection) {

		this.successed = 0;
		this.connection = connection;
		this.query = new EmployeeQuery();
		this.list = new ArrayList<>();

	}

	@Override
	public int InsertEmployee(Employee emp) {

		PreparedStatement statement = null;
		try {

			if (exist(emp.getEmpNo())) {
				LOGGER.info("이미 동일한 사원번호를 가진 사원이 존재함");
				return 0;
			}
			statement = connection.prepareStatement(query.getQuery("INSERT"));
			successed = fromEmployeeToStatement(statement, emp).executeUpdate();

		} catch (SQLException e) {
			throw new EmployeeException(e);
		} finally {
			close(statement);
		}
		return successed;
	}

	@Override
	public List<Employee> readAllEmployeeList() {

		ResultSet rs = null;
		try {
			rs = connection.prepareStatement(query.getQuery("SELECT_ALL")).executeQuery();
			while (rs.next()) {
				list.add(fromResultSet(rs, Employee.class));
			}
		} catch (Exception e) {
			throw new EmployeeException(e);
		} finally {
			close(rs);
		}
		return list;
	}

	@Override
	public List<Employee> readSomeEmployeeList(int page) {

		Map<String, Integer> rowInfo = getPageRowInfo(page);
		PreparedStatement statement = null;
		ResultSet rs = null;

		try {
			statement = connection.prepareStatement(query.getQuery("SELECT_SOME"));
			statement.setInt(1, rowInfo.get("beginRow"));
			statement.setInt(2, rowInfo.get("endRow"));
			rs = statement.executeQuery();

			while (rs.next()) {
				list.add(fromResultSet(rs, Employee.class));
			}

		} catch (Exception e) {
			throw new EmployeeException(e);
		} finally {
			close(rs);
			close(statement);
		}
		return list;

	}

	@Override
	public int updateEmployee(Employee emp) {

		PreparedStatement statement = null;
		try {
			statement = connection.prepareStatement(query.getQuery("UPDATE"));
			statement.setString(1, emp.geteName());
			statement.setInt(2, emp.getEmpNo());
			successed = statement.executeUpdate();
		} catch (SQLException e) {
			throw new EmployeeException(e);
		} finally {
			close(statement);
		}
		return successed;
	}

	@Override
	public int deleteEmployee(Employee emp) {

		PreparedStatement statement = null;
		try {

			statement = connection.prepareStatement(query.getQuery("DELETE"));
			statement.setInt(1, emp.getEmpNo());
			successed = statement.executeUpdate();

		} catch (SQLException e) {
			throw new EmployeeException(e);
		} finally {
			close(statement);
		}
		return successed;
	}

	@Override
	public boolean exist(int empno) {

		PreparedStatement statement = null;
		ResultSet rs = null;
		boolean exist = false;
		try {
			statement = connection.prepareStatement(query.getQuery("EXIST"));
			statement.setInt(1, empno);
			rs = statement.executeQuery();
			exist = rs.next();
		} catch (SQLException e) {
			throw new EmployeeException(e);
		} finally {
			close(rs);
			close(statement);
		}
		return exist;
	}

	@Override
	public int countEmployee() {

		ResultSet rs = null;
		int count = 0;

		try {
			rs = connection.prepareStatement(query.getQuery("COUNT")).executeQuery();
			while (rs.next()) {
				count = rs.getInt("COUNT");
			}

		} catch (SQLException e) {
			throw new EmployeeException(e);
		} finally {
			close(rs);
		}
		return count;
	}

	@Override
	public Map<String, Integer> getPageRowInfo(int page) {

		Map<String, Integer> rowInfo = new HashMap<>();

		int rowCount = countEmployee();
		int beginPage = 1;
		int lastPage = (rowCount / ROW_PER_PAGE) + (rowCount % ROW_PER_PAGE > 0 ? 1 : 0);

		if (page < beginPage) {
			page = beginPage;
		} else if (page > lastPage) {
			page = lastPage;
		}

		int beginRow = ROW_PER_PAGE * (page - 1) + 1;
		int endRow = ROW_PER_PAGE * page;

		endRow = (endRow > rowCount) ? rowCount : endRow;

		rowInfo.put("beginRow", beginRow);
		rowInfo.put("endRow", endRow);

		return rowInfo;
	}

}
