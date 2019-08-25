package prj01;

public class MatCateVo {
	private String cateName;
	private int cateNo;
	
	public MatCateVo(String cateName, int cateNo) {
		super();
		this.cateName = cateName;
		this.cateNo = cateNo;
	}

	public String getCateName() {
		return cateName;
	}

	public void setCateName(String cateName) {
		this.cateName = cateName;
	}

	public int getCateNo() {
		return cateNo;
	}

	public void setCateNo(int cateNo) {
		this.cateNo = cateNo;
	}
	
	
}
