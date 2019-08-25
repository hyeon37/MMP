package prj01;

public class CustDto {
	private int cust_no;
	private String cust_name;
	private int business_license1;
	private int business_license2;
	private int business_license3;
	private String trade_reg_date;
	private int phone_no1;
	private int phone_no2;
	private int phone_no3;
	
	public CustDto(int cust_no, String cust_name, int business_license1, int business_license2, int business_license3,
			String trade_reg_date, int phone_no1, int phone_no2, int phone_no3) {
		super();
		this.cust_no = cust_no;
		this.cust_name = cust_name;
		this.business_license1 = business_license1;
		this.business_license2 = business_license2;
		this.business_license3 = business_license3;
		this.trade_reg_date = trade_reg_date;
		this.phone_no1 = phone_no1;
		this.phone_no2 = phone_no2;
		this.phone_no3 = phone_no3;
	}
	
	public int getCust_no() {
		return cust_no;
	}
	public void setCust_no(int cust_no) {
		this.cust_no = cust_no;
	}
	public String getCust_name() {
		return cust_name;
	}
	public void setCust_name(String cust_name) {
		this.cust_name = cust_name;
	}
	public int getBusiness_license1() {
		return business_license1;
	}
	public void setBusiness_license1(int business_license1) {
		this.business_license1 = business_license1;
	}
	public int getBusiness_license2() {
		return business_license2;
	}
	public void setBusiness_license2(int business_license2) {
		this.business_license2 = business_license2;
	}
	public int getBusiness_license3() {
		return business_license3;
	}
	public void setBusiness_license3(int business_license3) {
		this.business_license3 = business_license3;
	}
	public String getTrade_reg_date() {
		return trade_reg_date;
	}
	public void setTrade_reg_date(String trade_reg_date) {
		this.trade_reg_date = trade_reg_date;
	}
	public int getPhone_no1() {
		return phone_no1;
	}
	public void setPhone_no1(int phone_no1) {
		this.phone_no1 = phone_no1;
	}
	public int getPhone_no2() {
		return phone_no2;
	}
	public void setPhone_no2(int phone_no2) {
		this.phone_no2 = phone_no2;
	}
	public int getPhone_no3() {
		return phone_no3;
	}
	public void setPhone_no3(int phone_no3) {
		this.phone_no3 = phone_no3;
	}
}
