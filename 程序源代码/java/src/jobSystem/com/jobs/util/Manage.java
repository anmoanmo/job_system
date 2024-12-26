package src.jobSystem.com.jobs.util;

public class Manage {
	//属性
	private String manageName;
	private String manageId;
	private String managePwd;
	private int manageRole;
	private int manageSign;
	
	//构造方法
	public Manage() {}
	public Manage(String manageName, String manageId, String managePwd) {
		this.manageName = manageName;
		this.manageId = manageId;
		this.managePwd = managePwd;
	}
	public Manage(String manageName, String manageId, String managePwd, int manageRole, int manageSign) {
		this.manageName = manageName;
		this.manageId = manageId;
		this.managePwd = managePwd;
		this.manageRole = manageRole;
		this.manageSign = manageSign;
	}
	
	//get和set方法
	public String getManageName() {
		return manageName;
	}
	public void setManageName(String manageName) {
		this.manageName = manageName;
	}
	public String getManageId() {
		return manageId;
	}
	public void setManageId(String manageId) {
		this.manageId = manageId;
	}
	public String getManagePwd() {
		return managePwd;
	}
	public void setManagePwd(String managePwd) {
		this.managePwd = managePwd;
	}
	public int getManageRole() {
		return manageRole;
	}
	public void setManageRole(int manageRole) {
		this.manageRole = manageRole;
	}
	public int getManageSign() {
		return manageSign;
	}
	public void setManageSign(int manageSign) {
		this.manageSign = manageSign;
	}
	
}
