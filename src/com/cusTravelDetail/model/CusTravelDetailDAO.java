package com.cusTravelDetail.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class CusTravelDetailDAO implements CusTravelDetailDAO_interface {
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDBG1");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = 
			"INSERT INTO CUS_TRAVEL_DETAIL (CUS_TRAVEL_DETAIL_ID,CUS_TRAVEL_NOTE_ID,DATE_RECORD,CONTENT,DETAIL_NAME) VALUES (CUS_TRAVEL_DETAIL_ID_SEQ.NEXTVAL, ?, ?, ?, ?)";
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
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, cusTravelDetailVO.getCusTravelNoteId());
			pstmt.setDate(2, cusTravelDetailVO.getDateRecord());
			pstmt.setString(3, cusTravelDetailVO.getContent());
			pstmt.setString(4, cusTravelDetailVO.getDetailName());

			updateCount = pstmt.executeUpdate();

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
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, cusTravelDetailVO.getCusTravelDetailId());
			pstmt.setString(2, cusTravelDetailVO.getCusTravelNoteId());
			pstmt.setDate(3, cusTravelDetailVO.getDateRecord());
			pstmt.setString(4, cusTravelDetailVO.getContent());
			pstmt.setString(5, cusTravelDetailVO.getDetailName());
			pstmt.setString(6, cusTravelDetailVO.getCusTravelDetailId());

			updateCount = pstmt.executeUpdate();

			// Handle any SQL errors
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
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, cusTravelDetailId);

			updateCount = pstmt.executeUpdate();

			// Handle any SQL errors
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
			con = ds.getConnection();
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

			// Handle any SQL Exception
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
			con = ds.getConnection();
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

			// Handle any SQL Exception
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

}// CusTravelDetailJDBCDAO
