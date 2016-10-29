package com.cusTrip.model;

import java.util.*;
import java.sql.*;

public class CusTripJDBCDAO implements CusTripDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "TravelFun";
	String passwd = "travelfun";
	
	private static final String INSERT_STMT =
			"INSERT INTO cus_trip ( cus_trip_id, cus_id, trip_name, date_st, share_yn, share_date) VALUES ('CT'||LPAD(CUS_TRIP_ID_SEQ.NEXTVAL,6,'0'), ?, ?, ?, ?, ?)";
	private static final String UPDATE =
			"UPDATE cus_trip SET trip_name = ?, date_st = ?, share_yn = ?, share_date = ? WHERE cus_trip_id = ?";
	private static final String DELETE = 
			"DELETE FROM cus_trip WHERE cus_trip_id = ?";
	private static final String GET_ONE_STMT =
			"SELECT cus_trip_id, cus_id, trip_name, to_char(date_st, 'yyyy-mm-dd') date_st, share_yn, to_char(share_date, 'yyyy-mm-dd hh24:mi:ss') share_date FROM cus_trip WHERE cus_trip_id = ?";
	private static final String GET_ALL_STMT =
			"SELECT cus_trip_id, cus_id, trip_name, to_char(date_st,'yyyy-mm-dd') date_st, share_yn, to_char(share_date, 'yyyy-mm-dd hh24:mi:ss') share_date FROM cus_trip ORDER BY cus_trip_id";
	
	@Override
	public void insert(CusTripVO cusTripVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try{
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, cusTripVO.getCusId());
			pstmt.setString(2, cusTripVO.getTripName());
			pstmt.setDate(3, cusTripVO.getDateSt());
			pstmt.setString(4, cusTripVO.getShareYn());
			pstmt.setTimestamp(5, cusTripVO.getShareDate());
			
			pstmt.executeUpdate();
		}
		// Handle any driver errors
		catch(ClassNotFoundException e){
			throw new RuntimeException("Could not load database driver. "+e.getMessage());
		}
		// Handle any SQL errors
		catch(SQLException se){
			throw new RuntimeException("A database error occured. "+se.getMessage());
		}
		// Clean up JDBC resources
		finally{
			if(pstmt != null){
				try{
					pstmt.close();
				}catch(SQLException se){
					se.printStackTrace(System.err);
				}
			}
			if(con != null){
				try{
					con.close();
				}catch(Exception e){
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public void update(CusTripVO cusTripVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try{
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setString(1, cusTripVO.getTripName());
			pstmt.setDate(2, cusTripVO.getDateSt());
			pstmt.setString(3, cusTripVO.getShareYn());
			pstmt.setTimestamp(4, cusTripVO.getShareDate());
			pstmt.setString(5, cusTripVO.getCusTripId());
			
			pstmt.executeUpdate();
		}
		// Handle any driver errors
		catch(ClassNotFoundException e){
			throw new RuntimeException("Could not load database driver. "+e.getMessage());
		}
		// Handle any SQL errors
		catch(SQLException se){
			throw new RuntimeException("A database error occured. "+se.getMessage());
		}
		// Clean up JDBC resources
		finally{
			if(pstmt != null){
				try{
					pstmt.close();
				}catch(SQLException se){
					se.printStackTrace(System.err);
				}
			}
			if(con != null){
				try{
					con.close();
				}catch(Exception e){
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public void delete(String cusTripId) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try{
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setString(1, cusTripId);
			
			
			pstmt.executeUpdate();
		}
		// Handle any driver errors
		catch(ClassNotFoundException e){
			throw new RuntimeException("Could not load database driver. "+e.getMessage());
		}
		// Handle any SQL errors
		catch(SQLException se){
			throw new RuntimeException("A database error occured. "+se.getMessage());
		}
		// Clean up JDBC resources
		finally{
			if(pstmt != null){
				try{
					pstmt.close();
				}catch(SQLException se){
					se.printStackTrace(System.err);
				}
			}
			if(con != null){
				try{
					con.close();
				}catch(Exception e){
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public CusTripVO findByPrimaryKey(String cusTripId) {
		CusTripVO cusTripVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setString(1, cusTripId);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				cusTripVO = new CusTripVO();
				cusTripVO.setCusTripId(rs.getString("cus_trip_id"));
				cusTripVO.setCusId(rs.getString("cus_id"));
				cusTripVO.setTripName(rs.getString("trip_name"));
				cusTripVO.setDateSt(rs.getDate("date_st"));
				cusTripVO.setShareYn(rs.getString("share_yn"));
				cusTripVO.setShareDate(rs.getTimestamp("share_date"));
				
			}
		}
		// Handle any driver errors
		catch(ClassNotFoundException e){
			throw new RuntimeException("Could not load database driver. "+e.getMessage());
		}
		// Handle any SQL errors
		catch(SQLException se){
			throw new RuntimeException("A database error occured. "+se.getMessage());
		}
		// Clean up JDBC resources
		finally{
			if(rs != null){
				try{
					rs.close();
				}catch(SQLException se){
					se.printStackTrace(System.err);
				}
			}
			if(pstmt != null){
				try{
					pstmt.close();
				}catch(SQLException se){
					se.printStackTrace(System.err);
				}
			}
			if(con != null){
				try{
					con.close();
				}catch(Exception e){
					e.printStackTrace(System.err);
				}
			}
		}
		return cusTripVO;
	}

	@Override
	public List<CusTripVO> getAll() {
		List<CusTripVO> list = new ArrayList<CusTripVO>();
		CusTripVO cusTripVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				cusTripVO = new CusTripVO();
				cusTripVO.setCusTripId(rs.getString("cus_trip_id"));
				cusTripVO.setCusId(rs.getString("cus_id"));
				cusTripVO.setTripName(rs.getString("trip_name"));
				cusTripVO.setDateSt(rs.getDate("date_st"));
				cusTripVO.setShareYn(rs.getString("share_yn"));
				cusTripVO.setShareDate(rs.getTimestamp("share_date"));
				list.add(cusTripVO);
			}
		}
		// Handle any driver errors
		catch(ClassNotFoundException e){
			throw new RuntimeException("Could not load database driver. "+e.getMessage());
		}
		// Handle any SQL errors
		catch(SQLException se){
			throw new RuntimeException("A database error occured. "+se.getMessage());
		}
		// Clean up JDBC resources
		finally{
			if(rs != null){
				try{
					rs.close();
				}catch(SQLException se){
					se.printStackTrace(System.err);
				}
			}
			if(pstmt != null){
				try{
					pstmt.close();
				}catch(SQLException se){
					se.printStackTrace(System.err);
				}
			}
			if(con != null){
				try{
					con.close();
				}catch(Exception e){
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}
	
	public static void main(String[] args){
		CusTripJDBCDAO dao = new CusTripJDBCDAO();
		
		//insert
//		CusTripVO cusTripVO1 = new CusTripVO();
//		cusTripVO1.setCusId("CU000001");
//		cusTripVO1.setTripName("��銋��");
//		cusTripVO1.setDateSt(java.sql.Date.valueOf("2017-01-15"));
//		cusTripVO1.setShareYn("N");
//		dao.insert(cusTripVO1);
		
		//update
//		CusTripVO cusTripVO2 = new CusTripVO();
//		cusTripVO2.setCusTripId("CT000011");
//		cusTripVO2.setCusId("CU000003");
//		cusTripVO2.setTripName("摰銋��");
//		cusTripVO2.setDateSt(java.sql.Date.valueOf("2017-01-15"));
//		cusTripVO2.setShareYn("Y");
//		cusTripVO2.setShareDate(java.sql.Timestamp.valueOf("2017-03-03 18:00:00"));
//		dao.update(cusTripVO2);
		
		//delete
//		dao.delete("CT000011");
		
		//query one data
//		CusTripVO cusTripVO3 = dao.findByPrimaryKey("CT000011");
//		System.out.print(cusTripVO3.getCusTripId()+" 嚗�  ");
//		System.out.print(cusTripVO3.getCusId()+" 嚗�  ");
//		System.out.print(cusTripVO3.getTripName()+" 嚗�  ");
//		System.out.print(cusTripVO3.getDateSt()+" 嚗�  ");		
//		System.out.print(cusTripVO3.getShareYn()+" 嚗�  ");
//		System.out.println(cusTripVO3.getShareDate());

		
		//query all data
//		List<CusTripVO> list = dao.getAll();
//		for(CusTripVO cusTripVO4 : list){
//			System.out.print(cusTripVO4.getCusTripId()+" 嚗�  ");
//			System.out.print(cusTripVO4.getCusId()+" 嚗�  ");
//			System.out.print(cusTripVO4.getTripName()+" 嚗�  ");
//			System.out.print(cusTripVO4.getDateSt()+" 嚗�  ");
//			System.out.print(cusTripVO4.getShareYn()+" 嚗�  ");
//			System.out.println(cusTripVO4.getShareDate());
//			System.out.println("----------------------------");
//		}
		
	}

}
