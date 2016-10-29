package com.cusTravelPic.model;

import java.util.*;

public interface CusTravelPicDAO_interface {
          public int insert(CusTravelPicVO cusTravelPicVO);
          public int update(CusTravelPicVO cusTravelPicVO);
          public int delete(String cusTravelPicId);
          public CusTravelPicVO findByPrimaryKey(String cusTravelPicId);
          public List<CusTravelPicVO> getAll();
        //萬用複合查詢(傳入參數型態Map)(回傳 List)
//        public List<CusTravelPicVO> getAll(Map<String, String[]> map);
}
