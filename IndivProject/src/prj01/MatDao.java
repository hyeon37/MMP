package prj01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

public class MatDao {
	
	
	private final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	private final String URL = "jdbc:oracle:thin:@localhost:1521:xe"; // 어느 DB
	private final String ID = "jangan";
	private final String PW = "1234";
	
	private static MatDao instance;
	
	private MatDao() {
		// singleton
	}
	
	public static MatDao getInstance() {
		if (instance == null) {
			instance = new MatDao();
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
			// TODO: handle exception
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
	 * 품목별 검색
	 * @param choice
	 * @param str
	 * @return
	 */
	public Vector<MatDto> searchMat(int choice, String str) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			
			String sql = "select * from mat_info"
					+ " where cate_no = " + choice + " and other_type like '%' || ? || '%'"
					+ " order by receive_date";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, str);
			
			rs = pstmt.executeQuery();
			Vector<MatDto> vec = new Vector<>();
			while(rs.next()) {
				int cate_no = rs.getInt("cate_no");
				String other_type = rs.getString("other_type");
				int size1 = rs.getInt("size1");
				int size2 = rs.getInt("size2");
				int size3 = rs.getInt("size3");
				int quantity = rs.getInt("quantity");
				int unit_price = rs.getInt("unit_price");
				int unit_set = rs.getInt("unit_set");
				String receive_date = rs.getString("receive_date");
				int cust_no = rs.getInt("cust_no");
				long item_serial = rs.getLong("item_serial");
				
				MatDto dto = new MatDto(cate_no, other_type, size1, size2, size3, quantity, unit_price,
						unit_set, receive_date, cust_no, item_serial);
				vec.add(dto);
			}
			return vec;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll(conn, pstmt, rs);
		}
		return null;
	} // =============================================searchMat=============================================
	
	/**
	 * 날짜별 검색
	 * @param str
	 * @return
	 */
	public Vector<MatDto> searchDate(String str) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			String sql = "";
			if (str.equals("")) {
				sql = "select * from mat_info order by receive_date";
			} else {
				sql = "select * from mat_info where receive_date = '%' || " + str + " || '%' order by receive_date";
			}
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			Vector<MatDto> vec = new Vector<>();
			while(rs.next()) {
				int cateNo = rs.getInt("cate_no");
				String otherType = rs.getString("other_type");
				int size1 = rs.getInt("size1");
				int size2 = rs.getInt("size2");
				int size3 = rs.getInt("size3");
				int quantity = rs.getInt("quantity");
				int unit_price = rs.getInt("unit_price");
				int unit_set = rs.getInt("unit_set");
				String receive_date = rs.getString("receive_date");
				int cust_no = rs.getInt("cust_no");
				long item_serial = rs.getLong("item_serial");
				
				MatDto dto = new MatDto(cateNo, otherType, size1, size2, size3, quantity, unit_price,
						unit_set, receive_date, cust_no, item_serial);
				vec.add(dto);
			}
			return vec;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll(conn, pstmt, rs);
		}
		return null;
	} // =============================================searchDate=============================================
	

	/**
	 * 품목리스트
	 * @return
	 */
	public Vector<MatCateVo> getMatList() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			String sql = "select * from mat_category";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			Vector<MatCateVo> vec = new Vector<>();
			while (rs.next()) {
				String cateName = rs.getString("cate_name");
				int cateNo = rs.getInt("cate_no");
				
				MatCateVo vo = new MatCateVo(cateName, cateNo);
				vec.add(vo);
			}
			return vec;
		} catch (Exception e) {
			e.getStackTrace();
		} finally {
			closeAll(conn, pstmt, rs);
		}
		return null;
	} // =============================================getMatList=============================================
	
	/**
	 * 단위리스트
	 * @return
	 */
	public Vector<Integer> getUnitList() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			String sql = "select * from unit_set";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			Vector<Integer> vec = new Vector<>();
			while (rs.next()) {
				int unitSet = rs.getInt("unit_set");
				
				vec.add(unitSet);
			}
			return vec;
		} catch (Exception e) {
			e.getStackTrace();
		} finally {
			closeAll(conn, pstmt, rs);
		}
		return null;
	} // =============================================getMatList=============================================

	/**
	 * 단위리스트
	 * @return
	 */
	public Vector<Long> getSerialList() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			String sql = "select * from mat_info";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			Vector<Long> vec = new Vector<>();
			while (rs.next()) {
				long item_serial = rs.getLong("item_serial");
				
				vec.add(item_serial);
			}
			return vec;
		} catch (Exception e) {
			e.getStackTrace();
		} finally {
			closeAll(conn, pstmt, rs);
		}
		return null;
	} // =============================================getMatList=============================================
	
	
	// insert
	public int insertMat(MatVo vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();
			String sql = "insert into mat_info"
					+ "	  values(?,?,?,?,?,?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			int i = 0;
			pstmt.setInt(++i, vo.getCateNo());
			pstmt.setString(++i, vo.getOtherType());
			pstmt.setInt(++i, vo.getSize1());
			pstmt.setInt(++i, vo.getSize2());
			pstmt.setInt(++i, vo.getSize3());
			pstmt.setInt(++i, vo.getQuantity());
			pstmt.setInt(++i, vo.getUnitPrice());
			pstmt.setInt(++i, vo.getUnitSet());
			pstmt.setString(++i, vo.getReceiveDate());
			pstmt.setInt(++i, vo.getCustNo());
			pstmt.setLong(++i, vo.getItemSerial());
			// select - executeQuery(); / insert, update, delete - executeUpdate();
			int count = pstmt.executeUpdate();
			return count;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll(conn, pstmt, null);
		}
		return 0;
	} // =============================================insertMat=============================================

	
	// select *
	public Vector<MatDto> selectAllMat() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			String sql = "select * from mat_info"
					+ "   order by receive_date desc";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			Vector<MatDto> vec = new Vector<>();
			while(rs.next()) {
				int cateNo = rs.getInt("cate_no");
				String otherType = rs.getString("other_type");
				int size1 = rs.getInt("size1");
				int size2 = rs.getInt("size2");
				int size3 = rs.getInt("size3");
				int quantity = rs.getInt("quantity");
				int unit_price = rs.getInt("unit_price");
				int unit_set = rs.getInt("unit_set");
				String receive_date = rs.getString("receive_date");
				int cust_no = rs.getInt("cust_no");
				long item_serial = rs.getLong("item_serial");
				
				MatDto dto = new MatDto(cateNo, otherType, size1, size2, size3, quantity, unit_price,
						unit_set, receive_date, cust_no, item_serial);
				vec.add(dto);
			}
			return vec;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll(conn, pstmt, rs);
		}
		return null;
	} // =============================================selectAllMat=============================================
	
	
	
		
	// update
	public int update(int quan, Long serial) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();
			String sql = "update mat_info set"
					+ "   quantity = ?"
					+ "   where item_serial = ?";
			int i = 0;
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(++i, quan);
			pstmt.setLong(++i, serial);
			int count = pstmt.executeUpdate();
			return count;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll(conn, pstmt, null);	
		}
		return 0;
	}
	
	
	// delete
//	public int delete(int empno) {
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		
//		try {
//			conn = getConnection();
//			String sql = "delete from emp01"
//					+ "	  where empno = ?";
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setInt(1, empno);
//			int count = pstmt.executeUpdate();
//			return count;
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			closeAll(conn, pstmt, null);
//		}
//		return 0;
//	}

}
