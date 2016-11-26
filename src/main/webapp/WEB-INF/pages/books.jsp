<%@page import="com.cesar.tcc.smartlibrary.utilities.Constants"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<jsp:include page="header.jsp" />

<div class="generic-container">

	<c:if test="${not empty success }">
		<div class="alert alert-success lead">${success}</div>
	</c:if>

	<div class="panel panel-default">
		<!-- Default panel contents -->
		<div class="panel-heading">
			<span class="lead"><spring:message code="label.list.book" />
			</span>
		</div>
		<table class="table table-hover">
			<thead>
				<tr>
					<th><spring:message code="label.book.isbn" /></th>
					<th><spring:message code="label.book.name" /></th>
					<th><spring:message code="label.book.edition" /></th>
					<th><spring:message code="label.book.quantity" /></th>
					<sec:authorize access="hasRole('Bibliotecaria')">
						<th width="100"></th>
					</sec:authorize>
					<sec:authorize access="hasRole('Bibliotecaria')">
						<th width="100"></th>
					</sec:authorize>

				</tr>
			</thead>
			<tbody>
				<c:forEach items="${books}" var="book">
					<tr>
						<td>${book.isbn}</td>
						<td>${book.name}</td>
						<td>${book.edition}</td>
						<td>${book.quantity}</td>
						<sec:authorize access="hasRole('Bibliotecaria')">
							<td>
								<a href="<c:url value='/books/edit-${book.name}' />"
									class="btn btn-success custom-width">
									<spring:message code="label.edit" />
								</a>
							</td>
							<td>
								<a href="<c:url value='/books/delete-${book.name}' />"
								class="btn btn-danger custom-width">
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
			<a href="<c:url value='/books/new' />"> <spring:message
					code="msg.add.element" arguments="livro" />
			</a>
		</div>
	</sec:authorize>
</div>

<jsp:include page="footer.jsp" />