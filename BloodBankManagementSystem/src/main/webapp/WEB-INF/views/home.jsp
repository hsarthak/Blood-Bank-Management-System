<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
	<%@ include file="/resources/head.jsp"%>
	
</head>
<body>
<h1>
	Welcome to Blood Bank Management System  
</h1><br/><br/>

<p><strong>Register</strong></P>
<ul>
<li><a href="${pageContext.request.contextPath}/registerdonor">DONOR</a></li>
<li><a href="${pageContext.request.contextPath}/registerbloodbank">BLOOD BANK</a></li>
<li><a href="${pageContext.request.contextPath}/registerhospital">HOSPITAL</a></li>	
</ul>
<br/>
<br/>
<p><strong>LOGIN</strong></p>

<form:form action="${pageContext.request.contextPath}/login" method="get"> 
	<input type="submit" value="Login" /><br/>
</form:form>
</body>
</html>
