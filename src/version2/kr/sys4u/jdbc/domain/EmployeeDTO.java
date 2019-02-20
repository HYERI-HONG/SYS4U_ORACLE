package version2.kr.sys4u.jdbc.domain;

public class EmployeeDTO {
	private String eName, job, hireDate;
	private int empNo,mgr,sal, comm, deptNo;
	
	public String geteName() {
		return eName;
	}
	public void seteName(String eName) {
		this.eName = eName;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public int getEmpNo() {
		return empNo;
	}
	public void setEmpNo(int empNo) {
		this.empNo = empNo;
	}
	public int getMgr() {
		return mgr;
	}
	public void setMgr(int mgr) {
		this.mgr = mgr;
	}
	public int getSal() {
		return sal;
	}
	public void setSal(int sal) {
		this.sal = sal;
	}
	public int getComm() {
		return comm;
	}
	public void setComm(int comm) {
		this.comm = comm;
	}
	public int getDeptNo() {
		return deptNo;
	}
	public void setDeptNo(int deptNo) {
		this.deptNo = deptNo;
	}
	public String getHireDate() {
		return hireDate;
	}
	public void setHireDate(String hireDate) {
		this.hireDate = hireDate;
	}
	@Override
	public String toString() {
		
		return String.format("EmployeeBean [eName= %6s, job= %9s, empNo= %5d, mgr= %5d, sal= %5d"
				+ ",comm= %4d, deptNo= %2d, hireDate= %10s]", eName,job,empNo,mgr,sal,comm,deptNo,hireDate);
	} 
	
}
