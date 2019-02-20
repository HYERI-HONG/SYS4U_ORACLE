package version1.kr.sys4u.jdbc.dao;

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
			query = "";
			break;
		case "SELECT_ONE":
			query = "";
			break;
		case "UPDATE":
			query = "UPDATE EMP SET ENAME = ?" + 
					" WHERE EMPNO = ?";
			break;
		case "DELETE":
			query = "DELETE FROM EMP" + 
					" WHERE EMPNO = ?";
			break;
		}

		return query;
	}

}
