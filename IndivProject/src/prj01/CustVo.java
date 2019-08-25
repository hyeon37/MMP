package prj01;

public class CustVo {
	private int custNo;
	private String custName;
	private int licNum1;
	private int licNum2;
	private int licNum3;
	private String regDate;
	private int phoneNum1;
	private int phoneNum2;
	private int phoneNum3;
	
	public CustVo(int custNo, String custName, int licNum1, int licNum2, int licNum3,
			String regDate, int phoneNum1, int phoneNum2, int phoneNum3) {
		super();
		this.custNo = custNo;
		this.custName = custName;
		this.licNum1 = licNum1;
		this.licNum2 = licNum2;
		this.licNum3 = licNum3;
		this.regDate = regDate;
		this.phoneNum1 = phoneNum1;
		this.phoneNum2 = phoneNum2;
		this.phoneNum3 = phoneNum3;
	}
	
	public int getCustNo() {
		return custNo;
	}
	
	public void setCustNo(int custNo) {
		this.custNo = custNo;
	}
	
	public String getCustName() {
		return custName;
	}


	public void setCustName(String custName) {
		this.custName = custName;
	}


	public int getLicNum1() {
		return licNum1;
	}


	public void setLicNum1(int licNum1) {
		this.licNum1 = licNum1;
	}


	public int getLicNum2() {
		return licNum2;
	}


	public void setLicNum2(int licNum2) {
		this.licNum2 = licNum2;
	}


	public int getLicNum3() {
		return licNum3;
	}


	public void setLicNum3(int licNum3) {
		this.licNum3 = licNum3;
	}


	public String getRegDate() {
		return regDate;
	}


	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}


	public int getPhoneNum1() {
		return phoneNum1;
	}


	public void setPhoneNum1(int phoneNum1) {
		this.phoneNum1 = phoneNum1;
	}


	public int getPhoneNum2() {
		return phoneNum2;
	}


	public void setPhoneNum2(int phoneNum2) {
		this.phoneNum2 = phoneNum2;
	}


	public int getPhoneNum3() {
		return phoneNum3;
	}


	public void setPhoneNum3(int phoneNum3) {
		this.phoneNum3 = phoneNum3;
	}

}
