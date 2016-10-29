package com.cusFavoriteTrip.model;

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

import com.cusFavoriteSpot.model.CusFavoriteSpotJDBCDAO;
import com.cusFavoriteSpot.model.CusFavoriteSpotVO;

public class CusFavoriteTripJNDIDAO implements CusFavoriteTripDAO_interface {
	
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
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, cusFavoriteTripVO.getCusId());
			pstmt.setString(2, cusFavoriteTripVO.getCusTripId());
			pstmt.setString(3, cusFavoriteTripVO.getTripName());
			
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
	public int update(CusFavoriteTripVO cusFavoriteTripVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int updateCount = 0;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setString(1, cusFavoriteTripVO.getCusId());
			pstmt.setString(2, cusFavoriteTripVO.getCusTripId());
			pstmt.setString(3, cusFavoriteTripVO.getTripName());
			pstmt.setString(4, cusFavoriteTripVO.getCusId());
			pstmt.setString(5, cusFavoriteTripVO.getCusTripId());
			
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
	public int delete(String cusId, String cusTripId) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int updateCount = 0;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setString(1, cusId);
			pstmt.setString(2, cusTripId);
			
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
	public CusFavoriteTripVO findByPrimaryKey(String cusId, String cusTripId) {
		CusFavoriteTripVO cusFavoriteTripVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
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
			con = ds.getConnection();
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

	
}//CusFavoriteTripJDBCDAO
