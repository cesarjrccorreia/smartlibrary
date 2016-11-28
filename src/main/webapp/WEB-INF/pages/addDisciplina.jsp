<%@page import="com.cesar.tcc.smartlibrary.utilities.Constants"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<jsp:include page="header.jsp" />

	<div class="well lead"><spring:message code="title.add.disciplina" /></div>
 	<form:form method="POST" modelAttribute="user" class="form-horizontal">
 		<form:input type="hidden" path="id" id="id"/>
 		
		<div class="form-group">
			<label class="col-md-3 control-label" for="name"><spring:message code="label.name" /></label>
			<div class="col-md-7">
				<form:input type="text" path="name" id="name" class="form-control input-sm" />
				<div class="has-error">
					<form:errors path="name" class="help-inline"/>
				</div>
			</div>
		</div>
		
		<div class="form-group">
			<label class="col-md-3 control-label" for="disciplinas"><spring:message code="label.disciplina" /></label>
			<div class="col-md-7">
				<form:select path="disciplinas" items="${disciplinas}" multiple="true" itemValue="id" itemLabel="name" class="form-control input-sm" />
				<div class="has-error">
					<form:errors path="disciplinas" class="help-inline"/>
				</div>
			</div>
		</div>

		<div class="form-group">
			<div class="form-actions floatRight">
				<c:choose>
					<c:when test="${edit}">
						<input type="submit" value="<spring:message code="label.button.update" />" class="btn btn-primary"/>
					</c:when>
					<c:otherwise>
						<input type="submit" value="<spring:message code="label.button.save" />" class="btn btn-primary"/>
					</c:otherwise>
				</c:choose>
				<a href="<c:url value='/' />"><spring:message code="label.cancel" /></a>
			</div>
		</div>
		
	</form:form>
	
<jsp:include page="footer.jsp" />