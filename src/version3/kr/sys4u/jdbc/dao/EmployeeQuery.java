package version3.kr.sys4u.jdbc.dao;

public class EmployeeQuery {

	public String getQuery(String option) {

		String query = "";
		
		switch (option) {
		case "INSERT":
			query = "INSERT INTO EMP(EMPNO, ENAME, JOB, MGR, HIREDATE, SAL, COMM, DEPTNO)" 
			+ "VALUES(?,?,?,?,SYSDATE,?,?,?)";
			break;
		case "SELECT_ALL":
			query = "SELECT * FROM EMP ORDER BY HIREDATE DESC";
			break;
		case "SELECT_SOME":
			query = "SELECT * FROM ( SELECT ROWNUM AS NUM, E1.* FROM (SELECT * FROM EMP ORDER BY HIREDATE DESC) E1) E2" 
					+ " WHERE E2.NUM BETWEEN ? AND ?";
			break;
		case "EXIST":
			query = "SELECT 1 FROM DUAL "
					+ "WHERE EXISTS (SELECT 1 FROM EMP WHERE EMPNO = ?)";
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
