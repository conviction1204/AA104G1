package com.cusFavoriteTrip.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cusFavoriteSpot.model.CusFavoriteSpotJDBCDAO;
import com.cusFavoriteSpot.model.CusFavoriteSpotVO;

public class CusFavoriteTripJDBCDAO implements CusFavoriteTripDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "FUN";
	String passwd = "aa104g1";
	
	private static final String INSERT_STMT =
			"INSERT INTO CUS_FAVORITE_TRIP (CUS_ID, CUS_TRIP_ID, TRIP_NAME) VALUES (?, ?, ?)";
	private static final String GET_ALL_STMT =
			"SELECT CUS_ID, CUS_TRIP_ID, TRIP_NAME FROM CUS_FAVORITE_TRIP order by CUS_ID, CUS_TRIP_ID";
	private static final String GET_ONE_STMT =
			"SELECT CUS_ID, CUS_TRIP_ID, TRIP_NAME FROM CUS_FAVORITE_TRIP where CUS_ID = ? AND CUS_TRIP_ID=?";
	private static final String DELETE =
			"DELETE FROM CUS_FAVORITE_TRIP where CUS_ID = ? AND CUS_TRIP_ID=?";
	private static final String UPDATE =
			"UPDATE CUS_FAVORITE_TRIP set CUS_ID=?, CUS_TRIP_ID=?, TRIP_NAME=? where CUS_ID = ? AND CUS_TRIP_ID=?";
		
	
	@Override
	public int insert(CusFavoriteTripVO cusFavoriteTripVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int updateCount = 0;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, cusFavoriteTripVO.getCusId());
			pstmt.setString(2, cusFavoriteTripVO.getCusTripId());
			pstmt.setString(3, cusFavoriteTripVO.getTripName());
			
			updateCount = pstmt.executeUpdate();
			
		}catch(ClassNotFoundException e){
			throw new RuntimeException("Couldn't load database driver. "
						+ e.getMessage()); 
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
	public int update(CusFavoriteTripVO cusFavoriteTripVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int updateCount = 0;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setString(1, cusFavoriteTripVO.getCusId());
			pstmt.setString(2, cusFavoriteTripVO.getCusTripId());
			pstmt.setString(3, cusFavoriteTripVO.getTripName());
			pstmt.setString(4, cusFavoriteTripVO.getCusId());
			pstmt.setString(5, cusFavoriteTripVO.getCusTripId());
			
			updateCount = pstmt.executeUpdate();
			
			//Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
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
	public int delete(String cusId, String cusTripId) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int updateCount = 0;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setString(1, cusId);
			pstmt.setString(2, cusTripId);
			
			updateCount = pstmt.executeUpdate();
			
			//Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
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
	public CusFavoriteTripVO findByPrimaryKey(String cusId, String cusTripId) {
		CusFavoriteTripVO cusFavoriteTripVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setString(1, cusId);
			pstmt.setString(2, cusTripId);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				//cusTravelNoteVO 也稱為 Domain objects(object?)
				cusFavoriteTripVO = new CusFavoriteTripVO();
				cusFavoriteTripVO.setCusId(rs.getString("CUS_ID"));
				cusFavoriteTripVO.setCusTripId(rs.getString("CUS_TRIP_ID"));
				cusFavoriteTripVO.setTripName(rs.getString("TRIP_NAME"));	
			}
			
			// Handle anyu driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
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
		
		return cusFavoriteTripVO;
	}//findbyPK

	@Override
	public List<CusFavoriteTripVO> getAll() {
		List<CusFavoriteTripVO> list = new ArrayList<CusFavoriteTripVO>();
		CusFavoriteTripVO cusFavoriteTripVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while (rs.next()){
				cusFavoriteTripVO = new CusFavoriteTripVO();
				cusFavoriteTripVO.setCusId(rs.getString("CUS_ID"));
				cusFavoriteTripVO.setCusTripId(rs.getString("CUS_TRIP_ID"));
				cusFavoriteTripVO.setTripName(rs.getString("TRIP_NAME"));
				list.add(cusFavoriteTripVO);// store the row(record) in the list
			}
			
			//Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
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
	}//List<CusFavoriteTripVO> getAll()

	public static void main(String[] args) {
CusFavoriteTripJDBCDAO dao = new CusFavoriteTripJDBCDAO();
		
		//新增
		for(int i=1; i<10; i++){
		CusFavoriteTripVO cusFavoriteTripVO1 = new CusFavoriteTripVO();
		cusFavoriteTripVO1.setCusId("CU00000"+i);
		cusFavoriteTripVO1.setCusTripId("CT00000"+i);
		cusFavoriteTripVO1.setTripName("最愛行程名稱"+i);
		dao.insert(cusFavoriteTripVO1);
		}
//				
////		//修改
		CusFavoriteTripVO cusFavoriteTripVO2 = new CusFavoriteTripVO();
		cusFavoriteTripVO2.setCusId("CU000002");
		cusFavoriteTripVO2.setCusTripId("CT000002");
		cusFavoriteTripVO2.setTripName("最愛行程名稱_改了");
		dao.update(cusFavoriteTripVO2);
				
//		//刪除
		dao.delete("CU000003","CT000003");
////				
//		//查詢 by pk
		CusFavoriteTripVO cusFavoriteTripVO3 = dao.findByPrimaryKey("CU000001", "CT000001");
		System.out.print(cusFavoriteTripVO3.getCusId() + ",");
		System.out.print(cusFavoriteTripVO3.getCusTripId() + ",");
		System.out.println(cusFavoriteTripVO3.getTripName());
		System.out.println("-------------------------");
				
//		//查詢 all
		List<CusFavoriteTripVO> list = dao.getAll();
		for (CusFavoriteTripVO aCusFavoriteTripVO : list){
			System.out.print(aCusFavoriteTripVO.getCusId() + ",");
			System.out.print(aCusFavoriteTripVO.getCusTripId() + ",");
			System.out.println(aCusFavoriteTripVO.getTripName());
			//System.out.println();
		}//for

	}//main

}//CusFavoriteTripJDBCDAO
