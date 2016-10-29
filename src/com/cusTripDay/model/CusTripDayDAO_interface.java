package com.cusTripDay.model;
import java.util.*;

public interface CusTripDayDAO_interface {
	public void insert(CusTripDayVO cusTripDayVO);
	public void update(CusTripDayVO cusTripDayVO);
	public void delete(String cusTripDayId);
	public CusTripDayVO findByPrimaryKey(String cusTripDayId);
	public List<CusTripDayVO> getAll();
}
