package version2.kr.sys4u.jdbc.dao;

public class EmployeeQuery {

	public String getQuery(String option) {

		String query = "";
		
		switch (option) {
		case "INSERT":
			query = "INSERT INTO EMP(EMPNO, ENAME, JOB, MGR, HIREDATE, SAL, COMM, DEPTNO)" 
			+ "VALUES(?,?,?,?,TO_DATE(SYSDATE,'YY/MM/DD'),?,?,?)";
			break;
		case "SELECT_ALL":
			query = "SELECT * FROM EMP";
			break;
		case "SELECT_SOME":
			query = "SELECT * FROM ( SELECT ROWNUM AS NUM, E1.* FROM EMP E1 ORDER BY EMPNO DESC) E2" 
					+ " WHERE E2.NUM BETWEEN ? AND ?";
			break;
		case "SELECT_ONE":
			query = "SELECT * FROM EMP "
					+ " WHERE EMPNO = ?";
			break;
		case "UPDATE":
			query = "UPDATE EMP SET ENAME = ?" 
					+ " WHERE EMPNO = ?";
			break;
		case "DELETE":
			query = "DELETE FROM EMP" 
					+ " WHERE EMPNO = ?";
			break;
		case "COUNT":
			query = "SELECT COUNT(*) AS COUNT FROM EMP";
			break;
		}

		return query;
	}

}
