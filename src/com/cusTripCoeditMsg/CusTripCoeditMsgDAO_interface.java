package com.cusTripCoeditMsg;
import java.util.*;

public interface CusTripCoeditMsgDAO_interface {
	public void insert(CusTripCoeditMsgVO cusTripCoeditMsgVO);
	public void update(CusTripCoeditMsgVO cusTripCoeditMsgVO);
	public void delete(String cusTripCoeditMsgId);
	public CusTripCoeditMsgVO findByPrimaryKey(String cusTripCoeditMsgId);
	public List<CusTripCoeditMsgVO> getAll();

}
