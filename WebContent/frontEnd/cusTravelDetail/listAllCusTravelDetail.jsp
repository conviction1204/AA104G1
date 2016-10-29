<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.cusTravelDetail.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
	CusTravelDetailService cusTravelDetailSvc = new CusTravelDetailService();
    List<CusTravelDetailVO> list = cusTravelDetailSvc.getAll();
    pageContext.setAttribute("list",list);
%>

<html>
<head>
<title>所有遊記細節資料 - listAllCusTravelDetail.jsp</title>
</head>
<body bgcolor='white'>
<b><font color=red>此頁練習採用 EL 的寫法取值:</font></b>
<table border='1' cellpadding='5' cellspacing='0' width='800'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>所有遊記細節資料 - ListAllCusTravelDetail.jsp</h3>
		<a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a>
		</td>
	</tr>
</table>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font color='red'>請修正以下錯誤:
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li>${message}</li>
		</c:forEach>
	</ul>
	</font>
</c:if>

<table border='1' bordercolor='#CCCCFF' width='800'>
	<tr>
		<th>遊記細節編號</th>
		<th>遊記細節名稱</th>
		<th>日期</th>
		<th>內文</th>		
		<th>遊記編號</th>
	</tr>
	<%@ include file="page1.file" %> 
	<c:forEach var="cusTravelDetailVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		<tr align='center' valign='middle'>
			<td>${cusTravelDetailVO.cusTravelDetailId}</td>
			<td>${cusTravelDetailVO.detailName}</td>
			<td>${cusTravelDetailVO.dateRecord}</td>
			<td>${cusTravelDetailVO.content}</td>
			<td>${cusTravelDetailVO.cusTravelNoteId}</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/frontEnd/cusTravelDetail/cusTravelDetail.do">
			     <input type="submit" value="修改">
			     <input type="hidden" name="cusTravelDetailId" value="${cusTravelDetailVO.cusTravelDetailId}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/frontEnd/cusTravelDetail/cusTravelDetail.do">
			    <input type="submit" value="刪除">
			    <input type="hidden" name="cusTravelDetailId" value="${cusTravelDetailVO.cusTravelDetailId}">
			    <input type="hidden" name="action"value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>

</body>
</html>
