<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<jsp:include page="header.jsp" />

	<div class="generic-container">
		<div class="authbar">
			<span><strong>${loggedinuser}</strong>,<spring:message code="msg.access.denied" /></span> <span class="floatRight"><a href="<c:url value="/logout" />">Logout</a></span>
		</div>
	</div>
	
<jsp:include page="footer.jsp" />