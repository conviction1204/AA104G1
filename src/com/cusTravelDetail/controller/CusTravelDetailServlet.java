package com.cusTravelDetail.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.cusTravelDetail.model.*;

public class CusTravelDetailServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("cusTravelDetailId");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入遊記細節編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/frontEnd/cusTravelDetail/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				String cusTravelDetailId = null;
				try {
					cusTravelDetailId = new String(str);
				} catch (Exception e) {
					errorMsgs.add("遊記細節編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/frontEnd/cusTravelDetail/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				CusTravelDetailService cusTravelDetailService = new CusTravelDetailService();
				CusTravelDetailVO cusTravelDetailVO = cusTravelDetailService.getOneCusTravelDetail(cusTravelDetailId);
				if (cusTravelDetailVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/frontEnd/cusTravelDetail/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("cusTravelDetailVO", cusTravelDetailVO); // 資料庫取出的cusTravelDetailVO物件,存入req
				String url = "/frontEnd/cusTravelDetail/listOneCusTravelDetail.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneCusTravelDetail.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/frontEnd/cusTravelDetail/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("getOne_For_Update".equals(action)) { // 來自listAllCusTravelDetail.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				String cusTravelDetailId = new String(req.getParameter("cusTravelDetailId"));
				
				/***************************2.開始查詢資料****************************************/
				CusTravelDetailService cusTravelDetailService = new CusTravelDetailService();
				CusTravelDetailVO cusTravelDetailVO = cusTravelDetailService.getOneCusTravelDetail(cusTravelDetailId);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("cusTravelDetailVO", cusTravelDetailVO);         // 資料庫取出的cusTravelDetailVO物件,存入req
				String url = "/frontEnd/cusTravelDetail/update_cusTravelDetail_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_cusTravelDetail_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/frontEnd/cusTravelDetail/listAllCusTravelDetail.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String cusTravelDetailId = new String(req.getParameter("cusTravelDetailId").trim());
				String cusTravelNoteId = req.getParameter("cusTravelNoteId").trim();
				String detailName = req.getParameter("detailName").trim();				
				
				java.sql.Date dateRecord = null;
				try {
					dateRecord = java.sql.Date.valueOf(req.getParameter("dateRecord").trim());
				} catch (IllegalArgumentException e) {
					dateRecord=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}

				String content = new String(req.getParameter("content").trim());

				
//				Double sal = null;
//				try {
//					sal = new Double(req.getParameter("sal").trim());
//				} catch (NumberFormatException e) {
//					sal = 0.0;
//					errorMsgs.add("薪水請填數字.");
//				}

				CusTravelDetailVO cusTravelDetailVO = new CusTravelDetailVO();
				cusTravelDetailVO.setCusTravelDetailId(cusTravelDetailId);
				cusTravelDetailVO.setCusTravelDetailId(cusTravelNoteId);
				cusTravelDetailVO.setDateRecord(dateRecord);
				cusTravelDetailVO.setContent(content);
				cusTravelDetailVO.setDetailName(detailName);
				

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("cusTravelDetailVO", cusTravelDetailVO); // 含有輸入格式錯誤的cusTravelDetailVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/frontEnd/cusTravelDetail/update_cusTravelDetail_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				CusTravelDetailService cusTravelDetailService = new CusTravelDetailService();
				cusTravelDetailVO = cusTravelDetailService.updateCusTravelDetail(cusTravelDetailId, cusTravelNoteId, dateRecord, content, detailName);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("cusTravelDetailVO", cusTravelDetailVO); // 資料庫update成功後,正確的的cusTravelDetailVO物件,存入req
				String url = "/frontEnd/cusTravelDetail/listOneCusTravelDetail.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneCusTravelDetail.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/frontEnd/cusTravelDetail/update_cusTravelDetail_input.jsp");
				failureView.forward(req, res);
			}
		}

        if ("insert".equals(action)) { // 來自addCusTravelDetail.jsp的請求  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
//				String cusTravelDetailId = req.getParameter("cusTravelDetailId").trim();
				String cusTravelNoteId = req.getParameter("cusTravelNoteId").trim();
				String detailName = req.getParameter("detailName").trim();				
				
				java.sql.Date dateRecord = null;
				try {
					dateRecord = java.sql.Date.valueOf(req.getParameter("dateRecord").trim());
				} catch (IllegalArgumentException e) {
					dateRecord=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}

				String content = new String(req.getParameter("content").trim());

				CusTravelDetailVO cusTravelDetailVO = new CusTravelDetailVO();
//				cusTravelDetailVO.setCusTravelDetailId(cusTravelDetailId);
				cusTravelDetailVO.setCusTravelNoteId(cusTravelNoteId);
				cusTravelDetailVO.setDateRecord(dateRecord);
				cusTravelDetailVO.setContent(content);
				cusTravelDetailVO.setDetailName(detailName);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("cusTravelDetailVO", cusTravelDetailVO); // 含有輸入格式錯誤的cusTravelDetailVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/frontEnd/cusTravelDetail/addCusTravelDetail.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				CusTravelDetailService cusTravelDetailService = new CusTravelDetailService();
				cusTravelDetailVO = cusTravelDetailService.addCusTravelDetail(cusTravelNoteId, dateRecord, content, detailName);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/frontEnd/cusTravelDetail/listAllCusTravelDetail.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\�����listAllEmp.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/frontEnd/cusTravelDetail/addCusTravelDetail.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("delete".equals(action)) { // 來自listAllCusTravelDetail.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數***************************************/
				String cusTravelDetailId = new String(req.getParameter("cusTravelDetailId"));
				
				/***************************2.開始刪除資料***************************************/
				CusTravelDetailService cusTravelDetailService = new CusTravelDetailService();
				cusTravelDetailService.deleteCusTravelDetail(cusTravelDetailId);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/frontEnd/cusTravelDetail/listAllCusTravelDetail.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("其他可能的錯誤處理:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/frontEnd/cusTravelDetail/listAllCusTravelDetail.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
