package com.sysQa.model;
import java.util.*;

public interface SysQaDAO_interface {
	public void update(SysQaVO sysQaVO);
	public void insert(SysQaVO sysQaVO);
	public void delete(String sysQaId);
	public SysQaVO findByPrimaryKey(String sysQaId);
	public List<SysQaVO> getAll();
}
