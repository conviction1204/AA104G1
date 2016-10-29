package com.cusTravelDetail.model;

import java.util.List;

public class CusTravelDetailService {

	private CusTravelDetailDAO_interface dao;

	public CusTravelDetailService() {
		dao = new CusTravelDetailDAO();
	}

	public CusTravelDetailVO addCusTravelDetail(String cusTravelDetailId, String cusTravelNoteId,java.sql.Date dateRecord, String content, 
			String detailName) {

		CusTravelDetailVO cusTravelDetailVO = new CusTravelDetailVO();

		cusTravelDetailVO.setCusTravelDetailId(cusTravelDetailId);
		cusTravelDetailVO.setCusTravelNoteId(cusTravelNoteId);
		cusTravelDetailVO.setDateRecord(dateRecord);
		cusTravelDetailVO.setContent(content);
		cusTravelDetailVO.setDetailName(detailName);
		dao.insert(cusTravelDetailVO);

		return cusTravelDetailVO;
	}

	//預留給 Struts 2 用的
	public void addCusTravelDetail(CusTravelDetailVO cusTravelDetailVO) {
		dao.insert(cusTravelDetailVO);
	}
	
	public CusTravelDetailVO updateCusTravelDetail(String cusTravelDetailId, String cusTravelNoteId,java.sql.Date dateRecord, String content, 
			String detailName) {

		CusTravelDetailVO cusTravelDetailVO = new CusTravelDetailVO();

		cusTravelDetailVO.setCusTravelDetailId(cusTravelDetailId);
		cusTravelDetailVO.setCusTravelNoteId(cusTravelNoteId);
		cusTravelDetailVO.setDateRecord(dateRecord);
		cusTravelDetailVO.setContent(content);
		cusTravelDetailVO.setDetailName(detailName);
		dao.update(cusTravelDetailVO);

		return dao.findByPrimaryKey(cusTravelDetailId);
	}
	
	//預留給 Struts 2 用的
	public void updateCusTravelDetail(CusTravelDetailVO cusTravelDetailVO) {
		dao.update(cusTravelDetailVO);
	}

	public void deleteCusTravelDetail(String cusTravelDetailId) {
		dao.delete(cusTravelDetailId);
	}

	public CusTravelDetailVO getOneCusTravelDetail(String cusTravelDetailId) {
		return dao.findByPrimaryKey(cusTravelDetailId);
	}

	public List<CusTravelDetailVO> getAll() {
		return dao.getAll();
	}
}
