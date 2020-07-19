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

<h1>Welcome to Donor Registration</h1><br/>
<!-- commandName="donor" -->
<form:form  action="registerdonor" modelAttribute="donor">


<label>Username</label>
<form:input type="text" path="userName" placeholder="Donor Username"/><form:errors path="userName"/><br/>

<label>Password</label>
<form:input type="password" path="password"/><form:errors path="password"/><br/>

<label>First Name</label>
<form:input type="text" path="firstName" placeholder="Donor First Name"/> <form:errors path="firstName"/><br/>

<label>Last Name</label>
<form:input type="text" path="lastName" placeholder="Donor Last Name"/> <form:errors path="lastName"/><br/>

<label>Date of Birth</label>
<input type="date" name="dob" placeholder="YYYY-MM-DD"/><br/>

<label>Blood Type</label>
<form:select path="bloodType">
    <form:option value="A+" label="A+"/>
    <form:option value="A-" label="A-"/>
    <form:option value="AB+" label="AB+"/>
    <form:option value="AB-" label="AB-"/>
    <form:option value="B+" label="B+"/>
    <form:option value="B-" label="B-"/>
    <form:option value="O+" label="O+"/>
    <form:option value="O-" label="O-"/>
</form:select><form:errors path="bloodType"/><br/>

<label>Gender</label>
<form:select path="gender">
    <form:option value="Male" label="Male"/>
    <form:option value="Female" label="Female" />
    <form:option value="Other" label="Other" />
</form:select><form:errors path="gender"/><br/>

<label>Email</label>
<form:input type="text" path="email" placeholder="Donor Email"/><form:errors path="email"/><br/>

<label>Phone</label>
<form:input type="text" path="phone" placeholder="Donor Phone"/><form:errors path="phone"/><br/>

<input type="submit" value="Register Donor" />

</form:form>

</body>
</html>