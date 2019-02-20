package version3.kr.sys4u.jdbc.dto;

import java.io.Serializable;
import java.sql.Date;

public class Employee implements Serializable{
	
	private static final long serialVersionUID = -4312910826157323298L;
	
	private String eName, job;
	private int empNo,mgr,sal, comm, deptNo;
	private Date hireDate;
	
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
	public Date getHireDate() {
		return hireDate;
	}
	public void setHireDate(Date hireDate) {
		this.hireDate = hireDate;
	}
	public void setHireDate(String hireDate) {
		this.hireDate = Date.valueOf(hireDate);
	}
	
	@Override
	public String toString() {
		
		return String.format("EmployeeBean [eName= %6s, job= %9s, empNo= %5d, mgr= %5d, sal= %5d"
				+ ",comm= %4d, deptNo= %2d, hireDate= %10s]", eName,job,empNo,mgr,sal,comm,deptNo,hireDate);
	} 
	
	
	
}
