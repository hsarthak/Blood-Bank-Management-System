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
<h1>Hospital Requests History</h1><br/><br/>
<c:set var="srNo" value="0" scope="page"/>
<table>
<tr>
<th>Sr No.</th>
<th>Date Requested</th>
<th>Blood-Bank Name</th>
<th>Blood-Bank Email</th>
<th>Blood-Bank Phone</th>
<th>Blood-Type</th>
<th>Amount Requested</th>
<th>Confirmation</th>
<th>Delete( <sup>*</sup>Only Pending Requests)</th>
</tr>
<tr><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th></tr>
<c:if test="${!requestScope.bloodRequestsList.isEmpty()}">
	<c:forEach items="${requestScope.bloodRequestsList}" var="bloodRequest">
	<c:set var="srNo" value="${srNo+1}" scope="page"/>
          <tr>
               <td><c:out value="${srNo}" /></td>
               <td><c:out value="${bloodRequest.getDate()}" /></td> 
               <td><c:out value="${bloodRequest.getBloodBank().getName()}"/></td>
               <td><c:out value="${bloodRequest.getBloodBank().getEmail()}"/></td>
               <td><c:out value="${bloodRequest.getBloodBank().getPhone()}"/></td>
               <td><c:out value="${bloodRequest.getBloodType()}"/></td>
               <td><c:out value="${bloodRequest.getBloodAmount()}"/></td>
               <td><c:out value="${bloodRequest.getConfirmation()}"/></td>
               <td>
               		<form action="${pageContext.request.contextPath}/login/homehospital/requesthistory" method="get">
               			<c:set var="bloodRequestId" value="${bloodRequest.getId()}"/>
               			<input type="hidden" value="${bloodRequestId}" name="bloodRequestId"/>
               			<input type="hidden" value="delete" name="delete"/>
               			<input type="submit" value="Delete"/>
               		</form>
               </td>
          </tr>
    </c:forEach>
   
</c:if>
</table>

<c:if test="${requestScope.bloodRequestsList.isEmpty()}">
<br/><h3>No Blood Requests Yet!!</h3>
</c:if> 


</body>
</html>