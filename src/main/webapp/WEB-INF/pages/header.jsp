<%@page import="com.cesar.tcc.smartlibrary.utilities.Constants"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ page isELIgnored="false" %>

<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title><spring:message code="title.app" /></title>
	<script type="text/javascript" src="<c:url value="/resources/static/js/jquery-3.1.1.min.js" />"></script>
	<script type="text/javascript" src="<c:url value="/resources/static/js/bootstrap.min.js" />"></script>
	
	<link href="<c:url value='/resources/static/css/bootstrap.min.css' />" rel="stylesheet"></link>
	<link href="<c:url value='/resources/static/css/normalize.css' />" rel="stylesheet"></link>
	<link href="<c:url value='/resources/static/css/smartlibrary.css' />" rel="stylesheet"></link>
</head>

<body class="alt-grid">
	<div class="container">
		<nav class="header navbar navbar-inverse">
			<div class="container-fluid">
				<div class="navbar-header">
					<span class="navbar-brand logo active"><spring:message code="title.app" /></span>
				</div>
				<ul class="nav navbar-nav">
					<li><a id="inicio" href="<c:url value='/' />"><spring:message code="label.main"/></a><br/></li>
					<li><a id="user" href="<c:url value='<%= Constants.USER_PAGE %>' />"><spring:message code="label.user"/></a></li>
					<li><a id="author" href="<c:url value='<%= Constants.AUTHOR_PAGE %>' />"><spring:message code="label.author"/></a></li>
					<li><a id="editora" href="<c:url value='<%= Constants.EDITORA_PAGE %>' />"><spring:message code="label.editora"/></a><br/></li>		
					<li><a id="book" href="<c:url value='<%= Constants.BOOK_PAGE %>' />"><spring:message code="label.book"/></a><br/></li>
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<li>
						<a href="#"><span class="glyphicon glyphicon-user"></span> ${loggedinuser}</a>
					</li>
					<li>
						<a href="<c:url value="/logout" />">
							<span class="glyphicon glyphicon-log-out"></span>
							<spring:message code="label.button.logout"/>
						</a>
					</li>
				</ul>
			</div>
		</nav>
