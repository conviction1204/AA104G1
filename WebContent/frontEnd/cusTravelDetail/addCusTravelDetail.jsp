<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.cusTravelDetail.model.*"%>
<%
CusTravelDetailVO cusTravelDetailVO = (CusTravelDetailVO) request.getAttribute("cusTravelDetailVO");
%>

<html>
<head>
<title>遊記內容細節新增 - addCusTravelDetail.jsp</title></head>
<link rel="stylesheet" type="text/css" href="js/calendar.css">
<script language="JavaScript" src="js/calendarcode.js"></script>
<div id="popupcalendar" class="text"></div>

<body bgcolor='white'>

<table border='1' cellpadding='5' cellspacing='0' width='400'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>遊記內容細節新增 - addCusTravelDetail.jsp</h3>
		</td>
		<td>
		   <a href="select_page.jsp"><img src="images/tomcat.gif" width="100" height="100" border="1">回首頁</a>
	    </td>
	</tr>
</table>

<h3>資料遊記細節:</h3>
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

<FORM METHOD="post" ACTION="cusTravelDetail.do" name="form1">
<table border="0">

	<tr>
		<td>遊記編號:</td>
		<td><input type="TEXT" name="cusTravelNoteId" size="45" 
			value="<%= (cusTravelDetailVO==null)? "CTN000001" : cusTravelDetailVO.getCusTravelNoteId()%>" /></td>
	</tr>
	<tr>
		<td>遊記標題名稱:</td>
		<td><input type="TEXT" name="detailName" size="45"
			value="<%= (cusTravelDetailVO==null)? "我要去海邊" : cusTravelDetailVO.getDetailName()%>" /></td>
	</tr>
	<tr>
		<%java.sql.Date date_SQL = new java.sql.Date(System.currentTimeMillis());%>
		<td>日期:</td>
		<td bgcolor="#CCCCFF">
		    <input class="cal-TextBox"
			onFocus="this.blur()" size="9" readonly type="text" name="dateRecord" value="<%= (cusTravelDetailVO==null)? date_SQL : cusTravelDetailVO.getDateRecord()%>">
			<a class="so-BtnLink"
			href="javascript:calClick();return false;"
			onmouseover="calSwapImg('BTN_date', 'img_Date_OVER',true);"
			onmouseout="calSwapImg('BTN_date', 'img_Date_UP',true);"
			onclick="calSwapImg('BTN_date', 'img_Date_DOWN');showCalendar('form1','dateRecord','BTN_date');return false;">
		    <img align="middle" border="0" name="BTN_date"	src="images/btn_date_up.gif" width="22" height="17" alt="記錄日期"></a>
		</td>
	</tr>
	<tr>
		<td>內文:</td>
		<td><input type="TEXT" name="content" size="45"
			value="<%= (cusTravelDetailVO==null)? "你媽知道你在這裡發廢文嗎!?!" : cusTravelDetailVO.getContent()%>" /></td>
	</tr>
	

	<jsp:useBean id="cusTravelNoteSvc" scope="page" class="com.cusTravelNote.model.CusTravelNoteService" />
	<tr>
		<td>遊記名稱:<font color=red><b>*</b></font></td>
		<td><select size="1" name="cusTravelNoteId">
			<c:forEach var="cusTravelNoteVO" items="${cusTravelNoteSvc.all}">
				<option value="${cusTravelNoteVO.cusTravelNoteId}" ${(cusTravelDetailVO.cusTravelNoteId==cusTravelNoteVO.cusTravelNoteId)? 'selected':'' } >${cusTravelNoteVO.noteName}
			</c:forEach>
		</select></td>
	</tr>

</table>
<br>
<input type="hidden" name="action" value="insert">
<input type="submit" value="送出新增"></FORM>
</body>

</html>
