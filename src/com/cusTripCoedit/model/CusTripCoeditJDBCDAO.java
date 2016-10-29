package com.cusTripCoedit.model;

import java.util.*;
import java.sql.*;

public class CusTripCoeditJDBCDAO implements CusTripCoeditDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "TravelFun";
	String passwd = "travelfun";
	
	private static final String INSERT_STMT =
			"INSERT INTO cus_trip_coedit (cus_trip_id, cus_id, status, content) VALUES ( ?, ?, ?, ?)";
	private static final String UPDATE =
			"UPDATE cus_trip_coedit SET status = ?, content = ? WHERE cus_trip_id = ? AND cus_id = ?";
	private static final String DELETE = 
			"DELETE FROM cus_trip_coedit WHERE cus_trip_id = ? AND cus_id = ?";
	private static final String GET_ONE_STMT =
			"SELECT cus_trip_id, cus_id, status, content FROM cus_trip_coedit WHERE cus_trip_id = ? AND cus_id = ?";
	private static final String GET_ALL_STMT =
			"SELECT cus_trip_id, cus_id, status, content FROM cus_trip_coedit ORDER BY cus_trip_id";
	
	@Override
	public void insert(CusTripCoeditVO cusTripCoeditVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try{
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, cusTripCoeditVO.getCusTripId());
			pstmt.setString(2, cusTripCoeditVO.getCusId());
			pstmt.setString(3, cusTripCoeditVO.getStatus());
			pstmt.setString(4, cusTripCoeditVO.getContent());
			
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
	public void update(CusTripCoeditVO cusTripCoeditVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try{
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setString(1, cusTripCoeditVO.getStatus());
			pstmt.setString(2, cusTripCoeditVO.getContent());
			pstmt.setString(3, cusTripCoeditVO.getCusTripId());
			pstmt.setString(4, cusTripCoeditVO.getCusId());
			
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
	public void delete(String cusTripId, String cusId) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try{
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setString(1, cusTripId);
			pstmt.setString(2, cusId);
			
			
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
	public CusTripCoeditVO findByPrimaryKey(String cusTripId, String cusId) {
		CusTripCoeditVO cusTripCoeditVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setString(1, cusTripId);
			pstmt.setString(2, cusId);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				cusTripCoeditVO = new CusTripCoeditVO();
				cusTripCoeditVO.setCusTripId(rs.getString("cus_trip_id"));
				cusTripCoeditVO.setCusId(rs.getString("cus_id"));
				cusTripCoeditVO.setStatus(rs.getString("status"));
				cusTripCoeditVO.setContent(rs.getString("content"));
				
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
		return cusTripCoeditVO;
	}

	@Override
	public List<CusTripCoeditVO> getAll() {
		List<CusTripCoeditVO> list = new ArrayList<CusTripCoeditVO>();
		CusTripCoeditVO cusTripCoeditVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				cusTripCoeditVO = new CusTripCoeditVO();
				cusTripCoeditVO.setCusTripId(rs.getString("cus_trip_id"));
				cusTripCoeditVO.setCusId(rs.getString("cus_id"));
				cusTripCoeditVO.setStatus(rs.getString("status"));
				cusTripCoeditVO.setContent(rs.getString("content"));
				list.add(cusTripCoeditVO);
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
		CusTripCoeditJDBCDAO dao = new CusTripCoeditJDBCDAO();
		
		//insert
//		CusTripCoeditVO cusTripCoeditVO1 = new CusTripCoeditVO();
//		cusTripCoeditVO1.setCusTripId("CT000005");
//		cusTripCoeditVO1.setCusId("CU000004");
//		cusTripCoeditVO1.setStatus("C");
//		dao.insert(cusTripCoeditVO1);
		
		//update
//		CusTripCoeditVO cusTripCoeditVO2 = new CusTripCoeditVO();
//		cusTripCoeditVO2.setCusTripId("CT000005");
//		cusTripCoeditVO2.setCusId("CU000004");
//		cusTripCoeditVO2.setStatus("Y");
//		cusTripCoeditVO2.setContent("要注意回程時間");
//		dao.update(cusTripCoeditVO2);
		
		//delete
// 		dao.delete("CT000005","CU000004");
		
		//query one data
//		CusTripCoeditVO cusTripCoeditVO3 = dao.findByPrimaryKey("CT000005","CU000004");
//		System.out.print(cusTripCoeditVO3.getCusTripId()+" ，  ");
//		System.out.print(cusTripCoeditVO3.getCusId()+" ，  ");
//		System.out.println(cusTripCoeditVO3.getStatus());
		
		//query all data
//		List<CusTripCoeditVO> list = dao.getAll();
//		for(CusTripCoeditVO cusTripCoeditVO4 : list){
//			System.out.print(cusTripCoeditVO4.getCusTripId()+" ，  ");
//			System.out.print(cusTripCoeditVO4.getCusId()+" ，  ");
//			System.out.println(cusTripCoeditVO4.getStatus());
//			System.out.println(cusTripCoeditVO4.getContent());
//			System.out.println("----------------------------");
//		}
		
	}

}
