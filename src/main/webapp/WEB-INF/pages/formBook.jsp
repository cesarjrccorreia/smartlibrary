<%@page import="com.cesar.tcc.smartlibrary.utilities.Constants"%>
<%@page import="java.time.Year"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<jsp:include page="header.jsp" />

 	<div class="generic-container">

		<div class="well lead"><spring:message code="title.form.book" /></div>
	 	<form:form method="POST" modelAttribute="book" class="form-horizontal">
			<form:input type="hidden" path="id" id="id"/>
			
			<div class="form-group">
				<label class="col-md-3 control-label" for="isbn"><spring:message code="label.book.isbn" /></label>
				<div class="col-md-7">
					<form:input type="text" path="isbn" id="isbn" class="form-control input-sm"/>
					<div class="has-error">
						<form:errors path="isbn" class="help-inline"/>
					</div>
				</div>
			</div>
	
			<div class="form-group">
				<label class="col-md-3 control-label" for="name"><spring:message code="label.book.name" /></label>
				<div class="col-md-7">
					<c:choose>
						<c:when test="${edit}">
							<form:input type="text" path="name" id="name" class="form-control input-sm" disabled="true"/>
						</c:when>
						<c:otherwise>
							<form:input type="text" path="name" id="name" class="form-control input-sm" />
							<div class="has-error">
								<form:errors path="name" class="help-inline"/>
							</div>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
	
			<div class="form-group">
				<label class="col-md-3 control-label" for="year"><spring:message code="label.book.year" /></label>
				<div class="col-md-7">
					<form:input type="number" min="1900" max="<%= Constants.CURRENT_YEAR %>" path="year" id="year" class="form-control input-sm" />
					<div class="has-error">
						<form:errors path="year" class="help-inline"/>
					</div>
				</div>
			</div>

			<div class="form-group">
				<label class="col-md-3 control-label" for="edition"><spring:message code="label.book.edition" /></label>
				<div class="col-md-7">
					<form:input type="number" min="1" path="edition" id="edition" class="form-control input-sm" />
					<div class="has-error">
						<form:errors path="edition" class="help-inline"/>
					</div>
				</div>
			</div>
		
			<div class="form-group">
				<label class="col-md-3 control-label" for="summary"><spring:message code="label.book.summary" /></label>
				<div class="col-md-7">
					<form:textarea path="summary" id="summary" class="form-control input-sm" />
					<div class="has-error">
						<form:errors path="summary" class="help-inline"/>
					</div>
				</div>
			</div>
		
			<div class="form-group">
				<label class="col-md-3 control-label" for="image"><spring:message code="label.book.image" /></label>
				<div class="col-md-7">
					<form:input type="file" path="image" id="image" class="form-control" />
					<div class="has-error">
						<form:errors path="image" class="help-inline"/>
					</div>
				</div>
			</div>
			
			<div class="form-group">
				<label class="col-md-3 control-label" for="quantity"><spring:message code="label.book.quantity" /></label>
				<div class="col-md-7">
					<form:input type="number" min="1" path="quantity" id="quantity" class="form-control input-sm" />
					<div class="has-error">
						<form:errors path="quantity" class="help-inline"/>
					</div>
				</div>
			</div>
			
			<div class="form-group">
				<label class="col-md-3 control-label" for="authors"><spring:message code="label.book.authors" /></label>
				<div class="col-md-7">
					<form:select path="authors" items="${allAuthors}" multiple="true" itemValue="id" itemLabel="name" class="form-control input-sm" />
					<div class="has-error">
						<form:errors path="authors" class="help-inline"/>
					</div>
				</div>
			</div>
			
			<div class="form-group">
				<label class="col-md-3 control-label" for="editora" ><spring:message code="label.book.editoras" /></label>
				<div class="col-md-7">
					<form:select path="editora" class="form-control" >
						<c:if test="${book.editora == null }">
							<form:option value="0" label="--- Selecione uma editora ---" disabled="true" selected="selected" />
						</c:if>
						<form:options items="${allEditoras}" itemValue="id" itemLabel="name"  />
					</form:select>
					<div class="has-error">
						<form:errors path="editora" class="help-inline"/>
					</div>
				</div>
			</div>
		
			<div class="form-actions floatRight">
				<c:choose>
					<c:when test="${edit}">
						<input type="submit" value="<spring:message code="label.button.update" />" class="btn btn-primary"/>
						<a href="<c:url value='/books' />">
							<spring:message code="label.cancel" />
						</a>
					</c:when>
					<c:otherwise>
						<input type="submit" value="<spring:message code="label.button.save" />" class="btn btn-primary"/>
						<a href="<c:url value='/books' />">
							<spring:message code="label.cancel" />
						</a>
					</c:otherwise>
				</c:choose>
			</div>
				
		</form:form>
	</div>
	
<jsp:include page="footer.jsp" />