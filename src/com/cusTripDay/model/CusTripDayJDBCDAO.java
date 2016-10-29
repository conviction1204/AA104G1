package com.cusTripDay.model;

import java.util.*;
import java.sql.*;

public class CusTripDayJDBCDAO implements CusTripDayDAO_interface{
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "TravelFun";
	String passwd = "travelfun";
	
	private static final String INSERT_STMT =
			"INSERT INTO cus_trip_day ( cus_trip_day_id, cus_trip_id, day_number, date_st) VALUES ('CTD'||LPAD(CUS_TRIP_DAY_ID_SEQ.NEXTVAL,6,'0'), ?, ?, ?)";
	private static final String UPDATE =
			"UPDATE cus_trip_day SET date_st = ? WHERE cus_trip_day_id = ?";
	private static final String DELETE = 
			"DELETE FROM cus_trip_day WHERE cus_trip_day_id = ?";
	private static final String GET_ONE_STMT =
			"SELECT cus_trip_day_id, cus_trip_id, day_number, to_char(date_st, 'yyyy-mm-dd hh24:mi:ss') date_st FROM cus_trip_day WHERE cus_trip_day_id = ?";
	private static final String GET_ALL_STMT =
			"SELECT cus_trip_day_id, cus_trip_id, day_number, to_char(date_st,'yyyy-mm-dd hh24:mi:ss') date_st FROM cus_trip_day ORDER BY cus_trip_day_id";
	
	@Override
	public void insert(CusTripDayVO cusTripDayVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try{
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, cusTripDayVO.getCusTripId());
			pstmt.setInt(2, cusTripDayVO.getDayNumber());
			pstmt.setTimestamp(3, cusTripDayVO.getDateSt());
			
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
	public void update(CusTripDayVO cusTripDayVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try{
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setTimestamp(1, cusTripDayVO.getDateSt());
			pstmt.setString(2, cusTripDayVO.getCusTripDayId());
			
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
	public void delete(String cusTripDayId) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try{
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setString(1, cusTripDayId);
			
			
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
	public CusTripDayVO findByPrimaryKey(String cusTripDayId) {
		CusTripDayVO cusTripDayVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setString(1, cusTripDayId);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				cusTripDayVO = new CusTripDayVO();
				cusTripDayVO.setCusTripDayId(rs.getString("cus_trip_day_id"));
				cusTripDayVO.setCusTripId(rs.getString("cus_trip_id"));
				cusTripDayVO.setDayNumber(rs.getInt("day_number"));
				cusTripDayVO.setDateSt(rs.getTimestamp("date_st"));
				
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
		return cusTripDayVO;
	}

	@Override
	public List<CusTripDayVO> getAll() {
		List<CusTripDayVO> list = new ArrayList<CusTripDayVO>();
		CusTripDayVO cusTripDayVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				cusTripDayVO = new CusTripDayVO();
				cusTripDayVO.setCusTripDayId(rs.getString("cus_trip_day_id"));
				cusTripDayVO.setCusTripId(rs.getString("cus_trip_id"));
				cusTripDayVO.setDayNumber(rs.getInt("day_number"));
				cusTripDayVO.setDateSt(rs.getTimestamp("date_st"));
				list.add(cusTripDayVO);
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
		CusTripDayJDBCDAO dao = new CusTripDayJDBCDAO();
		
		//insert
//		CusTripDayVO cusTripDayVO1 = new CusTripDayVO();
//		cusTripDayVO1.setCusTripId("CT000006");
//		cusTripDayVO1.setDayNumber(1);
//		cusTripDayVO1.setDateSt(java.sql.Timestamp.valueOf("2015-03-03 08:00:00"));
//		dao.insert(cusTripDayVO1);
		
		//update
//		CusTripDayVO cusTripDayVO2 = new CusTripDayVO();
//		cusTripDayVO2.setCusTripDayId("CTD000011");
//		cusTripDayVO2.setDateSt(java.sql.Timestamp.valueOf("2015-03-03 10:30:00"));
//		dao.update(cusTripDayVO2);
		
		//delete
//		dao.delete("CTD000011");
		
		//query one data
//		CusTripDayVO cusTripDayVO3 = dao.findByPrimaryKey("CTD000011");
//		System.out.print(cusTripDayVO3.getCusTripDayId()+" ，  ");
//		System.out.print(cusTripDayVO3.getCusTripId()+" ，  ");
//		System.out.print(cusTripDayVO3.getDayNumber()+" ，  ");
//		System.out.println(cusTripDayVO3.getDateSt());
		
		//query all data
//		List<CusTripDayVO> list = dao.getAll();
//		for(CusTripDayVO cusTripDayVO4 : list){
//			System.out.print(cusTripDayVO4.getCusTripDayId()+" ，  ");
//			System.out.print(cusTripDayVO4.getCusTripId()+" ，  ");
//			System.out.print(cusTripDayVO4.getDayNumber()+" ，  ");
//			System.out.println(cusTripDayVO4.getDateSt());
//			System.out.println("----------------------------");
//		}
		
	}

}
