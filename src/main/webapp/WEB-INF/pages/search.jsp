<%@page import="com.cesar.tcc.smartlibrary.utilities.Constants"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<jsp:include page="header.jsp" />
	
<div class="panel-body">
	
	<form:form  method="POST" >
		<div class="input-group input-group-lg">
		    <input type="text" class="form-control" name="search" value="${search}" placeholder="<spring:message code="label.search" />" />
		    <div class="input-group-btn">
		      <button class="btn btn-default" type="submit">
		        <i class="glyphicon glyphicon-search"></i>
		      </button>
		    </div>
		</div>
	</form:form>
	
	<c:if test="${ empty books }">
		<p><spring:message code="msg.not.found.search" /></p>
	</c:if>		
	
	<c:if test="${not empty books }">
		<table class="table table-hover">
   			<thead>
	      		<tr>
			        <th><spring:message code="label.code"/></th>
			        <th><spring:message code="label.book"/></th>
			        <th><spring:message code="label.book.year"/></th>
			        <th><spring:message code="label.book.edition"/></th>
			        <th><spring:message code="label.book.rating"/></th>
		        	<th width="100"></th>
		        	<th width="100"></th>
				</tr>
	    	</thead>
    		<tbody>
				<c:forEach items="${books}" var="book">
					<tr>
						<td>${book.id}</td>
						<td>${book.name}</td>
						<td>${book.year}</td>
						<td>${book.edition}</td>
						<td>${book.rating}</td>
						<td>
							<a href="<c:url value='/book/reservar-${book.id}' />" class="btn btn-primary custom-width">
								<spring:message code="label.button.reservar" />
							</a>
						</td>
						<td>
							<a href="<c:url value='/book/details-${book.id}' />" class="btn btn-default custom-width">
								<spring:message code="label.button.details" />
							</a>
						</td>
					</tr>
				</c:forEach>
	   		</tbody>
  		</table>
	</c:if>
</div>
   	
<jsp:include page="footer.jsp" />