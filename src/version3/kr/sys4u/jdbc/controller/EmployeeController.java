package version3.kr.sys4u.jdbc.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import version3.kr.sys4u.jdbc.dao.ConnectionPool;
import version3.kr.sys4u.jdbc.dao.EmployeeDAOImpl;
import version3.kr.sys4u.jdbc.dto.Employee;

/**
 * Main : 어떤 SQL을 수행할 것인지 결정
 *  1번 : Create 
 *  2번 : Read - 모든 사원 목록
 *  3번 : Read - 해당 페이지의 사원 목록 
 *  4번 : Read - 특정 사원 
 *  5번 : Update
 *  6번 : Delete
 */

public class EmployeeController {

	private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeController.class);

	public static void main(String[] args) throws Exception {

		Employee emp = new Employee();
		ConnectionPool connectionPool = new ConnectionPool();

		int option = 2;
		int successed = 0;
		
		switch (option) {
		case 1:
			LOGGER.info("상태 :: 삽입 코드 입력");
			EmployeeDAOImpl insertDao = new EmployeeDAOImpl(connectionPool.getConnection());
			emp.setEmpNo(8000);
			emp.seteName("BUN");
			emp.setJob("DEVELOPER");
			emp.setMgr(8001);
			emp.setHireDate("2019-02-20");
			emp.setSal(3000);
			emp.setComm(100);
			emp.setDeptNo(10);
			successed = insertDao.InsertEmployee(emp);
			LOGGER.info((successed == 1)? "성공적으로 데이터가 삽입됨" : "데이터 삽입 실패 : 중복 데이터 입력");
			break;

		case 2:
			LOGGER.info("상태 :: 조회 코드 입력(전체 목록)");
			EmployeeDAOImpl readAllDao = new EmployeeDAOImpl(connectionPool.getConnection());
			for (Employee employeeBean : readAllDao.readAllEmployeeList()) {
				LOGGER.debug(employeeBean.toString());
			}
			break;
		case 3:
			LOGGER.info("상태 :: 조회 코드 입력(해당 페이지 목록)");
			EmployeeDAOImpl readSomeDao = new EmployeeDAOImpl(connectionPool.getConnection());
			int page = 4;
			for (Employee employeeBean : readSomeDao.readSomeEmployeeList(page)) {
				LOGGER.debug(employeeBean.toString());
			}
			break;
		case 4:
			LOGGER.info("상태 :: 조회 코드 입력(해당 사원 존재 여부)");
			EmployeeDAOImpl existDao = new EmployeeDAOImpl(connectionPool.getConnection());
			LOGGER.debug((existDao.exist(8001))? "해당 사원이 있음" : "해당 사원이 없음" );
			break;
		case 5:
			LOGGER.info("상태 :: 갱신 코드 입력");
			EmployeeDAOImpl updateDao = new EmployeeDAOImpl(connectionPool.getConnection());
			emp.setEmpNo(8000);
			emp.seteName("LUCY");
			successed = updateDao.updateEmployee(emp);
			if (successed >0) {
				LOGGER.info("성공적으로 데이터가 갱신됨");
			}
			break;
		case 6:
			LOGGER.info("상태 :: 삭제 코드 입력");
			EmployeeDAOImpl deleteDao = new EmployeeDAOImpl(connectionPool.getConnection());
			emp.setEmpNo(8000);
			successed = deleteDao.deleteEmployee(emp);
			if (successed > 0) {
				LOGGER.info("성공적으로 데이터가 삭제됨");
			}
			break;
		}
		connectionPool.close();
	}
}
