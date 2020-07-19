<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<h1>You are not signed in!</h1>
<c:if test="${request.getRequestURI().equals('/bloodbankmanagement/login/homedonor/donationhistory')}">
Please signed in as Donor <a href="http://localhost:8080/bloodbankmanagement/login?"> here</a>
</c:if>

<c:if test="${request.getRequestURI().equals('/bloodbankmanagement/login/homebloodbank/donateform')}">
Please signed in as BloodBank <a href="http://localhost:8080/bloodbankmanagement/login?"> here</a>
</c:if>
<c:if test="${request.getRequestURI().equals('/bloodbankmanagement/login/homebloodbank/searchdonor')}">
Please signed in as BloodBank <a href="http://localhost:8080/bloodbankmanagement/login?"> here</a>
</c:if>
<c:if test="${request.getRequestURI().equals('/bloodbankmanagement/login/homebloodbank/stocks')}">
Please signed in as BloodBank <a href="http://localhost:8080/bloodbankmanagement/login?"> here</a>
</c:if>
<c:if test="${request.getRequestURI().equals('/bloodbankmanagement/login/homebloodbank/bloodrequest')}">
Please signed in as BloodBank <a href="http://localhost:8080/bloodbankmanagement/login?"> here</a>
</c:if>

<c:if test="${request.getRequestURI().equals('/bloodbankmanagement/login/homehospital/sendrequest')}">
Please signed in as Hospital <a href="http://localhost:8080/bloodbankmanagement/login?"> here</a>
</c:if>
<c:if test="${request.getRequestURI().equals('/bloodbankmanagement/login/homehospital/bloodbanksstock')}">
Please signed in as Hospital <a href="http://localhost:8080/bloodbankmanagement/login?"> here</a>
</c:if>
<c:if test="${request.getRequestURI().equals('/bloodbankmanagement/login/homehospital/requesthistory')}">
Please signed in as Hospital <a href="http://localhost:8080/bloodbankmanagement/login?"> here</a>
</c:if>




</body>
</html>