<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ include file="/resources/head.jsp"%>
</head>
<body>

<% System.out.println("on page HomeDonor");  %>
<h3><u>Logged In</u>: ${sessionScope.userName}</h3><br/><br/>
<h1>Donor's Login Home Page</h1><br/><br/>

<div>
<a href="${pageContext.request.contextPath}/login/homedonor/donationhistory">Donation History</a><br/>
<a href="${pageContext.request.contextPath}/home">Logout</a>
</div>

</body>
</html>