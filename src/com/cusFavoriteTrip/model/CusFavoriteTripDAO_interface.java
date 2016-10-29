package com.cusFavoriteTrip.model;

import java.util.*;

public interface CusFavoriteTripDAO_interface {
          public int insert(CusFavoriteTripVO cusFavoriteTripVO);
          public int update(CusFavoriteTripVO cusFavoriteTripVO);
          public int delete(String cusId, String cusTripId);
          public CusFavoriteTripVO findByPrimaryKey(String cusId, String cusTripId);
          public List<CusFavoriteTripVO> getAll();
        //萬用複合查詢(傳入參數型態Map)(回傳 List)
//        public List<CusFavoriteTripVO> getAll(Map<String, String[]> map);
}
