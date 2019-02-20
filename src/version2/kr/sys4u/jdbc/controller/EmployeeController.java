package version2.kr.sys4u.jdbc.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import version2.kr.sys4u.jdbc.dao.ConnectionPool;
import version2.kr.sys4u.jdbc.dao.EmployeeDAOImpl;
import version2.kr.sys4u.jdbc.domain.EmployeeDTO;

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

		EmployeeDTO emp = new EmployeeDTO();
		EmployeeDAOImpl dao = new EmployeeDAOImpl(new ConnectionPool());

		int option = 6;
		int successed = 0;
		
		switch (option) {
		case 0:
			LOGGER.info("상태 :: 종료 코드 입력");
			return;
		case 1:
			LOGGER.info("상태 :: 삽입 코드 입력");
			emp.setEmpNo(8000);
			emp.seteName("HYERI");
			emp.setJob("DEVELOPER");
			emp.setMgr(7788);
			emp.setSal(3000);
			emp.setComm(100);
			emp.setDeptNo(10);
			successed = dao.InsertEmployee(emp);

			LOGGER.info((successed == 1)? "성공적으로 데이터가 삽입됨" : "데이터 삽입 실패 : 중복 데이터 입력");
			break;

		case 2:
			LOGGER.info("상태 :: 조회 코드 입력(전체 목록)");

			for (EmployeeDTO employeeBean : dao.readAllEmployeeList()) {
				LOGGER.debug(employeeBean.toString());
			}

			break;
		case 3:
			LOGGER.info("상태 :: 조회 코드 입력(해당 페이지 목록)");
			int page = 3;
			for (EmployeeDTO employeeBean : dao.readSomeEmployeeList(page)) {
				LOGGER.debug(employeeBean.toString());
			}

			break;
		case 4:
			LOGGER.info("상태 :: 조회 코드 입력(해당 사원)");
			emp = dao.readEmployee(8000);
			LOGGER.debug(emp.toString());
			
			break;
		case 5:
			LOGGER.info("상태 :: 갱신 코드 입력");
			emp.setEmpNo(8000);
			emp.seteName("LUCY");
			successed = dao.updateEmployee(emp);
			if (successed >0) {
				LOGGER.info("성공적으로 데이터가 갱신됨");
			}
			break;
		case 6:
			LOGGER.info("상태 :: 삭제 코드 입력");
			emp.setEmpNo(8000);
			successed = dao.deleteEmployee(emp);
			if (successed > 0) {
				LOGGER.info("성공적으로 데이터가 삭제됨");
			}
			break;
		}
	}
}
