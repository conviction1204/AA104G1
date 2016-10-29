package com.cusFavoriteSpot.model;

import java.util.*;

public interface CusFavoriteSpotDAO_interface {
          public int insert(CusFavoriteSpotVO cusFavoriteSpotVO);
          public int update(CusFavoriteSpotVO cusFavoriteSpotVO);
          public int delete(String cusId, String sysSpotId);
          public CusFavoriteSpotVO findByPrimaryKey(String cusId, String sysSpotId);
          public List<CusFavoriteSpotVO> getAll();
        //萬用複合查詢(傳入參數型態Map)(回傳 List)
//        public List<CusFavoriteSpotVO> getAll(Map<String, String[]> map);
}
