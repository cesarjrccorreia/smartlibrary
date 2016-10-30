<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title><spring:message code="title.access.denied" /></title>
</head>
<body>
	<div class="generic-container">
		<div class="authbar">
			<span><strong>${loggedinuser}</strong>,<spring:message code="msg.access.denied" /></span> <span class="floatRight"><a href="<c:url value="/logout" />">Logout</a></span>
		</div>
	</div>
</body>
</html>