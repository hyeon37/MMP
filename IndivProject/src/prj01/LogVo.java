package prj01;

public class LogVo {
	private String id;
	private String pw;
	
	public LogVo(String id, String pw) {
		super();
		this.id = id;
		this.pw = pw;
	}

	public LogVo() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	@Override
	public String toString() {
		return "LogVo [id=" + id + ", pw=" + pw + "]";
	}
	
}
