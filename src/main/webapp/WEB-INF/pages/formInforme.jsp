<%@page import="com.cesar.tcc.smartlibrary.utilities.Constants"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<jsp:include page="header.jsp" />

<div class="well lead"><spring:message code="title.form.informe" /></div>

	<form:form method="POST" modelAttribute="informe" class="form-horizontal">
	<form:input type="hidden" path="id" id="id"/>
	
	<div class="form-group">
		<label class="col-md-3 control-label" for="titulo"><spring:message code="label.titulo" /></label>
		<div class="col-md-7">
			<form:input type="text" path="titulo" id="titulo" class="form-control input-sm"/>
			<div class="has-error">
				<form:errors path="titulo" class="help-inline"/>
			</div>
		</div>
	</div>
	
	<div class="form-group">
		<label class="col-md-3 control-label" for="text"><spring:message code="label.msg" /></label>
		<div class="col-md-7">
			<form:textarea type="text" path="text" id="text" class="form-control input-sm"/>
			<div class="has-error">
				<form:errors path="text" class="help-inline"/>
			</div>
		</div>
	</div>

	
	<div class="form-actions floatRight">
		<input type="submit" value="<spring:message code="label.button.save" />" class="btn btn-primary"/>
		<a href="<c:url value='/' />">
			<spring:message code="label.cancel" />
		</a>
	</div>
	
</form:form>

<jsp:include page="footer.jsp" />