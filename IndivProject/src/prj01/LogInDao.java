package prj01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

public class LogInDao {

	private final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	private final String URL = "jdbc:oracle:thin:@localhost:1521:xe"; // 어느 DB
	private final String ID = "jangan";
	private final String PW = "1234";
	
	private static LogInDao instance;
	
	private LogInDao() {
		// singleton
	}
	
	public static LogInDao getInstance() {
		if (instance == null) {
			instance = new LogInDao();
		}
		return instance;
	}
	
	// get connection - search, selectAll, insert, update, delete에서 공통으로 사용
	private Connection getConnection() {
		try {
			// 드라이버를 메모리에 로드
			Class.forName(DRIVER);
			Connection conn = DriverManager.getConnection(URL, ID, PW);
			return conn;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private void closeAll(Connection conn, PreparedStatement pstmt, ResultSet rs) {
		if (rs != null) try { rs.close(); } catch (Exception e) { }
		if (pstmt != null) try { pstmt.close(); } catch (Exception e) { }
		if (conn != null) try { conn.close(); } catch (Exception e) { }
	}
	
	/**
	 * 아이디 체크
	 * @param getId
	 * @return
	 */
	public Vector<LogVo> chkAccount(String getId) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			String sql = "select * from jangan_user where user_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, getId);
			rs = pstmt.executeQuery();
			Vector<LogVo> vec = new Vector<>();
			while(rs.next()) {
				String id = rs.getString("user_id");
				String pw = rs.getString("user_pw");
				LogVo logVo = new LogVo(id, pw);
				vec.add(logVo);
			}
			return vec;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll(conn, pstmt, rs);
		}
		return null;
	} // =============================================chkAccount=============================================
}
