package com.cusTravelDetail.model;

import java.util.*;

public interface CusTravelDetailDAO_interface {
          public int insert(CusTravelDetailVO cusTravelDetailVO);
          public int update(CusTravelDetailVO cusTravelDetailVO);
          public int delete(String cusTravelDetailId);
          public CusTravelDetailVO findByPrimaryKey(String cusTravelDetailId);
          public List<CusTravelDetailVO> getAll();
        //萬用複合查詢(傳入參數型態Map)(回傳 List)
//        public List<CusTravelDetailVO> getAll(Map<String, String[]> map);
}
