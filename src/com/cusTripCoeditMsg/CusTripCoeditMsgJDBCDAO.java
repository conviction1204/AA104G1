package com.cusTripCoeditMsg;

import java.util.*;
import java.sql.*;

public class CusTripCoeditMsgJDBCDAO implements CusTripCoeditMsgDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "TravelFun";
	String passwd = "travelfun";
	
	private static final String INSERT_STMT =
			"INSERT INTO cus_trip_coedit_msg ( cus_trip_coedit_msg_id, cus_trip_id, cus_id, content, date_record) VALUES ('CTCM'||LPAD(CUS_TRIP_COEDIT_MSG_ID_SEQ.NEXTVAL,6,'0'), ?, ?, ?, ?)";
	private static final String UPDATE =
			"UPDATE cus_trip_coedit_msg SET content = ?, date_record = ? WHERE cus_trip_coedit_msg_id = ?";
	private static final String DELETE = 
			"DELETE FROM cus_trip_coedit_msg WHERE cus_trip_coedit_msg_id = ?";
	private static final String GET_ONE_STMT =
			"SELECT cus_trip_coedit_msg_id, cus_trip_id, cus_id, content, to_char(date_record, 'yyyy-mm-dd hh24:mi:ss') date_record FROM cus_trip_coedit_msg WHERE cus_trip_coedit_msg_id = ?";
	private static final String GET_ALL_STMT =
			"SELECT cus_trip_coedit_msg_id, cus_trip_id, cus_id, content, to_char(date_record,'yyyy-mm-dd hh24:mi:ss') date_record FROM cus_trip_coedit_msg ORDER BY cus_trip_coedit_msg_id";

	@Override
	public void insert(CusTripCoeditMsgVO cusTripCoeditMsgVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try{
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, cusTripCoeditMsgVO.getCusTripId());
			pstmt.setString(2, cusTripCoeditMsgVO.getCusId());
			pstmt.setString(3, cusTripCoeditMsgVO.getContent());
			pstmt.setTimestamp(4, cusTripCoeditMsgVO.getDateRecord());
			
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
	public void update(CusTripCoeditMsgVO cusTripCoeditMsgVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try{
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setString(1, cusTripCoeditMsgVO.getContent());
			pstmt.setTimestamp(2, cusTripCoeditMsgVO.getDateRecord());
			pstmt.setString(3, cusTripCoeditMsgVO.getCusTripCoeditMsgId());
			
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
	public void delete(String cusTripCoeditMsgId) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try{
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setString(1, cusTripCoeditMsgId);
			
			
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
	public CusTripCoeditMsgVO findByPrimaryKey(String cusTripCoeditMsgId) {
		CusTripCoeditMsgVO cusTripCoeditMsgVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setString(1, cusTripCoeditMsgId);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				cusTripCoeditMsgVO = new CusTripCoeditMsgVO();
				cusTripCoeditMsgVO.setCusTripCoeditMsgId(rs.getString("cus_trip_coedit_msg_id"));
				cusTripCoeditMsgVO.setCusTripId(rs.getString("cus_trip_id"));
				cusTripCoeditMsgVO.setCusId(rs.getString("cus_id"));
				cusTripCoeditMsgVO.setContent(rs.getString("content"));
				cusTripCoeditMsgVO.setDateRecord(rs.getTimestamp("date_record"));
				
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
		return cusTripCoeditMsgVO;
	}

	@Override
	public List<CusTripCoeditMsgVO> getAll() {
		List<CusTripCoeditMsgVO> list = new ArrayList<CusTripCoeditMsgVO>();
		CusTripCoeditMsgVO cusTripCoeditMsgVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				cusTripCoeditMsgVO = new CusTripCoeditMsgVO();
				cusTripCoeditMsgVO.setCusTripCoeditMsgId(rs.getString("cus_trip_coedit_msg_id"));
				cusTripCoeditMsgVO.setCusTripId(rs.getString("cus_trip_id"));
				cusTripCoeditMsgVO.setCusId(rs.getString("cus_id"));
				cusTripCoeditMsgVO.setContent(rs.getString("content"));
				cusTripCoeditMsgVO.setDateRecord(rs.getTimestamp("date_record"));
				list.add(cusTripCoeditMsgVO);
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

	public static void main(String[] args) {
		CusTripCoeditMsgJDBCDAO dao = new CusTripCoeditMsgJDBCDAO();
		
		//insert
//		CusTripCoeditMsgVO cusTripCoeditMsgVO1 = new CusTripCoeditMsgVO();
//		cusTripCoeditMsgVO1.setCusTripId("CT000005");
//		cusTripCoeditMsgVO1.setCusId("CU000001");
//		cusTripCoeditMsgVO1.setContent("安安你好~~~");
//		cusTripCoeditMsgVO1.setDateRecord(java.sql.Timestamp.valueOf("2015-01-16 20:12:02"));
//		dao.insert(cusTripCoeditMsgVO1);
		
		//update
//		CusTripCoeditMsgVO cusTripCoeditMsgVO2 = new CusTripCoeditMsgVO();
//		cusTripCoeditMsgVO2.setCusTripCoeditMsgId("CTCM000011");
//		cusTripCoeditMsgVO2.setContent("安安你好啊");
//		cusTripCoeditMsgVO2.setDateRecord(java.sql.Timestamp.valueOf("2015-01-16 20:20:02"));
//		dao.update(cusTripCoeditMsgVO2);
		
		//delete
//		dao.delete("CTCM000011");
		
		//query one data
//		CusTripCoeditMsgVO cusTripCoeditMsgVO3 = dao.findByPrimaryKey("CTCM000011");
//		System.out.print(cusTripCoeditMsgVO3.getCusTripCoeditMsgId()+" ，  ");
//		System.out.print(cusTripCoeditMsgVO3.getCusTripId()+" ，  ");
//		System.out.print(cusTripCoeditMsgVO3.getCusId()+" ，  ");
//		System.out.print(cusTripCoeditMsgVO3.getContent()+" ，  ");
//		System.out.println(cusTripCoeditMsgVO3.getDateRecord());
		
//		query all data
		List<CusTripCoeditMsgVO> list = dao.getAll();
		for(CusTripCoeditMsgVO cusTripCoeditMsgVO4 : list){
			System.out.print(cusTripCoeditMsgVO4.getCusTripCoeditMsgId()+" ，  ");
			System.out.print(cusTripCoeditMsgVO4.getCusTripId()+" ，  ");
			System.out.print(cusTripCoeditMsgVO4.getCusId()+" ，  ");
			System.out.print(cusTripCoeditMsgVO4.getContent()+" ，  ");
			System.out.println(cusTripCoeditMsgVO4.getDateRecord());
			System.out.println("----------------------------");
		}
		
	}

}
