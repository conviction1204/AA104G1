package com.cusTravelNote.model;

import java.util.List;

public class CusTravelNoteService {

	private CusTravelNoteDAO_interface dao;

	public CusTravelNoteService() {
		dao = new CusTravelNoteDAO();
	}

	public CusTravelNoteVO addCusTravelNote(String cusTravelNoteId, String cusId, String cusTripId, java.sql.Date dateRecord,
			String noteName) {

		CusTravelNoteVO cusTravelNoteVO = new CusTravelNoteVO();

		cusTravelNoteVO.setCusTravelNoteId(cusTravelNoteId);
		cusTravelNoteVO.setCusId(cusId);
		cusTravelNoteVO.setCusTripId(cusTripId);
		cusTravelNoteVO.setDateRecord(dateRecord);
		cusTravelNoteVO.setNoteName(noteName);
		dao.insert(cusTravelNoteVO);

		return cusTravelNoteVO;
	}

	//預留給 Struts 2 用的
	public void addCusTravelNote(CusTravelNoteVO cusTravelNoteVO) {
		dao.insert(cusTravelNoteVO);
	}
	
	public CusTravelNoteVO updateCusTravelNote(String cusTravelNoteId, String cusId, String cusTripId, java.sql.Date dateRecord,
			String noteName) {

		CusTravelNoteVO cusTravelNoteVO = new CusTravelNoteVO();

		cusTravelNoteVO.setCusTravelNoteId(cusTravelNoteId);
		cusTravelNoteVO.setCusId(cusId);
		cusTravelNoteVO.setCusTripId(cusTripId);
		cusTravelNoteVO.setDateRecord(dateRecord);
		cusTravelNoteVO.setNoteName(noteName);
		dao.update(cusTravelNoteVO);

		return dao.findByPrimaryKey(cusTravelNoteId);
	}
	
	//預留給 Struts 2 用的
	public void updateCusTravelNote(CusTravelNoteVO cusTravelNoteVO) {
		dao.update(cusTravelNoteVO);
	}

	public void deleteCusTravelNote(String cusTravelNoteId) {
		dao.delete(cusTravelNoteId);
	}

	public CusTravelNoteVO getOneCusTravelNote(String cusTravelNoteId) {
		return dao.findByPrimaryKey(cusTravelNoteId);
	}

	public List<CusTravelNoteVO> getAll() {
		return dao.getAll();
	}
}
