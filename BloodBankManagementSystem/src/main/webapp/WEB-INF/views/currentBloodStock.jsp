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
  width:98%;
}

table, th, td {
  text-align:center;
  border: 1px solid black;
}
</style>
</head>
<body>
<a href="${pageContext.request.contextPath}/home">Logout</a><br/>
<a href="${pageContext.request.contextPath}/login/homehospital">Back</a><br/>
<h3><u>Logged In</u>: ${sessionScope.userName}</h3><br/><br/>
<h1>Blood Availablity at the BloodBanks</h1><br/><br/>
<c:set var="srNo" value="0" scope="page"/>
<table>
<tr>
<th>Sr No.</th>
<th>Blood-Bank Name</th>
<th>Blood-Type</th>
<th>Amount Available</th>
</tr>
<tr><th></th><th></th><th></th><th></tr>
<c:if test="${!requestScope.bloodRequestsList.isEmpty()}">
	<c:forEach var="count" begin="0" end="${requestScope.values.size()-3}" step="3">
	<c:set var="srNo" value="${srNo+1}" scope="page"/>
          <tr>
               <td><c:out value="${srNo}" /></td>
               <td><c:out value="${requestScope.values.get(count+1)}" /></td> 
               <td><c:out value="${requestScope.values.get(count+2)}"/></td>
               <td><c:out value="${requestScope.values.get(count)}"/></td>          
          </tr>
    </c:forEach>
   
</c:if>
</table>

</body>
</html>