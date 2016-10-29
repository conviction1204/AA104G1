package com.cusTravelPic.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.cusTravelNote.model.CusTravelNoteJDBCDAO;
import com.cusTravelNote.model.CusTravelNoteVO;

public class CusTravelPicJNDIDAO implements CusTravelPicDAO_interface {
	
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
			"INSERT INTO CUS_TRAVEL_PIC (CUS_TRAVEL_PIC_ID, CUS_TRAVEL_DETAIL_ID, PIC_NAME, PIC_FILETYPE, DATE_RECORD, PIC) VALUES ('CTP'||LPAD(to_char(CUS_TRAVEL_PIC_ID_SEQ.NEXTVAL),6,'0'), ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT =
			"SELECT CUS_TRAVEL_PIC_ID, CUS_TRAVEL_DETAIL_ID, PIC_NAME, PIC_FILETYPE, to_char(DATE_RECORD,'yyyy-mm-dd') DATE_RECORD, PIC FROM CUS_TRAVEL_PIC order by CUS_TRAVEL_PIC_ID";
	private static final String GET_ONE_STMT =
			"SELECT CUS_TRAVEL_PIC_ID, CUS_TRAVEL_DETAIL_ID, PIC_NAME, PIC_FILETYPE, to_char(DATE_RECORD,'yyyy-mm-dd') DATE_RECORD, PIC FROM CUS_TRAVEL_PIC where CUS_TRAVEL_PIC_ID = ?";
	private static final String DELETE =
			"DELETE FROM CUS_TRAVEL_PIC where CUS_TRAVEL_PIC_ID = ?";
	private static final String UPDATE =
			"UPDATE CUS_TRAVEL_PIC set CUS_TRAVEL_PIC_ID=?, CUS_TRAVEL_DETAIL_ID=?, PIC_NAME=?, PIC_FILETYPE=?, DATE_RECORD=?, PIC=? where CUS_TRAVEL_PIC_ID = ?";
	
	
	@Override
	public int insert(CusTravelPicVO cusTravelPicVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int updateCount = 0;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, cusTravelPicVO.getCusTravelDetailId());
			pstmt.setString(2, cusTravelPicVO.getPicName());
			pstmt.setString(3, cusTravelPicVO.getPicFiletype());
			pstmt.setDate(4, cusTravelPicVO.getDateRecord());
			pstmt.setBytes(5, cusTravelPicVO.getPic());
			
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
	public int update(CusTravelPicVO cusTravelPicVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int updateCount = 0;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setString(1, cusTravelPicVO.getCusTravelPicId());
			pstmt.setString(2, cusTravelPicVO.getCusTravelDetailId());
			pstmt.setString(3, cusTravelPicVO.getPicName());
			pstmt.setString(4, cusTravelPicVO.getPicFiletype());
			pstmt.setDate(5, cusTravelPicVO.getDateRecord());
			pstmt.setBytes(6, cusTravelPicVO.getPic());
			pstmt.setString(7, cusTravelPicVO.getCusTravelPicId());
			
			updateCount = pstmt.executeUpdate();
			
			//Handle any SQL errors
		} catch (SQLException se) {
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
	public int delete(String cusTravelPicId) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int updateCount = 0;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setString(1, cusTravelPicId);
			
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
	}//delete

	@Override
	public CusTravelPicVO findByPrimaryKey(String cusTravelPicId) {
		CusTravelPicVO cusTravelPicVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setString(1, cusTravelPicId);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				//cusTravelNoteVO 也稱為 Domain objects(object?)
				cusTravelPicVO = new CusTravelPicVO();
				cusTravelPicVO.setCusTravelPicId(rs.getString("CUS_TRAVEL_PIC_ID"));
				cusTravelPicVO.setCusTravelDetailId(rs.getString("CUS_TRAVEL_DETAIL_ID"));
				cusTravelPicVO.setPicName(rs.getString("PIC_NAME"));
				cusTravelPicVO.setPicFiletype(rs.getString("PIC_FILETYPE"));
				cusTravelPicVO.setDateRecord(rs.getDate("DATE_RECORD"));
				cusTravelPicVO.setPic(rs.getBytes("PIC"));	
			}
			
