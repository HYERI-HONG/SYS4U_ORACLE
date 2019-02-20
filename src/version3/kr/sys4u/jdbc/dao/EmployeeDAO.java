package version3.kr.sys4u.jdbc.dao;

import java.util.List;
import java.util.Map;

import version3.kr.sys4u.jdbc.dto.Employee;

public interface EmployeeDAO {

	public int InsertEmployee(Employee emp);
	public List<Employee> readAllEmployeeList();
	public List<Employee> readSomeEmployeeList(int page);
	public int updateEmployee(Employee emp);
	public int deleteEmployee(Employee emp);
	public int countEmployee();
	public boolean exist(int empno);
	public Map<String, Integer> getPageRowInfo(int page);
	
}
