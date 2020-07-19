<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ include file="/resources/head.jsp"%>
</head>
<body>
<a href="${pageContext.request.contextPath}/">Back</a><br/>

<h1>Welcome to Blood Bank Registration</h1><br/>
<!-- commandName="hospital" -->
<form:form  action="registerbloodbank" modelAttribute="bloodBank">

<label>Username</label>
<form:input type="text" path="userName" placeholder="BloodBank Username"/><form:errors path="userName"/><br/>

<label>Password</label>
<form:input type="password" path="password"/><form:errors path="password"/><br/>

<label>Name</label>
<form:input type="text" path="name" placeholder="BloodBank Name"/> <form:errors path="name"/><br/>

<label>Email</label>
<form:input type="text" path="email" placeholder="BloodBank Email"/><form:errors path="email"/><br/>

<label>Phone</label>
<form:input type="text" path="phone" placeholder="BloodBank Phone"/><form:errors path="phone"/><br/>

<input type="submit" value="Register Blood Bank" />

</form:form>

</body>
</html>