<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ include file="/resources/head.jsp"%>
</head>
<body>
<h1>Login </h1><br/><br/>
<form:form action="${pageContext.request.contextPath}/login" modelAttribute="loginUser"> 

	<select name="role">
	<option>Select the role</option>
	<option value="Donor">DONOR</option>
	<option value="BloodBank">BLOOD BANK</option>
	<option value="Hospital">HOSPITAL</option>	
	</select><br/>
	<br/>
	<label>Username:</label>
	<form:input type="text" path="userName" name="userName"/><form:errors path="userName"/><br/>
	<label>Password:</label>
	<form:input type="password" path="password" name="password"/><form:errors path="password"/><br/>
	<input type="submit" value="Login" /><br/>
</form:form>

</body>
</html>