package version3.kr.sys4u.jdbc.dto;

import java.lang.reflect.Field;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DTOFactory {
	
	public static PreparedStatement fromEmployeeToStatement(PreparedStatement st, Employee emp) throws SQLException {
		
		st.setInt(1, emp.getEmpNo());
		st.setString(2, emp.geteName());
		st.setString(3, emp.getJob());
		st.setInt(4, emp.getMgr());
		st.setInt(5, emp.getSal());
		st.setInt(6, emp.getComm());
		st.setInt(7, emp.getDeptNo());
		
		return st;
		
	}
	public static Employee fromResultSetToEmployee(ResultSet rs) throws SQLException {
		
		Employee emp = new Employee();
		emp.setEmpNo(rs.getInt("EMPNO"));
		emp.seteName(rs.getString("ENAME"));
		emp.setJob(rs.getString("JOB"));
		emp.setMgr(rs.getInt("MGR"));
		emp.setHireDate(rs.getDate("HIREDATE"));
		emp.setSal(rs.getInt("SAL"));
		emp.setComm(rs.getInt("COMM"));
		emp.setDeptNo(rs.getInt("DEPTNO"));
		
		return emp;
		
	}
	
	public static <T> T fromResultSet(ResultSet rs, Class<T> clazz) throws Exception {
		T instance = clazz.newInstance();

		Field[] fields = Employee.class.getDeclaredFields();
		for (Field field : fields) {
			if (field.getName().equals("serialVersionUID")) {
				continue;
			}
			field.setAccessible(true);
			String columnName = field.getName().toUpperCase();
			Class<?> type = field.getType();
			if (type == int.class) {
				field.set(instance, rs.getInt(columnName));
			} else if (type == String.class) {
				field.set(instance, rs.getString(columnName));
			} else if (type == Date.class) {
				field.set(instance, rs.getDate(columnName));
			}
		}

		return instance;
	}

}
