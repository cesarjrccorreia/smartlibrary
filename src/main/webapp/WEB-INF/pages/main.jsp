<%@page import="com.cesar.tcc.smartlibrary.utilities.Constants"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<jsp:include page="header.jsp" />

	<div class="container">
		<header>
		</header>
		<div id="center">
		
		</div>
		<div id="recommender">
		
		</div>
		<div id="">
		</div>
		<h1>Olá</h1>
		<label>Selecine a área de cadastro que deseja ir:</label><br/>
		<a href="<c:url value='<%= Constants.USER_PAGE %>' />"><spring:message code="label.user"/></a><br/>
		<a href="<c:url value='<%= Constants.AUTHOR_PAGE %>' />"><spring:message code="label.author"/></a><br/>
		<a href="<c:url value='<%= Constants.EDITORA_PAGE %>' />"><spring:message code="label.editora"/></a><br/>
   	</div>
   	
<jsp:include page="footer.jsp" />