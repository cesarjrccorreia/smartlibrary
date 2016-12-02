<%@ page import="com.cesar.tcc.smartlibrary.utilities.Constants"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
					<li><a id="inicio" href="<c:url value='/' />"><spring:message code="label.main"/></a></li>
					<sec:authorize access="hasRole('Bibliotecaria')">
					
						<li class="dropdown">
							<a id="cadastro" class="dropdown-toggle" data-toggle="dropdown" href="<c:url value='#' />">
								<spring:message code="label.manager"/>
								<span class="caret"></span>
							</a>
							<ul class="dropdown-menu">
								<li><a id="user" href="<c:url value='<%= Constants.USER_PAGE %>' />"><spring:message code="label.user"/></a></li>
								<li><a id="author" href="<c:url value='<%= Constants.AUTHOR_PAGE %>' />"><spring:message code="label.author"/></a></li>
								<li><a id="editora" href="<c:url value='<%= Constants.EDITORA_PAGE %>' />"><spring:message code="label.editora"/></a></li>		
								<li><a id="book" href="<c:url value='<%= Constants.BOOK_PAGE %>' />"><spring:message code="label.book"/></a></li>
								<li><a id="disciplina" href="<c:url value='<%= Constants.DISCIPLINA_PAGE %>' />"><spring:message code="label.disciplina"/></a></li>
							</ul>
						</li>

						<li class="dropdown">
							<a id="emprestimo" class="dropdown-toggle" data-toggle="dropdown" href="<c:url value='#' />">
								<spring:message code="title.emprestimo"/>
								<span class="caret"></span>
							</a>
							<ul class="dropdown-menu">
								<li><a id="newEmprestimo" href="<c:url value='<%= Constants.NEW_EMPRESTIMO %>' />"><spring:message code="label.new"/></a></li>
								<li><a id="closeEmprestimo" href="<c:url value='<%= Constants.CLOSE_EMPRESTIMO %>' />"><spring:message code="label.close"/></a></li>
							</ul>
						</li>
						
						<li><a id="add_informe" href="<c:url value='<%= Constants.INFORME_ADD %>' />"><spring:message code="label.informe.add"/></a></li>
					</sec:authorize>
					
					<sec:authorize access="hasRole('Aluno') or hasRole('Professor')">
						<li><a id="add_disciplina" href="<c:url value='<%= Constants.ADD_DISCIPLINA %>' />"><spring:message code="label.disciplina.add"/></a></li>
						<li><a id="renovar_emprestimo" href="<c:url value='<%= Constants.RENOVAR %>' />-${loggedinuser}"><spring:message code="label.emprestimo.renovar"/></a></li>
					</sec:authorize>
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
		
		<c:if test="${not empty success }">
			<div id="alert" class="alert alert-success alert-dismissible">
				<a href="#" class="close" data-dismiss="alert">&times;</a>
				${success}
			</div>
		</c:if>
		<c:if test="${not empty info }">
			<div id="alert" class="alert alert-info alert-dismissible">
				<a href="#" class="close" data-dismiss="alert">&times;</a>
				${info}
			</div>
		</c:if>
		<c:if test="${not empty error }">
			<div id="alert" class="alert alert-danger alert-dismissible">
				<a href="#" class="close" data-dismiss="alert">&times;</a>
				${error}
			</div>
		</c:if>
