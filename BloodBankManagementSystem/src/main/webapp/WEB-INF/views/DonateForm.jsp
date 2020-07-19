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
<a href="${pageContext.request.contextPath}/home">Logout</a><br/>
<a href="${pageContext.request.contextPath}/login/homebloodbank">Back</a><br/>
<h1><u>Logged In</u>: ${sessionScope.userName}</h1><br/><br/>
<h1>Donation Form</h1>
<br/>


<form action="${pageContext.request.contextPath}/login/homebloodbank/donateform" method="post">
<label>Name: </label><input type="text" name="donarName" /><br/>
<label>Email: </label><input type="text" name="donarEmail"></input><br/>
<label>Today's Date: </label><input type="date" name="donationDate"/><br>
<input type="submit" value="DONATE"/>

</form>

</body>
</html>