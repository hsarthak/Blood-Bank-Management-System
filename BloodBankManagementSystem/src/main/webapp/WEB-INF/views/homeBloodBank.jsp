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
<% System.out.println("on page Blood Bank Dashboard");  %>
<h1><u>Logged In</u>: ${sessionScope.userName}</h1>
<h3>Blood Bank Login Home Page</h3><br/>
<div>
<a href="${pageContext.request.contextPath}/login/homebloodbank/donateform">DONATE</a><br/>
<a href="${pageContext.request.contextPath}/login/homebloodbank/searchdonor">SEARCH DONOR</a><br/>
<a href="${pageContext.request.contextPath}/login/homebloodbank/stocks">BLOOD BANK STOCK</a><br/>
<a href="${pageContext.request.contextPath}/login/homebloodbank/bloodrequest">BLOOD REQUESTS</a><br/>
<a href="${pageContext.request.contextPath}/home">Logout</a>

</div>


</body>
</html>