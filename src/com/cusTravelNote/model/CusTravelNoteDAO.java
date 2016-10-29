package com.cusTravelNote.model;

import java.sql.*;
import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


public class CusTravelNoteDAO implements CusTravelNoteDAO_interface {

	private static DataSource ds = null;
	static{
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDBG1");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	private static final String INSERT_STMT =
			"INSERT INTO CUS_TRAVEL_NOTE (CUS_TRAVEL_NOTE_ID,CUS_ID,CUS_TRIP_ID,DATE_RECORD,NOTE_NAME) VALUES (CUS_TRAVEL_NOTE_ID_SEQ.NEXTVAL, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT =
			"SELECT CUS_TRAVEL_NOTE_ID,CUS_ID,CUS_TRIP_ID,to_char(DATE_RECORD,'yyyy-mm-dd') DATE_RECORD,NOTE_NAME FROM CUS_TRAVEL_NOTE order by CUS_TRAVEL_NOTE_ID";
	private static final String GET_ONE_STMT =
			"SELECT CUS_TRAVEL_NOTE_ID,CUS_ID,CUS_TRIP_ID,to_char(DATE_RECORD,'yyyy-mm-dd') DATE_RECORD,NOTE_NAME FROM CUS_TRAVEL_NOTE where CUS_TRAVEL_NOTE_ID = ?";
	private static final String DELETE =
			"DELETE FROM CUS_TRAVEL_NOTE where CUS_TRAVEL_NOTE_ID = ?";
	private static final String UPDATE =
			"UPDATE CUS_TRAVEL_NOTE set CUS_TRAVEL_NOTE_ID=?,CUS_ID=?,CUS_TRIP_ID=?,DATE_RECORD=?,NOTE_NAME=? where CUS_TRAVEL_NOTE_ID = ?";
	
	@Override
	public int insert(CusTravelNoteVO cusTravelNoteVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int updateCount = 0;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, cusTravelNoteVO.getCusId());
			pstmt.setString(2, cusTravelNoteVO.getCusTripId());
			pstmt.setDate(3, cusTravelNoteVO.getDateRecord());
			pstmt.setString(4, cusTravelNoteVO.getNoteName());
			
			updateCount = pstmt.executeUpdate();
			
		}catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			//Clean up JDBC resources
		}finally{
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}//catch
			}//if(pstmt)
			if(con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}//catch
			}//if(con)
		}//finally
		return updateCount;
	}//insert

	@Override
	public int update(CusTravelNoteVO cusTravelNoteVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int updateCount = 0;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setString(1, cusTravelNoteVO.getCusTravelNoteId());
			pstmt.setString(2, cusTravelNoteVO.getCusId());
			pstmt.setString(3, cusTravelNoteVO.getCusTripId());
			pstmt.setDate(4, cusTravelNoteVO.getDateRecord());
			pstmt.setString(5, cusTravelNoteVO.getNoteName());
			pstmt.setString(6, cusTravelNoteVO.getCusTravelNoteId());
			
			updateCount = pstmt.executeUpdate();
			
			//Handle any SQL errors
		} 
		catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+se.getMessage());
			//Clean up JDBC resources
		}finally{
			if(pstmt !=null){
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if(con != null){
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}//finally
		return updateCount;
	}//update

	@Override
	public int delete(String cusTravelNoteId) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		int updateCount = 0;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setString(1, cusTravelNoteId);
			
			updateCount = pstmt.executeUpdate();
			
			//Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			//Clean up JDBC resources
		}finally{
			if(pstmt != null){
				try {
					pstmt.close();
				} catch (SQLException se) {
					// TODO Auto-generated catch block
					se.printStackTrace(System.err);
				}
			}
			if(con != null){
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}//finally
		return updateCount;		
	}

	@Override
	public CusTravelNoteVO findByPrimaryKey(String cusTravelNoteId) {
		
		CusTravelNoteVO cusTravelNoteVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setString(1, cusTravelNoteId);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				//cusTravelNoteVO 也稱為 Domain objects(object?)
				cusTravelNoteVO = new CusTravelNoteVO();
				cusTravelNoteVO.setCusTravelNoteId(rs.getString("CUS_TRAVEL_NOTE_ID"));
				cusTravelNoteVO.setCusId(rs.getString("CUS_ID"));
				cusTravelNoteVO.setCusTripId(rs.getString("CUS_TRIP_ID"));
				cusTravelNoteVO.setDateRecord(rs.getDate("DATE_RECORD"));
				cusTravelNoteVO.setNoteName(rs.getString("NOTE_NAME"));	
			}
			
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		}finally{
			if(rs != null){
				try {
					rs.close();
				} catch (SQLException se) {
					// TODO Auto-generated catch block
					se.printStackTrace(System.err);
				}
			}
			if(pstmt != null){
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if(con != null){
				try {
					con.close();
				} catch (SQLException se) {
					// TODO Auto-generated catch block
					se.printStackTrace(System.err);
				}
			}
		}//finally
		
		return cusTravelNoteVO;
	}

	@Override
	public List<CusTravelNoteVO> getAll() {
		List<CusTravelNoteVO> list = new ArrayList<CusTravelNoteVO>();
		CusTravelNoteVO cusTravelNoteVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while (rs.next()){
				cusTravelNoteVO = new CusTravelNoteVO();
				cusTravelNoteVO.setCusTravelNoteId(rs.getString("CUS_TRAVEL_NOTE_ID"));
				cusTravelNoteVO.setCusId(rs.getString("CUS_ID"));
				cusTravelNoteVO.setCusTripId(rs.getString("CUS_TRIP_ID"));
				cusTravelNoteVO.setDateRecord(rs.getDate("DATE_RECORD"));
				cusTravelNoteVO.setNoteName(rs.getString("NOTE_NAME"));
				list.add(cusTravelNoteVO);// store the row(record) in the list
			}
			
			// Handle any SQL errors
		} 
		catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+se.getMessage());
			// Clean up JDBC resources
		}finally{
			if(rs !=null){
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if(pstmt != null){
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if(con != null){
				try {
					con.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
		}//finally
		return list;
	}
	
	public static void main(String[] args){
		
		CusTravelNoteDAO dao = new CusTravelNoteDAO();
		
//		//新增
		CusTravelNoteVO cusTravelNoteVO1 = new CusTravelNoteVO();
		cusTravelNoteVO1.setCusId("1101");
		cusTravelNoteVO1.setCusTripId("2201");
		cusTravelNoteVO1.setDateRecord(java.sql.Date.valueOf("2016-10-25"));
		cusTravelNoteVO1.setNoteName("遊記名稱");
		dao.insert(cusTravelNoteVO1);
		
//		//修改
		CusTravelNoteVO cusTravelNoteVO2 = new CusTravelNoteVO();
		cusTravelNoteVO2.setCusTravelNoteId("2602");
		cusTravelNoteVO2.setCusId("1105");
		cusTravelNoteVO2.setCusTripId("2205");
		cusTravelNoteVO2.setDateRecord(java.sql.Date.valueOf("2014-10-24"));
		cusTravelNoteVO2.setNoteName("遊記名稱改了呦");
		dao.update(cusTravelNoteVO2);
		
		//刪除
		dao.delete("2604");
//		
//		//查詢 by pk
		CusTravelNoteVO cusTravelNoteVO3 = dao.findByPrimaryKey("2601");
		System.out.print(cusTravelNoteVO3.getCusTravelNoteId() + ",");
		System.out.print(cusTravelNoteVO3.getCusId() + ",");
		System.out.print(cusTravelNoteVO3.getCusTripId() + ",");
		System.out.print(cusTravelNoteVO3.getDateRecord() + ",");
		System.out.println(cusTravelNoteVO3.getNoteName());
		System.out.println("-------------------------");
		
//		//查詢 all
		List<CusTravelNoteVO> list = dao.getAll();
		for (CusTravelNoteVO aCusTravelNote : list){
			System.out.print(aCusTravelNote.getCusTravelNoteId() + ",");
			System.out.print(aCusTravelNote.getCusId() + ",");
			System.out.print(aCusTravelNote.getCusTripId() + ",");
			System.out.print(aCusTravelNote.getDateRecord() + ",");
			System.out.println(aCusTravelNote.getNoteName());
			//System.out.println();
		}//for
	}//main
	
}//CusTravelNoteJDBCDAO