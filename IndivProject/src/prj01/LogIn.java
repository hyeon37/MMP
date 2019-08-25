package prj01;

import java.util.Vector;

public class LogIn {
	private String id;
	private char[] pw;
	
	// ==========================Singleton==========================
	
	public static LogIn instance;
	
	private LogIn() {	}
	
	public static LogIn getInstance() {
		if (instance == null) {
			instance = new LogIn();
		}
		return instance;
	}
	
	// ==========================Singleton==========================
	
	/**
	 * get ID&PASSWORD info
	 * @param id
	 * @param pw
	 */
	public void getInfo(String id,  char[] pw) {
		this.id = id;
		this.pw = pw;
	}
	
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public char[] getPw() {
		return pw;
	}

	public void setPw(char[] pw) {
		this.pw = pw;
	}

	/**
	 * Check ID&PASSWORD 
	 * if getinfo data are same to db data, return true
	 * else return false
	 * @return
	 */
	public boolean chkLogin() {
		LogInDao dao = LogInDao.getInstance();
		Vector<LogVo> vec = dao.chkAccount(id);
		
		String pw = "";
		
		for(char c : this.pw) {
			Character.toString(c);
			pw += c;
		}

		for (LogVo vo : vec) {
			if (id.equals(vo.getId()) && pw.equals(vo.getPw())) {
				return true;
			}
		}
		return false;
	}
}