package com.cusTravelNote.model;

import java.util.*;

public interface CusTravelNoteDAO_interface {
          public int insert(CusTravelNoteVO cusTravelNoteVO);
          public int update(CusTravelNoteVO cusTravelNoteVO);
          public int delete(String cusTravelNoteId);
          public CusTravelNoteVO findByPrimaryKey(String cusTravelNoteId);
          public List<CusTravelNoteVO> getAll();
        //萬用複合查詢(傳入參數型態Map)(回傳 List)
//        public List<CusTravelNoteVO> getAll(Map<String, String[]> map);
}
