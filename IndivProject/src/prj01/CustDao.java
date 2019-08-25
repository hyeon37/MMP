package prj01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

public class CustDao {

	private final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	private final String URL = "jdbc:oracle:thin:@localhost:1521:xe"; // 어느 DB
	private final String ID = "jangan";
	private final String PW = "1234";
	
	private static CustDao instance;
	
	private CustDao() {
		// singleton
	}
	
	public static CustDao getInstance() {
		if (instance == null) {
			instance = new CustDao();
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
	 * 거래처 번호 자동
	 * @return
	 */
	public Vector<CustDto> getCustNo() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			String sql = "select * from customer order by cust_no";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			Vector<CustDto> vec = new Vector<>();
			while(rs.next()) {
				int custNo = rs.getInt("cust_no");
				String custName = rs.getString("cust_name");
				int licNum1 = rs.getInt("business_license1");
				int licNum2 = rs.getInt("business_license2");
				int licNum3 = rs.getInt("business_license3");
				String regDate = rs.getString("trade_reg_date");
				int phoneNum1 = rs.getInt("phone_no1");
				int phoneNum2 = rs.getInt("phone_no2");
				int phoneNum3 = rs.getInt("phone_no3");
				
				CustDto dto = new CustDto(custNo, custName, licNum1, licNum2, licNum3,
						regDate, phoneNum1, phoneNum2, phoneNum3);
				vec.add(dto);
			}
			return vec;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll(conn, pstmt, rs);
		}
		return null;
	} // =============================================getCustDao=============================================
	
	/**
	 * 거래처 등록
	 * @param vo
	 * @return
	 */
	public int insertCust(CustVo vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();
			conn.setAutoCommit(false); // 자동커밋 해제
			String sql = "insert into customer (cust_no, cust_name,"
					+ "	  business_license1, business_license2, business_license3, "
					+ "	  trade_reg_date, phone_no1, phone_no2, phone_no3)"
					+ "	  values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			int i = 0;
			pstmt.setInt(++i, vo.getCustNo());
			pstmt.setString(++i, vo.getCustName());
			pstmt.setInt(++i, vo.getLicNum1());
			pstmt.setInt(++i, vo.getLicNum2());
			pstmt.setInt(++i, vo.getLicNum3());
			pstmt.setString(++i, vo.getRegDate());
			pstmt.setInt(++i, vo.getPhoneNum1());
			pstmt.setInt(++i, vo.getPhoneNum2());
			pstmt.setInt(++i, vo.getPhoneNum3());
			// select - executeQuery(); / insert, update, delete - executeUpdate();
			int count = pstmt.executeUpdate();
			conn.commit(); // 구문이 완료되면 커밋 실행
			return count;
		} catch (Exception e) {
			try {
				conn.rollback(); // 구문이 실패하면 롤백
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		} finally {
			try {
				conn.setAutoCommit(true); // 완료되면 자동커밋 다시 실행
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			closeAll(conn, pstmt, null);
		}
		return 0;
	} // =============================================insertCust=============================================
	
	/**
	 * 거래처 검색
	 * @param choice
	 * @param str
	 * @return
	 */
	public Vector<CustDto> searchCust(String str) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			String sql = "";
			sql = "select * from customer where cust_name like '%' || ? || '%' order by cust_no";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, str);
			rs = pstmt.executeQuery();
			Vector<CustDto> vec = new Vector<>();
			while(rs.next()) {
				int custNo = rs.getInt("cust_no");
				String custName = rs.getString("cust_name");
				int licNum1 = rs.getInt("business_license1");
				int licNum2 = rs.getInt("business_license2");
				int licNum3 = rs.getInt("business_license3");
				String regDate = rs.getString("trade_reg_date");
				int phoneNum1 = rs.getInt("phone_no1");
				int phoneNum2 = rs.getInt("phone_no2");
				int phoneNum3 = rs.getInt("phone_no3");
				
				CustDto dto = new CustDto(custNo, custName, licNum1, licNum2, licNum3,
						regDate, phoneNum1, phoneNum2, phoneNum3);
				vec.add(dto);
			}
			return vec;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll(conn, pstmt, rs);
		}
		return null;
	} // =============================================searchCust=============================================
	
	/**
	 * 거래처 목록
	 * @return
	 */
	public Vector<CustDto> getCustList() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			String sql = "";
			sql = "select * from customer order by cust_no";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			Vector<CustDto> vec = new Vector<>();
			while(rs.next()) {
				int custNo = rs.getInt("cust_no");
				String custName = rs.getString("cust_name");
				int licNum1 = rs.getInt("business_license1");
				int licNum2 = rs.getInt("business_license2");
				int licNum3 = rs.getInt("business_license3");
				String regDate = rs.getString("trade_reg_date");
				int phoneNum1 = rs.getInt("phone_no1");
				int phoneNum2 = rs.getInt("phone_no2");
				int phoneNum3 = rs.getInt("phone_no3");
				
				CustDto vo = new CustDto(custNo, custName, licNum1, licNum2, licNum3,
						regDate, phoneNum1, phoneNum2, phoneNum3);
				vec.add(vo);
			}
			return vec;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll(conn, pstmt, rs);
		}
		return null;
	} // =============================================getcustList=============================================
	
}
