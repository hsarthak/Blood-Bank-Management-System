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
<a href="${pageContext.request.contextPath}/login/homebloodbank">Back</a><br/>
<h3><u>Logged In</u>: ${sessionScope.userName}</h3><br/><br/>
<h1>Blood Bank Donor's List</h1>
<table>
<tr>
<th>Donation Date</th>
<th>First Name</th>
<th>Last Name</th>
<th>DoB</th>
<th>Gender</th>
<th>Email</th>
<th>Phone</th>
<th>BloodType</th>
</tr>
<c:if test="${!requestScope.donationHistoryList.isEmpty()}">
	<c:forEach items="${requestScope.donationHistoryList}" var="history">
          <tr>
               <td><c:out value="${history.getDate()}" /></td>
               <td><c:out value="${history.getDonor().getFirstName()}" /></td>
               <td><c:out value="${history.getDonor().getLastName()}" /></td>
               <td><c:out value="${history.getDonor().getDateOfBirth()}" /></td>
               <td><c:out value="${history.getDonor().getGender()}" /></td>
               <td><c:out value="${history.getDonor().getEmail()}" /></td>
               <td><c:out value="${history.getDonor().getPhone()}" /></td>
               <td><c:out value="${history.getDonor().getBloodType()}" /></td>   
          </tr>
    </c:forEach>
</c:if> 

</table>

</body>
</html>