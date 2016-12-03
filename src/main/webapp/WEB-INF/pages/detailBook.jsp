<%@page import="com.cesar.tcc.smartlibrary.utilities.Constants"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<jsp:include page="header.jsp" />

<div class="panel-body">
	<br><br>
	<div class="col-xs-2 col-sm-2">
		<!-- <img alt="Imagem" src="#" class="img-thumbnail"> -->
		<i class="glyphicon glyphicon-qrcode" style="font-size: 100px;" ></i>
	</div>
	
	<div class="col-xs-12 col-sm-6 col-md-8">
		<dl class="dl-horizontal">
			<dt><spring:message code="label.book.isbn" /></dt>
			<dd><c:out value="${book.isbn}" /></dd>
			<dt><spring:message code="label.book.name" /></dt>
			<dd><c:out value="${book.name}" /></dd>
			<dt><spring:message code="label.book.year" /></dt>
			<dd><c:out value="${book.year}" /></dd>
			<dt><spring:message code="label.book.edition" /></dt>
			<dd><c:out value="${book.edition}" /></dd>
			<dt><spring:message code="label.book.summary" /></dt>
			<dd><c:out value="${book.summary}" /></dd>
			<dt><spring:message code="label.book.rating" /></dt>
			<dd><c:out value="${book.rating}" /></dd>
			<dt><spring:message code="label.book.authors" /></dt>
			<dd></dd>
			<dt><spring:message code="label.book.editoras" /></dt>
			<dd></dd>
		</dl>
		<br><br>
		<div class="floatRight">
			<a href="<c:url value='/books/reservar-${book.id}' />" class="btn btn-info">
				<spring:message code="label.button.reservar" />
			</a>
			
			<sec:authorize access="hasRole('Professor')">
				<a href="<c:url value='/books/indicar-${book.id}' />" class="btn btn-default">
					<spring:message code="label.button.indicar" />
				</a>
			</sec:authorize>
			
		</div>
	</div>
	
	<div class="comment col-md-8 list-group">
		<c:forEach var="comment" items="${book.comments}">
			<div class="list-group-item list-group-item-action">
				<h3 class="list-group-item-heading">${comment.title}</h3>
				<p class="list-group-item-text" >${comment.comment}</p>
				<p class="list-group-item-text" >${comment.rating}</p>
			</div>
		</c:forEach>
	</div>
</div>

<jsp:include page="footer.jsp" />