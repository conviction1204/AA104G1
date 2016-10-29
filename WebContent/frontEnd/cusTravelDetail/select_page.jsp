<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head><title>AA104G1 CusTravelDetail: Home</title></head>
<body bgcolor='white'>

<table border='1' cellpadding='5' cellspacing='0' width='400'>
  <tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
    <td><h3>AA104G1 CusTravelDetail: Home</h3><font color=red>( MVC )</font></td>
  </tr>
</table>

<p>This is the Home page for AA104G1 CusTravelDetail: Home</p>

<h3>資料查詢:</h3>
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

<ul>
  <li><a href='listAllCusTravelDetail.jsp'>List</a> all CusTravelDetails. </li> <br><br>
  
  <li>
    <FORM METHOD="post" ACTION="cusTravelDetail.do" >
        <b>輸入旅遊細節編號 (如CTD000001):</b>
        <input type="text" name="cusTravelDetailId">
        <input type="submit" value="送出">
        <input type="hidden" name="action" value="getOne_For_Display">
    </FORM>
  </li>

  <jsp:useBean id="cusTravelDetailSvc" scope="page" class="com.cusTravelDetail.model.CusTravelDetailService" />
   
  <li>
     <FORM METHOD="post" ACTION="cusTravelDetail.do" >
       <b>選擇旅遊細節編號:</b>
       <select size="1" name="cusTravelDetailId">
         <c:forEach var="cusTravelDetailVO" items="${cusTravelDetailSvc.all}" > 
          <option value="${cusTravelDetailVO.cusTravelDetailId}">${cusTravelDetailVO.cusTravelDetailId}
         </c:forEach>   
       </select>
       <input type="submit" value="送出">
       <input type="hidden" name="action" value="getOne_For_Display">
    </FORM>
  </li>
  
  <li>
     <FORM METHOD="post" ACTION="cusTravelDetail.do" >
       <b>選擇遊記名稱:</b>
       <select size="1" name="cusTravelDetailId">
         <c:forEach var="cusTravelDetailVO" items="${cusTravelDetailSvc.all}" > 
          <option value="${cusTravelDetailVO.cusTravelDetailId}">${cusTravelDetailVO.detailName}
         </c:forEach>   
       </select>
       <input type="submit" value="送出">
       <input type="hidden" name="action" value="getOne_For_Display">
     </FORM>
  </li>
</ul>


<h3>遊記細節管理</h3>

<ul>
  <li><a href='addCusTravelDetail.jsp'>Add</a> a new CusTravelDetail.</li>
</ul>

</body>

</html>
