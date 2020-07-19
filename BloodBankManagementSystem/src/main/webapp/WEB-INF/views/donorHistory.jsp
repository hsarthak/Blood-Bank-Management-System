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
  border: 1px solid black;
}
</style>
</head>
<body>
<a href="${pageContext.request.contextPath}/home">Logout</a><br/>
<a href="${pageContext.request.contextPath}/login/homedonor">Back</a><br/>
<h3><u>Logged In</u>: ${sessionScope.userName}</h3><br/><br/>
<h1>Donor History</h1><br/><br/>
<table>
<tr>
<th>Donation Date</th>
<th>Blood Bank</th>
<th>Blood Bank Email</th>
<th>Blood Bank Phone</th>
</tr>
<c:if test="${!requestScope.donationHistoryList.isEmpty()}">
	<c:forEach items="${requestScope.donationHistoryList}" var="history">
          <tr>
               <td><c:out value="${history.getDate()}" /></td>
               <td><c:out value="${history.getBloodBank().getName()}" /></td>
               <td><c:out value="${history.getBloodBank().getEmail()}" /></td>
               <td><c:out value="${history.getBloodBank().getPhone()}" /></td>
          </tr>
    </c:forEach>
</c:if> 

</table>


</body>
</html>