<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ include file="/resources/head.jsp"%>
</head>
<body>
<a href="${pageContext.request.contextPath}/login/homehospital">Back</a><br/>
<h3><u>Logged In</u>: ${sessionScope.userName}</h3><br/><br/>
<h1>Blood Request</h1>
<form:form  action="${pageContext.request.contextPath}/login/homehospital/sendrequest" modelAttribute="bloodRequest">

<label>Blood Amount:</label>
<form:input type="text" path="bloodAmount"/><form:errors path="bloodAmount"/><br/>

<!--  Select for blood type-->
<label>Select Blood Type:</label>
<form:select path="bloodType" name="bloodType">
    <form:option value="A+" label="A+"/>
    <form:option value="A-" label="A-"/>
    <form:option value="AB+" label="AB+"/>
    <form:option value="AB-" label="AB-"/>
    <form:option value="B+" label="B+"/>
    <form:option value="B-" label="B-"/>
    <form:option value="O+" label="O+"/>
    <form:option value="O-" label="O-"/>
</form:select><form:errors path="bloodType"/><form:errors path="bloodType"/><br/>

<!--  Dynamic Select for bloodbank name-->
<label>Select Blood Bank:</label>
<select name="bloodBankName">
   <c:if test="${!requestScope.bloodBankList.isEmpty()}">
   	<c:forEach items="${requestScope.bloodBankList}" var="bank">
   	<option value="${bank.getName()}" label="${bank.getName()}"/>
   	</c:forEach>
   </c:if>
   
   <c:if test="${requestScope.bloodBankList.isEmpty()}">
   	<form:option value="noBloodBank" label="No BloodBank Available"/>
   </c:if>  
</select><br/>

 <input type="submit" value="Send Request"/><br/><br/>
 <input type="reset" value="Clear Form"/><br/>

</form:form>


</body>
</html>