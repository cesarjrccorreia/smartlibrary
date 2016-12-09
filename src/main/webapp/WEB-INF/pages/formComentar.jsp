<%@page import="com.cesar.tcc.smartlibrary.utilities.Constants"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<jsp:include page="header.jsp" />

<div class="well lead"><spring:message code="title.add.comment" /></div>

<div class="panel-body">
	<form:form method="POST" modelAttribute="comment" class="form-horizontal">
		<form:input type="hidden" path="id" id="id"/>
		
		<div class="form-group">
			<label class="col-md-3 control-label" for="title"><spring:message code="label.titulo" /></label>
			<div class="col-md-7">
				<form:input type="text" path="title" id="title" class="form-control input-sm"/>
				<div class="has-error">
					<form:errors path="title" class="help-inline"/>
				</div>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-3 control-label" for="rating"><spring:message code="label.book.rating" /></label>
			<div class="col-md-7">
				<form:input type="number" path="rating" id="rating" class="form-control input-sm"/>
				<div class="has-error">
					<form:errors path="rating" class="help-inline"/>
				</div>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-3 control-label" for="comment"><spring:message code="label.comment" /></label>
			<div class="col-md-7">
				<form:textarea type="text" path="comment" id="comment" class="form-control input-sm"/>
				<div class="has-error">
					<form:errors path="comment" class="help-inline"/>
				</div>
			</div>
		</div>
	
		<div class="form-actions floatRight">
			<input type="submit" value="<spring:message code="label.button.save" />" class="btn btn-primary"/>
			<a href="<c:url value='/books/detail-${comment.book.id}' />">
				<spring:message code="label.cancel" />
			</a>
		</div>
	</form:form>
</div>

<jsp:include page="footer.jsp" />