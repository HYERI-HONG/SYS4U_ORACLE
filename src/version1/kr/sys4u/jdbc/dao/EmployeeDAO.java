package version1.kr.sys4u.jdbc.dao;

import java.util.List;
import version1.kr.sys4u.jdbc.domain.EmployeeDTO;

public interface EmployeeDAO {

	public int InsertEmployee(EmployeeDTO emp);
	public List<EmployeeDTO> readAllEmployeeList();
	public List<EmployeeDTO> readSomeEmployeeList(int page);
	public EmployeeDTO readEmployee(int empno);
	public int updateEmployee(EmployeeDTO emp);
	public int deleteEmployee(EmployeeDTO emp);
	public int countEmployee();
	
}
