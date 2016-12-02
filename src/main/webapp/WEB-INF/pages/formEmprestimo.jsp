<%@page import="com.cesar.tcc.smartlibrary.utilities.Constants"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<jsp:include page="header.jsp" />

<div class="panel-body">
	<div class="well lead"><spring:message code="title.form.emprestimo" /></div>
 		<form:form method="POST" modelAttribute="emprestimo" class="form-horizontal">
			<form:input type="hidden" path="id" id="id"/>
			
			<div class="form-group">
				<label class="col-md-3 control-label" for="user"><spring:message code="label.user" /></label>
				<div class="col-md-7">
					<form:select path="user" items="${users}" multiple="false" itemValue="id" itemLabel="name" class="form-control input-sm" />
					<div class="has-error">
						<form:errors path="user" class="help-inline"/>
					</div>
				</div>
			</div>
			
			<div class="form-group">
				<label class="col-md-3 control-label" for="book" ><spring:message code="label.book" /></label>
				<div class="col-md-7">
					<form:select path="book" class="form-control" >
						<c:if test="${book == null }">
							<form:option value="0" label="--- Selecione uma livro ---" disabled="true" selected="selected" />
						</c:if>
						<form:options items="${books}" itemValue="id" itemLabel="name"  />
					</form:select>
					<div class="has-error">
						<form:errors path="book" class="help-inline"/>
					</div>
				</div>
			</div>
			
			<div class="form-group">
				<label class="col-md-3 control-label" for="periodo"><spring:message code="label.periodo" /></label>
				<div class="col-md-7">
					<form:input type="number" path="periodo" id="perido" class="form-control input-sm" />
					<div class="has-error">
						<form:errors path="periodo" class="help-inline"/>
					</div>
				</div>
			</div>
		
			<div class="form-actions floatRight">
				<c:choose>
					<c:when test="${edit}">
						<input type="submit" value="<spring:message code="label.button.update" />" class="btn btn-primary"/>
						<a href="<c:url value='/' />">
							<spring:message code="label.cancel" />
						</a>
					</c:when>
					<c:otherwise>
						<input type="submit" value="<spring:message code="label.button.save" />" class="btn btn-primary"/>
						<a href="<c:url value='/' />">
							<spring:message code="label.cancel" />
						</a>
					</c:otherwise>
				</c:choose>
			</div>
				
		</form:form>
</div>
	
<jsp:include page="footer.jsp" />