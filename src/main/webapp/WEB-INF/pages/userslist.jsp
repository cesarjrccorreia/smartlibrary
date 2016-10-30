<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Users List</title>
	<link href="<c:url value='/resources/static/css/bootstrap.min.css' />" rel="stylesheet"></link>
	<link href="<c:url value='/resources/static/css/smartlibrary.css' />" rel="stylesheet"></link>
</head>

<body>
	<div class="generic-container">
	
		<div class="authbar">
			<span><strong>${loggedinuser}</strong>, Seja Bem-Vindo.</span> <span class="floatRight"><a href="<c:url value="/logout" />"><spring:message code="label.button.logout"/></a></span>
		</div>
		
		<c:if test="${not empty success }">
			<div class="alert alert-success lead">
		    	${success}
			</div>
		</c:if>
		<div class="panel panel-default">
			  <!-- Default panel contents -->
		  	<div class="panel-heading"><span class="lead"><spring:message code="label.list.user"/></span></div>
			<table class="table table-hover">
	    		<thead>
		      		<tr>
				        <th><spring:message code="label.name"/></th>
				        <th><spring:message code="label.email"/></th>
				        <th><spring:message code="label.username"/></th>
				        <sec:authorize access="hasRole('Bibliotecaria')">
				        	<th width="100"></th>
				        	<th width="100"></th>
				        </sec:authorize>
				        
					</tr>
		    	</thead>
	    		<tbody>
				<c:forEach items="${users}" var="user">
					<tr>
						<td>${user.name}</td>
						<td>${user.email}</td>
						<td>${user.username}</td>
					    <sec:authorize access="hasRole('Bibliotecaria')">
							<td><a href="<c:url value='/edit-user-${user.username}' />" class="btn btn-success custom-width">edit</a></td>
							<td><a href="<c:url value='/delete-user-${user.username}' />" class="btn btn-danger custom-width">delete</a></td>
        				</sec:authorize>
					</tr>
				</c:forEach>
	    		</tbody>
	    	</table>
		</div>
		<sec:authorize access="hasRole('Bibliotecaria')">
		 	<div class="well">
		 		<a href="<c:url value='/newuser' />"><spring:message code="msg.add.element" arguments="usuário"/></a>
		 	</div>
	 	</sec:authorize>
   	</div>
</body>
</html>