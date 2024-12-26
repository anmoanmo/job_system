package src.jobSystem.com.jobs.util;

public class Company {
	//属性
	private int companyNumber;
	private String companyName;
	private String companyId;
	private String companyPwd;
	private int companyRole;
	private int companySign;
	
	//构造方法
	public Company() {}
	public Company(String companyName, String companyId, String companyPwd) {
		this.companyName = companyName;
		this.companyId = companyId;
		this.companyPwd = companyPwd;
	}
	public Company(String companyName, String companyId, String companyPwd, int companyRole, int companySign) {
		this.companyName = companyName;
		this.companyId = companyId;
		this.companyPwd = companyPwd;
		this.companyRole = companyRole;
		this.companySign = companySign;
	}
	
	//get和set方法
	public int getCompanyNumber() {
		return companyNumber;
	}
	public void setCompanyNumber(int companyNumber) {
		this.companyNumber = companyNumber;
	}
	public String getCompanyName() {
		return companyName;
	}	
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getCompanyId() {
		return companyId;
	}
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	public String getCompanyPwd() {
		return companyPwd;
	}
	public void setCompanyPwd(String companyPwd) {
		this.companyPwd = companyPwd;
	}
	public int getCompanyRole() {
		return companyRole;
	}
	public void setCompanyRole(int companyRole) {
		this.companyRole = companyRole;
	}
	public int getCompanySign() {
		return companySign;
	}
	public void setCompanySign(int companySign) {
		this.companySign = companySign;
	}
	
}
