<%@page import="com.cesar.tcc.smartlibrary.utilities.Constants"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<jsp:include page="header.jsp" />

<div class="panel-body">
	<div class="well lead"><spring:message code="title.form.user" /></div>
 	<form:form method="POST" modelAttribute="user" class="form-horizontal">
		<form:input type="hidden" path="id" id="id"/>
		
		<div class="form-group">
			<label class="col-md-3 control-label" for="name"><spring:message code="label.name" /></label>
			<div class="col-md-7">
				<form:input type="text" path="name" id="name" class="form-control input-sm"/>
				<div class="has-error">
					<form:errors path="name" class="help-inline"/>
				</div>
			</div>
		</div>

		<div class="form-group">
			<label class="col-md-3 control-label" for="username"><spring:message code="label.username" /></label>
			<div class="col-md-7">
				<c:choose>
					<c:when test="${edit}">
						<form:input type="text" path="username" id="username" class="form-control input-sm" disabled="true"/>
					</c:when>
					<c:otherwise>
						<form:input type="text" path="username" id="username" class="form-control input-sm" />
						<div class="has-error">
							<form:errors path="username" class="help-inline"/>
						</div>
					</c:otherwise>
				</c:choose>
			</div>
		</div>

		<div class="form-group">
			<label class="col-md-3 control-label" for="password"><spring:message code="label.password" /></label>
			<div class="col-md-7">
				<form:input type="password" path="password" id="password" class="form-control input-sm" />
				<div class="has-error">
					<form:errors path="password" class="help-inline"/>
				</div>
			</div>
		</div>

		<div class="form-group">
			<label class="col-md-3 control-label" for="email"><spring:message code="label.email" /></label>
			<div class="col-md-7">
				<form:input type="email" path="email" id="email" class="form-control input-sm" />
				<div class="has-error">
					<form:errors path="email" class="help-inline"/>
				</div>
			</div>
		</div>

		<div class="form-group">
			<label class="col-md-3 control-label" for="userProfiles"><spring:message code="label.roles" /></label>
			<div class="col-md-7">
				<form:select path="userProfiles" items="${roles}" multiple="true" itemValue="id" itemLabel="type" class="form-control input-sm" />
				<div class="has-error">
					<form:errors path="userProfiles" class="help-inline"/>
				</div>
			</div>
		</div>

		<div class="form-group">
			<div class="form-actions floatRight">
				<c:choose>
					<c:when test="${edit}">
						<input type="submit" value="<spring:message code="label.button.update" />" class="btn btn-primary"/>
						<a href="<c:url value='/users' />"><spring:message code="label.cancel" /></a>
					</c:when>
					<c:otherwise>
						<input type="submit" value="<spring:message code="label.button.save" />" class="btn btn-primary"/>
						<a href="<c:url value='/users' />"><spring:message code="label.cancel" /></a>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
	</form:form>
</div>
	
<jsp:include page="footer.jsp" />