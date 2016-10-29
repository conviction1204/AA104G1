<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.cusTravelDetail.model.*"%>
<%
CusTravelDetailVO cusTravelDetailVO = (CusTravelDetailVO) request.getAttribute("cusTravelDetailVO");; //CusTravelDetailServlet.java(Concroller), 存入req的cusTravelDetailVO物件
%>
<html>
<head>
<title>遊記細節資料 - listOneCusTravelDetail.jsp</title>
</head>
<body bgcolor='white'>

<table border='1' cellpadding='5' cellspacing='0' width='600'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>遊記細節資料 - ListOneCusTravelDetail.jsp</h3>
		<a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a>
		</td>
	</tr>
</table>

<table border='1' bordercolor='#CCCCFF' width='600'>
	<tr>
		<th>遊記細節編號</th>
		<th>遊記細節名稱</th>
		<th>日期</th>
		<th>內文</th>		
		<th>遊記編號</th>
	</tr>
	<tr align='center' valign='middle'>
		<td><%=cusTravelDetailVO.getCusTravelDetailId()%></td>
		<td><%=cusTravelDetailVO.getDetailName()%></td>
		<td><%=cusTravelDetailVO.getDateRecord()%></td>
		<td><%=cusTravelDetailVO.getContent()%></td>
		<td><%=cusTravelDetailVO.getCusTravelNoteId()%></td>
	</tr>
</table>

</body>
</html>
