<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Lista de autores</title>
	<link href="<c:url value='/resources/static/css/bootstrap.min.css' />" rel="stylesheet"></link>
	<link href="<c:url value='/resources/static/css/smartlibrary.css' />" rel="stylesheet"></link>
</head>

<body>
	<div class="generic-container">
		<div class="panel panel-default">
			  <!-- Default panel contents -->
		  	<div class="panel-heading"><span class="lead"><spring:message code="label.list.author"/> </span></div>
			<table class="table table-hover">
	    		<thead>
		      		<tr>
				        <th><spring:message code="label.code"/></th>
				        <th><spring:message code="label.name"/></th>
				        <sec:authorize access="hasRole('Bibliotecaria')">
				        	<th width="100"></th>
				        </sec:authorize>
				        <sec:authorize access="hasRole('Bibliotecaria')">
				        	<th width="100"></th>
				        </sec:authorize>
				        
					</tr>
		    	</thead>
	    		<tbody>
				<c:forEach items="${authors}" var="author">
					<tr>
						<td>${author.id}</td>
						<td>${author.name}</td>
					    <sec:authorize access="hasRole('Bibliotecaria')">
							<td><a href="<c:url value='/edit-author-${author.name}' />" class="btn btn-success custom-width">edit</a></td>
							<td><a href="<c:url value='/delete-author-${author.name}' />" class="btn btn-danger custom-width">delete</a></td>
        				</sec:authorize>
					</tr>
				</c:forEach>
	    		</tbody>
	    	</table>
		</div>
		<sec:authorize access="hasRole('Bibliotecaria')">
		 	<div class="well">
		 		<a href="<c:url value='/authors/new' />"><spring:message code="msg.add.element" arguments="autor"/></a>
		 	</div>
	 	</sec:authorize>
   	</div>
</body>
</html>