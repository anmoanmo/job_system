package src.jobSystem.com.jobs.util;

public class Position {
	
	//属性
	private int companyNumber;      //编号
	private String companyId;     //账号
	private String companyName;       //公司名称
	private String companyPosition;      //职位名称
	private String companyPerson;     //联系人
	private String companyPhone;  //电话
	private String companyCount;    //人数
	private String companyLearn;    //学历
	private String companyRequest;      //职称
	private String companyMoney;    //薪资
	private String companyAddress;      //地址
	private int companySign;        //删除标志
	private int companyFlag;        //禁用标志
	private int staffSum;
	
	public Position() {}
	public Position(String companyId,String companyName,String companyPosition,String companyPerson,String companyPhone,String companyCount,String companyLearn,String companyRequest,String companyMoney,String companyAddress) {
		this.companyId=companyId;
		this.companyName=companyName;
		this.companyPosition=companyPosition;
		this.companyPerson=companyPerson;
		this.companyPhone=companyPhone;
		this.companyCount=companyCount;
		this.companyLearn=companyLearn;
		this.companyRequest=companyRequest;
		this.companyMoney=companyMoney;
		this.companyAddress=companyAddress;
	}
	public Position(String companyId,String companyName,String companyPosition,String companyPerson,String companyPhone,String companyCount,String companyLearn,String companyRequest,String companyMoney,String companyAddress,int companySign,int companyFlag) {
		this.companyId=companyId;
		this.companyName=companyName;
		this.companyPosition=companyPosition;
		this.companyPerson=companyPerson;
		this.companyPhone=companyPhone;
		this.companyCount=companyCount;
		this.companyLearn=companyLearn;
		this.companyRequest=companyRequest;
		this.companyMoney=companyMoney;
		this.companyAddress=companyAddress;
		this.companySign=companySign;
		this.companyFlag=companyFlag;		
	}
	
	public int getCompanyNumber() {
		return companyNumber;
	}
	public void setCompanyNumber(int companyNumber) {
		this.companyNumber = companyNumber;
	}
	public String getCompanyId() {
		return companyId;
	}
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getCompanyPosition() {
		return companyPosition;
	}
	public void setCompanyPosition(String companyPosition) {
		this.companyPosition = companyPosition;
	}
	public String getCompanyPerson() {
		return companyPerson;
	}
	public void setCompanyPerson(String companyPerson) {
		this.companyPerson = companyPerson;
	}
	public String getCompanyPhone() {
		return companyPhone;
	}
	public void setCompanyPhone(String companyPhone) {
		this.companyPhone = companyPhone;
	}
	public String getCompanyCount() {
		return companyCount;
	}
	public void setCompanyCount(String companyCount) {
		this.companyCount = companyCount;
	}
	public String getCompanyLearn() {
		return companyLearn;
	}
	public void setCompanyLearn(String companyLearn) {
		this.companyLearn = companyLearn;
	}
	public String getCompanyRequest() {
		return companyRequest;
	}
	public void setCompanyRequest(String companyRequest) {
		this.companyRequest = companyRequest;
	}
	public String getCompanyMoney() {
		return companyMoney;
	}
	public void setCompanyMoney(String companyMoney) {
		this.companyMoney = companyMoney;
	}
	public String getCompanyAddress() {
		return companyAddress;
	}
	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}
	public int getCompanySign() {
		return companySign;
	}
	public void setCompanySign(int companySign) {
		this.companySign = companySign;
	}
	public int getCompanyFlag() {
		return companyFlag;
	}
	public void setCompanyFlag(int companyFlag) {
		this.companyFlag = companyFlag;
	}
	public int getStaffSum() {
		return staffSum;
	}
	public void setStaffSum(int staffSum) {
		this.staffSum = staffSum;
	}

}
