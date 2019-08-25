package prj01;

public class MatVo {
	private int cateNo;
	private String otherType;
	private int size1;
	private int size2;
	private int size3;
	private int quantity;
	private int unitPrice;
	private int unitSet;
	private String receiveDate;
	private int custNo;
	private long itemSerial;
	
	public MatVo() {
		super();
	}

	public MatVo(int cateNo, String otherType, int size1, int size2, int size3, int quantity, int unitPrice,
			int unitSet, String receiveDate, int custNo, long itemSerial) {
		super();
		this.cateNo = cateNo;
		this.otherType = otherType;
		this.size1 = size1;
		this.size2 = size2;
		this.size3 = size3;
		this.quantity = quantity;
		this.unitPrice = unitPrice;
		this.unitSet = unitSet;
		this.receiveDate = receiveDate;
		this.custNo = custNo;
		this.itemSerial = itemSerial;
	}
	
	public int getCateNo() {
		return cateNo;
	}

	public void setCateNo(int cateNo) {
		this.cateNo = cateNo;
	}

	public String getOtherType() {
		return otherType;
	}

	public void setOtherType(String otherType) {
		this.otherType = otherType;
	}

	public int getSize1() {
		return size1;
	}

	public void setSize1(int size1) {
		this.size1 = size1;
	}

	public int getSize2() {
		return size2;
	}

	public void setSize2(int size2) {
		this.size2 = size2;
	}

	public int getSize3() {
		return size3;
	}

	public void setSize3(int size3) {
		this.size3 = size3;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(int unitPrice) {
		this.unitPrice = unitPrice;
	}

	public int getUnitSet() {
		return unitSet;
	}

	public void setUnitSet(int unitSet) {
		this.unitSet = unitSet;
	}

	public String getReceiveDate() {
		return receiveDate;
	}

	public void setReceiveDate(String receiveDate) {
		this.receiveDate = receiveDate;
	}

	public int getCustNo() {
		return custNo;
	}

	public void setCustNo(int custNo) {
		this.custNo = custNo;
	}

	public long getItemSerial() {
		return itemSerial;
	}

	public void setItemSerial(long itemSerial) {
		this.itemSerial = itemSerial;
	}
}
