<%@page import="com.cesar.tcc.smartlibrary.utilities.Constants"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<jsp:include page="header.jsp" />

	<div class="generic-container">
	
		<c:if test="${not empty success }">
			<div class="alert alert-success lead">
		    	${success}
			</div>
		</c:if>
		
		<div class="authbar">
			<span>
				<strong>${loggedinuser}</strong>, Seja Bem-Vindo.
			</span>
			<span class="floatRight">
				<a href="<c:url value='/' />"><spring:message code="label.main"/></a><br/>
				<a href="<c:url value="/logout" />"><spring:message code="label.button.logout"/></a>
			</span>
		</div>
		
		<div class="panel panel-default">
			  <!-- Default panel contents -->
		  	<div class="panel-heading">
		  		<span class="lead">
		  		</span>
	  		</div>
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
				<c:forEach items="${editoras}" var="editora">
					<tr>
						<td>${editora.id}</td>
						<td>${editora.name}</td>
					    <sec:authorize access="hasRole('Bibliotecaria')">
							<td>
								<a href="<c:url value='/editoras/edit-${editora.name}' />" class="btn btn-success custom-width">
									<spring:message code="label.edit" />
								</a>
							</td>
							<td>
								<a href="<c:url value='/editoras/delete-${editora.name}' />" class="btn btn-danger custom-width">
									<spring:message code="label.delete" />
								</a>
							</td>
        				</sec:authorize>
					</tr>
				</c:forEach>
	    		</tbody>
	    	</table>
		</div>
		<sec:authorize access="hasRole('Bibliotecaria')">
		 	<div class="well">
		 		<a href="<c:url value='/editoras/new' />">
		 			<spring:message code="msg.add.element" arguments="autor"/>
 				</a>
		 	</div>
	 	</sec:authorize>
   	</div>
   	
<jsp:include page="footer.jsp" />