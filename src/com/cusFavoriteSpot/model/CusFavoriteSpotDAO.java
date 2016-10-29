package com.cusFavoriteSpot.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.cusTravelNote.model.CusTravelNoteVO;

public class CusFavoriteSpotDAO implements CusFavoriteSpotDAO_interface {
	
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
			"INSERT INTO CUS_FAVORITE_SPOT (CUS_ID, SYS_SPOT_ID, SPOT_NAME) VALUES (?, ?, ?)";
	private static final String GET_ALL_STMT =
			"SELECT CUS_ID, SYS_SPOT_ID, SPOT_NAME FROM CUS_FAVORITE_SPOT order by CUS_ID, SYS_SPOT_ID";
	private static final String GET_ONE_STMT =
			"SELECT CUS_ID, SYS_SPOT_ID, SPOT_NAME FROM CUS_FAVORITE_SPOT where CUS_ID = ? AND SYS_SPOT_ID=?";
	private static final String DELETE =
			"DELETE FROM CUS_FAVORITE_SPOT where CUS_ID = ? AND SYS_SPOT_ID=?";
	private static final String UPDATE =
			"UPDATE CUS_FAVORITE_SPOT set CUS_ID=?, SYS_SPOT_ID=?, SPOT_NAME=? where CUS_ID = ? AND SYS_SPOT_ID=?";
	
	@Override
	public int insert(CusFavoriteSpotVO cusFavoriteSpotVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int updateCount = 0;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, cusFavoriteSpotVO.getCusId());
			pstmt.setString(2, cusFavoriteSpotVO.getSysSpotId());
			pstmt.setString(3, cusFavoriteSpotVO.getSpotName());
			
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
	public int update(CusFavoriteSpotVO cusFavoriteSpotVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int updateCount = 0;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setString(1, cusFavoriteSpotVO.getCusId());
			pstmt.setString(2, cusFavoriteSpotVO.getSysSpotId());
			pstmt.setString(3, cusFavoriteSpotVO.getSpotName());
			pstmt.setString(4, cusFavoriteSpotVO.getCusId());
			pstmt.setString(5, cusFavoriteSpotVO.getSysSpotId());
			
			updateCount = pstmt.executeUpdate();
			
			//Handle any driver errors
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
	public int delete(String cusId, String sysSpotId) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int updateCount = 0;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setString(1, cusId);
			pstmt.setString(2, sysSpotId);
			
			updateCount = pstmt.executeUpdate();
			
			//Handle any driver errors
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
	public CusFavoriteSpotVO findByPrimaryKey(String cusId, String sysSpotId) {
		CusFavoriteSpotVO cusFavoriteSpotVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setString(1, cusId);
			pstmt.setString(2, sysSpotId);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				//cusTravelNoteVO 也稱為 Domain objects(object?)
				cusFavoriteSpotVO = new CusFavoriteSpotVO();
				cusFavoriteSpotVO.setCusId(rs.getString("CUS_ID"));
				cusFavoriteSpotVO.setSysSpotId(rs.getString("SYS_SPOT_ID"));
				cusFavoriteSpotVO.setSpotName(rs.getString("SPOT_NAME"));	
			}
			
			// Handle anyu driver errors
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
		
		return cusFavoriteSpotVO;
	}//findbyPK

	@Override
	public List<CusFavoriteSpotVO> getAll() {
		List<CusFavoriteSpotVO> list = new ArrayList<CusFavoriteSpotVO>();
		CusFavoriteSpotVO cusFavoriteSpotVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while (rs.next()){
				cusFavoriteSpotVO = new CusFavoriteSpotVO();
				cusFavoriteSpotVO.setCusId(rs.getString("CUS_ID"));
				cusFavoriteSpotVO.setSysSpotId(rs.getString("SYS_SPOT_ID"));
				cusFavoriteSpotVO.setSpotName(rs.getString("SPOT_NAME"));
				list.add(cusFavoriteSpotVO);// store the row(record) in the list
			}
			
			//Handle any driver errors
		} catch (SQLException se) {
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
	}//List<CusFavoriteSpotVO> getAll()

	public static void main(String[] args) {
		
		CusFavoriteSpotDAO dao = new CusFavoriteSpotDAO();
		
		//新增
		for(int i=1; i<10; i++){
		CusFavoriteSpotVO cusFavoriteSpotVO1 = new CusFavoriteSpotVO();
		cusFavoriteSpotVO1.setCusId("CU00000"+i);
		cusFavoriteSpotVO1.setSysSpotId("SS00000"+i);
		cusFavoriteSpotVO1.setSpotName("最愛景點名稱"+i);
		dao.insert(cusFavoriteSpotVO1);
		}
//				
//		//修改
		CusFavoriteSpotVO cusFavoriteSpotVO2 = new CusFavoriteSpotVO();
		cusFavoriteSpotVO2.setCusId("CU000002");
		cusFavoriteSpotVO2.setSysSpotId("SS000002");
		cusFavoriteSpotVO2.setSpotName("最愛景點名稱_改");
		dao.update(cusFavoriteSpotVO2);
//				
//		//刪除
		dao.delete("CU000003","SS000003");
				
//		//查詢 by pk
		CusFavoriteSpotVO CusFavoriteSpotVO3 = dao.findByPrimaryKey("CU000001", "SS000001");
		System.out.print(CusFavoriteSpotVO3.getCusId() + ",");
		System.out.print(CusFavoriteSpotVO3.getSysSpotId() + ",");
		System.out.println(CusFavoriteSpotVO3.getSpotName());
		System.out.println("-------------------------");
				
//		//查詢 all
		List<CusFavoriteSpotVO> list = dao.getAll();
		for (CusFavoriteSpotVO aCusFavoriteSpotVO : list){
			System.out.print(aCusFavoriteSpotVO.getCusId() + ",");
			System.out.print(aCusFavoriteSpotVO.getSysSpotId() + ",");
			System.out.println(aCusFavoriteSpotVO.getSpotName());
			//System.out.println();
		}//for

	}//main

}//CusFavoriteSpotJDBCDAO
