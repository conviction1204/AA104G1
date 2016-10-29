package com.cusTripCoedit.model;
import java.util.*;

public interface CusTripCoeditDAO_interface {
	public void insert(CusTripCoeditVO cusTripCoeditVO);
	public void update(CusTripCoeditVO cusTripCoeditVO);
	public void delete(String cusTripId, String cusId);
	public CusTripCoeditVO findByPrimaryKey(String cusTripId, String cusId);
	public List<CusTripCoeditVO> getAll();
}
