package com.cusTravelDetail.model;

import java.util.*;

import com.cusTravelNote.model.CusTravelNoteJDBCDAO;
import com.cusTravelNote.model.CusTravelNoteVO;

import java.sql.*;

public class CusTravelDetailJDBCDAO implements CusTravelDetailDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "FUN";
	String passwd = "aa104g1";

	private static final String INSERT_STMT = 
			"INSERT INTO CUS_TRAVEL_DETAIL (CUS_TRAVEL_DETAIL_ID,CUS_TRAVEL_NOTE_ID,DATE_RECORD,CONTENT,DETAIL_NAME) VALUES ('CTD'||LPAD(CUS_TRAVEL_DETAIL_ID_SEQ.NEXTVAL,6,'0'), ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
			"SELECT CUS_TRAVEL_DETAIL_ID,CUS_TRAVEL_NOTE_ID,to_char(DATE_RECORD,'yyyy-mm-dd') DATE_RECORD,CONTENT,DETAIL_NAME FROM CUS_TRAVEL_DETAIL order by CUS_TRAVEL_DETAIL_ID";
	private static final String GET_ONE_STMT = 
			"SELECT CUS_TRAVEL_DETAIL_ID,CUS_TRAVEL_NOTE_ID,to_char(DATE_RECORD,'yyyy-mm-dd') DATE_RECORD,CONTENT,DETAIL_NAME FROM CUS_TRAVEL_DETAIL where CUS_TRAVEL_DETAIL_ID = ?";
	private static final String DELETE = 
			"DELETE FROM CUS_TRAVEL_DETAIL where CUS_TRAVEL_DETAIL_ID = ?";
	private static final String UPDATE = 
			"UPDATE CUS_TRAVEL_DETAIL set CUS_TRAVEL_DETAIL_ID=?,CUS_TRAVEL_NOTE_ID=?,DATE_RECORD=?,CONTENT=?,DETAIL_NAME=? where CUS_TRAVEL_DETAIL_ID = ?";

	@Override
	public int insert(CusTravelDetailVO cusTravelDetailVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int updateCount = 0;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, cusTravelDetailVO.getCusTravelNoteId());
			pstmt.setDate(2, cusTravelDetailVO.getDateRecord());
			pstmt.setString(3, cusTravelDetailVO.getContent());
			pstmt.setString(4, cusTravelDetailVO.getDetailName());

			updateCount = pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				} // catch
			} // if(pstmt)
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				} // catch
			} // if(con)
		} // finally
		return updateCount;
	}// insert

	@Override
	public int update(CusTravelDetailVO cusTravelDetailVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int updateCount = 0;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, cusTravelDetailVO.getCusTravelDetailId());
			pstmt.setString(2, cusTravelDetailVO.getCusTravelNoteId());
			pstmt.setDate(3, cusTravelDetailVO.getDateRecord());
			pstmt.setString(4, cusTravelDetailVO.getContent());
			pstmt.setString(5, cusTravelDetailVO.getDetailName());
			pstmt.setString(6, cusTravelDetailVO.getCusTravelDetailId());

			updateCount = pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		} // finally
		return updateCount;
	}// update

	@Override
	public int delete(String cusTravelDetailId) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int updateCount = 0;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, cusTravelDetailId);

			updateCount = pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					// TODO Auto-generated catch block
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		} // finally
		return updateCount;
	}

	@Override
	public CusTravelDetailVO findByPrimaryKey(String cusTravelDetailId) {
		CusTravelDetailVO cusTravelDetailVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, cusTravelDetailId);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// cusTravelDetailVO 也稱為 Domain objects(object?)
				cusTravelDetailVO = new CusTravelDetailVO();
				cusTravelDetailVO.setCusTravelDetailId(rs.getString("CUS_TRAVEL_DETAIL_ID"));
				cusTravelDetailVO.setCusTravelNoteId(rs.getString("CUS_TRAVEL_NOTE_ID"));
				cusTravelDetailVO.setDateRecord(rs.getDate("DATE_RECORD"));
				cusTravelDetailVO.setContent(rs.getString("CONTENT"));
				cusTravelDetailVO.setDetailName(rs.getString("DETAIL_NAME"));
			}

			// Handle anyu driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					// TODO Auto-generated catch block
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
		} // finally

		return cusTravelDetailVO;
	}

	@Override
	public List<CusTravelDetailVO> getAll() {
		List<CusTravelDetailVO> list = new ArrayList<CusTravelDetailVO>();
		CusTravelDetailVO cusTravelDetailVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				cusTravelDetailVO = new CusTravelDetailVO();
				cusTravelDetailVO.setCusTravelDetailId(rs.getString("CUS_TRAVEL_DETAIL_ID"));
				cusTravelDetailVO.setCusTravelNoteId(rs.getString("CUS_TRAVEL_NOTE_ID"));
				cusTravelDetailVO.setDateRecord(rs.getDate("DATE_RECORD"));
				cusTravelDetailVO.setContent(rs.getString("CONTENT"));
				cusTravelDetailVO.setDetailName(rs.getString("DETAIL_NAME"));
				list.add(cusTravelDetailVO);// store the row(record) in the list
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
		} // finally
		return list;
	}// getAll

	public static void main(String[] args) {

		CusTravelDetailJDBCDAO dao = new CusTravelDetailJDBCDAO();

		// //新增
		for(int i=1; i<10; i++){
			CusTravelDetailVO cusTravelDetailVO1 = new CusTravelDetailVO();
			cusTravelDetailVO1.setCusTravelNoteId("CTN000002");
			cusTravelDetailVO1.setDateRecord(java.sql.Date.valueOf("2016-10-25"));
			cusTravelDetailVO1.setContent("遊記細節內容"+i);
			cusTravelDetailVO1.setDetailName("遊記細節標題名稱"+i);
			dao.insert(cusTravelDetailVO1);
		}
		// //修改
		CusTravelDetailVO cusTravelDetailVO2 = new CusTravelDetailVO();
		cusTravelDetailVO2.setCusTravelDetailId("CTD000004");
		cusTravelDetailVO2.setCusTravelNoteId("CTN000002");
		cusTravelDetailVO2.setDateRecord(java.sql.Date.valueOf("2019-10-25"));
		cusTravelDetailVO2.setContent("遊記細節內容_改");
		cusTravelDetailVO2.setDetailName("遊記細節標題名稱_改");
		dao.update(cusTravelDetailVO2);

		// 刪除
		dao.delete("CTD000005");
		//
		// //查詢 by pk
		CusTravelDetailVO cusTravelDetailVO3 = dao.findByPrimaryKey("CTD000001");
		System.out.print(cusTravelDetailVO3.getCusTravelDetailId() + ",");
		System.out.print(cusTravelDetailVO3.getCusTravelNoteId() + ",");
		System.out.print(cusTravelDetailVO3.getDateRecord() + ",");
		System.out.print(cusTravelDetailVO3.getContent() + ",");
		System.out.println(cusTravelDetailVO3.getDetailName());
		System.out.println("-------------------------");

		// //查詢 all
		List<CusTravelDetailVO> list = dao.getAll();
		for (CusTravelDetailVO aCusTravelDetail : list) {
			System.out.print(aCusTravelDetail.getCusTravelDetailId() + ",");
			System.out.print(aCusTravelDetail.getCusTravelNoteId() + ",");
			System.out.print(aCusTravelDetail.getDateRecord() + ",");
			System.out.print(aCusTravelDetail.getContent() + ",");
			System.out.println(aCusTravelDetail.getDetailName());
			// System.out.println();
		} // for
	}// main

}// CusTravelDetailJDBCDAO
