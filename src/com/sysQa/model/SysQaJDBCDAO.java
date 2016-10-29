package com.sysQa.model;
import java.sql.*;
import java.util.*;

public class SysQaJDBCDAO implements SysQaDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "TravelFun";
	String passwd = "travelfun";
	
	private static final String INSERT_STMT =
			"INSERT INTO sys_qa ( sys_qa_id, que_content, ans_content) VALUES ('SQ'||LPAD(SYS_QA_ID_SEQ.NEXTVAL,6,'0'), ?, ?)"; 
	private static final String UPDATE =
			"UPDATE sys_qa SET que_content=?, ans_content = ? WHERE sys_qa_id = ?";
	private static final String DELETE = 
			"DELETE FROM sys_qa WHERE sys_qa_id = ?";
	private static final String GET_ONE_STMT =
			"SELECT sys_qa_id, que_content, ans_content FROM sys_qa WHERE sys_qa_id = ?";
	private static final String GET_ALL_STMT =
			"SELECT sys_qa_id, que_content, ans_content FROM sys_qa ORDER BY sys_qa_id";
	

	@Override
	public void insert(SysQaVO sysQaVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try{
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, sysQaVO.getQueContent());
			pstmt.setString(2, sysQaVO.getAnsContent());
			
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
	public void update(SysQaVO sysQaVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try{
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setString(1, sysQaVO.getQueContent());
			pstmt.setString(2, sysQaVO.getAnsContent());
			pstmt.setString(3, sysQaVO.getSysQaId());
			
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
	public void delete(String sysQaId) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try{
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setString(1, sysQaId);
			
			
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
	public SysQaVO findByPrimaryKey(String sysQaId) {
		SysQaVO sysQaVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setString(1, sysQaId);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				sysQaVO = new SysQaVO();
				sysQaVO.setSysQaId(rs.getString("sys_qa_id"));
				sysQaVO.setQueContent(rs.getString("que_content"));
				sysQaVO.setAnsContent(rs.getString("ans_content"));
				
				
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
		return sysQaVO;
	}

	@Override
	public List<SysQaVO> getAll() {
		List<SysQaVO> list = new ArrayList<SysQaVO>();
		SysQaVO sysQaVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				sysQaVO = new SysQaVO();
				sysQaVO.setSysQaId(rs.getString("sys_qa_id"));
				sysQaVO.setQueContent(rs.getString("que_content"));
				sysQaVO.setAnsContent(rs.getString("ans_content"));
				list.add(sysQaVO);
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
		SysQaJDBCDAO dao = new SysQaJDBCDAO();
		
		//insert
//		SysQaVO sysQaVO1 = new SysQaVO();
//		sysQaVO1.setQueContent("問題十一");
//		sysQaVO1.setAnsContent("答案十一");
//		dao.insert(sysQaVO1);
		
		//update
//		SysQaVO sysQaVO2 = new SysQaVO();
//		sysQaVO2.setSysQaId("SQ000011");
//		sysQaVO2.setQueContent("問題十一");
//		sysQaVO2.setAnsContent("答案十一new");
//		dao.update(sysQaVO2);
		
		//delete
//		dao.delete("SQ000011");
		
		//query one data
//		SysQaVO sysQaVO3 = dao.findByPrimaryKey("SQ000011");
//		System.out.print(sysQaVO3.getSysQaId()+" ，  ");
//		System.out.print(sysQaVO3.getQueContent()+" ，  ");
//		System.out.println(sysQaVO3.getAnsContent());
		
		//query all data
//		List<SysQaVO> list = dao.getAll();
//		for(SysQaVO sysQaVO4 : list){
//			System.out.print(sysQaVO4.getSysQaId()+" ，  ");
//			System.out.print(sysQaVO4.getQueContent()+" ，  ");
//			System.out.println(sysQaVO4.getAnsContent());
//			System.out.println("----------------------------");
//		}
		
	}

}
