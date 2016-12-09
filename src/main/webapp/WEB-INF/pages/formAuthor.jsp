<%@page import="com.cesar.tcc.smartlibrary.utilities.Constants"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<jsp:include page="header.jsp" />

<div class="panel-body">

		<div class="well lead"><spring:message code="title.form.author" /></div>
		
	 	<form:form method="POST" modelAttribute="author" class="form-horizontal">
			<form:input type="hidden" path="id" id="id"/>
			
			<div class="form-group">
				<label class="control-label col-md-2" for="name"><spring:message code="label.name" /></label>
				<div class="col-md-8">
					<form:input type="text" path="name" id="name" class="form-control"/>
					<div class="has-error">
						<form:errors path="name" class="help-inline"/>
					</div>
				</div>
			</div>
	
			<div class="form-group">
				<div class="form-actions floatRight">
					<c:choose>
						<c:when test="${edit}">
							<input type="submit" value="<spring:message code="label.button.update" />" class="btn btn-primary"/>
							<a href="<c:url value='/authors' />"><spring:message code="label.cancel" /></a>
						</c:when>
						<c:otherwise>
							<input type="submit" value="<spring:message code="label.button.save" />" class="btn btn-primary "/>
							<a href="<c:url value='/authors' />"><spring:message code="label.cancel" /></a>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
		</form:form>
	</div>

<jsp:include page="footer.jsp" />