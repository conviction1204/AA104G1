package com.cusTrip.model;
import java.util.*;

public interface CusTripDAO_interface {
	public void insert(CusTripVO cusTripVO);
	public void update(CusTripVO cusTripVO);
	public void delete(String cusTripId);
	public CusTripVO findByPrimaryKey(String cusTripId);
	public List<CusTripVO> getAll();
}