			// Handle anyu SQL errors
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
		return cusTravelPicVO;
	}//findbypk

	@Override
	public List<CusTravelPicVO> getAll() {
		List<CusTravelPicVO> list = new ArrayList<CusTravelPicVO>();
		CusTravelPicVO cusTravelPicVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while (rs.next()){
				cusTravelPicVO = new CusTravelPicVO();
				cusTravelPicVO.setCusTravelPicId(rs.getString("CUS_TRAVEL_PIC_ID"));
				cusTravelPicVO.setCusTravelDetailId(rs.getString("CUS_TRAVEL_DETAIL_ID"));
				cusTravelPicVO.setPicName(rs.getString("PIC_NAME"));
				cusTravelPicVO.setPicFiletype(rs.getString("PIC_FILETYPE"));
				cusTravelPicVO.setDateRecord(rs.getDate("DATE_RECORD"));
				cusTravelPicVO.setPic(rs.getBytes("PIC"));
				list.add(cusTravelPicVO);// store the row(record) in the list
			}
			
			//Handle any SQL errors
		}  catch (SQLException se) {
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
	}//getAll

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CusTravelPicJNDIDAO dao = new CusTravelPicJNDIDAO();
		File pic = new File("D:\\pictures\\漂亮嗎.jpg");
		File pic2 = new File("D:\\pictures\\2835-2.gif");
		InputStream in;
		
//		//新增
		CusTravelPicVO cusTravelPicVO1 = new CusTravelPicVO();
		cusTravelPicVO1.setCusTravelPicId("CTP000007");
		cusTravelPicVO1.setCusTravelDetailId("CTD000007");
		cusTravelPicVO1.setPicName("漂亮嗎?");
		cusTravelPicVO1.setPicFiletype(".jpg");
		cusTravelPicVO1.setDateRecord(java.sql.Date.valueOf("2016-10-25"));
		 
		try {
			in = new FileInputStream(pic);
			byte[] buf = new byte[in.available()];
			in.read(buf);
			cusTravelPicVO1.setPic(buf);
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}		
		dao.insert(cusTravelPicVO1);
		
		
////		//修改
//		CusTravelPicVO cusTravelPicVO2 = new CusTravelPicVO();
//		cusTravelPicVO2.setCusTravelPicId("CTP000003");
//		cusTravelPicVO2.setCusTravelDetailId("CTD000001");
//		cusTravelPicVO2.setPicName("很漂亮,假的");
//		cusTravelPicVO2.setPicFiletype(".gif");		
//		cusTravelPicVO2.setDateRecord(java.sql.Date.valueOf("2014-10-24"));
//		
//		try {
//			in = new FileInputStream(pic2);
//			byte[] buf = new byte[in.available()];
//			in.read(buf);
//			cusTravelPicVO2.setPic(buf);
//			in.close();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}		
//		dao.update(cusTravelPicVO2);
		
//		//刪除
//		dao.delete("CTP000002");
//		
//		//查詢 by pk
		CusTravelPicVO cusTravelPicVO3 = dao.findByPrimaryKey("CTP000003");
		System.out.print(cusTravelPicVO3.getCusTravelPicId() + ",");
		System.out.print(cusTravelPicVO3.getCusTravelDetailId() + ",");
		System.out.print(cusTravelPicVO3.getPicName() + ",");
		System.out.print(cusTravelPicVO3.getPicFiletype() + ",");
		System.out.print(cusTravelPicVO3.getDateRecord() + ",");
		System.out.println(cusTravelPicVO3.getPic());
		System.out.println("-------------------------");
		
//		//查詢 all
		List<CusTravelPicVO> list = dao.getAll();
		for (CusTravelPicVO aCusTravelPic : list){
			System.out.print(aCusTravelPic.getCusTravelPicId() + ",");
			System.out.print(aCusTravelPic.getCusTravelDetailId() + ",");
			System.out.print(aCusTravelPic.getPicName() + ",");
			System.out.print(aCusTravelPic.getPicFiletype() + ",");
			System.out.print(aCusTravelPic.getDateRecord() + ",");
			System.out.println(aCusTravelPic.getPic());
			System.out.println();
		}//for
	}//main

}//CusTravelPicJDBCDAO
