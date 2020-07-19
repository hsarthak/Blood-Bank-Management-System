<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>     
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ include file="/resources/head.jsp"%>
<style>
table {
  border-collapse: collapse;
  width:90%;
}

table, th, td {
  text-align:center;
  border: 1px solid black;
}
</style>
</head>
<body>
<a href="${pageContext.request.contextPath}/home">Logout</a><br/>
<a href="${pageContext.request.contextPath}/login/homebloodbank">Back</a><br/>
<h3><u>Logged In</u>: ${sessionScope.userName}</h3><br/><br/>
<h1>Blood Bank Stocks</h1><br/><br/>
<table>
<tr>
<th>Blood Type</th>
<th>Blood Amount (ml)</th>
</tr>
<c:if test="${!requestScope.stockList.isEmpty()}">
	<c:forEach items="${requestScope.stockList}" var="stock">
          <tr>
               <td><c:out value="${stock[0]}" /></td>
               <td><c:out value="${stock[1]}" /></td>   
          </tr>
    </c:forEach>
</c:if> 

</table>



</body>
</html>